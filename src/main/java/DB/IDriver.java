package DB;

import java.sql.Connection;
import java.sql.ResultSet;

public interface IDriver {
  
    Connection connect (String url, String user, String password);

    ResultSet querry (Connection connection, String querry);
}
