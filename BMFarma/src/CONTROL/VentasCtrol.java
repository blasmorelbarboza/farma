package CONTROL;

import FORMULARIOS.VentasP;
import GENERICOS.Buscador;
import GENERICOS.Conexion;
import GENERICOS.ConexionSP;
import GENERICOS.ConstructorTabla;
import GENERICOS.ConstructorCombo;
import GENERICOS.MIError;
import GENERICOS.setearObjeto;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Font;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.table.TableModel;
import GENERICOS.Utiles;
import GENERICOS.ValidarVacios;
import Genericos.FormatoTextos;
import Genericos.PropiedadesTextos;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class VentasCtrol {

    private VentasP Ventas;
    private Conexion conec;
    private String sintaxiSql;
    private String MiMsM;
    private PreparedStatement ps;
    private ResultSet rs;
    private ConstructorTabla grilla;
    private NumberFormat nf = NumberFormat.getNumberInstance();
    public Utiles utiles;

    private FormatoTextos formatoTxt;
    private PropiedadesTextos propiedadesTxt;

    private ConstructorCombo constCboVendedor;
    private ValidarVacios textoVacio;

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

    private String InsertarCabecera = "CALL VentasCab_Ins(?,?,?,?,?,?);";
    private String InsertarDetalle = "call VentasDetalle_Ins (?,?,?,?,?);";
    private int condicionVenta = 0;

    public VentasCtrol(JDialog dialogoPadre) {
        Ventas = (VentasP) dialogoPadre;
        utiles = new Utiles();
        formatoTxt = new FormatoTextos();
        textoVacio =new ValidarVacios();

    }

    public void initControlers() throws MIError {
        try {
            HabilitarBotones(estados.activo);
            HabilitarTexto(estados.inactivo, paneles.todos);
            limpiarTexto(paneles.todos);
            cargarComboVendedor();
            // tabla = new ConstructorTabla(new Object[]{"COD.", "DESCRIPCION", "PRECIO PUBLIC", "%", "CANT", "PREC_COSTO", "SUBTOTAL"});
            grilla = new ConstructorTabla(new Object[]{"VTA", "CODIGO", "DESCRIPCION", "PRECIO PUBLIC", "CANT", "CON DESCUENTO", "SUBTOTAL"});
            //Ventas.grlProductos.setModel(tabla);
            Ventas.grlProductos.setModel((TableModel) grilla);
            configurarGrilla();

            //utiles.prepararDateField(pedidos.txtFechaFactura);
            Ventas.txtfFecha.setText(utiles.fechaServidor());
            itemsAutoCompletado();
        } catch (MIError ex) {
            MiMsM = "Ocurrio un error en el metodo  initControlers ";
            throw new MIError(MiMsM, ex);
        }
    }

    
    
    public void validarTextos() {
    Object[] camposObligatorios = {Ventas.txtIdVenta.getText()
                            , Ventas.txtCodigo.getText()
                            , Ventas.txtPrecioPublico.getText()
                            , Ventas.txtPorcentaje.getText()
                            , Ventas.txtCantidad.getText()
                            , Ventas.txtDescuentoMonto.getText()
//                            ,Ventas.txtProducto.getText()
            
                };
    
    textoVacio.validar(camposObligatorios);
    
                        if (textoVacio.vacioAviso==1) {
                            
                            Ventas.txtIdVenta.setBorder(new LineBorder(Color.RED)); 
                            Ventas.txtCodigo.setBorder(new LineBorder(Color.RED)); 
                            Ventas.txtPrecioPublico.setBorder(new LineBorder(Color.RED)); 
                            Ventas.txtPorcentaje.setBorder(new LineBorder(Color.RED)); 
                            Ventas.txtCantidad.setBorder(new LineBorder(Color.RED)); 
                            Ventas.txtDescuentoMonto.setBorder(new LineBorder(Color.RED)); 
                            Ventas.txtProducto.setBorder(new LineBorder(Color.RED)); 
         
                            }
    

    }

    public void HabilitarBotones(estados estadoBotones) {
        switch (estadoBotones) {
            case activo:
                Ventas.btnNuevo.setEnabled(true);
                Ventas.btnAnular.setEnabled(true);
                Ventas.btnGuardar.setEnabled(false);
                Ventas.btnCancelar.setEnabled(false);
                Ventas.btnAgregar.setEnabled(false);
                Ventas.btnRemover.setEnabled(false);
                //  Ventas.btnSalir.setEnabled(true);

                break;
            case inactivo:
                Ventas.btnNuevo.setEnabled(false);
                Ventas.btnAnular.setEnabled(false);
                Ventas.btnGuardar.setEnabled(false);
                Ventas.btnCancelar.setEnabled(true);
//                Ventas.btnAgregar.setEnabled(true);
                Ventas.btnRemover.setEnabled(false);
                //Ventas.btnSalir.setEnabled(false);
                break;
        }
    }

    public void limpiarTexto(paneles p) throws MIError {
        try {

            switch (p) {

                case cabecera:
                    // Ventas.txtFecha.setText("2018-01-17 18:20:48");
                    Ventas.txtfFecha.setText(utiles.fechaServidor().toString());

                    Ventas.txtRuc.setText("0");
                    Ventas.lblNombreVendedor.setText("VENDEDOR..");
                    Ventas.txtIdVenta.setText("0");
                    break;
                case detalle:
                    Ventas.txtCodigo.setText("0");
                    Ventas.txtPorcentaje.setText("0");
                    Ventas.txtDescuentoMonto.setText("0");
                    Ventas.txtCantidad.setText("0");
                    Ventas.txtPrecioPublico.setText("0");
                    Ventas.txtSubtotal.setText("0");
                    Ventas.txtProducto.setText("");

//                Ventas.txtIva5.setText("0");
//                Ventas.txtIva10.setText("0");
//                Ventas.txtTotal.setText("0");
                    break;
                case todos:
                    // Ventas.txtFecha.setText("2018-01-17 18:20:48");

                    Ventas.txtfFecha.setText(utiles.fechaServidor().toString());

                    Ventas.txtRuc.setText("0");
                    Ventas.lblNombreVendedor.setText("VENDEDOR..");

                    Ventas.txtIdVenta.setText("0");
                    Ventas.txtCodigo.setText("0");
                    Ventas.txtPorcentaje.setText("0");
                    Ventas.txtDescuentoMonto.setText("0");
                    Ventas.txtCantidad.setText("0");
                    Ventas.txtPrecioPublico.setText("0");
                    Ventas.txtSubtotal.setText("0");
                    Ventas.txtProducto.setText("");

                    Ventas.txtIva5.setText("0");
                    Ventas.txtIva10.setText("0");
                    Ventas.txtTotal.setText("0");

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
                        Ventas.txtfFecha.setEnabled(false);

                        Ventas.txtRuc.setEnabled(false);
                        Ventas.txtRazonSocial.setEnabled(false);

                        Ventas.txtIdVenta.setEnabled(false);

                        Ventas.cboVendedor.setEnabled(false);

                        Ventas.opcContado.setEnabled(false);
                        Ventas.opcCredito.setEnabled(false);
                        break;
                    case detalle:
                        Ventas.txtCodigo.setEnabled(false);
                        Ventas.txtPorcentaje.setEnabled(false);
                        Ventas.txtDescuentoMonto.setEnabled(false);
                        Ventas.txtCantidad.setEnabled(false);
                        Ventas.txtPrecioPublico.setEnabled(false);
                        Ventas.txtSubtotal.setEnabled(false);
                        Ventas.txtProducto.setEnabled(false);

                        Ventas.txtIva5.setEnabled(false);
                        Ventas.txtIva10.setEnabled(false);
                        Ventas.txtTotal.setEnabled(false);
                        break;
                    case todos:
                        Ventas.txtfFecha.setEnabled(false);

                        Ventas.txtRuc.setEnabled(false);
                        Ventas.txtRazonSocial.setEnabled(false);
                        Ventas.txtIdVenta.setEnabled(false);

                        Ventas.cboVendedor.setEnabled(false);

                        Ventas.txtCodigo.setEnabled(false);
                        Ventas.txtPorcentaje.setEnabled(false);
                        Ventas.txtDescuentoMonto.setEnabled(false);
                        Ventas.txtCantidad.setEnabled(false);
                        Ventas.txtPrecioPublico.setEnabled(false);
                        Ventas.txtSubtotal.setEnabled(false);
                        Ventas.txtProducto.setEnabled(false);
                        Ventas.txtIva5.setEnabled(false);
                        Ventas.txtIva10.setEnabled(false);
                        Ventas.txtTotal.setEnabled(false);

                        Ventas.opcContado.setEnabled(false);
                        Ventas.opcCredito.setEnabled(false);
                        break;
                }
                break;
            case activo:
                switch (HabPaneles) {
                    case cabecera:
//                        Ventas.txtFecha.setEnabled(true);

                        Ventas.txtRuc.setEnabled(true);
                        Ventas.txtRazonSocial.setEnabled(true);
                        Ventas.txtIdVenta.setEnabled(true);

                        Ventas.cboVendedor.setEnabled(true);

                        Ventas.opcContado.setEnabled(true);
                        Ventas.opcCredito.setEnabled(true);
                        Ventas.opcContado.doClick();

                        break;
                    case detalle:
                        Ventas.txtCodigo.setEnabled(true);
                        Ventas.txtPorcentaje.setEnabled(true);
                        Ventas.txtDescuentoMonto.setEnabled(true);
                        Ventas.txtCantidad.setEnabled(true);
//                        Ventas.txtPrecioPublico.setEnabled(true);
//                        Ventas.txtSubtotal.setEnabled(true);
                        Ventas.txtProducto.setEnabled(true);
//                        Ventas.txtIva5.setEnabled(true);
//                        Ventas.txtIva10.setEnabled(true);
//                        Ventas.txtTotal.setEnabled(true);
                        break;

                    case todos:
//                        Ventas.txtFecha.setEnabled(true);

                        Ventas.txtRuc.setEnabled(true);
                        Ventas.txtRazonSocial.setEnabled(true);
                        Ventas.txtIdVenta.setEnabled(true);

                        Ventas.cboVendedor.setEnabled(true);

                        Ventas.txtCodigo.setEnabled(true);
                        Ventas.txtPorcentaje.setEnabled(true);
                        Ventas.txtDescuentoMonto.setEnabled(true);
                        Ventas.txtCantidad.setEnabled(true);
//                        Ventas.txtPrecioPublico.setEnabled(true);
//                        Ventas.txtSubtotal.setEnabled(true);
                        Ventas.txtProducto.setEnabled(true);
//                        Ventas.txtIva5.setEnabled(true);
//                        Ventas.txtIva10.setEnabled(true);
//                        Ventas.txtTotal.setEnabled(true);

                        Ventas.opcContado.setEnabled(true);
                        Ventas.opcCredito.setEnabled(true);
                        Ventas.opcContado.doClick();
                        break;
                }
                break;
        }
    }

    public void itemsAutoCompletado() /* throws MIError*/ {
        TextAutoCompleter AutoAcompletado = new TextAutoCompleter(Ventas.txtProducto);
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
                            + "  WHERE p.ID = '" + Ventas.txtCodigo.getText().trim() + "' ORDER BY p.id ;";
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
                            + "  WHERE ProductoDescripcion= '" + Ventas.txtProducto.getText().trim() + "' ORDER BY p.id ;";
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
                Ventas.txtCantidad.requestFocus();
            } else {
                JOptionPane.showMessageDialog(Ventas, "Codigo ingresado no existe");
                limpiarTexto(VentasCtrol.paneles.detalle);
                Ventas.txtCodigo.requestFocus();
            }
        } catch (SQLException ex) {
            // Logger.getLogger(PedidosControl.class.getName()).log(Level.SEVERE, null, ex);
            MiMsM = "Ocurrio un error en el metodo recuperarDatosProductos";
            throw new MIError(MiMsM, ex);
        }
    }

    public void limpiarGrilla() {
        grilla.LimpiarTabla();
    }
    public void agregarNuevaFila() throws MIError {
        
        try {
            if (utiles.verificarTxtVacioCero(Ventas.txtCodigo)) {

                Integer id, codigoProd, cantidad, precioPublico, descuentoPorc, montoDescuento, subTotal;
                String descriproducto;

                id = Integer.parseInt(Ventas.txtIdVenta.getText().trim());
                codigoProd = Integer.parseInt(Ventas.txtCodigo.getText().trim());
                descriproducto = Ventas.txtProducto.getText().trim();
                precioPublico = Integer.parseInt(Ventas.txtPrecioPublico.getText().trim());
                descuentoPorc = Integer.parseInt(Ventas.txtPorcentaje.getText().trim());
                cantidad = Integer.parseInt(Ventas.txtCantidad.getText().trim());
                montoDescuento = Integer.parseInt(Ventas.txtDescuentoMonto.getText().trim());
                subTotal = Integer.parseInt(Ventas.txtSubtotal.getText().trim());
                subTotal = cantidad * montoDescuento;

                grilla.agregarFila(new Object[]{
                    id, // {"VTA",
                    codigoProd, //, "CODIGO"
                    descriproducto, //, "DESCRIPCION"
                    nf.format(precioPublico), //, "PRECIO PUBLIC"
                    //            descuentoPorc,        //
                    nf.format(cantidad), //, "CANT"
                    nf.format(montoDescuento), //, "CON DESCUENTO"        
                    nf.format(subTotal), //, "SUBTOTAL"}
                    false});
                refrescarDatosGrilla();
                //tabla = new ConstructorTabla(new Object[]
            } else {
                Ventas.txtCodigo.requestFocus();
            }
        } catch (Exception e) {
            MiMsM = "Ocurrio un error en el metodo agregarNuevaFila";
            throw new MIError(MiMsM, e);
        }

    }

    public void agregarFilaDefault() {
        grilla.agregarFila(new Object[]{0, 0, "Prod_Generico", 0, 0, 0});
    }

    private void refrescarDatosGrilla() throws MIError {
        try {
            Double montoTotal = 0.0;
            Double iva5 = 0.0, iva10 = 0.0;
            for (int i = 0; i < grilla.recuperarNroFilas(); i++) {
                montoTotal = montoTotal + Double.parseDouble(Ventas.grlProductos.getValueAt(i, 6).toString().replace(".", ""));
            }
            iva10 = montoTotal / 11;
            iva5 = montoTotal / 21;

            Ventas.txtTotal.setText(nf.format(montoTotal));

            Ventas.txtIva5.setText(nf.format(iva5));
            Ventas.txtIva10.setText(nf.format(iva10));

        } catch (Exception e) {
            MiMsM = "Ocurrio un error en el metodo refrescarDatosGrilla";
            throw new MIError(MiMsM, e);
        }
    }

    public void eliminarFila() {
        int fila = Ventas.grlProductos.getSelectedRow();
        if (fila >= 0) {
            if (Ventas.grlProductos.getRowCount() == 0) {
                grilla.removeRow(fila);
//             agregarFilaDefault();
                Ventas.btnRemover.setEnabled(false);
                Ventas.btnGuardar.setEnabled(false);
            } else {
                grilla.removeRow(fila);
                if (Ventas.grlProductos.getRowCount() == 0) {
                    Ventas.btnRemover.setEnabled(false);
                    Ventas.btnGuardar.setEnabled(false);
                }
            }
            // pedidos.tablaDetalle.grabFocus();
            Ventas.grlProductos.changeSelection(fila - 1, 0, false, false);
            try {
                refrescarDatosGrilla();
            } catch (MIError ex) {
                Logger.getLogger(VentasCtrol.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(Ventas, "Seleccione Item a Remover");
        }
    }

    private void configurarGrilla() throws MIError {
        try {
            Ventas.grlProductos.setRowHeight(17);
            Ventas.grlProductos.setFont(new Font("tahoma", 1, 12));
            Ventas.grlProductos.getColumnModel().getColumn(0).setPreferredWidth(5);
            Ventas.grlProductos.getColumnModel().getColumn(1).setPreferredWidth(5);
            Ventas.grlProductos.getColumnModel().getColumn(2).setPreferredWidth(400);
            Ventas.grlProductos.getColumnModel().getColumn(3).setPreferredWidth(40);
            Ventas.grlProductos.getColumnModel().getColumn(4).setPreferredWidth(10);
            Ventas.grlProductos.getColumnModel().getColumn(5).setPreferredWidth(40);
            //oculatar columnas
            //        pedidos.tablaDetalle.getColumnModel().getColumn(0).setPreferredWidth(0);
            //        pedidos.tablaDetalle.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
            //        pedidos.tablaDetalle.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
            Ventas.grlProductos.getTableHeader().setReorderingAllowed(false);  // para no reordenar la columna
            Ventas.grlProductos.getTableHeader().setResizingAllowed(false);
        } catch (Exception e) {
            MiMsM = "Ocurrio un error en el metodo configurarGrilla ";
            throw new MIError(MiMsM, e);
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
            new Buscador(Ventas, true, resulseTabla(tablas.clientes), new int[]{0, 1}, objeto).setVisible(true);
            Ventas.txtRuc.setText(objeto.getCodigo().toString());
        } catch (Exception e) {
            MiMsM = "Ocurrio un error en el metodo llamarBuscador ";
            throw new MIError(MiMsM, e);
        }
    }
    
        public void idVentas() throws MIError {
        try {
            
            // 
            
            conec = new Conexion();
            sintaxiSql = null;
            // sintaxiSQL = "SELECT produ_codigo,produ_descripcion,produ_precio_vent FROM productos WHERE produ_codigo = " + pedidos.txtCodigoProducto.getText().trim() + " ORDER BY produ_codigo;";
            sintaxiSql = "SELECT MAX(id)+1 AS\"IDx\" FROM ventas;";
            ps = conec.getConexion().prepareStatement(sintaxiSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                Ventas.txtIdVenta.setText(rs.getString(1));
            }
            
            
        } catch (Exception e) {
            MiMsM = "Ocurrio un error en el metodo idVentas()";
            throw new MIError(MiMsM, e);
        }
    }

    public void cargarComboVendedor() {
        try {
            constCboVendedor = new ConstructorCombo(resulseCbo(tablas.vendedor));

            for (int i = 0; i < constCboVendedor.getRegistrosCombo().size(); i++) {
                Ventas.cboVendedor.addItem(constCboVendedor.getRegistrosCombo().get(i).getDesCombo());
            }
        } catch (MIError ex) {
            Logger.getLogger(VentasCtrol.class.getName()).log(Level.SEVERE, null, ex);
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

    public void altaCabecera() throws MIError {
        if (Ventas.opcContado.isSelected()) {
            condicionVenta = 1;
        }
        if (Ventas.opcCredito.isSelected()) {
            condicionVenta = 2;
        }

        Connection cnn;
        String resultado;
        try {
            cnn = ConexionSP.Conectar();
            CallableStatement cmst = cnn.prepareCall(InsertarCabecera);
            cmst.setString(1, Ventas.txtIdVenta.getText().trim());          //p_id INT 
            //cmst.setString(2, Ventas.txtFecha.getText().trim());            //p_FechaVenta DATETIME
            cmst.setString(2, Ventas.txtRuc.getText().trim());              //p_Cliente_id INT 
//            cmst.setInt(3, constCboCondicionVenta.getCodigoActual(Ventas.cboCondicionVenta.getSelectedIndex()));
            cmst.setInt(3, condicionVenta);/*constCboCondicionVenta*/

//            cmst.setString(4, Ventas.txtNumeroFactura.getText().trim());    //p_FacturaNumero VARCHAR(20)
            cmst.setString(4, Ventas.txtSucursal.getText().trim());         //p_AperturaCierre_id BIGINT(10) /**/
            cmst.setString(5, Ventas.txtSucursal.getText().trim());         //p_Talonario_id INT
            cmst.setInt(6, constCboVendedor.getCodigoActual(Ventas.cboVendedor.getSelectedIndex()));

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

    public void altaDetalleSP() throws MIError {
        Connection cnn;
        String resultado;
        try {
            cnn = ConexionSP.Conectar();
            CallableStatement cmst = cnn.prepareCall(InsertarDetalle);
            for (int fila = 0; fila < grilla.recuperarNroFilas(); fila = fila + 1) {

                cmst.setString(1, grilla.getValueAt(fila, 0).toString().replace(".", ""));
                cmst.setString(2, grilla.getValueAt(fila, 1).toString().replace(".", ""));
                cmst.setString(3, grilla.getValueAt(fila, 4).toString().replace(".", ""));
                cmst.setString(4, grilla.getValueAt(fila, 5).toString().replace(".", ""));
                cmst.setString(5, grilla.getValueAt(fila, 6).toString().replace(".", ""));
                System.out.println(cmst);
                cmst.execute();

            }

            resultado = "Los datos se ingresaron exitosamente !!!";
            JOptionPane.showMessageDialog(Ventas, resultado);
            cnn.close();

        } catch (SQLException ex) {
            MiMsM = "Ocurrio un error en el metodo alta Detalle de la tabla VentasDetalle";
            throw new MIError(MiMsM, ex);
            //Logger.getLogger(ControlCiudad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void subTotal() {
        Integer precio = 0, cantidad = 0, subTotal = 0;
        precio = Integer.parseInt(Ventas.txtDescuentoMonto.getText().trim());

        if (Ventas.txtCantidad.getText().isEmpty()) {
            cantidad = 0;

        } else {
            cantidad = Integer.parseInt(Ventas.txtCantidad.getText().trim());
        }
        subTotal = cantidad * precio;
//        Integer iva5=0, iva10=0;
//        iva10 = subTotal / 11;
//        iva5 = subTotal / 21;
        Ventas.txtSubtotal.setText(subTotal.toString());
//        Ventas.txtIva5.setText(iva5.toString());
//        Ventas.txtIva10.setText(iva10.toString());
    }

    public void descuentos(descuentosTipos descuent, JTextField txtJporMonto, JTextField txtJporcentaje) {
        Double precioPublico = 0.0, precioDescuento = 0.0, porcentajaDescuento = 0.0;
        //Ventas.txtIva10.setText(nf.format(iva10));                        
        switch (descuent) {
            case porcentaje:
                /*id.setText(rs.getString(1));*/
 /*Precio desc =	p.pub- (P.Pub * %)/100*/
                precioPublico = Double.parseDouble(Ventas.txtPrecioPublico.getText());
                porcentajaDescuento = Double.parseDouble(Ventas.txtPorcentaje.getText());
                precioDescuento = precioPublico - ((precioPublico * porcentajaDescuento) / 100.0);

                precioDescuento = utiles.Redondear(precioDescuento, 0);
                Ventas.txtDescuentoMonto.setText(precioDescuento.toString());
                // System.out.println("descuentos(porMonto)>>:"+precioDescuento);

                break;

            case porMonto:
                /*desc % = 100- (precio desc *100)/ P.Publico	*/
                precioPublico = Double.parseDouble(Ventas.txtPrecioPublico.getText());
                precioDescuento = Double.parseDouble(Ventas.txtDescuentoMonto.getText());
                porcentajaDescuento = Double.parseDouble(Ventas.txtPorcentaje.getText());
                porcentajaDescuento = 100.0 - ((precioDescuento * 100.0) / precioPublico);

                porcentajaDescuento = utiles.Redondear(porcentajaDescuento, 0);
                Ventas.txtPorcentaje.setText(porcentajaDescuento.toString());
                System.out.println("descuentos(porcentaje)>>:" + porcentajaDescuento);

                break;
        }

    }

    public void descuentosInt(descuentosTipos descuent, JTextField txtJporMonto, JTextField txtJporcentaje) {
        int precioPublico = 0, precioDescuento = 0, porcentajaDescuento = 0;
        //Ventas.txtIva10.setText(nf.format(iva10));                        
        switch (descuent) {
            case porcentaje:
                /*id.setText(rs.getString(1));*/
 /*Precio desc =	p.pub- (P.Pub * %)/100*/
                precioPublico = Integer.parseInt(Ventas.txtPrecioPublico.getText());
                porcentajaDescuento = Integer.parseInt(Ventas.txtPorcentaje.getText());
                precioDescuento = precioPublico - ((precioPublico * porcentajaDescuento) / 100);

                System.out.println("descuentosINT(porcentaje)>>:" + precioDescuento);
                // setText(rs.getString(3));
                Ventas.txtDescuentoMonto.setText(nf.format(precioDescuento));

                break;

            case porMonto:
                /*desc % = 100- (precio desc *100)/ P.Publico	*/
                precioPublico = Integer.parseInt(Ventas.txtPrecioPublico.getText());
                precioDescuento = Integer.parseInt(Ventas.txtDescuentoMonto.getText());
                porcentajaDescuento = Integer.parseInt(Ventas.txtPorcentaje.getText());
                porcentajaDescuento = 100 - ((precioDescuento * 100) / precioPublico);

                //Ventas.txtPorcentaje.setText(porcentajaDescuento.toString());
                Ventas.txtPorcentaje.setText(nf.format(porcentajaDescuento));
                System.out.println("descuentosINT(porMonto)>>:" + porcentajaDescuento);

                break;
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
                            + "FROM cliente WHERE Documento='" + Ventas.txtRuc.getText().trim() + "'  ORDER BY Apellido ;";
                    break;

            }

            ps = conec.getConexion().prepareStatement(sintaxiSql);
            rs = ps.executeQuery();
            if (rs.next()) {
                txt.setText(rs.getString(2));
            } else {
                JOptionPane.showMessageDialog(Ventas, "Codigo Ingresado no existe");
            }

        } catch (SQLException ex) {
            Logger.getLogger(VentasP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
