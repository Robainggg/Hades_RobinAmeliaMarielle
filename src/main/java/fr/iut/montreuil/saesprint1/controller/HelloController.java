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
    private RadioButton boutonArthemis;

    @FXML
    private RadioButton boutonPoséidon;

    @FXML
    private ToggleGroup groupeRadio;

    @FXML
    private Button boutonAjouterTour;



    @FXML
    private Label argent;

    @FXML
    private Label pv;

    @FXML
    private VBox vboutique;

    @FXML
    private ImageView boutique_bg;

    private int temps;

    private Terrain terrain;

    private Environnement evt;

    private VueTerrain vueTerrain;

    private VueInventaire vueInventaire;
    private Timeline gameLoop;

    private ListObsEnnemis listenerEnnemis;

    private Ennemi ennemi;

    private ListObsAttaquesTours listenersAttaques;
    private ListObsTours listenersTours;

    private Tour tourEnCoursAjout ;
    private boolean ajoutTourEnCours = false;
    private String typeTourSelectionne;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Chargement de l'environnement et du Terrain
        this.evt = new Environnement();
        this.vueTerrain = new VueTerrain(tilePane, evt.getTerrain());

        //Chargement de l'inventaire
        this.vueInventaire = new VueInventaire(imageTourArthemis, imageTourPoséidon, boutonArthemis,  boutonPoséidon, groupeRadio, boutonAjouterTour, panePrincipal, tilePane, vboutique, boutique_bg, evt);

        //Listeners
        listenerEnnemis = new ListObsEnnemis(panePrincipal);
        this.listenersAttaques = new ListObsAttaquesTours(panePrincipal);
        this.listenersTours = new ListObsTours(panePrincipal);

        this.evt.getEnnemis().addListener(listenerEnnemis);
        this.evt.getAttaques().addListener(listenersAttaques);
        this.evt.getTours().addListener(listenersTours);

        this.pv.textProperty().bind(this.evt.getJoueur().pvProperty().asString());
        this.argent.textProperty().bind(this.evt.getJoueur().argentProperty().asString());

        //Test pour affichage de base
        Artémis artemis = new Artémis(8*32,12*32,evt);
        Artémis tour = new Artémis(6*32,10*32,evt);
        Dionysos dyo = new Dionysos(10*32,10*32,evt);
        Poséidon poseidon = new Poséidon(9*32,7*32,evt);
        Tour demeter = new Déméter(15*32,10*32,evt);
        //ennemi = new Ennemi(evt);
        //evt.ajouterEnnemi(ennemi);
        //this.evt.ajouterTour(tour);
        this.evt.ajouterTour(artemis);
        this.evt.ajouterTour(dyo);
        this.evt.ajouterTour(poseidon);
        this.evt.ajouterTour(demeter);

        artemis.améliorer();
        poseidon.améliorer();
        
        initAnimation();
        gameLoop.play();

    }

    private void initAnimation() {
        gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.017),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev ->{
                    if(temps%60 == 0)
                        this.evt.ajouterEnnemi(new Ennemi(evt));
                    if(this.evt.getJoueur().getPv() <=0)
                        gameLoop.stop();
                    this.evt.getVagueActuelle().prochainEnnemi();
                    this.evt.nouvelleVague();
                    for(int i = 0; i < evt.getEnnemis().size();i++) {
//                        if (evt.getEnnemis().get(i).estArriveAuBout()) {
//                            System.out.println("fini");
//                            gameLoop.stop();
//                        }
//                        else{
                        evt.getEnnemis().get(i).agit();
                        //}
                    }

                    for (Tour tour: this.evt.getTours()) {
                        tour.attaque();
                    }

                    for (int i = this.evt.getAttaques().size()-1; i >= 0 ; i--) {
                       this.evt.getAttaques().get(i).agit();
                    }
                    
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

}