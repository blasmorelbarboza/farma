package CONTROL;

import FORMULARIOS.Compras;
import GENERICOS.Conexion;
import GENERICOS.ConexionSP;
import GENERICOS.ConstructorTabla;
import GENERICOS.HabilitarTextosPanel;
import GENERICOS.LimpiarTextosPanel;
import GENERICOS.MIError;
import GENERICOS.Utiles;
import GENERICOS.ValidarVacios;
import Genericos.FormatoTextos;
import Genericos.PropiedadesTextos;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Color;
import java.awt.Font;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;

public class ComprasCtrol {

    private Compras Compra;
    private ConstructorTabla grilla;
    private Conexion conec;
    private String MiMsM;
    private String sintaxiSql;
    private FormatoTextos formatoTxt;
    private PropiedadesTextos propiedadesTxt;
    private PreparedStatement ps;
    private ResultSet rs;
    private int condicionCompra = 0;
    private int filasAfectadas;
    private ValidarVacios validarTextoVacio;
    private Utiles utiles;
    private LimpiarTextosPanel limpiarPanel;
    private HabilitarTextosPanel habilitarPanel;
    private NumberFormat nf = NumberFormat.getNumberInstance();

    private String InsertarCabecera = "CALL ComprasCab_Ins(?,?,?,?,?);";
    private String InsertarDetalle = "call ComprasDet_Ins(?,?,?,?,?,?);";

    public enum estados {
        activo, pendiente, inactivo
    };

    public enum paneles {
        cabecera, detalle, proveedores, detalleBtn, botonera, todos,
    };

    public ComprasCtrol(JDialog dialogoPadre) {
        Compra = (Compras) dialogoPadre;
        formatoTxt = new FormatoTextos();
        validarTextoVacio = new ValidarVacios();

        limpiarPanel = new LimpiarTextosPanel();
        habilitarPanel = new HabilitarTextosPanel();

    }

    public void initControlers() throws MIError {
        itemsAutoCompletado();
//HabilitarBotones(estados.activo);
        grilla = new ConstructorTabla(new Object[]{"IDCOMPRA", "CODIGO", "DESCRIPCION", "LOTE", "VENCIMIENTO", "PRECIO PUB", "CANT", "PRECIO UNIDAD", "SUBTOTAL"});
        Compra.grlProductos.setModel((TableModel) grilla);

    }

    public void validarTextos() {
        Object[] camposObligatorios
                = {Compra.txtfIdCompra.getText(), Compra.txtCodigo.getText(), Compra.txtfCantidad.getText(), Compra.txtfPrecioCosto.getText(), Compra.txtfRuc.getText()
                };
        validarTextoVacio.validar(camposObligatorios);

        if (validarTextoVacio.vacioAviso == 1) {
            Compra.txtfIdCompra.setBorder(new LineBorder(Color.RED));
            Compra.txtCodigo.setBorder(new LineBorder(Color.RED));
            Compra.txtfCantidad.setBorder(new LineBorder(Color.RED));
            Compra.txtfPrecioCosto.setBorder(new LineBorder(Color.RED));
            Compra.txtfRuc.setBorder(new LineBorder(Color.RED));
            
            
        }
        if (validarTextoVacio.vacioAviso != 1) {
            try {
                this.agregarNuevaFila();
                limpiarTexto(ComprasCtrol.paneles.detalle);

                Compra.txtfIdCompra.setBorder(new LineBorder(Color.GRAY));
                Compra.txtCodigo.setBorder(new LineBorder(Color.GRAY));
                Compra.txtfCantidad.setBorder(new LineBorder(Color.GRAY));
                Compra.txtfPrecioCosto.setBorder(new LineBorder(Color.GRAY));
                Compra.txtfRuc.setBorder(new LineBorder(Color.gray));

            } catch (MIError ex) {
                Logger.getLogger(PedidoInternoCtrol.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        validarTextoVacio.vacioAviso = 0;

    }

    public void habilitarTexto(paneles p, estados estadoTexto) throws MIError {

        try {
            switch (estadoTexto) {
                case activo:
                    switch (p) {
                        case cabecera:

                            habilitarPanel.habilitarCampos(Compra.pnlCabecera);
                            habilitarPanel.habilitarCampos(Compra.pnlProveedor);

//                             Compra.txtfFechaCompra.setText(utiles.fechaServidor().toString());
//                             Compra.txtfFechaOrden.setText(utiles.fechaServidor().toString());
                            break;
                        case detalle:

                            habilitarPanel.habilitarCampos(Compra.pnlDetalle);

                            break;
                        case todos:

//                             Compra.txtfFechaCompra.setText(utiles.fechaServidor().toString());
//                             Compra.txtfFechaOrden.setText(utiles.fechaServidor().toString());
                            habilitarPanel.habilitarCampos(Compra.pnlCabecera);
                            habilitarPanel.habilitarCampos(Compra.pnlDetalle);

                            habilitarPanel.habilitarCampos(Compra.pnlProveedor);
                            habilitarPanel.habilitarCampos(Compra.pnlCondicion);
//                            habilitarPanel.habilitarCampos(Compra.pnlTotal);
                            habilitarPanel.habilitarCampos(Compra.pnlFacturaCompra);
                            //                            
                            break;
                        case proveedores:
                            if (Compra.opcCredito.isSelected()) {
                                condicionCompra = 2;
                                habilitarPanel.habilitarCampos(Compra.pnlCondicion);

                            }
                            break;

                    }
                    break;
                case inactivo:
                    switch (p) {
                        case cabecera:
                            habilitarPanel.deshabilitarCampos(Compra.pnlCabecera);
                            break;
                        case detalle:
                            habilitarPanel.deshabilitarCampos(Compra.pnlDetalle);
                            break;
                        case todos:
                            habilitarPanel.deshabilitarCampos(Compra.pnlCabecera);
                            habilitarPanel.deshabilitarCampos(Compra.pnlDetalle);

                            habilitarPanel.deshabilitarCampos(Compra.pnlProveedor);
                            habilitarPanel.deshabilitarCampos(Compra.pnlCondicion);
                            habilitarPanel.deshabilitarCampos(Compra.pnlTotal);
                            habilitarPanel.deshabilitarCampos(Compra.pnlFacturaCompra);

                            break;
                        case proveedores:
                            if (Compra.opcContado.isSelected()) {
                                condicionCompra = 1;
                                habilitarPanel.deshabilitarCampos(Compra.pnlCondicion);

                            }
                            break;

                    }
                    break;
            }
        } catch (Exception ex) {
            MiMsM = "Ocurrio un error en el metodo  habilitarTexto ";
            throw new MIError(MiMsM, ex);
        }
    }

    public void itemsAutoCompletado() throws MIError {
        TextAutoCompleter AutoAcompletado = new TextAutoCompleter(Compra.txtProducto);
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
            // Logger.getLogger(PedidosControl.class.getName()).log(Level.SEVERE, null, ex);
            MiMsM = "Ocurrio un error en el metodo  itemsAutoCompletado de la tabla Ventas ";
            throw new MIError(MiMsM, ex);
        }
    }

    public void recuperarDatosProductos(JTextField id,
            JTextField descripcion,
            JTextField precioCosto,
            JTextField precioPublico,           
            JTextField elaboracion,
            JTextField vencimiento,
            JTextField laboratorioNomb,
            JTextField divisionLab,
            
            Integer tipoBusca) throws MIError {
        try {
            conec = new Conexion();
            sintaxiSql = null;
            sintaxiSql = "SELECT " +
                            "    pro.id " +
                            "  , pro.ProductoDescripcion"
                            + " , 5000 as 'PrecioCosto' "
                            + " , 10000 as 'PrecioPub' " +
                            " , cast(lp.Elaboracion as date)  " +
                            " , MAX(cast(lp.Vencimiento as date))" +
                            " , lab.NombreLaboratorio" +
                            " , dlab.NombreDivision" +
                            
                            " FROM " +
                            " producto pro" +
                            " INNER JOIN laboratorio lab" +
                            " ON pro.Laboratorio_id=lab.id" +
                            " INNER JOIN divisionlaboratorio dlab" +
                            " ON pro.DivisionLaboratorio_id =dlab.id" +
                            " INNER JOIN loteproducto lp " +
                            " ON pro.id = lp.Producto_id " ;
            switch (tipoBusca) {
                case 1:
                    /*SINTAXIS POR ID*/
                    
                    sintaxiSql = sintaxiSql+" WHERE pro.ID = " + Compra.txtCodigo.getText().trim() +
                            " GROUP BY pro.ID" +" ORDER BY  lp.Vencimiento DESC ;" ;
                    System.out.println(sintaxiSql);
                    break;
                case 2:

                            sintaxiSql = sintaxiSql+" WHERE "+
                            " pro.ProductoDescripcion= '" + Compra.txtProducto.getText().trim() +"'"+ 
                            " GROUP BY pro.ID" +" ORDER BY  lp.Vencimiento DESC ;" ;
                    System.out.println(sintaxiSql);
                    break;
                    
            }
            ps = conec.getConexion().prepareStatement(sintaxiSql);
            rs = ps.executeQuery();
            if (rs.next()) {
                id.setText(rs.getString(1));
                descripcion.setText(rs.getString(2));
                precioCosto.setText(rs.getString(3));
                precioPublico.setText(rs.getString(4));
                elaboracion.setText(rs.getString(5));
                vencimiento.setText(rs.getString(6));
               
                laboratorioNomb.setText(rs.getString(7));
                divisionLab.setText(rs.getString(8));
                
                             
                
            } else {
                JOptionPane.showMessageDialog(Compra, "Codigo ingresado no existe");
                //   limpiarTexto(VentasCtrol.paneles.detalle);
            }
        } catch (SQLException ex) {
            // Logger.getLogger(PedidosControl.class.getName()).log(Level.SEVERE, null, ex);
            MiMsM = "Ocurrio un error en el metodo recuperarDatosProductos";
            throw new MIError(MiMsM, ex);
        }
    }

    public void limpiarTexto(paneles p) throws MIError {
        try {
            switch (p) {

                case cabecera:

                    limpiarPanel.limpiarCampos(Compra.pnlProveedor);
                    //limpiarPanel.limpiarCampos(PedidosInt.pnlPedidoCabecera);
                    break;
                case detalle:
                    limpiarPanel.limpiarCampos(Compra.pnlDetalle);
                    break;

                case todos:
                    limpiarPanel.limpiarCampos(Compra.pnlCabecera);
                    limpiarPanel.limpiarCampos(Compra.pnlProveedor);
                    limpiarPanel.limpiarCampos(Compra.pnlCondicion);

                    limpiarPanel.limpiarCampos(Compra.pnlDetalle);
                    limpiarPanel.limpiarCampos(Compra.pnlTotal);
                    limpiarPanel.limpiarCampos(Compra.pnlFacturaCompra);

                    break;

            }
        } catch (Exception ex) {
            MiMsM = "Ocurrio un error en el metodo  limpiarTexto ";
            throw new MIError(MiMsM, ex);
        }
    }

    public void HabilitarBotones(estados estadoBotones) {
        switch (estadoBotones) {
            case activo:
                Compra.btnNuevo.setEnabled(true);
                Compra.btnEditar.setEnabled(true);
                Compra.btnGuardar.setEnabled(true); // false
                Compra.btnCancelar.setEnabled(false);
                Compra.btnAgregar.setEnabled(true); //false
                Compra.btnRemover.setEnabled(false);
                //  Ventas.btnSalir.setEnabled(true);

                break;
            case inactivo:
                Compra.btnNuevo.setEnabled(false);
                Compra.btnEditar.setEnabled(false);
                Compra.btnGuardar.setEnabled(true);//false
                Compra.btnCancelar.setEnabled(true);
//                Ventas.btnAgregar.setEnabled(true);
                Compra.btnRemover.setEnabled(false);
                //Ventas.btnSalir.setEnabled(false);
                break;
        }
    }

    public void altaCabecera() throws MIError {
        if (Compra.opcContado.isSelected()) {
            condicionCompra = 1;
        }
        if (Compra.opcCredito.isSelected()) {
            condicionCompra = 2;
        }

        Connection cnn;
        String resultado;
        try {
            cnn = ConexionSP.Conectar();
            CallableStatement cmst = cnn.prepareCall(InsertarCabecera);
            cmst.setInt(1, Integer.parseInt(Compra.txtfIdCompra.getText()));
            cmst.setInt(2, Integer.parseInt(Compra.txtfRuc.getText()));
            cmst.setInt(3, condicionCompra);/*constCboCondicionVenta*/
            // cmst.setString(4, Compra.txtSucursal.getText().trim());
            cmst.setString(4, Compra.txtfFacturaCompra.getText().trim());
            cmst.setInt(5, Integer.parseInt(Compra.txtfTimbradoNumero.getText().trim()));
            //cmst.setInt(6, Integer.parseInt( Compra.txtfTimbradoVencimiento.getText().trim()));   

            System.out.println(cmst);

            filasAfectadas = cmst.executeUpdate();
            if (filasAfectadas >= 0) {
                resultado = "Los datos se ingresaron exitosamente !!!";
            } else {
                resultado = "Los datos se NO ingresaron!!!altaCabecera";
                System.out.println("filasAfectadas else" + filasAfectadas);
                JOptionPane.showMessageDialog(Compra, resultado);
            }

            cnn.close();

        } catch (SQLException ex) {
            MiMsM = "Ocurrio un error en el metodo alta de la tabla Compra Cabecera ";
            throw new MIError(MiMsM, ex);
            //Logger.getLogger(ControlCiudad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void altaDetalleSP() throws MIError {
        Connection cnn;
        String resultado = "";
        filasAfectadas = 1;
        try {
            cnn = ConexionSP.Conectar();
            CallableStatement cmst = cnn.prepareCall(InsertarDetalle);
            for (int fila = 0; fila < grilla.recuperarNroFilas(); fila = fila + 1) {

/*PROCEDURE `ComprasDet_Ins` (
  p_CompraCabecera_id INT,
  Producto_id INT ,
  p_cantidad INT ,
  p_Precio INT   
    CALL ComprasDet_Ins(1,1,5,4567) */
/*{"IDCOMPRA", "CODIGO", "DESCRIPCION", "LOTE", 
"VENCIMIENTO", "PRECIO PUB", "CANT", "PRECIO UNIDAD", "SUBTOTAL"}*/
                cmst.setString(1, grilla.getValueAt(fila, 0).toString().replace(".", ""));
                cmst.setString(2, grilla.getValueAt(fila, 1).toString().replace(".", ""));
                cmst.setString(3, grilla.getValueAt(fila, 6).toString().replace(".", ""));
                cmst.setString(4, grilla.getValueAt(fila, 7).toString().replace(".", ""));
                cmst.setString(5, grilla.getValueAt(fila, 3).toString().replace(".", ""));
                cmst.setString(6, grilla.getValueAt(fila, 4).toString().replace(".", ""));
                
                 System.out.println(cmst);
                //cmst.execute();

                filasAfectadas = cmst.executeUpdate();
                if (filasAfectadas >= 0) {
                    resultado = "Los datos se ingresaron exitosamente !!!altaDetalleSP";
                } else {
                    resultado = "Los datos DEL DETALLE NO ingresaron!!!altaDetalleSP";
                    System.out.println("filasAfectadas else" + filasAfectadas);
                }

            }

            JOptionPane.showMessageDialog(Compra, resultado);
            cnn.close();

        } catch (SQLException ex) {
            MiMsM = "Ocurrio un error en el metodo alta Detalle de la tabla CompraDetalle";
            throw new MIError(MiMsM, ex);
            //Logger.getLogger(ControlCiudad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void agregarNuevaFila() throws MIError {

        try {
//            if (utiles.verificarTxtVacioCero(Compra.txtCodigo)) {

            Integer id,
                    codigoProd,
                    cantidad,
                    precioCosto,
                    precioPublico,
                    descuentoPorc,
                    subTotal;
            String descriproducto, vencimiento, lote;

            id = Integer.parseInt(Compra.txtfIdCompra.getText().trim());
            codigoProd = Integer.parseInt(Compra.txtCodigo.getText().trim());
            descriproducto = Compra.txtProducto.getText().trim();
            precioPublico = Integer.parseInt(Compra.txtfPrecioPublico.getText().trim());
            //descuentoPorc = Integer.parseInt(Compra.txtPorcentaje.getText().trim());
            cantidad = Integer.parseInt(Compra.txtfCantidad.getText().trim());
            precioCosto = Integer.parseInt(Compra.txtfPrecioCosto.getText().trim());
            subTotal = Integer.parseInt(Compra.txtfSubTotal.getText().trim());

            vencimiento = Compra.txtfVencimientoProducto.getText().trim();
            lote = Compra.txtLote.getText().trim();

            subTotal = cantidad * precioCosto;

            grilla.agregarFila(new Object[]{
                id, // {"VTA",
                codigoProd, //, "CODIGO"
                descriproducto, //, "DESCRIPCION"
                lote,
                vencimiento,
                nf.format(precioPublico), //, "PRECIO PUBLIC"
                //            descuentoPorc,        //
                nf.format(cantidad), //, "CANT"
                nf.format(precioCosto), //, "CON DESCUENTO"        
                nf.format(subTotal), //, "SUBTOTAL"}
                false});
            refrescarDatosGrilla();
            //tabla = new ConstructorTabla(new Object[]
//            } else {
//                Compra.txtCodigo.requestFocus();
//            }
        } catch (Exception e) {
            MiMsM = "Ocurrio un error en el metodo agregarNuevaFila";
            throw new MIError(MiMsM, e);
        }

    }

    public void agregarFilaDefault() {
        grilla.agregarFila(new Object[]{0, 0, "Prod_Generico", "lote", "01/01/1990", 0, 0, 0});
        /*"IDCOMPRA", "CODIGO", 
        "DESCRIPCION", "LOTE", 
        "VENCIMIENTO", "PRECIO PUB", 
        "CANT", "PRECIO UNIDAD", 
        "SUBTOTAL"});*/
    }

    private void refrescarDatosGrilla() throws MIError {
        try {
            Double montoTotal = 0.0;
            Double iva5 = 0.0, iva10 = 0.0;
            for (int i = 0; i < grilla.recuperarNroFilas(); i++) {
                montoTotal = montoTotal + Double.parseDouble(Compra.grlProductos.getValueAt(i, 6).toString().replace(".", ""));
            }
            iva10 = montoTotal / 11;
            iva5 = montoTotal / 21;

            Compra.txtTotal.setText(nf.format(montoTotal));

            Compra.txtIva5.setText(nf.format(iva5));
            Compra.txtIva10.setText(nf.format(iva10));

        } catch (Exception e) {
            MiMsM = "Ocurrio un error en el metodo refrescarDatosGrilla";
            throw new MIError(MiMsM, e);
        }
    }

    public void eliminarFila() throws MIError {
        try {
            int fila = Compra.grlProductos.getSelectedRow();
            if (fila >= 0) {
                if (Compra.grlProductos.getRowCount() == 0) {
                    grilla.removeRow(fila);
//             agregarFilaDefault();
                    Compra.btnRemover.setEnabled(false);
                    Compra.btnGuardar.setEnabled(false);
                } else {
                    grilla.removeRow(fila);
                    if (Compra.grlProductos.getRowCount() == 0) {
                        Compra.btnRemover.setEnabled(false);
                        Compra.btnGuardar.setEnabled(false);
                    }
                }
                // pedidos.tablaDetalle.grabFocus();
                Compra.grlProductos.changeSelection(fila - 1, 0, false, false);
                try {
                    refrescarDatosGrilla();
                } catch (MIError ex) {
                    Logger.getLogger(VentasCtrol.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(Compra, "Seleccione Item a Remover");
            }
        } catch (Exception e) {
            MiMsM = "Ocurrio un error en el metodo refrescarDatosGrilla";
            throw new MIError(MiMsM, e);
        }
    }

    private void configurarGrilla() throws MIError {
        try {
            Compra.grlProductos.setRowHeight(17);
            Compra.grlProductos.setFont(new Font("tahoma", 1, 12));
            Compra.grlProductos.getColumnModel().getColumn(0).setPreferredWidth(5);
            Compra.grlProductos.getColumnModel().getColumn(1).setPreferredWidth(5);
            Compra.grlProductos.getColumnModel().getColumn(2).setPreferredWidth(400);
            Compra.grlProductos.getColumnModel().getColumn(3).setPreferredWidth(40);
            Compra.grlProductos.getColumnModel().getColumn(4).setPreferredWidth(10);
            Compra.grlProductos.getColumnModel().getColumn(5).setPreferredWidth(40);
            //oculatar columnas
            //        pedidos.tablaDetalle.getColumnModel().getColumn(0).setPreferredWidth(0);
            //        pedidos.tablaDetalle.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
            //        pedidos.tablaDetalle.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
            Compra.grlProductos.getTableHeader().setReorderingAllowed(false);  // para no reordenar la columna
            Compra.grlProductos.getTableHeader().setResizingAllowed(false);
        } catch (Exception e) {
            MiMsM = "Ocurrio un error en el metodo configurarGrilla ";
            throw new MIError(MiMsM, e);
        }
    }

    public void idCompra() throws MIError {
        try {

            // 
            conec = new Conexion();
            sintaxiSql = null;
            // sintaxiSQL = "SELECT produ_codigo,produ_descripcion,produ_precio_vent FROM productos WHERE produ_codigo = " + pedidos.txtCodigoProducto.getText().trim() + " ORDER BY produ_codigo;";
            sintaxiSql = "SELECT MAX(id)+1 AS'IDx' FROM compracabecera;";
            ps = conec.getConexion().prepareStatement(sintaxiSql);
            rs = ps.executeQuery();
            while (rs.next()) {

                Compra.txtfIdCompra.setText(rs.getString(1));
            }

        } catch (Exception e) {
            MiMsM = "Ocurrio un error en el metodo idVentas()";
            throw new MIError(MiMsM, e);
        }
    }
}
