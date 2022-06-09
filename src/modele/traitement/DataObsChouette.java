package modele.traitement;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import modele.donnee.Lieu;
import modele.donnee.ObsChouette;
import modele.donnee.Observateur;
import modele.donnee.TypeObservation;

public class DataChouette extends DataGeneral<ObsChouette> {

    public DataChouette(String table) {
        super("Obs_Chouette");
    }

    public ArrayList<ObsChouette> getAll() {
        ArrayList<ObsChouette> ret = new ArrayList<ObsChouette>();
        try {
            ret = super.getAll();
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public ObsChouette getInstance(ResultSet rs) throws SQLException {
        
        String id = rs.getString(4);

        ResultSet observation = getObs(id);

        String date  = observation.getString(2);
        String heure = observation.getString(3);  
        String coord_x = observation.getString(4);
        String coord_y = observation.getString(5);
        
        Lieu lieu = new Lieu(Double.parseDouble(coord_x), Double.parseDouble(coord_y));
        
        // DataObservateur.getAll()
        ArrayList<Observateur> observateurs = null;
        
        String type = rs.getString(2);

        return new ObsChouette(
            Integer.getInteger(id), 
            Date.valueOf(date), 
            Time.valueOf(heure), 
            lieu, 
            observateurs, 
            TypeObservation.valueOf(type)
        );
    }
    
}
