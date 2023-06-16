package fr.iut.montreuil.saesprint1.modele.Tours;

import fr.iut.montreuil.saesprint1.modele.Attaques.Vegetation;
import fr.iut.montreuil.saesprint1.modele.Ennemis.Ennemi;
import fr.iut.montreuil.saesprint1.modele.Environnement;

import java.util.ArrayList;

public class Déméter extends TourAvecPortée {

    public static int coutDéméter = 20;
    private static int portéeDeBase = 2;
    //Amélioration
    private static int coutAmélioréDéméter = 25;

    private static int portéeAmélioréeDéméter = 3;
    private static int ralentissement = 1;

    private ArrayList<Ennemi> ennemisAportée;

    public Déméter(int x, int y, Environnement env) {
        super("Déméter", coutDéméter, x, y, env, portéeDeBase, 8, coutAmélioréDéméter,8,portéeAmélioréeDéméter);
        this.ralentissement = 1;
        this.ennemisAportée = new ArrayList<>();
    }

    //Vérifie que les ennemis qui ne sont plus à portée ne sont plus ralentit par cette Tour
    //Ralentit les ennemis à portée si ce n'est pas déjà le cas et les blesse si la tour est améliorée
    @Override
    public void attaque() {

        ArrayList<Ennemi> nouvellesCibles = this.chercheCibles();

        for (int indOld = this.ennemisAportée.size() - 1; indOld >= 0; indOld--) {
            Ennemi ancien = this.ennemisAportée.get(indOld);
            if (ennemiZone(ancien) == null && ancien.estRalenti()) {
                ancien.nestPlusRalenti(ralentissement);
            }
        }

        for (int indNouv = nouvellesCibles.size() - 1; indNouv >= 0; indNouv--) {
            Ennemi nouveau = nouvellesCibles.get(indNouv);
            if (!nouveau.estRalenti()) {
                nouveau.seFaitRalentir(1);
            }
            if (super.isAmélioré() && nouveau.estRalenti()) {
                nouveau.pertPv(1);
            }
        }
        this.ennemisAportée = nouvellesCibles;
    }

    public ArrayList<Ennemi> chercheCibles() {

        ArrayList<Ennemi> nouvellesCibles = new ArrayList<>();
        for (int i = this.getEnv().getEnnemis().size() - 1; i >= 0; i--) {
            Ennemi e = this.getEnv().getEnnemis().get(i);
            if (ennemiZone(e) != null) {
                nouvellesCibles.add(e);
            }
        }
        return nouvellesCibles;
    }

    public void creerVégétation() {

        //On fait en sorte que la tour se retrouve au milieu de la végétation
        int portée = this.getPortée();
        int maxX = this.getX() + portée - 16;
        int minX = this.getX() - portée - 16;
        int maxY = this.getY() + portée - 16;
        int minY = this.getY() - portée - 16;

        for (int x = maxX; x > minX; x -= 32) {
            for (int y = maxY; y > minY; y -= 32) {
                //On ajoute pas la végétation en dehors des limites du terrain
                if (x > 0 && x < (960 - 32) && y > 0 && y < (640 - 32)) {
                    Vegetation vegetation = new Vegetation(this, x, y);
                    this.getEnv().ajouterVegetation(vegetation);
                }
            }
        }
    }

    public void améliorer() {
        super.améliorer();
        if(this.isAmélioré()){creerVégétation();}
    }

}
