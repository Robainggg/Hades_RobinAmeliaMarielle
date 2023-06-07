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

        this.temps = 0;

        this.nbEnnemis = environnement.getNiveau() * 5;

        this.cadenceApparition = 240 - 30 * environnement.getNiveau();

    }

    public void prochainEnnemi(){
        if(this.nbEnnemis > 0 && this.temps%cadenceApparition == 0) {
            nbEnnemis--;
            this.environnement.ajouterEnnemi(new Ennemi(environnement));
        }
        if(this.nbEnnemis == 0)
            this.vagueEstFinie = true;
        temps++;
    }

    public boolean isVagueEstFinie() {
        return vagueEstFinie;
    }
}
