package GENERICOS;

import java.awt.Color;
import javax.swing.JOptionPane;

/*Metodos de Validacion de Campos*/
public class ValidarVacios {
    public int vacioAviso=0;
    public void validame() {
        Object[] datos = {};
//    Object[] datos = {jTextField1.getText(), jTextField2.getText(), jTextArea1.getText()};

//    validar(datos);

    }

    public void validar(Object[] datos) 
    {
            for (int i = 0; i <= datos.length - 1; i++) 
            {
                    if (datos[i].toString().isEmpty()) 
                    {
                         JOptionPane.showMessageDialog(null, "Algunos campos estan vacios");
                            vacioAviso=1;                                                            
                            break;
                    }
            }
            
        
    }
    /*
            No funciona con el equals, si entras mas de un espacios en blanco en el JTextField no entra a esa condicion; asi queda mejor: 

        //Se elimina cualquier espacio en blanco 
        String aux = miJTextField.getText().trim(); 
        //Si no ha escrito nada 
        if( aux.length()== 0 ) { 
        JOptionPane.showMessageDialog(this,"El campo no puede estar vacio","Error",JOptionPane.ERROR_MESSAGE); 
        }
    */
    
}
