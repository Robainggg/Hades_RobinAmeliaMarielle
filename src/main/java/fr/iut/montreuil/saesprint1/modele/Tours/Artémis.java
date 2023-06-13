package fr.iut.montreuil.saesprint1.modele.Tours;

import fr.iut.montreuil.saesprint1.modele.Ennemi;
import fr.iut.montreuil.saesprint1.modele.Environnement;
import fr.iut.montreuil.saesprint1.modele.Attaques.Flèche;

import java.util.ArrayList;

public class Artémis extends TourAvecPortée {

    //Initial
    public static int coutArtémis = 15;
    private static int porteeDeBase = 3;
    private static int espaceEntreAttaquesDeBase = 20;

    //Amélioration
    private static int coutAméliorationArtémis =  30;
    private static int nouvelEspaceEntreAttaques = 10;
    private static int nouvellePortée = 5;

    //Attributs de la classe
    private Ennemi ennemiAttaqué;

    private ArrayList<Ennemi> ennemisAttaqués;

    public Artémis(int x, int y, Environnement env) {
        super("Artémis", coutArtémis, x, y, env,porteeDeBase,espaceEntreAttaquesDeBase);
        this.ennemiAttaqué = null;
        this.ennemisAttaqués = new ArrayList<>();
    }
    @Override
    public void attaque () {

        if (!super.getEnv().getEnnemis().isEmpty()) {
            if (this.getTemps() % this.getEspaceEntreAttaques() == 0) {
                if (!super.isAmélioré()) {
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
                } else {
                    ArrayList<Ennemi> ennemisCiblés = new ArrayList<>();
                    if (!this.ennemisAttaqués.isEmpty()) {
                        for (int i = 0; i < this.ennemisAttaqués.size(); i++) {
                            if (super.ennemiZone(this.ennemisAttaqués.get(i)) != null && !this.ennemisAttaqués.get(i).estMort()) {
                                //Le stocke dans la nouvelle liste
                                ennemisCiblés.add(this.ennemisAttaqués.get(i));
                            }
                        }
                    }
                    int indiceEnnemi = 0;
                    while (ennemisCiblés.size() < 3 && indiceEnnemi < super.getEnv().getEnnemis().size()) {
                        Ennemi regardé = super.ennemiZone(super.getEnv().getEnnemis().get(indiceEnnemi));
                        if (regardé != null) {
                            if (!this.déjaCiblé(ennemisCiblés, regardé)) {
                                ennemisCiblés.add(regardé);
                            }
                        }
                        indiceEnnemi++;
                    }
                    this.ennemisAttaqués.clear();
                    for (int i = 0; i < ennemisCiblés.size(); i++) {
                        this.ennemiAttaqué = ennemisCiblés.get(i);
                        this.ennemisAttaqués.add(this.ennemiAttaqué);
                        Flèche flèche = new Flèche(this, ennemiAttaqué.getCoordX(), ennemiAttaqué.getCoordY());
                        super.getEnv().ajouterAttaqueTours(flèche);
                    }
                }

            }
        }
        this.incrementeTemps();
    }

    public boolean déjaCiblé(ArrayList<Ennemi> liste, Ennemi ennemi){
        for(int i=0; i < liste.size(); i++){
            if(ennemi.equals(liste.get(i))){
                return true;
            }
        }
        return false;
    }

    public void setEnnemiAttaqué() {
        this.ennemiAttaqué = null;
    }

    public void améliorer(){
        System.out.println("Entre dans Artémis");
        super.améliorer(coutAméliorationArtémis,nouvelEspaceEntreAttaques,nouvellePortée);
    }
}