package modulocompras.api.orden_compra;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ORDENES_COMPRA")
public class OrdenCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ORDEN_COMPRA")
    private Integer id;

    @Column(name = "STR_NRO_ORDEN", nullable = false, unique = true)
    private String nroOrdenCompra;

    @Column(name = "DATE_FECHA_EMISION", nullable = false)
    private Date fechaEmision;

    @Column(name = "STR_ESTADO", nullable = false)
    private String estado;

    @Column(name = "BOOL_ELIMINADO", nullable = false)
    private Boolean eliminado = false;

    // Constructor por defecto
    public OrdenCompra() {
        this.eliminado = false;
    }

    // Constructor desde DTO
    public OrdenCompra(OrdenCompraDTO ordenCompraDTO) {
        this.id = ordenCompraDTO.getId();
        this.fechaEmision = ordenCompraDTO.getFechaEmision();
        this.estado = ordenCompraDTO.getEstado();
        this.nroOrdenCompra = ordenCompraDTO.getNroOrdenCompra();
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

    public Boolean getEliminado() {
        return eliminado;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
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

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

}
