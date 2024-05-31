package modulocompras.api.pedido_compra.detalle;

import modulocompras.api.proveedor.ProveedorDTO;

public class PrecioDTO {

    private ProveedorDTO proveedor;
    private Double precioUnitario;

    // Constructor
    public PrecioDTO() {
    }

    // Getters
    public ProveedorDTO getProveedor() {
        return proveedor;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    // Setters

    public void setProveedor(ProveedorDTO proveedor) {
        this.proveedor = proveedor;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

}
