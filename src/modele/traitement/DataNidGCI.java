package modele.traitement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.donnee.NidGCI;

public class DataNidGCI extends DataGeneral<NidGCI> {

    public DataNidGCI() {
        super("Nid_GCI");
    }

    public ArrayList<NidGCI> getAll() {
        ArrayList<NidGCI> ret = new ArrayList<NidGCI>();
        try {
            ret = super.getAll();
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public NidGCI getInstance(ResultSet rs) throws SQLException {
        
        String id = rs.getString(1);
        String plage = rs.getString(2);
        
        return new NidGCI(Integer.parseInt(id), plage);
    }
}
