package modulocompras.api.marca;

public class MarcaDTO {

    private Integer id;
    private String nombre;

    public MarcaDTO(Marca marca) {
        this.id = marca.getId();
        this.nombre = marca.getNombre();
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    // Setters
    public void setId(Integer idMarca) {
        this.id = idMarca;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
