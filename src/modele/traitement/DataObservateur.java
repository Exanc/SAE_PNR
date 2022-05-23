package modele.traitement;

import java.util.ArrayList;
import modele.donnee.*;
import java.sql.*;

public class DataObservateur extends DataGeneral<Observateur> {
    
    public static ArrayList<Observateur> getAll(String str) throws NumberFormatException, SQLException {

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
    
    public static ArrayList<Observateur> getFromResultSet (ResultSet rs) throws NumberFormatException, SQLException {

        ArrayList<Observateur> list = new ArrayList<Observateur>();

        if (rs == null) {
            return null;
        }
        else {
            while (rs.next()) {
                
                String idObservateur = rs.getString("idObservateur");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");

                list.add(new Observateur(
                    Integer.parseInt(idObservateur), nom, prenom
                ));
            }
        }

        return list;
    }

    public void deleteEntry(int id) {
        throw new UnsupportedOperationException();
    }
}
