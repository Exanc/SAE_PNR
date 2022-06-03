import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import modele.donnee.*;
import modele.traitement.ConnectionFactory;
import modele.traitement.DataChouette;
import modele.traitement.DataLieu;
import modele.*;

public class TestBDD {

    public static void main(String[] args) {
        try {
            System.out.println(ConnectionFactory.getConnectionFactory().getConnection());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        /*try {
            ArrayList<Lieu> listLieu = DataLieu.getAll("Lieu");
            for (Lieu lieu : listLieu) {
                System.out.println(lieu.getXCoord() + " , " + lieu.getYCoord());
            }
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }*/
    }
}
