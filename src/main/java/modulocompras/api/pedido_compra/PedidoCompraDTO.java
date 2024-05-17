package modulocompras.api.pedido_compra;

import java.util.Date;

public class PedidoCompraDTO {

    private Integer id;
    private Date fechaEmision;
    private String estado;
    private String nroPedido;

    // Constructor por defecto
    public PedidoCompraDTO() {
    }

    // Constructor desde entidad
    public PedidoCompraDTO(PedidoCompra pedidoCompra) {
        this.id = pedidoCompra.getId();
        this.fechaEmision = pedidoCompra.getFechaEmision();
        this.estado = pedidoCompra.getEstado();
        this.nroPedido = pedidoCompra.getNroPedido();
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public String getEstado() {
        return estado;
    }

    public String getNroPedido() {
        return nroPedido;
    }

    // Setters
    public void setId(Integer idPedidoCompra) {
        this.id = idPedidoCompra;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setNroPedido(String nroPedido) {
        this.nroPedido = nroPedido;
    }

}
