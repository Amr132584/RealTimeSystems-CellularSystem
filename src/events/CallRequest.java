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
public class CallRequest {
    String number;
    
    public CallRequest(String number) {
        this.number = number;
    }
        public String getNumber() {
        return number;
    }

     public static String getStatement()
    {
        String statement = "select number from CallRequest";
        return statement;
    }
}
