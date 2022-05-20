package modele.traitement;

import java.sql.*;
import java.util.ArrayList;

public abstract class Table <T> {
    
    //Jsp a quoi ça sert
    // C'est pour supprimer une ligne de la table avec sont id
    public abstract void deleteEntry (int id);
    // TODO: Quel était la quatriéme méthode demandé par le prof
}
