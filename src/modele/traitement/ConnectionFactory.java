package modele.traitement;

import java.sql.*;

public class ConnectionFactory {

    private String url = "";
    private String user = "";
    private String password = "";

    private static Connection connection = null;
    private static ConnectionFactory connectionFactory = null;

    // on a plus beusion du jar, c'est maven qui fair le boulot :)
    private ConnectionFactory () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionFactory getConnectionFactory() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }
        
        return connectionFactory;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
