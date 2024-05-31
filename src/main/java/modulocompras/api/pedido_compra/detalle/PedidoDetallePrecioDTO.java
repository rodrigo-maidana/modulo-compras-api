package modulocompras.api.pedido_compra.detalle;

import java.util.List;

public class PedidoDetallePrecioDTO {

    private Integer id;
    private List<PrecioDTO> precios;

    // Constructor
    public PedidoDetallePrecioDTO() {
    }

    // Constructor con parámetros
    public PedidoDetallePrecioDTO(Integer id, List<PrecioDTO> precios) {
        this.id = id;
        this.precios = precios;
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public List<PrecioDTO> getPrecios() {
        return precios;
    }

    // Setters

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPrecios(List<PrecioDTO> precios) {
        this.precios = precios;
    }

}
