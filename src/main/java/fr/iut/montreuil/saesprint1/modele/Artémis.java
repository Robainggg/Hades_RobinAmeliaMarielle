package fr.iut.montreuil.saesprint1.modele;

import java.util.ArrayList;

public class Artémis extends Tour {

    private final static int dégâts = 4;
    private final static int tailleCase = 32;

    private Ennemi ennemiAttaqué;
    private int portée;
    private int nbAttaques;
    private Environnement env;


    public Artémis(int x, int y, Environnement env) {
        super("Artémis", 10, x, y, env);
        this.ennemiAttaqué = null;
        this.portée = 4;
        this.nbAttaques = 1;
        this.env = env;
    }



    private Ennemi ennemiZone() {

        for (Ennemi e : this.env.getEnnemis()) {
            //Formule de Manhattan
            //Portée +16 pour la moitié de la case
            if ((Math.abs(super.centreTourX().get() - e.getCoordX()) + Math.abs(super.centreTourY().get() - e.getCoordY()) <= portée*tailleCase+(tailleCase/2))) {
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
                //this.envoitProjectile(cible);
                System.out.println("envoit un projectile");
            }
        }
    }

    public int getPortée() {
        return portée;
    }
}