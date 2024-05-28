package modulocompras.api.cotizacion;

import java.util.Date;

import modulocompras.api.pedido_compra.PedidoCompraDTO;
import modulocompras.api.proveedor.ProveedorDTO;

public class CotizacionDTO {

    private Integer id;
    private String nroCotizacion;
    private PedidoCompraDTO pedidoCompra;
    private ProveedorDTO proveedor;
    private Date fechaEmision;
    private String estado;

    // Constructor por defecto
    public CotizacionDTO() {
    }

    // Constructor desde Entity
    public CotizacionDTO(Cotizacion pedidoCotizacion) {
        this.id = pedidoCotizacion.getId();
        this.nroCotizacion = pedidoCotizacion.getNroCotizacion();
        this.pedidoCompra = new PedidoCompraDTO(pedidoCotizacion.getPedidoCompra());
        this.proveedor = new ProveedorDTO(pedidoCotizacion.getProveedor());
        this.fechaEmision = pedidoCotizacion.getFechaEmision();
        this.estado = pedidoCotizacion.getEstado();
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getNroCotizacion() {
        return nroCotizacion;
    }

    public PedidoCompraDTO getPedidoCompra() {
        return pedidoCompra;
    }

    public ProveedorDTO getProveedor() {
        return proveedor;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public String getEstado() {
        return estado;
    }

    // Setters
    public void setId(Integer idPedidoCotizacion) {
        this.id = idPedidoCotizacion;
    }

    public void setNroCotizacion(String nroCotizacion) {
        this.nroCotizacion = nroCotizacion;
    }

    public void setPedidoCompra(PedidoCompraDTO pedidoCompra) {
        this.pedidoCompra = pedidoCompra;
    }

    public void setProveedor(ProveedorDTO proveedor) {
        this.proveedor = proveedor;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
