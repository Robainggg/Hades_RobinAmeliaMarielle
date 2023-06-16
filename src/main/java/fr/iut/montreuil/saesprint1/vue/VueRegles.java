package fr.iut.montreuil.saesprint1.vue;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class VueRegles {

    private StackPane borderPane;

    private Pane pane;
    private ImageView imageView;

    private Label label;

    private Button button;

    public VueRegles(StackPane bp, Pane p, ImageView iv, Label l, Button b){
        this.borderPane = bp;
        this.pane =p;
        this.imageView = iv;
        this.label=l;
        this.button = b;
        charger();
    }

    public void charger(){
        Image image = new Image(getClass().getResourceAsStream("/images/regles.png"));
        imageView.setFitWidth(1160);
        imageView.setFitHeight(740);
        imageView.setImage(image);
        String lesRegles = "Tu es Hades, grand roi qui règne en maître dans les enfers,\n"
                +"Cependant les âmes qui s'y trouvent se rebellent et veulent renverser ton royaume...\n"+
                "Une armée se prépare... \n"+"Ainsi pour maintenir l'ordre tu a fait appel aux dieux de l'Olympe \n"+
                "Tu a su obtenir :\n les fleches d'Arthémis, \n les vagues de Poséidon,\n les moisson de Déméter,\n et l'alcool de Dionysos. \n "+
                "A toi de sauver ton royaume \n\n "+" Comment jouer ? \n"+
                " Poser une Tour : Sélectionnez le bouton de la tour souhaitée puis cliquez sur “Ajouter Tour” \n puis cliquez sur le terrain à l’endroit où vous souhaitez la positionner\n"+
                "Caractéristiques d’un Type de Tour : Clic sur la tour dans l’inventaire pour les afficher\n" +
                "Améliorer/Supprimer une Tour : Clic sur la Tour sur le terrain \npuis cliquer sur AMELIORER ou SUPPRIMER!\n  ";
        label.setText(lesRegles);
        
    }
}
