import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Donnee.ObsBatracien;
import Donnee.Observateur;

public class Batracien extends Table<ObsBatracien>
{
    @Override
    public ArrayList<ObsBatracien> getAll () {

        Statement statement = this.connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM OBS_BATRACIEN ");

        while (rs.next()) {
            String id = rs.getString("ID");
            String date = rs.getString("DATE");
            String heure = rs.getString("HEURE");
            String lieu = rs.getString("LIEU");
            String observateurs = rs.getString("OBSERVATEURS");
            String resObs = rs.getString("RESOBS");
            String IEspece = rs.getString("IESPECE");
            String[] observateurs_tab = observateurs.split(",");    
            //String[] resObs_tab = resObs.split(",");
            //int[] resObs_tab2 = Integer.parseInt(resObs_tab);

            // Les observations ne stock que l'id des observateurs
            // Ils va donc falloir trouvé un moyen de récup les observateurs
            // par leurs id.
            ArrayList<Observateur> observateurs_liste;
            for (int i = 0; i < observateurs_tab.length-1; i++) {
                observateurs_liste.add(observateurs_tab[i]);
            }
            Donnee.ObsBatracien obsBatracien = new Donnee.ObsBatracien(id, date, heure, lieu, observateurs_liste, resObs_tab, IEspece);
            liste_ObsBatricien.add(obsBatracien);
        }
    }

    @Override
    public ArrayList<ObsBatracien> getCuston (String filters) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteEntry(int id) {
        // TODO Auto-generated method stub
        
    }
}