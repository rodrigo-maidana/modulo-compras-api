package modulocompras.api.pedido_compra;

import java.util.Date;

public class PedidoCompraDTO {

    private Integer id;
    private Date fechaEmision;
    private String estado;

    public PedidoCompraDTO(PedidoCompra pedidoCompra) {
        this.id = pedidoCompra.getId();
        this.fechaEmision = pedidoCompra.getFechaEmision();
        this.estado = pedidoCompra.getEstado();
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

}
