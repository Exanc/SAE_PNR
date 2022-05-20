package modele.traitement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.Map;

public class DataAObserver extends Table<Map.Entry<Integer, Integer>> {

    public static HashMap<Integer, Integer> getAll (String str) throws NumberFormatException, SQLException {

        ResultSet rs = null;

        try {
            PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement("SELECT * FROM AObserver?");
            statement.setString(1, str);
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }

        return getFromResultSet(rs);
    }

    public static HashMap<Integer, Integer> getFromResultSet (ResultSet rs) throws NumberFormatException, SQLException {

        // Une liste de pairs :
        //         key => idObservation
        //         Value => idObservateur
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        if (rs == null) {
            return null;

        } else {
            while (rs.next()) {
                
                int lobservation = rs.getInt("lobservation");
                int lobservateur = rs.getInt("lobservateur");
                
                map.put(lobservation, lobservateur);
            }
        }

        return map;
    }

    @Override
    public void deleteEntry(int id) {
        // TODO Auto-generated method stub
    }
}
