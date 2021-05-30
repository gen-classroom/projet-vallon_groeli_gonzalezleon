package ch.heigvd.VallonGroeliGonzalezLeon.statique.command.api;

import ch.heigvd.VallonGroeliGonzalezLeon.statique.util.Util;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.FileTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Template html.
 */
public class TemplateHTML {
    private final File layoutFile;
    private final JsonAPI.JsonContent jsonContent;


    /**
     * Instantiates a new Template html and fill jsonContent file with the configuration file
     *
     * @param layoutFile the layout file
     * @param configFile the config file
     * @throws IOException the io exception
     */
    public TemplateHTML(File layoutFile, File configFile) throws IOException {
        this.layoutFile = layoutFile;
        jsonContent = JsonAPI.analyseFile(configFile);
    }

    /**
     * Initiates the layout file with a basic structure
     *
     * @param emptyFile an empty file which will be the layout file
     * @throws IllegalArgumentException the illegal argument exception
     * @throws IOException              the io exception
     */
    public static void initLayoutFile(File emptyFile) throws IllegalArgumentException, IOException {
        if (emptyFile.length() > 0) {
            throw new IllegalArgumentException();
        }
        String defaultContent =
                "<html lang=\"{{{language}}}\">\n<head>\n<meta charset=\"{{{charset}}}\">" +
                        "\n<title> {{{siteTitle}}} | {{{pageTitle}}} </title>\n</head>\n<body>\n" +
                        "{%include menu.html}\n{{{content}}}\n</body>\n</html>";

        Util.writeFile(defaultContent, new FileWriter(emptyFile));
    }

    /**
     * Generate the html content page in a string with the field completed with the data in the index and
     * the configuration file
     *
     * @param mdFile the md file
     * @return a string which contains the content of the html page
     * @throws IOException the io exception
     */
    public String generatePage(File mdFile) throws IOException {
        MdAPI.MdContent mdContent = MdAPI.analyseFile(mdFile);
        TemplateLoader loader = new FileTemplateLoader(layoutFile.getParentFile(), ".html");
        //TemplateLoader loader = new ClassPathTemplateLoaderCustom(layoutFile.getParentFile().getPath(), ".html");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("layout");
        Map<String, String> parameterMap = new HashMap<>();
        parameterMap.put("language", jsonContent.getLanguage());
        parameterMap.put("charset", jsonContent.getCharset());
        parameterMap.put("siteTitle", jsonContent.getSiteTitle());
        parameterMap.put("pageTitle", mdContent.getPageTitle());
        parameterMap.put("content", mdContent.getContent());
        return template.apply(parameterMap);
    }

/*  Redefinition pour windows : pb de séparateur dans les paths
    class ClassPathTemplateLoaderCustom extends ClassPathTemplateLoader {

        private String prefix = "";

        public ClassPathTemplateLoaderCustom(String prefix, String suffix) {
            super(prefix, suffix);
            this.prefix = prefix;
        }

        @Override
        public String getPrefix() {
            return this.prefix;
        }

        @Override
        protected URL getResource(String location) {
            try {
                return new File(location).toURL();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void setPrefix(String prefix) {
            //this.prefix = notNull(prefix, "A view prefix is required.");
            if (prefix != null) {
                this.prefix += prefix;
                if (!this.prefix.endsWith("\\")) {
                    this.prefix += "\\";
                }
            }
        }

        @Override
        public String resolve(String uri) {
            return getPrefix() + "\\" + normalize(uri) + getSuffix();
        }

        @Override
        protected String normalize(String location) {
            return location;
        }
    }

 */
}
