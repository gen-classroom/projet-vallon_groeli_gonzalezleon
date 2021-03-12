/*
 * @File ServeCommand.java
 * @Authors : David González León
 * @Date 5 mars 2021
 */
package com.mycompany.app.command;

import picocli.CommandLine;

@CommandLine.Command(
        name = "serve", description = "serving"
)
public class ServeCommand implements Runnable{
   public static void main(String[] args) {
      CommandLine.run(new ServeCommand(), args);
   }

   @Override
   public void run() {
      System.out.println("serving");
   }
}
