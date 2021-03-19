package ch.heigvd.VallonGroeliGonzalezLeon.statique.command;


import ch.heigvd.VallonGroeliGonzalezLeon.statique.util.Util;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import picocli.CommandLine;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;


@CommandLine.Command(name = "build", description = "build command")
public class Build implements Callable<Integer> {

   @Override
   public Integer call() {
      File currentDirectory;
      try {
         currentDirectory = new File(new File(".").getCanonicalPath());
      } catch (IOException e) {
         System.err.println("Error while reading current directory");
         e.printStackTrace();
         return 2;
      }
      File buildDirectory = new File(currentDirectory.getPath() + "\\build");
      buildDirectory.mkdir();
      if (createIndexPage(buildDirectory, currentDirectory) != 0) {
         return 2;
      }
      return 0;
   }

   private int createIndexPage(File buildDirectory, File currentDirectory) {
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