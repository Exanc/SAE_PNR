package modele.traitement;

import java.sql.*;

public class ConnectionFactory {

    private static String user;
    private static String password;
    private static String url = controlleur.Preferences.getDefaultUrl();

    private static Connection connection = null;
    private static ConnectionFactory connectionFactory = null;

    /*
     * Detection of the driver connectivity
     */
    private ConnectionFactory () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
     * Set the name of the user, his password and the url of the database
     */
    public static void setProperties (String user, String password, String url) {
        
        if (user != null && password != null) {
            ConnectionFactory.user = user;
            ConnectionFactory.password = password;
        }

        if (url != null) ConnectionFactory.url = url;
    }

    /**
     * Renvoi une instance de la classe
     */
    public static ConnectionFactory getConnectionFactory() {
        
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }
        
        return connectionFactory;
    }

    /**
     * Renvoi un objet connection
     * @return connection à la base de données
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://"+ url +"?user="+ user +"&password="+ password);
    }
}
