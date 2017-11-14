/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import logika.IHra;

/**
 *
 * @author xzenj02
 */
public class MenuPole extends MenuBar{
    
    
    private Main main;
    
    public MenuPole(Main main){
        this.main = main;
        init();
    }
    
    private void init(){
        Menu menuSoubor = new Menu("Advenura");
        
        MenuItem itemNovaHra = new MenuItem("Nová hra");
//        MenuItem itemNovaHra = new MenuItem(new Image("Nová hra", new ImageView(Main.class.getResourceAsStream("/zdroje/ikona.png"))));
        itemNovaHra.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        
        MenuItem itemKonec = new MenuItem("Konec");
        
        menuSoubor.getItems().addAll(itemNovaHra, itemKonec);
        menuSoubor.getItems().addAll(itemOProgramu, itemNapoveda);
       
        
        
        Menu menuHelp = new Menu("Help");
        MenuItem itemOProgramu = new MenuItem("O pragramu");
        MenuItem itemNapoveda = new MenuItem("Napoveda");
        
        
        
        
        this.getMenus().addAll(menuSoubor, menuHelp);

        
        itemNapoveda.setOnAction(new EventHandler<ActionEvent>() {
      
        @Override
            public void handle(ActionEvent event) {
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               
               alert.setTitle("O Adventure");
               alert.setHeaderText("Toto je ma adventura");
               alert.setContentText("Graficka verze adventury");
               alert.initOwner(main.getPrimaryStage());
               alert.showAndWate();
            };
             });  
            
        itemNapoveda.setOnAction(new EventHandler<ActionEvent>() {
        
        
           @Override
            public void handle(ActionEvent event) {
                            
            Stage stage = new Stage();
            stage.setTitile("Napoveda");
            
            }
                
        });  
        
        
        
        
        
        itemKonec.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                            
            System.exit(0);
            
            }
        });
        
        itemNovaHra.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                main.novaHra();
                
            }
        });
        
    }
    
}