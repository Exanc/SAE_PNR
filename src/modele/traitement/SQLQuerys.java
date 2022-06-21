package modele.traitement;

import java.sql.*;

import controlleur.ErrorHandler;

public class SQLQuerys {

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
            e.printStackTrace();
        }
        return rs;
    }

    public static int getRole() throws NumberFormatException, SQLException {
        int ret = 0;
        ResultSet rs = executeSQL("SELECT CURRENT_ROLE();");

        while (rs.next()) {
            if (rs.getString(1).contains("administrator") && ret < 3) ret = 3;
            else  if (rs.getString(1).contains("field_man") && ret < 2) ret = 2;
            else  if (rs.getString(1).contains("observer") && ret < 1) ret = 1;
        }

        return ret;
    }
}
