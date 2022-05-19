package modele.donnee;

public class Observateur {

    private int idObservateur;
    private String nom;
    private String prenom;

    /**
     * Create an Observator with an single index, a last name and a first name
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
     * Sets the id
     * @param idObservateur id
     */
    public void setId (int idObservateur) {
        this.idObservateur = idObservateur;
    }

    /**
     * Get the id of the Observator
     * @return id
     */
    public int getId () {
        return this.idObservateur;
    }

    /**
     * Sets the last name
     * @param nom name
     */
    public void setNom (String nom) {

        if (nom == null)
            throw new IllegalArgumentException();

        this.nom = nom;
    }

    /**
     * Get the last name of the Observator
     * @return name
     */
    public String getNom () {
        return this.nom;
    }

    /**
     * Sets the first name
     * @param prenom name
     */
    public void setPrenom (String prenom) {

        if (prenom == null)
            throw new IllegalArgumentException();

        this.prenom = prenom;
    }

    /**
     * Get the first name of the Observator
     * @return name
     */
    public String getPrenom () {
        return this.prenom;
    } 
}