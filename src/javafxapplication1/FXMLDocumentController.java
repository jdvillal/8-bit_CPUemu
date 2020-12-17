/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafxapplication1.CPU.CPU;
import javafxapplication1.CPU.CpuRegister;
import javafxapplication1.CPU.GUI_BusAnimator;
import javafxapplication1.CPU.NumberingSystem;
import javafxapplication1.CPU.RAM;
import javafxapplication1.CPU.RamRegister;

/**
 *
 * @author Jorge
 */
public class FXMLDocumentController implements Initializable {
    private CPU cpu = new CPU();
    private RAM ram = new RAM();
    private GUI_BusAnimator  bs_animator;
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
    
    

    //@FXML
    private void startCPU() {
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
    private void line(ActionEvent event){
        this.cpu.setAnimator(this.bs_animator);
        Thread th = new Thread(this.cpu);
        th.start();
    }
    
    @FXML
    private void newFile(){
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("TextEditor.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("LittleEmu - Editor de codigo");
            stage.show();
        }catch(Exception ex){
        
        }
    }
    
    @FXML
    private void displayFilePicker(){
        Stage primaryStage = new Stage();
        Label titleLbl = new Label("Seleccione un archivo y presione cargar para empezar");
        titleLbl.setFont(new Font("Tahoma", 22));
        Label dirLbl = new Label("No se ha seleccionado un archivo");
        Button selectFileBtn = new Button();
        selectFileBtn.setText("Buscar archivo");
        Button cargarBtn = new Button();
        cargarBtn.setText("Cargar Archivo");
        
        cargarBtn.setDisable(true);
        
        selectFileBtn.setOnAction((ActionEvent event) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Buscar un archivo de texto");
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile == null) {
                System.out.println("No File selected");
            } else {
                cargarBtn.setDisable(false);
                filePath = selectedFile.getAbsolutePath();
                dirLbl.setText(selectedFile.getAbsolutePath());
            }
        });
        
        cargarBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(uploadCode()){
                    startCPU();
                    primaryStage.close();
                }else{
                    Alert al = new Alert(AlertType.ERROR);
                    al.setTitle("Error al leer el directorio seleccionado");
                    al.setContentText("El formato del archivo cargado no es compatible");
                    al.showAndWait();  
                }
            }
        });
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        root.getChildren().addAll(titleLbl, dirLbl, selectFileBtn, cargarBtn);
        Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("Cargar programa en RAM");
        primaryStage.setScene(scene);
        primaryStage.show(); 
    }
    
    public boolean uploadCode(){
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
