package ch.heigvd.VallonGroeliGonzalezLeon.statique.command;

import org.apache.commons.io.FileUtils;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "clean", mixinStandardHelpOptions = true,
                     description = "Cleans the given directory of any generated html files and the build directory. " +
                                   "This command needs to be executed at the root of the directory created by the " +
                                   "init command")
public class Clean implements Callable<Integer> {

   @Override
   public Integer call() throws IOException {
      File targetDirectory = new File(new File(".").getCanonicalPath());
      File buildDirectory = new File(targetDirectory.getPath() + "/build");
      if (buildDirectory.exists()) {
         try {
            FileUtils.deleteDirectory(buildDirectory);
         } catch (IOException ioException) {
            System.out.println("Error while deleting the build directory");
         }
      } else {
         System.err.println("Build directory does not exist, please make sure you are in the correct repository");
      }
      return 0;
   }
}
