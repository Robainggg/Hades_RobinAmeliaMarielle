package fr.iut.montreuil.saesprint1.vue;

import fr.iut.montreuil.saesprint1.modele.Attaques.Bouteille;
import fr.iut.montreuil.saesprint1.modele.Attaques.Flèche;
import fr.iut.montreuil.saesprint1.modele.Attaques.Projectile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class SpriteProjectile {
    private Projectile projectile;
    private Pane pane;

    final Image fleche = new Image(getClass().getResource("/images/projectiles/arrow.png").toExternalForm());
    final Image biere = new Image(getClass().getResource("/images/projectiles/beer.png").toExternalForm());

    private ImageView image;

    public SpriteProjectile(Projectile projectile, Pane pane) {
        this.projectile = projectile;
        this.pane = pane;

        image = new ImageView();

        if(this.projectile instanceof Flèche){

            image.setImage(fleche);
            image.translateXProperty().bind(projectile.xProperty());
            image.translateYProperty().bind(projectile.yProperty());
            this.pane.getChildren().add(image);

            image.setId(projectile.getId());
        }
        if(this.projectile instanceof Bouteille){
            this.projectile = (Bouteille)this.projectile;
            image.setImage(biere);
            image.translateXProperty().bind(((Bouteille) this.projectile).getEnnemi().mainXEnnemi());
            image.translateYProperty().bind(((Bouteille) this.projectile).getEnnemi().mainYEnnemi());
            this.pane.getChildren().add(image);

            image.setId(projectile.getId());
        }


        
    }
}
