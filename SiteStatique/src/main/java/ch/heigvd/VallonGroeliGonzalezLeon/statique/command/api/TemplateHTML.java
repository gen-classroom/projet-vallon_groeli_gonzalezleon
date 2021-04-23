package ch.heigvd.VallonGroeliGonzalezLeon.statique.command.api;

import ch.heigvd.VallonGroeliGonzalezLeon.statique.util.Util;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.TypeSafeTemplate;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.FileTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class TemplateHTML {
    private File layoutFile;
    private File configFile;
    private Template template;
    private JsonAPI.JsonContent jsonContent;


    public TemplateHTML(File layoutFile, File configFile) throws IOException {
        this.layoutFile = layoutFile;
        this.configFile = configFile;
        jsonContent = JsonAPI.returnJSONParam(configFile);
    }

    public static void initLayoutFile(File emptyFile) throws IllegalArgumentException, IOException {
        if (emptyFile.length() > 0) {
            throw new IllegalArgumentException();
        }
        String defaultContent =
                "<html lang=\"en\">\n<head>\n<meta charset={{charset}}>" +
                        "\n<title> {{siteTitle}} | {{pageTitle}} </title>\n</head>\n<body>\n" +
                        "{%include menu.html}\n{{content}}\n</body>\n</html>";

        Util.writeFile(defaultContent, new FileWriter(emptyFile));
    }

    public String generatePage(File mdFile) throws IOException {
        MdAPI.MdContent mdContent = MdAPI.analyseFile(mdFile);
        TemplateLoader loader = new FileTemplateLoader(layoutFile.getParentFile(), ".html");
        //TemplateLoader loader = new ClassPathTemplateLoaderCustom(layoutFile.getParentFile().getPath(), ".html");
        Handlebars handlebars = new Handlebars(loader);
        template = handlebars.compile("layout");

        Map<String, String> parameterMap = new HashMap<>();
        parameterMap.put("siteTitle", jsonContent.getSiteTitle());
        parameterMap.put("pageTitle", mdContent.getPageTitle());
        parameterMap.put("content", mdContent.getContent());
        String templateString = template.apply(parameterMap);
        return templateString;
    }

/*  Redefinition pour windows : pb de separateur dans les paths
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
