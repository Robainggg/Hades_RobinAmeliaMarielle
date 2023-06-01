package fr.iut.montreuil.saesprint1.modele;

public class Dionysos extends Tour{

    private Environnement env;

    public Dionysos(int x, int y, Environnement env) {
        super("Dionysos", 10, x, y, env,5);
        this.env = env;
    }
    
    @Override
    public void attaque() {

    }
}
