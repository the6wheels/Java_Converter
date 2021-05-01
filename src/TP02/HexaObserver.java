/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP02;

/**
 *
 * @author hisham
 */
public class HexaObserver extends Observer {

   
    
    public HexaObserver(Subject subject, Model model){
    this.subject = subject;
    this.subject.attach(this);
    this.model = model;
    }
    @Override
    public void update() {
        model.setValueHex(Integer.toHexString(subject.getState() ).toUpperCase());
    //System.out.println( "Hex String: " + Integer.toHexString(subject.getState() ).toUpperCase() );
    }
    
}
