package modulocompras.api.categoria;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CATEGORIAS")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CATEGORIA")
    private Integer id;

    @Column(name = "STR_NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "BOOL_ELIMINADO", nullable = false)
    private Boolean eliminado = false;

    // Constructor por defecto
    public Categoria() {
        this.eliminado = false;
    }

    // Constructor desde DTO
    public Categoria(CategoriaDTO categoriaDTO) {
        this.id = categoriaDTO.getId();
        this.nombre = categoriaDTO.getNombre();
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer idCategoria) {
        this.id = idCategoria;
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
