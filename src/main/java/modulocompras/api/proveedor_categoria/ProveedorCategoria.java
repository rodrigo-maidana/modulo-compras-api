package modulocompras.api.proveedor_categoria;

import modulocompras.api.proveedor.Proveedor;
import modulocompras.api.categoria.Categoria;

import jakarta.persistence.*;

@Entity
@Table(name = "PROVEEDORES_CATEGORIAS")
public class ProveedorCategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROVEEDOR_CATEGORIA")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_PROVEEDOR", nullable = false)
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "ID_CATEGORIA", nullable = false)
    private Categoria categoria;

    // Constructor por defecto
    public ProveedorCategoria() {
    }

    // Constructor con parámetros
    public ProveedorCategoria(Proveedor proveedor, Categoria categoria) {
        this.proveedor = proveedor;
        this.categoria = categoria;
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    // Setters

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
