import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import modele.donnee.*;

public class SenarioDonee {

    public static void main(String[] args) throws ClassNotFoundException {
        Observateur alfred = new Observateur(0, "Remorkfeuil", "Alfred");
        System.out.println(alfred.getPrenom() + alfred.getNom());
        Observateur sophie = new Observateur(2, "Preon", "Sophie");
        System.out.println(sophie.getPrenom() + sophie.getNom());

        Lieu lieu1 = new Lieu(50, 55);
        Lieu lieu2 = new Lieu(40, 50);
        Lieu lieu3 = new Lieu(30, 24);
        Lieu lieu4 = new Lieu(69, 42);
        Lieu lieu5 = new Lieu(83, 05);

        // ObsBatracien
        ArrayList<Observateur> observateurs = new ArrayList<Observateur>();
        observateurs.add(sophie);

        ObsBatracien obsBatracien = new ObsBatracien(
            0, 
            new Date(2022, 05, 20), 
            new Time(10, 11, 50), 
            lieu1, 
            observateurs, 
            new int[]{1,1,1}, 
            EspeceBatracien.PELODYTE
        );

        obsBatracien.setNombreAdultes(2);
        obsBatracien.setNombreAmplexus(1);
        obsBatracien.setNombrePonte(10);
        obsBatracien.setNombreTetard(5);
        
        System.out.println("ObsBatracien ");
        System.out.println("id " + obsBatracien.getIdObs());
        System.out.println("nombre d'adulte " + obsBatracien.getNombreAdultes());
        System.out.println("amplexus " + obsBatracien.getNombreAmplexus());
        System.out.println("pontes " + obsBatracien.getNombrePontes());
        System.out.println("tetards " + obsBatracien.getNombreTetard());
        System.out.println("============================\n\n");


        // ObsLoutre
        observateurs = new ArrayList<Observateur>();
        observateurs.add(alfred);

        ObsLoutre obsLoutre = new ObsLoutre(
            0, 
            new Date(2022, 01, 21), 
            new Time(10, 28, 51), 
            lieu4, 
            observateurs, 
            IndiceLoutre.POSITIF
        );
        
        System.out.println("ObsLoutre ");
        System.out.println("id " + obsLoutre.getIdObs());
        System.out.println("indice " + obsLoutre.getIndice());
        System.out.println("============================\n\n");


        // ObsHippocampe
        observateurs = new ArrayList<Observateur>();
        observateurs.add(alfred);
        
        ObsHippocampe obsHippocampe = new ObsHippocampe(
            5, 
            new Date(2022, 8, 3), 
            new Time(11, 10, 00), 
            lieu2, 
            observateurs, 
            8.0,
            Peche.CASIER_CREVETTES,
            EspeceHippocampe.SYNGNATHUS_ACUS,
            Sexe.MALE
        );
                
        System.out.println("ObsHippocampe ");
        System.out.println("id " + obsHippocampe.getIdObs());
        System.out.println("peche " + obsHippocampe.getTypePeche());
        System.out.println("taille " + obsHippocampe.getTaille());
        System.out.println("sexe " + obsHippocampe.getSexe());
        System.out.println("gestant " + obsHippocampe.estGestant());
        System.out.println("============================\n\n");


        // ObsGCI
        observateurs = new ArrayList<Observateur>();
        observateurs.add(sophie);
        observateurs.add(alfred);
        
        ObsGCI obsGCI = new ObsGCI(
            2, 
            new Date(2022, 8, 3), 
            new Time(11, 10, 00), 
            lieu3, 
            observateurs, 
            ContenuNid.POUSSIN,
            2
        );
                
        System.out.println("ObsGCI ");
        System.out.println("id " + obsGCI.getIdObs());
        System.out.println("nature " + obsGCI.getNatureObs());
        System.out.println("============================\n\n");

        // NidGCI
        NidGCI nidGCI = new NidGCI(0, "Plage des oliviers");

        ArrayList<ObsGCI> obsGCIs = new ArrayList<ObsGCI>();
        nidGCI.setNbEnvol(1);
        nidGCI.setLesObservations(obsGCIs);

        System.out.println("NidGCI ");
        System.out.println("id " + nidGCI.getIdNid());
        System.out.println("nb envol " + nidGCI.getNbEnvol());
        System.out.println("plage " + nidGCI.getNomPlage());
        System.out.println("============================\n\n");

        // ObsChouette
        observateurs = new ArrayList<Observateur>();
        observateurs.add(alfred);
        
        ObsChouette obsChouette = new ObsChouette(
            3, 
            new Date(2022, 6, 24), 
            new Time(00, 00, 00), 
            lieu5, 
            observateurs, 
            TypeObservation.SONORE_VISUELLE
        );
                
        System.out.println("ObsChouette ");
        System.out.println("id " + obsChouette.getIdObs());
        System.out.println("type " + obsChouette.getTypeObs());
        System.out.println("============================\n\n");
        
        // Chouette
        Chouette chouette = new Chouette("0", Sexe.FEMELLE, EspeceChouette.CHEVECHE);

        ArrayList<ObsChouette> obChouettes = new ArrayList<ObsChouette>();
        chouette.setLesObservations(obChouettes);

        System.out.println("Chouette ");
        System.out.println("id " + chouette.getIdChouette());
        System.out.println("espece " + chouette.getEspece());
        System.out.println("sexe " + chouette.getSexe());
        System.out.println("============================\n\n");
    }
}
