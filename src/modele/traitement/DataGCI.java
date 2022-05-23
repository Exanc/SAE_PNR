package modele.traitement;

import java.sql.*;
import java.util.ArrayList;

import modele.donnee.*;

public class DataGCI extends DataGeneral<ObsGCI> {

    public static ArrayList<ObsGCI> getAll (String str) throws NumberFormatException, SQLException {

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

    private static ArrayList<ObsGCI> getFromResultSet (ResultSet rs) throws NumberFormatException, SQLException {

        ArrayList<ObsGCI> list = new ArrayList<ObsGCI>();

        if (rs == null) {
            return null;
        }
        else {
            while (rs.next()) {
            
                int id = rs.getInt("obsG");
                Date date  = rs.getDate("dateObs");
                Time heure = rs.getTime("heureObs");

                Double coord_x  = rs.getDouble("lieu_Lambert_X");
                Double coord_y  = rs.getDouble("lieu_Lambert_Y");
                Lieu lieu = new Lieu(coord_x, coord_y);

                ArrayList<Observateur> liste_obervateurs = DataObservateur.getAll("Observateur, AObserve, ObsChouette WHERE lobservateur = idObservateur AND lobservation = " + id + "\"");

                String nature = rs.getString("nature");
                int leNombre = rs.getInt("nombre");

                list.add(new ObsGCI (
                    id, date, heure, lieu, liste_obervateurs, ContenuNid.valueOf(nature), leNombre
                ));
            }
        }

        return list;
    }

    public void deleteEntry(int id) {
        throw new UnsupportedOperationException();
    }
}