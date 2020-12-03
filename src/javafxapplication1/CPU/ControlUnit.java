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
public class ControlUnit {
    private ALU alu;
    private CpuRegister register1;
    private CpuRegister register2;
    private CpuRegister register3;
    private CpuRegister register4;
    
    private CpuRegister instructionRegister;
    private CpuRegister addressRegister;
    
    private RAM ram;
    
    private Boolean O_flag;
    private Boolean Z_flag;
    private Boolean N_flag;
    
    public ControlUnit(){
        this.O_flag = false;
        this.Z_flag = false;
        this.N_flag = false;  
    }
    
    public void setALU(ALU alu){
        this.alu = alu;
    }
    
    public void setRAM(RAM ram){
        this.ram = ram;
    }
    
    public void setRegisters(CpuRegister register1, CpuRegister register2, CpuRegister register3, CpuRegister register4){
        this.register1 = register1;
        this.register2 = register2;
        this.register3 = register3;
        this.register4 = register4;
    }
    
    public void setFlags(Boolean o_flag, Boolean z_flag, Boolean n_flag){
        this.O_flag = o_flag;
        this.Z_flag = z_flag;
        this.N_flag = n_flag;
    }
   
    public void resetFlags(){
        this.O_flag = false;
        this.Z_flag = false;
        this.N_flag = false;
    }
    
    public void swapAddressRegister(Boolean isBinaryOn){
        this.addressRegister.swapBase(isBinaryOn);
    }
    
    public void swapInstructionRegister(Boolean isBinaryOn){
        if(!isBinaryOn){
            this.instructionRegister.swapBase(isBinaryOn);
        }else{
            this.instructionRegister.showInText();
        }
    }
    
    public void setInstructionRegister(CpuRegister instructionRegister){
        this.instructionRegister = instructionRegister;
    }
    
    public void setAddressRegister(CpuRegister addressRegister){
        this.addressRegister = addressRegister;
    }
    
    
    public static Instruction getOpcode(Register reg){
        String st = reg.getBinaryValueAsString();
        String opcodeSt = "" + st.charAt(0) +  st.charAt(1) + st.charAt(2) + st.charAt(3);
        switch(opcodeSt){
                case("0000"):
                    return Instruction.LOAD_D;
                case("0001"):
                    return Instruction.LOAD_B;
                case("0010"):
                    return Instruction.LOAD_A;
                case("0011"):
                    return Instruction.LOAD_C;
                case("0100"):
                    return Instruction.STORE_A;
                case("0101"):
                    return Instruction.STORE_B;
                case("0110"):
                    return Instruction.STORE_C;
                case("0111"):
                    return Instruction.STORE_D;
                case("1000"):
                    return Instruction.ADD;
                case("1001"):
                    return Instruction.SUB;
                case("1010"):
                    return Instruction.JUMP;
                case("1011"):
                    return Instruction.JUMP_NEG;
                case("1100"):
                    return Instruction.JUMP_EQL;
                case("1101"):
                    return Instruction.JUMP_ABV;
                case("1110"):
                    return Instruction.JUMP_BLW;
                case("1111"):
                    return Instruction.HALT;
                default:
                    return null;
        }
    }
    
    public static String getInstructionAsString(NumberingSystem ns, Register reg){
        Instruction opcode = getOpcode(reg);
        String instruction = opcode.toString();
        if(opcode == Instruction.ADD && opcode == Instruction.SUB){
            String st = reg.getBinaryValueAsString();
            String op1 = "" + st.charAt(4) +  st.charAt(5);
            String op2 = "" + st.charAt(6) +  st.charAt(7);
            if(ns == NumberingSystem.OPCbin){
                instruction = instruction + "_" + op1 + "-" + op2;
            }else if(ns == NumberingSystem.OPCdec){
                if(op1.equals("00")){
                    instruction = instruction + "_" + "A";
                    if(op2.equals("00")){
                        instruction = instruction + "-" + "A";
                    }else if(op2.equals("01")){
                        instruction = instruction + "-" + "B";
                    }else if(op2.equals("10")){
                        instruction = instruction + "-" + "C";
                    }else if(op2.equals("11")){
                        instruction = instruction + "-" + "D";
                    }
                }else if(op1.equals("01")){
                    instruction = instruction + "_" + "B";
                    if(op2.equals("00")){
                        instruction = instruction + "-" + "A";
                    }else if(op2.equals("01")){
                        instruction = instruction + "-" + "B";
                    }else if(op2.equals("10")){
                        instruction = instruction + "-" + "C";
                    }else if(op2.equals("11")){
                        instruction = instruction + "-" + "D";
                    }
                }else if(op1.equals("10")){
                    instruction = instruction + "_" + "C";
                    if(op2.equals("00")){
                        instruction = instruction + "-" + "A";
                    }else if(op2.equals("01")){
                        instruction = instruction + "-" + "B";
                    }else if(op2.equals("10")){
                        instruction = instruction + "-" + "C";
                    }else if(op2.equals("11")){
                        instruction = instruction + "-" + "D";
                    }
                }else if(op1.equals("11")){
                    instruction = instruction + "_" + "D";
                    if(op2.equals("00")){
                        instruction = instruction + "-" + "A";
                    }else if(op2.equals("01")){
                        instruction = instruction + "-" + "B";
                    }else if(op2.equals("10")){
                        instruction = instruction + "-" + "C";
                    }else if(op2.equals("11")){
                        instruction = instruction + "-" + "D";
                    }
                }
            } 
        }else{
            if(opcode == Instruction.HALT){
                return instruction;
            }else{
                String st = reg.getBinaryValueAsString();
                String op = "" + st.charAt(4) +  st.charAt(5) +  st.charAt(6) +  st.charAt(7) ;
                if(ns == NumberingSystem.OPCbin){
                    instruction = instruction + "_" + op;
                }else if(ns == NumberingSystem.OPCdec){
                    if(op.equals("0000")){
                        instruction = instruction + "_" + "0";
                    }else if(op.equals("0001")){
                        instruction = instruction + "_" + "1";
                    }else if(op.equals("0010")){
                        instruction = instruction + "_" + "2";
                    }else if(op.equals("0011")){
                        instruction = instruction + "_" + "3";
                    }else if(op.equals("0100")){
                        instruction = instruction + "_" + "4";
                    }else if(op.equals("0101")){
                        instruction = instruction + "_" + "5";
                    }else if(op.equals("0110")){
                        instruction = instruction + "_" + "6";
                    }else if(op.equals("0111")){
                        instruction = instruction + "_" + "7";
                    }else if(op.equals("1000")){
                        instruction = instruction + "_" + "8";
                    }else if(op.equals("1001")){
                        instruction = instruction + "_" + "9";
                    }else if(op.equals("1010")){
                        instruction = instruction + "_" + "10";
                    }else if(op.equals("1011")){
                        instruction = instruction + "_" + "11";
                    }else if(op.equals("1100")){
                        instruction = instruction + "_" + "12";
                    }else if(op.equals("1101")){
                        instruction = instruction + "_" + "13";
                    }else if(op.equals("1110")){
                        instruction = instruction + "_" + "14";
                    }else if(op.equals("1111")){
                        instruction = instruction + "_" + "15";
                    }
                }
            }
        }
        return instruction;
    }
   
    
}
