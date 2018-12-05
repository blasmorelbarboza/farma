package CONTROL;

import FORMULARIOS.Compras;
import GENERICOS.Buscador;
import GENERICOS.Conexion;
import GENERICOS.ConexionSP;
import GENERICOS.ConstructorTabla;
import GENERICOS.HabilitarTextosPanel;
import GENERICOS.LimpiarTextosPanel;
import GENERICOS.MIError;
import GENERICOS.Utiles;
import GENERICOS.ValidarVacios;
import GENERICOS.setearObjeto;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;
/**
 *<h1>ComprasCtrol</h1>
 * Es el COntrolador del Formulario Compras
 * 
 */
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

    private LimpiarTextosPanel limpiarPanel;
    private HabilitarTextosPanel habilitarPanel;
    private NumberFormat nf = NumberFormat.getNumberInstance();

    public Utiles utiles;

    private String InsertarCabecera = "CALL ComprasCab_Ins(?,?,?,?,?);";
    private String InsertarDetalle = "call ComprasDet_Ins(?,?,?,?,?,?);";

    public enum estados {
        activo, pendiente, inactivo
    };

    public enum paneles {
        cabecera, detalle, proveedores, detalleBtn, botonera, todos,
    };

    public enum tablas {
        compracabecera, compradetalle, proveedor
    };

    public ComprasCtrol(JDialog dialogoPadre) {
        Compra = (Compras) dialogoPadre;
        formatoTxt = new FormatoTextos();
        validarTextoVacio = new ValidarVacios();

        limpiarPanel = new LimpiarTextosPanel();
        habilitarPanel = new HabilitarTextosPanel();
        utiles = new Utiles();

    }

    public void initControlers() throws MIError {
        try {
            grilla = new ConstructorTabla(new Object[]{"IDCOMPRA", "CODIGO", "DESCRIPCION", "LOTE", "VENCIMIENTO", "PRECIO PUB", "CANT", "PRECIO UNIDAD", "SUBTOTAL"});
            Compra.grlProductos.setModel((TableModel) grilla);
            itemsAutoCompletado();
//HabilitarBotones(estados.activo);
            

        } catch (MIError ex) {
            MiMsM = "Ocurrio un error en el metodo  initControlers ";
            throw new MIError(MiMsM, ex);
        }

    }

    public void llamarBuscadorProveedor() throws MIError {
        try {
            setearObjeto objeto = new setearObjeto();
            new Buscador(Compra, true, resulseTabla(ComprasCtrol.tablas.proveedor), new int[]{ 1,3}, objeto).setVisible(true);
            Compra.txtfRuc.setText(objeto.getCodigo().toString());

            if (objeto.getCodigo() != null) {

                Compra.txtfRuc.setText(objeto.getCodigo().toString());
                recupararDatos(ComprasCtrol.tablas.proveedor, Compra.lbProveedorlNombre);
            }else{
            System.out.print("No seleccionado");
            
            }

        } catch (MIError ex) {
            MiMsM = "Ocurrio un error en el metodo  llamarBuscadorProveedor ";
            throw new MIError(MiMsM, ex);
        }
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
                this.habilitarTexto(paneles.cabecera, estados.inactivo);

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
                            //Compra.txtfFechaCompra.setText(utiles.fechaServidor());

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

                            //   Compra.txtfFechaCompra.setText(utiles.fechaServidor());
                            //habilitarPanel.habilitarCampos(Compra.pnlCondicion);
//                            habilitarPanel.habilitarCampos(Compra.pnlTotal);
                            Compra.opcCredito.setEnabled(true);
                            //                            
                            break;
                        case proveedores:
                            if (Compra.opcCredito.isSelected()) {
                                condicionCompra = 2;
                                habilitarPanel.habilitarCampos(Compra.pnlCondicion);

                            }
                            Compra.opcCredito.setEnabled(true);
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

                            habilitarPanel.deshabilitarCampos(Compra.pnlCondicion);
                            habilitarPanel.deshabilitarCampos(Compra.pnlTotal);
                            Compra.opcCredito.setEnabled(false);

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

            /*sintaxiSql = "SELECT  id, "
                    + "ProductoDescripcion "
                    + "FROM producto ORDER BY ProductoDescripcion;";*/
            sintaxiSql = "SELECT "
                    + "  pro.id,"
                    + "  pro.ProductoDescripcion,"
                    + "  5000 AS 'PrecioCosto',"
                    + "  10000 AS 'PrecioPub',"
                    + "  lab.NombreLaboratorio,"
                    + "  dlab.NombreDivision "
                    + " FROM "
                    + "  producto pro "
                    + "  INNER JOIN laboratorio lab "
                    + "    ON pro.Laboratorio_id = lab.id "
                    + "  INNER JOIN divisionlaboratorio dlab "
                    + "    ON pro.DivisionLaboratorio_id = dlab.id ";

            ps = conec.getConexion().prepareStatement(sintaxiSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                AutoAcompletado.addItem(rs.getString(2));
            }
        } catch (SQLException ex) {
            // Logger.getLogger(PedidosControl.class.getName()).log(Level.SEVERE, null, ex);
            MiMsM = "Ocurrio un error en el metodo  itemsAutoCompletado de la tabla Productos ";
            throw new MIError(MiMsM, ex);
        }
    }

    public ResultSet resulseTabla(tablas t) {
        try {
            conec = new Conexion();
            sintaxiSql = null;
            switch (t) {
                case proveedor:
                    sintaxiSql = "SELECT * FROM proveedor ORDER BY NombreProveedor;";
                    break;
//                case producto:
//                    sintaxiSql = "SELECT  id, ProductoDescripcion, PrecioPublico, PrecioCosto FROM producto  ORDER BY id;";
//                    break;
//                    
//                case idVenta:
//                    sintaxiSql =" SELECT MAX(id)+1 FROM ventas;";
//                        break;
            }
            ps = conec.getConexion().prepareStatement(sintaxiSql);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(PedidosControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public void recuperarDatosProductos(JTextField id,
            JTextField descripcion,
            JTextField precioCosto,
            JTextField precioPublico,
            JTextField elaboracion,
            JTextField vencimiento,
            Integer tipoBusca) throws MIError {
        try {
            conec = new Conexion();
            sintaxiSql = null;

            sintaxiSql = " SELECT \n"
                    + "    pro.* \n"
                    + "    ,lpro.*\n"
                    + "  \n"
                    + "FROM\n"
                    + "  Producto_v pro \n"
                    + "  LEFT JOIN loteproducto lpro \n"
                    + "    ON pro.id = lpro.Producto_id ";
            switch (tipoBusca) {
                case 1:
                    /*SINTAXIS POR ID*/
                    sintaxiSql = sintaxiSql + " WHERE pro.ID = " + Compra.txtCodigo.getText().trim()
                            + " ORDER BY lpro.Vencimiento ";
                    ;
                    break;
                case 2:
                    /*SINTAXIS POR descri*/
                    sintaxiSql = sintaxiSql + " WHERE "
                            + " pro.ProductoDescripcion= '" + Compra.txtProducto.getText().trim() + "'"
                            + " ORDER BY lpro.Vencimiento ";
                    
                    break;

            
           }
            ps = conec.getConexion().prepareStatement(sintaxiSql);
            //System.out.println(sintaxiSql);
            rs = ps.executeQuery();
            if (rs.next()) {
                id.setText(rs.getString("ID"));
                descripcion.setText(rs.getString("ProductoDescripcion"));
                precioCosto.setText(rs.getString("PrecioReferencia"));
                precioPublico.setText(rs.getString("PrecioPublico"));
                elaboracion.setText(rs.getString("Elaboracion"));
                vencimiento.setText(rs.getString("Vencimiento"));

                Compra.txtfCantidad.requestFocus();
                

            } else {
                JOptionPane.showMessageDialog(Compra, "Codigo ingresado no existe");
                limpiarTexto(ComprasCtrol.paneles.detalle);
                Compra.txtProducto.requestFocus();
            }
        } catch (SQLException ex) {
            // Logger.getLogger(PedidosControl.class.getName()).log(Level.SEVERE, null, ex);
            MiMsM = "Ocurrio un error en el metodo recuperarDatosProductos";
            throw new MIError(MiMsM, ex);
        }
    }

    public void recupararDatos(tablas t, JLabel lbl) throws MIError {
        Conexion conec = new Conexion();
        String sintaxiSql = null;
        PreparedStatement ps;
        ResultSet rs = null;
        try {

            switch (t) {
                case proveedor:

                    sintaxiSql = "SELECT * FROM proveedor WHERE id='" + Compra.txtfRuc.getText().trim() + "'  ORDER BY id;";
                    break;

            }

            ps = conec.getConexion().prepareStatement(sintaxiSql);
            rs = ps.executeQuery();
            if (rs.next()) {
                lbl.setText(rs.getString(2));
                // Compra.lblNombreProveedor.setText(rs.getString(2));

                this.habilitarTexto(paneles.detalle, estados.activo);
            } else {
                JOptionPane.showMessageDialog(Compra, "Codigo Ingresado no existe");
                //"NOMBRE PROVEEDOR"
                Compra.lbProveedorlNombre.setText("NOMBRE PROVEEDOR");
                this.habilitarTexto(paneles.detalle, estados.inactivo);
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

                    limpiarPanel.limpiarCampos(Compra.pnlCabecera);
                    //limpiarPanel.limpiarCampos(PedidosInt.pnlPedidoCabecera);
                    Compra.lbProveedorlNombre.setText("NOMBRE PROVEEDOR");
                    break;
                case detalle:
                    limpiarPanel.limpiarCampos(Compra.pnlDetalle);
                    break;

                case todos:
                    limpiarPanel.limpiarCampos(Compra.pnlCabecera);
                    limpiarPanel.limpiarCampos(Compra.pnlCondicion);

                    limpiarPanel.limpiarCampos(Compra.pnlDetalle);
                    limpiarPanel.limpiarCampos(Compra.pnlTotal);
                    Compra.lbProveedorlNombre.setText("NOMBRE PROVEEDOR");

                    //grilla.LimpiarTabla();
                    //limpiarGrilla();
                                        
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
                Compra.btnGuardar.setEnabled(false);//false
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

//            System.out.println(cmst);

            filasAfectadas = cmst.executeUpdate();
            //System.out.println("filasAfectadas antesIF" + filasAfectadas);
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

//                System.out.println(cmst);
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
            Compra.btnGuardar.setEnabled(true);
            Compra.btnRemover.setEnabled(true);
        
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

    public void subTotal() {
        Integer precio = 0, cantidad = 0, subTotal = 0;
        precio = Integer.parseInt(Compra.txtfPrecioCosto.getText().trim());

        if (Compra.txtfPrecioCosto.getText().isEmpty()) {
            precio = 0;

        }
        if (Compra.txtfCantidad.getText().isEmpty()) {
            cantidad = 0;

        } else {
            cantidad = Integer.parseInt(Compra.txtfCantidad.getText().trim());
        }
        subTotal = cantidad * precio;
//        Integer iva5=0, iva10=0;
//        iva10 = subTotal / 11;
//        iva5 = subTotal / 21;
        Compra.txtfSubTotal.setText(subTotal.toString());
//        Ventas.txtIva5.setText(iva5.toString());
//        Ventas.txtIva10.setText(iva10.toString());
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

    public void limpiarGrilla() {
        grilla.LimpiarTabla();
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
            Compra.txtfFechaCompra.setText(utiles.fechaServidor());
            while (rs.next()) {

                Compra.txtfIdCompra.setText(rs.getString(1));
                Compra.txtfIdCompra.setEnabled(false);
                Compra.txtfIdCompra.setEnabled(false);
            }

        } catch (Exception e) {
            MiMsM = "Ocurrio un error en el metodo idVentas()";
            throw new MIError(MiMsM, e);
        }
    }
}
