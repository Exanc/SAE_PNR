package modele.traitement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.donnee.Chouette;
import modele.donnee.EspeceChouette;
import modele.donnee.Sexe;

public class DataChouette extends DataGeneral<Chouette> {

   
    public DataChouette() {
        super("Chouette");
    }

    /**
     * Execute an sql query and return the responses
     * @return an ArrayList of several objects 
     * @throws NumberFormatException
     * @throws SQLException
     */
    public ArrayList<Chouette> getAll() {
        ArrayList<Chouette> ret = new ArrayList<Chouette>();
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
    public Chouette getInstance(ResultSet rs) throws SQLException {
        
        String id = rs.getString(1);
        String leSexe = rs.getString(3);
        String espece = rs.getString(2);
        
        return new Chouette(id, Sexe.valueOf(leSexe), EspeceChouette.valueOf(espece));
    }
}
