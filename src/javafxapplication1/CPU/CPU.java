/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1.CPU;

import static java.lang.Thread.sleep;
import javafx.application.Platform;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Jorge
 */
public class CPU implements Runnable{

    private ALU alu;
    private ControlUnit controlUnit;
    private RAM ram;
    private GUI_BusAnimator animator;
    private CPU_Stage nextStage;
    private EmuStage emulationStage;
    private EmuMode emulationMode;
    
    private CpuRegister registerA;
    private CpuRegister registerB;
    private CpuRegister registerC;
    private CpuRegister registerD;
    
    public static int delay;
    public static int clockSpeed;
    public int speed;
    public static boolean interrupt;
    
    public CPU() {
        this.controlUnit = new ControlUnit();
        this.alu = new ALU(controlUnit);
        this.ram = new RAM();

        this.registerA = new CpuRegister();
        this.registerB = new CpuRegister();
        this.registerC = new CpuRegister();
        this.registerD = new CpuRegister();
        
        interrupt = false;
        this.nextStage = CPU_Stage.FETCH;
        this.emulationStage = EmuStage.PAUSED;
        this.emulationMode = EmuMode.BY_STEP;
        CPU.clockSpeed = 300;
        CPU.delay = clockSpeed;
        
        this.controlUnit.setALU(this.alu);
        this.controlUnit.setRegisters(this.registerA, this.registerB, this.registerC, this.registerD);
    }
    
    public void setStepMode(Boolean stepMode){
        if(stepMode){
            this.emulationMode = EmuMode.BY_STEP;
        }else{
            this.emulationMode = EmuMode.BY_LOOP;
        } 
    }
    
    public EmuStage swapPauseStage(){
        if(this.emulationStage == EmuStage.PAUSED){
            this.emulationStage = EmuStage.RUNNING;
            return EmuStage.PAUSED;
        }else if (this.emulationStage == EmuStage.RUNNING){
            this.emulationStage = EmuStage.PAUSED;
            return EmuStage.RUNNING;
        }
        return null;
    }
    
    public void pauseEnulation(){
        this.emulationStage = EmuStage.PAUSED;
    }
    
    public void resumeEmulation(){
        this.emulationStage = EmuStage.RUNNING;
    }
    
    public boolean isPaused(){
        if(this.emulationStage == EmuStage.PAUSED){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean isRunning(){
        return !this.isPaused();
    }
    
    public boolean isStepRunning(){
        if(this.emulationMode == EmuMode.BY_STEP){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean isLoopRunning(){
        return !this.isStepRunning();
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
        Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    registerA.update();
                    registerB.update();
                    registerC.update();
                    registerD.update();
                    controlUnit.updateGUI();
                }
            });
    }
    
    public void resetRegistersHighlight(){
        this.animator.resetRegistersBus();
        this.registerA.setHighlight(false);
        this.registerB.setHighlight(false);
        this.registerC.setHighlight(false);
        this.registerD.setHighlight(false);
        this.controlUnit.getAddressRegister().setHighlight(false);
        this.controlUnit.getInstructionRegister().setHighlight(false);
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
    
    public void setFlagsGUI(Rectangle oflag_rec, Rectangle zflag_rec, Rectangle nflag_rec){
        this.controlUnit.setFlagsGUI(oflag_rec, zflag_rec, nflag_rec);
    }
    
    public void resetRegisters(){
        this.registerA.setValue(0);
        this.registerB.setValue(0);
        this.registerC.setValue(0);
        this.registerD.setValue(0);
        this.controlUnit.getAddressRegister().setValue(0);
        this.controlUnit.getInstructionRegister().setValue(0);
        this.updateGUI();
    }
    
    public void resetAll(){
        this.resetBus();
        this.resetRegistersHighlight();
        this.resetRegisters();
    }
    
    public void fetch(){
        try{
            this.resetRegistersHighlight();
            this.animator.resetBus();
            this.ram.resetHighlight();
            this.controlUnit.getAddressRegister().setHighlight(true);
            sleep(CPU.delay);
            this.animator.addressInput_On();
            sleep(CPU.delay);
            this.animator.readEnable_On();
            sleep(CPU.delay);
            int address = Integer.parseInt(this.controlUnit.getAddressRegister().getIntegerValueAsString());
            this.ram.setHighlight(address, true);
            sleep(CPU.delay);
            this.animator.ramToControlUnit();
            sleep(CPU.delay);
            this.controlUnit.getInstructionRegister().setHighlight(true);
            sleep(CPU.delay/2);
            this.controlUnit.getInstructionRegister().copyValue(this.ram.getByAddress(address));
            this.updateGUI(); 
        }catch(Exception ex){
        }
    }
    
    public void decode( ){
        try{
            this.controlUnit.getAddressRegister().setHighlight(false);
            this.animator.resetBus();
            for(int i = 0; i < 15 ; i ++){
                if(i%2 == 0){
                    this.controlUnit.getInstructionRegister().setHighlight(true);
                }else{
                    this.controlUnit.getInstructionRegister().setHighlight(false);
                }
                sleep(CPU.delay/8);
            }
        }catch(Exception ex){
        }
    }
    
    public void excecute( ){
        try{
            this.animator.resetBus();
            Instruction inst = ControlUnit.getOpcode(this.controlUnit.getInstructionRegister());
            String inst_st = ControlUnit.getInstructionAsString(NumberingSystem.OPCdec, this.controlUnit.getInstructionRegister());
            //*************************ADD OR SUB OPERATION*****************************
            if(inst == Instruction.ADD || inst == Instruction.SUB){
                char op1 = inst_st.charAt(4);
                char op2 = inst_st.charAt(6);
                Register op1_reg = null;
                Register op2_reg = null;
                if(op1 == 'A'){
                    op1_reg = this.registerA;
                    this.registerA.setHighlight(true);
                    this.animator.registerAtoControlUnit();
                }else if(op1 == 'B'){
                    op1_reg = this.registerB;
                    this.registerB.setHighlight(true);
                    this.animator.registerBtoControlUnit();
                }else if(op1 == 'C'){
                    op1_reg = this.registerC;
                    this.registerC.setHighlight(true);
                    this.animator.registerCtoControlUnit();
                }else if(op1 == 'D'){
                    op1_reg = this.registerD;
                    this.registerD.setHighlight(true);
                    this.animator.registerDtoControlUnit();
                }
                
                if(op2 == 'A'){
                    op2_reg = this.registerA;
                    this.registerA.setHighlight(true);
                    this.animator.registerAtoControlUnit();
                }else if(op2 == 'B'){
                    op2_reg = this.registerB;
                    this.registerB.setHighlight(true);
                    this.animator.registerBtoControlUnit();
                }else if(op2 == 'C'){
                    op2_reg = this.registerC;
                    this.registerC.setHighlight(true);
                    this.animator.registerCtoControlUnit();
                }else if(op2 == 'D'){
                    op2_reg = this.registerD;
                    this.registerD.setHighlight(true);
                    this.animator.registerDtoControlUnit();
                }
                
                sleep(CPU.delay);
                this.animator.controlUnitToALU();
                int output = this.alu.operate(op1_reg, op2_reg, inst);
                sleep(CPU.delay);
                this.resetRegistersHighlight();
                this.animator.ALUtoControlUnit();
                sleep(CPU.delay);
                this.animator.resetALUinputBus();
                sleep(CPU.delay);
                if(op1 == 'A'){
                    this.animator.controlUnitToRegisterA();
                    sleep(CPU.delay);
                    this.registerA.setHighlight(true);
                    this.registerA.setValue(output);
                    this.registerA.update();
                }else if(op1 == 'B'){
                    this.animator.controlUnitToRegisterB();
                    sleep(CPU.delay);
                    this.registerB.setHighlight(true);
                    this.registerB.setValue(output);
                    this.registerB.update();
                }else if(op1 == 'C'){
                    this.animator.controlUnitToRegisterC();
                    sleep(CPU.delay);
                    this.registerC.setHighlight(true);
                    this.registerC.setValue(output);
                    this.registerC.update();
                }else if(op1 == 'D'){
                    this.animator.controlUnitToRegisterD();
                    sleep(CPU.delay);
                    this.registerD.setHighlight(true);
                    this.registerD.setValue(output);
                    this.registerD.update();
                }
                
                if(this.controlUnit.isFlagOn()){
                    this.animator.flags_On();
                    sleep(CPU.delay);
                    this.controlUnit.updateFlagsGUI();
                }else{
                    this.controlUnit.updateFlagsGUI();
                }
                
                this.addrRegisterCountUp();
                //***************************LOAD OPERATIONS*************************   
            }else if(inst == Instruction.LOAD_A || inst == Instruction.LOAD_B || inst == Instruction.LOAD_C ||inst == Instruction.LOAD_D){
                String str = this.controlUnit.getInstructionRegister().getBinaryValueAsString();
                String load_addr = ""+str.charAt(4)+str.charAt(5)+str.charAt(6)+str.charAt(7);
                int addr = ControlUnit.stringBinaryToInt(load_addr);
                this.animator.addressInput_On();
                sleep(CPU.delay);
                this.animator.readEnable_On();
                sleep(CPU.delay);
                this.ram.setHighlight(addr, true);
                sleep(CPU.delay);
                if(inst == Instruction.LOAD_A){
                    this.animator.ramToRegisterA();
                    this.animator.controlUnitToRegisterA();
                    sleep(CPU.delay);
                    this.registerA.setHighlight(true);
                    this.registerA.copyValue(this.ram.getByAddress(addr));
                    this.registerA.update();
                }else if(inst == Instruction.LOAD_B){
                    this.animator.ramToRegisterB();
                    this.animator.controlUnitToRegisterB();
                    sleep(CPU.delay);
                    this.registerB.setHighlight(true);
                    this.registerB.copyValue(this.ram.getByAddress(addr));
                    this.registerB.update();
                }else if(inst == Instruction.LOAD_C){
                    this.animator.ramToRegisterC();
                    this.animator.controlUnitToRegisterC();
                    sleep(CPU.delay);
                    this.registerC.setHighlight(true);
                    this.registerC.copyValue(this.ram.getByAddress(addr));
                    this.registerC.update();
                }else if(inst == Instruction.LOAD_D){
                    this.animator.ramToRegisterD();
                    this.animator.controlUnitToRegisterD();
                    sleep(CPU.delay);
                    this.registerD.setHighlight(true);
                    this.registerD.copyValue(this.ram.getByAddress(addr));
                    this.registerD.update();
                }
                this.addrRegisterCountUp();
                //***************************STORE OPERATIONS************************************
            }else if(inst == Instruction.STORE_A || inst == Instruction.STORE_B || inst == Instruction.STORE_C ||inst == Instruction.STORE_D){
                String str = this.controlUnit.getInstructionRegister().getBinaryValueAsString();
                String store_addr = ""+str.charAt(4)+str.charAt(5)+str.charAt(6)+str.charAt(7);
                int addr = ControlUnit.stringBinaryToInt(store_addr);
                
                if(inst == Instruction.STORE_A){
                    this.animator.controlUnitToRegisterA();
                    sleep(CPU.delay);
                    this.animator.registerAtoRAM();
                    sleep(CPU.delay);
                    this.ram.getByAddress(addr).copyValue(registerA);
                }else if(inst == Instruction.STORE_B){
                    this.animator.controlUnitToRegisterB();
                    sleep(CPU.delay);
                    this.animator.registerBtoRAM();
                    sleep(CPU.delay);
                    this.ram.getByAddress(addr).copyValue(registerB);
                }else if(inst == Instruction.STORE_C){
                    this.animator.controlUnitToRegisterC();
                    sleep(CPU.delay);
                    this.animator.registerCtoRAM();
                    sleep(CPU.delay);
                    this.ram.getByAddress(addr).copyValue(registerC);
                }else if(inst == Instruction.STORE_D){
                    this.animator.controlUnitToRegisterD();
                    sleep(CPU.delay);
                    this.animator.registerDtoRAM();
                    sleep(CPU.delay);
                    this.ram.getByAddress(addr).copyValue(registerD);
                }
                this.animator.addressInput_On();
                sleep(CPU.delay);
                this.animator.writeEnable_On();
                sleep(CPU.delay);
                this.ram.setHighlight(addr, true);
                this.ram.update();
                
                this.addrRegisterCountUp();
            }else if(inst == Instruction.JUMP || inst == Instruction.JUMP_ABV || inst == Instruction.JUMP_BLW || inst == Instruction.JUMP_ZRO || inst == Instruction.JUMP_NEG){
                for(int i = 0; i  < 11; i ++){
                    if(i%2 == 0){
                        this.controlUnit.getAddressRegister().setHighlight(true);
                    }else{
                        this.controlUnit.getAddressRegister().setHighlight(false);
                    }
                        sleep(CPU.delay/8);
                }
                if(inst == Instruction.JUMP){
                    String str = this.controlUnit.getInstructionRegister().getBinaryValueAsString();
                    String jump_addr = "0000"+str.charAt(4)+str.charAt(5)+str.charAt(6)+str.charAt(7);
                    this.controlUnit.getAddressRegister().setValue(jump_addr);
                    this.updateGUI();
                }if(inst == Instruction.JUMP_NEG){
                    if(this.controlUnit.getNflag()){
                        String str = this.controlUnit.getInstructionRegister().getBinaryValueAsString();
                        String jump_addr = "0000"+str.charAt(4)+str.charAt(5)+str.charAt(6)+str.charAt(7);
                        this.controlUnit.getAddressRegister().setValue(jump_addr);
                        this.updateGUI();
                    }else{
                        this.addrRegisterCountUp();
                    }
                }if(inst == Instruction.JUMP_ZRO){
                    if(this.controlUnit.getZflag()){
                        String str = this.controlUnit.getInstructionRegister().getBinaryValueAsString();
                        String jump_addr = "0000"+str.charAt(4)+str.charAt(5)+str.charAt(6)+str.charAt(7);
                        this.controlUnit.getAddressRegister().setValue(jump_addr);
                        this.updateGUI();
                    }else{
                        this.addrRegisterCountUp();
                    }
                }
                //*****************HALT OPERATION************************
            }else if(inst == Instruction.HALT){
                this.nextStage = CPU_Stage.HALT;
                Thread.currentThread().interrupt();
            }
        }catch(Exception ex){
        }
    }
    
    public void addrRegisterCountUp(){
        int next_addr = Integer.parseInt(this.controlUnit.getAddressRegister().getIntegerValueAsString())+1;
        this.controlUnit.getAddressRegister().setValue(next_addr);
        this.updateGUI();
    }
    
    public void resetBus(){
        this.animator.resetBus();
    }  
    
    public CPU_Stage getNextStage(){
        return this.nextStage;
    }
    
    public static void safeInterrupt(){
        interrupt = true;
    }
    
    @Override
    public void run(){
        if(this.emulationMode == EmuMode.BY_STEP){
            if(this.nextStage == CPU_Stage.FETCH){
                this.fetch();
                this.nextStage = CPU_Stage.DECODE;
            }else if(this.nextStage == CPU_Stage.DECODE){
                this.decode();
                this.nextStage = CPU_Stage.EXCECUTE;
            }else if(this.nextStage == CPU_Stage.EXCECUTE){
                this.excecute();
                this.nextStage = CPU_Stage.FETCH;
            }else if (this.nextStage == CPU_Stage.HALT){
                Thread.currentThread().interrupt();
                Thread.currentThread().stop();
            }
        }else if (this.emulationMode == EmuMode.BY_LOOP){    
           while(!interrupt && this.nextStage != CPU_Stage.HALT && this.emulationMode == EmuMode.BY_LOOP && this.emulationStage != EmuStage.PAUSED){
                try{
                    if(this.nextStage == CPU_Stage.FETCH){
                        this.fetch();
                        sleep(CPU.delay);
                        this.nextStage = CPU_Stage.DECODE;
                    }else if(this.nextStage == CPU_Stage.DECODE){
                        this.decode();
                        sleep(CPU.delay);
                        this.nextStage = CPU_Stage.EXCECUTE;
                    }else if(this.nextStage == CPU_Stage.EXCECUTE){
                        this.excecute();
                        sleep(CPU.delay);
                        this.nextStage = CPU_Stage.FETCH;
                    }else if (this.nextStage == CPU_Stage.HALT){
                        Thread.currentThread().interrupt();
                        Thread.currentThread().stop();
                    }
                }catch(Exception ex){}
            }
            Thread.currentThread().interrupt();

        }

    }
    
}
