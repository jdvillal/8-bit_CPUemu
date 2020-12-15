/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1.CPU;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import static javafxapplication1.CPU.ControlUnit.getInstructionAsString;
import static javafxapplication1.CPU.ControlUnit.getOpcode;

/**
 *
 * @author Jorge
 */
public class CpuRegister extends Register{
    private Rectangle rectangle;
    private Label contentLabel;
    private NumberingSystem content_numberingSystem;
    
    
    public CpuRegister(){
        super();
    }
    
    //Setea los componentes que representan al registro en la GUI
    public void setGUI(Label label, Rectangle rectangle){
        this.contentLabel = label;
        this.rectangle = rectangle;
        this.content_numberingSystem = NumberingSystem.BIN;
    }
    
    //Resalta el registro instanciado (THIS) en la GUI 

    public void setHighlight(Boolean bool){
        this.rectangle.setVisible(!bool);
    }
    
    public NumberingSystem swapBase(){
        if(this.content_numberingSystem == NumberingSystem.BIN){
            this.content_numberingSystem = NumberingSystem.DEC;
        }else if(this.content_numberingSystem == NumberingSystem.DEC){
            this.content_numberingSystem = NumberingSystem.BIN;
        }
        return this.content_numberingSystem;
    }
    
    public NumberingSystem swapInstBase(){
        if(null != this.content_numberingSystem)switch (this.content_numberingSystem) {
            case BIN:
                this.content_numberingSystem = NumberingSystem.OPCbin;
                break;
            case OPCbin:
                this.content_numberingSystem = NumberingSystem.OPCdec;
                break;
            case OPCdec:
                this.content_numberingSystem = NumberingSystem.BIN;
                break;
            default:
                break;
        }
        return this.content_numberingSystem;
    }
    
    //Intercambia el formato del contenido del registro en la GUI
    public void updateGUI(){
        if(null !=  this.content_numberingSystem)switch (this.content_numberingSystem) {
            case BIN:
                this.contentLabel.setText(this.getBinaryValueAsString());
                break;
            case OPCbin:
                this.contentLabel.setText(getInstructionAsString(this.content_numberingSystem, this));
                break;
            case OPCdec:
                this.contentLabel.setText(getInstructionAsString(this.content_numberingSystem, this));
                break;
            case DEC:
                this.contentLabel.setText(this.getIntegerValueAsString());
                break;
            default:
                break;
        }
    }
    
    public void update(){
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                updateGUI();
            }
        });
    }

    public void showInText(){
        Instruction inst = getOpcode(this);
        System.out.println(inst);
    }
    
    public void personalizeLabel(String value){
        this.contentLabel.setText(value);
    }
    
    
    
    
    
    
}
