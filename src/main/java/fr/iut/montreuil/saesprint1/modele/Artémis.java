package fr.iut.montreuil.saesprint1.modele;

import java.util.ArrayList;

public class Artémis extends TourAvecPortée {

    private final static int dégâts = 4;
    private final static int tailleCase = 32;

    private Ennemi ennemiAttaqué;
    private int nbAttaques;
    private Environnement env;

    public Artémis(int x, int y, Environnement env) {
        super("Artémis", 10, x, y, env,4);
        this.ennemiAttaqué = null;
        this.nbAttaques = 1;
        this.env = env;
    }

    private Ennemi ennemiZone() {

        for (Ennemi e : this.env.getEnnemis()) {
            //Formule de Manhattan
            //Portée +16 pour qu'il commence à la moitié de la case de 32 pixels
            if ((Math.abs(super.centreTourX().get() - e.getCoordX()) + Math.abs(super.centreTourY().get() - e.getCoordY()) <= super.getPortée()*tailleCase+16)) {
                System.out.println("Trouve l'ennemi ");
                return e;
            }

        }

        return null;
    }


    @Override
    public void attaque () {
        //System.out.println("attaque");
        Ennemi cible = this.ennemiZone();
        if (cible != null) {
            for (int i = 0; i < this.nbAttaques; i++) {
                System.out.println("envoit un projectile");
            }
        }
    }


}