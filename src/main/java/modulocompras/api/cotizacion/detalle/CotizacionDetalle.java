package modulocompras.api.cotizacion.detalle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import modulocompras.api.cotizacion.Cotizacion;
import modulocompras.api.producto.Producto;

@Entity
@Table(name = "COTIZACION_DETALLES")
public class CotizacionDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PEDIDO_DETALLE")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "FK_ID_COTIZACION", referencedColumnName = "ID_COTIZACION", nullable = false)
    private Cotizacion cotizacion;

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
    public CotizacionDetalle() {
        this.eliminado = false;
    }

    // Constructor desde DTO
    public CotizacionDetalle(CotizacionDetalleDTO cotizacionDetalleDTO) {
        this.id = cotizacionDetalleDTO.getId();
        this.producto = new Producto(cotizacionDetalleDTO.getProducto());
        this.cantidad = cotizacionDetalleDTO.getCantidad();
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
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

    public void setId(Integer idPedidoDetalle) {
        this.id = idPedidoDetalle;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
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
