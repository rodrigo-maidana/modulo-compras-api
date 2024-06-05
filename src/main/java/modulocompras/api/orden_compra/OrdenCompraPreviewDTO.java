package modulocompras.api.orden_compra;

import java.util.Date;

public class OrdenCompraPreviewDTO {
    private Integer id;
    private Date fechaEmision;
    private String estado;
    private String nroOrdenCompra;

    // Constructor por defecto
    public OrdenCompraPreviewDTO() {
    }

    // Constructor desde entidad
    public OrdenCompraPreviewDTO(OrdenCompra ordenCompra) {
        this.id = ordenCompra.getId();
        this.fechaEmision = ordenCompra.getFechaEmision();
        this.estado = ordenCompra.getEstado();
        this.nroOrdenCompra = ordenCompra.getNroOrdenCompra();
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public String getEstado() {
        return estado;
    }

    public String getNroOrdenCompra() {
        return nroOrdenCompra;
    }

    // Setters
    public void setId(Integer idOrdenCompra) {
        this.id = idOrdenCompra;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setNroOrdenCompra(String nroOrdenCompra) {
        this.nroOrdenCompra = nroOrdenCompra;
    }

}
