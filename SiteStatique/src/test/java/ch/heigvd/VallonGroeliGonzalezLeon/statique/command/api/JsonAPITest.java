package ch.heigvd.VallonGroeliGonzalezLeon.statique.command.api;

import ch.heigvd.VallonGroeliGonzalezLeon.statique.util.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JsonAPITest {

    @Test
    void initJSONConfigFile() throws IOException {
        File testFile = new File(new File(".").getCanonicalPath() + "/test.json");
        JsonAPI.initJSONConfigFile(testFile);
        assertTrue(testFile.exists());
        String content = Util.readFile(new FileReader(testFile));
        assertTrue(content.length() != 0);
        testFile.delete();
    }

    @Test
    void initJSONConfigFileOnNotEmptyFile() throws IOException {
        File testFile = new File(new File(".").getCanonicalPath() + "/test.json");
        Util.writeFile("this text has content", new FileWriter(testFile));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {JsonAPI.initJSONConfigFile(testFile);});
        testFile.delete();
    }

    @Test
    void returnJSONParamWithEmptyFile() throws IOException {
        File testFile = new File(new File(".").getCanonicalPath() + "/test.json");
        testFile.createNewFile();
        assertTrue(testFile.exists());
        JsonAPI.JsonContent emptyJsonContent = JsonAPI.returnJSONParam(testFile);
        assertTrue(emptyJsonContent == null);
        testFile.delete();
    }

    @Test
    void returnJSONParamWithContentFile() throws IOException {
        File testFile = new File(new File(".").getCanonicalPath() + "/test.json");
        testFile.createNewFile();
        String testString = "{\"charset\":\"charset\"," +
                "\"siteTitle\":\"siteTitle\"," +
                "\"keywords\":\"keywords\"," +
                "\"domain\":\"domain\"}\n";
        Util.writeFile(testString, new FileWriter(testFile));
        assertTrue(testFile.exists());
        JsonAPI.JsonContent content = JsonAPI.returnJSONParam(testFile);
        assertTrue(content.getCharset().equals("charset"));
        assertTrue(content.getDomain().equals("domain"));
        assertTrue(content.getKeywords().equals("keywords"));
        assertTrue(content.getSiteTitle().equals("siteTitle"));
        testFile.delete();
    }
}