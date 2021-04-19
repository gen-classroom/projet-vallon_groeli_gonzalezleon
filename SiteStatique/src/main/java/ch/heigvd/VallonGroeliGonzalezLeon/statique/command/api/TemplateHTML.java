package ch.heigvd.VallonGroeliGonzalezLeon.statique.command.api;

import java.io.File;

public class TemplateHTML {
    private File layoutFile;
    private File configFile;

    public TemplateHTML(File layoutFile, File configFile) {
        this.layoutFile = layoutFile;
        this.configFile = configFile;
    }

    public String generatePage(File mdFile) {
        return "";
    }
}
