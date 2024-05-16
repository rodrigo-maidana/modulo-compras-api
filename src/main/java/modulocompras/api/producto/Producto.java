package modulocompras.api.producto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import modulocompras.api.categoria.Categoria;
import modulocompras.api.marca.Marca;

@Entity
@Table(name = "PRODUCTOS")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUCTO")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "FK_ID_MARCA", referencedColumnName = "ID_MARCA", nullable = false)
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "FK_ID_CATEGORIA", referencedColumnName = "ID_CATEGORIA", nullable = false)
    private Categoria categoria;

    @Column(name = "STR_DESCRIPCION", nullable = false)
    private String descripcion;

    @Column(name = "BOOL_ELIMINADO", nullable = false)
    private Boolean eliminado = false;

    // Constructor por defecto
    public Producto() {
        this.eliminado = false;
    }

    // Constructor desde DTO
    public Producto(ProductoDTO productoDTO) {
        this.id = productoDTO.getId();
        this.marca = new Marca(productoDTO.getMarca());
        this.categoria = new Categoria(productoDTO.getCategoria());
        this.descripcion = productoDTO.getDescripcion();
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer idProducto) {
        this.id = idProducto;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    // Constructor, hashCode, equals, y toString pueden ser añadidos según necesidad
}
