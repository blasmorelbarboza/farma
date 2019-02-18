package CONTROL;

import FORMULARIOS.PedidoInterno;
import GENERICOS.Conexion;
import GENERICOS.ConexionSP;
import GENERICOS.ConstructorTabla;
import GENERICOS.HabilitarTextosPanel;
import GENERICOS.LimpiarTextosPanel;
import GENERICOS.MIError;
import GENERICOS.Utiles;
import GENERICOS.ValidarVacios;
import Genericos.FormatoTextos;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Color;
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
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;

public class PedidoInternoCtrol {

    private PedidoInterno PedidosInt;
    private String MiMsM;
    private Utiles utiles;
    private FormatoTextos formatoTxt;
    private ValidarVacios textoVacio;
    private ConstructorTabla grilla;

    private LimpiarTextosPanel limpiarPanel;
    private HabilitarTextosPanel habilitarPanel;

    private Conexion conec;
    private String sintaxiSql;
    private int filasAfectadas;

    private PreparedStatement ps;
    private ResultSet rs;

    public enum paneles {
        cabecera, detalle, detalleTxt, detalleBtn, botonera, todos,
    };

    public enum tablas {
        producto, proveedores
    };

    public enum estados {
        activo, pendiente, inactivo
    };
   public PedidoInternoCtrol(JDialog dialogoPadre) {
        PedidosInt = (PedidoInterno) dialogoPadre;
        utiles = new Utiles();
        formatoTxt = new FormatoTextos();
        textoVacio = new ValidarVacios();
        limpiarPanel = new LimpiarTextosPanel();
        habilitarPanel = new HabilitarTextosPanel();

    }

    public void initControlers() throws MIError {
        //      PedidosInt.grlPedidoDetalle.setModel((TableModel) tabla);
        itemsAutoCompletado();
        PedidosInt.txtfFechaPedido.setText(utiles.fechaServidor().toString());

        grilla = new ConstructorTabla(new Object[]{"IDPEDIDO", "CODIGO", "DESCRIPCION", "ID_LAB", "LABORATORIO", "ID_DIV", "DIVISION", "PRESENTACION", "CANT"});
        PedidosInt.grlPedidoDetalle.setModel((TableModel) grilla);
        configurarGrilla();
    }

    public void validarTextos() {
        Object[] camposObligatorios
                = {PedidosInt.txtCodigo.getText(), PedidosInt.txtLaboratorio.getText(), PedidosInt.txtDivision.getText(), PedidosInt.txtPedidoId.getText(), PedidosInt.txtCantidad.getText()
                };
        textoVacio.validar(camposObligatorios);

        if (textoVacio.vacioAviso == 1) {
            PedidosInt.txtCodigo.setBorder(new LineBorder(Color.RED));
            PedidosInt.txtLaboratorio.setBorder(new LineBorder(Color.RED));
            PedidosInt.txtDivision.setBorder(new LineBorder(Color.RED));
            PedidosInt.txtPedidoId.setBorder(new LineBorder(Color.RED));
            PedidosInt.txtCantidad.setBorder(new LineBorder(Color.RED));
        } 
        if (textoVacio.vacioAviso != 1) {
            try {
                this.agregarNuevaFila();
                this.limpiarTexto(PedidoInternoCtrol.paneles.detalleTxt);
                
                PedidosInt.txtCodigo.setBorder(new LineBorder(Color.GRAY));
                PedidosInt.txtLaboratorio.setBorder(new LineBorder(Color.GRAY));
                PedidosInt.txtDivision.setBorder(new LineBorder(Color.GRAY));
                PedidosInt.txtPedidoId.setBorder(new LineBorder(Color.GRAY));
                PedidosInt.txtCantidad.setBorder(new LineBorder(Color.gray));
                
                
            } catch (MIError ex) {
                Logger.getLogger(PedidoInternoCtrol.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        textoVacio.vacioAviso=0;

    }

    public void HabilitarBotones(estados estadoBotones) {
        switch (estadoBotones) {
            case activo:
                PedidosInt.btnNuevo.setEnabled(true);
                PedidosInt.btnEditar.setEnabled(true);
                PedidosInt.btnGuardar.setEnabled(false);
                PedidosInt.btnCancelar.setEnabled(false);
                PedidosInt.btnAgregar.setEnabled(false);
                PedidosInt.btnRemover.setEnabled(false);
                //  Ventas.btnSalir.setEnabled(true);
                break;
            case inactivo:
                PedidosInt.btnNuevo.setEnabled(false);
                PedidosInt.btnEditar.setEnabled(false);
                PedidosInt.btnGuardar.setEnabled(false);
                PedidosInt.btnCancelar.setEnabled(true);
//                Ventas.btnAgregar.setEnabled(true);
                PedidosInt.btnRemover.setEnabled(false);
                //Ventas.btnSalir.setEnabled(false);
                break;
        }
    }

    public void limpiarTexto(paneles p) throws MIError {
        try {
            switch (p) {

                case cabecera:
                    limpiarPanel.limpiarCampos(PedidosInt.pnlPedidoCabecera);
                    break;
                case detalleTxt:
                    limpiarPanel.limpiarCampos(PedidosInt.pnlDetallePedidoTxt);
                    break;

                case todos:
                    limpiarPanel.limpiarCampos(PedidosInt.pnlPedidoCabecera);
                    limpiarPanel.limpiarCampos(PedidosInt.pnlDetallePedidoTxt);
                    break;

            }
        } catch (Exception ex) {
            MiMsM = "Ocurrio un error en el metodo  limpiarTexto ";
            throw new MIError(MiMsM, ex);
        }
    }

    public void habilitarTexto(paneles p, estados estadoTexto) throws MIError {
        try {
            switch (estadoTexto) {
                case activo:
                    switch (p) {
                        case cabecera:
                            habilitarPanel.habilitarCampos(PedidosInt.pnlPedidoCabecera);
                            PedidosInt.txtfFechaPedido.setText(utiles.fechaServidor().toString());
                            break;
                        case detalleTxt:
                            habilitarPanel.habilitarCampos(PedidosInt.pnlDetallePedidoTxt);
                            PedidosInt.txtfFechaPedido.setText(utiles.fechaServidor().toString());
                            break;
                        case todos:
                            PedidosInt.txtfFechaPedido.setText(utiles.fechaServidor().toString());
                            habilitarPanel.habilitarCampos(PedidosInt.pnlPedidoCabecera);
                            habilitarPanel.habilitarCampos(PedidosInt.pnlDetallePedidoTxt);
                            break;
                    }
                    break;
                case inactivo:
                    switch (p) {
                        case cabecera:
                            habilitarPanel.deshabilitarCampos(PedidosInt.pnlPedidoCabecera);
                            break;
                        case detalleTxt:
                            habilitarPanel.deshabilitarCampos(PedidosInt.pnlDetallePedidoTxt);
                            break;
                        case todos:
                            habilitarPanel.deshabilitarCampos(PedidosInt.pnlPedidoCabecera);
                            habilitarPanel.deshabilitarCampos(PedidosInt.pnlDetallePedidoTxt);
                            break;

                    }
                    break;
            }
        } catch (Exception ex) {
            MiMsM = "Ocurrio un error en el metodo  limpiarTexto ";
            throw new MIError(MiMsM, ex);
        }
    }

    /**
     * ***************AUTOCOMPLETE*********************
     */
    public void itemsAutoCompletado() /* throws MIError*/ {
        TextAutoCompleter AutoAcompletado = new TextAutoCompleter(PedidosInt.txtProducto);
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
            JTextField idLaboratorio,
            JTextField laboratorio,
            JTextField idDivision,
            JTextField division,
            JTextField presentacion,
            Integer tipoBusca) throws MIError {
        try {
            conec = new Conexion();
            sintaxiSql = null;
            switch (tipoBusca) {
                case 1:
                    /*SINTAXIS POR ID*/
                    sintaxiSql = "  SELECT  p.id\n"
                            + "	, p.ProductoDescripcion \n"
                            + "	, p.Laboratorio_id\n"
                            + "	, l.NombreLaboratorio\n"
                            + "	, p.DivisionLaboratorio_id\n"
                            + "	, d.NombreDivision\n"
                            + "       , pf.PresentacionDescripcion"
                            + " FROM producto p\n"
                            + " INNER JOIN laboratorio l\n"
                            + "	ON l.id=p.Laboratorio_id\n"
                            + " INNER JOIN divisionlaboratorio d\n"
                            + "	ON d.id=p.DivisionLaboratorio_id\n"
                            + " INNER JOIN presentacion pf\n"
                            + "	ON pf.id=p.Presentacion_id"
                            + "  WHERE p.ID = '" + PedidosInt.txtCodigo.getText().trim() + "' "
                            + " ORDER BY p.ProductoDescripcion;";
                    break;
                case 2:
                    /*SINTAXIS POR DESCRIPCION*/
                    sintaxiSql = "  SELECT  p.id\n"
                            + "	, p.ProductoDescripcion \n"
                            + "	, p.Laboratorio_id\n"
                            + "	, l.NombreLaboratorio\n"
                            + "	, p.DivisionLaboratorio_id\n"
                            + "	, d.NombreDivision\n "
                            + "       , pf.PresentacionDescripcion"
                            + " FROM producto p\n"
                            + " INNER JOIN laboratorio l\n"
                            + "	ON l.id=p.Laboratorio_id\n"
                            + " INNER JOIN divisionlaboratorio d\n"
                            + "	ON d.id=p.DivisionLaboratorio_id\n"
                            + " INNER JOIN presentacion pf\n"
                            + "	ON pf.id=p.Presentacion_id"
                            + " WHERE ProductoDescripcion= '" + PedidosInt.txtProducto.getText().trim() + "' ORDER BY p.id ;";
                    // + "WHERE ProductoDescripcion= '" + Ventas.txtProducto.getText().trim() + "' ORDER BY id;";
                    break;
            }
            ps = conec.getConexion().prepareStatement(sintaxiSql);
            rs = ps.executeQuery();
            if (rs.next()) {
                id.setText(rs.getString(1));
                descripcion.setText(rs.getString(2));
                idLaboratorio.setText(rs.getString(3));
                laboratorio.setText(rs.getString(4));
                idDivision.setText(rs.getString(5));
                division.setText(rs.getString(6));
                presentacion.setText(rs.getString(7));
            } else {
                JOptionPane.showMessageDialog(PedidosInt, "Codigo ingresado no existe");
                limpiarTexto(PedidoInternoCtrol.paneles.detalleTxt);
            }
        } catch (SQLException ex) {
            // Logger.getLogger(PedidosControl.class.getName()).log(Level.SEVERE, null, ex);
            MiMsM = "Ocurrio un error en el metodo recuperarDatosProductos";
            throw new MIError(MiMsM, ex);
        }
    }

    /**
     * ***************FIN AUTOCOMPLETE*********************
     */
    /**
     * ***************GRILLA*********************
     */
    public void agregarFilaDefault() {
        grilla.agregarFila(new Object[]{0, 0, "Prod_Generico", 0, 0, 0, 0, 0, 0});
        PedidosInt.btnRemover.setEnabled(true);
    }

    public void limpiarGrilla() {
        grilla.LimpiarTabla();

    }

    private void refrescarDatosGrilla() throws MIError {
        try {
//            Double montoTotal = 0.0;
            for (int i = 0; i < grilla.recuperarNroFilas(); i++) {
//                montoTotal = montoTotal + Double.parseDouble(PedidosInt.grlPedidoDetalle.getValueAt(i, 6).toString().replace(".", ""));
            }
//            PedidosInt.txtTotal.setText(nf.format(montoTotal));

        } catch (Exception e) {
            MiMsM = "Ocurrio un error en el metodo refrescarDatosGrilla";
            throw new MIError(MiMsM, e);
        }

    }

    public void eliminarFila() {
        int fila = PedidosInt.grlPedidoDetalle.getSelectedRow();
        if (fila >= 0) {
            if (PedidosInt.grlPedidoDetalle.getRowCount() == 0) {
                grilla.removeRow(fila);
                PedidosInt.btnRemover.setEnabled(false);
                PedidosInt.btnGuardar.setEnabled(false);
            } else {
                grilla.removeRow(fila);
                if (PedidosInt.grlPedidoDetalle.getRowCount() == 0) {
                    PedidosInt.btnRemover.setEnabled(false);
                    PedidosInt.btnGuardar.setEnabled(false);
                }
            }
            // pedidos.tablaDetalle.grabFocus();
            PedidosInt.grlPedidoDetalle.changeSelection(fila - 1, 0, false, false);
            try {
                refrescarDatosGrilla();
            } catch (MIError ex) {
                Logger.getLogger(VentasCtrol.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(PedidosInt, "Seleccione Item a Remover");
        }
    }

    private void configurarGrilla() throws MIError {
        try {
            PedidosInt.grlPedidoDetalle.setRowHeight(17);
            PedidosInt.grlPedidoDetalle.setFont(new Font("tahoma", 1, 12));
            PedidosInt.grlPedidoDetalle.getColumnModel().getColumn(0).setPreferredWidth(5);
            PedidosInt.grlPedidoDetalle.getColumnModel().getColumn(1).setPreferredWidth(5);
            PedidosInt.grlPedidoDetalle.getColumnModel().getColumn(2).setPreferredWidth(200);
            PedidosInt.grlPedidoDetalle.getColumnModel().getColumn(3).setPreferredWidth(15);
            PedidosInt.grlPedidoDetalle.getColumnModel().getColumn(4).setPreferredWidth(40);
            PedidosInt.grlPedidoDetalle.getColumnModel().getColumn(5).setPreferredWidth(15);
            PedidosInt.grlPedidoDetalle.getColumnModel().getColumn(6).setPreferredWidth(40);
            PedidosInt.grlPedidoDetalle.getColumnModel().getColumn(7).setPreferredWidth(40);
            PedidosInt.grlPedidoDetalle.getColumnModel().getColumn(8).setPreferredWidth(15);
            //oculatar columnas
            //        pedidos.tablaDetalle.getColumnModel().getColumn(0).setPreferredWidth(0);
            //        pedidos.tablaDetalle.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
            //        pedidos.tablaDetalle.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
            PedidosInt.grlPedidoDetalle.getTableHeader().setReorderingAllowed(false);  // para no reordenar la columna

            PedidosInt.grlPedidoDetalle.getTableHeader().setResizingAllowed(false);
        } catch (Exception e) {
            MiMsM = "Ocurrio un error en el metodo configurarGrilla ";
            throw new MIError(MiMsM, e);
        }
    }

    public void agregarNuevaFila() throws MIError {

        try {
            if (utiles.verificarTxtVacioCero(PedidosInt.txtCodigo)) {

                Integer id, codigoProd, cantidad, idLaboratorio, idDivision;
                String descriproducto, laboratorio, division, presentacion;

                id = Integer.parseInt(PedidosInt.txtPedidoId.getText().trim());
                codigoProd = Integer.parseInt(PedidosInt.txtCodigo.getText().trim());
                descriproducto = PedidosInt.txtProducto.getText().trim();
                idLaboratorio = Integer.parseInt(PedidosInt.txtLaboratorio.getText().trim());
                laboratorio = PedidosInt.txtLaboratorioNombre.getText().trim();
                idDivision = Integer.parseInt(PedidosInt.txtDivision.getText().trim());
                division = PedidosInt.txtDivisionNombre.getText().trim();
                cantidad = Integer.parseInt(PedidosInt.txtCantidad.getText().trim());
                presentacion = PedidosInt.txtPresentacion.getText().trim();

                grilla.agregarFila(new Object[]{
                    /*grilla = new ConstructorTabla(new Object[]
{"IDPEDIDO", "CODIGO", "DESCRIPCION", "ID_LAB","LABORATORIO","ID_DIV","DIVISION", "PRESENTACION","CANT"});*/
                    id,
                    codigoProd,
                    descriproducto,
                    idLaboratorio,
                    laboratorio,
                    idDivision,
                    division,
                    presentacion,
                    cantidad,
                    false
                });
                refrescarDatosGrilla();
                PedidosInt.btnRemover.setEnabled(true);
                //tabla = new ConstructorTabla(new Object[]
            } else {
                PedidosInt.txtCodigo.requestFocus();
            }
        } catch (Exception e) {
            MiMsM = "Ocurrio un error en el metodo agregarNuevaFila";
            throw new MIError(MiMsM, e);
        }

    }

    /**
     * ***************FIN GRILLA*********************
     */
    /************* GUARDAR -*********************/
    String InsertarCabecera = "CALL PedidoInterno_Ins(?,?,?)";
    /*PROCEDURE `PedidoInterno_Ins`(p_id INT
				, p_Descripcion VARCHAR(45)
				, p_Estado VARCHAR(20))*/
    
    String InsertarDetalle = "call PedidoInternoDetalle_Ins(?,?,?);";
    /*PROCEDURE `PedidoInternoDetalle_Ins`(
					p_Cantidad INT,
					p_PedidoCabecera_id INT,
					p_Producto_id INT)*/
    public void altaCabecera() throws MIError {
        
    filasAfectadas=1;
        Connection cnn;
        String resultado="";
        
        try {
            cnn = ConexionSP.Conectar();
            CallableStatement cmst = cnn.prepareCall(InsertarCabecera);
            cmst.setString(1, PedidosInt.txtPedidoId.getText().trim());         
            cmst.setString(2, PedidosInt.txtfFechaPedido.getText().trim());            
            cmst.setInt(3, 1);/*estado*/
            //cmst.execute();

            filasAfectadas =cmst.executeUpdate();
            if (filasAfectadas >= 0) 
            {
                resultado = "Los datos se ingresaron exitosamente !!!";
            } else 
            {
                resultado = "Los datos se NO ingresaron!!!altaCabecera";
                System.out.println("else"+filasAfectadas);
                JOptionPane.showMessageDialog(PedidosInt, resultado);
            }            
            
            cnn.close();

        } catch (SQLException ex) {
            MiMsM = "Ocurrio un error en el metodo alta de la tabla Ventas Cabecera ";
            throw new MIError(MiMsM, ex);
            //Logger.getLogger(ControlCiudad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void altaDetalleSP() throws MIError {
        Connection cnn;
        String resultado="";
        filasAfectadas=1;
        try {
            cnn = ConexionSP.Conectar();
            CallableStatement cmst = cnn.prepareCall(InsertarDetalle);
            for (int fila = 0; fila < grilla.recuperarNroFilas(); fila = fila + 1) {
                //prueba cmst.setInt(1, 23);
                cmst.setString(1, grilla.getValueAt(fila, 0).toString().replace(".", ""));
                cmst.setString(2, grilla.getValueAt(fila, 1).toString().replace(".", ""));
                cmst.setString(3, grilla.getValueAt(fila, 8).toString().replace(".", ""));
                
                System.out.println(cmst);
             //   cmst.execute();
            filasAfectadas =cmst.executeUpdate();
                if (filasAfectadas >= 0) 
                {
                    resultado = "Los datos se ingresaron exitosamente !!!altaDetalleSP";
                } else 
                {
                    resultado = "Los datos DEL DETALLE NO ingresaron!!!altaDetalleSP";
                    System.out.println("else"+filasAfectadas);
                }
            }
            JOptionPane.showMessageDialog(PedidosInt, resultado);
            cnn.close();
        } catch (SQLException ex) {
            MiMsM = "Ocurrio un error en el metodo alta Detalle de la tabla VentasDetalle";
            throw new MIError(MiMsM, ex);
            //Logger.getLogger(ControlCiudad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        public void idPedido() throws MIError {
        try {
            conec = new Conexion();
            sintaxiSql = null;
            // sintaxiSQL = "SELECT produ_codigo,produ_descripcion,produ_precio_vent FROM productos WHERE produ_codigo = " + pedidos.txtCodigoProducto.getText().trim() + " ORDER BY produ_codigo;";
            sintaxiSql = "SELECT MAX(id)+1 AS \"IDx\" FROM pedidointerno ; ";
            ps = conec.getConexion().prepareStatement(sintaxiSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                PedidosInt.txtPedidoId.setText(rs.getString(1));
            }
            
            
        } catch (Exception e) {
            MiMsM = "Ocurrio un error en el metodo idPedido()";
            throw new MIError(MiMsM, e);
        }
    }
    
/************* GUARDAR -*********************/
}
