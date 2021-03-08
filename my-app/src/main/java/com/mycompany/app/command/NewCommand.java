/*
 * @File NewCommand.java
 * @Authors : David González León
 * @Date 5 mars 2021
 */
package com.mycompany.app.command;

import picocli.CommandLine;

@CommandLine.Command(name = "new", description = "creates new")
public class NewCommand implements Runnable {
   public static void main(String[] args) {
      CommandLine.run(new NewCommand(), args);
   }

   @Override
   public void run() {
      System.out.println("new new");
   }
}
