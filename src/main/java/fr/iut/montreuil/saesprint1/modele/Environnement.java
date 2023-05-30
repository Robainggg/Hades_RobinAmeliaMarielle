package fr.iut.montreuil.saesprint1.modele;

import java.util.ArrayList;

public class Environnement {

    private ArrayList<Ennemi> ennemis;
    private ArrayList<Tour> tours;
    private ArrayList<Projectile> projectiles;

    private ArrayList<Projectile> àSupprimer;
    private Terrain terrain;

    public Environnement(Terrain terrain) {
        this.ennemis = new ArrayList<>();
        this.tours = new ArrayList<>();
        this.projectiles = new ArrayList<>();
        this.àSupprimer = new ArrayList<>();
        this.terrain = terrain;
    }

    public ArrayList<Ennemi> getEnnemis() {
        return ennemis;
    }

    public void ajouterTour(Tour tour){
        this.tours.add(tour);
    }

    public void ajouterEnnemi(Ennemi ennemi){
        this.ennemis.add(ennemi);
    }

    public void ajouterProjectile(Projectile projectile){this.projectiles.add(projectile);}

    public void supprimerProjectile(Projectile projectile){
        this.projectiles.remove(projectile);
        this.àSupprimer.add(projectile);
    }

    public void aDisparu(Projectile projectile){
        this.àSupprimer.remove(projectile);
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public ArrayList<Tour> getTours() {
        return tours;
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public ArrayList<Projectile> getàSupprimer() {
        return àSupprimer;
    }
}