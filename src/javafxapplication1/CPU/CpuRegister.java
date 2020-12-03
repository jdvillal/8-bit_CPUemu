/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1.CPU;

import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import static javafxapplication1.CPU.ControlUnit.getOpcode;

/**
 *
 * @author Jorge
 */
public class CpuRegister extends Register{
    private Rectangle rectangle;
    private Label label;
    
    
    public CpuRegister(){
        super();
    }
    
    //Setea los componentes que representan al registro en la GUI
    public void setGUI(Label label, Rectangle rectangle){
        this.label = label;
        this.rectangle = rectangle;
    }
    
    //Resalta el registro instanciado (THIS) en la GUI 
    public void setHighlight(Boolean bool){
        if(bool){
            rectangle.setVisible(false);
        }else{
            rectangle.setVisible(true);
        }
    }
    
    //Intercambia el formato del contenido del registro en la GUI
    public void swapBase(Boolean isBinary){
        if(isBinary){
            this.label.setText(this.getValue().toString());
        }else{
            this.label.setText(this.getBinaryValueAsString());
        }
    }

    public void showInText(){
        Instruction inst = getOpcode(this);
        System.out.println(inst);
    }
    
    public void personalizeLabel(String value){
        this.label.setText(value);
    }
    public void resetLabel(){
    
    }
    
    
    
    
    
}
