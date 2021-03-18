package CONTROL;

//import GENERICOS.ConexionSP;
//import GENERICOS.Conexion;
import GENERICOS.ConexionPrepareCall;

import FORMULARIOS.ProductoExpress;
import GENERICOS.ConstructorCombo;
import GENERICOS.ConstructorTabla;
import GENERICOS.MIError;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class ProductosExpressCtrol {
   
private ProductoExpress producto;           
public enum tablas{laboratorio, tipoimpuesto, nacionalidad, presentacion, tipoproducto };

private ConexionPrepareCall conec;  
private String MiMsM;
private String sintaxiSql;
private PreparedStatement preparedStatement;
private ResultSet rs;

String InsertarQuery="CALL ProductoExp_Ins(?,?,?,?,?,?,?)";      


private ConstructorCombo constuirCboNacionalidad;
private ConstructorCombo constuirCboIva;
private ConstructorCombo constuirCboPresentacion;
private ConstructorCombo constuirCboTipoProducto;

    public ProductosExpressCtrol(JDialog dialogoPadre){
      producto = (ProductoExpress) dialogoPadre;
            
    }
    
    public void initControles(){
        usarCombos();
        producto.btnCancelar.doClick();
    }
    
    
    
    public void alta() throws MIError {
         Connection cnn;   
        String resultado;
        
        /** Recibe los parametros `bmfarmaciv`.`ProductoExp_Ins`(
		p_ProductoDescripcion VARCHAR(45)-1*
		, p_CodigoBarra VARCHAR(45)-2*
		, p_Nacionalidad_id INT(11)-3*
		, p_Presentacion_id INT(11)-4*
		, p_DivisionLaboratorio_id INT(11)-5
		, p_Laboratorio_id INT(5)-6
		, p_TipoImpuesto_id INT(11)-7
		) */
         
        
        try {
           cnn=ConexionPrepareCall.Conectar();           
           CallableStatement cmst= cnn.prepareCall(InsertarQuery);
           cmst.setString (1,producto.txtDescripcion.getText().trim() );
           cmst.setInt    (2,Integer.parseInt(producto.txtCodigoBarra.getText()));
           cmst.setInt    (3, constuirCboNacionalidad.getCodigoActual(producto.cboNacionalidad.getSelectedIndex()));
           cmst.setInt    (4, constuirCboPresentacion.getCodigoActual(producto.cboPresentacion.getSelectedIndex()));
           cmst.setInt    (5,999);
           cmst.setInt    (6,999);
           cmst.setInt    (7,constuirCboIva.getCodigoActual(producto.cboIva.getSelectedIndex()));

           cmst.execute();  
                              
           resultado="Los datos se ingresaron exitosamente !!!";
           JOptionPane.showMessageDialog(producto, resultado);
           conec.desConectarBD();   
           
        } catch (SQLException ex) {
            MiMsM = "Ocurrio un error en el metodo alta de la tabla Laboratorio ";
            throw new MIError(MiMsM, ex);
            //Logger.getLogger(ControlCiudad.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        
        
    }

    
//        public void modificacion() throws MIError {
//        Connection cnn;    
//        conec =new ConexionPrepareCall();
//        String resultado;
//        try {
//            cnn=ConexionPrepareCall.Conectar();
//           CallableStatement cmst= cnn.prepareCall(EditarQuery);
//           cmst.setInt(1,Integer.parseInt(producto.txtCodigo.getText()));
//           cmst.setString(2,producto.txtDescripcion.getText().trim() );
//           
//
//           cmst.execute();  
//            
//           resultado="Los datos Modificados exitosamente !!!";
//           JOptionPane.showMessageDialog(producto, resultado);
//           cnn.close();   
//           
//        } catch (SQLException ex) {
//            MiMsM = "Ocurrio un error en el metodo modificacion de la tabla Laboratorio ";
//            throw new MIError(MiMsM, ex);
//            //Logger.getLogger(ControlCiudad.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
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
    
    
        private ResultSet resulseCbo(tablas tcbo) throws MIError {
            // conec.Conectar();
            
           
        try {
            sintaxiSql = null;
            switch (tcbo) {
                case laboratorio:
                    sintaxiSql = "SELECT id, NombreLaboratorio  FROM laboratorio ORDER BY id;";
                    break;
                case tipoimpuesto:
                    sintaxiSql = "SELECT id,Descripcion FROM tipoimpuesto ORDER BY id;";
                    break;
                case presentacion:
                    sintaxiSql = "SELECT id, PresentacionDescripcion FROM  presentacion ORDER BY id;";
                    break;    
                case nacionalidad:
                    sintaxiSql = "SELECT id, Descripcion FROM nacionalidad ORDER BY id;";
                    break;    
                case tipoproducto:
                    sintaxiSql = "SELECT id, Descripcion FROM tipoproducto ORDER BY id;";
                    break;       
            }
            conec = new ConexionPrepareCall();
            preparedStatement = conec.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
        } catch (SQLException ex) {
            MiMsM = "Ocurrio un error en el  resulseCbo ";
            throw new MIError(MiMsM, ex);
        }
        return rs;
    }
    
        public void usarCombos() {
                try {
                    constuirCboTipoProducto = new ConstructorCombo(resulseCbo(tablas.tipoproducto));
                } catch (MIError ex) {
                    Logger.getLogger(ProductosExpressCtrol.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                for (int i = 0; i < constuirCboTipoProducto.getRegistrosCombo().size(); i++) {
                    producto.cboTipoProducto.addItem(constuirCboTipoProducto.getRegistrosCombo().get(i).getDesCombo());
                }

                try {
                    constuirCboIva = new ConstructorCombo(resulseCbo(tablas.tipoimpuesto));
                } catch (MIError ex) {
                    Logger.getLogger(ProductosExpressCtrol.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (int i = 0; i < constuirCboIva.getRegistrosCombo().size(); i++) {
                    producto.cboIva.addItem(constuirCboIva.getRegistrosCombo().get(i).getDesCombo());
                }

                try {
                    constuirCboPresentacion = new ConstructorCombo(resulseCbo(tablas.presentacion));
                } catch (MIError ex) {
                    Logger.getLogger(ProductosExpressCtrol.class.getName()).log(Level.SEVERE, null, ex);
                }
                    for (int i = 0; i < constuirCboPresentacion.getRegistrosCombo().size(); i++) {
                        producto.cboPresentacion.addItem(constuirCboPresentacion.getRegistrosCombo().get(i).getDesCombo());
                }
                try {

                constuirCboNacionalidad = new ConstructorCombo(resulseCbo(tablas.nacionalidad));
                for (int i = 0; i < constuirCboNacionalidad.getRegistrosCombo().size(); i++) {
                    producto.cboNacionalidad.addItem(constuirCboNacionalidad.getRegistrosCombo().get(i).getDesCombo());
                }


                } catch (MIError ex) {
                    Logger.getLogger(ProductosExpressCtrol.class.getName()).log(Level.SEVERE, null, ex);
                }
        
        }
        

    
     private ResultSet cargarGrilla()    {
         conec =new ConexionPrepareCall();
         ResultSet cursor =null;
         sintaxiSql = null;
         
        try {
            sintaxiSql ="SELECT ID, NombreLaboratorio, Estado  FROM laboratorio where Estado='ACTIVO' ORDER BY ID desc ;";
            preparedStatement= conec.getConexion().prepareStatement(sintaxiSql);
            cursor= preparedStatement.executeQuery();
        } catch (SQLException ex) {
            
        }
        return cursor;
         
     }
    }
