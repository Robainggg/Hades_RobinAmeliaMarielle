package fr.iut.montreuil.saesprint1.vue;

import fr.iut.montreuil.saesprint1.modele.Projectile;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SpriteProjectile {
    private Projectile projectile;
    private Pane pane;

    private Circle c;

    public SpriteProjectile(Projectile projectile, Pane pane) {
        this.projectile = projectile;
        this.pane = pane;
        this.c = new Circle(8);

        c.setFill(Color.SILVER);

        c.translateXProperty().bind(projectile.xProperty());
        c.translateYProperty().bind(projectile.yProperty());
        this.pane.getChildren().add(c);

        c.setId(projectile.getId());
        
    }
}
