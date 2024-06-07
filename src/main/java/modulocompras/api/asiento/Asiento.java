package modulocompras.api.asiento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "ASIENTOS")
public class Asiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ASIENTO")
    private Integer id;

    @Column(name = "FECHA", nullable = false)
    private Date fecha;

    @Column(name = "DESCRIPCION", nullable = false)
    private String descripcion;

    @Column(name = "TOTAL", nullable = false)
    private Double total;

    @Column(name = "BOOL_ELIMINADO")
    private Boolean eliminado = false;

    // Constructor por defecto
    public Asiento() {
        this.eliminado = false;
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    // hashCode, equals, y toString pueden ser añadidos según necesidad
}
