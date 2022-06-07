package modele.traitement;

import java.sql.*;
import java.util.ArrayList;

import modele.donnee.*;

public class DataLoutre extends DataGeneral<ObsLoutre> {

    public ArrayList<ObsLoutre> getAll (String str) throws NumberFormatException, SQLException {

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

    private static ArrayList<ObsLoutre> getFromResultSet (ResultSet rs) throws NumberFormatException, SQLException {

        ArrayList<ObsLoutre> list = new ArrayList<ObsLoutre>();

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

                ArrayList<Observateur> liste_obervateurs = new DataObservateur().getAll("Observateur, AObserve, ObsLoutre WHERE lobservateur = idObservateur AND lobservation = " + id + "\"");

                String iIndice = rs.getString("indice");

                list.add(new ObsLoutre (
                    id, date, heure, lieu, liste_obervateurs, IndiceLoutre.valueOf(iIndice)
                ));
            }
        }

        return list;
    }

    public void deleteEntry(int id) {
        throw new UnsupportedOperationException();
    }
}