package modulocompras.api.orden_pago;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import modulocompras.api.proveedor.Proveedor;

@Entity
@Table(name = "ORDENES_PAGO")
public class OrdenPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ORDEN_PAGO")
    private Integer idOrdenPago;

    @ManyToOne
    @JoinColumn(name = "FK_ID_PROVEEDOR", referencedColumnName = "ID_PROVEEDOR", nullable = false)
    private Proveedor proveedor;

    @Column(name = "DATE_FECHA_PAGO", nullable = false)
    private Date fechaPago;

    @Column(name = "NUMERO_ORDEN_PAGO", nullable = false)
    private Integer numeroOrdenPago;

}
