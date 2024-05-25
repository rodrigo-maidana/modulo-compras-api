package modulocompras.api.proveedor_categoria;

public class ProveedorCategoriaDTO {

    private Integer proveedorId;
    private Integer categoriaId;

    // Constructor por defecto
    public ProveedorCategoriaDTO() {
    }

    // Constructor con parámetros
    public ProveedorCategoriaDTO(Integer proveedorId, Integer categoriaId) {
        this.proveedorId = proveedorId;
        this.categoriaId = categoriaId;
    }

    // Constructor desde entidad
    public ProveedorCategoriaDTO(ProveedorCategoria proveedorCategoria) {
        this.proveedorId = proveedorCategoria.getProveedor().getId();
        this.categoriaId = proveedorCategoria.getCategoria().getId();
    }

    // Getters
    public Integer getProveedorId() {
        return proveedorId;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    // Setters
    public void setProveedorId(Integer proveedorId) {
        this.proveedorId = proveedorId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }
}
