package modele.traitement;

import java.sql.*;

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
            System.out.println("SQL : CONNECTION / QUERY : ERROR");
            e.printStackTrace();
        }
        return rs;
    }

    public static int getRole() throws NumberFormatException, SQLException {
        int ret = 0;
        ResultSet rs = executeSQL("SELECT CURRENT_ROLE();");

        while (rs.next()) {
            if (rs.getString(1).equals("`administrator`@`%`") && ret < 3) ret = 3;
            else  if (rs.getString(1).equals("`field_man`@`%`") && ret < 2) ret = 2;
            else  if (rs.getString(1).equals("`observer`@`%`") && ret < 1) ret = 1;
        }

        return ret;
    }
}
