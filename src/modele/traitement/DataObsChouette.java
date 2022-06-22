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

public class DataObsChouette extends DataGeneral<ObsChouette> {

    public DataObsChouette() {
        super("Obs_Chouette");
    }

    /**
     * Execute an sql query and return the responses
     * @return an ArrayList of several objects 
     * @throws NumberFormatException
     * @throws SQLException
     */
    public ArrayList<ObsChouette> getAll() {
        ArrayList<ObsChouette> ret = new ArrayList<ObsChouette>();
        try {
            ret = super.getAll();
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Get all Object Chouette
     * @return create all objects Chouette
     */
    public ObsChouette getInstance(ResultSet rs) throws SQLException {
        
        String id = rs.getString(4);

        ResultSet observation = getObservation(id);

        String date  = observation.getString(2);
        String heure = observation.getString(3);  
        String coord_x = observation.getString(4);
        String coord_y = observation.getString(5);
        
        Lieu lieu = new Lieu(Double.parseDouble(coord_x), Double.parseDouble(coord_y));

        ArrayList<Observateur> observateurs = getObservateur(id);
        
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
