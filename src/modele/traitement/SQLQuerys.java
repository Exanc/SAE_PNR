package modele.traitement;

import java.sql.*;

import controlleur.ErrorHandler;
import vue.ERole;

public class SQLQuerys {

    public static ResultSet executeSQL (String command) {
        ResultSet rs = null;
        Statement statement = null;
        Connection connection = null;
        try {
            connection = ConnectionFactory.getConnectionFactory().getConnection();
            statement = connection.prepareStatement(command);
            rs = statement.executeQuery(command);
        } catch (Exception e) {
            ErrorHandler.show("Erreur de requête", 
                "L'envoie de la requête à échoué", e);
            e.printStackTrace();
        }
        return rs;
    }

    public static String executeSQLScript (String script) {

        // TODO: Rafiner umpeut la chose quand le medium de sortie serat mieux

        String[] commands = script.split(";");
        String output = "";

        for (String command : commands) {

            if (command.trim().isEmpty())
                continue;

            ResultSet rs = null;
            PreparedStatement statement = null;
            Connection connection = null;

            try {
                connection = ConnectionFactory.getConnectionFactory().getConnection();
                statement = connection.prepareStatement(command);
                boolean hasResult = statement.execute();

                if (hasResult)
                    output += resultSetToString(statement.executeQuery());
                else
                    output += "<no output>";

            } catch (Exception e) {
                System.out.println("SQL : CONNECTION / QUERY : ERROR");
                e.printStackTrace();
            }
        }

        return output;
    }

    private static String resultSetToString (ResultSet rs) throws SQLException {

        String output = "";

        // Prepare metadata object and get the number of columns.
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

        // Print column names (a header).
        for (int i = 1; i <= columnsNumber; i++) {
            if (i > 1) output += " | ";
            output += rsmd.getColumnName(i);
        }
        output += System.lineSeparator();

        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) output += " | ";
                output += rs.getString(i);
            }
            output += System.lineSeparator();
        }

        return output;
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

    public static int getLastObs() throws NumberFormatException, SQLException {
        int ret = 0;
        ResultSet rs = executeSQL("SELECT MAX(idObs) FROM observation;");

        while (rs.next()) {
            ret = rs.getInt(1);
        }

        return ret;
    }

    public static void addUser(String username, String password, ERole role) {
        // TODO: change localhost
        String host = "localhost";
        String userCreation =      "CREATE USER '" + username + "'@'" + host + "' IDENTIFIED BY '" + password + "';";
        String grantUser =         "GRANT '" + role.getRole() + "' TO " + username + "'@'" + host + "';";
        String setGrantByDefault = "SET DEFAULT ROLE ALL TO '" + username + "'@'" + host + "';";
        executeSQL(userCreation + grantUser + setGrantByDefault);
    }
}
