package fr.iut.montreuil.saesprint1.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class VueDéfaite {

    private StackPane pane;
    private ImageView imageView;

    public VueDéfaite(ImageView imv, StackPane pane) {
        this.imageView=imv;
        this.pane = pane;
        charger();
    }

    public void charger(){
        Image image = new Image(getClass().getResourceAsStream("/images/défaite.png"));
        imageView.setFitWidth(1160);
        imageView.setFitHeight(740);
        imageView.setImage(image);

    }
}
