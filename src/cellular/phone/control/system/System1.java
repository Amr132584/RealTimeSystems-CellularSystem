/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cellular.phone.control.system;

import java.util.ArrayList;

/**
 *
 * @author omar1
 */
public class System1 {
    private static ArrayList<Cell>cells =new ArrayList<>();

    public System1() {
        Cell cell=new Cell();
        cells.add(cell);
    }

    
    public static ArrayList<Cell> getCells() {
        return cells;
    }

    public void setCells(ArrayList<Cell> cells) {
        this.cells = cells;
    }
    
}
