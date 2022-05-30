package modele.traitement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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
     * @return the nomber of edges
     */
    public int nbAretes () {

        int[][] mat = this.matriceAdjacence();
        int num = 0;

        for (int i = 1; i < mat.length; i++) {
            for (int j = 1; j < mat[i].length; j++) {
                num += mat[i][j];
            }
        }
        return num;
    }

    /**
     * @param idSom id of the vertex
     * @return true, if the vertex is in the graph
     */
    public boolean estDansGraphe (int idSom) {

        for (Sommet s : this.sommetsVoisins.keySet()) {
            if (s.getId() == idSom)
                return true;
        }
        return false;
    }

    /**
     * @param idSom id of a vertex
     * @return the degree of the verticies, -1 if not in the graph
     */
    public int caculeDegre (int idSom) {
        
        Sommet som = this.getSommetById(idSom);
        int[][] mat = this.matriceAdjacence();
        int ret = -1;
        
        if (som != null) {
            int index = this.getIndice(som);
            ret = 0;

            for (int j = 0; j < mat[index].length; j++)
                ret += mat[index][j]; 
        }

        return ret;
    }

    /**
     * @return the maximum degree
     */
    public int maxDegre () {
        int max = -1;

        for (Map.Entry<Sommet, ArrayList<Sommet>> entry : this.sommetsVoisins.entrySet()) {
            
            int degree = caculeDegre(entry.getKey().getId());
            
            if (degree > max)
                max = degree;
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

        int[][] mat = this.matriceAdjacence();

        Sommet som1 = this.getSommetById(idSom1);
        Sommet som2 = this.getSommetById(idSom2);
        if (som1 == null || som2 == null) return false;

        if (mat[this.getIndice(som1)][this.getIndice(som2)] == 1)
            return true;
        return false;
    }

    /**
     * @param idSom1 id of a vertex
     * @param idSom2 id of a vertex
     * @return true, if there is a path between theses two verticies
     */
    public boolean existeChemin (int idSom1, int idSom2) {
        
        int index1 = getIndice(getSommetById(idSom1));
        int index2 = getIndice(getSommetById(idSom2));

        // Exploration BFS
        if (index1 != -1 || index2 != -1) {
            
            LinkedList<Integer> file = new LinkedList<Integer>();
            LinkedList<Integer> explored = new LinkedList<Integer>();

            for (int id : voisins(idSom1)) {
                if (id == idSom2)
                    return true;
                else
                    file.add(id);
            }

            while (!file.isEmpty()) {

                int cur = file.poll();
                explored.add(cur);

                for (int id : voisins(cur)) {
                    if (id == idSom2)
                        return true;
                    else if (!explored.contains(id) && !file.contains(id))
                        file.add(id);
                }
            }
        }
        return false;
    }

    /**
     * @param idSom id of a vertex
     * @return ids of thy neigbhors
     */
    public int[] voisins (int idSom) {

        for (var entry : this.sommetsVoisins.entrySet()) {
            if (entry.getKey().getId() != idSom) continue;
            if (entry.getValue() != null) break;
            
            int[] ids = new int[entry.getValue().size()];
            int i = 0;
            for (var som : entry.getValue()) {
                ids[i] = som.getId();
                i++;
            }
        }
        return null;
    }

    /**
     * @param idSom1 id of a vertex
     * @param idSom2 id of a vertex
     * @return true, if an edge has been placed
     */
    public boolean ajouteArete (int idSom1, int idSom2) {
        
        Sommet som1 = getSommetById(idSom1);
        Sommet som2 = getSommetById(idSom2);

        boolean valid = false;
        if (som1 != null && som2 != null) {

            if (this.sommetsVoisins.get(som1).indexOf(som2) == -1) {
                this.sommetsVoisins.get(som1).add(som2);
                valid = valid || true;
            }
            if (this.sommetsVoisins.get(som2).indexOf(som1) == -1) {
                this.sommetsVoisins.get(som2).add(som1);
                valid = valid || true;
            }
        }
        return valid;
    }

    /**
     * @param idSom1 id of a vertex
     * @param idSom2 id of a vertex
     * @return true, if an edge has been deleted
     */
    public boolean retireArete (int idSom1, int idSom2) {
        
        Sommet som1 = getSommetById(idSom1);
        Sommet som2 = getSommetById(idSom2);

        boolean valid = false;
        if (som1 != null && som2 != null) {

            valid = valid || this.sommetsVoisins.get(som1).remove(som2);
            valid = valid || this.sommetsVoisins.get(som2).remove(som1);
        }
        return valid;
    }

    /**
     * @param idSom1 id of a vertex
     * @param idSom2 id of a vertex
     * @return length of the shortest path between two verticies, -1 if they're not connected
     */
    public double calculeDist (int idSom1, int idSom2) {
        
        double dist = 0;
        if (!existeChemin(idSom1, idSom2)) {
            dist = -1;

        } else { // https://fr.wikipedia.org/wiki/Algorithme_de_Dijkstra#Sch%C3%A9ma_de_l'algorithme
            double[][] G = this.matriceAdjacencePoids();

            // Initialisation de l'algorithme
            double[] d = new double[this.sommetsVoisins.size()];
            for (int i = 0; i < d.length; i++) d[i] = Double.POSITIVE_INFINITY;
            d[getIndice(getSommetById(idSom1))] = 0;
            ArrayList<Sommet> Q = new ArrayList<>(this.sommetsVoisins.keySet());

            while (!Q.isEmpty()) {

                // On trouve le somment de distance minimale
                Sommet s1 = null;
                double min = Double.POSITIVE_INFINITY;
                for (Sommet s : Q) {
                    if (d[getIndice(s)] < min) {
                        min = d[getIndice(s)];
                        s1 = s;
                    } 
                }

                Q.remove(s1);
                for (Sommet s2 : this.sommetsVoisins.get(s1)) {
                    if (d[getIndice(s2)] > d[getIndice(s1)] + s1.caculeDist(s2)){
                        d[getIndice(s2)] = d[getIndice(s1)] + s1.caculeDist(s2);
                        //prédécesseur[getIndice(s2)] = s1
                        // NOTE : si jamais on veut retourner le chemin
                    }
                }
            }

            dist = d[getIndice(getSommetById(idSom2))];
        }
        return dist;
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
    public int diametre () {
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
     * @return the adjacency matrix 
     */
    public int[][] matriceAdjacence () {

        int[][] res = new int[this.nbSomments()][this.nbSomments()];

        int i = 0;
        for (var entry : this.sommetsVoisins.entrySet()) {

            if (entry.getValue() != null) {
                for (var som : entry.getValue()) {
                    res[i][this.getIndice(som)] = 1;
                }
            }
            i++;
        }
        return res;
    }

    /**
     * @return the weighted adjancy matrix
     */
    public double[][] matriceAdjacencePoids () {

        double[][] res = new double[this.nbSomments()][this.nbSomments()];

        int i = 0;
        for (var entry : this.sommetsVoisins.entrySet()) {

            if (entry.getValue() != null) {
                for (var som : entry.getValue()) {
                    res[i][this.getIndice(som)] = som.caculeDist(entry.getKey());
                }
            }
            i ++;
        }
        return res;
    }

    /**
     * @param id
     * @return the edge with this id
     */
    private Sommet getSommetById (int id) {

        for (var som : this.sommetsVoisins.keySet())
            if (som.getId() == id) return som;
        return null;
    }

    /**
     * @param s an edge
     * @return the index of this edge inside of the HashMap
     */
    private int getIndice (Sommet s) {

        int i = 0;

        for (var som : this.sommetsVoisins.keySet()) {
            if (som.equals(s))
                return i;
            i++;
        }
        return -1;
    }
}
