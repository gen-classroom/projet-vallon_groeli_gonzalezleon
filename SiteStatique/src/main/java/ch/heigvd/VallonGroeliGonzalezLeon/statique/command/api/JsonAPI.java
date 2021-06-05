/*
 * @File JsonAPI.java
 * @Authors : David González León, Jade Gröli, Axel Vallon
 * @Date 19 mars 2021
 */
package ch.heigvd.VallonGroeliGonzalezLeon.statique.command.api;

import ch.heigvd.VallonGroeliGonzalezLeon.statique.util.Util;
import lombok.Getter;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;


/**
 * A class containing methods that allow to analyse and create the config.json file. This class is final and cannot
 * be instantiated.
 */
public final class JsonAPI {

   private JsonAPI() {}

   /**
    * Put the content of default website parameters (charset, site description and keywords) into the given file.
    *
    * @param emptyFile The target file to initialize. This file must exist, and be empty.
    *
    * @throws IOException              if the file does not exist or is not writtable
    * @throws IllegalArgumentException the file must be empty
    */
   public static void initJSONConfigFile(File emptyFile) throws IOException, IllegalArgumentException {
      JSONObject conf = new JSONObject();
      if (emptyFile.length() > 0) { throw new IllegalArgumentException(); }
      conf.put(JsonParameters.CHARSET.getName(), JsonParameters.CHARSET.getDefaultValue());
      conf.put(JsonParameters.SITE_TITLE.getName(), JsonParameters.SITE_TITLE.getDefaultValue());
      conf.put(JsonParameters.KEYWORDS.getName(), JsonParameters.KEYWORDS.getDefaultValue());
      conf.put(JsonParameters.DOMAIN.getName(), JsonParameters.DOMAIN.getDefaultValue());
      conf.put(JsonParameters.LANGUAGE.getName(), JsonParameters.LANGUAGE.getDefaultValue());

      Util.writeFile(conf.toString(), new FileWriter(emptyFile));
   }

   /**
    * Fonction that return the content of a JSON file in the form of a JsonContent instance.
    *
    * @param file file containing the json parameters
    *
    * @return If the file is empty, it returns null, otherwise it returns a JsonContent instance containing the value
    *         of the parameters found in the given file, or null if the parameter is not in the file
    *
    * @throws IOException the file must exist and be readable
    */
   public static JsonContent analyseFile(File file) throws IOException {
      String chaine = Util.readFile(
              new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)));
      if (chaine.equals("")) {
         return null;
      }
      JSONObject obj = new JSONObject(chaine);
      return new JsonContent(getStringContent(obj, JsonParameters.CHARSET.getName()),
                             getStringContent(obj, JsonParameters.DOMAIN.getName()),
                             getStringContent(obj, JsonParameters.KEYWORDS.getName()),
                             getStringContent(obj, JsonParameters.SITE_TITLE.getName()),
                             getStringContent(obj, JsonParameters.LANGUAGE.getName()));

   }

   private static String getStringContent(JSONObject obj, String key) {
      String getStringContent = null;
      try {
         getStringContent = obj.getString(key);
      } catch (org.json.JSONException exception) {
         // nothing to do here, the parameter is just unspecified
      }
      return getStringContent;
   }

   /**
    * An enum describing the different options available in the config.json file and their default value
    */
   enum JsonParameters {

      CHARSET("charset", "UTF-8"), DOMAIN("domain", "www.monsite.ch"), KEYWORDS("keywords", "HTML, CSS, JavaScript"),
      SITE_TITLE("siteTitle", "My statique website"), LANGUAGE("language", "FR");

      @Getter private final String name;
      @Getter private final String defaultValue;

      JsonParameters(String name, String defaultValue) {
         this.name = name;
         this.defaultValue = defaultValue;
      }
   }

   /**
    * A class containing the parameters extracted from a config.json file
    */
   static class JsonContent {
      @Getter private final String charset;
      @Getter private final String domain;
      @Getter private final String keywords;
      @Getter private final String siteTitle;
      @Getter private final String language;


      /**
       * Instantiates a new Json content.
       *
       * @param charset   the charset parameter in the config.json file
       * @param domain    the domain parameter in the config.json file
       * @param keywords  the keywords parameter in the config.json file
       * @param siteTitle the site title parameter in the config.json file
       * @param language  the language parameter in the config.json file
       */
      public JsonContent(String charset, String domain, String keywords, String siteTitle, String language) {
         this.charset = charset;
         this.domain = domain;
         this.keywords = keywords;
         this.siteTitle = siteTitle;
         this.language = language;
      }
   }


}
