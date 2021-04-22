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

      Util.writeFile(conf.toString(), new FileWriter(emptyFile));
   }

   /**
    * Fonction that return the content of a JSON file as a map.
    * If the file is empty, it return an empty map
    *
    * @param file file containing the json parameters
    *
    * @return the map with datas
    *
    * @throws IOException the file must exist and be readable
    */
   public static JsonContent returnJSONParam(File file) throws IOException {
      String chaine = Util.readFile(new FileReader(file));
      if (chaine.equals("")) {
         return null;
      }
      JSONObject obj = new JSONObject(chaine);
      return new JsonContent(obj.getString("charset"), obj.getString("domain"),
              obj.getString("keywords"), obj.getString("siteTitle"));
   }

   public static String returnHTMLHeader(File json, final String mdContent) throws IOException {
      String header = "<head>\n";

      //Map<String, Object> map = JsonAPI.returnJSONParam(json);
      Scanner scanner = new Scanner(mdContent);
      int i = 0;
      while (scanner.hasNextLine() && i < 3) {
         String[] line = scanner.nextLine().split(":");
         //map.put(line[0], line[1]);
         i++;
      }
      scanner.close();
      //header += "\t<meta charset=\"" + map.get("charset") + "\">\n";
      //header += "\t<meta name=\"description\" content=\"" + map.get("description") + "\">\n";
      //header += "\t<meta name=\"keywords\" content=\"" + map.get("keywords") + "\">\n";
      //header += "\t<meta name=\"author\" content=\"" + map.get("auteur") + "\">\n";
      //header += "\t<title>" + map.get("titre") + " " + map.get("date") + "</title>\n";
      header += "</head>\n";
      return header;
   }

   static class JsonContent{
      @Getter private final String charset;
      @Getter private final String domain;
      @Getter private final String keywords;
      @Getter private final String siteTitle;

      public JsonContent(String charset, String domain, String keywords, String siteTitle){
         this.charset = charset;
         this.domain = domain;
         this.keywords = keywords;
         this.siteTitle = siteTitle;
      }
   }
}
