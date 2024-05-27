package modulocompras.api.cotizacion.detalle;

import modulocompras.api.producto.ProductoDTO;

public class CotizacionDetalleDTO {
    private Integer id;
    private ProductoDTO producto;
    private Integer cantidad;
    private Double precioUnitario;

    // Constructor por defecto
    public CotizacionDetalleDTO() {
    }

    // Constructor desde entidad
    public CotizacionDetalleDTO(CotizacionDetalle pedidoDetalle) {
        this.id = pedidoDetalle.getId();
        this.producto = new ProductoDTO(pedidoDetalle.getProducto());
        this.cantidad = pedidoDetalle.getCantidad();
        this.precioUnitario = pedidoDetalle.getPrecioUnitario();
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

    public Double getPrecioUnitario() {
        return precioUnitario;
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

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
