package modulocompras.api.orden_pago.detalle;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/ordenes-pago-detalles")
@Tag(name = "P. Detalles de órdenes de pago")
public class OrdenPagoDetalleController {

        @Autowired
        private OrdenPagoDetalleService ordenPagoDetalleService;

        // Listar todos los detalles
        @GetMapping
        public ResponseEntity<List<OrdenPagoDetalleDTO>> getAllOrdenesPagoDetalles() {
                List<OrdenPagoDetalle> ordenesPagoDetalles = ordenPagoDetalleService.getAllOrdenesPagoDetalles();

                return ResponseEntity.ok(ordenesPagoDetalles.stream()
                                .map(OrdenPagoDetalleDTO::new)
                                .collect(Collectors.toList()));
        }

        // Obtener un detalle por ID
        @GetMapping("/{id}")
        public ResponseEntity<OrdenPagoDetalleDTO> getOrdenPagoDetalleById(@PathVariable Integer id) {
                return ordenPagoDetalleService.getOrdenPagoDetalleById(id)
                                .map(OrdenPagoDetalleDTO::new)
                                .map(ResponseEntity::ok)
                                .orElse(ResponseEntity.notFound().build());
        }

        // Crear un detalle
        @PostMapping("/{idOrdenPago}")
        public ResponseEntity<OrdenPagoDetalleDTO> createOrdenPagoDetalle(@PathVariable Integer idOrdenPago,
                        @RequestBody OrdenPagoDetalleDTO ordenPagoDetalleDTO) {

                OrdenPagoDetalle newOrdenPagoDetalle = ordenPagoDetalleService.createOrdenPagoDetalle(idOrdenPago,
                                ordenPagoDetalleDTO).orElse(null);
                return newOrdenPagoDetalle != null
                                ? ResponseEntity.ok(new OrdenPagoDetalleDTO(newOrdenPagoDetalle))
                                : ResponseEntity.badRequest().build();
        }

        // Crear detalles en bulk
        @PostMapping("/{idOrdenPago}/bulk")
        public ResponseEntity<List<OrdenPagoDetalleDTO>> createOrdenPagoDetallesBulk(
                        @PathVariable Integer idOrdenPago,
                        @RequestBody List<OrdenPagoDetalleCreateDTO> ordenPagoDetallesCreateDTO) {

                List<OrdenPagoDetalle> newOrdenPagoDetalles = ordenPagoDetalleService.createOrdenPagoDetallesBulk(
                                idOrdenPago, ordenPagoDetallesCreateDTO);

                return ResponseEntity.ok(newOrdenPagoDetalles.stream()
                                .map(OrdenPagoDetalleDTO::new)
                                .collect(Collectors.toList()));
        }

}
