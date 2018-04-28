/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

/**
 *
 * @author omar1
 */
public class IncomingCall {
    String recieverNumber;
    String senderNumber;
     public IncomingCall(String recieverNumber,String senderNumber) {
        this.recieverNumber = recieverNumber;
        this.senderNumber = senderNumber;
    }

    public String getRecieverNumber() {
        return recieverNumber;
    }

    public String getSenderNumber() {
        return senderNumber;
    }
     
     public static String getStatement()
    {
        String statement = "select recieverNumber, senderNumber from IncomingCall";
        return statement;
    }

   
     
     
}
