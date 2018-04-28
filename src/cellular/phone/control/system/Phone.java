/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cellular.phone.control.system;

import GUI.Calc;
import GUI.Calling;
import GUI.Calling1;
import GUI.CellRecievedCall;
import GUI.Idle;
import GUI.MainClass;
import GUI.NoSignal;
import GUI.Off;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import events.OnPressed;
import java.util.ArrayList;
import javax.microedition.midlet.MIDletStateChangeException;
import javax.swing.JFrame;

/**
 *
 * @author omar1
 */
public class Phone {

    private final Cell cell;
    private Receiver receiver;
    private Transmitter transmitter;
    private EPServiceProvider engine;
    private String enteredNumber;
    private String number;

    private String currentState;
    private JFrame screen;
    private boolean callRequestPhone;

    public Phone(Cell cell, Receiver receiver, Transmitter transmitter) {
        this.cell = cell;
        this.receiver = receiver;
        this.transmitter = transmitter;
        engine = EPServiceProviderManager.getDefaultProvider();
        currentState = "Off";
        enteredNumber = "";
        screen = new Off();
        System1 s = new System1();
        callRequestPhone=false;
        number="01023231444";
    }

    public void scanForSignal() throws InterruptedException {

        ArrayList<Cell> cells = new ArrayList<>();
        cells = System1.getCells();
        int i = 0;
        boolean founded = false;
        while (i < cells.size()) {

            if (cells.get(i).isAvailable()) {

                if (cells.get(i).addPhone(this)) {

                    i = cells.size();
                    founded = true;
                } else {
                    i++;
                }
            } else {
                i++;
            }
        }
        if (!founded) {
            System.out.println("No Signal");
            currentState = "No Signal";
            screen.setVisible(false);
            NoSignal window = new NoSignal();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
            screen = window;
            Thread.sleep(10000);
            MainClass.engine.getEPRuntime().sendEvent(new OnPressed());
        } else {
            currentState = "Idle";
            screen.setVisible(false);

            Idle window = new Idle();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
            screen = window;
        }
    }

    public void turnOff() throws InterruptedException {

        currentState = "Off";
        screen.setVisible(false);

        Off window = new Off();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        screen = window;

    }

    public void calling(String dialedNumber) {
        enteredNumber=dialedNumber;
        currentState = "Calling";
        screen.setVisible(false);
        boolean answered=false;
        Calling window = new Calling();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        screen = window;
        CellRecievedCall window2 = new CellRecievedCall();
        long startTime = System.currentTimeMillis(); //fetch starting time
        while (callRequestPhone || (System.currentTimeMillis() - startTime) < 5000) {
       
        window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window2.setVisible(true);
        }
        enteredNumber="";
    }

    public JFrame getScreen() {
        return screen;
    }

    public void setScreen(JFrame screen) {
        this.screen = screen;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public Transmitter getTransmitter() {
        return transmitter;
    }

    public void setTransmitter(Transmitter transmitter) {
        this.transmitter = transmitter;
    }

    public EPServiceProvider getEngine() {
        return engine;
    }

    public void setEngine(EPServiceProvider engine) {
        this.engine = engine;
    }

    public String getEnteredNumber() {
        return enteredNumber;
    }

    public void setEnteredNumber(String enteredNumber) {
        this.enteredNumber = enteredNumber;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isCallRequestPhone() {
        return callRequestPhone;
    }

    public void setCallRequestPhone(boolean callRequestPhone) {
        this.callRequestPhone = callRequestPhone;
    }

}
