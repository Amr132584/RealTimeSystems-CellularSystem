/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cellular.phone.control.system;

import GUI.CellOption;
import GUI.MainClass;
import GUI.NoSignal;
import javax.swing.JFrame;

/**
 *
 * @author omar1
 */
public class Transmitter {
    
    public void lostSignal()
    {
        
        NoSignal s=new NoSignal();
     // s.setVisible(true);
        MainClass.phone.getScreen().setVisible(false);
        MainClass.phone.getCellScreen().setVisible(false);
       CellOption.screenC.setVisible(false);
        
        
    }
}
