package modele.traitement;

import java.sql.*;
import java.util.ArrayList;

import modele.donnee.*;

public class DataChouette extends DataGeneral<ObsChouette> {

    public ArrayList<ObsChouette> getAll (String str) throws NumberFormatException, SQLException {

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

    private static ArrayList<ObsChouette> getFromResultSet (ResultSet rs) throws NumberFormatException, SQLException {

        ArrayList<ObsChouette> list = new ArrayList<ObsChouette>();

        if (rs == null) {
            return null;
        }
        else {
            while (rs.next()) {
            
                int id = rs.getInt("numObs");
                Date date  = rs.getDate("dateObs");
                Time heure = rs.getTime("heureObs");

                Double coord_x  = rs.getDouble("lieu_Lambert_X");
                Double coord_y  = rs.getDouble("lieu_Lambert_Y");
                Lieu lieu = new Lieu(coord_x, coord_y);

                ArrayList<Observateur> liste_obervateurs = new DataObservateur().getAll("Observateur, AObserve, ObsChouette WHERE lobservateur = idObservateur AND lobservation = " + id + "\"");

                String type = rs.getString("typeObs");

                list.add(new ObsChouette (
                    id, date, heure, lieu, liste_obervateurs, TypeObservation.valueOf(type)
                ));
            }
        }

        return list;
    }

    public void deleteEntry(int id) {
        throw new UnsupportedOperationException();
    }
}