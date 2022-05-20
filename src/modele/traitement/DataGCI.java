package modele.traitement;

import java.sql.*;
import java.util.ArrayList;

import modele.donnee.*;

public class DataGCI extends Table<ObsGCI> {

    public static ArrayList<ObsGCI> getAll (String str) throws NumberFormatException, SQLException {

        ResultSet rs = null;

        try {
            PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement("SELECT * FROM ?");
            statement.setString(1, str);
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }

        return getFromResultSet(rs);
    }

    private static ArrayList<ObsGCI> getFromResultSet (ResultSet rs) throws NumberFormatException, SQLException {

        ArrayList<ObsGCI> list = new ArrayList<ObsGCI>();

        if (rs == null) {
            return null;
        }
        else {
            while (rs.next()) {
            
                String id = rs.getString("obsG");
                String date  = rs.getString("dateObs");
                String heure = rs.getString("heureObs");

                String coord_x  = rs.getString("lieu_Lambert_X");
                String coord_y  = rs.getString("lieu_Lambert_Y");
                Lieu lieu = new Lieu(Double.parseDouble(coord_x), Double.parseDouble(coord_y));

                // TODO : Condition WHERE pas folle help me
                ArrayList<Observateur> liste_obervateurs = DataObservateur.getAll("Observateur, AObserve, ObsChouette WHERE lobservateur = idObservateur AND lobservation = " + id + "\"");

                String nature = rs.getString("nature");

                list.add(new ObsGCI (
                    Integer.parseInt(id), Date.valueOf(date), Time.valueOf(heure), lieu, liste_obervateurs, ContenuNid.valueOf(nature)
                ));
            }
        }

        return list;
    }

    public void deleteEntry(int id) {
        throw new UnsupportedOperationException();
    }
}