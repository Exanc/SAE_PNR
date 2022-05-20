package modele.traitement;

import java.sql.Connection;
import java.util.ArrayList;

public abstract class Table <T>
{
    protected Connection connection;

    public void setConnection (Connection c) {
        this.connection = c;
    }

    public abstract ArrayList<T> getAll ();
    public abstract ArrayList<T> getAllWithFilters (String filters);

    public abstract void deleteEntry (int id);
    // TODO: Quel était la quatriéme méthode demandé par le prof
}
