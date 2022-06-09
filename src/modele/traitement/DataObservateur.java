package modele.traitement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.donnee.Observateur;

public class DataObservateur extends DataGeneral<Observateur> {

    public DataObservateur(String table) {
        super("Observateur");
    }

    public ArrayList<Observateur> getAll() {
        ArrayList<Observateur> ret = new ArrayList<Observateur>();
        try {
            ret = super.getAll();
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public Observateur getInstance(ResultSet rs) throws SQLException {
                
        String id = rs.getString(1);
        String nom = rs.getString(2);
        String prenom = rs.getString(3);

        return new Observateur(Integer.parseInt(id), nom, prenom);
    }
}
