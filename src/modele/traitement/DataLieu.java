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

        String coord_x = rs.getString(1);
        String coord_y = rs.getString(2);

        return new Lieu(Double.parseDouble(coord_x), Double.parseDouble(coord_y));
    }
}
