package modele.traitement;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import org.ietf.jgss.GSSCredential;

import modele.donnee.EspeceBatracien;
import modele.donnee.Lieu;
import modele.donnee.ObsBatracien;
import modele.donnee.Observateur;
import modele.donnee.Observation;

public class DataBatracien extends DataGeneral<ObsBatracien> {

    public DataBatracien(String table) {
        super("ObsBatracien");
    }

    public ArrayList<ObsBatracien> getAll() {
        ArrayList<ObsBatracien> ret = new ArrayList<ObsBatracien>();
        try {
            ret = super.getAll();
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public ObsBatracien getInstance(ResultSet rs) throws SQLException {
        
        String id = rs.getString(1);

        ResultSet observation = getObs(id);

        int idObs = observation.getInt(1);
        Date dateObs = observation.getDate(2);
        Time heureObs = observation.getTime(3);
        Double coordObs_x = observation.getDouble(4);
        Double coordObs_y = observation.getDouble(5);

        String date  = rs.getString(2);
        String heure = rs.getString(3);  
        
        Double coord_x  = coordObs_x;
        Double coord_y  = coordObs_y;
        
        Lieu lieu = new Lieu(coord_x, coord_y);

        ArrayList<Observateur> liste_obervateurs = DataObservateur.getAll();

        String resObs = rs.getString("RESOBS");
        String[] str = resObs.split(",");
        int[] resObs_tab = new int[str.length];
        for (int i = 0; i < str.length-1; i++) {
            resObs_tab[i] = Integer.parseInt(str[i]);
        }

        String IEspece = rs.getString("espece");

        return new ObsBatracien(
            Integer.parseInt(id), 
            Date.valueOf(date), 
            Time.valueOf(heure), 
            lieu, 
            liste_obervateurs, 
            resObs_tab, 
            EspeceBatracien.valueOf(IEspece)
        );
    }
    
}
