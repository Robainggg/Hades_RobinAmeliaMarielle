package fr.iut.montreuil.saesprint1.modele.Tours;


import fr.iut.montreuil.saesprint1.modele.Ennemi;
import fr.iut.montreuil.saesprint1.modele.Environnement;
import fr.iut.montreuil.saesprint1.modele.Attaques.Bouteille;

public class Dionysos extends Tour {

    //Ici pour faciliter leur changement
    private final static int cout = 20;
    private final static int espaceEntreAttaques = 300;

    private final static int nbToursIvres = 80;


    public Dionysos(int x, int y, Environnement env) {
        super("Dionysos", cout, x, y, env,espaceEntreAttaques);
    }
    
    @Override
    public void attaque() {
        if (!super.getEnv().getEnnemis().isEmpty()) {
            if (this.getTemps() % espaceEntreAttaques == 0) {

            //Choisit un ennemi au hasard parmi tous les ennemis (attention pas tjrs le premier de la liste)
            int indice = (int) (Math.random() * super.getEnv().getEnnemis().size());
            Ennemi e = super.getEnv().getEnnemis().get(indice);

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
        }
        this.incrementeTemps();
    }
}
