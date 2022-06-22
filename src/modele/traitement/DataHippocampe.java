package modele.traitement;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import modele.donnee.EspeceHippocampe;
import modele.donnee.Lieu;
import modele.donnee.ObsHippocampe;
import modele.donnee.Observateur;
import modele.donnee.Peche;
import modele.donnee.Sexe;

public class DataHippocampe extends DataGeneral<ObsHippocampe> {

    public DataHippocampe() {
        super("Obs_Hippocampe");
    }

    /**
     * Execute an sql query and return the responses
     * @return an ArrayList of several objects 
     * @throws NumberFormatException
     * @throws SQLException
     */
    public ArrayList<ObsHippocampe> getAll() {
        ArrayList<ObsHippocampe> ret = new ArrayList<ObsHippocampe>();
        try {
            ret = super.getAll();
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Get all Object Hippocampe
     * @return create all objects Hippocampe
     */
    @Override
    public ObsHippocampe getInstance(ResultSet rs) throws SQLException {
        
        String id = rs.getString(1);

        ResultSet observation = getObservation(id);

        String date  = observation.getString(2);
        String heure = observation.getString(3);  
        String coord_x = observation.getString(4);
        String coord_y = observation.getString(5);
        
        Lieu lieu = new Lieu(Double.parseDouble(coord_x), Double.parseDouble(coord_y));
        
        ArrayList<Observateur> observateurs = getObservateur(id);

        String laTaille = rs.getString(6);
        String leTypePeche = rs.getString(5);
        String IEspece = rs.getString(2);
        String leSexe = rs.getString(4);
        
        return new ObsHippocampe(
            Integer.parseInt(id), 
            Date.valueOf(date), 
            Time.valueOf(heure), 
            lieu, 
            observateurs, 
            Double.parseDouble(laTaille), 
            Peche.valueOf(leTypePeche), 
            EspeceHippocampe.valueOf(IEspece), 
            Sexe.valueOf(leSexe)
        );
    }
}
