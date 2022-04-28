package DataBase;

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
            Model.ErrorHandler.show("Erreur d'inportation de fichier JSON", "Fichier : "+ filePath +"\n\n"+ e.getMessage());
        }

        if (o != null) {
            
            /* On vérifie le type du fichier */
            if (o.containsKey("type")) {
                if (o.get("type").equals(Model.ressources.EJSONType.SQLRequestLibrary.getName())) {
                    data = o;
                } else {
                    Model.ErrorHandler.show("Erreur d'inportation de fichier JSON", "Le fichier "+ filePath 
                                         + " n'est pas de type "+ Model.ressources.EJSONType.SQLRequestLibrary.getName());
                }

            } else {
                Model.ErrorHandler.show("Erreur d'inportation de fichier JSON", "Le fichier "+ filePath + " ne contient pas de champs \"type\".");
            }
        }
    }

    
    /**
     * TODO
     * @param args
     * @param id
     * @return
     */
    public static String make (String[] args, String id) {

        if (data == null) return null;

        if (! data.containsKey("requests")) {
            Model.ErrorHandler.show("Erreur de fichier JSON", "Le fichier charger dans QuerryMaker n'est pas au bon format.");
            return null;
        }

        JSONObject lib = (JSONObject) data.get("requests");
        if (! lib.containsKey(id)) {
            Model.ErrorHandler.show("Erreur de fichier JSON", "La requête "+ id +" n'existe pas.");
            return null;
        }

        String querry = (String) lib.get(id);
        String[] elements = querry.split("{\\$[0-9]}");

        String _finalQuerry = "";

        if (args.length + 1 == elements.length) {

            _finalQuerry = elements[0];
            for (int i = 0; i < args.length; i++) {
                _finalQuerry += args[i] + elements[i + 1]; 
            }
        }

        return _finalQuerry;
    }
    
}
