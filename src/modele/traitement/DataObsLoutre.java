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

    public DataObsLoutre() {
        super("Obs_Loutre");
    }

    /**
     * Execute an sql query and return the responses
     * @return an ArrayList of several objects 
     * @throws NumberFormatException
     * @throws SQLException
     */
    public ArrayList<ObsLoutre> getAll() {
        ArrayList<ObsLoutre> ret = new ArrayList<ObsLoutre>();
        try {
            ret = super.getAll();
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Execute an sql query and return the responses
     * @return an ArrayList of several objects 
     * @throws NumberFormatException
     * @throws SQLException
     */
    @Override
    public ObsLoutre getInstance(ResultSet rs) throws SQLException {
        
        String id = rs.getString(1);

        ResultSet observation = getObservation(id);

        String date  = observation.getString(2);
        String heure = observation.getString(3);  
        String coord_x = observation.getString(4);
        String coord_y = observation.getString(5);
        
        Lieu lieu = new Lieu(Double.parseDouble(coord_x), Double.parseDouble(coord_y));
        
        ArrayList<Observateur> observateurs = getObservateur(id);

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
