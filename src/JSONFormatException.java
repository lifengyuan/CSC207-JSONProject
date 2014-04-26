/**
 * JSON format error exception
 * 
 * @throw extendsException
 * 
 * @Fengyuan Li
 * 
 */
public class JSONFormatException
    extends
      Exception
{
  private static final long serialVersionUID = 1L;

  /**
   * @param (String) message
   */
  public JSONFormatException (String message)
  {
    // Constructs a new exception with the specified detail message
    super (message);
  }// JSONFormateException(String)
}// class JSONFormatException
