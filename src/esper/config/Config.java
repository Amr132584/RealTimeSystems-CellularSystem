/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esper.config;

import cellular.phone.control.system.Phone;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import events.CallRequest;
import events.CellFounded;
import events.CellFullCapacity;
import events.EndEntered;
import events.IncomingCall;
import events.LongPress;
import events.NoCellFounded;
import events.NumberEntered;
import events.OffPressed;
import events.OnPressed;
import events.OutOFRange;
import events.PositiveResponseRecieved;
import events.SendEntered;
import events.SignalLost;
import events.TerminationRequestCell;
import events.TerminationRequestPhone;
import handlers.CallRequestHandler;
import handlers.CellFoundedHandler;
import handlers.CellFullCapacityHandler;
import handlers.EndEnteredHandler;
import handlers.*;

/**
 *
 * @author omar1
 */
public class Config {

    private EPServiceProvider engine;
private Phone phone;
    
     public Config(Phone phone) {
        this.phone = this.phone;
    }
    public void init() {
        System.err.println("\n\n************************");
        engine = EPServiceProviderManager.getDefaultProvider();
        registerEvents();
        createEPLs();
        System.err.println("************************\n\n");
    }

    private void registerEvents() {
        System.out.println("Registering Events...");
        engine.getEPAdministrator().getConfiguration().addEventType(CallRequest.class);
        engine.getEPAdministrator().getConfiguration().addEventType(CellFounded.class);
        engine.getEPAdministrator().getConfiguration().addEventType(CellFullCapacity.class);
        engine.getEPAdministrator().getConfiguration().addEventType(EndEntered.class);
        engine.getEPAdministrator().getConfiguration().addEventType(LongPress.class);
        engine.getEPAdministrator().getConfiguration().addEventType(NoCellFounded.class);
        engine.getEPAdministrator().getConfiguration().addEventType(NumberEntered.class);
        engine.getEPAdministrator().getConfiguration().addEventType(OffPressed.class);
        engine.getEPAdministrator().getConfiguration().addEventType(OnPressed.class);
        engine.getEPAdministrator().getConfiguration().addEventType(OutOFRange.class);
        engine.getEPAdministrator().getConfiguration().addEventType(PositiveResponseRecieved.class);
        engine.getEPAdministrator().getConfiguration().addEventType(SendEntered.class);
        engine.getEPAdministrator().getConfiguration().addEventType(SignalLost.class);
        engine.getEPAdministrator().getConfiguration().addEventType(TerminationRequestCell.class);
        engine.getEPAdministrator().getConfiguration().addEventType(TerminationRequestPhone.class);
        engine.getEPAdministrator().getConfiguration().addEventType(IncomingCall.class);
        System.out.println("Registering Events [DONE]");
    }
     private void createEPLs() {
        System.out.println("Creating EPLs...");
      createEPL_CallRequest();/*
        createEPL_CellFounded();
        createEPL_CellFullCapacity();*/
        createEPL_EndEntered();
        /*createEPL_LongPress();
        createEPL_NoCellFounded();
        createEPL_NumberEntered();*/
        createEPL_OffPressed();
        createEPL_OnPressed();
      // createEPL_OutOFRange();
        createEPL_PositiveResponseRecieved();
       /* createEPL_SendEntered();*/
        createEPL_SignalLost();
       /* createEPL_TerminationRequestCell();
        createEPL_TerminationRequestPhone();*/
        createEPL_IncomingCall();
        System.out.println("Creating EPLs [DONE]");
    }
     
     
  private void createEPL_CallRequest() {
        EPStatement statement = engine.getEPAdministrator().createEPL(CallRequest.getStatement());
        statement.setSubscriber(new CallRequestHandler(phone));
    }
/*
    private void createEPL_CellFounded() {
        EPStatement statement = engine.getEPAdministrator().createEPL(CellFounded.getStatement());
        statement.setSubscriber(new CellFoundedHandler(phone));
    }

    private void createEPL_CellFullCapacity() {
        EPStatement statement = engine.getEPAdministrator().createEPL(CellFullCapacity.getStatement());
        statement.setSubscriber(new CellFullCapacityHandler(phone));
    }
*/
    private void createEPL_EndEntered() {
        EPStatement statement = engine.getEPAdministrator().createEPL(EndEntered.getStatement());
        statement.setSubscriber(new EndEnteredHandler(phone));
    }
/*
    private void createEPL_LongPress() {
        EPStatement statement = engine.getEPAdministrator().createEPL(LongPress.getStatement());
        statement.setSubscriber(new LongPressHandler(phone));
    }
    
       private void createEPL_NoCellFounded() {
        EPStatement statement = engine.getEPAdministrator().createEPL(NoCellFounded.getStatement());
        statement.setSubscriber(new NoCellFoundedHandler(phone));
    }

    private void createEPL_NumberEntered() {
        EPStatement statement = engine.getEPAdministrator().createEPL(NumberEntered.getStatement());
        statement.setSubscriber(new NumberEnteredHandler(phone));
    }
*/
    private void createEPL_OffPressed() {
        EPStatement statement = engine.getEPAdministrator().createEPL(OffPressed.getStatement());
        statement.setSubscriber(new OffPressedHandler(phone));
    }

    private void createEPL_OnPressed() {
        EPStatement statement = engine.getEPAdministrator().createEPL(OnPressed.getStatement());
        statement.setSubscriber(new OnPressedHandler(phone));
    }
/*
    private void createEPL_OutOFRange() {
        EPStatement statement = engine.getEPAdministrator().createEPL(OutOFRange.getStatement());
        statement.setSubscriber(new OutOFRangeHandler(phone));
    }*/
       private void createEPL_PositiveResponseRecieved() {
        EPStatement statement = engine.getEPAdministrator().createEPL(PositiveResponseRecieved.getStatement());
        statement.setSubscriber(new PositiveResponseRecievedHandler(phone));
    }
/*
    private void createEPL_SendEntered() {
        EPStatement statement = engine.getEPAdministrator().createEPL(SendEntered.getStatement());
        statement.setSubscriber(new SendEnteredHandler(phone));
    }
*/
    private void createEPL_SignalLost() {
        EPStatement statement = engine.getEPAdministrator().createEPL(SignalLost.getStatement());
        statement.setSubscriber(new SignalLostHandler(phone));
    }
/*
    private void createEPL_TerminationRequestCell() {
        EPStatement statement = engine.getEPAdministrator().createEPL(TerminationRequestCell.getStatement());
        statement.setSubscriber(new TerminationRequestCellHandler(phone));
    }

    private void createEPL_TerminationRequestPhone() {
        EPStatement statement = engine.getEPAdministrator().createEPL(TerminationRequestPhone.getStatement());
        statement.setSubscriber(new TerminationRequestPhoneHandler(phone));
    }*/
     private void createEPL_IncomingCall() {
        EPStatement statement = engine.getEPAdministrator().createEPL(IncomingCall.getStatement());
        statement.setSubscriber(new IncomingCallHandler(phone));
    }
}
