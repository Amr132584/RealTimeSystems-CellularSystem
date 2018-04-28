/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;/////////GitHub

import cellular.phone.control.system.Cell;
import cellular.phone.control.system.Phone;
import cellular.phone.control.system.Receiver;
import cellular.phone.control.system.Transmitter;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import esper.config.Config;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author omar1
 */
public class MainClass {
     
      public static EPServiceProvider engine;
      public static Phone phone;
      public static Cell cell;
      public static void main(String[] args) {
        //... Set the Look and Feel to that of system we're running on.
      
        phone = new Phone(new Cell(), new Receiver(), new Transmitter());
       engine = EPServiceProviderManager.getDefaultProvider();
       new Config(phone).init();
     /*   try {
            
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception unused) {
            ; // Ignore exception because we can't do anything.  Will use default.
        }*/
        
        //... Create the window.
        JFrame window = phone.getScreen();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
