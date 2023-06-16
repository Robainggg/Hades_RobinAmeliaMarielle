package fr.iut.montreuil.saesprint1.controller;

import fr.iut.montreuil.saesprint1.modele.Attaques.AttaqueTours;
import fr.iut.montreuil.saesprint1.modele.Attaques.Vegetation;
import fr.iut.montreuil.saesprint1.modele.Environnement;
import fr.iut.montreuil.saesprint1.modele.Tours.Déméter;
import fr.iut.montreuil.saesprint1.modele.Tours.Tour;
import fr.iut.montreuil.saesprint1.vue.SpriteTour;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class ListObsTours implements ListChangeListener<Tour> {

    private Pane pane;

    private Environnement evt;

    public ListObsTours(Pane pane, Environnement evt) {
        this.pane = pane;
        this.evt = evt;
    }

    @Override
    public void onChanged(Change<? extends Tour> c) {
        while (c.next()) {
            if (c.wasAdded()) {
                Tour tour = c.getAddedSubList().get(0);
                SpriteTour spriteTour = new SpriteTour(tour, pane, evt);
            }
            if (c.wasRemoved()) {
                Tour aSupprimer = c.getRemoved().get(0);
                this.disparitionAttaques(aSupprimer);
                this.pane.getChildren().remove(this.pane.lookup("#"+aSupprimer.getId()));
            }
        }
    }

    //Fait disparaitre les attaques de la tour qui va être supprimée
    public void disparitionAttaques(Tour tour){

        if (tour instanceof Déméter){
            for(int indEnnemi = this.evt.getEnnemis().size()-1; indEnnemi >= 0; indEnnemi--){
                this.evt.getEnnemis().get(indEnnemi).nestPlusRalenti(1);
            }
            for (int i = this.evt.getVegetation().size()-1; i >= 0; i--) {
                Vegetation vege = this.evt.getVegetation().get(i);
                if (vege.getTour().equals(tour)){
                    this.evt.supprimerVegetation(vege);
                }
            }
        }

        else{
            for (int i = this.evt.getAttaques().size()-1; i >= 0; i--){
                AttaqueTours attaqueTours = this.evt.getAttaques().get(i);
                if(attaqueTours.getTour().equals(tour)){
                    this.evt.supprimerAttaqueTours(attaqueTours);
                }
            }
        }
    }
}