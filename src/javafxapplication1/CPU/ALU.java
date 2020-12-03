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
public class ALU {
    private ControlUnit controlUnit;
    
    public ALU(ControlUnit controlUnit){
        this.controlUnit = controlUnit;
    }
    
    public void setControlUnit(ControlUnit controlUnit){
        this.controlUnit = controlUnit;
    }

    
    public RamRegister operate(RamRegister input1, RamRegister input2, Instruction opcode){
        switch (opcode) {
            case ADD:
                return SUM(input1,input2);
            case SUB:
                return SUB(input1,input2);
            default:
                return null;
        }
    }  
    
    private RamRegister SUM(RamRegister b1, RamRegister b2){
        int input1 = Integer.parseInt(b1.getBinaryValueAsString());
        int input2 = Integer.parseInt(b2.getBinaryValueAsString());
        Integer output = input1 + input2;
        Boolean o_flag = false;
        Boolean z_flag = false;
        Boolean n_flag = false;
        if(output > 127 || output < -128){
            o_flag = true;
        }
        if(output == 0){
            z_flag = true;
        }
        if(output < 0){
            n_flag = true;
        }
        this.controlUnit.setFlags(o_flag, z_flag, n_flag);
        return new RamRegister(output.toString());  
    }
    
    private RamRegister SUB(RamRegister b1, RamRegister b2){
        int input1 = Integer.parseInt(b1.getBinaryValueAsString());
        int input2 = Integer.parseInt(b2.getBinaryValueAsString());
        Integer output = input1 - input2;
        Boolean o_flag = false;
        Boolean z_flag = false;
        Boolean n_flag = false;
        if(output > 127 || output < -128){
            o_flag = true;
        }
        if(output == 0){
            z_flag = true;
        }
        if(output < 0){
            n_flag = true;
        }
        this.controlUnit.setFlags(o_flag, z_flag, n_flag);
        return new RamRegister(output.toString());  
    }
    
}
