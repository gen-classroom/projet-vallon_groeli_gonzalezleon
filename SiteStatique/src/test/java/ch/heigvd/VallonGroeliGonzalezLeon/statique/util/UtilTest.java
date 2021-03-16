/*
 * @File UtilTest.java
 * @Authors : David González León
 * @Date 12 mars 2021
 */
package ch.heigvd.VallonGroeliGonzalezLeon.statique.util;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

   @Test
   void testWritingFileWorks() throws IOException {
      StringWriter writer = new StringWriter();
      String content = "This is a test to see if the writer works\n Let's hope it does.";
      assertDoesNotThrow(() -> Util.writeFile(content, writer));
      assertEquals(content,writer.toString());
   }

}