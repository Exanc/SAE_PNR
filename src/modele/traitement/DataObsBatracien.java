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
        super("Obs_Batracien");
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

        String date  = observation.getString(2);
        String heure = observation.getString(3);  
        String coord_x = observation.getString(4);
        String coord_y = observation.getString(5);
        
        Lieu lieu = new Lieu(Double.parseDouble(coord_x), Double.parseDouble(coord_y));

        // DataObservateur.getAll()
        ArrayList<Observateur> observateurs = null;

        String resObs = rs.getString(5);
        String[] str = resObs.split(",");
        int[] resObs_tab = new int[str.length];
        for (int i = 0; i < str.length-1; i++) {
            resObs_tab[i] = Integer.parseInt(str[i]);
        }

        String IEspece = rs.getString(2);

        return new ObsBatracien(
            Integer.parseInt(id), 
            Date.valueOf(date), 
            Time.valueOf(heure), 
            lieu, 
            observateurs, 
            resObs_tab, 
            EspeceBatracien.valueOf(IEspece)
        );
    }
    
}
