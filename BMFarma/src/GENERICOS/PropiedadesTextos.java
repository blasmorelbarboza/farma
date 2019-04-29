/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genericos;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Juan
 */
public class PropiedadesTextos extends PlainDocument {
    private final int maxlength;
    private final JTextField jTextField;
    private final boolean esCaracter;

    public PropiedadesTextos(JTextField jTextField, boolean esCaracter, int maxlength) {
         this.esCaracter=esCaracter;
        this.maxlength = maxlength;
        this.jTextField = jTextField;
    }

    @Override
    public void insertString(int offSet, String str, AttributeSet atrr) throws BadLocationException {
        if (this.esCaracter) {
            if ((this.jTextField.getText().length() + str.length()) < this.maxlength) {
                super.insertString(offSet, str.toUpperCase(), atrr);
               // super.insertString(offSet, str.toUpperCase().replaceAll("[^a-z|^A-Z^ ,^Ñ^.,]", ""), atrr);
            }
        } else if ((this.jTextField.getText().length() + str.length()) < this.maxlength) {
            super.insertString(offSet, str.toUpperCase().replaceAll("[^0-9|^ , ^-]", ""), atrr);
        }
    }
}
