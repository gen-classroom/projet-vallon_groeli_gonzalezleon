/*
 * @File Util.java
 * @Authors : David González León
 * @Date 12 mars 2021
 */
package ch.heigvd.VallonGroeliGonzalezLeon.statique.util;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * A class for general utility functions
 */
public class Util {

   /**
    * Read a file using the given reader
    *
    * @param reader the reader to use
    *
    * @return the content given by the reader
    *
    * @throws IOException if there is an issue while reading the file
    */
   public static String readFile(Reader reader) throws IOException {
      int c = reader.read();
      StringBuilder result = new StringBuilder();
      while (c != -1) {
         result.append((char) c);
         c = reader.read();
      }
      reader.close();
      return result.toString();
   }

   /**
    * Writes the given content using the given Writer
    *
    * @param content the sonctent to write
    * @param writer  The writer to use
    *
    * @throws IOException if there is an issue while using the given writer
    */
   public static void writeFile(String content, Writer writer) throws IOException {
      writer.write(content);
      writer.flush();
      writer.close();
   }
}
