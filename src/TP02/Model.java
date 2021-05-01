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
public class Model {
    String value = "0";
    String valueHex;
    String valueBinary;
    String valueOctal;

    public String getValueHex() {
        return valueHex;
    }

    public void setValueHex(String valueHex) {
        this.valueHex = valueHex;
    }

    public String getValueBinary() {
        return valueBinary;
    }

    public void setValueBinary(String valueBinary) {
        this.valueBinary = valueBinary;
    }

    public String getValueOctal() {
        return valueOctal;
    }

    public void setValueOctal(String valueOctal) {
        this.valueOctal = valueOctal;
    }
   

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
}
