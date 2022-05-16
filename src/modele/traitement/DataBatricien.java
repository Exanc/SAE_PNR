import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DataBatricien {

    private Connection c;
    private ArrayList<ObsBatracien> liste_ObsBatricien;

    public Connection setC(String url) {
        return this.c = DriverManager.getConnection(url);
    }

    public void getAll () {
        this.c = setC("jdbc:mysql://localhost:3306/myDB");

        Statement statement = this.c.createStatement();

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
            ArrayList<Observateur> observateurs_liste;
            for (int i = 0; i < observateurs_tab.length-1; i++) {
                observateurs_liste.add(observateurs_tab[i]);
            }
            ObsBatracien obsBatracien = new ObsBatracien(id, date, heure, lieu, observateurs_liste, resObs_tab, IEspece);
            liste_ObsBatricien.add(obsBatracien);
        }
    }
}