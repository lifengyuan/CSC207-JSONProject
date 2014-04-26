/**
 * 
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

/**
 * 
 * ParserTest class
 * 
 * @author Fengyuan Li
 * 
 */
public class ParserTest
{
  @SuppressWarnings("deprecation")
  @Test
  public void
    readTest ()
  {
    BufferedReader br = null;
    String contents = "";
    try
      {
        br = new BufferedReader (new FileReader (new File ("json.txt")));
        String line;
        while ((line = br.readLine ()) != null)
          {
            contents += line + "\n";
          }

        JSONParser reader = new JSONParser ();
        JSONObject result = reader.parse (contents);
        JSONWriter writer = new JSONWriter ();
        System.out.println (writer.write (result));
        Assert.assertEquals ("example glossary",
                             result.getJSONObject ("glossary")
                                   .getString ("title"));
        Assert.assertEquals ("ISO 8879:1986",
                             result.getJSONObject ("glossary")
                                   .getJSONObject ("GlossDiv")
                                   .getJSONObject ("GlossList")
                                   .getJSONObject ("GlossEntry")
                                   .getString ("Abbrev"));
        List<Object> list = result.getJSONObject ("glossary")
                                  .getJSONObject ("GlossDiv")
                                  .getJSONObject ("GlossList")
                                  .getJSONObject ("GlossEntry")
                                  .getJSONObject ("GlossDef")
                                  .getArray ("GlossSeeAlso");
        Assert.assertEquals ("[1, 2, 3, 4, 5.5, 0.000001]",
                             list.get (2).toString ());
        List<Object> slist = (List<Object>) list.get (2);
        Assert.assertEquals (new BigInteger ("1"), slist.get (0));
        Assert.assertEquals (1, ((Number) slist.get (0)).intValue ());
      }
    catch (FileNotFoundException e)
      {
        e.printStackTrace ();
      }
    catch (IOException e)
      {
        e.printStackTrace ();
      }
    catch (JSONFormatException e)
      {
        e.printStackTrace ();
      }
    finally
      {
        try
          {
            br.close ();
          }
        catch (IOException e)
          {
            e.printStackTrace ();
          }
      }

  }// readTest()
}// class ParserTest()
