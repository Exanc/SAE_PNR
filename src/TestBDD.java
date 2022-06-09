import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import modele.donnee.*;
import modele.traitement.ConnectionFactory;
import modele.traitement.DataLieu;
import modele.*;

public class TestBDD {

    public static void main(String[] args) {
        //modele.traitement.ConnectionFactory.setProperties("root", "123456741", null);
        try {
            System.out.println(ConnectionFactory.getConnectionFactory().getConnection());
            System.out.println("Connecté à la BDD");

            ArrayList<Lieu> listLieu = new DataLieu().getAll();
            for (Lieu lieu : listLieu) {
                System.out.println(lieu.getXCoord() + " , " + lieu.getYCoord());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
