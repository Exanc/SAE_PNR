package modele.traitement;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import modele.donnee.IndiceLoutre;
import modele.donnee.Lieu;
import modele.donnee.ObsLoutre;
import modele.donnee.Observateur;

public class DataObsLoutre extends DataGeneral<ObsLoutre> {

    public DataObsLoutre(String table) {
        super("Obs_Loutre");
    }

    @Override
    public ObsLoutre getInstance(ResultSet rs) throws SQLException {
        
        String id = rs.getString(1);

        ResultSet observation = getObs(id);

        String date  = observation.getString(2);
        String heure = observation.getString(3);  
        String coord_x = observation.getString(4);
        String coord_y = observation.getString(5);
        
        Lieu lieu = new Lieu(Double.parseDouble(coord_x), Double.parseDouble(coord_y));

        // DataObservateur.getAll()
        ArrayList<Observateur> observateurs = null;

        String iIndice = rs.getString(4);
        
        return new ObsLoutre(
            Integer.parseInt(id), 
            Date.valueOf(date), 
            Time.valueOf(heure), 
            lieu, 
            observateurs, 
            IndiceLoutre.valueOf(iIndice)
        );
    }
}
