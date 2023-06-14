package fr.iut.montreuil.saesprint1.controller;

import fr.iut.montreuil.saesprint1.modele.*;
import fr.iut.montreuil.saesprint1.modele.Tours.*;
import fr.iut.montreuil.saesprint1.vue.VueInventaire;
import fr.iut.montreuil.saesprint1.vue.VueTerrain;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
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


    @FXML
    private ImageView imageTourArthemis;

    @FXML
    private ImageView imageTourPoséidon;

    @FXML
    private ImageView imageTourDéméter;

    @FXML
    private ImageView imageTourDionysos;

    @FXML
    private RadioButton boutonArthemis;

    @FXML
    private RadioButton boutonPoséidon;

    @FXML
    private RadioButton boutonDéméter;

    @FXML
    private RadioButton boutonDionysos;

    @FXML
    private ToggleGroup groupeRadio;

    @FXML
    private Button boutonAjouterTour;


    @FXML
    private Button boutonProchaineVague;

    @FXML
    private Label argent;

    @FXML
    private Label pv;

    @FXML
    private VBox vboutique;

    @FXML
    private ImageView boutique_bg;

    @FXML
    private Label nomItem;
    @FXML
    private Label argentItem;

    @FXML
    private ImageView pieces;
    @FXML
    private ImageView pieces2;

    private Environnement evt;

    private VueTerrain vueTerrain;

    private VueInventaire vueInventaire;
    private Timeline gameLoop;

    private ListObsEnnemis listenerEnnemis;

    private ListObsAttaquesTours listenersAttaques;
    private ListObsTours listenersTours;

    private ListObsVegetation listenersVegetation;

    private Partie partie;

    private Joueur joueur;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        joueur = new Joueur();
        //Chargement de l'environnement et du Terrain
        this.evt = new Environnement();
        this.vueTerrain = new VueTerrain(tilePane, evt.getTerrain());

        //Chargement de l'inventaire
        this.vueInventaire = new VueInventaire(imageTourArthemis, imageTourPoséidon, imageTourDéméter, imageTourDionysos, boutonArthemis,  boutonPoséidon, boutonDéméter, boutonDionysos, groupeRadio, boutonAjouterTour, pieces, pieces2, argentItem, nomItem, panePrincipal, tilePane, vboutique, boutique_bg, evt);

        partie = new Partie(evt.getJoueur(),evt);

        //Listeners
        listenerEnnemis = new ListObsEnnemis(panePrincipal);
        this.listenersAttaques = new ListObsAttaquesTours(panePrincipal);
        this.listenersTours = new ListObsTours(panePrincipal,evt);
        this.listenersVegetation = new ListObsVegetation(panePrincipal);
        this.evt.getEnnemis().addListener(listenerEnnemis);
        this.evt.getAttaques().addListener(listenersAttaques);
        this.evt.getTours().addListener(listenersTours);
        this.evt.getVegetation().addListener(listenersVegetation);

        this.pv.textProperty().bind(this.evt.getJoueur().pvProperty().asString());
        this.argent.textProperty().bind(this.evt.getJoueur().argentProperty().asString());

        this.boutonProchaineVague.setOnAction(e -> {
            if(this.partie.getVagueActuelle() == null){
                this.partie.lanceVague();
            }

        });

        initAnimation();
        gameLoop.play();

    }

    private void initAnimation() {
        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.017),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda

                (ev ->{

                    if(this.partie.getVagueActuelle() != null) {
                        this.partie.getVagueActuelle().prochainEnnemi();
                        if(this.partie.getVagueActuelle().isVagueEstFinie()){
                            this.partie.stoppeVagueActuelle();
                        }
                    }

//                    int indiceEnnemi = evt.getEnnemis().size()-1;
//                    int indiceTour = evt.getTours().size()-1;
//                    int indiceAttaque = evt.getAttaques().size()-1;
//
//                    int indice = indiceEnnemi;
//                    int changeIndice = 0;
//                    boolean arreteLaPartie = this.joueur.aPerdu();
//
//                    while(indice >= 0 && !arreteLaPartie){
//                        if(changeIndice == 0) {
//                            evt.getEnnemis().get(indice).agit();
//                            System.out.println("parcours les ennemis");
//                        }
//                        else if(changeIndice == 1){
//                            evt.getTours().get(indice).agit();
//                            System.out.println("parcours les tours");
//                        }
//                        else if(changeIndice == 2){
//                            evt.getAttaques().get(indice).attaque();
//                            System.out.println("parcours les attaques");
//                        }
//                        System.out.println(this.joueur.aPerdu());
//                        arreteLaPartie = this.joueur.aPerdu();
//                        System.out.println(arreteLaPartie);
//                        indice--;
//                        if(indice < 0){
//                            changeIndice++;
//                            if(changeIndice == 1){
//                                indice = indiceTour;
//                            }
//                            else if(changeIndice == 2){
//                                indice = indiceAttaque;
//                            }
//                        }
//                    }
//
//                    if(this.joueur.aPerdu()){
//                        gameLoop.stop();
//                    }
                    

                    for(int i = evt.getEnnemis().size()-1; i >= 0;i--) {
                        evt.getEnnemis().get(i).agit();
                    }

                    for(int i = evt.getTours().size()-1; i >= 0;i--) {
                        evt.getTours().get(i).agit();
                    }

                    for (int i = this.evt.getAttaques().size()-1 ; i >= 0; i--) {
                        this.evt.getAttaques().get(i).attaque();
                    }
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

}