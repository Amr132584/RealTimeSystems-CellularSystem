/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import GUI.MainClass;
import cellular.phone.control.system.Phone;
import events.CallRequest;

/**
 *
 * @author omar1
 */
public class CallRequestHandler {

    private final Phone phone;

    public CallRequestHandler(Phone phone) {
        this.phone = phone;
    }
    public void update(String number) throws InterruptedException
    {
        MainClass.phone.calling(number);
        
    }
    
}