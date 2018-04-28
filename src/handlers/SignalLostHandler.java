/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import GUI.MainClass;
import cellular.phone.control.system.Phone;
import events.SignalLost;

/**
 *
 * @author omar1
 */
public class SignalLostHandler {
    private Phone phone;

    public SignalLostHandler(Phone phone) {
        this.phone = phone;
    }
    public void update(SignalLost event)
    {
        MainClass.phone.getTransmitter().lostSignal();
    }
}
