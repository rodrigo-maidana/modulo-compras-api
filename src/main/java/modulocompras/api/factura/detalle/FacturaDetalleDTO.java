package modulocompras.api.factura.detalle;

import modulocompras.api.producto.ProductoDTO;

public class FacturaDetalleDTO {

    private Integer id;
    private ProductoDTO producto;
    private Integer cantidad;
    private Double precioUnitario;
    private Double porcentajeIva;

    // Constructor por defecto
    public FacturaDetalleDTO() {
    }

    // Constructor desde Entidad
    public FacturaDetalleDTO(FacturaDetalle facturaDetalle) {
        this.id = facturaDetalle.getId();
        this.producto = new ProductoDTO(facturaDetalle.getProducto());
        this.cantidad = facturaDetalle.getCantidad();
        this.precioUnitario = facturaDetalle.getPrecioUnitario();
        this.porcentajeIva = facturaDetalle.getPorcentajeIva();
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

    public Double getPorcentajeIva() {
        return porcentajeIva;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
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

    public void setPorcentajeIva(Double porcentajeIva) {
        this.porcentajeIva = porcentajeIva;
    }

}
