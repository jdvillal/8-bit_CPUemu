/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1.CPU;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge
 */
public class CPU {

    private ALU alu;
    private ControlUnit controlUnit;
    private RAM ram;
    private GUI_BusAnimator animator;

    private CpuRegister registerA;
    private CpuRegister registerB;
    private CpuRegister registerC;
    private CpuRegister registerD;

    public CPU() {
        this.controlUnit = new ControlUnit();
        this.alu = new ALU(controlUnit);
        this.ram = new RAM();

        this.registerA = new CpuRegister();
        this.registerB = new CpuRegister();
        this.registerC = new CpuRegister();
        this.registerD = new CpuRegister();

        this.controlUnit.setALU(this.alu);
        this.controlUnit.setRegisters(this.registerA, this.registerB, this.registerC, this.registerD);
    }

    public void setRAM(RAM ram) {
        this.ram = ram;
    }
    
    public void setAnimator(GUI_BusAnimator animator){
        this.animator = animator;
    }

    public NumberingSystem swapRegistersBase() {
        registerA.swapBase();
        registerB.swapBase();
        registerC.swapBase();
        return registerD.swapBase();
        
    }
    
    public void updateGUI(){
        registerA.update();
        registerB.update();
        registerC.update();
        registerD.update();
        this.controlUnit.updateGUI();
    }
    
    public NumberingSystem swapAddressRegisterBase(){
        return this.controlUnit.swapAddressRegisterBase();
    }
    
    public NumberingSystem swapInstructionRegisterBase(){
        return this.controlUnit.swapInstructionRegisterBase();
    }

    public void setRegisterA(CpuRegister registerA) {
        this.registerA = registerA;
    }

    public void setRegisterB(CpuRegister registerB) {
        this.registerB = registerB;
    }

    public void setRegisterC(CpuRegister registerC) {
        this.registerC = registerC;
    }

    public void setRegisterD(CpuRegister registerD) {
        this.registerD = registerD;
    }
    
    public void setInstructionRegister(CpuRegister instructionRegister) {
        this.controlUnit.setInstructionRegister(instructionRegister);
    }
    
    public void setAddressRegister(CpuRegister addressRegister) {
        this.controlUnit.setAddressRegister(addressRegister);
    }
    
    
    public void fetch( ){
         this.animator.addressInput_On();
         
         
         this.animator.readEnable_On();
         
         this.animator.ramToControlUnit();
         
         this.controlUnit.fetch();
    }
    
    public void decode( ){
        
    }
    
    public void excecute( ){
        
    }
    
}
