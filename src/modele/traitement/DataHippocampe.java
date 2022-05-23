package modele.traitement;

import java.sql.*;
import java.util.ArrayList;

import modele.donnee.*;

public class DataHippocampe extends DataGeneral<ObsHippocampe> {

    public static ArrayList<ObsHippocampe> getAll (String str) throws NumberFormatException, SQLException {

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

    private static ArrayList<ObsHippocampe> getFromResultSet (ResultSet rs) throws NumberFormatException, SQLException {

        ArrayList<ObsHippocampe> list = new ArrayList<ObsHippocampe>();

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

                ArrayList<Observateur> liste_obervateurs = DataObservateur.getAll("Observateur, AObserve, ObsHippocampe WHERE lobservateur = idObservateur AND lobservation = " + id + "\"");

                Double laTaille = rs.getDouble("taille");
                String leTypePeche = rs.getString("typePeche");
                String IEspece = rs.getString("espece");
                String leSexe = rs.getString("sexe");

                list.add(new ObsHippocampe (
                    id, date, heure, lieu, liste_obervateurs, laTaille , Peche.valueOf(leTypePeche), EspeceHippocampe.valueOf(IEspece), Sexe.valueOf(leSexe)
                ));
            }
        }

        return list;
    }

    public void deleteEntry(int id) {
        throw new UnsupportedOperationException();
    }
}