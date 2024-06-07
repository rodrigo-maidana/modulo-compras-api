package modulocompras.api.orden_pago.detalle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import modulocompras.api.metodo_pago.MetodoPago;
import modulocompras.api.orden_pago.OrdenPago;

@Entity
@Table(name = "ORDEN_PAGO_DETALLES")
public class OrdenPagoDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ORDEN_PAGO_DETALLE")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "FK_ID_ORDEN_PAGO", referencedColumnName = "ID_ORDEN_PAGO", nullable = false)
    private OrdenPago ordenPago;

    @ManyToOne
    @JoinColumn(name = "FK_ID_METODO_PAGO", referencedColumnName = "ID_METODO_PAGO", nullable = false)
    private MetodoPago metodoPago;

    @Column(name = "DEC_MONTO", nullable = false)
    private Double monto;

    @Column(name = "BOOL_ELIMINADO", nullable = false)
    private Boolean eliminado = false;

    // Constructor por defecto
    public OrdenPagoDetalle() {
    }

    // Constructor desde DTO
    public OrdenPagoDetalle(OrdenPagoDetalleDTO ordenPagoDetalleDTO) {
        this.id = ordenPagoDetalleDTO.getId();
        this.metodoPago = new MetodoPago(ordenPagoDetalleDTO.getMetodoPago());
        this.monto = ordenPagoDetalleDTO.getMonto();
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public OrdenPago getOrdenPago() {
        return ordenPago;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public Double getMonto() {
        return monto;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setOrdenPago(OrdenPago ordenPago) {
        this.ordenPago = ordenPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }
}
