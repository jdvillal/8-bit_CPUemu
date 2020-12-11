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
public class LineAnimator extends Thread{
    private ArrayList<Line> bus;
    private Boolean setReverse;
    
    public void setBusAndSence(ArrayList<Line> bus, Boolean setReverse){
        this.bus = bus;
        this.setReverse = setReverse;
    }
    
    @Override
    public void run() {
        if(!setReverse){
            for(Line l : this.bus){
                try{
                    sleep(3000);
                    l.setStyle("-fx-stroke: ff9f00;");
                }catch(Exception ex){
                }
            }
        }else{
            for(int i = this.bus.size()-1; i >= 0; i--){
                try{
                    sleep(150);
                    this.bus.get(i).setStyle("-fx-stroke: ff9f00;");
                }catch(Exception ex){
                }
            }
        }
        this.setReverse = false;
    }
    
}
