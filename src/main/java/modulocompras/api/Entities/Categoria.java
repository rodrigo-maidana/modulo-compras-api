package modulocompras.api.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

Identity
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
