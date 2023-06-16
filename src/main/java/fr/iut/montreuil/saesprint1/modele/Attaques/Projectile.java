package fr.iut.montreuil.saesprint1.modele.Attaques;

import fr.iut.montreuil.saesprint1.modele.Ennemis.Ennemi;
import fr.iut.montreuil.saesprint1.modele.Tours.Artémis;
import fr.iut.montreuil.saesprint1.modele.Tours.TourAvecPortée;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import static java.lang.Math.round;

public class Projectile extends AttaqueTours {
    private DoubleProperty x;
    private DoubleProperty y;
    private int vitesse;

    private int degats;
    private double deltaX;
    private double deltaY;
    private double magnitude;
    private double normalizeDeltaX;
    private double normalizeDeltaY;

    public Projectile(TourAvecPortée tour, int coordXArrivé, int coordYArrivé, int vitesse, int degats) {

        super(tour, coordXArrivé, coordYArrivé);
        this.vitesse = vitesse;
        this.x = new SimpleDoubleProperty((double) tour.centreTourX().getValue());
        this.y = new SimpleDoubleProperty((double) tour.centreTourY().getValue());
        this.degats = degats;

        //Calcul du vecteur de déplacement
        this.deltaX = super.getCoordXArrivé() - this.getX();
        this.deltaY = super.getCoordYArrivé() - this.getY();
        this.magnitude = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        this.normalizeDeltaX = deltaX / magnitude;
        this.normalizeDeltaY = deltaY / magnitude;
    }

    //Raffraichit les X et Y avec leurs nouveaux emplacements
    public void avance() {
        this.setX((this.getX() + normalizeDeltaX * vitesse));
        this.setY((this.getY() + normalizeDeltaY * vitesse));
    }

    //Deplace le projectile et supprime leur position si en dehors de la map ou portée
    //Fait perdre des pv si le projectile le touche
    //S'il s'agit d'une flèche et qu'elle touche : la fait disparaitre
    //Si l'ennemi ciblé par Artémis(Flèche) est mort : la tour Artémis n'a plus de cible
    @Override
    public void attaque() {

        this.avance();

        TourAvecPortée tourAvecPortée = (TourAvecPortée) super.getTour();

        //S'il est sorti de la portée ou en dehors de la map
        if (!tourAvecPortée.estDansLaZone(this.getX(), this.getY())
                || this.getX() >= 960 - 32 || this.getX() <= 32 || this.getY() >= 640 - 32 || this.getY() <= 32) {
            tourAvecPortée.getEnv().supprimerAttaqueTours(this);
        }

        for (int i = tourAvecPortée.getEnv().getEnnemis().size() - 1; i >= 0; i--) {
            Ennemi ennemi = tourAvecPortée.getEnv().getEnnemis().get(i);
            if (tourAvecPortée.ennemiZone(ennemi) != null) {
                if (ennemi.getCoordX() <= this.getX() + 16 && ennemi.getCoordX() + 32 >= this.getX() + 16 &&
                        ennemi.getCoordY() <= this.getY() + 16 && ennemi.getCoordY() + 32 >= this.getY() + 16) {
                    ennemi.pertPv(this.degats);

                    if (this instanceof Flèche) {
                        this.getTour().getEnv().supprimerAttaqueTours(this);
                        if (ennemi.isEstMort()) {
                            Artémis tourArtémis = (Artémis) this.getTour();
                            tourArtémis.setEnnemiAttaqué();
                        }
                    }
                } else if (this instanceof Flèche) {
                    Artémis tourArtémis = (Artémis) this.getTour();
                    tourArtémis.setEnnemiAttaqué();
                }
            }
        }

    }


    //Getters & Setters
    public final double getX() {
        return x.get();
    }
    public final DoubleProperty xProperty() {
        return x;
    }
    public final double getY() {
        return y.get();
    }
    public final DoubleProperty yProperty() {
        return y;
    }
    public void setX(double x) {
        this.x.set(x);
    }
    public void setY(double y) {
        this.y.set(y);
    }
}
