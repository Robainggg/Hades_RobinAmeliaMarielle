package fr.iut.montreuil.saesprint1.modele.Tours;


import fr.iut.montreuil.saesprint1.modele.Attaques.Tonneau;
import fr.iut.montreuil.saesprint1.modele.Ennemi;
import fr.iut.montreuil.saesprint1.modele.Environnement;
import fr.iut.montreuil.saesprint1.modele.Attaques.Bouteille;

public class Dionysos extends Tour {

    private int nbToursIvres = 80;

    private int nbToursTonneau = 120;


    public Dionysos(int x, int y, Environnement env) {
        super("Dionysos", 20, x, y, env, 300);
    }

    @Override
    public void attaque() {
        if (!super.getEnv().getEnnemis().isEmpty()) {
            if (this.getTemps() % this.getEspaceEntreAttaques() == 0) {

                //Choisit un ennemi au hasard parmi tous les ennemis (attention pas tjrs le premier de la liste)
                int indice = (int) (Math.random() * super.getEnv().getEnnemis().size());
                Ennemi e = super.getEnv().getEnnemis().get(indice);

                if (!super.isAmélioré()) {
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

                } else {
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
        super.améliorer(45,this.getEspaceEntreAttaques());
    }
}
