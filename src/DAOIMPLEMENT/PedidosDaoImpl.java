/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOIMPLEMENT;

import DAO.PedidosDao;
import DTO.PedidosDetalleDto;
import DTO.PedidosDto;
import GENERICOS.Conexion;
import GENERICOS.MIError;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc
 */
public class PedidosDaoImpl implements PedidosDao {

    private Conexion conec;
    private String query;
    private String miError;
    private PreparedStatement ps, psD;
    private int filasAfectadas;

    @Override
    public boolean agregar(PedidosDto dto) throws MIError {
        try {
            //        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            conec = new Conexion();
            query = "INSERT INTO bmfarma.pedidocabecera "
                    + "(\n"
                    + "  id"
                    + ", Descripcion"
                    + ", FechaPedido"
                    + ", Observacion"
                    + ", EstadoPedido_id"
                    + ", Usuario_id"
                    + ", Proveedor_id\n"
                    + ") \n"
                    + "VALUES (?,?,?,?,?,?,? ) ;";
            ps = conec.getConexion().prepareStatement(query);

            ps.setObject(1, dto.getId());
            ps.setObject(2, dto.getDescripcion());
            ps.setObject(3, dto.getFechaPedido());
            ps.setObject(4, dto.getObservacion());
            ps.setObject(5, dto.getEstadoPedido_id());
            ps.setObject(6, dto.getUsuario_id());
            ps.setObject(7, dto.getProveedor_id());

            if (filasAfectadas > 0) {
                filasAfectadas = 0;
                query = null;

                for (PedidosDetalleDto detalle : dto.getDetalle()) {
                    query = "INSERT INTO bmfarma.pedidodetalle (\n"
                            + "  id\n"
                            + "  , Cantidad\n"
                            + "  , Producto_id\n"
                            + "  , Precio\n"
                            + "  , PrecioPublico\n"
                            + "  , PedidoCabecera_id\n"
                            + ") \n"
                            + "VALUES  ( ?,?,?,?,?,?  ) ;";
                    psD = conec.getConexion().prepareStatement(query);
                    psD.setObject(1, detalle.getId());
                    psD.setObject(2, detalle.getCantidad());
                    psD.setObject(3, detalle.getProducto_id());
                    psD.setObject(4, detalle.getPrecio());
                    psD.setObject(5, detalle.getPrecioPublico());
                    psD.setObject(6, detalle.getPedidoCabecera_id());
                    
                    if (filasAfectadas<=0) {
                        conec.rollback();
                        return false;
                    }
                    
                }
            } else {
                return false;
            }
            conec.rollback();
            return false;
            

        } catch (SQLException ex) {
            Logger.getLogger(PedidosDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public boolean anular(Integer idDto) throws MIError {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
