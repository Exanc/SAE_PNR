package modele.traitement;

import java.sql.*;
import java.util.ArrayList;

import modele.donnee.*;

public class DataBatracien extends Table<ObsBatracien>{

    public static ArrayList<ObsBatracien> getAll (String str) throws NumberFormatException, SQLException {

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

    public static ArrayList<ObsBatracien> getAllWithFilters (String filters) throws NumberFormatException, SQLException {
        
        ResultSet rs = null;

        try {
            Statement statement = ConnectionFactory.getConnection().createStatement();
            rs = statement.executeQuery("SELECT * FROM Obs_Batracien WHERE " + filters);
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }

        return getFromResultSet(rs);
    }

    //Jsp a quoi ça sert
    public void deleteEntry(int id) {
        throw new UnsupportedOperationException();
    }

    private static ArrayList<ObsBatracien> getFromResultSet (ResultSet rs) throws NumberFormatException, SQLException {

        ArrayList<ObsBatracien> list = new ArrayList<ObsBatracien>();

        if (rs == null) {
            return null;
        }
        else {
            while (rs.next()) {
            
                String id    = rs.getString("obsB");
                String date  = rs.getString("dateObs");
                String heure = rs.getString("heureObs");

                String coord_x  = rs.getString("lieu_Lambert_X");
                String coord_y  = rs.getString("lieu_Lambert_Y");
                Lieu lieu = new Lieu(Double.parseDouble(coord_x), Double.parseDouble(coord_y));

                // Stocke tout les observateurs même si pas dans l'observation
                // Rajouter condition WHERE
                ArrayList<Observateur> liste_obervateurs = DataObservateur.getAll("Observateur, Observation, AObserve WHERE lobservateur = idObservateur AND lobservation = idObs");

                String resObs = rs.getString("RESOBS");
                String[] str = resObs.split(",");
                int[] resObs_tab = new int[str.length];
                for (int i = 0; i < str.length-1; i++) {
                    resObs_tab[i] = Integer.parseInt(str[i]);
                }

                String IEspece = rs.getString("espece");
                
                list.add(new ObsBatracien(
                    Integer.parseInt(id), Date.valueOf(date), Time.valueOf(heure), lieu , liste_obervateurs, resObs_tab, EspeceBatracien.valueOf(IEspece)
                ));
            }
    
            return list;
     
        } 
    }
}