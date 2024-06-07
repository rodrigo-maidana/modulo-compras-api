package modulocompras.api.orden_pago.detalle;

public class OrdenPagoDetalleCreateDTO {

    private Integer idMetodoPago;
    private Double monto;

    // Constructor por defecto
    public OrdenPagoDetalleCreateDTO() {
    }

    // Getters
    public Integer getIdMetodoPago() {
        return idMetodoPago;
    }

    public Double getMonto() {
        return monto;
    }

    // Setters
    public void setIdMetodoPago(Integer idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

}
