package fr.iut.montreuil.saesprint1.vue;

import fr.iut.montreuil.saesprint1.modele.Ennemis.DiableJaune;
import fr.iut.montreuil.saesprint1.modele.Ennemis.Ennemi;
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
    private boolean estJaune;

    final static Image diableGauche = new Image(SpriteEnnemi.class.getResource("/images/diable/devilLeft.png").toExternalForm());
    final static Image diableDroit = new Image(SpriteEnnemi.class.getResource("/images/diable/devilRight.png").toExternalForm());
    final static Image diableDos = new Image(SpriteEnnemi.class.getResource("/images/diable/devilBack.png").toExternalForm());
    final static Image diableFace = new Image(SpriteEnnemi.class.getResource("/images/diable/devilFront.png").toExternalForm());
    final static Image diableJauneGauche = new Image(SpriteEnnemi.class.getResource("/images/diable/YellowDevilLeft.png").toExternalForm());
    final static Image diableJauneDroit = new Image(SpriteEnnemi.class.getResource("/images/diable/YellowDevilRight.png").toExternalForm());
    final static Image diableJauneDos = new Image(SpriteEnnemi.class.getResource("/images/diable/YellowDevilBack.png").toExternalForm());
    final static Image diableJauneFace = new Image(SpriteEnnemi.class.getResource("/images/diable/YellowDevilFront.png").toExternalForm());



    public SpriteEnnemi(Ennemi ennemi, Pane pane) {

        this.ennemi = ennemi;
        this.pane = pane;
        this.image = new ImageView();
        image.setId(ennemi.getIdEnnemi());

        if(ennemi instanceof DiableJaune)
            this.estJaune = true;
        else
            this.estJaune = false;



        // Créer la barre de vie
        this.barreDeVie = new ProgressBar();
        barreDeVie.setPrefWidth(32);
        barreDeVie.setStyle("-fx-accent: green");


        this.pane.getChildren().add(barreDeVie);
        if(!this.estJaune) {
            if (this.ennemi.getDirection().equals("d")) {
                image.setImage(diableDroit);
            } else if (this.ennemi.getDirection().equals("g")) {
                image.setImage(diableGauche);
            } else if (this.ennemi.getDirection().equals("h")) {
                image.setImage(diableDos);
            } else {
                image.setImage(diableFace);
            }
        }
        else{
            if (this.ennemi.getDirection().equals("d")) {
                image.setImage(diableJauneDroit);
            } else if (this.ennemi.getDirection().equals("g")) {
                image.setImage(diableJauneGauche);
            } else if (this.ennemi.getDirection().equals("h")) {
                image.setImage(diableJauneDos);
            } else {
                image.setImage(diableJauneFace);
            }
        }


        ChangeListener<String> listenerDirection = (((obs, old, nouv) -> {
            if(!this.estJaune) {
                switch (nouv) {
                    case "d":
                        this.image.setImage(diableDroit);
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
            }
            else{
                switch (nouv) {
                    case "d":
                        this.image.setImage(diableJauneDroit);
                        break;
                    case "g":
                        this.image.setImage(diableJauneGauche);
                        break;
                    case "h":
                        this.image.setImage(diableJauneDos);
                        break;
                    case "b":
                        this.image.setImage(diableJauneFace);
                        break;
                }
            }
        }));

        this.ennemi.directionProperty().addListener(listenerDirection);
        image.translateXProperty().bind(ennemi.coordXProperty());
        image.translateYProperty().bind(ennemi.coordYProperty());
        this.pane.getChildren().add(image);

        //affichage de la barre de vie au dessus du sprite de l'ennemi + bind avec les pv de l'ennemi
        barreDeVie.setId(ennemi.getIdEnnemi());
        barreDeVie.setMaxHeight(15);

        barreDeVie.progressProperty().bind(ennemi.pvProperty().divide(ennemi.getPointsDeVieMax()));
        barreDeVie.maxWidthProperty().bind(pane.widthProperty().multiply(ennemi.getPointsDeVieMax()));
        barreDeVie.translateXProperty().bind(image.translateXProperty());
        barreDeVie.translateYProperty().bind(image.translateYProperty().subtract(10));

        //changement de couleur de la barre de vie : vert entre 1 et 0.75, orange entre 0.75 et 0.5 et rouge en dessous
        barreDeVie.progressProperty().addListener((obs, oldProgress, newProgress) -> {
            if (newProgress.doubleValue() <= 0.75 && newProgress.doubleValue() >= 0.5) {
                barreDeVie.setStyle("-fx-accent: orange;");
            } else if (newProgress.doubleValue() <= 0.5){
                barreDeVie.setStyle("-fx-accent: red");
            }
        });
    }


    //getters pour retirer les ennemis et leur barre de vie, une fois mort
    public ProgressBar getBarreDeVie() {
        return barreDeVie;
    }

    public ImageView getImage() {
        return image;
    }
    public Ennemi getEnnemi() {
        return ennemi;
    }
}
