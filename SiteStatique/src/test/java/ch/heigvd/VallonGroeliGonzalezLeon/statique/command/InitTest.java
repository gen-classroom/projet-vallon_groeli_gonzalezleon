package ch.heigvd.VallonGroeliGonzalezLeon.statique.command;/*
 * @File CleanTest.java
 * @Authors : David González León
 * @Date 16 mars 2021
 */

import ch.heigvd.VallonGroeliGonzalezLeon.statique.Statique;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class InitTest {
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @AfterEach
    void tearDown() throws IOException {
        File testDirectory = new File(new File(".").getCanonicalPath() + "/mon");
        FileUtils.deleteDirectory(testDirectory);
    }

    @Test
    void testInitCreateDirectory() throws IOException {
        new CommandLine(new Statique()).execute("init", "/mon/site");
        File testDirectory = new File(new File(".").getCanonicalPath() + "/mon/site");
        File fileIndex = new File(new File(".").getCanonicalPath() + "/mon/site/index.md");
        File fileConfig = new File(new File(".").getCanonicalPath() + "/mon/site/config.json");
        File layout = new File(new File(".").getCanonicalPath() + "/mon/site/template/layout.html");
        assertTrue(fileIndex.exists());
        assertTrue(fileConfig.exists());
        assertTrue(layout.exists());
        assertTrue(testDirectory.exists());
    }

    @Test
    void testReplaceIfDirectoryAlreadyExists() throws IOException {
        File testDirectory = new File(new File(".").getCanonicalPath() + "/mon/site");
        File testConfigFile = new File(new File(".").getCanonicalPath() + "/mon/site/test.txt");
        testDirectory.mkdirs();
        testConfigFile.createNewFile();
        assertTrue(testDirectory.exists());
        assertTrue(testConfigFile.exists());
        new CommandLine(new Statique()).execute("init", "/mon/site");
        File dir1 = new File(new File(".").getCanonicalPath() + "/mon/site/index.md");
        File fileIndex = new File(new File(".").getCanonicalPath() + "/mon/site/index.md");
        File fileConfig = new File(new File(".").getCanonicalPath() + "/mon/site/config.json");
        File layout = new File(new File(".").getCanonicalPath() + "/mon/site/template/layout.html");
        assertTrue(dir1.exists());
        assertTrue(fileIndex.exists());
        assertTrue(fileConfig.exists());
        assertTrue(layout.exists());
        assertFalse(testConfigFile.exists());
    }


    @Test
    void testInitAlertsIfInitDoesNotExist() throws IOException {
        System.setOut(new PrintStream(output));
        System.setErr(new PrintStream(output));
        new CommandLine(new Statique()).execute("init", "/mon/site");
        assertTrue(output.toString().length() != 0);
        File testDirectory = new File(new File(".").getCanonicalPath() + "/mon/site");
        assertTrue(testDirectory.exists());
    }
}