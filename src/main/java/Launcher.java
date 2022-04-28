import Vue.App;

public class Launcher {
    
    /**
     * Le point d'entrée du programme
     * @param args
     */
    public static void main(String[] args) {
        /*
         * L'application ne peut pas ce lancer si ont appel directement
         * la classe App.
         */

        App.main(args);
    }

}
