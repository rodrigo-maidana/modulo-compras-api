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

    @Column(name = "DATE_FECHA_EMISION", nullable = false)
    private Date fechaEmision;

    @Column(name = "STR_ESTADO", nullable = false)
    private String estado;

    // Getters y setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer idPedidoCompra) {
        this.id = idPedidoCompra;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
