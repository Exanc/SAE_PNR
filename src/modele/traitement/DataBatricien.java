import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import donnee.*;

public class DataBatricien {

    private Connection c;
    private ArrayList<ObsBatracien> liste_ObsBatricien;

    public Connection setC(String url) {
        return this.c = DriverManager.getConnection(url);
    }

    public void getAll () {
        this.c = setC("jdbc:mysql://localhost:3306/myDB");

        Statement statement = this.c.getStatement();

        ResultSet rs = statement.executeQuery("SELECT NOM,PRENOM FROM OBS_BATRACIEN ");

        int id = 0;
        while (rs.next()) {
            String nom = rs.getString("NOM");
            String prenom = rs.getString("PRENOM");
            for (ObsBatracien obsBatracien : liste_ObsBatricien) {
                obsBatracien = new ObsBatracien(id);
                id++;
            }
        }
    }
}