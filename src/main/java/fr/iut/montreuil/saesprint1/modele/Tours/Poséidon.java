package fr.iut.montreuil.saesprint1.modele.Tours;

import fr.iut.montreuil.saesprint1.modele.*;
import fr.iut.montreuil.saesprint1.modele.Attaques.Vagues;

public class Poséidon extends TourAvecPortée{

    public Poséidon(int x, int y, Environnement env) {
        super("Poséidon", 20, x, y, env, 6, 600);
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
        super.améliorer(45,this.getEspaceEntreAttaques(),3);
    }

}
