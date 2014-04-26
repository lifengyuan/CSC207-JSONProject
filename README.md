JSON Obeserver
==================
I would like to call this project JSON Observer since this observer is a library to view, edit about data structure,report error message, converse a JSON code to JAVA object. More detail about JSON, Please see its offical website: http://www.json.org/

JSON Obeserver features:
+++++++++++++++++++++++++

*support whitespace in input 
* Error messages
  * Useful/helpful
  * Indicate precisely where the error is
  * Line/Column
*pretty printer
*Support for unicode. eg: /u1234
*Support for take input from file, String, url source

I use the apporach of standard JAVA object to represent JSON numbers as Java Number object, JSON strings as Java String objects, Java arrays as List<Object> objects, and JSON objects as Java Hashtable<String,Object> objects.


JSONObject.java: The JSONObject can parse text from a String into a hashtable to produce a map-look object.

JSONFormatException.java: The JSONFormatException is the standard exception type thrown
by this package.

JSONWriter.java: The JSONWriter provides a convenient facility for building
JSON text through a writer.

JSONPrase.java:

PraseTest.java:
