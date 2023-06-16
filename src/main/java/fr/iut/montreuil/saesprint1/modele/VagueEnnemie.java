package fr.iut.montreuil.saesprint1.modele;

public class VagueEnnemie {
    private Partie partie;
    Environnement environnement;
    private int temps;
    private int nbEnnemis;
    private int cadenceApparition;
    private double alterneSpawn;
    private boolean vagueEstFinie;

    public VagueEnnemie(Partie partie, Environnement environnement){
        this.partie = partie;
        this.environnement = environnement;
        this.temps = 1;
        this.nbEnnemis = partie.getNiveau() * 5 + 5;
        this.cadenceApparition  = 240 - 30 * partie.getNiveau();
        this.vagueEstFinie = false;
    }

    public void prochainEnnemi(){
        double doubleSpawn;
        this.incrementeTemps();
        if(this.nbEnnemis > 0 && this.temps%cadenceApparition == 0) {
            nbEnnemis--;
            this.alterneSpawn = Math.random() *2;
            if(alterneSpawn<=1) {
                doubleSpawn = Math.random() *2;
                this.spawnBas( doubleSpawn <= 1);
            }
            else {
                doubleSpawn = Math.random() *2;
                this.spawnHaut( doubleSpawn <= 1);
            }

        }
        if(this.nbEnnemis == 0)
            this.vagueEstFinie = true;

    }

    public boolean isVagueEstFinie() {
        return vagueEstFinie;
    }

    public void incrementeTemps(){
        this.temps++;
    }

    public void spawnBas(boolean doubleSpawn){
        this.environnement.ajouterEnnemi(new Ennemi(environnement,0,19));
        if(doubleSpawn)
            this.environnement.ajouterEnnemi((new Ennemi(environnement,1,19)));
    }

    public void spawnHaut(boolean doubleSpawn){
        this.environnement.ajouterEnnemi(new Ennemi(environnement,0,2));
        if(doubleSpawn)
            this.environnement.ajouterEnnemi((new Ennemi(environnement,0,3)));
    }
}
