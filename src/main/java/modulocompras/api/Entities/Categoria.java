package modulocompras.api.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CATEGORIAS")
public class Categoria {

    @Id
    @Column(name = "ID_CATEGORIA")
    private Integer idCategoria;

    @Column(name = "STR_NOMBRE")
    private String nombre;

    // Getters y setters
    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Constructor, hashCode, equals, y toString pueden ser añadidos según necesidad
}
