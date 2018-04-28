/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cellular.phone.control.system;

import GUI.Calc;
import GUI.Calling;
import GUI.Calling1;
import GUI.CellIdle;
import GUI.CellRecievedCall;
import GUI.CellRinging;
import GUI.Idle;
import GUI.InCall;
import GUI.InCallCell;
import GUI.MainClass;
import GUI.NoSignal;
import GUI.Off;
import GUI.Ringing;
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

    public class MyRunnable implements Runnable {

        private int var;

        public MyRunnable(int var) {
            this.var = var;
        }

        public void run() {
            MainClass.engine.getEPRuntime().sendEvent(new OnPressed());
        }
    }

    private final Cell cell;
    private Receiver receiver;
    private Transmitter transmitter;
    private EPServiceProvider engine;
    private String enteredNumber;
    private String number;

    private String currentState;
    private JFrame screen;
    private JFrame cellScreen;
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
        callRequestPhone = false;
        number = "01023231444";
    }

    public void terminateCall() {
        enteredNumber = "";
        currentState = "Idle";
        screen.setVisible(false);
        cellScreen.setVisible(false);
        Idle window = new Idle();
        CellIdle window2 = new CellIdle();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        screen = window;
        window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window2.setVisible(true);
        cellScreen = window2;
    }

    public void enterCall() {

        currentState = "In Call";
        screen.setVisible(false);
        cellScreen.setVisible(false);

        InCall window = new InCall();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        screen = window;

        InCallCell window2 = new InCallCell();
        window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window2.setVisible(true);
        cellScreen = window2;
    }

    public void scanForSignal() throws InterruptedException {

        /* if (MainClass.cell.isAvailable()) {
            CellIdle window = new CellIdle();
            MainClass.phone.cellScreen = window;
            window.setVisible(true);
            cellScreen = window;
             currentState = "Idle";
            screen.setVisible(false);

           Idle window2 = new Idle();
            window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window2.setVisible(true);
            screen = window2;
        }
        else
        {
            System.out.println("Ameer Group");
        }*/
        ArrayList<Cell> cells = new ArrayList<>();
        
        cells = System1.getCells();
        int i = 0;
        boolean founded = false;
        while (i < cells.size()) {

            if (MainClass.cell.isAvailable()) {

                if (cells.get(i).addPhone(this)) {
                    System.out.println("Test1");
                    i = cells.size();
                    founded = true;
                    CellIdle window = new CellIdle();
                    MainClass.phone.cellScreen = window;
                    window.setVisible(true);
                    cellScreen = window;
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

            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                @Override
                public void run() {
                    MainClass.engine.getEPRuntime().sendEvent(new OnPressed());
                }
            },
                    10000
            );

            //Thread.sleep(10000);
        } else {
            System.out.println("Test");
            currentState = "Idle";
            screen.setVisible(false);

            Idle window = new Idle();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
            screen = window;
        }
    }

    public void ringing(String recieverNumber, String senderNumber) {
        System.out.println("before checking number");
        if (recieverNumber.equals(MainClass.phone.getNumber())) {
            System.out.println("Correct number0");
            currentState = "Ringing";
            System.out.println("Correct number1");
            MainClass.phone.setEnteredNumber(senderNumber);
            System.out.println("Correct number2");
            screen.setVisible(false);
            cellScreen.setVisible(false);
            System.out.println("Correct number3");
            Ringing window = new Ringing();
            System.out.println("Correct number4");
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            CellRinging window2 = new CellRinging();
            System.out.println("Correct number4");
            window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window2.setVisible(true);
            System.out.println("Correct number5");
            window.setVisible(true);
            System.out.println("Correct number6");
            screen = window;
            cellScreen = window2;
            System.out.println("Correct number7");
        } else {
            System.out.println("Wrong number");
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
        enteredNumber = dialedNumber;
        currentState = "Calling";
        screen.setVisible(false);
        cellScreen.setVisible(false);
        boolean answered = false;
        Calling window = new Calling();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        screen = window;
        CellRecievedCall window2 = new CellRecievedCall();

        long startTime = System.currentTimeMillis(); //fetch starting time
        while (callRequestPhone || (System.currentTimeMillis() - startTime) < 5000) {

            window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window2.setVisible(true);
            cellScreen = window2;
        }

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

    public void setCellScreen(JFrame cellScreen) {
        this.cellScreen = cellScreen;
    }

    public Cell getCell() {
        return cell;
    }

    public JFrame getCellScreen() {
        return cellScreen;
    }

}
