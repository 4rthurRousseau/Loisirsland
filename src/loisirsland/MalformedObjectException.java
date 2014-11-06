package loisirsland;


import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author E14C504H
 */
public class MalformedObjectException extends Exception {
     public MalformedObjectException(){
        super("MalformedException");
    }
    public MalformedObjectException(String message){
        super(message);
    }
}
