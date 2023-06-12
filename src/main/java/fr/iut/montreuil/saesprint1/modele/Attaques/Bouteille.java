package fr.iut.montreuil.saesprint1.modele.Attaques;

import fr.iut.montreuil.saesprint1.modele.Ennemi;
import fr.iut.montreuil.saesprint1.modele.Tours.Tour;

public class Bouteille extends AttaqueTours{
    private Ennemi ennemi;
    public Bouteille(Tour tour, Ennemi ennemi) {
        super(tour, ennemi.getCoordX(), ennemi.getCoordY());
        this.ennemi = ennemi;
    }
    @Override
    public void agit(){

        //Si l'ennemi est mort, la bouteille doit disparaître
        //Si l'ennemi n'est plus ivre, doit disparaitre
        if(this.ennemi.estMort() || this.ennemi.getToursIvres() == 0){
            this.getTour().getEnv().supprimerAttaqueTours(this);
        }
    }

    public Ennemi getEnnemi() {
        return ennemi;
    }
}
