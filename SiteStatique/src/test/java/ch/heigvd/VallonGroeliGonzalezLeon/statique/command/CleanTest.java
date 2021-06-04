package ch.heigvd.VallonGroeliGonzalezLeon.statique.command;/*
 * @File CleanTest.java
 * @Authors : David González León
 * @Date 16 mars 2021
 */

import ch.heigvd.VallonGroeliGonzalezLeon.statique.Statique;
import ch.heigvd.VallonGroeliGonzalezLeon.statique.command.api.JsonAPI;
import ch.heigvd.VallonGroeliGonzalezLeon.statique.command.api.MdAPI;
import ch.heigvd.VallonGroeliGonzalezLeon.statique.command.api.TemplateHTML;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CleanTest {
   private final ByteArrayOutputStream output = new ByteArrayOutputStream();

   @Test
   void testCleanDeletesBuildDirectory() throws IOException {
      File testDirectory = new File(new File(".").getCanonicalPath() + "/build");
      testDirectory.mkdir();
      assertTrue(testDirectory.exists());
      new CommandLine(new Statique()).execute("clean");
      assertFalse(testDirectory.exists());
   }

   @Test
   void testCleanDeletesFilesInsideDirectoryToo() throws IOException {
      File testDirectory = new File(new File(".").getCanonicalPath() + "/build");
      File testHtmlFile = new File(testDirectory.getPath() + "/index.html");
      testDirectory.mkdir();
      testHtmlFile.createNewFile();
      assertTrue(testDirectory.exists());
      assertTrue(testHtmlFile.exists());
      new CommandLine(new Statique()).execute("clean");
      assertFalse(testDirectory.exists());
      assertFalse(testHtmlFile.exists());
   }

   @Test
   void cleanDoesNotRemoveAdjacentFiles() throws IOException {
      File testDirectory = new File(new File(".").getCanonicalPath());
      File fileIndex = new File(testDirectory + "/index.md");
      if (fileIndex.exists()) {
         fileIndex.delete();
      }
      fileIndex.createNewFile();
      File fileConfig = new File(testDirectory + "/config.json");
      if (fileConfig.exists()) {
         fileConfig.delete();
      }
      fileConfig.createNewFile();
      File templateDir = new File(testDirectory.getPath() + "/template");

      templateDir.mkdir();
      File layout = new File(testDirectory + "/template/layout.html");
      if (layout.exists()) {
         layout.delete();
      }
      layout.createNewFile();

      File buildDirectory = new File(testDirectory.getPath()+"/build");
      buildDirectory.mkdir();
      new CommandLine(new Statique()).execute("clean");
      assertTrue(fileIndex.exists());
      assertTrue(layout.exists());
      assertTrue(fileConfig.exists());

      fileIndex.delete();
      fileConfig.delete();
      FileUtils.deleteDirectory(templateDir);
      FileUtils.deleteDirectory(buildDirectory);
   }

   @Test
   void testCleanAlertsIfBuildDoesNotExist() {
      System.setOut(new PrintStream(output));
      System.setErr(new PrintStream(output));
      new CommandLine(new Statique()).execute("clean");
      assertTrue(output.toString().length() != 0);
   }
}