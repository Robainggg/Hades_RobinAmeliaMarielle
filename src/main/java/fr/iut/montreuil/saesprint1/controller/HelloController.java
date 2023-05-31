package fr.iut.montreuil.saesprint1.controller;

import fr.iut.montreuil.saesprint1.modele.*;
import fr.iut.montreuil.saesprint1.vue.VueTerrain;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

// Créer une BufferedImage de 100 pixels de


import java.net.URL;
import java.util.ResourceBundle;


public class HelloController implements Initializable {


    @FXML
    private Pane panePrincipal;

    @FXML
    private TilePane tilePane;

    @FXML
    private Circle testCercleEnnemi;

    private int temps;

    private Terrain terrain;

    private VueTerrain vueTerrain;

    private Timeline gameLoop;

    private Environnement environnement;

    private Ennemi ennemi;

    private ListObsProjectile listenerspProjectiles;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.terrain=new Terrain();
        this.vueTerrain = new VueTerrain(tilePane, terrain);
        listenerspProjectiles = new ListObsProjectile(panePrincipal);
        ennemi = new Ennemi(terrain);
        testCercleEnnemi.translateXProperty().bind(ennemi.coordXProperty());
        testCercleEnnemi.translateYProperty().bind(ennemi.coordYProperty());
        
        this.environnement = new Environnement(this.terrain);
        Tour tour = new Artémis(12,10,environnement);
        Tour tour1 = new Artémis(17,8,environnement);

        environnement.ajouterTour(tour);
        creerUneTour(tour);
        environnement.ajouterTour(tour1);
        creerUneTour(tour1);
        environnement.ajouterEnnemi(ennemi);
        this.environnement.getProjectiles().addListener(listenerspProjectiles);
        
        
        initAnimation();
        gameLoop.play();

        this.terrain.afficheTableau();

    }

    private void initAnimation() {
        gameLoop = new Timeline();
        temps = 0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.017),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev ->{
                    if(ennemi.estArriveAuBout()){
                        System.out.println("fini");
                        gameLoop.stop();
                    }
                    else if (temps%4 == 0){
                        if(ennemi.estArrivé()) {
                            if(ennemi.getBfs().getParcours().size()==0)
                                gameLoop.stop();
                            ennemi.changeProchaineCase();
                            System.out.println(ennemi.getProchaineCase());
                            if(ennemi.getProchaineCase() != null)
                                ennemi.definirDirection();
                            System.out.println(ennemi.getDirection());
                        }
                        if(ennemi.getDirection() == 'd'){
                            ennemi.setCoordX(ennemi.getCoordX()+2);
                        }
                        else if (ennemi.getDirection() == 'g'){
                            ennemi.setCoordX(ennemi.getCoordX()-2);
                        }
                        else if (ennemi.getDirection() == 'h'){
                            ennemi.setCoordY(ennemi.getCoordY()-2);
                        }
                        else if (ennemi.getDirection() == 'b'){
                            ennemi.setCoordY(ennemi.getCoordY()+2);
                        }
                        //System.out.println(ennemiTesté.estArrivé() + " ennemi a pour coordonnées: " + ennemiTesté.getCoordX() + " , " + ennemiTesté.getCoordY() + " et pour destination " + (ennemiTesté.getProchaineCase().getI()*32-16) + " ," + (ennemiTesté.getProchaineCase().getJ()*32-16));

                    }

                    for (Tour tour: this.environnement.getTours()) {
                        if(tour instanceof Artémis){
                            if(temps%(((Artémis) tour).getNbAttaques()*Tour.tailleCase) == 0){
                               tour.attaque();
                            }
                        }
                        //tour.attaque();
                    }

                    for (Projectile projectile : this.environnement.getProjectiles()) {
                        //A sa création, un projectile se situe dans sa tour
                        //Pour le moment avance à chaque tour de jeu
                        if(projectile.getY() == projectile.getTour().centreTourY().get() && projectile.getX() == projectile.getTour().centreTourX().get()){
                           creerProjectile(projectile);
                        }
                        projectile.avance();
                    }

//                    for(Projectile projectile : this.environnement.getàSupprimer()){
//                        for(Node node : panePrincipal.getChildren() ){
//                            if (node.getId() != null && node.getId().equals(projectile.getId())) {
//                                panePrincipal.getChildren().remove(node);
//                            }
//                        }
//                    }
                    
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    @FXML
    private void poserTour (ActionEvent event){

        //Récupérer le type de la tour en fonction du ToggleButton ou autre
        //Récupérer l'emplacement de la tour
        //Créer la tour et la mettre dans l'environnement
        //creerUneTour();

    }

    void creerUneTour(Tour tour){

        Rectangle t = new Rectangle(32,32);

        if(tour instanceof TourAvecPortée){

            Circle c = new Circle(((TourAvecPortée) tour).getPortée()*32);
            c.setOpacity(0.1);
            c.setFill(Color.PINK);
            c.translateXProperty().bind(tour.centreTourX());
            c.translateYProperty().bind(tour.centreTourY());
            panePrincipal.getChildren().add(c);
            c.setId("rangeOf"+tour.getId());

            t.setFill(Color.PINK);
            t.translateXProperty().bind(tour.getXProperty());
            t.translateYProperty().bind(tour.getYProperty());
            panePrincipal.getChildren().add(t);
            t.setId(tour.getId());

        }

        else{
            t.setFill(Color.ORANGE);
            t.translateXProperty().bind(tour.getXProperty());
            t.translateYProperty().bind(tour.getYProperty());
            panePrincipal.getChildren().add(t);
            t.setId(tour.getId());
        }

    }

    void creerProjectile(Projectile projectile){
        Circle c = new Circle(8);
        c.setFill(Color.SILVER);
        c.translateXProperty().bind(projectile.xProperty());
        c.translateYProperty().bind(projectile.yProperty());
        panePrincipal.getChildren().add(c);
        c.setId(projectile.getId());
    }




}