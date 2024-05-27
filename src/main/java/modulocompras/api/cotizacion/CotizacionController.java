package modulocompras.api.cotizacion;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import modulocompras.api.cotizacion.detalle.CotizacionDetalle;
import modulocompras.api.cotizacion.detalle.CotizacionDetalleDTO;
import modulocompras.api.cotizacion.detalle.CotizacionDetalleService;
import modulocompras.api.pedido_compra.detalle.PedidoDetalleService;
import modulocompras.api.proveedor_categoria.ProveedorCategoriaService;

@RestController
@RequestMapping("api/v1/cotizaciones") // Endpoint para Cotizaciones
@Tag(name = "Cotizaciones")
public class CotizacionController {

    @Autowired
    private CotizacionService cotizacionService;

    @Autowired
    private CotizacionDetalleService cotizacionDetalleService;

    @Autowired
    private PedidoDetalleService pedidoDetalleService;

    @Autowired
    private ProveedorCategoriaService proveedorCategoriaService;

    // Obtener todos los pedidos de compra
    @GetMapping
    public List<CotizacionDTO> getAllCotizaciones() {
        return cotizacionService.getAllCotizaciones().stream()
                .map(CotizacionDTO::new)
                .collect(Collectors.toList());
    }

    // Obtener un pedido de compra por ID
    @GetMapping("/{id}")
    public ResponseEntity<CotizacionDTO> getCotizacionById(Integer id) {
        Cotizacion cotizacion = cotizacionService.getCotizacionById(id).orElse(null);
        if (cotizacion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new CotizacionDTO(cotizacion));
    }

    // Crear un nuevo pedido de cotizacion
    @PostMapping
    public ResponseEntity<CotizacionDTO> createPedidoCotizacion(
            @RequestBody CotizacionCreateDTO pedidoCotizacionCreateDTO) {
        CotizacionDTO savedCotizacionDTO = cotizacionService.createCotizacion(pedidoCotizacionCreateDTO);
        if (savedCotizacionDTO == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(savedCotizacionDTO);
    }

    // Obtener todos los detalles de un pedido de cotizacion
    @GetMapping("/{id}/detalles")
    public List<CotizacionDetalleDTO> getCotizacionDetalles(Integer id) {
        return cotizacionDetalleService.getCotizacionDetallesById(id).stream()
                .map(CotizacionDetalleDTO::new)
                .collect(Collectors.toList());
    }

}
