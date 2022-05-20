package modele.traitement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.donnee.ObsBatracien;
import modele.donnee.Observateur;

public class DataBatracien extends Table<ObsBatracien>{

    public ArrayList<ObsBatracien> getAll () {

        ResultSet rs = null;

        try {
            Statement statement = this.connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM OBS_BATRACIEN ");
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }

        return getFromResultSet(rs);
    }

    public ArrayList<ObsBatracien> getAllWithFilters (String filters) {
        
        ResultSet rs = null;

        try {
            Statement statement = this.connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM OBS_BATRACIEN WHERE " + filters);
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }

        return getFromResultSet(rs);
    }

    //Jsp a quoi ça sert
    public void deleteEntry(int id) {
        throw new UnsupportedOperationException();
    }

    private static ArrayList<ObsBatracien> getFromResultSet (ResultSet rs) {

        ArrayList<ObsBatracien> list = new ArrayList<ObsBatracien>();
        Statement statement = this.connection.createStatement();
        ResultSet AObserve = get

        if (rs == null) {
            return null;
        }
        else {
            while (rs.next()) {
            
                String id    = rs.getString("obsB");
                String date  = rs.getString("dateObs");
                String heure = rs.getString("heureObs");
                String lieu  = rs.getString("LIEU");
                String observateurs = rs.getString("OBSERVATEURS");
                String resObs  = rs.getString("RESOBS");
                int[] resObs_tab;
                String IEspece = rs.getString("espece");
                
                String[] str = resObs.split(",");
                for (int i = 0; i < str.length-1; i++) {
                    resObs_tab[i] = Integer.parseInt(str[i]);
                }
    
                // Les observations ne stock que l'id des observateurs
                // Ils va donc falloir trouvé un moyen de récup les observateurs
                // par leurs id.
                // TODO : Systéme de récupération / autorisation
                ArrayList<Observateur> observateurs_liste;
                
                String[] observateurs_tab = observateurs.split(","); 
                for (int i = 0; i < observateurs_tab.length-1; i++) {
                    observateurs_liste.add(observateurs_tab[i]);
                }
                
                list.add(new ObsBatracien(
                    id, date, heure, lieu, observateurs_liste, resObs_tab, IEspece
                ));
            }
    
            return list;
     
        } 
    }
}