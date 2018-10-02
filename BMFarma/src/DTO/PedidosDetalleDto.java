package DTO;

public class PedidosDetalleDto {
    private Integer id;
    private Integer Cantidad;
    private Integer Producto_id;
    private Integer Precio;
    private Integer PrecioPublico;
    private Integer PedidoCabecera_id;

    public PedidosDetalleDto(Integer id, Integer Cantidad, Integer Producto_id, Integer Precio, Integer PrecioPublico, Integer PedidoCabecera_id) {
        this.id = id;
        this.Cantidad = Cantidad;
        this.Producto_id = Producto_id;
        this.Precio = Precio;
        this.PrecioPublico = PrecioPublico;
        this.PedidoCabecera_id = PedidoCabecera_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return Cantidad;
    }

    public void setCantidad(Integer Cantidad) {
        this.Cantidad = Cantidad;
    }

    public Integer getProducto_id() {
        return Producto_id;
    }

    public void setProducto_id(Integer Producto_id) {
        this.Producto_id = Producto_id;
    }

    public Integer getPrecio() {
        return Precio;
    }

    public void setPrecio(Integer Precio) {
        this.Precio = Precio;
    }

    public Integer getPrecioPublico() {
        return PrecioPublico;
    }

    public void setPrecioPublico(Integer PrecioPublico) {
        this.PrecioPublico = PrecioPublico;
    }

    public Integer getPedidoCabecera_id() {
        return PedidoCabecera_id;
    }

    public void setPedidoCabecera_id(Integer PedidoCabecera_id) {
        this.PedidoCabecera_id = PedidoCabecera_id;
    }
    
    
    

}
