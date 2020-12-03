/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1.CPU;

import java.util.ArrayList;

/**
 *
 * @author Jorge
 */
public class RAM {
    
    private ArrayList<RamRegister> registers;
    
    public RAM(){
        this.registers = new ArrayList<>();
        for(int i = 0; i < 16; i++){
            this.registers.add(new RamRegister(i));
        }
    }
    
    
    public void swapRamAddressBase(){
        for(int i = 0; i < registers.size(); i ++){
            registers.get(i).swapAddressBase();
        }
    }
    
    public void swapRamContentBase(){
        for(int i = 0; i < registers.size(); i ++){
            registers.get(i).swapContentBase();
        }
    }
    
    public RamRegister getByAddress(int i){
        return this.registers.get(i);
    }
    
    public void setByAddress(int i, String st){
        this.registers.get(i).setValue(st);
    }
    
    public void update(){
        for(int i = 0; i < registers.size() ; i ++){
            registers.get(i).update();
        }
    }
    
    
}
