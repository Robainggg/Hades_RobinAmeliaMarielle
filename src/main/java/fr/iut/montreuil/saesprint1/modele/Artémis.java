package fr.iut.montreuil.saesprint1.modele;

import java.util.ArrayList;

public class Artémis extends TourAvecPortée {

    private final static int dégâts = 4;
    private final static int tailleCase = 32;

    private Ennemi ennemiAttaqué;
    private Environnement env;


    public Artémis(int x, int y, Environnement env) {
        super("Artémis", 10, x, y, env,3,30);
        this.ennemiAttaqué = null;
        this.env = env;
    }
    
    @Override
    public void attaque () {
        int i=0;
        if(this.ennemiAttaqué != null) {
            if (super.ennemiZone(ennemiAttaqué) != null) {
//                Projectile projectile = new Projectile(this, 1, ennemiAttaqué.getCoordX(), ennemiAttaqué.getCoordY());
//                this.env.ajouterProjectile(projectile);
            }
            else {this.ennemiAttaqué = null;}
        }

        while(this.ennemiAttaqué == null && i < this.env.getEnnemis().size() ){
            ennemiAttaqué = super.ennemiZone(this.env.getEnnemis().get(i));
            if(ennemiAttaqué!=null){
//                Projectile projectile = new Projectile(this, 1, ennemiAttaqué.getCoordX(), ennemiAttaqué.getCoordY());
//                this.env.ajouterProjectile(projectile);
            }
            i++;
        }
    }

}