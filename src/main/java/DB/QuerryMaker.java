package DB;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class QuerryMaker {

    /**
     * Objet JSON contenant les requêtes du logiciel
     */
    private static JSONObject data;

    /**
     * Lit le fichier JSON
     * Le fichier doit être de type SQLRequestLibrary
     * @param filePath le chemin vers le fichier
     */
    public static void setData (String filePath) {

        JSONObject o = null;
        try {
            o = (JSONObject) new JSONParser().parse(new FileReader(filePath));
        } catch (Exception e) {
            CORE.ErrorHandler.show("Erreur d'inportation de fichier JSON", "Fichier : "+ filePath +"\n\n"+ e.getMessage());
        }

        if (o != null) {
            
            /* On vérifie le type du fichier */
            if (o.containsKey("type")) {
                if (o.get("type").equals(CORE.ressources.EJSONType.SQLRequestLibrary.getName())) {
                    data = o;
                } else {
                    CORE.ErrorHandler.show("Erreur d'inportation de fichier JSON", "Le fichier "+ filePath 
                                         + " n'est pas de type "+ CORE.ressources.EJSONType.SQLRequestLibrary.getName());
                }

            } else {
                CORE.ErrorHandler.show("Erreur d'inportation de fichier JSON", "Le fichier "+ filePath + " ne contient pas de champs \"type\".");
            }
        }
    }

    // TODO
    public static String make (String[] args, String id) {
        return null;
    }
    
}
