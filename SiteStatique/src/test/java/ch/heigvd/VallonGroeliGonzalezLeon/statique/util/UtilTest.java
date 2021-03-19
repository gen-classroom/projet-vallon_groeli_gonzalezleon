/*
 * @File UtilTest.java
 * @Authors : David González León
 * @Date 12 mars 2021
 */
package ch.heigvd.VallonGroeliGonzalezLeon.statique.util;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilTest {

   @Test
   void testWritingFileWorks() throws IOException {
      StringWriter writer = new StringWriter();
      String content = "This is a test to see if the writer works\n Let's hope it does.";
      assertDoesNotThrow(() -> Util.writeFile(content, writer));
      assertEquals(content, writer.toString());
   }

   @Test
   void testReadingFileWorks() throws IOException {
      File test = new File("test.txt");
      test.createNewFile();
      String content = "This is a test to see if the writer works\n Let's hope it does.";
      Util.writeFile(content, new BufferedWriter(
              new OutputStreamWriter(new FileOutputStream(test), StandardCharsets.UTF_8)));

      Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream(test)));
      String result = Util.readFile(reader);
      assertEquals(result,content);
      test.delete();
   }

}