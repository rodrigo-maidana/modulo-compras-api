package modulocompras.api.Deposito;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DEPOSITOS")
public class Deposito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DEPOSITO")
    private Integer idDeposito;

    @Column(name = "STR_NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "STR_DIRECCION")
    private String direccion;

    @Column(name = "STR_CONTACTO")
    private String contacto;

    // Getters y setters
    public Integer getIdDeposito() {
        return idDeposito;
    }

    public void setIdDeposito(Integer idDeposito) {
        this.idDeposito = idDeposito;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    // Métodos como constructor, hashCode, equals, y toString pueden ser añadidos según necesidad
}
