package fr.iut.montreuil.saesprint1.controller;

import fr.iut.montreuil.saesprint1.PanneauDéfaite;
import fr.iut.montreuil.saesprint1.PanneauVictoire;
import fr.iut.montreuil.saesprint1.modele.*;
import fr.iut.montreuil.saesprint1.modele.Tours.*;
import fr.iut.montreuil.saesprint1.vue.VueDéfaite;
import fr.iut.montreuil.saesprint1.vue.VueInventaire;
import fr.iut.montreuil.saesprint1.vue.VueTerrain;
import fr.iut.montreuil.saesprint1.vue.VueVictoire;
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
import javafx.stage.Stage;
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
    private ImageView boutonProchaineVague;

    @FXML
    private ImageView boutonPause;

    @FXML
    private ImageView boutonDepause;

    @FXML
    private ImageView boutonRelancerPartie;

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

    @FXML
    private Label nombreVagues;

    @FXML
    private Label afficheAmélioration;

    private Environnement evt;

    private VueTerrain vueTerrain;

    private VueInventaire vueInventaire;
    private Timeline gameLoop;

    private ListObsEnnemis listenerEnnemis;

    private ListObsAttaquesTours listenersAttaques;
    private ListObsTours listenersTours;

    private ListObsVegetation listenersVegetation;

    private Partie partie;

    @FXML
    private ProgressBar barreDeVie;

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        //Chargement Environnement/Terrain/Inventaire/Partie
        this.evt = new Environnement();
        this.vueTerrain = new VueTerrain(tilePane, evt.getTerrain());
        this.vueInventaire = new VueInventaire(imageTourArthemis, imageTourPoséidon, imageTourDéméter, imageTourDionysos, boutonArthemis, boutonPoséidon, boutonDéméter, boutonDionysos, groupeRadio, boutonAjouterTour, pieces, pieces2, argentItem, nomItem, panePrincipal, tilePane, vboutique, boutique_bg, evt);
        partie = new Partie(this.evt.getJoueur(), evt);


        //Listeners
        listenerEnnemis = new ListObsEnnemis(panePrincipal);
        this.listenersAttaques = new ListObsAttaquesTours(panePrincipal);
        this.listenersTours = new ListObsTours(panePrincipal, evt);
        this.listenersVegetation = new ListObsVegetation(panePrincipal);
        this.evt.getEnnemis().addListener(listenerEnnemis);
        this.evt.getAttaques().addListener(listenersAttaques);
        this.evt.getTours().addListener(listenersTours);
        this.evt.getVegetation().addListener(listenersVegetation);

        //Initialisation Joueur
        this.pv.textProperty().bind(this.evt.getJoueur().pvProperty().asString());
        this.argent.textProperty().bind(this.evt.getJoueur().argentProperty().asString());
        this.nombreVagues.textProperty().bind(this.partie.niveauProperty().asString());
        this.afficheAmélioration.textProperty().bind(this.evt.argentSuffisantProperty());

        this.boutonProchaineVague.setOnMouseClicked(e -> {
            if (this.partie.getVagueActuelle() == null && this.partie.getNiveau() != 6) {
                this.partie.lanceVague();
                System.out.println("Nouvelle vague de niveau : " + this.partie.getNiveau());
            }

        });

        this.boutonPause.setOnMouseClicked(e -> this.gameLoop.pause());

        this.boutonDepause.setOnMouseClicked(e -> this.gameLoop.play());

        this.boutonRelancerPartie.setOnMouseClicked(e -> {
            gameLoop.stop();
            this.partie.resetPartie();
            this.evt.nettoieEnvironnement();
            this.evt.getJoueur().resetJoueur();
            gameLoop.play();
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

                (ev -> {

                    if (this.partie.getVagueActuelle() != null) {
                        this.partie.getVagueActuelle().prochainEnnemi();
                        if (this.partie.getVagueActuelle().isVagueEstFinie()) {
                            this.partie.stoppeVagueActuelle();
                        }
                    } else {
                        if (this.partie.getNiveau() == 3 && this.evt.getEnnemis().isEmpty()) {
                            gameLoop.stop();
                            PanneauVictoire panneauVictoire = new PanneauVictoire();
                            try {
                                panneauVictoire.start(new Stage());
                                this.primaryStage.close();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }

                    this.action();

                    if (this.evt.getJoueur().aPerdu()) {
                        gameLoop.stop();
                        PanneauDéfaite panneauDéfaite = new PanneauDéfaite();
                        try {
                            panneauDéfaite.start(new Stage());
                            this.primaryStage.close();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }

                    }

                    for (int i = this.evt.getAttaques().size() - 1; i >= 0; i--) {
                        this.evt.getAttaques().get(i).attaque();
                    }

                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    public void action() {

        int indiceEnnemi = evt.getEnnemis().size() - 1;
        int indiceTour = evt.getTours().size() - 1;
        int indice = indiceEnnemi;
        int changeIndice = 0;

        while (indice >= 0 && !this.evt.getJoueur().aPerdu()) {
            if (changeIndice == 0) {
                evt.getEnnemis().get(indice).agit();
            } else if (changeIndice == 1) {
                evt.getTours().get(indice).agit();
            }
            indice--;
            if (indice < 0) {
                changeIndice++;
                if (changeIndice == 1) {
                    indice = indiceTour;
                }
            }

        }

    }
}