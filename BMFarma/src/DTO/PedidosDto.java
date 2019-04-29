package DTO;

import java.sql.Date;
import java.util.List;

public class PedidosDto {

    private Integer id;
    private String Descripcion;
    private Date FechaPedido;
    private String Observacion;
    private Integer EstadoPedido_id;
    private Integer Usuario_id;
    private Integer Proveedor_id;

    private List<PedidosDetalleDto> detalle;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public Date getFechaPedido() {
        return FechaPedido;
    }

    public void setFechaPedido(Date FechaPedido) {
        this.FechaPedido = FechaPedido;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }

    public Integer getEstadoPedido_id() {
        return EstadoPedido_id;
    }

    public void setEstadoPedido_id(Integer EstadoPedido_id) {
        this.EstadoPedido_id = EstadoPedido_id;
    }

    public Integer getUsuario_id() {
        return Usuario_id;
    }

    public void setUsuario_id(Integer Usuario_id) {
        this.Usuario_id = Usuario_id;
    }

    public Integer getProveedor_id() {
        return Proveedor_id;
    }

    public void setProveedor_id(Integer Proveedor_id) {
        this.Proveedor_id = Proveedor_id;
    }

    public List<PedidosDetalleDto> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<PedidosDetalleDto> detalle) {
        this.detalle = detalle;
    }

    
}
