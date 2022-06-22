package modele.traitement;

import java.sql.*;
import java.util.ArrayList;

import controlleur.ErrorHandler;
import modele.donnee.Observateur;

public abstract class DataGeneral<T> {
    private String table;

    /**
     * Constructor of DataGeneral
     * @param table name of the sql table
     */
    public DataGeneral(String table) {
        if (table != null) this.table = table;
    }

    /**
     * Create the connection with the database
     * @param command the sql request
     * @return the request lines
     */
    public static ResultSet executeSQL(String command) {
        ResultSet rs = null;
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = ConnectionFactory.getConnectionFactory().getConnection();
            statement = connection.prepareStatement(command);
            rs = statement.executeQuery();
        } catch (Exception e) {
            ErrorHandler.show("Erreur de requête", 
                "L'envoie de la requête à échoué", e);
        }
        return rs;
    }

    /**
     * Execute an sql query and return the responses
     * @return an ArrayList of several objects 
     * @throws NumberFormatException
     * @throws SQLException
     */
    public ArrayList<T> getAll() throws NumberFormatException, SQLException {
        ArrayList<T> ret = new ArrayList<T>();
        ResultSet rs = executeSQL("SELECT * FROM " + table);

        while (rs.next()) {
            ret.add(getInstance(rs));
        }
        
        return ret;
    }

    /**
     * Get all observation where the observator is the one requested
     * @param id requested observator
     * @return a resulset of several Observation
     */
    public ResultSet getObservation(String id) {
        return executeSQL("SELECT * FROM Observation WHERE idObs = " + id);
    }

    /**
     * Get all observators on an observation
     * @param idObservation the observation to get his observators
     * @return an ArrayList of observators
     * @throws SQLException
     */
    public ArrayList<Observateur> getObservateur(String idObservation) throws SQLException {
        ArrayList<Observateur> ret = new ArrayList<Observateur>();
        ResultSet rs = executeSQL(
            "SELECT idObservateur, nom, prenom FROM AObserve, Observateur WHERE lobservation = " + idObservation
        );
        while (rs.next()) {
            String idObservateur = rs.getString(1);
            String nom = rs.getString(2);
            String prenom = rs.getString(3);
            ret.add(new Observateur(Integer.parseInt(idObservateur), nom,  prenom ));
        }

        return ret;
    }
    
    public abstract T getInstance(ResultSet rs) throws SQLException;

    /**
     * Delete entries
     * @param columnsNames columns where we want delete entries
     * @param values entry to delete
     */
    public void deleteEntry (String[] columnsNames, String[] values) {
        if (columnsNames.length == values.length && columnsNames.length > 0) {
            String condition = "";
            for (int i = 0; i < columnsNames.length; i++) {
                condition += columnsNames[i] + " = " + values[i];
            }
            executeSQL("DELETE FROM " + table + " WHERE " + condition);
        }
    }
    
}
