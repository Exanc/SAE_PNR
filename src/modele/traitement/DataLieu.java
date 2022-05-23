package modele.traitement;

import java.util.ArrayList;
import modele.donnee.*;
import java.sql.*;

public class DataLieu extends Table<Lieu> {

    public static ArrayList<Lieu> getAll(String str) throws NumberFormatException, SQLException {

        ResultSet rs = null;

        try {
            PreparedStatement statement = ConnectionFactory.getConnectionFactory().getConnection().prepareStatement("SELECT * FROM ?");
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
                
                String x = rs.getString("coord_Lambert_X");
                String y = rs.getString("coord_Lambert_Y");

                list.add(new Lieu(
                    Double.parseDouble(x), Double.parseDouble(y)
                ));
            }
        }

        return list;
    }

    public void deleteEntry(int id) {
        throw new UnsupportedOperationException();
    }
}
