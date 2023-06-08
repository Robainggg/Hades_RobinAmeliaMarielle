package fr.iut.montreuil.saesprint1.modele.Tours;


import fr.iut.montreuil.saesprint1.modele.Ennemi;
import fr.iut.montreuil.saesprint1.modele.Environnement;
import fr.iut.montreuil.saesprint1.modele.Attaques.Bouteille;

public class Dionysos extends Tour {

    private final static int tailleCase = 32;

    //Ici pour faciliter leur changement
    private final static int cout = 20;
    private final static int espaceEntreAttaques = 300;

    private final static int nbToursIvres = 80;

    //Attributs de la classe
    private Environnement env;

    public Dionysos(int x, int y, Environnement env) {
        super("Dionysos", cout, x, y, env,espaceEntreAttaques);
        this.env = env;
    }
    
    @Override
    public void attaque() {
        if (!this.env.getEnnemis().isEmpty()) {
            if (this.getTemps() % espaceEntreAttaques == 0) {

                //Choisit un ennemi au hasard parmi tous les ennemis (attention pas tjrs le premier de la liste)
                int indice = (int) (Math.random() * this.env.getEnnemis().size());
                Ennemi e = this.env.getEnnemis().get(indice);

                //On cherche un nouvel ennemi si celui-ci est déjà en train de boire
                while (e.getToursIvres() != 0) {
                    indice = (int) (Math.random() * this.env.getEnnemis().size());
                    e = this.env.getEnnemis().get(indice);
                }
                //Lui donne une bouteille
                Bouteille bouteille = new Bouteille(this, e);
                e.setToursIvres(nbToursIvres);
                //System.out.println("Arrête un ennemi" + nbToursIvres + " tours");
                this.env.ajouterAttaqueTours(bouteille);

            }
            this.incrementeTemps();
        }
    }
}
