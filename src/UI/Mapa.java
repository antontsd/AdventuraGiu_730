/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Utils.Obsever;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import logika.IHra;

/**
 *
 * @author koza02
 */
public class Mapa extends AnchorPane implements Obsever {
    
    
    
    private Circle tecka;
    public IHra hra;
    
  

    public Mapa(IHra hra) {
    this.hra = hra;
    hra.getHerniPlan().registerObserver(this);
    init();
    }
}

private void init() {
ImageView obrazek = new ImageView (new Image (Main.class.getResourceAsStream("/zdroje/mapa.jpg"),400,400,false,false));
Circle tecka = new Circle(10, Paint.valueOf("red"));
this.getChildren().addAll(obrazek, tecka);
update();

}

@Override
public void update() {

this.setTopAnchor(tecka, hra.getHerniPlan().getAktualniProstor().getPosY());
this.setLeftAnchor(tecka, hra.getHerniPlan().getAktualniProstor().getPosX());

}
