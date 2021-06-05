/*
 * @File Init.java
 * @Authors : David González León, Jade Gröli, Axel Vallon
 * @Date 5 mars 2021
 */
package ch.heigvd.VallonGroeliGonzalezLeon.statique.command;

import ch.heigvd.VallonGroeliGonzalezLeon.statique.command.api.JsonAPI;
import ch.heigvd.VallonGroeliGonzalezLeon.statique.command.api.MdAPI;
import ch.heigvd.VallonGroeliGonzalezLeon.statique.command.api.TemplateHTML;
import org.apache.commons.io.FileUtils;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;


/**
 * The init command. This command allows to initialize directories with the basic files for the static site generator
 * . It also allows it's user to create single index.md files.
 */
@CommandLine.Command(name = "init", mixinStandardHelpOptions = true,
                     description = "Initializes the site and creates a directory which contains a configuration file " +
                                   "(json), a layout file (html) located inside a second directory called template " +
                                   "and an index file (markdown). \nThis command needs to be executed where the user " +
                                   "wants to create the repository of the site. The first argument is the destination" +
                                   " directory. \nIf the target directory already exists, All of it's content will be" +
                                   " deleted before initializing thee directory.\nIf there is any issue while " +
                                   "creating the files, this command will return 1")


public class Init implements Callable<Integer> {
   /**
    * The path to the site
    */
   @CommandLine.Parameters(index = "0", description = "The path to the directory") String sitePath;

   /**
    * Option to indicate that the command should only initiate a new index file
    */
   @CommandLine.Option(names = {"-m", "--markdown"},
                       description = "Generate only a new index file (markdown) in current repertory. If there is " +
                                     "already a index.md file in the current directory, returns 1 and does nothing.")
   boolean mdFile;

   @Override
   public Integer call() {
      try {
         if (mdFile) {
            File index = new File(new File(".").getCanonicalPath() + "/index.md");
            if (index.exists()) {
               System.err.println("Index.md file already exists in the current directory.");
               return 1;
            }
            index.createNewFile();
            MdAPI.initMdIndexFile(index);
         } else {
            if (sitePath != null) {
               File targetDirectory = new File(new File(".").getCanonicalPath() + sitePath);
               if (targetDirectory.exists()) {
                  FileUtils.deleteDirectory(targetDirectory);
               }
               if (targetDirectory.mkdirs()) {
                  File config = new File(targetDirectory.getPath() + "/config.json");
                  File index = new File(targetDirectory.getPath() + "/index.md");
                  config.createNewFile();
                  JsonAPI.initJSONConfigFile(config);
                  System.out.println("File created : " + config.getName());
                  index.createNewFile();
                  MdAPI.initMdIndexFile(index);
                  System.out.println("File created : " + index.getName());
                  File templateDir = new File(targetDirectory.getPath() + "/template");
                  if (templateDir.mkdirs()) {
                     File layout = new File(templateDir.getPath() + "/layout.html");
                     layout.createNewFile();
                     TemplateHTML.initLayoutFile(layout);
                     System.out.println("File created : " + layout.getName());
                  }
               }
            }
         }
      } catch (IOException e) {
         System.out.println("An error occurred");
         e.printStackTrace();
         return 1;
      } catch (IllegalArgumentException e) {
         System.out.println("File not empty");
         e.printStackTrace();
         return 1;
      }
      return 0;
   }
}