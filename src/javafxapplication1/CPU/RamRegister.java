/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1.CPU;

import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import static javafxapplication1.CPU.ControlUnit.getInstructionAsString;
/**
 * @author Jorge
 */
public class RamRegister extends Register{  
    public int address;
    Label addressLabel;
    Label contentLabel;
    Rectangle rectangle;    
    NumberingSystem address_numberingSystem;
    NumberingSystem content_numberingSystem;   
    
    public RamRegister(int adress){
        super();
        this.address = adress;        
        address_numberingSystem = NumberingSystem.BIN;
        content_numberingSystem= NumberingSystem.BIN;
    }
    
    public RamRegister(String value){
        super(value);
    }
    
    public void setRamRegisterGUI(Label addressLbl, Label contentLbl, Rectangle rectangle){
        this.addressLabel = addressLbl;
        this.contentLabel = contentLbl;
        this.rectangle = rectangle;
    }
    
    public NumberingSystem swapAddressBase(){
        if(this.address_numberingSystem == NumberingSystem.BIN){
            this.address_numberingSystem = NumberingSystem.DEC;
        }else if(this.address_numberingSystem == NumberingSystem.DEC){
            this.address_numberingSystem = NumberingSystem.BIN;
        }
        return this.address_numberingSystem;
    }
    
    public NumberingSystem swapContentBase(){
        if(null != this.content_numberingSystem)switch (this.content_numberingSystem) {
            case BIN:
                this.content_numberingSystem = NumberingSystem.OPCbin;
                break;
            case OPCbin:
                this.content_numberingSystem = NumberingSystem.OPCdec;
                break;
            case OPCdec:
                this.content_numberingSystem = NumberingSystem.DEC;
                break;
            case DEC:
                this.content_numberingSystem = NumberingSystem.BIN;
                break;
            default:
                break;
        }
        return this.content_numberingSystem;
    }
    
        //Resalta el registro instanciado (THIS) en la GUI 
    public void setHighlight(Boolean bool){
        this.rectangle.setVisible(!bool);
    }
    
    public void update(){
        if(this.address_numberingSystem == NumberingSystem.BIN){
            String binaryValue = Integer.toBinaryString(this.address);
            if(binaryValue.length()==1){
                binaryValue = "000" + binaryValue;
            }else if(binaryValue.length()==2){
                binaryValue = "00" + binaryValue;
            }else if(binaryValue.length()==3){
                binaryValue = "0" + binaryValue;
            }
            this.addressLabel.setText(binaryValue);
        }else if(this.address_numberingSystem == NumberingSystem.DEC){
            this.addressLabel.setText(Integer.toString(this.address));
        }
        
        if(this.content_numberingSystem == NumberingSystem.BIN){
            this.contentLabel.setText(this.getBinaryValueAsString());
        }else if(this.content_numberingSystem == NumberingSystem.OPCbin){
            this.contentLabel.setText(getInstructionAsString(this.content_numberingSystem, this));
        }else if(this.content_numberingSystem == NumberingSystem.OPCdec){
            this.contentLabel.setText(getInstructionAsString(this.content_numberingSystem, this));
        }else if(this.content_numberingSystem == NumberingSystem.DEC){
            this.contentLabel.setText(this.getIntegerValueAsString());
        }
    }  
}
