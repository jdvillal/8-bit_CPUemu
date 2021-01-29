/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

/**
 * FXML Controller class
 *
 * @author Jorge
 */
public class SettingsController implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML
    private ChoiceBox res_choiceBox;
    
    @FXML
    private ChoiceBox clock_choiceBox;
    
    @FXML
    private ChoiceBox demo_choiceBox;
    
    ObservableList<String> resList = FXCollections.observableArrayList("UHD - (3840x2160p)", "QHD - (2560x1440p)", "FHD - (1920x1080p)");
    ObservableList<String> clockList = FXCollections.observableArrayList("0.5x","0.7x","1.0x","1.5x","2.0x","2.5x","3.0x","3.5x","4.0x");
    ObservableList<String> demoList = FXCollections.observableArrayList("Sumar dos números","Restar dos números","Bucle infinito","Dividir dos números","Multiplicar dos números");

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.res_choiceBox.setItems(resList);
        this.clock_choiceBox.setItems(clockList);
        this.demo_choiceBox.setItems(demoList);
    }    
    
}
