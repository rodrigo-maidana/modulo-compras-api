package modulocompras.api.factura;

import java.util.Date;

import modulocompras.api.depositos.DepositoDTO;
import modulocompras.api.orden_compra.OrdenCompraDTO;
import modulocompras.api.proveedor.ProveedorDTO;

public class FacturaDTO {

    private Integer id;
    private ProveedorDTO proveedor;
    private OrdenCompraDTO ordenCompra;
    private DepositoDTO deposito;
    private Date fechaEmision;
    private Date fechaVencimiento;
    private String nroFactura;
    private String timbrado;
    private String condicion;
    private String estado;
    private Double montoTotal;
    private Double saldoPendiente;

    // Constructor por defecto
    public FacturaDTO() {
    }

    // Constructor desde entidad
    public FacturaDTO(Factura factura) {
        this.id = factura.getId();
        this.proveedor = new ProveedorDTO(factura.getProveedor());
        this.ordenCompra = new OrdenCompraDTO(factura.getOrdenCompra());
        this.deposito = new DepositoDTO(factura.getDeposito());
        this.fechaEmision = factura.getFechaEmision();
        this.fechaVencimiento = factura.getFechaVencimiento();
        this.nroFactura = factura.getNroFactura();
        this.timbrado = factura.getTimbrado();
        this.condicion = factura.getCondicion();
        this.estado = factura.getEstado();
        this.montoTotal = factura.getMontoTotal();
        this.saldoPendiente = factura.getSaldoPendiente();
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public ProveedorDTO getProveedor() {
        return proveedor;
    }

    public OrdenCompraDTO getOrdenCompra() {
        return ordenCompra;
    }

    public DepositoDTO getDeposito() {
        return deposito;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public Date getFechaVencimiento() {
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

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setProveedor(ProveedorDTO proveedor) {
        this.proveedor = proveedor;
    }

    public void setOrdenCompra(OrdenCompraDTO ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public void setDeposito(DepositoDTO deposito) {
        this.deposito = deposito;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
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

}
