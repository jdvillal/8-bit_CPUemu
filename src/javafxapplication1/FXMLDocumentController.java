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
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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
import javafxapplication1.CPU.RAM;
import javafxapplication1.CPU.RamRegister;

/**
 *
 * @author Jorge
 */
public class FXMLDocumentController implements Initializable {
    CPU cpu = new CPU();
    RAM ram = new RAM();
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
    @FXML//registro B
    private Label regB_lbl; 
    @FXML
    private Rectangle regB_rec;
    @FXML//registro C
    private Label regC_lbl;
    @FXML
    private Rectangle regC_rec;
    @FXML//registro D
    private Label regD_lbl;
    @FXML
    private Rectangle regD_rec;
    
    @FXML//registro de instruccion
    private Label instructionReg_lbl;
    @FXML
    private Rectangle instructionReg_rec ;
    @FXML//registro de direccion
    private Label addressReg_lbl;
    @FXML
    private Rectangle addressReg_rec;
    
    //****************RAM REGISTERS********************
    @FXML//
    private Label addr0_lbl;
    @FXML//
    private Label addr0Num_lbl;
    @FXML
    private Rectangle addr0_rec;
    
    @FXML//
    private Label addr1_lbl;
    @FXML//
    private Label addr1Num_lbl;
    @FXML
    private Rectangle addr1_rec;
    
    @FXML//
    private Label addr2_lbl;
    @FXML//
    private Label addr2Num_lbl;
    @FXML
    private Rectangle addr2_rec;
    
    @FXML//
    private Label addr3_lbl;
    @FXML//
    private Label addr3Num_lbl;
    @FXML
    private Rectangle addr3_rec;
    
    @FXML//
    private Label addr4_lbl;
    @FXML//
    private Label addr4Num_lbl;
    @FXML
    private Rectangle addr4_rec;
    
    @FXML//
    private Label addr5_lbl;
    @FXML//
    private Label addr5Num_lbl;
    @FXML
    private Rectangle addr5_rec;
    
    @FXML//
    private Label addr6_lbl;
    @FXML//
    private Label addr6Num_lbl;
    @FXML
    private Rectangle addr6_rec;
    
    @FXML//
    private Label addr7_lbl;
    @FXML//
    private Label addr7Num_lbl;
    @FXML
    private Rectangle addr7_rec;
    
    @FXML//
    private Label addr8_lbl;
    @FXML//
    private Label addr8Num_lbl;
    @FXML
    private Rectangle addr8_rec;
    
    @FXML//
    private Label addr9_lbl;
    @FXML//
    private Label addr9Num_lbl;
    @FXML
    private Rectangle addr9_rec;
    
    @FXML//
    private Label addr10_lbl;
    @FXML//
    private Label addr10Num_lbl;
    @FXML
    private Rectangle addr10_rec;
    
    @FXML//
    private Label addr11_lbl;
    @FXML//
    private Label addr11Num_lbl;
    @FXML
    private Rectangle addr11_rec;
    
    @FXML//
    private Label addr12_lbl;
    @FXML//
    private Label addr12Num_lbl;
    @FXML
    private Rectangle addr12_rec;
    
    @FXML//
    private Label addr13_lbl;
    @FXML//
    private Label addr13Num_lbl;
    @FXML
    private Rectangle addr13_rec;
    
    @FXML//
    private Label addr14_lbl;
    @FXML//
    private Label addr14Num_lbl;
    @FXML
    private Rectangle addr14_rec;
    
    @FXML//
    private Label addr15_lbl;
    @FXML//
    private Label addr15Num_lbl;
    @FXML
    private Rectangle addr15_rec;
    
    
    
    
    //*********ADDRES AND ENABLE BUSES GUI LINES********
    @FXML
    private Line data1_line;
    @FXML
    private Line adata2_line;
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
    private void startCPU(ActionEvent event) {
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
    }
    
    @FXML
    private void swapRamAddressBase(){
        ram.swapRamAddressBase();
        ram.update();
    }
    
    @FXML
    private void swapRamContentBase_0(){
        ram.getByAddress(0).swapContentBase();
        ram.getByAddress(0).update();
    }
    @FXML
    private void swapRamContentBase_1(){
        ram.getByAddress(1).swapContentBase();
        ram.getByAddress(1).update();
    }
    @FXML
    private void swapRamContentBase_2(){
        ram.getByAddress(2).swapContentBase();
        ram.getByAddress(2).update();
    }
    @FXML
    private void swapRamContentBase_3(){
        ram.getByAddress(3).swapContentBase();
        ram.getByAddress(3).update();
    }
    @FXML
    private void swapRamContentBase_4(){
        ram.getByAddress(4).swapContentBase();
        ram.getByAddress(4).update();
    }
    @FXML
    private void swapRamContentBase_5(){
        ram.getByAddress(5).swapContentBase();
        ram.getByAddress(5).update();
    }
    @FXML
    private void swapRamContentBase_6(){
        ram.getByAddress(6).swapContentBase();
        ram.getByAddress(6).update();
    }
    @FXML
    private void swapRamContentBase_7(){
        ram.getByAddress(7).swapContentBase();
        ram.getByAddress(7).update();
    }
    @FXML
    private void swapRamContentBase_8(){
        ram.getByAddress(8).swapContentBase();
        ram.getByAddress(8).update();
    }
    @FXML
    private void swapRamContentBase_9(){
        ram.getByAddress(9).swapContentBase();
        ram.getByAddress(9).update();
    }
    @FXML
    private void swapRamContentBase_10(){
        ram.getByAddress(10).swapContentBase();
        ram.getByAddress(10).update();
    }
    @FXML
    private void swapRamContentBase_11(){
        ram.getByAddress(11).swapContentBase();
        ram.getByAddress(11).update();
    }
    @FXML
    private void swapRamContentBase_12(){
        ram.getByAddress(12).swapContentBase();
        ram.getByAddress(12).update();
    }
    @FXML
    private void swapRamContentBase_13(){
        ram.getByAddress(13).swapContentBase();
        ram.getByAddress(13).update();
    }
    @FXML
    private void swapRamContentBase_14(){
        ram.getByAddress(14).swapContentBase();
        ram.getByAddress(14).update();
    }
    @FXML
    private void swapRamContentBase_15(){
        ram.getByAddress(15).swapContentBase();
        ram.getByAddress(15).update();
    }
    
    
    
    
    
    
    @FXML
    private void swapRegisters(ActionEvent event){
        cpu.swapRegistersBase(cpuRegisters_isBinaryOn);
        this.cpuRegisters_isBinaryOn = !this.cpuRegisters_isBinaryOn;
    }
    
    @FXML
    private void swapAddressRegister(ActionEvent event){
        cpu.swapAddressRegisterBase(this.addresRegister_isBinaryOn);
        this.addresRegister_isBinaryOn = !this.addresRegister_isBinaryOn;
    }
    
    @FXML
    private void swapInstructionRegister(ActionEvent event){
        cpu.swapInstructionRegisterBase(this.instructionRegister_isBinaryOn);
        this.instructionRegister_isBinaryOn = !this.instructionRegister_isBinaryOn;
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
