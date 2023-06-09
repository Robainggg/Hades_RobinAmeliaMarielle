package fr.iut.montreuil.saesprint1.vue;

import fr.iut.montreuil.saesprint1.modele.Attaques.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class SpriteAttaqueTours {
    private AttaqueTours attaqueTours;
    private Pane pane;
    final Image fleche = new Image(getClass().getResource("/images/projectiles/arrow.png").toExternalForm());
    final Image biere = new Image(getClass().getResource("/images/projectiles/beer.png").toExternalForm());

    final Image vague = new Image(getClass().getResource("/images/projectiles/wave.png").toExternalForm());

    final Image barrel = new Image(getClass().getResource("/images/projectiles/tonneau.png").toExternalForm());

    private ImageView image;

    public SpriteAttaqueTours(AttaqueTours attaqueTours, Pane pane) {

        this.attaqueTours = attaqueTours;
        this.pane = pane;
        image = new ImageView();

        if(this.attaqueTours instanceof Flèche){
            Flèche flèche = (Flèche) this.attaqueTours;
            image.setImage(fleche);
            image.translateXProperty().bind(flèche.xProperty());
            image.translateYProperty().bind(flèche.yProperty());
        }
        if(this.attaqueTours instanceof Bouteille){
            Bouteille bouteille = (Bouteille)this.attaqueTours;
            image.setImage(biere);
            image.translateXProperty().bind(bouteille.getEnnemi().mainXEnnemi());
            image.translateYProperty().bind((bouteille.getEnnemi().mainYEnnemi()));
        }
        
        if(this.attaqueTours instanceof PetiteVague){
            PetiteVague petiteVague = (PetiteVague)this.attaqueTours;
            image.setImage(vague);
            image.translateXProperty().bind(petiteVague.xProperty());
            image.translateYProperty().bind(petiteVague.yProperty());
        }

        
        if(this.attaqueTours instanceof Tonneau){
            Tonneau tonneau = (Tonneau) this.attaqueTours;
            image.setImage(barrel);
            image.translateXProperty().bind(tonneau.getEnnemi().mainXEnnemi());
            image.translateYProperty().bind(tonneau.getEnnemi().mainYEnnemi());
        }

        this.pane.getChildren().add(image);
        image.setId(this.attaqueTours.getId());


    }
}
