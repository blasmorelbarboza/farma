/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GENERICOS;

import java.awt.event.KeyEvent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Juan
 */
public class Utiles {

    private final SimpleDateFormat formatoddMMyyy = new SimpleDateFormat("dd-MM-yyyy");
    private final SimpleDateFormat formatoyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
    private Conexion conexion;
    private ResultSet cursor;
    private PreparedStatement st;
    public enum adicionar {
        Cantidad, intervaloMes,intervaloDia, intervaloAnio
    };
    public String fechaServidor() throws MIError {
        conexion = new Conexion();
        try {
            // String sql = "select to_char(current_date, 'dd/mm/yyyy') as fecha"; /*Postgres*/
            String sql = "SELECT DATE_FORMAT(curdate(), '%d/%m/%Y')as fecha;";
            /*mysql*/
            st = conexion.getConexion().prepareStatement(sql);
            cursor = st.executeQuery();
            if (cursor.next()) {
                return cursor.getString("fecha");
            }
        } catch (SQLException ex) {
            //  Logger.getLogger(genericos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public String fechaAdicionar(Integer canti, adicionar Intervalo) throws MIError {
        conexion = new Conexion();
        String sql="";
        try {
            // String sql = "select to_char(current_date, 'dd/mm/yyyy') as fecha"; /*Postgres*/
            //String sql = "SELECT DATE_FORMAT(curdate(), '%d/%m/%Y')as fecha;";
            
            switch (Intervalo) {
                case intervaloDia:
                    sql = "SELECT DATE_FORMAT(DATE_ADD(NOW(), INTERVAL "+canti+" DAY), '%d/%m/%Y')AS fecha;";
                break;
                case intervaloMes:
                    sql = "SELECT DATE_FORMAT(DATE_ADD(NOW(), INTERVAL "+canti+" MONTH), '%d/%m/%Y')AS fecha;";
                break;
                case intervaloAnio:
                    sql = "SELECT DATE_FORMAT(DATE_ADD(NOW(), INTERVAL "+canti+" year), '%d/%m/%Y')AS fecha;";
                break;
            }
            
            
            /*mysql*/
            st = conexion.getConexion().prepareStatement(sql);
            cursor = st.executeQuery();
            if (cursor.next()) {
                return cursor.getString("fecha");
            }
        } catch (SQLException ex) {
            //  Logger.getLogger(genericos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    

    public String prepararDateField(JFormattedTextField prepararJTfField) throws MIError {
        try {
            prepararJTfField.setFormatterFactory(
                    new javax.swing.text.DefaultFormatterFactory(
                            new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (Exception e) {
        }
        return formatoddMMyyy.format(fechaServidor());
    }

    public String retornarFechayyyyMMdd(String fecha) {
        Date fechaLocal = null;
        try {
            fechaLocal = formatoddMMyyy.parse(fecha);
        } catch (ParseException ex) {
            //Logger.getLogger(genericos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return formatoyyyMMdd.format(fechaLocal);
    }

    public String retornarFechaddMMyyyy(Date fechaRecuperada) {
        return formatoddMMyyy.format(fechaRecuperada);
    }

    public boolean verificarTxtVacio(JTextField txt) {
        if (txt.getText().isEmpty()) {
            System.out.print("campo vAcIO");
            JOptionPane.showMessageDialog(null, "Campo Requerido");
            return false;
        }
        return true;

        /*if (txtCodigo.getText().isEmpty())
{
     //El campo esta vacio, 
}
else
{
    //El campo tiene texto
}       

 // No dejar campo vacio:
  if ( (txtCodigo != null) && (!txtCodigo.equals(" ")) ) {
    // cadena no está vacía
            JOptionPane.showMessageDialog(this, "No dejar campos Vacíos", "Error!", JOptionPane.ERROR_MESSAGE);    
        }
         */
    }

    public boolean verificarTXTcero(JTextField txt) {
        if (txt.getText().equals("0")) {
            JOptionPane.showMessageDialog(null, "Campo Requerido Distinto Cero");
            return false;
        }
        return true;
    }

    public boolean verificarTxtVacioCero(JTextField txt) {
        if ((txt.getText().equals("0")) || (txt.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "Campo Requerido Distinto a Vacio o Cero(0)");
            return false;
        }
        return true;
    }

    public static double Redondear(double numero, int digitos) {
        int cifras = (int) Math.pow(10, digitos);
        return Math.rint(numero * cifras) / cifras;
    }

    public void convertiraMayusculasEnJtextfield(javax.swing.JTextField jTextfieldS) {
        String cadena = (jTextfieldS.getText()).toUpperCase();
        jTextfieldS.setText(cadena);
    }
    
    private  boolean validarVacios(String codigo, String nombre ){
     boolean error=false;
      //quitando espacios en blanco de las variales
        codigo=codigo.trim();
        nombre=nombre.trim();

      //validando que no esten vacias.
        if(codigo.isEmpty()){
           error= true;
        }
        if(nombre.isEmpty()){
           error= true;
        }
      return error;
}

}
