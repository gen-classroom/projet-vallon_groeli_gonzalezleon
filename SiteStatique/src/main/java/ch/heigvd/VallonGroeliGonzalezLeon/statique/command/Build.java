package ch.heigvd.VallonGroeliGonzalezLeon.statique.command;


import ch.heigvd.VallonGroeliGonzalezLeon.statique.command.api.MdAPI;
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
      if (MdAPI.createIndexPage(buildDirectory, currentDirectory) != 0) {
         return 2;
      }
      return 0;
   }


}