import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * 
 Copyright {2014} {Fengyuan Li}
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * class JSONParser
 * 
 * @author Fengyuan
 * 
 */
public class JSONParser
{

  // +--------+----------------------------------------------
  // | Fields |
  // +--------+
  /**
   * Determine if the source string still contains object that next() can
   * consume.
   */
  private static final String OBJECT_END = "OBJECT_END";
  /**
   * Determine if the source string still contains array that next() can
   * consume.
   */
  private static final String ARRAY_END = "ARRAY_END";

  /**
   * The comma flag determines if a comma should be output before the next
   * value.
   */
  private static final String COMMA = "COMMA";

  private static final String COLON = "COLON";

  private int index;
  private String json;
  private Object jsonObject;

  // +--------------+----------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * parse json string to HashTable
   * 
   * @param json
   * @return jsonObject
   * @throws JSONFormatException
   */
  @SuppressWarnings("unchecked")
  public JSONObject
    parse (String json)
      throws JSONFormatException
  {
    this.json = json;
    this.index = 0;
    jsonObject = parse ();
    return new JSONObject ((Hashtable<String, Object>) jsonObject);
  }// parse(String)

  // +---------+----------------------------------------
  // | Methods |
  // +---------+
  /**
   * parse json string to object
   * 
   * @return object
   * @throws JSONFormatException
   */
  private Object
    parse ()
      throws JSONFormatException
  {
    Object ret = null;

    // skip all white space
    while (Character.isWhitespace (json.charAt (index)))
      {
        index++;
      }// while

    char ch = json.charAt (index);

    // A value can be a string in double quotes, or a number, or true or false
    // or null, or an object or an array.
    if (ch == '"')
      {
        index++;
        ret = readString ('"');
      }// if
    else if (ch == '\'')
      {
        index++;
        ret = readString ('\'');
      }// else if
    else if (ch == ',')
      {
        index++;
        ret = COMMA;
      }// else if
    else if (ch == ':')
      {
        index++;
        ret = COLON;
      }
    else if (ch == '{')
      {
        index++;
        ret = readObject ();
      }
    else if (ch == '}')
      {
        index++;
        ret = OBJECT_END;
      }
    else if (ch == '[')
      {
        index++;
        ret = readArray ();
      }
    else if (ch == ']')
      {
        index++;
        ret = ARRAY_END;
      }
    else if (Character.isDigit (ch) || ch == '-')
      {
        ret = readNumber ();
      }
    else if (json.substring (index, index + 4).equals ("null"))
      {
        index += 4;
        ret = "null";
      }
    else if (json.substring (index, index + 4).equals ("true"))
      {
        index += 4;
        ret = new Boolean (true);
      }
    else if (json.substring (index, index + 5).equals ("false"))
      {
        index += 5;
        ret = new Boolean (false);
      }
    else
      {
        throw new JSONFormatException ("json string format error at " + index);
      }

    return ret;
  }// parse()

  /**
   * read a string value from json string in sheet or double quotes
   * 
   * @param start
   * @return value String
   */
  private String
    readString (char start)
  {
    StringBuffer str = new StringBuffer ();
    char ch = json.charAt (index);

    while (ch != start)
      {
        if (ch == '\\')
          {
            ch = json.charAt (++index);

            if (ch == '"')
              {
                str.append (ch);
              }
            else if (ch == '\\')
              {
                str.append (ch);
              }
            else if (ch == '/')
              {
                str.append (ch);
              }
            else if (ch == 'b')
              {
                str.append ("\b");
              }
            else if (ch == 'f')
              {
                str.append ("\f");
              }
            else if (ch == 'n')
              {
                str.append ("\n");
              }
            else if (ch == 'r')
              {
                str.append ("\r");
              }
            else if (ch == 't')
              {
                str.append ("\t");
              }
            else if (ch == 'u')
              {
                // unicode
                int value = 0;
                for (int i = 0; i < 4; ++i)
                  {
                    ch = json.charAt (++index);
                    switch (ch)
                      {
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                          value = (value << 4) + (ch - '0');
                          break;
                        case 'a':
                        case 'b':
                        case 'c':
                        case 'd':
                        case 'e':
                        case 'f':
                          value = (value << 4) + (ch - 'W');
                          break;
                        case 'A':
                        case 'B':
                        case 'C':
                        case 'D':
                        case 'E':
                        case 'F':
                          value = (value << 4) + (ch - '7');
                          break;
                      }// switch
                  }// for
                str.append ((char) value);
              }// else if
          }// if
        else
          {
            str.append (ch);
          }
        ch = json.charAt (++index);
      }
    index++;
    return str.toString ();
  }// readString(char)

  /**
   * read an object from json string.
   * 
   * @return a HashTable represent the json object
   * @throws JSONFormatException
   */
  private Hashtable<String, Object>
    readObject ()
      throws JSONFormatException
  {
    Hashtable<String, Object> table = new Hashtable<String, Object> ();
    Object next = parse ();

    if (!next.equals (OBJECT_END))
      {
        String key = next.toString ();

        while (!next.equals (OBJECT_END))
          {
            next = parse ();
            // Is the next is object or not?
            if (!next.equals (OBJECT_END))
              {
                Object value = parse ();
                table.put (key, value);

                next = parse ();
                // Is the next is comma or not?
                if (next.equals (COMMA))
                  {
                    next = parse ();

                    if (next instanceof String)
                      {
                        key = next.toString ();
                      }// if
                    else
                      {
                        // erro message report
                        throw new JSONFormatException ("json format error at "
                                                       + index);
                      }
                  }// if
              }// if
          }// while
      }// if
    return table;
  }// readObject()

  /**
   * read an array object from json string
   * 
   * @return a List represent an array in json Object
   * @throws JSONFormatException
   */
  private List<Object>
    readArray ()
      throws JSONFormatException
  {
    List<Object> array = new ArrayList<Object> ();
    Object next = parse ();
    // read following the list until the next is not a array
    while (!next.equals (ARRAY_END))
      {
        array.add (next);
        next = parse ();

        // if next is COMMA read the next Element
        if (next.equals (COMMA))
          {
            next = parse ();
          }// if
        else if (!next.equals (ARRAY_END))
          {
            throw new JSONFormatException ("No array end character");
          }// else if
      }// while

    return array;
  }// readArray()

  /**
   * read a number from json string
   * 
   * @return a Number object
   */
  private Number
    readNumber ()
  {
    StringBuffer str = new StringBuffer ();

    char ch = json.charAt (index);
    if (ch == '-')
      {
        str.append (ch);
        ch = json.charAt (++index);
      }// if

    while (Character.isDigit (ch))
      {
        str.append (ch);
        ch = json.charAt (++index);
      }// while

    if (ch == '.')
      {
        str.append (ch);
        ch = json.charAt (++index);
        while (Character.isDigit (ch))
          {
            str.append (ch);
            ch = json.charAt (++index);
          }// while
      }// if

    if (ch == 'e' || ch == 'E')
      {
        str.append (ch);
        ch = json.charAt (++index);
        if (ch == '+' || ch == '-')
          {
            str.append (ch);
            ch = json.charAt (++index);
          }// if

        while (Character.isDigit (ch))
          {
            str.append (ch);
            ch = json.charAt (++index);
          }// while
      }// if

    Number number = null;
    if (str.indexOf (".") >= 0 || str.indexOf ("e") >= 0
        || str.indexOf ("E") >= 0)
      {
        number = new BigDecimal (str.toString ());
      }// if
    else
      {
        number = new BigInteger (str.toString ());
      }
    return number;
  }// readNumber()
}// class JSONParser
