package modulocompras.api.metodo_pago;

public class MetodoPagoDTO {

    private Integer id;
    private String nombre;

    // Constructor por defecto
    public MetodoPagoDTO() {
    }

    // Constructor desde Entidad
    public MetodoPagoDTO(MetodoPago metodoPago) {
        this.id = metodoPago.getId();
        this.nombre = metodoPago.getNombre();
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
