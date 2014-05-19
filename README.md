JSON Obeserver
==================
I would like to call this project JSON Observer since this observer is a library to view, edit about data structure,report error message, converse a JSON code to JAVA object. More detail about JSON, Please see its offical website: http://www.json.org/

### Features: 
#### Table observer:     ` private Hashtable<String, Object> table; `
      - Edit, add, move, remove,  fields and values.
      - Converse
      - Sort arrays and objects.
      - Search and get
      
#### Code observer: 
      - Format and compact JSON.
      - Support whitespace in input 
      - Error messages.  Location(line) and reason 
      - Unicode. eg: /u1234
      - Read from file, String, url source
  

### Example:

```html

  "GlossDef": {
                        "para": "A meta-markup language, used to create markup languages such as DocBook.",
                        "GlossSeeAlso": [
                            "GML",
                            "XML",
                            [
                                1,
                                2,
                                3,
                                4,
                                5.5,
                                0.000001
                            ],
                            {
                                "a": "b",
                                "c":[-1,-2,-3,-4]
                            }
                        ]
                    },


 "GlossDef": 
 {
         "para": "A meta-markup language, used to create markup languages such as DocBook.",
         "GlossSeeAlso": [ "GML", "XML", [ 1, 2, 3, 4, 5.5, 0.000001 ],
         {
                  "a": "b",
                  "c":[-1,-2,-3,-4]
          } ]
 },

```

####Unicode

```html

 "unicode": " \u123E" 
 
  
 
Expect result:ሾ
junit.framework.AssertionFailedError: expected:<> but was:< ሾ >
JSONFormatException: json string format error at 176

``` 


I use the apporach of standard JAVA object to represent JSON numbers as Java Number object, JSON strings as Java String objects, Java arrays as List<Object> objects, and JSON objects as Java Hashtable<String,Object> objects.


JSONObject.java: The JSONObject can parse text from a String into a hashtable to produce a map-look object.

JSONFormatException.java: The JSONFormatException is the standard exception type thrown
by this package.

JSONWriter.java: The JSONWriter provides a convenient facility for building
JSON text through a writer.

JSONPrase.java:

PraseTest.java:
