/*
 * @File Statique.java
 * @Authors : David González León, Jade Gröli, Axel Vallon
 * @Date 5 mars 2021
 */
package ch.heigvd.VallonGroeliGonzalezLeon.statique;

import ch.heigvd.VallonGroeliGonzalezLeon.statique.command.Build;
import ch.heigvd.VallonGroeliGonzalezLeon.statique.command.Clean;
import ch.heigvd.VallonGroeliGonzalezLeon.statique.command.Init;
import ch.heigvd.VallonGroeliGonzalezLeon.statique.command.Serve;
import ch.heigvd.VallonGroeliGonzalezLeon.statique.util.VersionProviderWithVariables;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

/**
 * The Static class. This class is the main command of this application.
 */
@Command(name = "Statique", mixinStandardHelpOptions = true,
         description = "Creates and handles the generation of a statique site generator",
         versionProvider = VersionProviderWithVariables.class,
         subcommands = {Build.class, Clean.class, Init.class, Serve.class})
public class Statique implements Callable<Integer> {

   /**
    * The entry point of application.
    *
    * @param args the input arguments
    */
   public static void main(String... args) {
      int exitCode = new CommandLine(new Statique()).execute(args);
      System.exit(exitCode);
   }

   @Override
   public Integer call() {
      CommandLine.usage(this, System.out);
      return 0;
   }
}

