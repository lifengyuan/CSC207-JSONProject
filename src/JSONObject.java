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
 */

import java.util.Hashtable;
import java.util.List;
import java.util.Set;

/**
 *
 * JSONObject class
 * 
 * @author Fengyuan Li
 * 
 */
public class JSONObject
{

  // +--------+----------------------------------------
  // | Fields |
  // +--------+

  private Hashtable<String, Object> table;

  // +--------------+----------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Construct an empty JSONObject.
   */
  public JSONObject ()
  {
    this.table = new Hashtable<String, Object> ();
  }// JSONObject()

  public JSONObject (Hashtable<String, Object> table)
  {
    this.table = table;
  }// JSONObject(Hashtable<String,Object>

  // +---------+-------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get the value object associated with a key.
   * 
   * @param key
   *          A key string.
   * @return The object associated with the key.
   * 
   */

  public Object
    get (String key)
  {
    return table.get (key);
  }// get(String)

  /**
   * Get the int value associated with a key.
   * 
   * @param key
   *          A key string.
   * @return The numeric value. return null if the key is not found or if the
   *         value cannot be converted to an integer.
   */
  public Number
    getNumber (String key)
  {
    Object obj = get (key);
    if (obj instanceof Number)
      {
        return (Number) obj;
      }// if
    else
      {
        return null;
      }// else
  }

  /**
   * Remove a key and its value, if present.
   * 
   * @param key
   *          The name to be removed.
   * @return The value that was associated with the key, or null if there was no
   *         value.
   */
  public Object
    remove (String key)
  {
    return table.remove (key);
  }// remove(String)

  /**
   * Get the string associated with a key.
   * 
   * @param key
   *          A key string.
   * @return A string which is the value.
   * @return null if there is no string value for the key.
   */
  public String
    getString (String key)
  {
    Object obj = get (key);
    if (obj instanceof String)
      {
        return (String) obj;
      }// if
    else
      {
        return null;
      }
  }// getString(String)

  /**
   * Get the JSONArray value associated with a key.
   * 
   * @param key
   *          A key string.
   * @return A JSONArray which is the value.
   * @return null if the key is not found or if the value is not a JSONArray.
   */
  @SuppressWarnings("unchecked")
  public List<Object>
    getArray (String key)
  {
    Object obj = get (key);
    if (obj instanceof List)
      {
        return (List<Object>) obj;
      }// if
    else
      {
        return null;
      }
  }// getArray(String)

  /**
   * Get the JSONObject value associated with a key.
   * 
   * @param key
   *          A key string.
   * @return A JSONObject which is the value.
   * @return null if the key is not found or if the value is not a JSONObject.
   */
  @SuppressWarnings("unchecked")
  public JSONObject
    getJSONObject (String key)
  {
    Object obj = get (key);
    if (obj instanceof Hashtable)
      {
        return new JSONObject ((Hashtable<String, Object>) obj);
      }// if
    else
      {
        return null;
      }
  }// getJSONObject(String)

  /**
   * Get a set of keys of the JSONObject.
   * 
   * @return A keySet.
   */

  public Set<String>
    getKeySet ()
  {
    return table.keySet ();
  }// getKeySet()

  // +---------------+-------------------------------------------------
  // |Helper Methods |
  // +---------------+

  /**
   * Make a prettyprinted JSON text of this JSONObject. For compactness, no
   * whitespace is added. If this would not result in a syntactically correct
   * JSON text, then null will be returned instead.
   * 
   * Warning: This method assumes that the data structure is acyclical.
   * 
   * @return a printable, displayable, portable, transmittable representation of
   *         the object.
   */

  @Override
  public String
    toString ()
  {
    JSONWriter writer = new JSONWriter ();
    return writer.write (this);
  }// toString()
}// class JSONObject
