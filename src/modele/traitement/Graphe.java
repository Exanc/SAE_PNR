package modele.traitement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Graphe {
    
    private HashMap<Sommet, ArrayList<Sommet>> sommetsVoisins;

    public Graphe (ArrayList<Sommet> sommets, double distMax) {

        if (sommets == null)
            throw new IllegalArgumentException();

        this.sommetsVoisins = new HashMap<Sommet, ArrayList<Sommet>>();

        for (Sommet s : sommets) {
            ArrayList<Sommet> voisins = null;

            for (Sommet s2 : sommets) {
                if (!s.equals(s2) && s.caculeDist(s2) < distMax) {

                    if (voisins == null) {
                        voisins = new ArrayList<Sommet>();
                    }
                    voisins.add(s2);
                }
            }
            this.sommetsVoisins.put(s, voisins);
        }
    }

    /**
     * @return the number of edges in the graph
     */
    public int nbSomments () {
        return this.sommetsVoisins.size();
    }

    /**
     * @returnxthe adjacency matrix 
     */
    public int[][] matriceAdjacence () {

        int[][] res = new int[this.nbSomments()][this.nbSomments()];

        int i = 0;
        Set<Map.Entry<Sommet, ArrayList<Sommet>>> entries = this.sommetsVoisins.entrySet();

        for (Map.Entry<Sommet, ArrayList<Sommet>> entry : entries) {

            if (entry.getValue() != null) {


            }
            i++;
        }

        return res;
    }
}
