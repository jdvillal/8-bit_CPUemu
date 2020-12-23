/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafxapplication1.CPU.CPU;

/**
 *
 * @author Jorge
 */
public class LittleEmuApp extends Application {   
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaxHeight(1020);
        stage.setMaxWidth(1700);
        stage.setMinHeight(1020);
        stage.setMinWidth(1700);
        stage.setTitle("LittleEmu - Emulador");
        stage.getIcons().add(new Image(LittleEmuApp.class.getResourceAsStream("microchip.png")));
        stage.setOnCloseRequest(new EventHandler(){
            @Override
            public void handle(Event event) {
                CPU.safeInterrupt();
            }
        });
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {      
        launch(args);
    }
    
}
