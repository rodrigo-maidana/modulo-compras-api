package modulocompras.api.proveedor;

public class ProveedorDTO {

    private Integer id;
    private String nombre;
    private String ruc;
    private String contacto;
    private String correo;
    private String direccion;

    // Constructor por defecto
    public ProveedorDTO() {
    }

    // Constructor desde entidad
    public ProveedorDTO(Proveedor proveedor) {
        this.id = proveedor.getId();
        this.nombre = proveedor.getNombre();
        this.ruc = proveedor.getRuc();
        this.contacto = proveedor.getContacto();
        this.correo = proveedor.getCorreo();
        this.direccion = proveedor.getDireccion();
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRuc() {
        return ruc;
    }

    public String getContacto() {
        return contacto;
    }

    public String getCorreo() {
        return correo;
    }

    public String getDireccion() {
        return direccion;
    }

    // Setters

    public void setId(Integer idProveedor) {
        this.id = idProveedor;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
