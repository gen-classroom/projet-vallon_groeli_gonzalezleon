package ch.heigvd.VallonGroeliGonzalezLeon.statique.command;

import org.apache.commons.io.FileUtils;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "clean", mixinStandardHelpOptions = true,
                     description = "Cleans the given directory of any generated html files")
public class Clean implements Callable<Integer> {

   @CommandLine.Parameters(index = "1") String sitePath;

   @Override
   public Integer call() throws IOException {
      File targetDirectory = new File(new File(".").getCanonicalPath() + sitePath);
      File[] files = targetDirectory.listFiles();
      if (files != null) {
         for (File file : files) {
            if (file.isDirectory() && file.getName().equals("build")) {
               try {
                  FileUtils.deleteDirectory(file);
               } catch (IOException ioException) {
                  System.out.println("Error while deleting the build directory");
               }
            }
         }
      }
      return 0;
   }


}
