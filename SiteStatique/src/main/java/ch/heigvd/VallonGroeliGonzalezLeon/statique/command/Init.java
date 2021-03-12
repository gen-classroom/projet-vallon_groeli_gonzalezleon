/*
 * @File Init.java
 * @Authors : David González León
 * @Date 5 mars 2021
 */
package ch.heigvd.VallonGroeliGonzalezLeon.statique.command;

import picocli.CommandLine.Command;
import java.util.concurrent.Callable;

@Command(name = "init", description = "initialize a new static site")
public class Init implements Callable<Integer> {

   @Override
   public Integer call() {
      System.out.println("command init");
      return 1;
   }
}
