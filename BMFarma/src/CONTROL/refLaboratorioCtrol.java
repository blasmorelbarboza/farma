package CONTROL;

import GENERICOS.ConexionSP;
import GENERICOS.Conexion;
import FORMULARIOS.refLaboratorio;
import GENERICOS.ConstructorTabla;
import GENERICOS.MIError;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class refLaboratorioCtrol {
   
private refLaboratorio laboratorio;           
public enum tablas{laboratorio};

private Conexion conec;  
private String MiMsM;
private String sintaxiSql;
private PreparedStatement preparedStatement;
private ConstructorTabla construirTabla;
String Insertar="CALL Laboratorio_Ins(?)";      
String Borrar="CALL Laboratorio_Del(?)";      
String Editar="CALL Laboratorio_Upd(?,?)";      

    public refLaboratorioCtrol(JDialog dialogoPadre){
      laboratorio = (refLaboratorio) dialogoPadre;
            
    }
    
    public void initControles(){
        construirTabla = new ConstructorTabla(cargarGrilla());
        laboratorio.grlDatos.setModel(construirTabla);
        laboratorio.btnCancelar.doClick();
    }
    
    
    public void alta() throws MIError {
        Connection cnn;   
        String resultado;
        try {
           cnn=ConexionSP.Conectar();
           CallableStatement cmst= cnn.prepareCall(Insertar);
           cmst.setString(1,laboratorio.txtDescripcion.getText().trim() );

           cmst.execute();  
            
           resultado="Los datos se ingresaron exitosamente !!!";
           JOptionPane.showMessageDialog(laboratorio, resultado);
           cnn.close();   
           
        } catch (SQLException ex) {
            MiMsM = "Ocurrio un error en el metodo alta de la tabla Laboratorio ";
            throw new MIError(MiMsM, ex);
            //Logger.getLogger(ControlCiudad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            public void baja() throws MIError {
        Connection cnn;   
        String resultado;
        try {
            cnn=ConexionSP.Conectar();
           CallableStatement cmst= cnn.prepareCall(Borrar);
           cmst.setInt(1,Integer.parseInt( laboratorio.txtCodigo.getText()));

           cmst.execute();  
            
           resultado="Los datos borrados exitosamente !!!";
           JOptionPane.showMessageDialog(laboratorio, resultado);
           cnn.close();   
           
        } catch (SQLException ex) {
            MiMsM = "Ocurrio un error en el metodo baja de la tabla Laboratorio ";
            throw new MIError(MiMsM, ex);
            //Logger.getLogger(ControlCiudad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        public void modificacion() throws MIError {
        Connection cnn;    
        String resultado;
        try {
            cnn=ConexionSP.Conectar();
           CallableStatement cmst= cnn.prepareCall(Editar);
           cmst.setInt(1,Integer.parseInt(laboratorio.txtCodigo.getText()));
           cmst.setString(2,laboratorio.txtDescripcion.getText().trim() );
           

           cmst.execute();  
            
           resultado="Los datos Modificados exitosamente !!!";
           JOptionPane.showMessageDialog(laboratorio, resultado);
           cnn.close();   
           
        } catch (SQLException ex) {
            MiMsM = "Ocurrio un error en el metodo modificacion de la tabla Laboratorio ";
            throw new MIError(MiMsM, ex);
            //Logger.getLogger(ControlCiudad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//        public ResultSet resultseTabla(tablas t){
//       Conexion conec=new Conexion();
//       String sintaxiSql=null;
//        PreparedStatement ps;
//        ResultSet rs=null;
//        try {
//            
//            switch(t){
//                case cargos : 
//                    sintaxiSql="SELECT cargo_codigo, cargo_descrip,cargo_obvs FROM cargos ORDER BY cargo_codigo;";
//                    break;
//                case ciudad: 
//                    sintaxiSql="SELECT ciud_codigo, ciud_descrip FROM ciudades ORDER BY ciud_codigo;";
//                    break;
//                case nacionalidad: 
//                    sintaxiSql="SELECT nac_codigo,nac_descrip,nac_obsv FROM nacionalidades ORDER BY nac_codigo;";
//                    break;
//            }
//    
//            ps=conec.getConexion().prepareStatement(sintaxiSql);
//            rs=ps.executeQuery();
//  
//        } catch (SQLException ex) {
//            Logger.getLogger(ejemploBuscardorGenerico.class.getName()).log(Level.SEVERE, null, ex);
//        }
//           return rs;
//    }
     private ResultSet cargarGrilla()    {
         conec =new Conexion();
         ResultSet cursor =null;
         sintaxiSql = null;
         
        try {
            sintaxiSql ="SELECT ID, NombreLaboratorio, Estado  FROM laboratorio ORDER BY ID desc ;";
            preparedStatement= conec.getConexion().prepareStatement(sintaxiSql);
            cursor= preparedStatement.executeQuery();
        } catch (SQLException ex) {
            
        }
        return cursor;
         
     }
    }
