package modele.traitement;

import java.sql.*;
import java.util.ArrayList;

import modele.donnee.*;

public class DataGCINid extends DataGeneral<NidGCI> {

    public ArrayList<NidGCI> getAll (String str) throws NumberFormatException, SQLException {

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

    private static ArrayList<NidGCI> getFromResultSet (ResultSet rs) throws NumberFormatException, SQLException {

        ArrayList<NidGCI> list = new ArrayList<NidGCI>();

        if (rs == null) {
            return null;
        }
        else {
            while (rs.next()) {
            
                int id = rs.getInt("idNid");
                String plage  = rs.getString("nomPlage");

                list.add(new NidGCI (
                    id, plage
                ));
            }
        }

        return list;
    }

    public void deleteEntry(int id) {
        throw new UnsupportedOperationException();
    }
}