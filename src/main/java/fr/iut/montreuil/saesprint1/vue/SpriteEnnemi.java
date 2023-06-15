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


        // Créer la barre de vie
        this.barreDeVie = new ProgressBar();
        barreDeVie.setPrefWidth(32);
        barreDeVie.setStyle("-fx-accent: green;");



        this.pane.getChildren().add(barreDeVie);

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



        barreDeVie.setId(ennemi.getIdEnnemi());
        barreDeVie.progressProperty().bind(ennemi.pvProperty().divide(ennemi.getPointsDeVieMax()));
        barreDeVie.maxWidthProperty().bind(pane.widthProperty().multiply(ennemi.getPointsDeVieMax()));
        barreDeVie.translateXProperty().bind(image.translateXProperty());
        barreDeVie.translateYProperty().bind(image.translateYProperty().subtract(10));


        // Ajouter une écoute sur la propriété de progression de la barre de vie
        barreDeVie.progressProperty().addListener((obs, oldProgress, newProgress) -> {
            // Vérifier si la barre atteint la moitié
            if (newProgress.doubleValue() >= 0.75 && newProgress.doubleValue()<=1) {
                barreDeVie.setStyle("-fx-accent: orange;");
            }
            else if (newProgress.doubleValue() <= 0.5) {
                barreDeVie.setStyle("-fx-accent: red;");
            }
        });







    }

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
