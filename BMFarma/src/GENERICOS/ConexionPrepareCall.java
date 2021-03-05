package GENERICOS;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConexionPrepareCall {
    static Connection Conexion = null;
    private String miError;
//    private final String ControladorBD = "org.postgresql.Driver";
    static String ControladorBD = "com.mysql.jdbc.Driver";
    
    //Class.forName("com.mysql.jdbc.Driver");

    static String baseDatos = "bmfarmaciv";
    static String usuario = "root";
    static String password = "";
 
    private ResultSet ResultSet;
    public boolean conectado = false;
    static String url = "jdbc:mysql://localhost/" + baseDatos;

    
    
public static Connection Conectar(){  
            /**
             * El Metodo Conectar() Enfocado para Uso de Procedimientos Almacenados
             */
            
            try{
            
            Class.forName(ControladorBD);
            Conexion=DriverManager.getConnection(url,usuario,password);
            
           }catch(ClassNotFoundException cnfex){
    JOptionPane.showMessageDialog(null, "ConexionMySQL:     ClassNotFoundException " +cnfex.getMessage());
           }catch(SQLException sqlex){
    JOptionPane.showMessageDialog(null, "ConexionMySQL:     SQLException " +sqlex.getMessage());
           }catch(Exception ex){
    JOptionPane.showMessageDialog(null, "ConexionMySQL:     Exception   " +ex.getMessage());
           }
            return Conexion;

      }


/**
 
 */
    
    //constructor que recibe los parametros para conectarse a la base de datos
    public ConexionPrepareCall() {
//        String url = "jdbc:postgresql://localhost:5432/" + baseDatos;
        
        
        
        try {
            Class.forName(ControladorBD);
            Conexion = DriverManager.getConnection(url, usuario, password);
            conectado = true;
            Conexion.setAutoCommit(false);// el motor BD maneja transacciones SQL  (Desabilita las transaccion del motor)
//            JOptionPane.showMessageDialog(null, "Conexion Ok", "AVISO", JOptionPane.INFORMATION_MESSAGE);
        } catch (ClassNotFoundException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            JOptionPane.showMessageDialog(null, "Conexion Fallida  __1.__", "AVISO", JOptionPane.INFORMATION_MESSAGE);
            conectado = false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Conexion Fallida__2__", "AVISO", JOptionPane.INFORMATION_MESSAGE);
            System.out.print(e.getMessage());
            conectado = false;
        }
    }


    
    public Connection getConexion() {
    /**
     * El Metodo getConexion() Enfocado para que java Realice el control Secion COMIT/ROLLBACK 
     */
        return Conexion;
    }

    public void desConectarBD() {
        try {
            Conexion.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Durante la DesConecion", "AVISO", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public ResultSet ejecutarExecuteQuery(PreparedStatement preparedStatement) throws MIError/*indicar la clase q administre*/ {
        try {
            ResultSet = preparedStatement.executeQuery();
        } catch (SQLException ex) {
            miError = "Error durante la ejecucion de la siguiente consulta ";
            throw new MIError(miError, ex);
            //JOptionPane.showMessageDialog(null, "Error Durante la operacion", "AVISO", JOptionPane.INFORMATION_MESSAGE);
        }
        return ResultSet;
    }

    public int ejecutarExecuteUpdate(PreparedStatement preparedStatement) throws MIError {
        int fila = 0;
        try {
            fila = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            miError = "Error durante la ejecucion de la  ";
            throw new MIError(miError, ex);
        }
        return fila;
    }

    public void comit() {
        try {
            Conexion.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionPrepareCall.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void rollback() {
        try {
            Conexion.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionPrepareCall.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *
     * @return
     */
    
    
    
}
