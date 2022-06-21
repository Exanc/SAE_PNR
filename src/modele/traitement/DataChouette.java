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

    public ArrayList<Chouette> getAll() {
        ArrayList<Chouette> ret = new ArrayList<Chouette>();
        try {
            ret = super.getAll();
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public Chouette getInstance(ResultSet rs) throws SQLException {
        
        String id = rs.getString(1);
        String leSexe = rs.getString(3);
        String espece = rs.getString(2);
        
        return new Chouette(id, Sexe.valueOf(leSexe), EspeceChouette.valueOf(espece));
    }
}
