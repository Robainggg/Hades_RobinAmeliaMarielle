package fr.iut.montreuil.saesprint1.vue;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VueInventaire {
    private ImageView imageTourArthemis;

    public VueInventaire(ImageView imageTourArthemis) {
        this.imageTourArthemis = imageTourArthemis;
        chargerImage();
    }

    public void chargerImage() {
        Image image = new Image(getClass().getResourceAsStream("/images/Tower-PNG-Image.png"));
        imageTourArthemis.setImage(image);
    }
}

