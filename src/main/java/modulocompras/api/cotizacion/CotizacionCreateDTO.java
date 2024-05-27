package modulocompras.api.cotizacion;

public class CotizacionCreateDTO {
    private Integer idPedidoCompra;
    private Integer idProveedor;

    public CotizacionCreateDTO() {
    }

    public Integer getIdPedidoCompra() {
        return idPedidoCompra;
    }

    public void setIdPedidoCompra(Integer idPedidoCompra) {
        this.idPedidoCompra = idPedidoCompra;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }
}