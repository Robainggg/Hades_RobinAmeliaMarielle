package fr.iut.montreuil.saesprint1.modele;

import java.util.ArrayList;

public class Artémis extends TourAvecPortée {

    private final static int dégâts = 4;
    
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
            if ((Math.abs(super.centreTourX().get() - e.getCoordX()) + Math.abs(super.centreTourY().get() - e.getCoordY()) <= super.getPortée()*tailleCase+(tailleCase/2))) {
                //System.out.println("Trouve l'ennemi ");
                return e;
            }

        }

        return null;
    }

    private void envoitProjectile(Ennemi e){

        //Savoir dans quelle direction X doit évoluer
        int indicateurDirectionX;
        if(super.getX()+e.getCoordX() < super.getX()){indicateurDirectionX=-1;}
        else if(super.getX()+e.getCoordX() > super.getX()){indicateurDirectionX=1;}
        else{
            indicateurDirectionX=0;
            System.out.println("Cas particulier ennemi en dessous/ au dessus de la tour");
        }

        //Calcul de la droite
        double a = (e.getCoordY() - super.getY()) / (e.getCoordX() - super.getX());  // Coefficient directeur
        double b = super.getY() - a * super.getX();  // Ordonnée à l'origine
        Projectile projectile = new Projectile(this.getX(),this.getY(),a,b,1,this, indicateurDirectionX);
    }
    
    @Override
    public void attaque () {
        //System.out.println("attaque");
        Ennemi cible = this.ennemiZone();
        if (cible != null) {
            //Trouver un moyen pour envoi projectile
            System.out.println("envoit un projectile");
        }
    }


    public int getNbAttaques() {
        return nbAttaques;
    }
}