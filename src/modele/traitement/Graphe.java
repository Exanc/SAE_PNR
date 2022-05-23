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
     * @return the number of verticies in the graph
     */
    public int nbSomments () {
        return this.sommetsVoisins.size();
    }

    /**
     * @param idSomme id of the vertex
     * @return true, if the vertex is in the graph
     */
    public boolean nbAretes (int idSomme) {
        // TODO
        throw new UnsupportedOperationException();
    }

    /**
     * @param idSom id of a vertex
     * @return the degree of the verticies, -1 if not in the graph
     */
    public int caculeDegre (int idSom) {
        int ret = -1;
        if (this.sommetsVoisins.get(idSom).size() != 0) ret = this.sommetsVoisins.get(idSom).size();
        return ret;
    }

    /**
     * @return the maximum degree
     */
    public int maxDegre () {
        int max = -1;
        for (Map.Entry<Sommet, ArrayList<Sommet>> entry : this.sommetsVoisins.entrySet()) {
            int degree = caculeDegre(entry.getKey().getId());
            if (degree > max) max = degree;
        }
        return max;
    }

    /**
     * @return the vertex with the maximum degree
     */
    public Sommet somMaxDegre () {
        int max = -1;
        Sommet som = null;
        for (Map.Entry<Sommet, ArrayList<Sommet>> entry : this.sommetsVoisins.entrySet()) {
            int degree = caculeDegre(entry.getKey().getId());
            if (degree > max) {
                max = degree;
                som = entry.getKey();
            }
        }
        return som;
    }

    /**
     * @param idSom1 id of a vertex
     * @param idSom2 id of a vertex
     * @return true, if both those verticies are neighbors
     */
    public boolean sontVoisins (int idSom1, int idSom2) {
        // TODO
        throw new UnsupportedOperationException();
    }

    /**
     * @param idSom1 id of a vertex
     * @param idSom2 id of a vertex
     * @return true, if there is a path between theses two verticies
     */
    public boolean existeChemin (int idSom1, int idSom2) {
        // TODO
        throw new UnsupportedOperationException();
    }

    /**
     * @param idSom id of a vertex
     * @return ids of thy neigbhors
     */
    public int[] voisins (int idSom) {
        // TODO
        throw new UnsupportedOperationException();
    }

    /**
     * @param idSom1 id of a vertex
     * @param idSom2 id of a vertex
     * @return true, if an edge has been placed
     */
    public boolean ajouteArete (int idSom1, int idSom2) {
        // TODO
        throw new UnsupportedOperationException();
    }

    /**
     * @param idSom1 id of a vertex
     * @param idSom2 id of a vertex
     * @return true, if an edge has been deleted
     */
    public boolean retireArete (int idSom1, int idSom2) {
        // TODO
        throw new UnsupportedOperationException();
    }

    /**
     * @param idSom1 id of a vertex
     * @param idSom2 id of a vertex
     * @return length of the shortest path between two verticies, -1 if they're not connected
     */
    public double calculeDist (int idSom1, int idSom2) {
        // TODO
        throw new UnsupportedOperationException();
    }

    /**
     * @param idSom1 id of a vertex
     * @param idSom2 id of a vertex
     * @return the number of edges making the shortest path between the two vertices, -1 if there is no path
     */
    public int caculeNbAretes (int idSom1, int idSom2) {
        // TODO
        throw new UnsupportedOperationException();
    }

    /**
     * @return the diameter of the graph
     */
    public int diameter() {
        // TODO
        throw new UnsupportedOperationException();
    }

    /**
     * @return the radius of the graph
     */
    public int rayon () {
        // TODO
        throw new UnsupportedOperationException();
    }

    /**
     * @return the list of connected graphs that make up the graph
     */
    public ArrayList<Graphe> composanteConnexe () {
        // TODO
        throw new UnsupportedOperationException();
    }

    /**
     * @return the transitive closure of the current graph
     */
    public Graphe clotureTransitive () {
        // TODO
        throw new UnsupportedOperationException();
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

                //TODO
            }
            i++;
        }

        return res;
    }
}
