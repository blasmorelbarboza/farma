package CONTROL;

import FORMULARIOS.ProductoExpress;


import GENERICOS.Conexion;
import GENERICOS.ConexionSP;

import GENERICOS.ConstructorCombo;
import GENERICOS.MIError;
import GENERICOS.setearObjeto;
import com.mxrck.autocompleter.TextAutoCompleter;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.text.NumberFormat;

import GENERICOS.Utiles;
import GENERICOS.ValidarVacios;
import Genericos.FormatoTextos;
import Genericos.PropiedadesTextos;



public class ProductosExpresCtrol {

    private ProductoExpress productoExpres;
    private Conexion conec;
    private String sintaxiSql;
    private String MiMsM;
    private PreparedStatement ps;
    private ResultSet rs;
    
    private NumberFormat nf = NumberFormat.getNumberInstance();
    public Utiles utiles;

    private FormatoTextos formatoTxt;
    private PropiedadesTextos propiedadesTxt;

    private ConstructorCombo constCboVendedor;
    private ValidarVacios textoVacio;
    
    private int filasAfectadas;

    public enum tablas {
        clientes, producto, vendedor, condicionVenta, idVenta
    };

    public enum paneles {
        cabecera, detalle, botonera, todos
    };

    public enum estados {
        activo, pendiente, inactivo
    };

    public enum descuentosTipos {
        porMonto, porcentaje
    };

    String InsertarCabecera = "CALL Productos_Ins(?,?,?,?,?,?,?)";
    /*
        CREATE DEFINER=`root`@`localhost` PROCEDURE `Productos_Ins`(
			  p_ProductoDescripcion VARCHAR(45)
			, p_CodigoBarra VARCHAR(45)
			, p_Nacionalidad_id INT(11)
			, p_Presentacion_id INT(11)
			, p_DivisionLaboratorio_id INT(11)
			, p_Laboratorio_id INT(11)
			, p_TipoImpuesto_id INT(11))
    */
    
    private int condicionVenta = 0;

    public ProductosExpresCtrol(JDialog dialogoPadre) {
        productoExpres = (ProductoExpress) dialogoPadre;
        utiles = new Utiles();
        formatoTxt = new FormatoTextos();
        textoVacio =new ValidarVacios();

    }

    public void initControlers() throws MIError {
        
            //HabilitarBotones(estados.activo);
            //HabilitarTexto(estados.inactivo, paneles.todos);
            limpiarTexto(paneles.todos);
            //cargarComboFraccion();

            //Ventas.grlProductos.setModel(tabla);
//            productoExpress.grlProductos.setModel((TableModel) grilla);
            

            //utiles.prepararDateField(pedidos.txtFechaFactura);
///            productoExpress.txtfFecha.setText(utiles.fechaServidor());
            //itemsAutoCompletado();
        
    }

    
    

    public void HabilitarBotones(estados estadoBotones) {
        switch (estadoBotones) {
            case activo:
                productoExpres.chkAgregando.setEnabled(true);
                productoExpres.btnGuardar.setEnabled(false);
                productoExpres.btnCancelar.setEnabled(false);
                productoExpres.btnVolver.setEnabled(true);

                break;
            case inactivo:
                productoExpres.chkAgregando.setEnabled(false);
                productoExpres.btnGuardar.setEnabled(false);
                productoExpres.btnCancelar.setEnabled(true);
                productoExpres.btnVolver.setEnabled(false);
                break;
        }
    }

    public void limpiarTexto(paneles p) throws MIError {
        try {

            switch (p) {
                case cabecera:
                    break;
                
                case detalle:
                    break;

                case todos:
                    // Ventas.txtFecha.setText("2018-01-17 18:20:48");
                    // productoExpress.txtfFecha.setText(utiles.fechaServidor().toString());
                    productoExpres.txtCodigo.setText("0");
                    productoExpres.txtCodigoBarra.setText("");
                    productoExpres.txtCodigoItem.setText("");
                    productoExpres.txtDescripcion.setText("");
                    productoExpres.txtPresentacion.setText("");
                    
                    productoExpres.txtLinea.setText("");
                    
                    productoExpres.txtNacionalidadCodigo.setText("");
                    productoExpres.txtNacionalidad.setText("");
                    productoExpres.txtPrecio.setText("");
                    
                    productoExpres.txtDescuento.setText("");
                    productoExpres.txtFraccion.setText("");
                    
                    productoExpres.txtStockInicial.setText("0");

                    break;
            }
        } catch (Exception ex) {
            MiMsM = "Ocurrio un error en el metodo  limpiarTexto ";
            throw new MIError(MiMsM, ex);
        }
    }

    public void HabilitarTexto(estados estadoTexto, paneles HabPaneles) {
        switch (estadoTexto) {
            case inactivo:
                switch (HabPaneles) {
                    case cabecera:
//                        productoExpress.txtfFecha.setEnabled(false);
//                        productoExpress.txtRuc.setEnabled(false);
//                        productoExpress.txtRazonSocial.setEnabled(false);
//                        productoExpress.txtIdVenta.setEnabled(false);
//                        productoExpress.cboVendedor.setEnabled(false);
//                        productoExpress.opcContado.setEnabled(false);
//                        productoExpress.opcCredito.setEnabled(false);
                        break;
                    case detalle:
//                        productoExpress.txtCodigo.setEnabled(false);
//                        productoExpress.txtPorcentaje.setEnabled(false);
//                        productoExpress.txtDescuentoMonto.setEnabled(false);
//                        productoExpress.txtCantidad.setEnabled(false);
//                        productoExpress.txtPrecioPublico.setEnabled(false);
//                        productoExpress.txtSubtotal.setEnabled(false);
//                        productoExpress.txtProducto.setEnabled(false);
//
//                        productoExpress.txtIva5.setEnabled(false);
//                        productoExpress.txtIva10.setEnabled(false);
//                        productoExpress.txtTotal.setEnabled(false);
                        break;
                    case todos:
//                        productoExpress.txtfFecha.setEnabled(false);
//                        productoExpress.txtRuc.setEnabled(false);
//                        productoExpress.txtRazonSocial.setEnabled(false);
//                        productoExpress.txtIdVenta.setEnabled(false);
//                        productoExpress.cboVendedor.setEnabled(false);
//                        productoExpress.txtCodigo.setEnabled(false);
//                        productoExpress.txtPorcentaje.setEnabled(false);
//                        productoExpress.txtDescuentoMonto.setEnabled(false);
//                        productoExpress.txtCantidad.setEnabled(false);
//                        productoExpress.txtPrecioPublico.setEnabled(false);
//                        productoExpress.txtSubtotal.setEnabled(false);
//                        productoExpress.txtProducto.setEnabled(false);
//                        productoExpress.txtIva5.setEnabled(false);
//                        productoExpress.txtIva10.setEnabled(false);
//                        productoExpress.txtTotal.setEnabled(false);
//                        productoExpress.opcContado.setEnabled(false);
//                        productoExpress.opcCredito.setEnabled(false);
                        break;
                }
                break;
            case activo:
                switch (HabPaneles) {
                    case cabecera:
//                        Ventas.txtFecha.setEnabled(true);

//                        productoExpress.txtRuc.setEnabled(true);
//                        productoExpress.txtRazonSocial.setEnabled(true);
//                        productoExpress.txtIdVenta.setEnabled(true);
//                        productoExpress.cboVendedor.setEnabled(true);
//                        productoExpress.opcContado.setEnabled(true);
//                        productoExpress.opcCredito.setEnabled(true);
//                        productoExpress.opcContado.doClick();

                        break;
                    case detalle:
//                        productoExpress.txtCodigo.setEnabled(true);
//                        productoExpress.txtPorcentaje.setEnabled(true);
//                        productoExpress.txtDescuentoMonto.setEnabled(true);
//                        productoExpress.txtCantidad.setEnabled(true);
//                        Ventas.txtPrecioPublico.setEnabled(true);
//                        Ventas.txtSubtotal.setEnabled(true);
//                        productoExpress.txtProducto.setEnabled(true);
//                        Ventas.txtIva5.setEnabled(true);
//                        Ventas.txtIva10.setEnabled(true);
//                        Ventas.txtTotal.setEnabled(true);
                        break;

                    case todos:
//                        Ventas.txtFecha.setEnabled(true);

//                        productoExpress.txtRuc.setEnabled(true);
//                        productoExpress.txtRazonSocial.setEnabled(true);
//                        productoExpress.txtIdVenta.setEnabled(true);
//                        productoExpress.cboVendedor.setEnabled(true);
//                        productoExpress.txtCodigo.setEnabled(true);
//                        productoExpress.txtPorcentaje.setEnabled(true);
//                        productoExpress.txtDescuentoMonto.setEnabled(true);
//                        productoExpress.txtCantidad.setEnabled(true);
//                        Ventas.txtPrecioPublico.setEnabled(true);
//                        Ventas.txtSubtotal.setEnabled(true);
//                        productoExpress.txtProducto.setEnabled(true);
//                        Ventas.txtIva5.setEnabled(true);
//                        Ventas.txtIva10.setEnabled(true);
//                        Ventas.txtTotal.setEnabled(true);

//                        productoExpress.opcContado.setEnabled(true);
//                        productoExpress.opcCredito.setEnabled(true);
//                        productoExpress.opcContado.doClick();
                        break;
                }
                break;
        }
    }

    public void itemsAutoCompletado() /* throws MIError*/ {
        TextAutoCompleter AutoAcompletado = new TextAutoCompleter(productoExpres.txtDescripcion);
//        AutoAcompletado.setMode(-1); // prefijo, viene por defecto
        AutoAcompletado.setMode(0); // infijo
//        AutoAcompletado.setMode(1); // sufijo
//        AutoAcompletado.setCaseSensitive(true); // Sensible a mayúsculas
        AutoAcompletado.setCaseSensitive(false); //No sensible a mayúsculas
        try {
            conec = new Conexion();
            sintaxiSql = null;
            // sintaxiSQL = "SELECT produ_codigo,produ_descripcion,produ_precio_vent FROM productos WHERE produ_codigo = " + pedidos.txtCodigoProducto.getText().trim() + " ORDER BY produ_codigo;";
            sintaxiSql = "SELECT  id, "
                    + "ProductoDescripcion "
                    + "FROM producto ORDER BY ProductoDescripcion;";
            ps = conec.getConexion().prepareStatement(sintaxiSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                AutoAcompletado.addItem(rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidosControl.class.getName()).log(Level.SEVERE, null, ex);
            /*MiMsM = "Ocurrio un error en el metodo  itemsAutoCompletado de la tabla Ventas ";
            throw new MIError(MiMsM, ex);*/
        }
    }

    public void recuperarDatosProductos(JTextField id,
            JTextField descripcion,
            JTextField precioPublico,
            JTextField precioDescuento,
            Integer tipoBusca) throws MIError {
        try {
            conec = new Conexion();
            sintaxiSql = null;
            switch (tipoBusca) {
                case 1:
                    /*SINTAXIS POR ID*/
                    sintaxiSql = "SELECT "
                            + "  p.id, "
                            + "  p.ProductoDescripcion, "
                            + "  pr.PrecioPublico AS 'Precio',"
                            + "  pr.PrecioReferencia AS 'PrecioCosto' "
                            + "FROM "
                            + "  producto p "
                            + "  INNER JOIN preciolista pr "
                            + "    ON p.id = pr.Producto_id "
                            + "    AND pr.TipoCliente_id=1 "
                            + "  WHERE p.ID = '" + productoExpres.txtCodigo.getText().trim() + "' ORDER BY p.id ;";
                    break;
                case 2:
                    /*SINTAXIS POR DESCRIPCION*/
                    sintaxiSql = "SELECT "
                            + "  p.id, "
                            + "  p.ProductoDescripcion, "
                            + "  pr.PrecioPublico AS 'Precio',"
                            + "  pr.PrecioReferencia AS 'PrecioCosto' "
                            + " FROM "
                            + "  producto p "
                            + "  INNER JOIN preciolista pr "
                            + "    ON p.id = pr.Producto_id "
                            + "    AND pr.TipoCliente_id=1 "
                            + "  WHERE ProductoDescripcion= '" + productoExpres.txtDescripcion.getText().trim() + "' ORDER BY p.id ;";
                    // + "WHERE ProductoDescripcion= '" + Ventas.txtProducto.getText().trim() + "' ORDER BY id;";
                    break;
            }
            System.out.print(sintaxiSql);
            ps = conec.getConexion().prepareStatement(sintaxiSql);
            rs = ps.executeQuery();
            if (rs.next()) {
                id.setText(rs.getString(1));
                descripcion.setText(rs.getString(2));
                precioPublico.setText(rs.getString(3));
                precioDescuento.setText(rs.getString(4));
                productoExpres.txtPresentacion.requestFocus();
            } else {
                JOptionPane.showMessageDialog(productoExpres, "Codigo ingresado no existe");
                limpiarTexto(ProductosExpresCtrol.paneles.detalle);
                productoExpres.txtCodigo.requestFocus();
            }
        } catch (SQLException ex) {
            // Logger.getLogger(PedidosControl.class.getName()).log(Level.SEVERE, null, ex);
            MiMsM = "Ocurrio un error en el metodo recuperarDatosProductos";
            throw new MIError(MiMsM, ex);
        }
    }

    
    





    public ResultSet resulseTabla(tablas t) {
        try {
            conec = new Conexion();
            sintaxiSql = null;
            switch (t) {
                case clientes:
                    sintaxiSql = "SELECT Documento,Nombre,Apellido FROM cliente ORDER BY Apellido ;";
                    break;
                case producto:
                    sintaxiSql = "SELECT  id, ProductoDescripcion, PrecioPublico, PrecioCosto FROM producto  ORDER BY id;";
                    break;
                    
                case idVenta:
                    sintaxiSql =" SELECT MAX(id)+1 FROM ventas;";
                        break;
            }
            ps = conec.getConexion().prepareStatement(sintaxiSql);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(PedidosControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public void llamarBuscador() throws MIError {
        try {
            setearObjeto objeto = new setearObjeto();
//            new Buscador(productoExpress, true, resulseTabla(tablas.clientes), new int[]{0, 1}, objeto).setVisible(true);
//            productoExpress.txtRuc.setText(objeto.getCodigo().toString());
        } catch (Exception e) {
            MiMsM = "Ocurrio un error en el metodo llamarBuscador ";
            throw new MIError(MiMsM, e);
        }
    }
    
        
    public void cargarComboFraccion() {
        try {
            constCboVendedor = new ConstructorCombo(resulseCbo(tablas.vendedor));

            for (int i = 0; i < constCboVendedor.getRegistrosCombo().size(); i++) {
                productoExpres.cboFraccion.addItem(constCboVendedor.getRegistrosCombo().get(i).getDesCombo());
            }
        } catch (MIError ex) {
            Logger.getLogger(ProductosExpresCtrol.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ResultSet resulseCbo(tablas tcbo) throws MIError {
        try {
            sintaxiSql = null;
            switch (tcbo) {
                case vendedor:
                    sintaxiSql = "SELECT id, Nombres FROM empleado ORDER BY id;";
                    break;
                case condicionVenta:
                    sintaxiSql = "SELECT ID, Descripcion FROM condicionventa ORDER BY 1;";
                    break;
            }
            conec = new Conexion();
            ps = conec.getConexion().prepareStatement(sintaxiSql);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            MiMsM = "Ocurrio un error en el  resulseCbo ";
            throw new MIError(MiMsM, ex);
        }
        return rs;
    }

    public void seguirAgregando() throws MIError {
        if (productoExpres.chkAgregando.isSelected()) {
            condicionVenta = 1;
        }
        if (productoExpres.chkAgregando.isSelected()) {
            condicionVenta = 2;
        }

        Connection cnn;
        String resultado;
        try {
            cnn = ConexionSP.Conectar();
            CallableStatement cmst = cnn.prepareCall(InsertarCabecera);
//            cmst.setString(1, productoExpress.txtIdVenta.getText().trim());          //p_id INT 
            //cmst.setString(2, Ventas.txtFecha.getText().trim());            //p_FechaVenta DATETIME
//            cmst.setString(2, productoExpress.txtRuc.getText().trim());              //p_Cliente_id INT 
//            cmst.setInt(3, constCboCondicionVenta.getCodigoActual(Ventas.cboCondicionVenta.getSelectedIndex()));
            cmst.setInt(3, condicionVenta);/*constCboCondicionVenta*/

//            cmst.setString(4, Ventas.txtNumeroFactura.getText().trim());    //p_FacturaNumero VARCHAR(20)
//            cmst.setString(4, productoExpress.txtSucursal.getText().trim());         //p_AperturaCierre_id BIGINT(10) /**/
//            cmst.setString(5, productoExpress.txtSucursal.getText().trim());         //p_Talonario_id INT
//            cmst.setInt(6, constCboVendedor.getCodigoActual(productoExpress.cboVendedor.getSelectedIndex()));

            System.out.println(cmst);
            cmst.execute();
            resultado = "Los datos se ingresaron exitosamente !!!";
            //JOptionPane.showMessageDialog(Ventas, resultado);
            cnn.close();

        } catch (SQLException ex) {
            MiMsM = "Ocurrio un error en el metodo alta de la tabla Ventas Cabecera ";
            throw new MIError(MiMsM, ex);
            //Logger.getLogger(ControlCiudad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    public void recupararDatos(tablas t, JTextField txt) {
        Conexion conec = new Conexion();
        String sintaxiSql = null;
        PreparedStatement ps;
        ResultSet rs = null;
        try {

            switch (t) {
                case clientes:

                    sintaxiSql = "SELECT Documento,Nombre,Apellido "
                            + "FROM cliente WHERE Documento='" + productoExpres.txtCodigo.getText().trim() + "'  ORDER BY Apellido ;";
                    break;

            }

            ps = conec.getConexion().prepareStatement(sintaxiSql);
            rs = ps.executeQuery();
            if (rs.next()) {
                txt.setText(rs.getString(2));
            } else {
                JOptionPane.showMessageDialog(productoExpres, "Codigo Ingresado no existe");
            }

        } catch (SQLException ex) {
            //Logger.getLogger(VentasP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public void altaCabecera() throws MIError {
        
        Connection cnn;
        String resultado="";
        filasAfectadas=1;

        try {
            cnn = ConexionSP.Conectar();
            CallableStatement cmst = cnn.prepareCall(InsertarCabecera);
            cmst.setString(1, productoExpres.txtDescripcion.getText().trim());                     
            cmst.setString(2, productoExpres.txtCodigoBarra.getText().trim());            
            
            cmst.setInt(3, 1); 
            //  productoExpres.txtNacionalidadCodigo.getText().trim());           /*p_Nacionalidad_id INT(11)*/
            cmst.setInt(4, 3) ;//  productoExpres.txtPresentacion.getText().trim());           /*p_Presentacion_id INT(11)*/ 
            cmst.setInt(5, 1);           /*p_DivisionLaboratorio_id INT(11)*/ 
            cmst.setInt(6, 3);                                                            /*, p_Laboratorio_id INT(11)*/ 
            cmst.setInt(7, 5);                                                            /*, p_Laboratorio_id INT(11)*/ 
            System.out.println("*****Antescmst******");
            System.out.println(cmst);
            System.out.println("******Fincmst*****");
            //cmst.execute();

            filasAfectadas =cmst.executeUpdate();
            if (filasAfectadas >= 0) 
            {
                resultado = "Los datos se ingresaron exitosamente -Productos Express!!!";
            } else 
            {
                resultado = "Los datos se NO ingresaron!!!altaCabecera - Productos Express";
                System.out.println("else"+filasAfectadas);
                JOptionPane.showMessageDialog(productoExpres, resultado);
            }            
            
            cnn.close();

        } catch (SQLException ex) {
            MiMsM = "Ocurrio un error en el metodo alta del Formulario ProductosExpress";
            throw new MIError(MiMsM, ex);
//            //Logger.getLogger(ControlCiudad.class.getName()).log(Level.SEVERE, null, ex);
        }           
    }

}
