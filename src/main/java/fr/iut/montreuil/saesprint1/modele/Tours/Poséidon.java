package fr.iut.montreuil.saesprint1.modele.Tours;

import fr.iut.montreuil.saesprint1.modele.Environnement;

public class Poséidon extends TourAvecPortée{

    //Constantes pour faciliter leur changement
    private final static int cout = 20;
    private final static int espaceEntreAttaques = 1000;
    private final static int portée = 6;

    public Poséidon(String nomTour, int x, int y, Environnement env) {
        super("Poséidon", cout, x, y, env, portée, espaceEntreAttaques);
    }

    @Override
    public void attaque() {

    }
}
