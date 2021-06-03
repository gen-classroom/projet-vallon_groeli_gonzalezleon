/*
 * @File Init.java
 * @Authors : David González León
 * @Date 5 mars 2021
 */
package ch.heigvd.VallonGroeliGonzalezLeon.statique.command;

import ch.heigvd.VallonGroeliGonzalezLeon.statique.command.api.*;
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

    @CommandLine.Option(names = {"-m", "--markdown"},
            description = "Generate only a new index file (markdown) in current repertory")
    boolean mdFile;

    @Override
    public Integer call() {
        try {
            if (mdFile) {
                File index = new File(new File(".").getCanonicalPath() + "/index.md");
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
        } catch (IllegalArgumentException e) {
            System.out.println("File not empty");
            e.printStackTrace();
        }
        return 0;
    }
}