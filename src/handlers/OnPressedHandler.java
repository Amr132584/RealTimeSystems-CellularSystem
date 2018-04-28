/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import GUI.MainClass;
import cellular.phone.control.system.Phone;
import events.OnPressed;

/**
 *
 * @author omar1
 */
public class OnPressedHandler {
    private Phone phone;

    public OnPressedHandler(Phone phone) {
        this.phone = phone;
    }
    public void update(OnPressed event) throws InterruptedException {
          System.out.println("In OnPressed Handler");
        if(MainClass.phone.getCurrentState().equals("Off")||MainClass.phone.getCurrentState().equals("No Signal"))
        {
            
            MainClass.phone.scanForSignal();
        }
        else
        {
            
        }
        
    }
}
