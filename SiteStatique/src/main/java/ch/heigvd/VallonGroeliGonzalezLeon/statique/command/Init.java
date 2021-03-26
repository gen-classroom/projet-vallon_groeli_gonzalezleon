/*
 * @File Init.java
 * @Authors : David González León
 * @Date 5 mars 2021
 */
package ch.heigvd.VallonGroeliGonzalezLeon.statique.command;

import org.apache.commons.io.FileUtils;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;


@CommandLine.Command(name = "build", mixinStandardHelpOptions = true,
        description = "Initializes the site and creates a file and a subfile which contains a configuration file (json) and an index file (markdown). This command needs to be" +
                " executed where the user wants to create the repository of the site")


public class Init implements Callable<Integer> {
   @CommandLine.Parameters(index = "0") String sitePath;

   @Override
   public Integer call() throws IOException {
      if (sitePath != null) {
         File targetDirectory = new File(new File(".").getCanonicalPath() + sitePath);
         if (targetDirectory.exists()) {
            FileUtils.deleteDirectory(targetDirectory);
         }
         boolean success = targetDirectory.mkdirs();
         if (success) {
            try {
               File config = new File(targetDirectory + "/config.json");
               File index = new File(targetDirectory + "/index.md");

               if (config.exists()) {
                  System.out.println("File already exists.");
               } else {
                  config.createNewFile();
                  System.out.println("File created : " + config.getName());
               }

               if (index.exists()) {
                  System.out.println("File already exists.");
               } else {
                  index.createNewFile();
                  System.out.println("File created : " + config.getName());
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
