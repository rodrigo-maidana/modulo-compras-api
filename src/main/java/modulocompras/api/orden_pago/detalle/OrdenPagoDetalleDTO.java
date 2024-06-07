package modulocompras.api.orden_pago.detalle;

import modulocompras.api.metodo_pago.MetodoPagoDTO;

public class OrdenPagoDetalleDTO {

    private Integer id;
    private MetodoPagoDTO metodoPago;
    private Double monto;

    // Constructor por defecto
    public OrdenPagoDetalleDTO() {
    }

    // Constructor desde entidad
    public OrdenPagoDetalleDTO(OrdenPagoDetalle ordenPagoDetalle) {
        this.id = ordenPagoDetalle.getId();
        this.metodoPago = new MetodoPagoDTO(ordenPagoDetalle.getMetodoPago());
        this.monto = ordenPagoDetalle.getMonto();
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public MetodoPagoDTO getMetodoPago() {
        return metodoPago;
    }

    public Double getMonto() {
        return monto;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setMetodoPago(MetodoPagoDTO metodoPago) {
        this.metodoPago = metodoPago;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

}
