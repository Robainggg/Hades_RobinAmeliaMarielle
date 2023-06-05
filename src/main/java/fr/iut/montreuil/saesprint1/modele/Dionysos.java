package fr.iut.montreuil.saesprint1.modele;


public class Dionysos extends Tour{

    private final static int tailleCase = 32;

    //Constantes pour faciliter leur changement
    private final static int cout = 20;
    private final static int espaceEntreAttaques = 200;

    //Attributs de la classe
    private Environnement env;
    private int temps = 0;  //Propre à chaque Tour

    public Dionysos(int x, int y, Environnement env) {
        super("Dionysos", cout, x, y, env,espaceEntreAttaques);
        this.env = env;
    }
    
    @Override
    public void attaque() {

        if(this.temps%espaceEntreAttaques == 0) {
            //Choisit un ennemi au hasard parmi tous les ennemis (attention pas tjrs le premier de la liste)
            int indice = (int) (Math.random() * this.env.getEnnemis().size());
            Ennemi e = this.env.getEnnemis().get(indice);
            System.out.println("Arrête un ennemi 30 tours");

            //Le stoppe
            e.arrêteEnnemi(30);
        }
        temps++;
        
        

    }
}
