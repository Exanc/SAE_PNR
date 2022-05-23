package modele.traitement;

import java.sql.*;
import java.util.ArrayList;

public abstract class DataGeneral <T> {

    private static Connection con;

    public static Connection getDataConnection() throws SQLException {
        return con = ConnectionFactory.getConnectionFactory().getConnection();
    }
    
    //Jsp a quoi ça sert
    public abstract void deleteEntry (int id);
    // TODO: Quel était la quatriéme méthode demandé par le prof
}
