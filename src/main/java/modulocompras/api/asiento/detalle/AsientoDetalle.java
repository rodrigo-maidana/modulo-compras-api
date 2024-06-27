package modulocompras.api.asiento.detalle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import modulocompras.api.asiento.Asiento;
import modulocompras.api.cuenta.Cuenta;

@Entity
@Table(name = "ASIENTO_DETALLES")
public class AsientoDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ASIENTO_DETALLE")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "FK_ID_ASIENTO", referencedColumnName = "ID_ASIENTO", nullable = false)
    private Asiento asiento;

    @ManyToOne
    @JoinColumn(name = "FK_ID_CUENTA", referencedColumnName = "ID_CUENTA", nullable = false)
    private Cuenta cuenta;

    @Column(name = "DEBE", nullable = false)
    private Double debe;

    @Column(name = "HABER", nullable = false)
    private Double haber;

    @Column(name = "BOOL_ELIMINADO")
    private Boolean eliminado = false;

    // Constructor por defecto
    public AsientoDetalle() {
        this.eliminado = false;
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public void setAsiento(Asiento asiento) {
        this.asiento = asiento;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Double getDebe() {
        return debe;
    }

    public void setDebe(Double debe) {
        this.debe = debe;
    }

    public Double getHaber() {
        return haber;
    }

    public void setHaber(Double haber) {
        this.haber = haber;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    // hashCode, equals, y toString pueden ser añadidos según necesidad
}
