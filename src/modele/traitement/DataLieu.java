package modele.traitement;

import java.util.ArrayList;
import modele.donnee.*;
import java.sql.*;

public class DataLieu extends DataGeneral<Lieu> {
    public DataLieu() {
        super("Lieu");
    }

    public ArrayList<Lieu> getAll() {
        ArrayList<Lieu> ret = new ArrayList<Lieu>();
        try {
            ret = super.getAll();
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public Lieu getInstance(ResultSet rs) throws SQLException {
        return new Lieu(rs.getDouble(1), rs.getDouble(2));
    }
}
