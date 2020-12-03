/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1.CPU;

import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Jorge
 */
public class CPU {

    private ALU alu;
    private ControlUnit controlUnit;
    private RAM ram;

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

    public void swapRegistersBase(Boolean isBinaryOn) {
        registerA.swapBase(isBinaryOn);
        registerB.swapBase(isBinaryOn);
        registerC.swapBase(isBinaryOn);
        registerD.swapBase(isBinaryOn);
    }
    
    public void swapAddressRegisterBase(Boolean isBinaryOn){
        this.controlUnit.swapAddressRegister(isBinaryOn);
    }
    
    public void swapInstructionRegisterBase(Boolean isBinaryOn){
        this.controlUnit.swapInstructionRegister(isBinaryOn);
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
    

}
