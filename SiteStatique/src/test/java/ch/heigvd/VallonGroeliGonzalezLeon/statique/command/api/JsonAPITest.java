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
        Map<String, Object> mapEmpty = JsonAPI.returnJSONParam(testFile);
        assertTrue(mapEmpty.isEmpty());
        testFile.delete();
    }

    @Test
    void returnJSONParamWithContentFile() throws IOException {
        File testFile = new File(new File(".").getCanonicalPath() + "/test.json");
        testFile.createNewFile();
        String testString = "{\n" +
                "    \"glossary\": {\n" +
                "        \"title\": \"example glossary\",\n" +
                "\t\t\"GlossDiv\": {\n" +
                "            \"title\": \"S\",\n" +
                "\t\t\t\"GlossList\": {\n" +
                "                \"GlossEntry\": {\n" +
                "                    \"ID\": \"SGML\",\n" +
                "\t\t\t\t\t\"SortAs\": \"SGML\",\n" +
                "\t\t\t\t\t\"GlossTerm\": \"Standard Generalized Markup Language\",\n" +
                "\t\t\t\t\t\"Acronym\": \"SGML\",\n" +
                "\t\t\t\t\t\"Abbrev\": \"ISO 8879:1986\",\n" +
                "\t\t\t\t\t\"GlossDef\": {\n" +
                "                        \"para\": \"A meta-markup language, used to create markup languages such as DocBook.\",\n" +
                "\t\t\t\t\t\t\"GlossSeeAlso\": [\"GML\", \"XML\"]\n" +
                "                    },\n" +
                "\t\t\t\t\t\"GlossSee\": \"markup\"\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";
        Util.writeFile(testString, new FileWriter(testFile));
        assertTrue(testFile.exists());
        Map<String, Object> map = JsonAPI.returnJSONParam(testFile);
        assertTrue(!map.isEmpty());
        testFile.delete();
    }
}