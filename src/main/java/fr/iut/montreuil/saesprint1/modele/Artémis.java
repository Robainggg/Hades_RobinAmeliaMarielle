package fr.iut.montreuil.saesprint1.modele;

public class Artémis extends TourAvecPortée {

    private final static int dégâts = 4;
    
    private Ennemi ennemiAttaqué;
    //Attention : pour le moment contre-intuitif. Il faut augmenter le nombre d'attaque pour qu'il y en ait moins

    private Environnement env;

    public Artémis(int x, int y, Environnement env) {
        super("Artémis", 10, x, y, env,4,30);
        this.ennemiAttaqué = null;
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