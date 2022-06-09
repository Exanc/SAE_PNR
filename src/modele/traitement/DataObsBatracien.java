package modele.traitement;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import modele.donnee.EspeceBatracien;
import modele.donnee.Lieu;
import modele.donnee.ObsBatracien;
import modele.donnee.Observateur;

public class DataObsBatracien extends DataGeneral<ObsBatracien> {

    public DataObsBatracien(String table) {
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
        
        ArrayList<Observateur> observateurs = new DataObservateur("Observateur").getAll();

        String nombreAdultes = rs.getString(3);
        String nombreAmplexus = rs.getString(4);
        String nombreTetard = rs.getString(5);
        String nombrePonte = rs.getString(6);
        int[] resObs = new int[4];
        resObs[0] = Integer.parseInt(nombreAdultes);
        resObs[1] = Integer.parseInt(nombreAmplexus);
        resObs[2] = Integer.parseInt(nombreTetard);
        resObs[3] = Integer.parseInt(nombrePonte);

        String IEspece = rs.getString(2);

        return new ObsBatracien(
            Integer.parseInt(id), 
            Date.valueOf(date), 
            Time.valueOf(heure), 
            lieu, 
            observateurs, 
            resObs, 
            EspeceBatracien.valueOf(IEspece)
        );
    }
    
}
