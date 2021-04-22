package CONTROL;

//import GENERICOS.ConexionSP;
//import GENERICOS.Conexion;
import GENERICOS.ConexionPrepareCall;

import FORMULARIOS.ProductoExpress;
import GENERICOS.ConstructorCombo;
import GENERICOS.ConstructorTabla;
import GENERICOS.MIError;
import genericos.FormatoBotones;
import genericos.FormatoBotones.opcionesBotones;
import genericos.FormatoVentanas;
import java.sql.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class ProductosExpressCtrol {

    private ProductoExpress producto;

    public enum tablas {

        laboratorio, 
        divisionlaboratorio, 
        tipoimpuesto, 
        nacionalidad, 
        presentacion, 
        tipoproducto, 
        fraccion
    };

    private ConexionPrepareCall conec;
    private String MiMsM;
    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet rs;

    String InsertarQuery = "CALL ProductoExp_Ins(?,?,?,?,?,?,?)";
//    public int idComboDivision=0; 
    
    private FormatoBotones formatearBotones;
    private FormatoVentanas formatearVentanas;

    private ConstructorCombo constuirCboNacionalidad;
    private ConstructorCombo constuirCboIva;
    private ConstructorCombo constuirCboPresentacion;
    private ConstructorCombo constuirCboTipoProducto;
    private ConstructorCombo constuirCboFraccionar;
    
    private ConstructorCombo constuirCboLaboratorio;
    private ConstructorCombo constuirCboDivisionLaboratorio;

    public ProductosExpressCtrol(JDialog dialogoPadre) {
        producto = (ProductoExpress) dialogoPadre;

    }

    public void initControles()  {
        
        formatearVentanas=new FormatoVentanas(producto, FormatoVentanas.tipoVentana.estandard);
        
        producto.chkAgregando.doClick();
        try {
            usarCombos();
            formatoBoton();            
            producto.btnCancelar.doClick();
        } catch (MIError ex) {
            Logger.getLogger(ProductosExpressCtrol.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public void alta() throws MIError {
        Connection cnn;
        String resultado;

        /**
         * Recibe los parametros `bmfarmaciv`.`ProductoExp_Ins`(
         * p_ProductoDescripcion VARCHAR(45)-1* , p_CodigoBarra VARCHAR(45)-2* ,
         * p_Nacionalidad_id INT(11)-3* , p_Presentacion_id INT(11)-4* ,
         * p_DivisionLaboratorio_id INT(11)-5 , p_Laboratorio_id INT(5)-6 ,
         * p_TipoImpuesto_id INT(11)-7 )
         */
        try {
            cnn = ConexionPrepareCall.Conectar();
            CallableStatement cmst = cnn.prepareCall(InsertarQuery);
            cmst.setString(1, producto.txtDescripcion.getText().trim());
            cmst.setInt(2, Integer.parseInt(producto.txtCodigoBarra.getText()));
            cmst.setInt(3, constuirCboNacionalidad.getCodigoActual(producto.cboNacionalidad.getSelectedIndex()));
            cmst.setInt(4, constuirCboPresentacion.getCodigoActual(producto.cboPresentacion.getSelectedIndex()));
            cmst.setInt(5, 999);
            cmst.setInt(6, 999);
            cmst.setInt(7, constuirCboIva.getCodigoActual(producto.cboIva.getSelectedIndex()));

            cmst.execute();

            resultado = "Los datos se ingresaron exitosamente !!!";
            JOptionPane.showMessageDialog(producto, resultado);
            conec.desConectarBD();

        } catch (SQLException ex) {
            MiMsM = "Ocurrio un error en el metodo alta de la tabla Laboratorio ";
            throw new MIError(MiMsM, ex);
            
        }

    }

    public ResultSet resulseCboFiltros(tablas tcbo, int id) throws MIError {
        // conec.Conectar();

        sintaxiSql = null;
        try {
            //sintaxiSql = null;
            switch (tcbo) {
                
                case divisionlaboratorio:
                    //sintaxiSql = "SELECT id, NombreDivision FROM divisionlaboratorio ORDER BY id;";
                    sintaxiSql = "SELECT id, NombreDivision FROM divisionlaboratorio WHERE Laboratorio_id= ? ORDER BY id;";
                    
                    break;    
                   
                    //
            }
// cmst.setInt(4, constuirCboPresentacion.getCodigoActual(producto.cboPresentacion.getSelectedIndex()));            
            conec = new ConexionPrepareCall();
            
            //idComboDivision= constuirCboPresentacion.getCodigoActual(producto.cboLaboratorio.getSelectedIndex());
            //idComboDivision=id;
            preparedStatement = conec.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, id);
            
            rs = preparedStatement.executeQuery();
        } catch (SQLException ex) {
            MiMsM = "Ocurrio un error en el  resulseCboFiltros ";
            throw new MIError(MiMsM, ex);
        }
        return rs;
    }
    
    private ResultSet resulseCbo(tablas tcbo) throws MIError {
        // conec.Conectar();

        try {
            sintaxiSql = null;
            switch (tcbo) {
                case laboratorio:
                    sintaxiSql = "SELECT id, NombreLaboratorio  FROM laboratorio ORDER BY id;";
                    break;
//                case divisionlaboratorio:
//                    sintaxiSql = "SELECT id, NombreDivision FROM divisionlaboratorio ORDER BY id;";
//                    
//                    break;    
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
                case fraccion:
                    sintaxiSql = "SELECT id,descripcion FROM fraccion  ORDER BY id;";
                    break;    
                    
                    //
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

    private void formatoBoton() throws MIError{
        
        formatearBotones= new FormatoBotones();
        HashMap<JButton, opcionesBotones> Botones =new HashMap<>();

        Botones.put(producto.btnGuardar, opcionesBotones.Grabar);
        Botones.put(producto.btnCancelar, opcionesBotones.Cancelar);
        Botones.put(producto.btnVolver, opcionesBotones.Volver);
        formatearBotones.asignarFormatoBotones(Botones, FormatoBotones.propiedadesBotones.profesional);
        
//        HashMap<JButton, opcionesBotones> Botonito =new HashMap<>();
//        Botonito.put(producto.btnCancelar, opcionesBotones.Cancelar);
//        formatearBotones.asignarFormatoBotones(Botonito, FormatoBotones.propiedadesBotones.estandar);

    }
    
       public void usarCombosDependientes(int filtroCombo) throws MIError {
           
           //idComboDivision=producto.cboDivisionLab.addItem(constuirCboDivisionLaboratorio.getCodigoActual(i));
           //System.out.println("idComboDivision:"+ idComboDivision);
        try {
       
            //int laboratorioId=999;
            producto.cboDivisionLab.removeAllItems();
           
            constuirCboDivisionLaboratorio = new ConstructorCombo(resulseCboFiltros(tablas.divisionlaboratorio, filtroCombo));         
          
            // removeAllItems()
//          for (int i = 0; i <        constuirCboTipoProducto.getRegistrosCombo().size(); i++) {                    
            for (int i = 0; i < constuirCboDivisionLaboratorio.getRegistrosCombo().size(); i++) {
                
                producto.cboDivisionLab.addItem(constuirCboDivisionLaboratorio.getRegistrosCombo().get(i).getDesCombo());
                  
                //producto.cboDivisionLab.addItem(constuirCboDivisionLaboratorio.getCodigoActual(i));
                  //getCodigoActual

            }

        } catch (Exception e) {
            MiMsM = "Ocurrio un error usarCombosDependientes";
            throw new MIError(MiMsM, e);
        }

    }
    
    private void usarCombos() throws MIError {

        try {

            constuirCboTipoProducto = new ConstructorCombo(resulseCbo(tablas.tipoproducto));
            for (int i = 0; i < constuirCboTipoProducto.getRegistrosCombo().size(); i++) {
                producto.cboTipoProducto.addItem(constuirCboTipoProducto.getRegistrosCombo().get(i).getDesCombo());
            }

            constuirCboIva = new ConstructorCombo(resulseCbo(tablas.tipoimpuesto));
            for (int i = 0; i < constuirCboIva.getRegistrosCombo().size(); i++) {
                producto.cboIva.addItem(constuirCboIva.getRegistrosCombo().get(i).getDesCombo());
            }

            constuirCboPresentacion = new ConstructorCombo(resulseCbo(tablas.presentacion));
            for (int i = 0; i < constuirCboPresentacion.getRegistrosCombo().size(); i++) {
                producto.cboPresentacion.addItem(constuirCboPresentacion.getRegistrosCombo().get(i).getDesCombo());
            }

            constuirCboNacionalidad = new ConstructorCombo(resulseCbo(tablas.nacionalidad));
            for (int i = 0; i < constuirCboNacionalidad.getRegistrosCombo().size(); i++) {
                producto.cboNacionalidad.addItem(constuirCboNacionalidad.getRegistrosCombo().get(i).getDesCombo());
            }
            constuirCboFraccionar = new ConstructorCombo(resulseCbo(tablas.fraccion));
            for (int i = 0; i < constuirCboFraccionar.getRegistrosCombo().size(); i++) {
                producto.cboFraccion.addItem(constuirCboFraccionar.getRegistrosCombo().get(i).getDesCombo());
            }
            
            constuirCboLaboratorio = new ConstructorCombo(resulseCbo(tablas.laboratorio));
            for (int i = 0; i < constuirCboLaboratorio.getRegistrosCombo().size(); i++) {
                producto.cboLaboratorio.addItem(constuirCboLaboratorio.getRegistrosCombo().get(i).getDesCombo());
            }
            
//            constuirCboDivisionLaboratorio = new ConstructorCombo(resulseCbo(tablas.divisionlaboratorio));
//            for (int i = 0; i < constuirCboDivisionLaboratorio.getRegistrosCombo().size(); i++) {
//                producto.cboDivisionLab.addItem(constuirCboDivisionLaboratorio.getRegistrosCombo().get(i).getDesCombo());
//            }

        } catch (Exception e) {
            MiMsM = "Ocurrio un error usarCombos";
            throw new MIError(MiMsM, e);
        }

    }

    private ResultSet cargarGrilla() throws MIError {
        conec = new ConexionPrepareCall();
        ResultSet cursor = null;
        sintaxiSql = null;

        try {
            sintaxiSql = "SELECT ID, NombreLaboratorio, Estado  FROM laboratorio where Estado='ACTIVO' ORDER BY ID desc ;";
            preparedStatement = conec.getConexion().prepareStatement(sintaxiSql);
            cursor = preparedStatement.executeQuery();
        } catch (SQLException ex) {
           MiMsM = "Ocurrio un error cargarGrilla";
            throw new MIError(MiMsM, ex);

        }
        return cursor;

    }
}
