package modulocompras.api.marca;

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

    @Column(name = "BOOL_ELIMINADO")
    private Boolean eliminado = false;

    // Constructor por defecto
    public Marca() {
        this.eliminado = false;
    }

    // Constructor desde DTO
    public Marca(MarcaDTO marcaDTO) {
        this.id = marcaDTO.getId();
        this.nombre = marcaDTO.getNombre();
    }

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

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    // Constructor, hashCode, equals, y toString pueden ser añadidos según necesidad
}
