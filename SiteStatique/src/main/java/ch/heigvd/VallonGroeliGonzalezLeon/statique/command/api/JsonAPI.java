package ch.heigvd.VallonGroeliGonzalezLeon.statique.command.api;

import ch.heigvd.VallonGroeliGonzalezLeon.statique.util.Util;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;


public class JsonAPI {
    /**
     * Put the content of default website parameters, the user than can edit the file.
     * The default parameters are hard coded in this function.
     * @param emptyFile This file must exist, and be empty
     * @throws IOException if the file does not exist or is not writtable
     * @throws IllegalArgumentException the file must be empty
     */
    public static void initJSONConfigFile(File emptyFile) throws IOException, IllegalArgumentException {
        JSONObject conf = new JSONObject();
        if (emptyFile.getTotalSpace() > 0)
            throw new IllegalArgumentException();
        // contenu par défaut
        conf.put("titre_site", "Mon site");
        conf.put("langue", "fr");
        conf.put("encodage", "UTF-8");
        conf.put("erreur 404", "404.html");
        conf.put("default_location", emptyFile.getParentFile().getPath());

        Util.writeFile(conf.toString(), new FileWriter(emptyFile));
    }

    /**
     * Fonction that return the content of a JSON file as a map.
     * If the file is empty, it return an empty map
     * @param file file containing the json parameters
     * @return the map with datas
     * @throws IOException the file must exist and be readable
     */
    public static Map<String, Object> returnJSONParam(File file) throws IOException {
        String chaine = Util.readFile(new FileReader(file));
        if(chaine == null || chaine.equals(""))
        {
            return Collections.emptyMap();
        }
        JSONObject obj = new JSONObject(chaine);
        return obj.toMap();
    }
}
