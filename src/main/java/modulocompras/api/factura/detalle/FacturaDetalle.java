package modulocompras.api.factura.detalle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import modulocompras.api.factura.Factura;
import modulocompras.api.producto.Producto;

@Entity
@Table(name = "FACTURAS_DETALLES")
public class FacturaDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FACTURA_DETALLE")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "FK_ID_FACTURA", referencedColumnName = "ID_FACTURA", nullable = false)
    private Factura factura;

    @ManyToOne
    @JoinColumn(name = "FK_ID_PRODUCTO", referencedColumnName = "ID_PRODUCTO", nullable = false)
    private Producto producto;

    @Column(name = "INT_CANTIDAD", nullable = false)
    private Integer cantidad;

    @Column(name = "DEC_PRECIO_UNITARIO", nullable = false)
    private Double precioUnitario;

    @Column(name = "DEC_PORCENTAJE_IVA", nullable = false)
    private Double porcentajeIva;

    @Column(name = "BOOL_ELIMINADO", nullable = false)
    private Boolean eliminado = false;

    // Constructor por defecto
    public FacturaDetalle() {
        this.eliminado = false;
    }

    // Constructor desde DTO

    public FacturaDetalle(FacturaDetalleDTO facturaDetalleDTO) {
        this.id = facturaDetalleDTO.getId();
        this.producto = new Producto(facturaDetalleDTO.getProducto());
        this.cantidad = facturaDetalleDTO.getCantidad();
        this.precioUnitario = facturaDetalleDTO.getPrecioUnitario();
        this.porcentajeIva = facturaDetalleDTO.getPorcentajeIva();
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public Factura getFactura() {
        return factura;
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

    public Double getPorcentajeIva() {
        return porcentajeIva;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    // Setters
    public void setId(Integer idFacturaDetalle) {
        this.id = idFacturaDetalle;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
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

    public void setPorcentajeIva(Double porcentajeIva) {
        this.porcentajeIva = porcentajeIva;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

}
