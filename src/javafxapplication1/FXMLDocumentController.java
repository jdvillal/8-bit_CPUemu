/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafxapplication1.CPU.CPU;
import javafxapplication1.CPU.CPU_Stage;
import javafxapplication1.CPU.CpuRegister;
import javafxapplication1.CPU.EmuStage;
import javafxapplication1.CPU.GUI_BusAnimator;
import javafxapplication1.CPU.NumberingSystem;
import javafxapplication1.CPU.RAM;
import javafxapplication1.CPU.RamRegister;

/**
 *
 * @author Jorge
 */
public class FXMLDocumentController implements Initializable {
    Boolean demo = false;
    
    private CPU cpu = new CPU();
    private RAM ram = new RAM();
    private GUI_BusAnimator  bs_animator;
    public static ArrayList<Thread> threads = new ArrayList<>();
    String filePath;
    
    Boolean cpuRegisters_isBinaryOn = true;
    Boolean addresRegister_isBinaryOn = true;
    Boolean instructionRegister_isBinaryOn = true;
    
    ArrayList<RamRegister> ramRegisters = new ArrayList<>();
        
    //*************CPU REGISTERS***********
    @FXML//registro A
    private Label regA_lbl;
    @FXML
    private Rectangle regA_rec;
    @FXML
    private Button regA_btn;
    @FXML//registro B
    private Label regB_lbl; 
    @FXML
    private Rectangle regB_rec;
    @FXML
    private Button regB_btn;
    @FXML//registro C
    private Label regC_lbl;
    @FXML
    private Rectangle regC_rec;
    @FXML
    private Button regC_btn;
    @FXML//registro D
    private Label regD_lbl;
    @FXML
    private Rectangle regD_rec;
    @FXML
    private Button regD_btn;
    
    @FXML//registro de instruccion
    private Label instructionReg_lbl;
    @FXML
    private Rectangle instructionReg_rec ;
    @FXML
    private Button instructionRegister_btn;
    @FXML//registro de direccion
    private Label addressReg_lbl;
    @FXML
    private Rectangle addressReg_rec;
    @FXML
    private Button addressRegister_btn;
    
    //****************RAM REGISTERS********************
    @FXML//
    private Label addr0_lbl;
    @FXML//
    private Label addr0Num_lbl;
    @FXML
    private Rectangle addr0_rec;
    @FXML
    private Button ramBtn0;
    
    @FXML//
    private Label addr1_lbl;
    @FXML//
    private Label addr1Num_lbl;
    @FXML
    private Rectangle addr1_rec;
    @FXML
    private Button ramBtn1;
    
    @FXML//
    private Label addr2_lbl;
    @FXML//
    private Label addr2Num_lbl;
    @FXML
    private Rectangle addr2_rec;
    @FXML
    private Button ramBtn2;
    
    @FXML//
    private Label addr3_lbl;
    @FXML//
    private Label addr3Num_lbl;
    @FXML
    private Rectangle addr3_rec;
    @FXML
    private Button ramBtn3;
    
    @FXML//
    private Label addr4_lbl;
    @FXML//
    private Label addr4Num_lbl;
    @FXML
    private Rectangle addr4_rec;
    @FXML
    private Button ramBtn4;
    
    @FXML//
    private Label addr5_lbl;
    @FXML//
    private Label addr5Num_lbl;
    @FXML
    private Rectangle addr5_rec;
    @FXML
    private Button ramBtn5;
    
    @FXML//
    private Label addr6_lbl;
    @FXML//
    private Label addr6Num_lbl;
    @FXML
    private Rectangle addr6_rec;
    @FXML
    private Button ramBtn6;
    
    @FXML//
    private Label addr7_lbl;
    @FXML//
    private Label addr7Num_lbl;
    @FXML
    private Rectangle addr7_rec;
    @FXML
    private Button ramBtn7;
    
    @FXML//
    private Label addr8_lbl;
    @FXML//
    private Label addr8Num_lbl;
    @FXML
    private Rectangle addr8_rec;
    @FXML
    private Button ramBtn8;
    
    @FXML//
    private Label addr9_lbl;
    @FXML//
    private Label addr9Num_lbl;
    @FXML
    private Rectangle addr9_rec;
    @FXML
    private Button ramBtn9;
    
    @FXML//
    private Label addr10_lbl;
    @FXML//
    private Label addr10Num_lbl;
    @FXML
    private Rectangle addr10_rec;
    @FXML
    private Button ramBtn10;
    
    @FXML//
    private Label addr11_lbl;
    @FXML//
    private Label addr11Num_lbl;
    @FXML
    private Rectangle addr11_rec;
    @FXML
    private Button ramBtn11;
    
    @FXML//
    private Label addr12_lbl;
    @FXML//
    private Label addr12Num_lbl;
    @FXML
    private Rectangle addr12_rec;
    @FXML
    private Button ramBtn12;
    
    @FXML//
    private Label addr13_lbl;
    @FXML//
    private Label addr13Num_lbl;
    @FXML
    private Rectangle addr13_rec;
    @FXML
    private Button ramBtn13;
    
    @FXML//
    private Label addr14_lbl;
    @FXML//
    private Label addr14Num_lbl;
    @FXML
    private Rectangle addr14_rec;
    @FXML
    private Button ramBtn14;
    
    @FXML//
    private Label addr15_lbl;
    @FXML//
    private Label addr15Num_lbl;
    @FXML
    private Rectangle addr15_rec;
    @FXML
    private Button ramBtn15;
    
    
    @FXML
    private Button ramAddresses_btn;
    
    
    
    
    //*********ADDRES AND ENABLE BUSES GUI LINES********
    @FXML
    private Line data1_line;
    @FXML
    private Line data2_line;
    @FXML
    private Line data3_line;
    @FXML
    private Line data4_line;
    @FXML
    private Line data5_line;
    @FXML
    private Line data6_line;
    @FXML
    private Line data7_line;
    @FXML
    private Line dataRegA_line;
    @FXML
    private Line dataRegB_line;
    @FXML
    private Line dataRegC_line;
    @FXML
    private Line dataRegD_line;
    
    @FXML
    private Line addressInput_line;
    @FXML
    private Line rEnable1_line;
    @FXML
    private Line rEnable2_line;
    @FXML
    private Line wEnable1_line;
    @FXML
    private Line wEnable2_line;
    @FXML
    private Line regA1_line;
    @FXML
    private Line regA2_line;
    @FXML
    private Line regA3_line;
    @FXML
    private Line regB1_line;
    @FXML
    private Line regB2_line;
    @FXML
    private Line regB3_line;
    @FXML
    private Line regC1_line;
    @FXML
    private Line regC2_line;
    @FXML
    private Line regC3_line;
    @FXML
    private Line regD1_line;
    @FXML
    private Line regD2_line;
    @FXML
    private Line regD3_line;
    
    @FXML
    private Line ALUinputA1_line;
    @FXML
    private Line ALUinputA2_line;
    @FXML
    private Line ALUinputB1_line;
    @FXML
    private Line ALUinputB2_line;
    @FXML
    private Line opcode_line;
    @FXML
    private Line flags_line;
    @FXML
    private Line ALUout1_line;
    @FXML
    private Line ALUout2_line;
    @FXML
    private Line ALUout3_line;
    
    @FXML
    private Rectangle oflag_rec;
    @FXML
    private Rectangle zflag_rec;
    @FXML
    private Rectangle nflag_rec;
    
    @FXML
    private Slider clockSpeed_slider;
    
    
    @FXML
    private Button stepRun_btn;
    
    @FXML
    private Button loopRun_btn;
    
    @FXML
    private Button previous_btn;
    
   
    public void onSlide(){
        DecimalFormat df = new DecimalFormat("#.#");
        df.setRoundingMode(RoundingMode.CEILING);
        double db = Double.parseDouble(df.format(this.clockSpeed_slider.getValue()));
        if(db == 0){
            db = 0.1;
        }
        this.clockSpeed_slider.setValue(db);
        this.setClockSpeed(db);
    }
    public void setSlider(){
        this.clockSpeed_slider.setMin(0.5);
        this.clockSpeed_slider.setMax(5);
        this.clockSpeed_slider.setValue(1);
        this.clockSpeed_slider.setShowTickLabels(true);
        this.clockSpeed_slider.setShowTickMarks(true);
        this.clockSpeed_slider.setMajorTickUnit(0.50);
        this.clockSpeed_slider.setBlockIncrement(0.05);
    }
    
    public void setClockSpeed(double mult){
        Double d = CPU.clockSpeed/mult;
        CPU.delay = d.intValue();
    }
    
    public void enableRunButtons(){
        this.stepRun_btn.setDisable(false);
        this.loopRun_btn.setDisable(false);
    }
    
    

    //@FXML
    private void startCPU() {
        this.setSlider();
        this.clockSpeed_slider.setOnMouseReleased((Event event) -> {
            onSlide();
        });
        
        this.enableRunButtons();
        CpuRegister registerA = new CpuRegister(); registerA.setGUI(regA_lbl, regA_rec);
        cpu.setRegisterA(registerA);
        CpuRegister registerB = new CpuRegister(); registerB.setGUI(regB_lbl, regB_rec);
        cpu.setRegisterB(registerB);
        CpuRegister registerC = new CpuRegister(); registerC.setGUI(regC_lbl, regC_rec);
        cpu.setRegisterC(registerC);
        CpuRegister registerD = new CpuRegister(); registerD.setGUI(regD_lbl, regD_rec);
        cpu.setRegisterD(registerD);
        
        CpuRegister addressRegister = new CpuRegister(); addressRegister.setGUI(addressReg_lbl, addressReg_rec);
        cpu.setAddressRegister(addressRegister);
        CpuRegister instructionRegister = new CpuRegister(); instructionRegister.setGUI(instructionReg_lbl, instructionReg_rec);
        cpu.setInstructionRegister(instructionRegister);
        
        cpu.setFlagsGUI(oflag_rec, zflag_rec, nflag_rec);
        
        this.ram.getByAddress(0).setRamRegisterGUI(addr0Num_lbl, addr0_lbl, addr0_rec);
        this.ram.getByAddress(1).setRamRegisterGUI(addr1Num_lbl, addr1_lbl, addr1_rec);
        this.ram.getByAddress(2).setRamRegisterGUI(addr2Num_lbl, addr2_lbl, addr2_rec);
        this.ram.getByAddress(3).setRamRegisterGUI(addr3Num_lbl, addr3_lbl, addr3_rec);
        this.ram.getByAddress(4).setRamRegisterGUI(addr4Num_lbl, addr4_lbl, addr4_rec);
        this.ram.getByAddress(5).setRamRegisterGUI(addr5Num_lbl, addr5_lbl, addr5_rec);
        this.ram.getByAddress(6).setRamRegisterGUI(addr6Num_lbl, addr6_lbl, addr6_rec);
        this.ram.getByAddress(7).setRamRegisterGUI(addr7Num_lbl, addr7_lbl, addr7_rec);
        this.ram.getByAddress(8).setRamRegisterGUI(addr8Num_lbl, addr8_lbl, addr8_rec);
        this.ram.getByAddress(9).setRamRegisterGUI(addr9Num_lbl, addr9_lbl, addr9_rec);
        this.ram.getByAddress(10).setRamRegisterGUI(addr10Num_lbl, addr10_lbl, addr10_rec);
        this.ram.getByAddress(11).setRamRegisterGUI(addr11Num_lbl, addr11_lbl, addr11_rec);
        this.ram.getByAddress(12).setRamRegisterGUI(addr12Num_lbl, addr12_lbl, addr12_rec);
        this.ram.getByAddress(13).setRamRegisterGUI(addr13Num_lbl, addr13_lbl, addr13_rec);
        this.ram.getByAddress(14).setRamRegisterGUI(addr14Num_lbl, addr14_lbl, addr14_rec);
        this.ram.getByAddress(15).setRamRegisterGUI(addr15Num_lbl, addr15_lbl, addr15_rec);
        
        this.ram.update();
        this.cpu.setRAM(this.ram); 
        
        ArrayList<Line> ramToRegisterA = new ArrayList<>();
        ramToRegisterA.add(data1_line);
        ramToRegisterA.add(data2_line);
        ramToRegisterA.add(data3_line);
        ramToRegisterA.add(data4_line);
        ramToRegisterA.add(data5_line);
        ramToRegisterA.add(dataRegA_line);
        
        ArrayList<Line> ramToRegisterB = new ArrayList<>();
        ramToRegisterB.add(data1_line);
        ramToRegisterB.add(data2_line);
        ramToRegisterB.add(data3_line);
        ramToRegisterB.add(data4_line);
        ramToRegisterB.add(dataRegB_line);
 
        ArrayList<Line> ramToRegisterC = new ArrayList<>();
        ramToRegisterC.add(data1_line);
        ramToRegisterC.add(data2_line);
        ramToRegisterC.add(data3_line);
        ramToRegisterC.add(dataRegC_line);
        
        ArrayList<Line> ramToRegisterD = new ArrayList<>();
        ramToRegisterD.add(data1_line);
        ramToRegisterD.add(data2_line);
        ramToRegisterD.add(dataRegD_line);
        
        ArrayList<Line> ramToControlUnit = new ArrayList<>();
        ramToControlUnit.add(data1_line);
        ramToControlUnit.add(data6_line);
        ramToControlUnit.add(data7_line);
        
        ArrayList<Line> addressInput = new ArrayList<>();
        addressInput.add(addressInput_line);
        
        ArrayList<Line> controlUnitToALU = new ArrayList<>();
        controlUnitToALU.add(ALUinputA1_line);
        controlUnitToALU.add(ALUinputA2_line);
        controlUnitToALU.add(ALUinputB1_line);
        controlUnitToALU.add(ALUinputB2_line);
        controlUnitToALU.add(opcode_line);
        
        ArrayList<Line> controlUnitToRegisterA = new ArrayList<>();
        controlUnitToRegisterA.add(regA1_line);
        controlUnitToRegisterA.add(regA2_line);
        controlUnitToRegisterA.add(regA3_line);
        
        ArrayList<Line> controlUnitToRegisterB = new ArrayList<>();
        controlUnitToRegisterB.add(regB1_line);
        controlUnitToRegisterB.add(regB2_line);
        controlUnitToRegisterB.add(regB3_line);
        
        ArrayList<Line> controlUnitToRegisterC = new ArrayList<>();
        controlUnitToRegisterC.add(regC1_line);
        controlUnitToRegisterC.add(regC2_line);
        controlUnitToRegisterC.add(regC3_line);
        
        ArrayList<Line> controlUnitToRegisterD = new ArrayList<>();
        controlUnitToRegisterD.add(regD1_line);
        controlUnitToRegisterD.add(regD2_line);
        controlUnitToRegisterD.add(regD3_line);
        
        ArrayList<Line> ALUtoControlUnit = new ArrayList<>();
        ALUtoControlUnit.add(ALUout1_line);
        ALUtoControlUnit.add(ALUout2_line);
        ALUtoControlUnit.add(ALUout3_line);
        
        ArrayList<Line> flags = new ArrayList<>();
        flags.add(flags_line);
        
        ArrayList<Line> rEnable = new ArrayList<>();
        rEnable.add(rEnable1_line);
        rEnable.add(rEnable2_line);
        
        ArrayList<Line> wEnable = new ArrayList<>();
        wEnable.add(wEnable1_line);
        wEnable.add(wEnable2_line);
        
        
        this.bs_animator = new GUI_BusAnimator();
        this.bs_animator.setALUtoControlUnit(ALUtoControlUnit);
        this.bs_animator.setAddressInput(addressInput);
        this.bs_animator.setControlUnitToALU(controlUnitToALU);
        this.bs_animator.setControlUnitToRegisterA(controlUnitToRegisterA);
        this.bs_animator.setControlUnitToRegisterB(controlUnitToRegisterB);
        this.bs_animator.setControlUnitToRegisterC(controlUnitToRegisterC);
        this.bs_animator.setControlUnitToRegisterD(controlUnitToRegisterD);
        this.bs_animator.setFlags(flags);
        this.bs_animator.setRamToControlUnit(ramToControlUnit);
        this.bs_animator.setRamToRegisterA(ramToRegisterA);
        this.bs_animator.setRamToRegisterB(ramToRegisterB);
        this.bs_animator.setRamToRegisterC(ramToRegisterC);
        this.bs_animator.setRamToRegisterD(ramToRegisterD);
        
        this.bs_animator.setReadEnable(rEnable);
        this.bs_animator.setWriteEnable(wEnable);
        try{
            this.cpu.resetAll();
            this.ram.resetHighlight();
        }catch(Exception ex){}
        
        this.cpu.setAnimator(this.bs_animator);
    }
    
    @FXML
    private void swapRamAddressesBase(){
        NumberingSystem ns = ram.swapRamAddressesBase();
        String st = "(" + ns.toString() + ")";
        this.ramAddresses_btn.setText(st);
        ram.update();    
    }
    
    @FXML
    private void swapRamContentBase_0(){
        NumberingSystem ns = ram.getByAddress(0).swapContentBase();
        String st = "(" + ns.toString() + ")";
        this.ramBtn0.setText(st);
        ram.getByAddress(0).update();
    }
    @FXML
    private void swapRamContentBase_1(){
        NumberingSystem ns = ram.getByAddress(1).swapContentBase();
        String st = "(" + ns.toString() + ")";
        this.ramBtn1.setText(st);
        ram.getByAddress(1).update();
    }
    @FXML
    private void swapRamContentBase_2(){
        NumberingSystem ns = ram.getByAddress(2).swapContentBase();
        String st = "(" + ns.toString() + ")";
        this.ramBtn2.setText(st);
        ram.getByAddress(2).update();
    }
    @FXML
    private void swapRamContentBase_3(){
        NumberingSystem ns = ram.getByAddress(3).swapContentBase();
        String st = "(" + ns.toString() + ")";
        this.ramBtn3.setText(st);
        ram.getByAddress(3).update();
    }
    @FXML
    private void swapRamContentBase_4(){
        NumberingSystem ns = ram.getByAddress(4).swapContentBase();
        String st = "(" + ns.toString() + ")";
        this.ramBtn4.setText(st);
        ram.getByAddress(4).update();
    }
    @FXML
    private void swapRamContentBase_5(){
        NumberingSystem ns = ram.getByAddress(5).swapContentBase();
        String st = "(" + ns.toString() + ")";
        this.ramBtn5.setText(st);
        ram.getByAddress(5).update();
    }
    @FXML
    private void swapRamContentBase_6(){
        NumberingSystem ns = ram.getByAddress(6).swapContentBase();
        String st = "(" + ns.toString() + ")";
        this.ramBtn6.setText(st);
        ram.getByAddress(6).update();
    }
    @FXML
    private void swapRamContentBase_7(){
        NumberingSystem ns = ram.getByAddress(7).swapContentBase();
        String st = "(" + ns.toString() + ")";
        this.ramBtn7.setText(st);
        ram.getByAddress(7).update();
    }
    @FXML
    private void swapRamContentBase_8(){
        NumberingSystem ns = ram.getByAddress(8).swapContentBase();
        String st = "(" + ns.toString() + ")";
        this.ramBtn8.setText(st);
        ram.getByAddress(8).update();
    }
    @FXML
    private void swapRamContentBase_9(){
        NumberingSystem ns = ram.getByAddress(9).swapContentBase();
        String st = "(" + ns.toString() + ")";
        this.ramBtn9.setText(st);
        ram.getByAddress(9).update();
    }
    @FXML
    private void swapRamContentBase_10(){
        NumberingSystem ns = ram.getByAddress(10).swapContentBase();
        String st = "(" + ns.toString() + ")";
        this.ramBtn10.setText(st);
        ram.getByAddress(10).update();
    }
    @FXML
    private void swapRamContentBase_11(){
        NumberingSystem ns = ram.getByAddress(11).swapContentBase();
        String st = "(" + ns.toString() + ")";
        this.ramBtn11.setText(st);
        ram.getByAddress(11).update();
    }
    @FXML
    private void swapRamContentBase_12(){
        NumberingSystem ns = ram.getByAddress(12).swapContentBase();
        String st = "(" + ns.toString() + ")";
        this.ramBtn12.setText(st);
        ram.getByAddress(12).update();
    }
    @FXML
    private void swapRamContentBase_13(){
        NumberingSystem ns = ram.getByAddress(13).swapContentBase();
        String st = "(" + ns.toString() + ")";
        this.ramBtn13.setText(st);
        ram.getByAddress(13).update();
    }
    @FXML
    private void swapRamContentBase_14(){
        NumberingSystem ns = ram.getByAddress(14).swapContentBase();
        String st = "(" + ns.toString() + ")";
        this.ramBtn14.setText(st);
        ram.getByAddress(14).update();
    }
    @FXML
    private void swapRamContentBase_15(){
        NumberingSystem ns = ram.getByAddress(15).swapContentBase();
        String st = "(" + ns.toString() + ")";
        this.ramBtn15.setText(st);
        ram.getByAddress(15).update();
    }
    
    
    @FXML
    private void swapRegisters(ActionEvent event){
        NumberingSystem ns = cpu.swapRegistersBase();
        String st = "(" + ns.toString() + ")";
        this.regA_btn.setText(st);
        this.regB_btn.setText(st);
        this.regC_btn.setText(st);
        this.regD_btn.setText(st);
        cpu.updateGUI();
    }
    
    @FXML
    private void swapAddressRegister(ActionEvent event){
        NumberingSystem ns = cpu.swapAddressRegisterBase();
        String st = "(" + ns.toString() + ")";
        this.addressRegister_btn.setText(st);
        cpu.updateGUI();
    }
    
    @FXML
    private void swapInstructionRegister(ActionEvent event){
        NumberingSystem ns = cpu.swapInstructionRegisterBase();
        String st = "(" + ns.toString() + ")";
        this.instructionRegister_btn.setText(st);
        cpu.updateGUI();
    }
    
    @FXML
    private void loopRun(ActionEvent event){
        EmuStage previousStage = this.cpu.swapPauseStage();
        if(previousStage == EmuStage.PAUSED){
            Image img = new Image(getClass().getResourceAsStream("pause.png"));
            ImageView view = new ImageView(img);
            this.loopRun_btn.setGraphic(view);
            this.stepRun_btn.setDisable(true);
            this.cpu.setStepMode(false);
            Thread th = new Thread(this.cpu);
            threads.add(th);
            th.start();   
        }else{
            Image img = new Image(getClass().getResourceAsStream("run.png"));
            ImageView view = new ImageView(img);
            this.loopRun_btn.setGraphic(view);
            Thread th = new Thread(() -> {
                CPU_Stage stg = cpu.getNextStage();
                CPU_Stage stg0 = cpu.getNextStage();
                try{
                    while(stg == stg0){
                        stg = cpu.getNextStage();
                        Thread.sleep(CPU.delay/3);
                    }
                    stepRun_btn.setDisable(false);
                }catch(InterruptedException ex){
                }
            });
            th.start();
        }
    }

    
    @FXML
    private void stepRun(ActionEvent event){
        this.stepRun_btn.setDisable(true);
        this.loopRun_btn.setDisable(true);
        this.cpu.swapPauseStage();
        this.cpu.resumeEmulation();
        this.cpu.setStepMode(true);
        Thread th = new Thread(this.cpu);
        threads.add(th);
        th.start();
        Thread th2 = new Thread(() -> {
            CPU_Stage stg = cpu.getNextStage();
            CPU_Stage stg0 = cpu.getNextStage();
            try{
                while(stg == stg0){
                    stg = cpu.getNextStage();
                    Thread.sleep(CPU.delay/3);
                }
                stepRun_btn.setDisable(false);
                loopRun_btn.setDisable(false);
                cpu.swapPauseStage();
            }catch(InterruptedException ex){}
        });
        th2.start();
    }
    
    @FXML
    private void newFile(){
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("TextEditor.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("LittleEmu - Editor de codigo");
            stage.setMaxHeight(695);
            stage.setMaxWidth(478);
            stage.setMinHeight(695);
            stage.setMinWidth(478);
            stage.show();
        }catch(Exception ex){       
        }
    }
    
    @FXML
    private void showHelp(ActionEvent event){
        try {
            java.awt.Desktop.getDesktop().browse(new java.net.URI("https://littleemu-app.blogspot.com/2020/12/littleemu-instruction-set-instruccion.html"));
        } catch (IOException | URISyntaxException ex) {
            
        }         
    }
    
    
    @FXML
    private void displayFilePicker(){
        Stage pickerStage = new Stage();
        Label titleLbl = new Label("Seleccione un archivo y presione cargar para empezar");
        titleLbl.setFont(new Font("Tahoma", 22));
        Label dirLbl = new Label("No se ha seleccionado un archivo");
        dirLbl.setTextFill(Color.web("#ef0400"));
        Button selectFileBtn = new Button("Buscar archivo");
        Button cargarBtn = new Button("Cargar archivo");
        Button demosBtn = new Button("      Demos     ");
        cargarBtn.setDisable(true);
        
        selectFileBtn.setOnAction((ActionEvent event) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Buscar un archivo");
            File selectedFile = fileChooser.showOpenDialog(pickerStage);
            if(selectedFile != null){
                demo = false;
                cargarBtn.setDisable(false);
                filePath = selectedFile.getAbsolutePath();
                dirLbl.setText(selectedFile.getAbsolutePath());
            }
        });
        
        cargarBtn.setOnAction((ActionEvent event) -> {
            if(demo){
                String st = dirLbl.getText();
                if(st.contains("DEMO1")){//sumar
                    loadDemo1();
                }else if(st.contains("DEMO2")){//restar
                    loadDemo2();
                }else if(st.contains("DEMO3")){//bucle
                    loadDemo3();
                }else if(st.contains("DEMO4")){//dividir
                    loadDemo4();
                }else if(st.contains("DEMO5")){//multiplicar
                    loadDemo5();
                }
                startCPU();
                pickerStage.close();
            }else{
                if(loadCode()){
                    startCPU();
                    pickerStage.close();
                }else{
                    Alert al = new Alert(AlertType.ERROR);
                    al.setTitle("Error al leer el archivo seleccionado");
                    al.setContentText("El formato del archivo cargado no es compatible");
                    al.showAndWait();
                }
            }
        });
        
        demosBtn.setOnAction((ActionEvent event) -> {
            Stage stg = new Stage();
            VBox root = new VBox();
            root.setAlignment(Pos.CENTER);
            Label demoLbl = new Label("Demos");
            demoLbl.setFont(new Font("Tahoma",22));
            Button sumBtn = new Button("Sumar dos números");
            Button subBtn =  new Button("Restar dos números");
            Button infLoopBtn = new Button("Bucle infinito");
            Button divBtn = new Button("Dividir dos números");
            Button mulBtn = new Button("Multiplicar dos números");
            
            sumBtn.setOnMouseClicked((Event event1) -> {
                dirLbl.setText("DEMO1: Sumar dos números");
                stg.close();
                demo = true;
                cargarBtn.setDisable(false);
            });
            subBtn.setOnMouseClicked((Event event1) -> {
                dirLbl.setText("DEMO2: Restar dos números");
                stg.close();
                demo = true;
                cargarBtn.setDisable(false);
            });
            infLoopBtn.setOnMouseClicked((Event event1) -> {
                dirLbl.setText("DEMO3: Bucle infinito");
                stg.close();
                demo = true;
                cargarBtn.setDisable(false);
            });
            divBtn.setOnMouseClicked((Event event1) -> {
                dirLbl.setText("DEMO4: Dividir dos números");
                stg.close();
                demo = true;
                cargarBtn.setDisable(false);
            });
            mulBtn.setOnMouseClicked((Event event1) -> {
                dirLbl.setText("DEMO5: Multiplicar dos números");
                stg.close();
                demo = true;
                cargarBtn.setDisable(false);
            });
            
            root.getChildren().addAll(demoLbl,sumBtn,subBtn,infLoopBtn,divBtn,mulBtn);
            root.setSpacing(30);
            Scene sc =  new Scene(root);
            stg.setScene(sc);
            stg.setMaxWidth(220);
            stg.setMaxHeight(450);
            stg.setMinWidth(220);
            stg.setMinHeight(450);
            stg.show();
        });
        
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        root.getChildren().addAll(titleLbl, dirLbl, selectFileBtn, demosBtn ,cargarBtn);
        Scene scene = new Scene(root, 600, 400);

        pickerStage.setTitle("Cargar programa en RAM");
        pickerStage.setScene(scene);
        pickerStage.show(); 
    }
    
    public boolean loadCode(){
        File fl = new File(this.filePath);
        try (BufferedReader br = new BufferedReader(new FileReader(fl))) {
            String line;
            int lineCounter = 0;
            while ((line = br.readLine()) != null) {
               if(line.length() > 8){
                   return false;
               }else{
                   for(int i = 0 ; i < 8; i++){
                       if(line.charAt(i) != '1' && line.charAt(i)!= '0'){
                           return false;
                       }
                   }
               }
               this.ram.setByAddress(lineCounter, line);
               lineCounter++;
            }
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    
    public void loadDemo1(){
        this.ram.resetAll();
        this.ram.setByAddress(0, "00101101");
        this.ram.setByAddress(1, "00011110");
        this.ram.setByAddress(2, "10000001");
        this.ram.setByAddress(3, "01001111");
        this.ram.setByAddress(4, "11110000");
        this.ram.setByAddress(13, "00011001");
        this.ram.setByAddress(14, "00001000");
    }
    public void loadDemo2(){
        this.ram.resetAll();
        this.ram.setByAddress(0, "00101101");
        this.ram.setByAddress(1, "00011110");
        this.ram.setByAddress(2, "10010001");
        this.ram.setByAddress(3, "01001111");
        this.ram.setByAddress(4, "11110000");
        this.ram.setByAddress(13, "01011010");
        this.ram.setByAddress(14, "00111011");
    }
    public void loadDemo3(){
        this.ram.resetAll();
        this.ram.setByAddress(0, "00101110");
        this.ram.setByAddress(1, "00011111");
        this.ram.setByAddress(2, "10010001");
        this.ram.setByAddress(3, "01001101");
        this.ram.setByAddress(4, "10100010");
        this.ram.setByAddress(5, "00111101");
        this.ram.setByAddress(6, "00001101");
        this.ram.setByAddress(7, "10001011");
        this.ram.setByAddress(8, "11110000");
        this.ram.setByAddress(14, "00001010");
        this.ram.setByAddress(15, "00001011");
    }
    
    public void loadDemo4(){
        this.ram.resetAll();
        this.ram.setByAddress(0, "00111100");
        this.ram.setByAddress(1, "00001101");
        this.ram.setByAddress(2, "00101110");
        this.ram.setByAddress(3, "00011111");
        this.ram.setByAddress(4, "10010001");
        this.ram.setByAddress(5, "10111000");
        this.ram.setByAddress(6, "10001011");
        this.ram.setByAddress(7, "10100100");
        this.ram.setByAddress(8, "01101100");
        this.ram.setByAddress(9, "10000001");
        this.ram.setByAddress(10, "01001101");
        this.ram.setByAddress(11, "11110000");
        this.ram.setByAddress(13, "00000001");
        this.ram.setByAddress(14, "01100001");
        this.ram.setByAddress(15, "00001010");
    }
    
    public void loadDemo5 (){
        this.ram.resetAll();
        this.ram.setByAddress(0, "00101110");//load_A 14
        this.ram.setByAddress(1, "00011110");//load_B 14
        this.ram.setByAddress(2, "00111111");//load_C 15
        this.ram.setByAddress(3, "00001101");//load_D 13
        this.ram.setByAddress(4, "10011011");//SUB C D
        this.ram.setByAddress(5, "11001000");//Jump_zro 8
        this.ram.setByAddress(6, "10000001");//add A B
        this.ram.setByAddress(7, "10100100");//jump 4
        this.ram.setByAddress(8, "01001100");//store_A 12
        this.ram.setByAddress(9, "11110000");//HALT
        this.ram.setByAddress(13, "00000001");
        this.ram.setByAddress(14, "00000111");
        this.ram.setByAddress(15, "00001000");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
