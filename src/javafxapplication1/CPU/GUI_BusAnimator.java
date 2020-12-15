/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1.CPU;

import java.util.ArrayList;
import javafx.scene.shape.Line;

/**
 *
 * @author Jorge
 */
public class GUI_BusAnimator {
    private ArrayList<ArrayList<Line>> all;
    private boolean reverse;
    private ArrayList<Line> toAnimate;
    //writeEnable
    private ArrayList<Line> ramToRegisterA;
    private ArrayList<Line> ramToRegisterB;
    private ArrayList<Line> ramToRegisterC;
    private ArrayList<Line> ramToRegisterD;
    
    private ArrayList<Line> ramToControlUnit;
    
    private ArrayList<Line> controlUnitToRegisterA;
    private ArrayList<Line> controlUnitToRegisterB;
    private ArrayList<Line> controlUnitToRegisterC;
    private ArrayList<Line> controlUnitToRegisterD;
    
    private ArrayList<Line> controlUnitToALU;
    private ArrayList<Line> ALUtoControlUnit;
    
    private ArrayList<Line> addressInput;
    
    private ArrayList<Line> writeEnable;
    private ArrayList<Line> readEnable;
    
    private ArrayList<Line> flags;
    
    public GUI_BusAnimator(){
        this.all = new ArrayList<>();
    }

    public void setFlags(ArrayList<Line> flags) {
        this.flags = flags;
        this.all.add(flags);
    }

    public void setRamToRegisterA(ArrayList<Line> ramToRegisterA) {
        this.ramToRegisterA = ramToRegisterA;
        this.all.add(ramToRegisterA);
    }

    public void setRamToRegisterB(ArrayList<Line> ramToRegisterB) {
        this.ramToRegisterB = ramToRegisterB;
        this.all.add(ramToRegisterB);
    }

    public void setRamToRegisterC(ArrayList<Line> ramToRegisterC) {
        this.ramToRegisterC = ramToRegisterC;
        this.all.add(ramToRegisterC);
    }

    public void setRamToRegisterD(ArrayList<Line> ramToRegisterD) {
        this.ramToRegisterD = ramToRegisterD;
        this.all.add(ramToRegisterD);
    }

    public void setRamToControlUnit(ArrayList<Line> ramToControlUnit) {
        this.ramToControlUnit = ramToControlUnit;
        this.all.add(ramToControlUnit);
    }

    public void setControlUnitToRegisterA(ArrayList<Line> ControlUnitToRegisterA) {
        this.controlUnitToRegisterA = ControlUnitToRegisterA;
        this.all.add(ControlUnitToRegisterA);
    }

    public void setControlUnitToRegisterB(ArrayList<Line> ControlUnitToRegisterB) {
        this.controlUnitToRegisterB = ControlUnitToRegisterB;
        this.all.add(ControlUnitToRegisterB);
    }

    public void setControlUnitToRegisterC(ArrayList<Line> ControlUnitToRegisterC) {
        this.controlUnitToRegisterC = ControlUnitToRegisterC;
        this.all.add(ControlUnitToRegisterC);
    }

    public void setControlUnitToRegisterD(ArrayList<Line> ControlUnitToRegisterD) {
        this.controlUnitToRegisterD = ControlUnitToRegisterD;
        this.all.add(ControlUnitToRegisterD);
    }

    public void setControlUnitToALU(ArrayList<Line> ControlUnitToALU) {
        this.controlUnitToALU = ControlUnitToALU;
        this.all.add(ControlUnitToALU);
    }

    public void setAddressInput(ArrayList<Line> addressInput) {
        this.addressInput = addressInput;
        this.all.add(addressInput);
    }

    public void setALUtoControlUnit(ArrayList<Line> ALUtoControlUnit) {
        this.ALUtoControlUnit = ALUtoControlUnit;
        this.all.add(ALUtoControlUnit);
    }

    public void setWriteEnable(ArrayList<Line> writeEnable) {
        this.writeEnable = writeEnable;
        this.all.add(writeEnable);
    }

    public void setReadEnable(ArrayList<Line> readEnable) {
        this.readEnable = readEnable;
        this.all.add(readEnable);
    }
    
   //METODOS
    
    public void writeEnable_On(){
        LineAnimator animator = new LineAnimator();
        animator.setBusAndSence(this.writeEnable, false);
        animator.start();
    }
    
    public void readEnable_On(){
         LineAnimator animator = new LineAnimator();
        animator.setBusAndSence(this.readEnable,false);
        animator.start();
    }
    
    public void addressInput_On(){
        LineAnimator animator = new LineAnimator();
        animator.setBusAndSence(this.addressInput, false);
        animator.start();
    }
    
    public void flags_On(){
        LineAnimator animator = new LineAnimator();
        animator.setBusAndSence(flags, false);
        animator.start();
    }
    
    public void ramToRegisterA(){
        LineAnimator animator = new LineAnimator();
        animator.setBusAndSence(this.ramToRegisterA,false);
        animator.start();
    }
    public void registerAtoRAM(){
        LineAnimator animator = new LineAnimator();
        animator.setBusAndSence(this.ramToRegisterA,true);
        animator.start();
    }
    
    public void ramToRegisterB(){
        LineAnimator animator = new LineAnimator();
        animator.setBusAndSence(this.ramToRegisterB,false);
        animator.start();
    }
    public void registerBtoRAM(){
        LineAnimator animator = new LineAnimator();
        animator.setBusAndSence(this.ramToRegisterB,true);
        animator.start();
    }
    public void ramToRegisterC(){
        LineAnimator animator = new LineAnimator();
        animator.setBusAndSence(this.ramToRegisterC,false);
        animator.start();
    }
    public void registerCtoRAM(){
        LineAnimator animator = new LineAnimator();
        animator.setBusAndSence(this.ramToRegisterC,true);
        animator.start();
    }
    public void ramToRegisterD(){
        LineAnimator animator = new LineAnimator();
        animator.setBusAndSence(this.ramToRegisterD,false);
        animator.start();
    }public void registerDtoRAM(){
        LineAnimator animator = new LineAnimator();
        animator.setBusAndSence(this.ramToRegisterD,true);
        animator.start();
    }
    
    public void controlUnitToRegisterA(){
        LineAnimator animator = new LineAnimator();
        animator.setBusAndSence(this.controlUnitToRegisterA, false);
        animator.start();
    }
    public void registerAtoControlUnit(){
        LineAnimator animator = new LineAnimator();
        animator.setBusAndSence(this.controlUnitToRegisterA, true);
        animator.start();
    }
    public void controlUnitToRegisterB(){
        LineAnimator animator = new LineAnimator();
        animator.setBusAndSence(this.controlUnitToRegisterB, false);
        animator.start();
    }
    public void registerBtoControlUnit(){
        LineAnimator animator = new LineAnimator();
        animator.setBusAndSence(this.controlUnitToRegisterB, true);
        animator.start();
    }
    public void controlUnitToRegisterC(){
        LineAnimator animator = new LineAnimator();
        animator.setBusAndSence(this.controlUnitToRegisterC, false);
        animator.start();
    }
    public void registerCtoControlUnit(){
        LineAnimator animator = new LineAnimator();
        animator.setBusAndSence(this.controlUnitToRegisterC, true);
        animator.start();
    }
    public void controlUnitToRegisterD(){
        LineAnimator animator = new LineAnimator();
        animator.setBusAndSence(this.controlUnitToRegisterD, false);
        animator.start();
    }
    public void registerDtoControlUnit(){
        LineAnimator animator = new LineAnimator();
        animator.setBusAndSence(this.controlUnitToRegisterD, true);
        animator.start();
    }
    
    public void ramToControlUnit(){////////////////////////////
        LineAnimator animator = new LineAnimator();
        animator.setBusAndSence(this.ramToControlUnit, false);
        animator.start();
    }
    
    public void controlUnitToRAM(){
        LineAnimator animator = new LineAnimator();
        animator.setBusAndSence(this.ramToControlUnit,true);
        animator.start();
    }
    
    public void controlUnitToALU(){
        LineAnimator animator = new LineAnimator();
        animator.setBusAndSence(this.controlUnitToALU, false);
       animator.start();
    }
    
    public void ALUtoControlUnit(){
        LineAnimator animator = new LineAnimator();
        animator.setBusAndSence(this.ALUtoControlUnit, false);
        animator.start();
    }

    public void resetBus(){
        for(ArrayList<Line> ar: all){
            for(Line l : ar){
                l.setStyle("-fx-stroke: #505050;");
            }
        }
    }
    
    
}
