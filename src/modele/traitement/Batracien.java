package modele.traitement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.donnee.ObsBatracien;
import modele.donnee.Observateur;

public class Batracien extends Table<ObsBatracien>
{
    @Override
    public ArrayList<ObsBatracien> getAll () {

        ResultSet rs = null;
        try {
            Statement statement = this.connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM OBS_BATRACIEN ");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return getFromResultSet(rs);
    }

    @Override
    public ArrayList<ObsBatracien> getCustom (String filters) {
        
        ResultSet rs = null;
        try {
            Statement statement = this.connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM OBS_BATRACIEN WHERE "+ filters);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return getFromResultSet(rs);
    }

    @Override
    public void deleteEntry(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    private static ArrayList<ObsBatracien> getFromResultSet (ResultSet r)
    {
        ArrayList<ObsBatracien> list = new ArrayList<ObsBatracien>();

        if (r == null) return null;

        while (r.next()) {
            
            String id    = r.getString("ID");
            String date  = r.getString("DATE");
            String heure = r.getString("HEURE");
            String lieu  = r.getString("LIEU");
            String observateurs = r.getString("OBSERVATEURS");
            String resObs  = r.getString("RESOBS");
            String IEspece = r.getString("IESPECE");
            
            String[] observateurs_tab = observateurs.split(",");    
            //String[] resObs_tab = resObs.split(",");
            //int[] resObs_tab2 = Integer.parseInt(resObs_tab);

            // Les observations ne stock que l'id des observateurs
            // Ils va donc falloir trouvé un moyen de récup les observateurs
            // par leurs id.
            // TODO : Systéme de récupération / autorisation
            ArrayList<Observateur> observateurs_liste;
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