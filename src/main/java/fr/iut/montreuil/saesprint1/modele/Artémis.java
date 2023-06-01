package fr.iut.montreuil.saesprint1.modele;

import java.util.ArrayList;

public class Artémis extends TourAvecPortée {

    private final static int dégâts = 4;
    private final static int tailleCase = 32;

    private Ennemi ennemiAttaqué;
    private Environnement env;


    public Artémis(int x, int y, Environnement env) {
        super("Artémis", 10, x, y, env,4,5);
        this.ennemiAttaqué = null;
        this.env = env;
    }



    private Ennemi ennemiZone() {

        for (Ennemi e : this.env.getEnnemis()) {
            //Formule de Manhattan
            //Portée +16 pour qu'il commence à la moitié de la case de 32 pixels
//            if (((Math.abs(super.centreTourX().get() - e.getCoordX())) + (Math.abs(super.centreTourY().get() - e.getCoordY()))) <= super.getPortée()*tailleCase+(tailleCase/2)) {
//                System.out.println("Trouve l'ennemi ");
//                return e;
//            }
            if((Math.sqrt(Math.pow(e.getCoordX() - super.centreTourX().get(),2)) + Math.pow(e.getCoordY() - super.centreTourY().get(),2)) <= super.getPortée()*tailleCase+(tailleCase/2)){
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

            Projectile projectile = new Projectile(this,1, cible.getCoordX(),cible.getCoordY());
            System.out.println("envoit un projectile");
        }
    }

}