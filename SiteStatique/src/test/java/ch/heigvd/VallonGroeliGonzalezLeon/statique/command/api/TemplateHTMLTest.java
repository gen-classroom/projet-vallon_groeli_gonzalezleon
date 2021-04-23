package ch.heigvd.VallonGroeliGonzalezLeon.statique.command.api;

import ch.heigvd.VallonGroeliGonzalezLeon.statique.Statique;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TemplateHTMLTest {

    private final File currentDirectory = new File(new File(".").getCanonicalPath() + "/Workspace");

    TemplateHTMLTest() throws IOException {}


    @BeforeEach
    void setUp() {
        currentDirectory.mkdir();
    }
/*
    @AfterEach
    void tearDown() throws IOException {
        FileUtils.deleteDirectory(currentDirectory);
    }

 */

    @Test
    void test() throws IOException {
        new CommandLine(new Statique()).execute("init", "/mon/site");
        File testDirectory = new File(new File(".").getCanonicalPath() + "/mon/site");
        File fileIndex = new File(new File(".").getCanonicalPath() + "/mon/site/index.md");
        File fileConfig = new File(new File(".").getCanonicalPath() + "/mon/site/config.json");
        File layout = new File(new File(".").getCanonicalPath() + "/mon/site/template/layout.html");
        assertTrue(fileIndex.exists());
        assertTrue(fileConfig.exists());
        assertTrue(layout.exists());
        assertTrue(testDirectory.exists());

        TemplateHTML template = new TemplateHTML(layout, fileConfig);

        String result =
                "<html lang=\"en\">\n<head>\n<meta charset=\"utf-8\">" +
                        "\n<title>TEST POUR LE SITE| {{page.titre}}</title>\n</head>\n<body>\n" +
                        "{%include menu.html}\n{{content}}\n</body>\n</html>";

        String test = template.generatePage(fileIndex);

        assertEquals(result, test);
    }


}