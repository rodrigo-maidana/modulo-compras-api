package modulocompras.api.factura;

import java.util.Date;

public class FacturaCreateDTO {

    private Integer idOrdenCompra;
    private Integer idDeposito;
    private Date fechaEmision;
    private Date fechaVencimiento;
    private String nroFactura;
    private String timbrado;
    private String condicion;

    public FacturaCreateDTO() {
    }

    // Getters
    public Integer getIdOrdenCompra() {
        return idOrdenCompra;
    }

    public Integer getIdDeposito() {
        return idDeposito;
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

    // Setters
    public void setIdOrdenCompra(Integer idOrdenCompra) {
        this.idOrdenCompra = idOrdenCompra;
    }

    public void setIdDeposito(Integer idDeposito) {
        this.idDeposito = idDeposito;
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

}
