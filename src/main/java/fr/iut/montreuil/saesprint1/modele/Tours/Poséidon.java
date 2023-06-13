package fr.iut.montreuil.saesprint1.modele.Tours;

import fr.iut.montreuil.saesprint1.modele.*;
import fr.iut.montreuil.saesprint1.modele.Attaques.Vagues;

public class Poséidon extends TourAvecPortée{

    //Ici pour faciliter leur changement
    public static int coutPoséidon = 20;
    private static int espaceEntreAttaquesPoséidon = 600;
    private static int portéePoséidon = 6;
    private static int nouveauCout = 45;
    private static int nouvelEspaceEntreAttaques = 400;
    private static int nouvellePortée = 12;

    public Poséidon(int x, int y, Environnement env) {
        super("Poséidon", coutPoséidon, x, y, env, portéePoséidon, espaceEntreAttaquesPoséidon);
    }
    @Override
    public void attaque() {

        boolean attaque = false;
        int i;

        if(this.getTemps()%this.getEspaceEntreAttaques() == 0) {
            i = this.getEnv().getEnnemis().size()-1;
            while( i >= 0 && !attaque){
                Ennemi e = this.getEnv().getEnnemis().get(i);
                if(ennemiZone(e)!=null){
                    Vagues vagues = new Vagues(this);
                    attaque = true;
                }
                i--;
            }
        }
        this.incrementeTemps();

    }

    public void améliorer(){
        System.out.println("Poséidon s'améliore");
        super.améliorer(nouveauCout,nouvelEspaceEntreAttaques,nouvellePortée);
    }

}
