package modulocompras.api.Marca;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "MARCAS")
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MARCA")
    private Integer id;

    @Column(name = "STR_NOMBRE", nullable = false)
    private String nombre;

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer idMarca) {
        this.id = idMarca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Constructor, hashCode, equals, y toString pueden ser añadidos según necesidad
}
