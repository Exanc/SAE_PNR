package modele.traitement;

import java.sql.*;

public class ConnectionFactory {

    private static String url = "";
    private static String user = "";
    private static String password = "";

    private static Connection connection = null;
    private static ConnectionFactory connectionFactory = null;

    private ConnectionFactory () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } 
        catch (ClassNotFoundException e) {
            System.out.println("Erreur de chargement du driver 1");
        }
        catch (Exception e) {
            System.out.println("Erreur de chargement du driver 2");
        }
    }

    public static ConnectionFactory getConnectionFactory() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }
        
        return connectionFactory;
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(url, user, password);
        }

        return connection;
    }
}
