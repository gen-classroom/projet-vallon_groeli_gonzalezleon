package ch.heigvd.VallonGroeliGonzalezLeon.statique;/*
 * @File StatiqueTest.java
 * @Authors : David González León
 * @Date 26 mai 2021
 */

import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import static org.junit.jupiter.api.Assertions.*;

class StatiqueTest {
   @Test
   void testStaticWorksCorrectly() {
      assertDoesNotThrow(() ->  new CommandLine(new Statique()).execute());
   }

   @Test
   void assertStaticVersionAndHelpWorksCorrectly() {
      assertDoesNotThrow(() -> new CommandLine(new Statique()).execute("--version"));
      assertDoesNotThrow(() -> new CommandLine(new Statique()).execute("-V"));
      assertDoesNotThrow(() -> new CommandLine(new Statique()).execute("-h"));
      assertDoesNotThrow(() -> new CommandLine(new Statique()).execute("--help"));
   }
}