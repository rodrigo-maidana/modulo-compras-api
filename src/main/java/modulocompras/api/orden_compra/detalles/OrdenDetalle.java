package modulocompras.api.orden_compra.detalles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import modulocompras.api.orden_compra.OrdenCompra;
import modulocompras.api.producto.Producto;

@Entity
@Table(name = "ORDENES_DETALLES")
public class OrdenDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ORDEN_DETALLE")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "FK_ID_ORDEN_COMPRA", referencedColumnName = "ID_ORDEN_COMPRA", nullable = false)
    private OrdenCompra ordenCompra;

    @ManyToOne
    @JoinColumn(name = "FK_ID_PRODUCTO", referencedColumnName = "ID_PRODUCTO", nullable = false)
    private Producto producto;

    @Column(name = "INT_CANTIDAD", nullable = false)
    private Integer cantidad;

    @Column(name = "DEC_PRECIO_UNITARIO", nullable = false)
    private Double precioUnitario;

    @Column(name = "BOOL_ELIMINADO", nullable = false)
    private Boolean eliminado = false;

    // Constructor por defecto
    public OrdenDetalle() {
        this.eliminado = false;
    }

    // Constructor desde DTO
    public OrdenDetalle(OrdenDetalleDTO ordenDetalleDTO) {
        this.id = ordenDetalleDTO.getId();
        this.producto = new Producto(ordenDetalleDTO.getProducto());
        this.cantidad = ordenDetalleDTO.getCantidad();
        this.precioUnitario = ordenDetalleDTO.getPrecioUnitario();
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    public Producto getProducto() {
        return producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    // Setters
    public void setId(Integer idOrdenDetalle) {
        this.id = idOrdenDetalle;
    }

    public void setOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

}
