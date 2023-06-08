package fr.iut.montreuil.saesprint1.modele.Tours;

import fr.iut.montreuil.saesprint1.modele.*;
import fr.iut.montreuil.saesprint1.modele.Attaques.Vagues;

public class Poséidon extends TourAvecPortée{

    //Ici pour faciliter leur changement
    private final static int cout = 20;
    private final static int espaceEntreAttaques = 600;
    private final static int portée = 6;

    public Poséidon(int x, int y, Environnement env) {
        super("Poséidon", cout, x, y, env, portée, espaceEntreAttaques);
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
//                    System.out.println(ennemiZone(e));
//                    System.out.println("Centre X Tour : "+ this.centreTourX());
//                    System.out.println("Centre Y Tour: "+ this.centreTourY());
//                    System.out.println();
                    Vagues vagues = new Vagues(this);
                    attaque = true;
                }
                i--;
            }
        }
        this.incrementeTemps();

    }
}
