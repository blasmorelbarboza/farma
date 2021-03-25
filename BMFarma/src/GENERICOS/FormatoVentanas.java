/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package genericos;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JDialog;

/**
 *
 * @author DELL
 */
public class FormatoVentanas {

    public enum tipoVentana {estandard, buscadores,maestro_detalle};
    
    
    public FormatoVentanas(JDialog ventana, tipoVentana tVentana ) {
        
        switch(tVentana){
            case estandard: 
                    ventana.getContentPane().setBackground(Color.white);
                    ventana.setSize(new Dimension(800, 600));
                break;
            case maestro_detalle: 
                 ventana.getContentPane().setBackground(new Color(202, 236, 238));
                    ventana.setSize(new Dimension(1200, 760));
                break;
            
            case buscadores: 
                ventana.getContentPane().setBackground(Color.WHITE);
                    ventana.setSize(new Dimension(500, 400));
                break;
        } 
        
        ventana.setResizable(false);
        ventana.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/Recursos/CarritoCompras (1).png")).getImage());
        ventana.setLocationRelativeTo(null);  
    }
  
}
