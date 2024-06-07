package modulocompras.api.orden_pago;

import modulocompras.api.factura.FacturaDTO;
import modulocompras.api.proveedor.ProveedorDTO;

public class OrdenPagoDTO {

    private Integer id;
    private FacturaDTO factura;
    private ProveedorDTO proveedor;
    private String fechaPago;
    private String nroOrdenPago;
    private String estado;
    private Double montoTotal;

    // Constructor por defecto
    public OrdenPagoDTO() {
    }

    // Constructor desde Entidad
    public OrdenPagoDTO(OrdenPago ordenPago) {
        this.id = ordenPago.getId();
        this.factura = new FacturaDTO(ordenPago.getFactura());
        this.proveedor = new ProveedorDTO(ordenPago.getProveedor());
        this.fechaPago = ordenPago.getFechaPago().toString();
        this.nroOrdenPago = ordenPago.getNroOrdenPago().toString();
        this.estado = ordenPago.getEstado();
        this.montoTotal = ordenPago.getMontoTotal();
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public FacturaDTO getFactura() {
        return factura;
    }

    public ProveedorDTO getProveedor() {
        return proveedor;
    }

    public String getFechaPago() {
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

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setFactura(FacturaDTO factura) {
        this.factura = factura;
    }

    public void setProveedor(ProveedorDTO proveedor) {
        this.proveedor = proveedor;
    }

    public void setFechaPago(String fechaPago) {
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

}
