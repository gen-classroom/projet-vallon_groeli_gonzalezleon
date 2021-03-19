/*
 * @File MdAPI.java
 * @Authors : David González León
 * @Date 19 mars 2021
 */
package ch.heigvd.VallonGroeliGonzalezLeon.statique.command.api;

import ch.heigvd.VallonGroeliGonzalezLeon.statique.util.Util;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class MdAPI {

   public static int createIndexPage(File buildDirectory, File currentDirectory) {
      try {
         File mdFile = new File(currentDirectory.getPath() + "/index.md");
         if (!mdFile.isFile()) {
            return 2;
         }
         String content = Util.readFile(new BufferedReader(new InputStreamReader(new FileInputStream(mdFile))));
         Parser parser = Parser.builder().build();
         Node document = parser.parse(content);
         HtmlRenderer renderer = HtmlRenderer.builder().build();
         File htmlFile = new File(buildDirectory.getPath() + "/index.html");
         htmlFile.createNewFile();
         Util.writeFile(renderer.render(document), new BufferedWriter(
                 new OutputStreamWriter(new FileOutputStream(htmlFile), StandardCharsets.UTF_8)));
      } catch (IOException e) {
         System.err.println("Error while reading or writing the file");
         e.printStackTrace();
         return 2;
      }
      return 0;
   }
}
