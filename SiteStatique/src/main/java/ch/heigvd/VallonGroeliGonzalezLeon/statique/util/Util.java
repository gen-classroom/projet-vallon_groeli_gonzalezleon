/*
 * @File Util.java
 * @Authors : David González León
 * @Date 12 mars 2021
 */
package ch.heigvd.VallonGroeliGonzalezLeon.statique.util;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Util {
   public static String readFile(File file) throws IOException {
      Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
      int c = reader.read();
      StringBuilder result = new StringBuilder();
      while (c != -1) {
         result.append((char) c);
         c = reader.read();
      }
      reader.close();
      return result.toString();
   }

   public static void writeFile(String content,  Writer writer) throws IOException {
      writer.write(content);
      writer.flush();
      writer.close();
   }
}
