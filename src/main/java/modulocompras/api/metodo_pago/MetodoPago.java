package modulocompras.api.metodo_pago;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import modulocompras.api.cuenta.Cuenta;

@Entity
@Table(name = "METODOS_PAGO")
public class MetodoPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_METODO_PAGO")
    private Integer id;

    @Column(name = "STR_NOMBRE", nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "FK_ID_CUENTA", referencedColumnName = "ID_CUENTA", nullable = false)
    private Cuenta cuenta;

    @Column(name = "BOOL_ELIMINADO", nullable = false)
    private Boolean eliminado = false;

    // Constructor por defecto
    public MetodoPago() {
    }

    // Constructor desde DTO
    public MetodoPago(MetodoPagoDTO metodoPagoDTO) {
        this.id = metodoPagoDTO.getId();
        this.nombre = metodoPagoDTO.getNombre();
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

}
