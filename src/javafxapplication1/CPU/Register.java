/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1.CPU;

/**
 *
 * @author Jorge
 */
public abstract class Register {
    
    private Byte value;
    
    public Register(){
        this.value = stringToByte("00000000");
    }
    
    public Register(String v){
        this.value = stringToByte(v);
    }
    
    public boolean setValue(String newValue){
        if(newValue.length()==8){
            this.value = stringToByte(newValue);
            return true;
        }else{
            return false;
        }
    }
    
    public void setValue(Byte value){
        this.value = value;
    }
    public void setValue(Integer value){
        this.value = Byte.parseByte(value.toString());
    }
    public void copyValue(Register register){
        this.value = register.getValue();
    }
    
    public Byte getValue(){
        return this.value;
    }
    
    public String getBinaryValueAsString(){
        return byteToString(this.value);
    }
    public String getIntegerValueAsString(){
        return this.value.toString();
    }
    
    public static String byteToString(Byte myByte){
        String myString = String.format("%8s", Integer.toBinaryString(myByte & 0xFF)).replace(' ', '0');
        return myString;
    }
    
    public static Byte stringToByte(String string){
        Byte myByte = (byte)Integer.parseInt(string, 2);
        return  myByte;
    }
}
