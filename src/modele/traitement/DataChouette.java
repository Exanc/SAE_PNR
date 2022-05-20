package modele.traitement;

import java.sql.*;
import java.util.ArrayList;

import modele.donnee.*;

public class DataChouette extends Table<ObsChouette> {

    public static ArrayList<ObsChouette> getAll (String str) throws NumberFormatException, SQLException {

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

    private static ArrayList<ObsChouette> getFromResultSet (ResultSet rs) throws NumberFormatException, SQLException {

        ArrayList<ObsChouette> list = new ArrayList<ObsChouette>();

        if (rs == null) {
            return null;
        }
        else {
            while (rs.next()) {
            
                String id = rs.getString("numObs");
                String date  = rs.getString("dateObs");
                String heure = rs.getString("heureObs");

                String coord_x  = rs.getString("lieu_Lambert_X");
                String coord_y  = rs.getString("lieu_Lambert_Y");
                Lieu lieu = new Lieu(Double.parseDouble(coord_x), Double.parseDouble(coord_y));

                // TODO : Condition WHERE pas folle help me
                ArrayList<Observateur> liste_obervateurs = DataObservateur.getAll("Observateur, AObserve, ObsChouette WHERE lobservateur = idObservateur AND lobservation = " + id + "\"");

                String type = rs.getString("typeObs");

                list.add(new ObsChouette (
                    Integer.parseInt(id), Date.valueOf(date), Time.valueOf(heure), lieu, liste_obervateurs, TypeObservation.valueOf(type)
                ));
            }
        }

        return list;
    }

    public void deleteEntry(int id) {
        throw new UnsupportedOperationException();
    }
}