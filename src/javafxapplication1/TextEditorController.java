/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jorge
 */
public class TextEditorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextArea code_TextArea;

    @FXML
    private Button save_btn;

    @FXML
    private Button insect_btn;

    @FXML
    private Button load_btn;

    @FXML
    private Button help_btn;

    @FXML
    public void drawSavePane(ActionEvent event) {
        Stage st = new Stage();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(st);

        if (file != null) {
            this.saveFile(this.code_TextArea.getText(), file);
        }
    }

    @FXML
    private void showHelp(ActionEvent event) {
        try {
            java.awt.Desktop.getDesktop().browse(new java.net.URI("https://littleemu-app.blogspot.com/2020/12/littleemu-instruction-set-instruccion.html"));
        } catch (IOException | URISyntaxException ex) {

        }
    }

    private void saveFile(String content, File file) {
        try {
            FileWriter fileWriter = null;
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
        }
    }

    @FXML
    public void inspect(ActionEvent event) {
        ArrayList<String> code = new ArrayList<>();
        String text = this.format(this.getCode()).toUpperCase();
        String[] lines = text.split("\n");
        int lineCount = lines.length;
        for (int i = 0; i < lineCount; i++) {
            String[] lineParts = lines[i].split(" ");
            if (lineParts.length != 2) {
                if(!lineParts[0].equals("HALT")){
                    this.throwErrorAlert("Error de sintaxis en la linea: " + lines[i]);
                    return;
                }
                
            }
            String opcode = lineParts[0];
            if (opcode.equals("ADD") || opcode.equals("SUB")) {
                if (opcode.equals("ADD")) {
                    code.add(i, "1000");
                } else if (opcode.equals("SUB")) {
                    code.add(i, "1001");
                }
                String[] arguments = lineParts[1].split(",");
                for (int j = 0; j < 2; j++) {
                    if (arguments[j].length() == 1) {//Si el primer argumento es la letra de un registro
                        if (!arguments[j].equals("A") && !arguments[j].equals("B") && !arguments[j].equals("C") && !arguments[j].equals("D")) {
                            this.throwErrorAlert("Referenciando un registro invalido en: " + lines[i]);
                            return;
                        } else {
                            if (arguments[j].equals("A")) {
                                code.set(i, code.get(i) + "00");
                            } else if (arguments[j].equals("B")) {
                                code.set(i, code.get(i) + "01");
                            } else if (arguments[j].equals("C")) {
                                code.set(i, code.get(i) + "10");
                            } else if (arguments[j].equals("D")) {
                                code.set(i, code.get(i) + "11");
                            }
                        }
                    } else {// Si el primer argumento esta dado en un sistema numerico
                        char ns = arguments[j].charAt(arguments[j].length() - 1);
                        String regSt = arguments[j].split(ns + "")[0];
                        if (ns != 'B' && ns != 'D' && ns != 'H') {
                            this.throwErrorAlert("Sistema numérico desconocido en: " + lines[i]);
                            return;
                        } else {
                            if (ns == 'B') {
                                if (regSt.length() > 2) {
                                    this.throwErrorAlert("Solo 4 registros disponibles(00,01,10,11)binary \nReferenciando un registro con más de dos dígitos en formato binario en la linea: " + lines[i]);
                                    return;
                                }
                                String toAdd = this.fillZeros(regSt, ns, 2, lines[i]);
                                if (toAdd != null) {
                                    code.set(i, code.get(i) + toAdd);
                                } else {
                                    return;
                                }
                            } else if (ns == 'D') {
                                if (regSt.length() > 1) {
                                    this.throwErrorAlert("Solo 4 registros disponibles(0,1,2,3)decimal \nReferenciando un registro con más de dos dígitos en formato decimal en la linea: " + lines[i]);
                                    return;
                                } else {
                                    if (this.isValidDecimal(regSt)) {
                                        if (Integer.parseInt(regSt) > 3) {
                                            this.throwErrorAlert("Solo 4 registros disponibles (0,1,2,3)decimal \nReferenciando un registro no existente en la linea: " + lines[i]);
                                            return;
                                        }
                                    }else{
                                        this.throwErrorAlert("Digito no decimal encontrado en la linea: " + lines[i]);
                                        return;
                                    }
                                }
                                String toAdd = this.fillZeros(regSt, ns, 1, lines[i]);
                                if (toAdd != null) {
                                    code.set(i, code.get(i) + toAdd);
                                } else {
                                    return;
                                }
                            } else if (ns == 'H') {
                                if (regSt.length() > 1) {
                                    this.throwErrorAlert("Solo 4 registros disponibles(0,1,2,3)hexadecimal \nReferenciando un registro con más de dos dígitos en formato hexadecimal en la linea: " + lines[i]);
                                    return;
                                } else {
                                    if (regSt.equals("4") || regSt.equals("5") || regSt.equals("6") || regSt.equals("7") || regSt.equals("8") || regSt.equals("9") || regSt.equals("A") || regSt.equals("B")
                                            || regSt.equals("C") || regSt.equals("D") || regSt.equals("E") || regSt.equals("F")) {
                                        this.throwErrorAlert("Solo 4 registros disponibles (0,1,2,3)hexadecimal \nReferenciando un registro no existente en la linea: " + lines[i]);
                                        return;
                                    }
                                    if (Integer.parseInt(regSt) > 3) {
                                        this.throwErrorAlert("Solo 4 registros disponibles (0,1,2,3)hexadecimal \nReferenciando un registro no existente en la linea: " + lines[i]);
                                        return;
                                    }
                                }
                                String toAdd = this.fillZeros(regSt, ns, 1, lines[i]);
                                if (toAdd != null) {
                                    code.set(i, code.get(i) + toAdd);
                                } else {
                                    return;
                                }
                            }

                        }
                    }
                }
            } else if (opcode.equals("JUMP") || opcode.equals("JUMP_NEG") || opcode.equals("JUMP_ZRO") || opcode.equals("JUMP_ABV") || opcode.equals("JUMP_OFW")
                    || opcode.equals("LOAD_A") || opcode.equals("LOAD_B") || opcode.equals("LOAD_C") || opcode.equals("LOAD_D")
                    || opcode.equals("STORE_A") || opcode.equals("STORE_B") || opcode.equals("STORE_C") || opcode.equals("STORE_D")) {
                if (opcode.equals("JUMP")) {
                    code.add(i, "1010");
                } else if (opcode.equals("JUMP_NEG")) {
                    code.add(i, "1011");
                } else if (opcode.equals("JUMP_ZRO")) {
                    code.add(i, "1100");
                } else if (opcode.equals("JUMP_ABV")) {
                    code.add(i, "1101");
                } else if (opcode.equals("JUMP_OFW")) {
                    code.add(i, "1110");
                } else if (opcode.equals("LOAD_A")) {
                    code.add(i, "0010");
                } else if (opcode.equals("LOAD_B")) {
                    code.add(i, "0001");
                } else if (opcode.equals("LOAD_C")) {
                    code.add(i, "0011");
                } else if (opcode.equals("LOAD_D")) {
                    code.add(i, "0000");
                } else if (opcode.equals("STORE_A")) {
                    code.add(i, "0100");
                } else if (opcode.equals("STORE_B")) {
                    code.add(i, "0101");
                } else if (opcode.equals("STORE_C")) {
                    code.add(i, "0110");
                } else if (opcode.equals("STORE_D")) {
                    code.add(i, "0111");
                }
                char base = lineParts[1].charAt(lineParts[1].length() - 1);
                String dirSt = lineParts[1].split(base + "")[0];
                if (base == 'B') {
                    if (dirSt.length() > 4) {
                        this.throwErrorAlert("LittleEmu Memory Range: (0000-1111)binary\nSe está intentando direccionar memoria con más de 4 digitos en formato binario en la linea: " + lines[i]);
                        return;
                    }
                    String toAdd = this.fillZeros(dirSt, base, 4, lines[i]);
                    if (toAdd != null) {
                        code.set(i, code.get(i) + toAdd);
                    } else {
                        return;
                    }
                } else if (base == 'D') {
                    if (dirSt.length() > 2) {
                        this.throwErrorAlert("LittleEmu Memory Range: (0-15)decimal\nSe está intentando direccionar memoria con más de 2 digitos en formato decimal en la linea: " + lines[1]);
                        return;
                    }else{
                        if(this.isValidDecimal(dirSt)){
                            if(Integer.parseInt(dirSt) > 15){
                                this.throwErrorAlert("LittleEmu Memory Range: (0-15)decimal\nSe está intentando direccionar memoria fuera del rango en la linea:" + lines[i]);
                                return;
                            }
                        }else{
                            this.throwErrorAlert("Dígito no decimal encontrado en la linea: "+ lines[i]);
                            return;
                        }
                    }
                    String toAdd = this.fillZeros(dirSt, base, 2, lines[i]);
                    if (toAdd != null) {
                        code.set(i, code.get(i) + this.getIntBinaryAsString(Integer.parseInt(dirSt)));
                    } else {
                        return;
                    }
                } else if (base == 'H') {
                    if(dirSt.length() > 1){
                        this.throwErrorAlert("LittleEmu Memory Range: (0-F)hexadecimal\nSe está intentando direccionar memoria con más de 1 dígito en formato hexadecimal en la linea: "+ lines[i]);
                        return;
                    }
                    String toAdd = this.fillZeros(dirSt, base, 1, lines[i]);
                    if (toAdd != null) {
                        code.set(i, code.get(i) + this.getHexBynaryAsString(toAdd));
                    } else {
                        return;
                    }
                }else{
                    this.throwErrorAlert("El sufijo que identifica el formato de la dirección ha sido omitido o es incorrecto.\nLinea: "+lines[i]);
                    return;
                }
            }else if(opcode.equals("HALT")){
                System.out.println(lineParts.length);
                if(lineParts.length > 1){
                    this.throwErrorAlert("Error de sintaxis en la linea: "+lines[i]+"\nLa instrucción HALT no debe recibir parametros.");
                }else{
                    code.add("11110000");
                }
            }else{
                this.throwErrorAlert("OPCODE desconocido, la instrucción no es válida.\nLinea: "+ lines[i]);
            }
        }
        System.out.println(code);
        
    }
    
    public String getIntBinaryAsString(int i){
        switch (i) {
            case 0:
                return "0000";
            case 1:
                return "0001";
            case 2:
                return "0010";
            case 3:
                return "0011";
            case 4:
                return "0100";
            case 5:
                return "0101";
            case 6:
                return "0110";
            case 7:
                return "0111";
            case 8:
                return "1000";
            case 9:
                return "1001";
            case 10:
                return "1010";
            case 11:
                return "1011";
            case 12:
                return "1100";
            case 13:
                return "1101";
            case 14:
                return "1110";
            case 15:
                return "1111";
            default:
                return null;
        }      
    }
    
    public String getHexBynaryAsString(String hex){
        switch (hex) {
            case "0":
                return "0000";
            case "1":
                return "0001";
            case "2":
                return "0010";
            case "3":
                return "0011";
            case "4":
                return "0100";
            case "5":
                return "0101";
            case "6":
                return "0110";
            case "7":
                return "0111";
            case "8":
                return "1000";
            case "9":
                return "1001";
            case "A":
                return "1010";
            case "B":
                return "1011";
            case "C":
                return "1100";
            case "D":
                return "1101";
            case "E":
                return "1110";
            case "F":
                return "1111";
            default:
                return null;
        }  
    }

    public String fillZeros(String st, char base, int size, String line) {
        String toReturn = "";
        switch (base) {
            case 'B':
                int len = st.length();
                if (this.isValidBinary(st)) {
                    for (int i = 0; i < size - len; i++) {
                        toReturn = toReturn + "0";
                    }
                    toReturn = toReturn + st;
                    return toReturn;
                } else {
                    this.throwErrorAlert("Dígito no binario encontrado en la linea: " + line);
                    return null;
                }
            case 'D':
                if (this.isValidDecimal(st)) {
                    if (st.length() == 1) {
                        toReturn = "0";
                    }
                    toReturn = toReturn + st;
                    return toReturn;
                } else {
                    this.throwErrorAlert("Dígito no decimal encontrado en la linea: " + line);
                    return null;
                }
            case 'H':
                if (this.isValidHex(st)) {
                    return st;
                } else {
                    this.throwErrorAlert("Dígito no hexadecimal encontrado en la linea: " + line);
                    return null;
                }
            default:
                return null;
        }
    }

    public void throwErrorAlert(String error) {
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle("Error al ensamblar el codigo");
        al.setContentText(error);
        al.showAndWait();
    }

    public boolean isValidBinary(String st) {
        System.out.println(st);
        for (int i = 0; i < st.length(); i++) {
            if (st.charAt(i) != '0' && st.charAt(i) != '1') {
                return false;
            }
        }
        return true;
    }

    public boolean isValidDecimal(String st) {
        for (int i = 0; i < st.length(); i++) {
            char c = st.charAt(i);
            if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' && c != '8' && c != '9') {
                return false;
            }
        }
        return true;
    }

    public boolean isValidHex(String st) {
        for (int i = 0; i < st.length(); i++) {
            char c = st.charAt(i);
            if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7'
                    && c != '8' && c != '9' && c != 'A' && c != 'B' && c != 'C' && c != 'D' && c != 'E' && c != 'F') {
                return false;
            }
        }
        return true;
    }

    public void formatCode(ActionEvent event) {
        this.code_TextArea.setText(this.format((this.code_TextArea.getText()).toUpperCase()));
    }

    public String format(String st) {
        LinkedList<Character> ls = new LinkedList<>();
        for (int i = 0; i < st.length(); i++) {
            ls.add(st.charAt(i));
        }
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i) == ' ') {
                try {
                    if (ls.get(i + 1) == '\n') {
                        ls.remove(i);
                        i = i - 1;
                    } else if (ls.get(i - 1) == ',') {
                        ls.remove(i);
                        i = i - 1;
                    }
                } catch (Exception ex) {
                }

            }
        }
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i) == ',') {
                try {
                    if (ls.get(i + 1) == ',') {
                        ls.remove(i);
                        i = i - 1;
                    }
                } catch (Exception ex) {
                }

            }
        }
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i) == '\n') {
                try {
                    if (ls.get(i + 1) == ' ') {
                        ls.remove(i + 1);
                        i = i - 1;
                    }
                } catch (Exception ex) {
                }
            }
        }
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i) == '\n') {
                try {
                    if (ls.get(i + 1) == '\n') {
                        ls.remove(i);
                        i = i - 1;
                    }
                } catch (Exception ex) {
                }
            }
        }
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i) == ' ') {
                try {
                    if (ls.get(i + 1) == ' ') {
                        ls.remove(i);
                        i = i - 1;
                    } else if (ls.get(i + 1) == ',') {
                        ls.remove(i);
                        i = i - 1;
                    }
                } catch (Exception ex) {
                }

            }
        }
        String toReturn = "";
        for (Character c : ls) {
            toReturn = toReturn + c;
        }
        return toReturn;
    }

    public String getCode() {
        return this.code_TextArea.getText();
    }

    public int countLines(String st) {
        boolean endText = false;
        int count = 0;
        for (int i = 0; i < st.length(); i++) {
            if (st.charAt(i) == '\n') {
                count = count + 1;
            }
        }
        return count;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
