package vue;

public enum ERole {

    ADMINISTRATEUR(         "administrator"),
    CONSULTANT(             "observer"),
    HOMME_DE_TERRAIN(       "field_man");

    private String role;

    ERole (String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}
