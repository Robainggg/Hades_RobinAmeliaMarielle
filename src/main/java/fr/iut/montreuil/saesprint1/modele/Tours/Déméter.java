package fr.iut.montreuil.saesprint1.modele.Tours;

import fr.iut.montreuil.saesprint1.modele.Attaques.Vegetation;
import fr.iut.montreuil.saesprint1.modele.Ennemi;
import fr.iut.montreuil.saesprint1.modele.Environnement;

public class Déméter extends TourAvecPortée {

    //Ici pour faciliter leur changement
    private int ralentissement;
    public Déméter(int x, int y, Environnement env) {
        super("Déméter", 20, x, y, env, 3, 0);
        this.ralentissement = 1;
        creerVégétation();
    }
    @Override
    public void attaque(){

            //fait ralentir les ennemis tant qu'ils sont dans la portée de la tour
            for (Ennemi e: this.getEnv().getEnnemis()) {
                if(ennemiZone(e) != null && !e.estRalenti()){
                   e.seFaitRalentir(ralentissement);
                   System.out.println("ralentit un ennemi");

                }
                else if(ennemiZone(e) == null && e.estRalenti()) {
                    e.nestPlusRalenti(ralentissement);
                    System.out.println("ne ralentit plus l'ennemi");
                }

                //Les blesse si la tour est améliorée
                if(super.isAmélioré()&&e.estRalenti()){
                    e.pertPv(1);
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

    public void améliorer(){
        super.améliorer(25, 0);
    }

}
