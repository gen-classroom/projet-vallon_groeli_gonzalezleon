package ch.heigvd.VallonGroeliGonzalezLeon.statique.command;/*
 * @File CleanTest.java
 * @Authors : David González León
 * @Date 16 mars 2021
 */

import ch.heigvd.VallonGroeliGonzalezLeon.statique.Statique;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CleanTest {
   @Test
   void testCleanDeletesBuildDirectory() throws IOException {
      File testDirectory = new File(new File(".").getCanonicalPath()+"/build");
      testDirectory.mkdir();
      assertTrue(testDirectory.exists());
      new CommandLine(new Statique()).execute("clean");
      assertFalse(testDirectory.exists());
   }

   @Test
   void testCleanDeletesFilesInsideDirectoryToo() throws IOException {
      File testDirectory = new File(new File(".").getCanonicalPath()+"/build");
      File testHtmlFile = new File(testDirectory.getPath()+"/index.html");
      testDirectory.mkdir();
      testHtmlFile.createNewFile();
      assertTrue(testDirectory.exists());
      assertTrue(testHtmlFile.exists());
      new CommandLine(new Statique()).execute("clean");
      assertFalse(testDirectory.exists());
      assertFalse(testHtmlFile.exists());
   }
}