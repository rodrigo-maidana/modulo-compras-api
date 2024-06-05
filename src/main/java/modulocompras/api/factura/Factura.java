package modulocompras.api.factura;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import modulocompras.api.orden_compra.OrdenCompra;
import modulocompras.api.proveedor.Proveedor;

@Entity
@Table(name = "FACTURAS")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FACTURA")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "FK_ID_PROVEEDOR", referencedColumnName = "FK_ID_PROVEEDOR", nullable = false)
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "FK_ID_ORDEN_COMPRA", referencedColumnName = "ID_ORDEN_COMPRA", nullable = false)
    private OrdenCompra ordenCompra;

    @Column(name = "DATE_FECHA_EMISION", nullable = false)
    private String fechaEmision;

    @Column(name = "DATE_FECHA_VENCIMIENTO", nullable = false)
    private String fechaVencimiento;

    @Column(name = "STR_NRO_FACTURA", nullable = false, unique = true)
    private String nroFactura;

    @Column(name = "STR_TIMBRADO", nullable = false)
    private String timbrado;

    @Column(name = "STR_CONDICION", nullable = false)
    private String condicion;

    @Column(name = "STR_ESTADO", nullable = false)
    private String estado;

    @Column(name = "DEC_MONTO_TOTAL", nullable = false)
    private Double montoTotal;

    @Column(name = "DEC_SALDO_PENDIENTE", nullable = false)
    private Double saldoPendiente;

    @Column(name = "BOOL_ELIMINADO", nullable = false)
    private Boolean eliminado = false;

    // Constructor por defecto
    public Factura() {
        this.eliminado = false;
    }

    // Constructor desde DTO
    public Factura(FacturaDTO facturaDTO) {
        this.id = facturaDTO.getId();
        this.proveedor = new Proveedor(facturaDTO.getProveedor());
        this.ordenCompra = new OrdenCompra(facturaDTO.getOrdenCompra());
        this.fechaEmision = facturaDTO.getFechaEmision();
        this.fechaVencimiento = facturaDTO.getFechaVencimiento();
        this.nroFactura = facturaDTO.getNroFactura();
        this.timbrado = facturaDTO.getTimbrado();
        this.condicion = facturaDTO.getCondicion();
        this.estado = facturaDTO.getEstado();
        this.montoTotal = facturaDTO.getMontoTotal();
        this.saldoPendiente = facturaDTO.getSaldoPendiente();
    }

    // Constructor desde FacturaCreateDTO
    public Factura(FacturaCreateDTO facturaCreateDTO) {
        this.fechaEmision = facturaCreateDTO.getFechaEmision();
        this.fechaVencimiento = facturaCreateDTO.getFechaVencimiento();
        this.nroFactura = facturaCreateDTO.getNroFactura();
        this.timbrado = facturaCreateDTO.getTimbrado();
        this.condicion = facturaCreateDTO.getCondicion();
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public String getNroFactura() {
        return nroFactura;
    }

    public String getTimbrado() {
        return timbrado;
    }

    public String getCondicion() {
        return condicion;
    }

    public String getEstado() {
        return estado;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public Double getSaldoPendiente() {
        return saldoPendiente;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    // Setters
    public void setId(Integer idFactura) {
        this.id = idFactura;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public void setOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public void setNroFactura(String nroFactura) {
        this.nroFactura = nroFactura;
    }

    public void setTimbrado(String timbrado) {
        this.timbrado = timbrado;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public void setSaldoPendiente(Double saldoPendiente) {
        this.saldoPendiente = saldoPendiente;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

}
