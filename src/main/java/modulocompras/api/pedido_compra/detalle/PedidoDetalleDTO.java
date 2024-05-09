package modulocompras.api.pedido_compra.detalle;

import modulocompras.api.producto.Producto;

public class PedidoDetalleDTO {
    private Integer id;
    private Producto producto;
    private Integer cantidad;

    public PedidoDetalleDTO(PedidoDetalle pedidoDetalle) {
        this.id = pedidoDetalle.getId();
        this.producto = pedidoDetalle.getProducto(); // Aquí asumimos que quieres exponer detalles del producto
        this.cantidad = pedidoDetalle.getCantidad();
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public Producto getProducto() {
        return producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    // Setters
    public void setId(Integer idPedidoDetalle) {
        this.id = idPedidoDetalle;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
