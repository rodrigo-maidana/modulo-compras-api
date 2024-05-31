package modulocompras.api.orden_compra.detalles;

import java.util.Date;

import modulocompras.api.producto.Producto;
import modulocompras.api.producto.ProductoDTO;

public class OrdenDetalleDTO {

    private Integer id;
    private ProductoDTO producto;
    private Integer cantidad;
    private Double precioUnitario;

    // Constructor por defecto
    public OrdenDetalleDTO() {
    }

    // Constructor desde entidad
    public OrdenDetalleDTO(OrdenDetalle ordenDetalle) {
        this.id = ordenDetalle.getId();
        this.producto = new ProductoDTO(ordenDetalle.getProducto());
        this.cantidad = ordenDetalle.getCantidad();
        this.precioUnitario = ordenDetalle.getPrecioUnitario();
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

}
