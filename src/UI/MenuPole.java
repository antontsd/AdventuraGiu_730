/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;

/**
 *
 * @author koza02
 */
public class MenuPole extends MenuBar{
    
public MenuPole(){

init();
}   

private void init(){
Menu menuSoubor = new Menu("Adventura");

MenuItem itemNovaHra = new MenuItem ("Nová hra");
//MenuItem itemNovaHra = new MenuItem (mew Image "Nová hra", new ImageView(Main.class.getResourcesAsStream("/zdroje/ikona.png")));

itemNovaHra.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));

MenuItem itemKonec = MenuItem("Konec");

menuSoubor.getItems().addAll(itemNovaHra, itemKonec);

this.getMenus().addAll(menuSoubor);


itemKonec.setOnAction(new EventHandler<ActionEvent>) {
    
    
    
}}

}


   


