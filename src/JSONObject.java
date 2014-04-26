import java.util.Hashtable;
import java.util.List;
import java.util.Set;

/**
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
   * JSONObject Constructor
   * 
   * @param table
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
   * get the key from table
   * 
   * @param key
   * @return object
   */

  public Object
    get (String key)
  {
    return table.get (key);
  }// get(String)

  /**
   * Get number from the key, if there do not have number in this key return
   * null.
   * 
   * @param key
   * @return number
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
   * remove the specific key from the table
   * 
   * @param key
   * 
   */
  public Object
    remove (String key)
  {
    return table.remove (key);
  }// remove(String)

  /**
   * get string from the key, if not a string return null
   * 
   * @param key
   * @return string
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
   * get all array from the key, if not return null
   * 
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
   * get object from the key converse the object to json object. if not return
   * null
   * 
   * @param key
   * @return JSONObject
   * 
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
   * Returns a Set of the keys contained in this table
   * 
   * @param
   * @return set
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
   * fix the format of the output
   * 
   */

  @Override
  public String
    toString ()
  {
    JSONWriter writer = new JSONWriter ();
    return writer.write (this);
  }// toString()
}// class JSONObject
