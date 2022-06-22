package modele.traitement;

import java.sql.*;

import controlleur.ErrorHandler;
import vue.ERole;

public class SQLQuerys {

    /**
     * Execute une commande sql
     * @param command la commande
     * @return le résultat
     */
    public static ResultSet executeSQL (String command) {
        ResultSet rs = null;
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = ConnectionFactory.getConnectionFactory().getConnection();
            statement = connection.prepareStatement(command);
            if (statement.execute()) rs = statement.getResultSet();
        } catch (Exception e) {
            ErrorHandler.show("Erreur de requête", 
                "L'envoie de la requête à échoué", e);
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * Execute un script en SQL
     * @param script le script
     * @return le resultat 
     */
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
                    output += resultSetToString(statement.getResultSet());
                else
                    output += "<no output>";

            } catch (Exception e) {
                ErrorHandler.show("Erreur de requête", 
                "L'envoie de la requête à échoué", e);
            e.printStackTrace();
            }
        }

        return output;
    }

    /**
     * Convertit un resultset en string
     * @param rs le resultset
     * @return le resultset sous forme de string
     * @throws SQLException
     */
    public static String resultSetToString (ResultSet rs) throws SQLException {

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

    /**
     * Retourne le role de l'utilisateur actuelle
     * @return 3 si administrator, 2 si field_man, 1 si observer, 0 si rien
     * @throws NumberFormatException
     * @throws SQLException
     */
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

    /**
     * Retourne l'id de la dernière observation
     * @return l'id de la dernière observation
     * @throws NumberFormatException
     * @throws SQLException
     */
    public static int getLastObs() throws NumberFormatException, SQLException {
        int ret = 0;
        ResultSet rs = executeSQL("SELECT MAX(idObs) FROM observation;");

        while (rs.next()) {
            ret = rs.getInt(1);
        }

        return ret;
    }

    /**
     * Ajoute un utilisateur à la base de donnée
     * @param username le nom de l'utilisateur
     * @param password le mots de passe
     * @param role le role de l'utilisateur
     */
    public static void addUser(String username, String password, ERole role) {
        // TODO: change localhost
        String host = "localhost";
        executeSQL("CREATE USER " + username + "@" + host + " IDENTIFIED BY '" + password + "';");
        executeSQL("GRANT '" + role.getRole() + "' TO " + username + "@" + host + ";");
        executeSQL("SET DEFAULT ROLE ALL TO " + username + "@" + host + ";");
    }

    /**
     * Récupère l'utilisateur actuelle de la base de donnée
     * @return le nom d'utilisateur
     */
    public static String getCurrentUser() {
        String ret = "";

        try {
            ResultSet rs = executeSQL("SELECT CURRENT_USER();");
            rs.next();
            ret = rs.getString(1);
        } catch (SQLException e) {
            ErrorHandler.show("Erreur de requête", 
            "L'envoie de la requête à échoué", e);
        e.printStackTrace();
        }

        return ret;
    }
}
