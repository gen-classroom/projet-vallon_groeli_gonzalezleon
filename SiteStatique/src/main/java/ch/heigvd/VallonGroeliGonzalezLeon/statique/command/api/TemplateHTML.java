/*
 * @File TemplateHTML.java
 * @Authors : David González León, Jade Gröli, Axel Vallon
 * @Date 19 mars 2021
 */
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
 * This class allows to generate html pages from md files following a specific format, using a html file for the
 * layout of the generated page, and a json file for specific parameters.
 * Uses {@link MdAPI} and {@link JsonAPI} classes to analyse md and json files
 */
public class TemplateHTML {
   private final JsonAPI.JsonContent jsonContent;
   private final Template template;


   /**
    * Instantiates a new Template html using aa json config file and an html layout file
    *
    * @param layoutFile the layout file
    * @param configFile the config file
    *
    * @throws IOException if there is an issue while reading the config file or the layout file
    */
   public TemplateHTML(File layoutFile, File configFile) throws IOException {
      jsonContent = JsonAPI.analyseFile(configFile);
      TemplateLoader loader = new FileTemplateLoader(layoutFile.getParentFile(), ".html");
      //TemplateLoader loader = new ClassPathTemplateLoaderCustom(layoutFile.getParentFile().getPath(), ".html");
      Handlebars handlebars = new Handlebars(loader);
      template = handlebars.compile("layout");
   }

   /**
    * Put the content of a default layout file in the given file.
    *
    * @param emptyFile The target file to initialize. This file must exist, and be empty
    *
    * @throws IOException              if the file does not exist or is not writtable
    * @throws IllegalArgumentException the file must be empty
    */
   public static void initLayoutFile(File emptyFile) throws IllegalArgumentException, IOException {
      if (emptyFile.length() > 0) {
         throw new IllegalArgumentException();
      }
      String defaultContent = "<html lang=\"{{{language}}}\">\n<head>\n<meta charset=\"{{{charset}}}\">" +
                              "\n<title> {{{siteTitle}}} | {{{pageTitle}}} </title>\n</head>\n<body>\n" +
                              "{{{content}}}\n</body>\n</html>";
      try (FileWriter writer = new FileWriter(emptyFile)) {
         Util.writeFile(defaultContent, writer);
      }
   }

   /**
    * Generates an html page from the given md file. The md file must follow specific format rules
    *
    * @param mdFile the md file to analyse
    *
    * @return A string containing the content of the html page generated from the md file.
    *
    * @throws IOException if there is an issue while reading the md file or while creating the html content
    */
   public String generatePage(File mdFile) throws IOException {
      MdAPI.MdContent mdContent = MdAPI.analyseFile(mdFile);

      Map<String, String> parameterMap = new HashMap<>();
      parameterMap.put("language", jsonContent.getLanguage());
      parameterMap.put("charset", jsonContent.getCharset());
      parameterMap.put("siteTitle", jsonContent.getSiteTitle());
      parameterMap.put("pageTitle", mdContent.getPageTitle());
      parameterMap.put("content", mdContent.getContent());
      return template.apply(parameterMap);
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
