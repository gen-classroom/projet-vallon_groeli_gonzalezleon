package ch.heigvd.VallonGroeliGonzalezLeon.statique.command.api;

import ch.heigvd.VallonGroeliGonzalezLeon.statique.util.Util;
import lombok.Getter;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;


public class JsonAPI {
   /**
    * Put the content of default website parameters (charset, site description and keywords)
    *
    * @param emptyFile This file must exist, and be empty
    *
    * @throws IOException              if the file does not exist or is not writtable
    * @throws IllegalArgumentException the file must be empty
    */
   public static void initJSONConfigFile(File emptyFile) throws IOException, IllegalArgumentException {
      JSONObject conf = new JSONObject();
       if (emptyFile.length() > 0) { throw new IllegalArgumentException(); }
      // contenu par défaut
      conf.put("charset", "UTF-8");
      conf.put("siteTitle", "My statique website");
      conf.put("keywords", "HTML, CSS, JavaScript");
      conf.put("domain", "www.monsite.ch");
      conf.put("language", "FR");

      Util.writeFile(conf.toString(), new FileWriter(emptyFile));
   }

   /**
    * Fonction that return the content of a JSON file.
    * If the file is empty, it return an empty map
    *
    * @param file file containing the json parameters
    *
    * @return the JsonContent
    *
    * @throws IOException the file must exist and be readable
    */
   public static JsonContent analyseFile(File file) throws IOException {
      String chaine = Util.readFile(new FileReader(file));
      if (chaine.equals("")) {
         return null;
      }
      JSONObject obj = new JSONObject(chaine);
      return new JsonContent(getStringContent(obj,"charset"),
              getStringContent(obj,"domain"),
              getStringContent(obj,"keywords"),
              getStringContent(obj,"siteTitle"),
              getStringContent(obj, "language"));

   }

   private static String getStringContent(JSONObject obj, String key){
      String getStringContent = null;
      try {
         getStringContent = obj.getString(key);
      }catch (org.json.JSONException exception){
         // nothing to do here, the parameter is just unspecified
      }
      return getStringContent;
   }

   static class JsonContent{
      @Getter private final String charset;
      @Getter private final String domain;
      @Getter private final String keywords;
      @Getter private final String siteTitle;
      @Getter private final String language;


      public JsonContent(String charset, String domain, String keywords, String siteTitle, String language){
         this.charset = charset;
         this.domain = domain;
         this.keywords = keywords;
         this.siteTitle = siteTitle;
         this.language = language;
      }
   }
}
