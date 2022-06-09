package modele.traitement;

import java.sql.*;
import java.util.ArrayList;

public abstract class DataGeneral<T> {
    private String table;

    public DataGeneral(String table) {
        if (table != null) this.table = table;
    }

    public ResultSet executeSQL(String command) {
        ResultSet rs = null;
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = ConnectionFactory.getConnectionFactory().getConnection();
            statement = connection.prepareStatement(command);
            rs = statement.executeQuery();
        } catch (Exception e) {
            System.out.println("SQL : CONNECTION / QUERY : ERROR");
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception e) {
                System.out.println("SQL : CLOSE CONNECTION : ERROR");
            }
        }
        return rs;
    }

    public ArrayList<T> getAll() throws NumberFormatException, SQLException {
        ArrayList<T> ret = new ArrayList<T>();
        ResultSet rs = executeSQL("SELECT * FROM " + table);
        while (rs.next()) {
            ret.add(getInstance(rs));
        }

        return ret;
    }

    public ResultSet getObs(String id) {
        return executeSQL("SELECT * FROM Observation WHERE idObs = " + id);
    }
    
    public abstract T getInstance(ResultSet rs) throws SQLException;

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
