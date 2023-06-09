package fr.iut.montreuil.saesprint1.vue;

import fr.iut.montreuil.saesprint1.modele.Ennemi;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class SpriteEnnemi {
    private Ennemi ennemi;
    private ImageView image;
    private Pane pane;
    private ProgressBar barreDeVie;

    final static Image diableGauche = new Image(SpriteEnnemi.class.getResource("/images/diable/devilLeft.png").toExternalForm());
    final static Image diableDroit = new Image(SpriteEnnemi.class.getResource("/images/diable/devilRight.png").toExternalForm());
    final static Image diableDos = new Image(SpriteEnnemi.class.getResource("/images/diable/devilBack.png").toExternalForm());
    final static Image diableFace = new Image(SpriteEnnemi.class.getResource("/images/diable/devilFront.png").toExternalForm());


    public SpriteEnnemi(Ennemi ennemi, Pane pane){
        
        this.ennemi = ennemi;
        this.pane = pane;
        this.image = new ImageView();
        image.setId(ennemi.getIdEnnemi());
        this.barreDeVie = new ProgressBar();
        barreDeVie.setMaxWidth(32);
        barreDeVie.setProgress(1.0);



        if(this.ennemi.getDirection().equals("d")) {
            System.out.println("début chargement");
            image.setImage(diableDroit);
            System.out.println("image chargée");
        }
        else if(this.ennemi.getDirection().equals("g")) {
            System.out.println("début chargement");
            image.setImage(diableGauche);
            System.out.println("image chargée");
        }
        else if(this.ennemi.getDirection().equals("h")) {
            System.out.println("début chargement");
                image.setImage(diableDos);
            System.out.println("image chargée");
            }
        else {
            System.out.println("début chargement");
            image.setImage(diableFace);
            System.out.println("image chargée");
        }
        System.out.println("ennemi " + ennemi.getIdEnnemi() + " est en " + ennemi.getCoordX() + " " + ennemi.getCoordY());



        ChangeListener<String> listenerDirection = (((obs, old, nouv) -> {
            switch(nouv){
                case "d":
                    this.image.setImage(diableDroit);
                    System.out.println("ennemi " + ennemi.getIdEnnemi() + " est en " + ennemi.getCoordX() + " " + ennemi.getCoordY());
                    break;
                case "g":
                    this.image.setImage(diableGauche);
                    break;
                case "h":
                    this.image.setImage(diableDos);
                    break;
                case "b":
                    this.image.setImage(diableFace);
                    break;
            }
        }));

        this.ennemi.directionProperty().addListener(listenerDirection);

        image.translateXProperty().bind(ennemi.coordXProperty());
        image.translateYProperty().bind(ennemi.coordYProperty());
        this.pane.getChildren().add(image);










    }
}
