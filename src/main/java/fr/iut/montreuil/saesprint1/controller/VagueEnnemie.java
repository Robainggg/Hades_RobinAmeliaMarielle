package fr.iut.montreuil.saesprint1.controller;

import fr.iut.montreuil.saesprint1.modele.Ennemi;
import fr.iut.montreuil.saesprint1.modele.Environnement;
import fr.iut.montreuil.saesprint1.modele.Joueur;

public class VagueEnnemie {

    private Partie partie;

    Environnement environnement;

    private int temps;

    private Joueur joueur;

    private int nbEnnemis;

    private int cadenceApparition;

    private boolean alterneSpawn;

    private boolean vagueEstFinie;

    public VagueEnnemie(Partie partie, Joueur joueur, Environnement environnement){
        this.partie = partie;
        this.joueur = joueur;
        this.alterneSpawn = false;
        this.environnement = environnement;

        this.temps = 1;

        this.nbEnnemis = partie.getNiveau() * 5 + 5;

        this.cadenceApparition  = 240 - 30 * partie.getNiveau();

        this.vagueEstFinie = false;

        System.out.println("Une nouvelle vague de niveau " + this.partie.getNiveau() + " est créée");

    }

    public void prochainEnnemi(){
        System.out.println(nbEnnemis + " ennemis restants " + temps + " modulo " + cadenceApparition + " = " + temps%cadenceApparition);
        this.incrementeTemps();
        if(this.nbEnnemis > 0 && this.temps%cadenceApparition == 0) {
            nbEnnemis--;
            if(!alterneSpawn) {
                this.environnement.ajouterEnnemi(new Ennemi(environnement, 0, 2));
                this.environnement.ajouterEnnemi(new Ennemi(environnement, 0, 3));

                alterneSpawn = !alterneSpawn;
            }
            else {
                this.environnement.ajouterEnnemi(new Ennemi(environnement, 1, 19));
                alterneSpawn = !alterneSpawn;
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
}
