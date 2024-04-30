package modulocompras.api.Producto;

import modulocompras.api.Categoria.Categoria;
import modulocompras.api.Marca.Marca;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUCTOS")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUCTO")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "FK_ID_MARCA", referencedColumnName = "ID_MARCA")
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "FK_ID_CATEGORIA", referencedColumnName = "ID_CATEGORIA")
    private Categoria categoria;

    @Column(name = "STR_DESCRIPCION", nullable = false)
    private String descripcion;

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

    // Constructor, hashCode, equals, y toString pueden ser añadidos según necesidad
}
