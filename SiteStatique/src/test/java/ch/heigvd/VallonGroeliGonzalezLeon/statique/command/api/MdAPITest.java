package ch.heigvd.VallonGroeliGonzalezLeon.statique.command.api;/*
 * @File MdAPITest.java
 * @Authors : David González León
 * @Date 19 mars 2021
 */

import ch.heigvd.VallonGroeliGonzalezLeon.statique.util.Util;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class MdAPITest {

   private final File currentDirectory = new File(new File(".").getCanonicalPath() + "/Workspace");
   private final File buildDirectory = new File(currentDirectory.getPath() + "/build");

   MdAPITest() throws IOException {}


   @BeforeEach
   void setUp() {
      currentDirectory.mkdir();
      buildDirectory.mkdir();
   }

   @AfterEach
   void tearDown() throws IOException {
      FileUtils.deleteDirectory(currentDirectory);
   }

   @Test
   void testInitMdFileCreatesAFile() throws IOException {
      File file = new File(currentDirectory.getPath()+"/test.md");
      assertFalse(file.exists());
      MdAPI.initMdIndexFile(file);
      assertTrue(file.exists());
   }

   @Test
   void testInitMdFileThrowsExceptionCorrectly() throws IOException {
      File file = new File(currentDirectory.getPath()+"/test.md");
      Util.writeFile("Test", new FileWriter(file));
      assertThrows(IllegalArgumentException.class,()->{MdAPI.initMdIndexFile(file);});
   }

   @Test
   void testCreateIndexFileReturns1IfMdFileDoesNotExist() {
      assertEquals(MdAPI.createIndexPage(buildDirectory, currentDirectory), 1);
   }
/*
   @Test
   void testCreateIndexFileCreatesHtmlFile() throws IOException {
      File mdFile = new File(currentDirectory.getPath() + "/index.md");
      Util.writeFile("# Test",
                     new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mdFile), StandardCharsets.UTF_8)));
      int result = MdAPI.createIndexPage(buildDirectory, currentDirectory);
      assertEquals(result, 0);
      File htmlFile = new File(buildDirectory.getPath() + "/index.html");
      assertTrue(htmlFile.exists());
      assertEquals("<h1>Test</h1>\n",
                   Util.readFile(new BufferedReader(new InputStreamReader(new FileInputStream(htmlFile)))));
   }*/


   @Test
   void test() throws IOException {
      File mdFile = new File(currentDirectory.getPath() + "/index.md");
      mdFile.createNewFile();
      File json = new File(currentDirectory.getPath() + "/config.json");
      json.createNewFile();
      JsonAPI.initJSONConfigFile(json);
      MdAPI.initMdIndexFile(mdFile);
      MdAPI.createIndexPage(new File(currentDirectory.getPath()),new File(currentDirectory.getPath()));
   }

}