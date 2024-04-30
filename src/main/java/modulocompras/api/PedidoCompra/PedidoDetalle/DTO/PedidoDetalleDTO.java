package modulocompras.api.PedidoCompra.PedidoDetalle.DTO;

import modulocompras.api.PedidoCompra.PedidoDetalle.PedidoDetalle;
import modulocompras.api.Producto.Producto;

public class PedidoDetalleDTO {
    private Integer idPedidoDetalle;
    private Producto producto;
    private Integer cantidad;

    public PedidoDetalleDTO(PedidoDetalle pedidoDetalle) {
        this.idPedidoDetalle = pedidoDetalle.getIdPedidoDetalle();
        this.producto = pedidoDetalle.getProducto();  // Aquí asumimos que quieres exponer detalles del producto
        this.cantidad = pedidoDetalle.getCantidad();
    }

    // Getters
    public Integer getIdPedidoDetalle() {
        return idPedidoDetalle;
    }

    public Producto getProducto() {
        return producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    // Setters
    public void setIdPedidoDetalle(Integer idPedidoDetalle) {
        this.idPedidoDetalle = idPedidoDetalle;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
