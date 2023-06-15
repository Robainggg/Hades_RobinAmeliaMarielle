package fr.iut.montreuil.saesprint1.vue;

import fr.iut.montreuil.saesprint1.modele.Attaques.Vegetation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class SpriteVegetation {

    private Pane pane;
    private Vegetation vegetation;

    private ImageView image;
    final Image buisson = new Image(getClass().getResource("/images/projectiles/vegetation.png").toExternalForm());
    public SpriteVegetation(Vegetation vegetation,Pane pane){

        this.vegetation = vegetation;
        this.pane= pane;
        image = new ImageView();
        image.setImage(buisson);

        image.translateXProperty().bind(vegetation.coordXprop());
        image.translateYProperty().bind(vegetation.coordYprop());

        this.pane.getChildren().add(image);
        image.setId(this.vegetation.getId());

    }


}
