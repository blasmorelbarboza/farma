package GENERICOS;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class HabilitarTextosPanel {
         public void habilitarCampos(JPanel jPanel)
     {
        for(int i=0; jPanel.getComponents().length>i;i++)
            {
                //Limpia todos los JTextField de un JPanel
                if(jPanel.getComponents()[i]instanceof JTextField)
                {
                    ((JTextField)jPanel.getComponents()[i]).setEnabled(true);
                }
            //Limpia todos los JPasswordField de un JPanel
            else if(jPanel.getComponents()[i] instanceof JPasswordField)
            {
                ((JPasswordField)jPanel.getComponents()[i]).setEnabled(true);
                
            }           
        }
    
    }
         
         public void deshabilitarCampos(JPanel jPanel)
     {
        for(int i=0; jPanel.getComponents().length>i;i++)
            {
                //Limpia todos los JTextField de un JPanel
                if(jPanel.getComponents()[i]instanceof JTextField)
                {
                    ((JTextField)jPanel.getComponents()[i]).setEnabled(false);
                }
            //Limpia todos los JPasswordField de un JPanel
            else if(jPanel.getComponents()[i] instanceof JPasswordField)
            {
                ((JPasswordField)jPanel.getComponents()[i]).setEnabled(false);
                
            }           
        }
    
    }

    
}
