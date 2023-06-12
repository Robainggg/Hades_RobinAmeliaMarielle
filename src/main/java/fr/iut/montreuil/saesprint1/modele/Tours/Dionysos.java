package fr.iut.montreuil.saesprint1.modele.Tours;


import fr.iut.montreuil.saesprint1.modele.Ennemi;
import fr.iut.montreuil.saesprint1.modele.Environnement;
import fr.iut.montreuil.saesprint1.modele.Attaques.Bouteille;

public class Dionysos extends Tour {

    //Initial
    private static int coutDeBase = 20;
    private static int espaceEntreAttaquesDeBase = 300;

    //Amélioration
    private static int coutAméliorationDionysos =  30;
    private static int nouvelEspaceEntreAttaques = 200;

    private int nbToursIvres = 80;
    private int nbToursTonneau = 200;

    //Attributs de la classe
    private Environnement env;

    public Dionysos(int x, int y, Environnement env) {
        super("Dionysos", coutDeBase, x, y, env, espaceEntreAttaquesDeBase);
    }
    
    @Override
    public void attaque() {
        if (!super.getEnv().getEnnemis().isEmpty()) {
            System.out.println("Il y a un ennemi");
            if (this.getTemps() % this.getEspaceEntreAttaques() == 0) {

            //Choisit un ennemi au hasard parmi tous les ennemis (attention pas tjrs le premier de la liste)
            int indice = (int) (Math.random() * super.getEnv().getEnnemis().size());
            Ennemi e = super.getEnv().getEnnemis().get(indice);

                if (!super.isAmélioré()) {
                    System.out.println("Bouteille");
                    //On cherche un nouvel ennemi si celui-ci est déjà en train de boire
                    while (e.getToursIvres() != 0) {
                        indice = (int) (Math.random() * super.getEnv().getEnnemis().size());
                        e = super.getEnv().getEnnemis().get(indice);
                    }
                    //Lui donne une bouteille
                    Bouteille bouteille = new Bouteille(this, e);
                    e.setToursIvres(nbToursIvres);
                    //System.out.println("Arrête un ennemi" + nbToursIvres + " tours");
                    super.getEnv().ajouterAttaqueTours(bouteille);

                }
                else {
                    System.out.println("Tonneau");
                    while (e.getToursEffetTonneau() != 0) {
                        indice = (int) (Math.random() * super.getEnv().getEnnemis().size());
                        e = super.getEnv().getEnnemis().get(indice);
                    }
                    //Lui balance un tonneau
                    Tonneau tonneau = new Tonneau(this, e);
                    e.setToursEffetTonneau(nbToursTonneau);
                    super.getEnv().ajouterAttaqueTours(tonneau);
                }
            }
        }
        this.incrementeTemps();
    }

    public void améliorer(){
        System.out.println("Dyo s'améliore");
        super.améliorer(coutAméliorationDionysos,nouvelEspaceEntreAttaques);
    }
}
