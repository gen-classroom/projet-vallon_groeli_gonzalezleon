package ch.heigvd.VallonGroeliGonzalezLeon.statique.command;/*
 * @File BuildTest.java
 * @Authors : David González León
 * @Date 19 mars 2021
 */

import ch.heigvd.VallonGroeliGonzalezLeon.statique.Statique;
import ch.heigvd.VallonGroeliGonzalezLeon.statique.util.Util;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BuildTest {


   @Test
   void testBuildCreatesDirectory() throws IOException {
      File mdFile = new File(new File(".").getCanonicalPath() + "/index.md");
      mdFile.createNewFile();
      Util.writeFile("# Test",
                     new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mdFile), StandardCharsets.UTF_8)));
      new CommandLine(new Statique()).execute("build");
      File buildDirectory = new File(new File(".").getCanonicalPath() + "/build");
      assertTrue(buildDirectory.exists());
      mdFile.delete();
      FileUtils.deleteDirectory(buildDirectory);
   }

   @Test
   void testBuildCreatesFileWithGoodContent() throws IOException {
      File mdFile = new File(new File(".").getCanonicalPath() + "/index.md");
      mdFile.createNewFile();
      Util.writeFile("# Test",
                     new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mdFile), StandardCharsets.UTF_8)));
      new CommandLine(new Statique()).execute("build");
      File buildDirectory = new File(new File(".").getCanonicalPath() + "/build");
      assertTrue(buildDirectory.exists());
      File index = new File(buildDirectory.getPath() + "/index.html");
      assertTrue(index.exists());
      String content = Util.readFile(new BufferedReader(new InputStreamReader(new FileInputStream(index))));
      assertEquals(content, "<h1>Test</h1>" + "\n");
      mdFile.delete();
      FileUtils.deleteDirectory(buildDirectory);
   }

}