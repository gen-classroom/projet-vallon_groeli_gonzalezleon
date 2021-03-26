/*
 * @File Init.java
 * @Authors : David González León
 * @Date 5 mars 2021
 */
package ch.heigvd.VallonGroeliGonzalezLeon.statique.command;

import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

@Command(name = "init", description = "initialize a new static site")
public class Init implements Callable<Integer> {
   @CommandLine.Parameters(index = "0") String sitePath;

   @Override
   public Integer call() throws IOException {
      if (sitePath != null) {
         File targetDirectory = new File(new File(".").getCanonicalPath() + sitePath);
         boolean success = targetDirectory.mkdirs();
         if (success) {
            try {
               File config = new File(targetDirectory + "/config.json");
               File index = new File(targetDirectory + "/index.md");
               if (config.createNewFile()) {
                  System.out.println("File created : " + config.getName());
               } else {
                  System.out.println("File already exists.");
               }
               if (index.createNewFile()) {
                  System.out.println("File created : " + index.getName());
               } else {
                  System.out.println("File already exists.");
               }
            } catch (IOException e) {
               System.out.println("An error occurred");
               e.printStackTrace();
            }
         }
      }
      return 0;
   }
}
