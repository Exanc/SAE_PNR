package modele.traitement;

import java.sql.*;

public class ConnectionFactory {

    private static String url = "";
    private static String user = "";
    private static String password = "";

    private static Connection connection = null;
    private static ConnectionFactory connectionFactory = null;

    // on a plus beusion du jar, c'est maven qui fair le boulot :)
    private ConnectionFactory () {
        com.mysql.cj.jdbc.Driver d;
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
