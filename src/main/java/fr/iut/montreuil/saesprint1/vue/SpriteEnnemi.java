package fr.iut.montreuil.saesprint1.vue;

import fr.iut.montreuil.saesprint1.modele.Ennemi;
import javafx.beans.value.ChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class SpriteEnnemi {
    private Ennemi ennemi;
    private ImageView image;
    private Pane pane;

    public SpriteEnnemi(Ennemi ennemi, Pane pane){
        this.ennemi = ennemi;
        this.pane = pane;
        this.image = new ImageView();
        if(this.ennemi.getDirection().equals("d")) {
            System.out.println("début chargement");
            image.setImage(new Image(getClass().getResource("/images/diable/devilRight.png").toExternalForm()));
            System.out.println("image chargée");
        }
        else if(this.ennemi.getDirection().equals("g")) {
            System.out.println("début chargement");
            image.setImage(new Image(getClass().getResource("/images/diable/devilLeft.png").toExternalForm()));
            System.out.println("image chargée");
        }
        else if(this.ennemi.getDirection().equals("h")) {
            System.out.println("début chargement");
                image.setImage(new Image(getClass().getResource("/images/diable/devilBack.png").toExternalForm()));
            System.out.println("image chargée");
            }
        else {
            System.out.println("début chargement");
            image.setImage(new Image(getClass().getResource("/images/diable/devilFront.png").toExternalForm()));
            System.out.println("image chargée");
        }

        image.translateXProperty().bind(ennemi.coordXProperty());
        image.translateYProperty().bind(ennemi.coordYProperty());
        this.pane.getChildren().add(image);

        ChangeListener<String> listenerDirection = (((obs, old, nouv) -> {
            switch(nouv){
                case "d":
                    this.image.setImage(new Image(getClass().getResource("/images/diable/devilRight.png").toExternalForm()));
                    break;
                case "g":
                    this.image.setImage(new Image(getClass().getResource("/images/diable/devilLeft.png").toExternalForm()));
                    break;
                case "h":
                    this.image.setImage(new Image(getClass().getResource("/images/diable/devilBack.png").toExternalForm()));
                    break;
                case "b":
                    this.image.setImage(new Image(getClass().getResource("/images/diable/devilFront.png").toExternalForm()));
                    break;
            }
        }));

        this.ennemi.directionProperty().addListener(listenerDirection);


    }
}
