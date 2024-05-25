package modulocompras.api.proveedor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PROVEEDORES")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROVEEDOR")
    private Integer id;

    @Column(name = "STR_NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "STR_RUC", nullable = false)
    private String ruc;

    @Column(name = "STR_CONTACTO")
    private String contacto;

    @Column(name = "STR_CORREO")
    private String correo;

    @Column(name = "STR_DIRECCION")
    private String direccion;

    @Column(name = "BOOL_ELIMINADO", nullable = false)
    private Boolean eliminado = false;

    // Constructor por defecto
    public Proveedor() {
        this.eliminado = false;
    }

    // Constructor desde DTO
    public Proveedor(ProveedorDTO proveedorDTO) {
        this.id = proveedorDTO.getId();
        this.nombre = proveedorDTO.getNombre();
        this.ruc = proveedorDTO.getRuc();
        this.contacto = proveedorDTO.getContacto();
        this.correo = proveedorDTO.getCorreo();
        this.direccion = proveedorDTO.getDireccion();
    }

    // Getters y setters
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

    public Boolean getEliminado() {
        return eliminado;
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

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    // Constructor, hashCode, equals, y toString pueden ser añadidos según necesidad
}
