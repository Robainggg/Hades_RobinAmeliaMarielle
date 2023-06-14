package fr.iut.montreuil.saesprint1.controller;

import fr.iut.montreuil.saesprint1.modele.Attaques.AttaqueTours;
import fr.iut.montreuil.saesprint1.modele.Attaques.Vegetation;
import fr.iut.montreuil.saesprint1.vue.SpriteAttaqueTours;
import fr.iut.montreuil.saesprint1.vue.SpriteVegetation;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class ListObsVegetation implements ListChangeListener<Vegetation> {
    private Pane pane;

    public ListObsVegetation(Pane pane) {
        this.pane = pane;
    }

    @Override
    public void onChanged(Change<? extends Vegetation> c) {
        while (c.next()) {
            if (c.wasAdded()) {
                Vegetation vegetation = c.getAddedSubList().get(0);
                SpriteVegetation sprite = new SpriteVegetation(vegetation, pane);
            }
            if(c.wasRemoved()) {
                Vegetation aSupprimer = c.getRemoved().get(0);
                this.pane.getChildren().remove(this.pane.lookup("#" + aSupprimer.getId()));

            }
        }
    }

}

