/*
 * @File Init.java
 * @Authors : David González León
 * @Date 5 mars 2021
 */
package ch.heigvd.VallonGroeliGonzalezLeon.statique.command;


import ch.heigvd.VallonGroeliGonzalezLeon.statique.command.api.JsonAPI;
import ch.heigvd.VallonGroeliGonzalezLeon.statique.command.api.MdAPI;
import org.apache.commons.io.FileUtils;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;


@CommandLine.Command(name = "init", mixinStandardHelpOptions = true,
        description = "Initializes the site and creates a file and a subfile which contains a configuration file (json) and an index file (markdown). This command needs to be" +
                " executed where the user wants to create the repository of the site. The first argument is the name of the command and the second the names of the two directories " +
                "(/fileA/fileB). If one or the two files already exist, it's replace by the new one.")


public class Init implements Callable<Integer> {
    @CommandLine.Parameters(index = "0")
    String sitePath;

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
                        JsonAPI.initJSONConfigFile(config);
                        System.out.println("File created : " + config.getName());
                    }

                    if (index.exists()) {
                        System.out.println("File already exists.");
                    } else {
                        index.createNewFile();
                        MdAPI.initMdIndexFile(index);
                        System.out.println("File created : " + index.getName());
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
