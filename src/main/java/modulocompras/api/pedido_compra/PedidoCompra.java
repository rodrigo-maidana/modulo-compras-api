package modulocompras.api.pedido_compra;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "PEDIDOS_COMPRA")
public class PedidoCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PEDIDO_COMPRA")
    private Integer id;

    @Column(name = "STR_NRO_PEDIDO", nullable = false, unique = true)
    private String nroPedido;

    @Column(name = "DATE_FECHA_EMISION", nullable = false)
    private Date fechaEmision;

    @Column(name = "STR_ESTADO", nullable = false)
    private String estado;

    @Column(name = "BOOL_ELIMINADO", nullable = false)
    private Boolean eliminado = false;

    // Constructor por defecto
    public PedidoCompra() {
        this.eliminado = false;
    }

    // Constructor desde DTO
    public PedidoCompra(PedidoCompraDTO pedidoCompraDTO) {
        this.id = pedidoCompraDTO.getId();
        this.fechaEmision = pedidoCompraDTO.getFechaEmision();
        this.estado = pedidoCompraDTO.getEstado();
        this.nroPedido = pedidoCompraDTO.getNroPedido();
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

    public Boolean getEliminado() {
        return eliminado;
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

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

}
