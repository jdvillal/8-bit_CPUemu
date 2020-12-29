/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jorge
 */
public class TextEditorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private TextArea code_TextArea;
    
    @FXML
    private Button save_btn;
    
    @FXML
    private Button insect_btn;
    
    @FXML
    private Button load_btn;
    
    @FXML
    private Button help_btn;
    
    @FXML
    public void drawSavePane(ActionEvent event) {
        Stage st = new Stage();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);   
        File file = fileChooser.showSaveDialog(st);
             
        if(file != null){
            this.saveFile(this.code_TextArea.getText(), file);
        }
    }
    
    private void saveFile(String content, File file){
        try {
            FileWriter fileWriter = null;
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
        }
    }
    
    
    public void inspect(ActionEvent event){
        String text = this.getCode();
        System.out.println(this.format(text));   
    }

    public String format(String st){
        LinkedList<Character> ls = new LinkedList<>();
        for(int i = 0; i < st.length(); i ++){
            ls.add(st.charAt(i));
        }    
        for(int i = 0; i < ls.size(); i ++){
            if(ls.get(i) == ' '){
                try{
                    if(ls.get(i+1) == '\n'){
                        ls.remove(i);
                        i = i - 1;
                    }
                }catch(Exception ex){}
                
            }
        }
        for(int i = 0; i < ls.size(); i ++){
            if(ls.get(i) == '\n'){
                try{
                    if(ls.get(i+1) == ' '){
                        ls.remove(i+1);
                        i = i - 1;
                    }
                }catch(Exception ex){}
            }
        }
        for(int i = 0; i < ls.size(); i ++){
            if(ls.get(i) == '\n'){
                try{
                    if(ls.get(i+1) == '\n'){
                        ls.remove(i);
                        i = i - 1;
                    }
                }catch(Exception ex){}
                
            }
        }
        for(int i = 0; i < ls.size(); i ++){
            if(ls.get(i) == ' '){
                try{
                    if(ls.get(i+1) == ' '){
                        ls.remove(i);
                        i = i - 1;
                    }
                }catch(Exception ex){}
                
            }
        }
        String toReturn = "";
        for(Character c : ls){
            toReturn = toReturn + c;
        }
        return toReturn;
    }
    
    public String getCode(){
        return this.code_TextArea.getText();
    }
    
    public int countLines(String st){
        boolean endText = false;
        int count = 0;
        for(int i = 0; i < st.length(); i ++){
            if(st.charAt(i) == '\n'){
                count = count + 1;
            }
        }
        return count;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
