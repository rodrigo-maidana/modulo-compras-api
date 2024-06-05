package modulocompras.api.factura;

public class FacturaCreateDTO {

    private Integer idOrdenCompra;
    private String fechaEmision;
    private String fechaVencimiento;
    private String nroFactura;
    private String timbrado;
    private String condicion;

    public FacturaCreateDTO() {
    }

    // Getters
    public Integer getIdOrdenCompra() {
        return idOrdenCompra;
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

    // Setters
    public void setIdOrdenCompra(Integer idOrdenCompra) {
        this.idOrdenCompra = idOrdenCompra;
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

}
