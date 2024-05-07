package modulocompras.api.entities.dto;

import modulocompras.api.entities.Categoria;

public class CategoriaDTO {

    private Integer id;
    private String nombre;

    public CategoriaDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.nombre = categoria.getNombre();
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    // Setters
    public void setId(Integer idCategoria) {
        this.id = idCategoria;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
