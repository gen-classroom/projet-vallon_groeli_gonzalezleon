/*
 * @File MdAPI.java
 * @Authors : David González León, Jade Gröli, Axel Vallon
 * @Date 19 mars 2021
 */
package ch.heigvd.VallonGroeliGonzalezLeon.statique.command.api;

import ch.heigvd.VallonGroeliGonzalezLeon.statique.util.Util;
import lombok.Getter;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class containing methods that allow to analyse and create any md file of a static site project. This class is final
 * and cannot be instantiated.
 */
public final class MdAPI {

   private MdAPI() {}

   /**
    * Put the content of default website content (two headers and text, with the author, date and title of the
    * article in the heading) in the given file.
    *
    * @param emptyFile The target file to initialize. This file must exist, and be empty
    *
    * @throws IOException              if the file does not exist or is not writtable
    * @throws IllegalArgumentException the file must be empty
    */
   public static void initMdIndexFile(File emptyFile) throws IOException, IllegalArgumentException {
      if (emptyFile.length() > 0) { throw new IllegalArgumentException(); }
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      LocalDateTime now = LocalDateTime.now();
      String defaultContent =
              "titre:Mon premier article\n" + "auteur:John Smith\n" + "date:" + dtf.format(now) + "\n" + "---\n" +
              "# Mon premier article\n" + "## Mon sous-titre\n" + "Le contenu de mon article.\n";

      Util.writeFile(defaultContent, new FileWriter(emptyFile));
   }


   /**
    * Analyses a given md file. This function extracts the parameters and the content of the md file and puts them in
    * an MdContent instance.
    *
    * @param mdFile the md file to analyse
    *
    * @return An instance of MdContent containing the values extracted from the md file
    *
    * @throws IOException              if the files does not exist, or if there is an issue while reading the file
    * @throws IllegalArgumentException if the file has not the correct format
    */
   public static MdContent analyseFile(File mdFile) throws IOException {
      String md = Util.readFile(
              new BufferedReader(new InputStreamReader(new FileInputStream(mdFile), StandardCharsets.UTF_8)));
      String[] lines = md.split("\\r?\\n|\\r");
      if (lines.length < 4) {
         throw new IllegalArgumentException("The md file did not coincide with the defined syntax.");
      }
      String author = lines[1].split(":")[1];
      if (author.equals("")) {
         author = null;
      }
      String date = lines[2].split(":")[1];
      if (date.equals("")) {
         date = null;
      }
      String pageTitle = lines[0].split(":")[1];
      if (pageTitle.equals("")) {
         pageTitle = null;
      }
      StringBuilder content = new StringBuilder();
      for (int i = 4; i < lines.length; ++i) {
         content.append(lines[i]).append("\n");
      }
      Parser parser = Parser.builder().build();
      Node document = parser.parse(content.toString());
      HtmlRenderer renderer = HtmlRenderer.builder().build();

      return new MdContent(renderer.render(document), author, date, pageTitle);
   }

   /**
    * A class containing the information of a md file.
    */
   static class MdContent {
      @Getter private final String content;
      @Getter private final String author;
      @Getter private final String date;
      @Getter private final String pageTitle;

      /**
       * Instantiates a new Md content.
       *
       * @param content   the content of the md file
       * @param author    the author of the md file
       * @param date      the date of the md file
       * @param pageTitle the page title of the md file
       */
      public MdContent(String content, String author, String date, String pageTitle) {
         this.content = content;
         this.author = author;
         this.date = date;
         this.pageTitle = pageTitle;
      }
   }
}

