package fr.iut.montreuil.saesprint1.modele.Tours;


import fr.iut.montreuil.saesprint1.modele.Attaques.Tonneau;
import fr.iut.montreuil.saesprint1.modele.Ennemis.Ennemi;
import fr.iut.montreuil.saesprint1.modele.Environnement;
import fr.iut.montreuil.saesprint1.modele.Attaques.Bouteille;

public class Dionysos extends Tour {
    public static int coutDionysos = 20;
    private static int espaceEntreAttaquesDeBase = 300;
    private static int coutAméliorationDionysos =  30;
    private static int nouvelEspaceEntreAttaques = 200;
    private int nbToursIvres = 80;
    private int nbToursTonneau = 160;
    private Environnement env;

    public Dionysos(int x, int y, Environnement env) {
        super("Dionysos", coutDionysos, x, y, env, espaceEntreAttaquesDeBase,coutAméliorationDionysos,nouvelEspaceEntreAttaques);
    }

    //Sélectionne un ennemi au hasard et vérifie qu'il n'est pas déjà servi
    //L'affecte s'il en trouve un non affecté
    @Override
    public void attaque() {

        //Choisit un ennemi au hasard parmi tous les ennemis
        int indice = (int) (Math.random() * super.getEnv().getEnnemis().size());
        Ennemi e = super.getEnv().getEnnemis().get(indice);

        //Trouve un nouvel ennemi si celui-ci est déjà ivre
        while (e.getToursIvres() != 0 || e.getToursEffetTonneau() != 0) {
            indice = (int) (Math.random() * super.getEnv().getEnnemis().size());
            e = super.getEnv().getEnnemis().get(indice);
        }

        if (!super.isAmélioré())
            attaqueNonAméliorée(e);

        else
            attaqueAméliorée(e);
        
    }

    public void attaqueNonAméliorée(Ennemi e){

        Bouteille bouteille = new Bouteille(this, e);
        e.setToursIvres(nbToursIvres);
        super.getEnv().ajouterAttaqueTours(bouteille);
    }

    public void attaqueAméliorée(Ennemi e){

        Tonneau tonneau = new Tonneau(this, e);
        e.setToursEffetTonneau(nbToursTonneau);
        super.getEnv().ajouterAttaqueTours(tonneau);
    }

}
