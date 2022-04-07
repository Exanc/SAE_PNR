package DB;

import java.sql.Connection;
import java.sql.ResultSet;

public interface IDriver {
    
    /*
     * A voir: https://www.youtube.com/watch?v=e8g9eNnFpHQ
     * A voir: https://doc.ubuntu-fr.org/mysql
     */

    /**
     * Connection à la base de données.
     * @param url adresse de la base de données
     * @param user non d'utilisateur
     * @param password mot de passe
     * @return session SQL
     */
    Connection connect (String url, String user, String password);

    /**
     * Requête à la base de données.
     * @param connection session SQL
     * @param querry la requête
     * @return résultat de la requéte
     */
    ResultSet querry (Connection connection, String querry);
}
