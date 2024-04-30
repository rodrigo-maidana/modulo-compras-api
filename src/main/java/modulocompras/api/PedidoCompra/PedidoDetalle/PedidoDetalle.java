package modulocompras.api.PedidoCompra.PedidoDetalle;

import modulocompras.api.PedidoCompra.PedidoCompra.PedidoCompra;
import modulocompras.api.Producto.Producto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PEDIDOS_DETALLES")
public class PedidoDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PEDIDO_DETALLE")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "FK_ID_PEDIDO_COMPRA", referencedColumnName = "ID_PEDIDO_COMPRA", nullable = false)
    private PedidoCompra pedidoCompra;

    @ManyToOne
    @JoinColumn(name = "FK_ID_PRODUCTO", referencedColumnName = "ID_PRODUCTO", nullable = false)
    private Producto producto;

    @Column(name = "INT_CANTIDAD", nullable = false)
    private Integer cantidad;

    // Getters y setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer idPedidoDetalle) {
        this.id = idPedidoDetalle;
    }

    public PedidoCompra getPedidoCompra() {
        return pedidoCompra;
    }

    public void setPedidoCompra(PedidoCompra pedidoCompra) {
        this.pedidoCompra = pedidoCompra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
