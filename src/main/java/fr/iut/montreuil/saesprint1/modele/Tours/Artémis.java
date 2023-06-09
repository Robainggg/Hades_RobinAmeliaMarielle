package fr.iut.montreuil.saesprint1.modele.Tours;

import fr.iut.montreuil.saesprint1.modele.Ennemi;
import fr.iut.montreuil.saesprint1.modele.Environnement;
import fr.iut.montreuil.saesprint1.modele.Attaques.Flèche;

public class Artémis extends TourAvecPortée {

    //Ici pour faciliter leur changement
    public final static int coutArtémis = 10;
    private final static int espaceEntreAttaques = 10;
    private final static int portée = 3;


    //Attributs de la classe
    private Ennemi ennemiAttaqué;
    
    public Artémis(int x, int y, Environnement env) {
        super("Artémis", coutArtémis, x, y, env,portée,espaceEntreAttaques);
        this.ennemiAttaqué = null;
    }
    @Override
    public void attaque () {

        if (this.getTemps() % this.getEspaceEntreAttaques() == 0) {
            int i = 0;
            if (this.ennemiAttaqué != null) {
                if (super.ennemiZone(ennemiAttaqué) != null && !this.ennemiAttaqué.estMort()) {
                    Flèche flèche = new Flèche(this, ennemiAttaqué.getCoordX(), ennemiAttaqué.getCoordY());
                    super.getEnv().ajouterAttaqueTours(flèche);
                } else {
                    this.ennemiAttaqué = null;
                }
            }

            while (this.ennemiAttaqué == null && i < super.getEnv().getEnnemis().size()) {
                ennemiAttaqué = super.ennemiZone(super.getEnv().getEnnemis().get(i));
                if (ennemiAttaqué != null) {
                    Flèche flèche = new Flèche(this, ennemiAttaqué.getCoordX(), ennemiAttaqué.getCoordY());
                    super.getEnv().ajouterAttaqueTours(flèche);
                }
                i++;
            }
          
        }
        this.incrementeTemps();
    }

    public void setEnnemiAttaqué() {
        this.ennemiAttaqué = null;
    }


}