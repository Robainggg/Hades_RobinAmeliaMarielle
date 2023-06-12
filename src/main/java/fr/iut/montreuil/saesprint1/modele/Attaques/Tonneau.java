package fr.iut.montreuil.saesprint1.modele.Attaques;

import fr.iut.montreuil.saesprint1.modele.Ennemi;
import fr.iut.montreuil.saesprint1.modele.Tours.Tour;

public class Tonneau extends AttaqueTours{
    private Ennemi ennemi;
    public Tonneau(Tour tour, Ennemi ennemi) {
        super(tour, ennemi.getCoordX(), ennemi.getCoordY());
        this.ennemi = ennemi;
    }

    @Override
    public void agit() {

        if(this.ennemi.estMort() || this.ennemi.getToursEffetTonneau() == 0){
            this.getTour().getEnv().supprimerAttaqueTours(this);
        }
        else{

        }

    }

    public Ennemi getEnnemi() {
        return ennemi;
    }
}


