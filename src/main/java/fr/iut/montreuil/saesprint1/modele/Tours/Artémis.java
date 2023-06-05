package fr.iut.montreuil.saesprint1.modele.Tours;

import fr.iut.montreuil.saesprint1.modele.Ennemi;
import fr.iut.montreuil.saesprint1.modele.Environnement;
import fr.iut.montreuil.saesprint1.modele.Projectiles.Flèche;

public class Artémis extends TourAvecPortée {
    private final static int tailleCase = 32;

    //Constantes pour faciliter leur changement
    private final static int cout = 10;
    private final static int dégâts = 4;
    private final static int espaceEntreAttaques = 100;
    private final static int portée = 3;

    //Attributs de la classe
    private Ennemi ennemiAttaqué;
    private Environnement env;
    private int temps = 0;  //Propre à chaque Tour
    
    public Artémis(int x, int y, Environnement env) {
        super("Artémis", cout, x, y, env,portée,espaceEntreAttaques);
        this.ennemiAttaqué = null;
        this.env = env;
    }
    @Override
    public void attaque () {

        if(this.temps %this.getEspaceEntreAttaques() == 0) {
            int i = 0;
            if (this.ennemiAttaqué != null) {
                if (super.ennemiZone(ennemiAttaqué) != null) {
                    Flèche flèche = new Flèche(this, 1, ennemiAttaqué.getCoordX(), ennemiAttaqué.getCoordY());
                    this.env.ajouterProjectile(flèche);
                } else {
                    this.ennemiAttaqué = null;
                }
            }

            while (this.ennemiAttaqué == null && i < this.env.getEnnemis().size()) {
                ennemiAttaqué = super.ennemiZone(this.env.getEnnemis().get(i));
                if (ennemiAttaqué != null) {
                    Flèche flèche = new Flèche(this, 1, ennemiAttaqué.getCoordX(), ennemiAttaqué.getCoordY());
                    this.env.ajouterProjectile(flèche);
                }
                i++;
            }
        }
        this.temps++;
    }

}