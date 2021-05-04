/*
 * @File Serve.java
 * @Authors : David González León
 * @Date 5 mars 2021
 */
package ch.heigvd.VallonGroeliGonzalezLeon.statique.command;

import picocli.CommandLine;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "serve", description = "serving"
)
public class Serve implements Callable<Integer> {

   @Override
   public Integer call() {
   File currentDirectory;
   try {
      currentDirectory = new File(new File(".").getCanonicalPath() + "\\build\\index.html");
      Desktop.getDesktop().browse(currentDirectory.toURI());
   } catch (IOException e) {
      System.err.println("Error while reading current directory");
      e.printStackTrace();
      return 2;
   }
      return 0;
   }


}
