package modele.traitement;

import java.util.ArrayList;
import modele.donnee.*;
import java.sql.*;

public class DataLieu extends DataGeneral<Lieu> {

    public ArrayList<Lieu> getAll(String str) throws NumberFormatException, SQLException {

        ResultSet rs = null;

        try {
            PreparedStatement statement = getDataConnection().prepareStatement("SELECT * FROM ?");
            statement.setString(1, str);
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }

        return getFromResultSet(rs);
    }
    
    private static ArrayList<Lieu> getFromResultSet (ResultSet rs) throws NumberFormatException, SQLException {

        ArrayList<Lieu> list = new ArrayList<Lieu>();

        if (rs == null) {
            return null;
        }
        else {
            while (rs.next()) {
                
                Double coord_x = rs.getDouble("coord_Lambert_X");
                Double coord_y = rs.getDouble("coord_Lambert_Y");

                list.add(new Lieu(
                    coord_x, coord_y
                ));
            }
        }

        return list;
    }

    public void deleteEntry(int id) {
        throw new UnsupportedOperationException();
    }
}
