package fr.iut.montreuil.saesprint1.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Environnement {

    private ObservableList<Ennemi> ennemis;
    private ObservableList<Tour> tours;
    private ObservableList<Projectile> projectiles;
    private Terrain terrain;

    public Environnement(Terrain terrain) {
        this.ennemis = FXCollections.observableArrayList();
        this.tours = FXCollections.observableArrayList();
        this.projectiles = FXCollections.observableArrayList();
        this.terrain = terrain;
    }

    public ObservableList<Ennemi> getEnnemis() {
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
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public ObservableList<Tour> getTours() {
        return tours;
    }

    public ObservableList<Projectile> getProjectiles() {
        return projectiles;
    }

}