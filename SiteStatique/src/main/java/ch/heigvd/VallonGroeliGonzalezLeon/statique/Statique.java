/*
 * @File Statique.java
 * @Authors : David González León
 * @Date 5 mars 2021
 */
package ch.heigvd.VallonGroeliGonzalezLeon.statique;

import ch.heigvd.VallonGroeliGonzalezLeon.statique.command.BuildCommand;
import ch.heigvd.VallonGroeliGonzalezLeon.statique.command.Clean;
import ch.heigvd.VallonGroeliGonzalezLeon.statique.command.NewCommand;
import ch.heigvd.VallonGroeliGonzalezLeon.statique.command.ServeCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(name = "Statique", mixinStandardHelpOptions = true, version = "0.0.1",
         description = "Creates and handles the generation of a statique site generator",
         subcommands = {BuildCommand.class, Clean.class, NewCommand.class, ServeCommand.class})
public class Statique implements Callable<Integer> {

   public static void main(String... args) {
      int exitCode = new CommandLine(new Statique()).execute(args);
      System.exit(exitCode);
   }


   @Override
   public Integer call() throws Exception { // your business logic goes here...
      CommandLine.usage(this, System.out);
      return 0;
   }

}
