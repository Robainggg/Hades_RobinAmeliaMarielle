package fr.iut.montreuil.saesprint1.controller;

import fr.iut.montreuil.saesprint1.modele.Ennemi;
import fr.iut.montreuil.saesprint1.modele.Environnement;
import fr.iut.montreuil.saesprint1.modele.Joueur;

public class VagueEnnemie {

    private Environnement environnement;

    private int temps;

    private Joueur joueur;

    private int nbEnnemis;

    private int cadenceApparition;

    private boolean vagueEstFinie;

    public VagueEnnemie(Environnement environnement, Joueur joueur){
        this.environnement = environnement;
        this.joueur = joueur;

        this.temps = 1;

        this.nbEnnemis = environnement.getNiveau() * 5;

        if(this.environnement.getNiveau() >= 6)
            this.cadenceApparition = 60;
        else
           this.cadenceApparition  = 240 - 30 * environnement.getNiveau();

        this.vagueEstFinie = false;

        System.out.println("Une nouvelle vague de niveau " + this.environnement.getNiveau() + " est créée");

    }

    public void prochainEnnemi(){
        System.out.println(nbEnnemis + " ennemis restants " + temps + " modulo " + cadenceApparition + " = " + temps%cadenceApparition);
        this.incrementeTemps();
        if(this.nbEnnemis > 0 && this.temps%cadenceApparition == 0) {
            nbEnnemis--;
            this.environnement.ajouterEnnemi(new Ennemi(environnement));
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
