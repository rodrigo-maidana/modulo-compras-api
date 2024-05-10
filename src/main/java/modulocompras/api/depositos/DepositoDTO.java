package modulocompras.api.depositos;

public class DepositoDTO {

    private Integer id;
    private String nombre;
    private String direccion;
    private String contacto;

    // Constructor por defecto
    public DepositoDTO() {
    }

    // Constructor desde entidad
    public DepositoDTO(Deposito deposito) {
        this.id = deposito.getId();
        this.nombre = deposito.getNombre();
        this.direccion = deposito.getDireccion();
        this.contacto = deposito.getContacto();
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getContacto() {
        return contacto;
    }

    // Setters
    public void setId(Integer idDeposito) {
        this.id = idDeposito;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }
}
