package modulocompras.api.orden_compra;

import java.util.Date;

import modulocompras.api.proveedor.ProveedorDTO;

public class OrdenCompraDTO {

    private Integer id;
    private ProveedorDTO proveedor;
    private Date fechaEmision;
    private String estado;
    private String nroOrdenCompra;

    // Constructor por defecto
    public OrdenCompraDTO() {
    }

    // Constructor desde entidad
    public OrdenCompraDTO(OrdenCompra ordenCompra) {
        this.id = ordenCompra.getId();
        this.proveedor = new ProveedorDTO(ordenCompra.getProveedor());
        this.fechaEmision = ordenCompra.getFechaEmision();
        this.estado = ordenCompra.getEstado();
        this.nroOrdenCompra = ordenCompra.getNroOrdenCompra();
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public ProveedorDTO getProveedor() {
        return proveedor;
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

    public void setProveedor(ProveedorDTO proveedor) {
        this.proveedor = proveedor;
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
