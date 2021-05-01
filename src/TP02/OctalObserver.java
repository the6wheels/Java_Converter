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
public class OctalObserver extends Observer {

    
    public OctalObserver(Subject subject, Model model){
    this.subject = subject;
    this.subject.attach(this);
    this.model = model;
    }
    @Override
    public void update() {
     
        model.setValueOctal(Integer.toOctalString(subject.getState() ));
        
        
    //System.out.println( "Octal String: " + Integer.toOctalString(subject.getState() ) );
    
    }
}
