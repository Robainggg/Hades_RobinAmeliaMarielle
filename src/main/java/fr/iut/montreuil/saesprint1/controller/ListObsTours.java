package fr.iut.montreuil.saesprint1.controller;

import fr.iut.montreuil.saesprint1.modele.Attaques.AttaqueTours;
import fr.iut.montreuil.saesprint1.modele.Attaques.Vegetation;
import fr.iut.montreuil.saesprint1.modele.Environnement;
import fr.iut.montreuil.saesprint1.modele.Tours.Déméter;
import fr.iut.montreuil.saesprint1.modele.Tours.Tour;
import fr.iut.montreuil.saesprint1.vue.SpriteTour;
import javafx.collections.ListChangeListener;
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
                this.pane.getChildren().remove(this.pane.lookup("#" + aSupprimer.getId()));

                if (aSupprimer instanceof Déméter) {
                    for (int i = this.evt.getVegetation().size() - 1; i >= 0; i--) {
                        Vegetation vege = this.evt.getVegetation().get(i);

                        if (vege.getTour().equals(aSupprimer)) {
                            this.evt.supprimerVegetation(vege);
                        }
                    }

                }
            }
        }
    }
}