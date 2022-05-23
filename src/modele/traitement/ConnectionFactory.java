package modele.traitement;

import java.sql.*;

public class ConnectionFactory {

    private String url = "";
    private String user = "";
    private String password = "";

    private static ConnectionFactory connection = null;

    private ConnectionFactory () {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } 
        catch (ClassNotFoundException e) {
            System.out.println("Erreur de chargement du driver 1");
        }
        catch (Exception e) {
            System.out.println("Erreur de chargement du driver 2");
        }
    }

    public static ConnectionFactory getConnectionFactory() throws SQLException {
        if (connection == null) connection = new ConnectionFactory();
        return connection;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
