package modulocompras.api.cotizacion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import modulocompras.api.pedido_compra.PedidoCompra;
import modulocompras.api.proveedor.Proveedor;

import java.util.Date;

@Entity
@Table(name = "COTIZACIONES")
public class Cotizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_COTIZACION")
    private Integer id;

    @Column(name = "STR_NRO_COTIZACION", nullable = false, unique = true)
    private String nroCotizacion;

    @ManyToOne
    @JoinColumn(name = "FK_ID_PEDIDO_COMPRA", referencedColumnName = "ID_PEDIDO_COMPRA", nullable = false)
    private PedidoCompra pedidoCompra;

    @ManyToOne
    @JoinColumn(name = "FK_ID_PROVEEDOR", referencedColumnName = "ID_PROVEEDOR", nullable = false)
    private Proveedor proveedor;

    @Column(name = "DATE_FECHA_EMISION", nullable = false)
    private Date fechaEmision;

    @Column(name = "STR_ESTADO", nullable = false)
    private String estado;

    @Column(name = "BOOL_ELIMINADO", nullable = false)
    private Boolean eliminado = false;

    // Constructor por defecto
    public Cotizacion() {
        this.eliminado = false;
    }

    public Cotizacion(PedidoCompra pedidoCompra, Proveedor proveedor) {
        this.eliminado = false;
        this.pedidoCompra = pedidoCompra;
        this.proveedor = proveedor;
    }

    // Constructor desde DTO
    public Cotizacion(CotizacionDTO cotizacionDTO) {
        this.id = cotizacionDTO.getId();
        this.nroCotizacion = cotizacionDTO.getNroCotizacion();
        this.pedidoCompra = new PedidoCompra(cotizacionDTO.getPedidoCompra());
        this.proveedor = new Proveedor(cotizacionDTO.getProveedor());
        this.fechaEmision = cotizacionDTO.getFechaEmision();
        this.estado = cotizacionDTO.getEstado();
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getNroCotizacion() {
        return nroCotizacion;
    }

    public PedidoCompra getPedidoCompra() {
        return pedidoCompra;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public String getEstado() {
        return estado;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    // Setters
    public void setId(Integer idPedidoCotizacion) {
        this.id = idPedidoCotizacion;
    }

    public void setNroCotizacion(String nroCotizacion) {
        this.nroCotizacion = nroCotizacion;
    }

    public void setPedidoCompra(PedidoCompra pedidoCompra) {
        this.pedidoCompra = pedidoCompra;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }
}