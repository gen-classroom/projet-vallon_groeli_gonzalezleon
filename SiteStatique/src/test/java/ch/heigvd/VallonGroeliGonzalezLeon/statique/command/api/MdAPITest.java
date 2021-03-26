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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
   void testCreateIndexFileReturns1IfMdFileDoesNotExist() {
      assertEquals(MdAPI.createIndexPage(buildDirectory, currentDirectory), 1);
   }

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
   }

}