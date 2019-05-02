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

public class Conexion {
    private Connection Conexion = null;
    private String miError;
//    private final String ControladorBD = "org.postgresql.Driver";
    private final String ControladorBD = "com.mysql.jdbc.Driver";

    private final String baseDatos = "bmfarmaposventa";
    private final String usuario = "root";
    private final String password = "";
 
    private ResultSet ResultSet;
    public boolean conectado = false;

    //constructor que recibe los parametros para conectarse a la base de datos
    public Conexion() {
//        String url = "jdbc:postgresql://localhost:5432/" + baseDatos;
        String url = "jdbc:mysql://localhost/" + baseDatos;
        try {
            Class.forName(ControladorBD);
            Conexion = DriverManager.getConnection(url, usuario, password);
            conectado = true;
            Conexion.setAutoCommit(false);// el motor BD maneja transacciones SQL  (Desabilita las transaccion del motor)
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
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void rollback() {
        try {
            Conexion.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
