package Donnee;

public class Observateur {

    private int idObservateur;
    private String nom;
    private String prenom;

    /**
     * 
     * @param id
     * @param nom
     * @param prenom
     */
    public Observateur(int id, String nom, String prenom) {
        this.idObservateur = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId () {
        return this.idObservateur;
    }

    public String getNom () {
        return this.nom;
    }

    public String getPrenom () {
        return this.prenom;
    } 
}