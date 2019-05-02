/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;

/**
 * @author DELL
 */
public class FormatoBotones {

  
    public enum propiedadesBotones {
        estandar, profesional, prueba
    };
    
    
    public void m(propiedadesBotones FormatoBotones ){
    switch(FormatoBotones){
        case estandar:break;
        case profesional:break;
        case prueba:break;
    }
        
        
    }

    public enum opcionesBotones {
        Agregar, Modificar, Eliminar, Grabar, Cancelar,Reporte,Ayuda
    };

    private ArrayList<JButton> arrayBotones;

    
   
    public void asignarFormatoBotones(HashMap<JButton, opcionesBotones> objetoBotones, propiedadesBotones propiedadBotones) {
        arrayBotones = new ArrayList<>();
        for (Map.Entry<JButton, opcionesBotones> entry : objetoBotones.entrySet()) {
            JButton jButton = entry.getKey();
            opcionesBotones botones = entry.getValue();
            arrayBotones.add(jButton);
            switch (botones) {
                case Agregar:
                    jButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/nuevo.png")));
                    jButton.setMnemonic('A');
                    jButton.setToolTipText("Agregar Nuevo Registro");
                    break;
                case Modificar:
                    jButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/actualizar.png")));
                    jButton.setMnemonic('M');
                    jButton.setToolTipText("Modificar Registro");
                    break;
                case Eliminar:
                    jButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/eliminar.png")));
                    jButton.setMnemonic('E');
                    jButton.setToolTipText("Eliminar Registro");
                    break;
                case Cancelar:
                    jButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cancelar.png")));
                    jButton.setMnemonic('C');
                    jButton.setToolTipText("Cancelar Operacion");
                    break;
                case Grabar:
                    jButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/guardar.png")));
                    jButton.setMnemonic('G');
                    jButton.setToolTipText("Guardar Nuevo Registro");
                    break;
            
                case Ayuda:
                    jButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/ayuda5.png")));
                    jButton.setToolTipText("Ayuda del Sistema");
                    break;
            
                case Reporte:
                    jButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/printer4.png")));
                    jButton.setMnemonic('R');
                    jButton.setToolTipText("Imprimir Listado/s");
                    break;
            }
        }

        switch (propiedadBotones) {
            case estandar:
                for (JButton jButton : arrayBotones) {
                    jButton.setBackground(new java.awt.Color(44, 80, 152));
                    jButton.setFont(new java.awt.Font("Arial", 1, 14));
                    jButton.setForeground(new java.awt.Color(255, 255, 255));
                    jButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                }
                break;
            case profesional:
                for (JButton jButton : arrayBotones) {
                    jButton.setBackground(new java.awt.Color(44, 80, 152));
                    jButton.setFont(new java.awt.Font("Arial", 1, 12));
                    jButton.setForeground(new java.awt.Color(255, 255, 255));
                    jButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                }
                break;
        }
    }
}
