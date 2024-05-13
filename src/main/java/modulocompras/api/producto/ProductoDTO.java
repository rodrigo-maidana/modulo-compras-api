package modulocompras.api.producto;

import modulocompras.api.categoria.CategoriaDTO;
import modulocompras.api.marca.MarcaDTO;

public class ProductoDTO {

    private Integer id;
    private String descripcion;
    private MarcaDTO marca;
    private CategoriaDTO categoria;

    // Constructor por defecto
    public ProductoDTO() {
    }

    // Constructor desde entidad
    public ProductoDTO(Producto producto) {
        this.id = producto.getId();
        this.descripcion = producto.getDescripcion();
        this.marca = new MarcaDTO(producto.getMarca());
        this.categoria = new CategoriaDTO(producto.getCategoria());
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public MarcaDTO getMarca() {
        return marca;
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    // Setters
    public void setId(Integer idProducto) {
        this.id = idProducto;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setMarca(MarcaDTO marca) {
        this.marca = marca;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }

}
