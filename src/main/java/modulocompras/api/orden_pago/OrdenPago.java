package modulocompras.api.orden_pago;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import modulocompras.api.factura.Factura;
import modulocompras.api.proveedor.Proveedor;

@Entity
@Table(name = "ORDENES_PAGO")
public class OrdenPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ORDEN_PAGO")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "FK_ID_FACTURA", referencedColumnName = "ID_FACTURA", nullable = false)
    private Factura factura;

    @ManyToOne
    @JoinColumn(name = "FK_ID_PROVEEDOR", referencedColumnName = "ID_PROVEEDOR", nullable = false)
    private Proveedor proveedor;

    @Column(name = "DATE_FECHA_PAGO", nullable = false)
    private Date fechaPago;

    @Column(name = "NUMERO_ORDEN_PAGO", nullable = false)
    private String nroOrdenPago;

    @Column(name = "STR_ESTADO", nullable = false)
    private String estado;

    @Column(name = "DEC_MONTO_TOTAL", nullable = false)
    private Double montoTotal;

    @Column(name = "BOOL_ELIMINADO", nullable = false)
    private Boolean eliminado = false;

    // Constructor por defecto
    public OrdenPago() {
    }

    // Constructor desde DTO
    public OrdenPago(OrdenPagoDTO ordenPagoDTO) {
        this.id = ordenPagoDTO.getId();
        this.factura = new Factura(ordenPagoDTO.getFactura());
        this.proveedor = new Proveedor(ordenPagoDTO.getProveedor());
        this.fechaPago = ordenPagoDTO.getFechaPago();
        this.nroOrdenPago = ordenPagoDTO.getNroOrdenPago();
        this.estado = ordenPagoDTO.getEstado();
        this.montoTotal = ordenPagoDTO.getMontoTotal();
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public Factura getFactura() {
        return factura;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public String getNroOrdenPago() {
        return nroOrdenPago;
    }

    public String getEstado() {
        return estado;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public void setNroOrdenPago(String nroOrdenPago) {
        this.nroOrdenPago = nroOrdenPago;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

}
