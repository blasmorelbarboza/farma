/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GENERICOS;

import java.sql.*;
import javax.swing.JOptionPane;

public class ConexionSP {
       static String user="root";
   static String pass="";
   static String url="jdbc:mysql://localhost/bmfarmaciv";
   static Connection cnn;
  
   public static Connection Conectar(){
           try{
           Class.forName("com.mysql.jdbc.Driver");
           cnn=DriverManager.getConnection(url,user,pass);
           }catch(ClassNotFoundException cnfex){
    JOptionPane.showMessageDialog(null, "ConexionMySQL:" +cnfex.getMessage());
           }catch(SQLException sqlex){
    JOptionPane.showMessageDialog(null, "ConexionMySQL:" +sqlex.getMessage());
           }catch(Exception ex){
    JOptionPane.showMessageDialog(null, "ConexionMySQL:" +ex.getMessage());
           }
           return cnn;
      }
    
}
