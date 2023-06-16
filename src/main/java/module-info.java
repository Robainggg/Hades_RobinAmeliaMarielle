module com.example.saesprint1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires eu.hansolo.tilesfx;
    requires java.desktop;

    opens fr.iut.montreuil.saesprint1 to javafx.fxml;
    exports fr.iut.montreuil.saesprint1;

    opens fr.iut.montreuil.saesprint1.controller to javafx.fxml;
    exports fr.iut.montreuil.saesprint1.controller;

    opens fr.iut.montreuil.saesprint1.vue to javafx.fxml;
    exports fr.iut.montreuil.saesprint1.vue;
    exports fr.iut.montreuil.saesprint1.controller.fenetres;
    opens fr.iut.montreuil.saesprint1.controller.fenetres to javafx.fxml;
    exports fr.iut.montreuil.saesprint1.modele;
    opens fr.iut.montreuil.saesprint1.modele to javafx.fxml;
    exports fr.iut.montreuil.saesprint1.modele.Ennemis;
    opens fr.iut.montreuil.saesprint1.modele.Ennemis to javafx.fxml;

}