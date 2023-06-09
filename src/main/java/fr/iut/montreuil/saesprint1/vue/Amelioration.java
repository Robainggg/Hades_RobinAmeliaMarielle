package fr.iut.montreuil.saesprint1.vue;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

public class Amelioration {

    private SpriteTour spriteTour;

    public Amelioration(SpriteTour sprite){
        spriteTour = sprite;
    }

    public void afficherAmélioration() {
        spriteTour.getT().setOnMouseClicked(event -> {

        });
    }
}
