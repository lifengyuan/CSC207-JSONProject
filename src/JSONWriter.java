import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
 * JSONWriter class
 * 
 * @author Fengyuan Li
 */
public class JSONWriter
{
  // +--------+----------------------------------------------
  // | Fields |
  // +--------+

  private boolean isCompress = false;

  // +--------------+----------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * initialize the writer
   * 
   * @param JSONObject
   *          obj
   * @return String
   */
  public String
    write (JSONObject obj)
  {
    return write (0, obj);
  }

  public void
    setCompress (boolean compress)
  {
    this.isCompress = compress;
  }

  // +------------------+---------------------------------------
  // | Instance Methods |
  // +------------------+

  /**
   * Identify JSONOject and get evey key in the json object return each element
   * of this specific json object as a String
   * 
   * @param level
   * @param obj
   * @return String
   */
  @SuppressWarnings("unchecked")
  private String
    write (int level, JSONObject obj)
  {
    String str = "";
    if (!isCompress)
      {
        str += "\n";
        for (int i = 0; i < level; i++)
          {
            str += "\t";
          }// for
      }// if
    str += "{";
    if (!isCompress)
      {
        str += "\n";
      }// if

    Set<String> keySet = obj.getKeySet ();

    for (Iterator<String> iter = keySet.iterator (); iter.hasNext ();)
      {
        String key = iter.next ();

        if (!isCompress)
          {
            for (int i = 0; i <= level; i++)
              {
                str += "\t";
              }// for
          }// if

        str += '"' + key + "\":";
        Object value = obj.get (key);
        if (value instanceof Hashtable)
          {
            str += write (level + 1, obj.getJSONObject (key));
          }// if
        else if (value instanceof String)
          {
            str += '"' + value.toString () + '"';
          }// else if
        else if (value instanceof List)
          {
            str += getArrayString (level, (List<Object>) value);
          }// else
        else
          {
            str += value.toString ();
          }// else

        if (iter.hasNext ())
          {
            str += ",";
          }// if

        if (!isCompress)
          str += "\n";
      }// if

    if (!isCompress)
      {
        for (int i = 0; i < level; i++)
          {
            str += "\t";
          }// for
      }// if
    str += "}";
    return str;
  }// write(int, JSONObject)

  /**
   * get the list of array return a readable format as a string
   * 
   * @param level
   * @param list
   * @return string
   */
  @SuppressWarnings("unchecked")
  private String
    getArrayString (int level, List<Object> list)
  {
    String str = "[";
    for (Iterator<Object> iter = list.iterator (); iter.hasNext ();)
      {
        Object value = iter.next ();
        if (value instanceof Hashtable)
          {
            str += write (level + 1,
                          new JSONObject ((Hashtable<String, Object>) value));
          }// if
        else if (value instanceof String)
          {
            str += '"' + value.toString () + '"';
          }// else if
        else if (value instanceof List)
          {
            str += getArrayString (level, (List<Object>) value);
          }// else if
        else
          {
            str += value.toString ();
          }// else

        if (iter.hasNext ())
          {
            str += ",";
          }// if

        if (!isCompress)
          {
            str += " ";
          }// if
      }// for
    str += ']';
    return str;
  }// getArrayString(int, List<Object>)
}// class JSONWriter
