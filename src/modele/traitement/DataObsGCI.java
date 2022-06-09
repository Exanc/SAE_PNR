package modele.traitement;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import modele.donnee.ContenuNid;
import modele.donnee.Lieu;
import modele.donnee.ObsGCI;
import modele.donnee.Observateur;

public class DataObsGCI extends DataGeneral<ObsGCI> {

    public DataObsGCI(String table) {
        super("Obs_GCI");
    }

    public ArrayList<ObsGCI> getAll() {
        ArrayList<ObsGCI> ret = new ArrayList<ObsGCI>();
        try {
            ret = super.getAll();
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public ObsGCI getInstance(ResultSet rs) throws SQLException {
        
        String id = rs.getString(1);

        ResultSet observation = getObs(id);

        String date  = observation.getString(2);
        String heure = observation.getString(3);  
        String coord_x = observation.getString(4);
        String coord_y = observation.getString(5);
        
        Lieu lieu = new Lieu(Double.parseDouble(coord_x), Double.parseDouble(coord_y));
        
        // DataObservateur.getAll()
        ArrayList<Observateur> observateurs = null;
        
        String nature = rs.getString(2);
        String leNombre = rs.getString(3);

        return new ObsGCI(
            Integer.getInteger(id), 
            Date.valueOf(date), 
            Time.valueOf(heure), 
            lieu, 
            observateurs, 
            ContenuNid.valueOf(nature), 
            Integer.parseInt(leNombre)
        );
    }
}