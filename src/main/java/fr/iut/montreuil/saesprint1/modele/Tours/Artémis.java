package fr.iut.montreuil.saesprint1.modele.Tours;

import fr.iut.montreuil.saesprint1.modele.Ennemi;
import fr.iut.montreuil.saesprint1.modele.Environnement;
import fr.iut.montreuil.saesprint1.modele.Attaques.Flèche;

import java.util.ArrayList;

public class Artémis extends TourAvecPortée {


    //Attributs après amélioration
    private int coutAmélioration = 10;
    private int espaceEntreAttaquesAmélioration = 5;
    private int portéeAmélioration = 3;

    //Attributs de la classe
    private Ennemi ennemiAttaqué;

    private ArrayList<Ennemi> ennemisAttaqués;

    public Artémis(int x, int y, Environnement env) {
        super("Artémis", 10, x, y, env,3,10);
        this.ennemiAttaqué = null;
        this.ennemisAttaqués = new ArrayList<>();
    }
    @Override
    public void attaque () {

        if (this.getTemps() % this.getEspaceEntreAttaques() == 0) {
            //if(!super.isAmélioré()) {
                int i = 0;
                //System.out.println("Premier if : attaque ");
                if (this.ennemiAttaqué != null) {
                    //System.out.println("a déjà un ennemi");
                    if (super.ennemiZone(ennemiAttaqué) != null && !this.ennemiAttaqué.estMort()) {
                        //System.out.println("envoit une flèche");
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
            //}
//            else{
//                 if(!this.ennemisAttaqués.isEmpty()){
//                     int tailleListe = this.ennemisAttaqués.size();
//                     for(int e=0; e < tailleListe; e++){
//
//                     }
//
//                 }
//            }

        }
        this.incrementeTemps();
    }

    public void setEnnemiAttaqué() {
        this.ennemiAttaqué = null;
    }

    public void améliorer(){
        System.out.println("Entre dans Artémis");
        super.améliorer(coutAmélioration,espaceEntreAttaquesAmélioration,portéeAmélioration);
    }
}