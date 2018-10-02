package CONTROL;

import FORMULARIOS.Pedidos;
import DAO.PedidosDao;
import DAOIMPLEMENT.PedidosDaoImpl;
import DTO.PedidosDetalleDto;
import DTO.PedidosDto;

import GENERICOS.Conexion;
import GENERICOS.ConstructorTabla;
import GENERICOS.MIError;
import GENERICOS.Utiles;

import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.NumberFormat;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.mxrck.autocompleter.TextAutoCompleter;

public class PedidosControl {

    private Pedidos pedidos;
    private String sintaxiSQL;
    private NumberFormat nf = NumberFormat.getNumberInstance();
    private ConstructorTabla tabla;
    private Utiles utiles;
    private Conexion conec;
    private PreparedStatement ps;
    private ResultSet rs;

    public PedidosControl(JDialog dialogoPadre) {
        pedidos = (Pedidos) dialogoPadre;
        utiles = new Utiles();
        initControles();

    }

    
    private void initControles() {
        tabla = new ConstructorTabla(new Object[]{"COD.", "DESCRIPCION", "PRECIO PUBLIC", "%", "CANT", "PREC_COSTO", "SUBTOTAL"});
        pedidos.grlPedidoDetalle.setModel(tabla);
        try {
            pedidos.txtfFecha.setText(utiles.fechaServidor());
        } catch (MIError ex) {
            Logger.getLogger(PedidosControl.class.getName()).log(Level.SEVERE, null, ex);

        }
        configurarGrilla();
    }

    public void agregarFilaDefault() {
        tabla.agregarFila(new Object[]{0, "Prod_Generico", 0, 0, 0, 0, 0});
    }
     
    public void itemsAutoCompletado() {
        TextAutoCompleter AutoAcompletado = new TextAutoCompleter(pedidos.txtNombreProducto);

//        AutoAcompletado.setMode(-1); // prefijo, viene por defecto
        AutoAcompletado.setMode(0); // infijo
//        AutoAcompletado.setMode(1); // sufijo
//        AutoAcompletado.setCaseSensitive(true); // Sensible a mayúsculas
        AutoAcompletado.setCaseSensitive(false); //No sensible a mayúsculas

        try {
            conec = new Conexion();
            sintaxiSQL = null;
            // sintaxiSQL = "SELECT produ_codigo,produ_descripcion,produ_precio_vent FROM productos WHERE produ_codigo = " + pedidos.txtCodigoProducto.getText().trim() + " ORDER BY produ_codigo;";
            sintaxiSQL = "SELECT  id, "
                    + "ProductoDescripcion "
                    + "FROM producto ORDER BY ProductoDescripcion;";
            ps = conec.getConexion().prepareStatement(sintaxiSQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                AutoAcompletado.addItem(rs.getString(2));

            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidosControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void agregarNuevaFila() {
        Integer codigoProd, cantidad, precioPublico, descuentoPorc, precioCosto, subTotal;
        String descriproducto;
        codigoProd = Integer.parseInt(pedidos.txtCodigoProducto.getText().trim());
        descriproducto = pedidos.txtNombreProducto.getText().trim();
        precioPublico = Integer.parseInt(pedidos.txtPrecioPublico.getText().trim());
        descuentoPorc = Integer.parseInt(pedidos.txtPorcentajeDescuento.getText().trim());
        cantidad = Integer.parseInt(pedidos.txtCantidad.getText().trim());
        precioCosto = Integer.parseInt(pedidos.txtPrecioCosto.getText().trim());
        subTotal = cantidad * precioCosto;

        tabla.agregarFila(new Object[]{codigoProd,
            descriproducto,
            nf.format(precioPublico),
            descuentoPorc,
            nf.format(cantidad),
            nf.format(precioCosto),
            nf.format(subTotal),
            false});
        refrescarDatosGrilla();
    }

    public void eliminarFila() {
        int fila = pedidos.grlPedidoDetalle.getSelectedRow();
        if (pedidos.grlPedidoDetalle.getRowCount() == 0) {
            tabla.removeRow(fila);
            agregarFilaDefault();
        } else {
            tabla.removeRow(fila);
        }
        // pedidos.tablaDetalle.grabFocus();
        pedidos.grlPedidoDetalle.changeSelection(fila - 1, 0, false, false);
        refrescarDatosGrilla();
    }

    private void refrescarDatosGrilla() {
        Double montoTotal = 0.0;
        for (int i = 0; i < pedidos.grlPedidoDetalle.getRowCount(); i++) {
            montoTotal = montoTotal + Double.parseDouble(pedidos.grlPedidoDetalle.getValueAt(i, 6).toString().replace(".", ""));
        }

        pedidos.txtTotalMonto.setText(nf.format(montoTotal));
    }

    private void configurarGrilla() {
        pedidos.grlPedidoDetalle.setRowHeight(17);
        pedidos.grlPedidoDetalle.setFont(new Font("tahoma", 1, 12));
        pedidos.grlPedidoDetalle.getColumnModel().getColumn(0).setPreferredWidth(10);
        pedidos.grlPedidoDetalle.getColumnModel().getColumn(1).setPreferredWidth(200);
        pedidos.grlPedidoDetalle.getColumnModel().getColumn(2).setPreferredWidth(50);
        pedidos.grlPedidoDetalle.getColumnModel().getColumn(3).setPreferredWidth(10);
        pedidos.grlPedidoDetalle.getColumnModel().getColumn(4).setPreferredWidth(50);
        pedidos.grlPedidoDetalle.getColumnModel().getColumn(5).setPreferredWidth(50);
        pedidos.grlPedidoDetalle.getColumnModel().getColumn(6).setPreferredWidth(50);

        //oculatar columnas
        //        pedidos.tablaDetalle.getColumnModel().getColumn(0).setPreferredWidth(0);
        //        pedidos.tablaDetalle.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        //        pedidos.tablaDetalle.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        pedidos.grlPedidoDetalle.getTableHeader().setReorderingAllowed(false);  // para no reordenar la columna
        pedidos.grlPedidoDetalle.getTableHeader().setResizingAllowed(false);
    }

    public ResultSet resulseTabla() {

        try {
            conec = new Conexion();
            sintaxiSQL = null;

            sintaxiSQL = "SELECT  id, ProductoDescripcion, PrecioPublico, PrecioCosto FROM producto  ORDER BY id;";

            ps = conec.getConexion().prepareStatement(sintaxiSQL);
            rs = ps.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(PedidosControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public void recuperarDatos(JTextField id,
            JTextField descripcion,
            JTextField precioPublico,
            JTextField precioCosto,
            Integer tipoBusca) {

        try {
            conec = new Conexion();
            sintaxiSQL = null;
            switch (tipoBusca) {
                case 1:
                    /*SINTAXIS POR ID*/                    
                    sintaxiSQL = "SELECT  id, "
                            + "ProductoDescripcion, "
                            + "Precio, "
                            + "round((Precio-(Precio*0.3)),0) as'PrecioCosto' "
                            + "FROM producto "
                            + "WHERE ID= " + pedidos.txtCodigoProducto.getText().trim() + " ORDER BY id;";

                    break;
                case 2:
                    /*SINTAXIS POR DESCRIPCION*/
                    sintaxiSQL = "SELECT  id, "
                            + "ProductoDescripcion, "
                            + "Precio, "
                            + "round((Precio-(Precio*0.3)),0) as'PrecioCosto' "
                            + "FROM producto "
                            + "WHERE ProductoDescripcion= '" + pedidos.txtNombreProducto.getText().trim() + "' ORDER BY id;";
                    break;

            }

            
            ps = conec.getConexion().prepareStatement(sintaxiSQL);
            rs = ps.executeQuery();
            if (rs.next()) {
                id.setText(rs.getString(1));
                descripcion.setText(rs.getString(2));
                precioPublico.setText(rs.getString(3));
                precioCosto.setText(rs.getString(4));

            } else {
                JOptionPane.showMessageDialog(pedidos, "Codigo ingresado no existe");
            }

        } catch (SQLException ex) {
            Logger.getLogger(PedidosControl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

 
}
