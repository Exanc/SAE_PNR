package model.donnee;

public class Observateur {

    private int idObservateur;
    private String nom;
    private String prenom;

    /**
     * Create an Observator with an single index, a name and a surname
     * @param id
     * @param nom
     * @param prenom
     */
    public Observateur(int id, String nom, String prenom) {
        if (nom != null && prenom != null) {
            this.idObservateur = id;
            this.nom = nom;
            this.prenom = prenom;
        }
    }

    /**
     * Get the id of the Observator
     * @return Integer id
     */
    public int getId () {
        return this.idObservateur;
    }

    /**
     * Get the name of the Observator
     * @return String name
     */
    public String getNom () {
        return this.nom;
    }

    /**
     * Get the surname of the Observator
     * @return String surname
     */
    public String getPrenom () {
        return this.prenom;
    } 
}