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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MdAPI {

   /**
    * Put the content of default website content (two headers and text, with the author, date and title of the
    * article in the heading)
    *
    * @param emptyFile This file must exist, and be empty
    *
    * @throws IOException              if the file does not exist or is not writtable
    * @throws IllegalArgumentException the file must be empty
    */
   public static void initMdIndexFile(File emptyFile) throws IOException, IllegalArgumentException {
      if (emptyFile.getTotalSpace() > 0) { throw new IllegalArgumentException(); }
      // contenu par défaut
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      LocalDateTime now = LocalDateTime.now();
      String defaultContent =
              "titre: Mon premier article\n" + "auteur: John Smith\n" + "date:" + dtf.format(now) + " \n" + "---\n" +
              "# Mon premier article\n" + "## Mon sous-titre\n" + "Le contenu de mon article.\n";

      Util.writeFile(defaultContent, new FileWriter(emptyFile));
   }

   /**
    * This function creates, from a basic markdown file index.md that must be located in currentDirectory, a html file
    * located in the buildDirectory, that replicates the structure of the markdown file
    *
    * @param buildDirectory   The directory where the html file must be stored
    * @param currentDirectory The directory where the MarkDown file is located
    *
    * @return - 0 if the creation of the html file was done without problem
    *         - 1 If the file index.md does not exist in currentDirectory
    *         - 2 if there was an issue while writing to the index.html file in the buildDirectory
    */
   public static int createIndexPage(File buildDirectory, File currentDirectory) {
      try {
         File mdFile = new File(currentDirectory.getPath() + "/index.md");
         if (!mdFile.exists()) {
            return 1;
         }
         String content = Util.readFile(new BufferedReader(new InputStreamReader(new FileInputStream(mdFile))));
         Parser parser = Parser.builder().build();
         Node document = parser.parse(content);
         HtmlRenderer renderer = HtmlRenderer.builder().build();
         File htmlFile = new File(buildDirectory.getPath() + "/index.html");
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
