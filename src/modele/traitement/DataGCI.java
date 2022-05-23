package modele.traitement;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import modele.donnee.*;

public class DataGCI extends Table<ObsGCI> {

    public static ArrayList<ObsGCI> getAll (String str) throws NumberFormatException, SQLException {

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

    private static ArrayList<ObsGCI> getFromResultSet (ResultSet rs) throws NumberFormatException, SQLException {

        ArrayList<ObsGCI> list = new ArrayList<ObsGCI>();

        if (rs == null) {
            return null;
        }
        else {
            while (rs.next()) {
            
                int    id    = rs.getInt("obsG");
                String date  = rs.getString("dateObs");
                String heure = rs.getString("heureObs");

                String coord_x  = rs.getString("lieu_Lambert_X");
                String coord_y  = rs.getString("lieu_Lambert_Y");
                Lieu lieu = new Lieu(Double.parseDouble(coord_x), Double.parseDouble(coord_y));

                // TODO: ça m'a l'aire bien ?
                HashMap<Integer, Integer> lobservateurs = DataAObserver.getAll("WHERE lobservation = "+ id);
                ArrayList<Observateur> liste_obervateurs = new ArrayList<Observateur>();

                for (Map.Entry<Integer, Integer> entry : lobservateurs.entrySet()) {

                    if (entry.getKey() == id) {
                        int lobservateur = entry.getValue();
                        DataObservateur.getAll("Observateur WHERE idObservateur = " + lobservateur);
                    }
                }

                String nature = rs.getString("nature");

                list.add(new ObsGCI (
                    id, Date.valueOf(date), Time.valueOf(heure), lieu, liste_obervateurs, ContenuNid.valueOf(nature),
                    0 // TODO: le nombre ?
                ));
            }
        }

        return list;
    }

    public void deleteEntry(int id) {
        throw new UnsupportedOperationException();
    }
}