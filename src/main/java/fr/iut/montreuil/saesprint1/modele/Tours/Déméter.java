package fr.iut.montreuil.saesprint1.modele.Tours;

import fr.iut.montreuil.saesprint1.modele.Attaques.Vegetation;
import fr.iut.montreuil.saesprint1.modele.Ennemi;
import fr.iut.montreuil.saesprint1.modele.Environnement;

public class Déméter extends TourAvecPortée {

    //Ici pour faciliter leur changement
    private final static int cout = 20;
    private final static int espaceEntreAttaques = 0;
    private final static int portée = 4 ;
    private static int ralentissement = 1;
    public Déméter(int x, int y, Environnement env) {
        super("Déméter", cout, x, y, env, portée, espaceEntreAttaques);
        creerVégétation();
    }
    @Override
    public void attaque(){

            //Créer de la végétation puis lui faire ralentir les ennemis tant qu'ils sont dans la portée de la tour
            for (Ennemi e: this.getEnv().getEnnemis()) {
                if(ennemiZone(e) != null && !e.estRalenti()){
                   e.seFaitRalentir(ralentissement);
                   System.out.println("ralentit un ennemi");
                }
                else if(ennemiZone(e) == null && e.estRalenti()) {
                    e.nestPlusRalenti(ralentissement);
                    System.out.println("ne ralentit plus l'ennemi");
                }
            }
    }

    public void creerVégétation(){

        int portée = this.getPortée();
        int maxX = this.getX()+portée;
        int minX = this.getX()-portée;
        int maxY = this.getY()+portée;
        int minY = this.getY()-portée;

        for(int x = maxX; x > minX; x-=32 ){
            for(int y = maxY; y > minY; y-=32){
                System.out.println("Creation végétaux");
                Vegetation vegetation = new Vegetation(this,x,y);
                this.getEnv().ajouterAttaqueTours(vegetation);
            }
        }
    }

}
