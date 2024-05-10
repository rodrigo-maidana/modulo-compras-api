package modulocompras.api.pedido_compra.detalle;

import modulocompras.api.producto.ProductoDTO;

public class PedidoDetalleDTO {
    private Integer id;
    private ProductoDTO producto;
    private Integer cantidad;

    public PedidoDetalleDTO(PedidoDetalle pedidoDetalle) {
        this.id = pedidoDetalle.getId();
        this.producto = new ProductoDTO(pedidoDetalle.getProducto());
        this.cantidad = pedidoDetalle.getCantidad();
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    // Setters
    public void setId(Integer idPedidoDetalle) {
        this.id = idPedidoDetalle;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
