package fr.iut.montreuil.saesprint1.vue;

import fr.iut.montreuil.saesprint1.modele.Projectiles.Projectile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class SpriteProjectile {
    private Projectile projectile;
    private Pane pane;

    final Image fleche = new Image(getClass().getResource("/images/arrow.png").toExternalForm());

    private ImageView image;

    public SpriteProjectile(Projectile projectile, Pane pane) {
        this.projectile = projectile;
        this.pane = pane;

        image = new ImageView();
        image.setImage(fleche);

        image.translateXProperty().bind(projectile.xProperty());
        image.translateYProperty().bind(projectile.yProperty());
        this.pane.getChildren().add(image);

        image.setId(projectile.getId());
        
    }
}
