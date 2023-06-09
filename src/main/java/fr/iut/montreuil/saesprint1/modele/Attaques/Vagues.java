package fr.iut.montreuil.saesprint1.modele.Attaques;

import fr.iut.montreuil.saesprint1.modele.Tours.TourAvecPortée;

public class Vagues {


    private TourAvecPortée tourAvecPortée;

    public Vagues(TourAvecPortée tourAvecPortée) {
        this.tourAvecPortée = tourAvecPortée;
        this.creerVagues();
    }

    // Permet de créer les projectiles de la tour Poséidon (PetiteVague)
    // Calcule les coordonnées des petites vagues de son périmètre
    public void creerVagues() {

        int centerX = this.tourAvecPortée.centreTourX().get();
        int centerY = this.tourAvecPortée.centreTourY().get();
        int rayon = this.tourAvecPortée.getPortée();
        int numVagues;

        if(this.tourAvecPortée.isAmélioré()){numVagues = 16;}
        else{numVagues = 8;}

        // Calcul de l'angle entre chaque point sur le périmètre
        double angle = 2 * Math.PI / numVagues;

        for (int i = 0; i < numVagues; i++) {
            // Calcul des coordonnées x et y du point sur le périmètre
            int x = (int) (centerX + rayon * Math.cos(i * angle));
            int y = (int) (centerY + rayon * Math.sin(i * angle));

            PetiteVague vague = new PetiteVague(this.tourAvecPortée,x,y);
            this.tourAvecPortée.getEnv().ajouterAttaqueTours(vague);
        }
    }
}
