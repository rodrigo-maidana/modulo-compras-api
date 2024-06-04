package modulocompras.api.orden_compra.detalle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/ordenes-detalles") // Endpoint para Ordenes Detalles
@Tag(name = "Ordenes Detalles")
public class OrdenDetalleController {

        @Autowired
        private OrdenDetalleService ordenDetalleService;

        // Obtener todos los ordenes detalles
        @GetMapping
        public List<OrdenDetalleDTO> getAllOrdenesDetalles() {
                List<OrdenDetalle> ordenDetalles = ordenDetalleService.getAllOrdenesDetalles();
                return ordenDetalles.stream()
                                .map(OrdenDetalleDTO::new)
                                .collect(Collectors.toList());
        }

        // Obtener un orden detalle por ID
        @GetMapping("/{id}")
        public ResponseEntity<OrdenDetalleDTO> getOrdenDetalleById(@PathVariable Integer id) {
                Optional<OrdenDetalleDTO> ordenDetalleDTO = ordenDetalleService.getOrdenDetalleById(id).stream()
                                .map(OrdenDetalleDTO::new).findFirst();
                return ordenDetalleDTO.map(ResponseEntity::ok)
                                .orElseGet(() -> ResponseEntity.notFound().build());
        }

        // Crear un nuevo orden detalle
        @PostMapping("/{id}")
        public ResponseEntity<OrdenDetalleDTO> createOrdenDetalle(@PathVariable Integer id,
                        @RequestBody OrdenDetalleDTO ordenDetalleDTO) {
                Optional<OrdenDetalleDTO> savedOrdenDetalleDTO = ordenDetalleService.createOrdenDetalle(id,
                                ordenDetalleDTO).map(OrdenDetalleDTO::new);
                return savedOrdenDetalleDTO.map(ResponseEntity::ok)
                                .orElseGet(() -> ResponseEntity.badRequest().body(null));
        }

        // Actualizar un orden detalle existente
        @PutMapping("/{id}")
        public ResponseEntity<OrdenDetalleDTO> updateOrdenDetalle(@PathVariable Integer id,
                        @RequestBody OrdenDetalleDTO ordenDetalleDTO) {
                Optional<OrdenDetalleDTO> updatedOrdenDetalleDTO = ordenDetalleService.updateOrdenDetalle(id,
                                ordenDetalleDTO).map(OrdenDetalleDTO::new);
                return updatedOrdenDetalleDTO.map(ResponseEntity::ok)
                                .orElseGet(() -> ResponseEntity.notFound().build());
        }

        // Eliminar un ordenDetalle (borrado suave)
        @DeleteMapping("/{id}")
        public ResponseEntity<Object> deleteOrdenDetalle(@PathVariable Integer id) {
                ordenDetalleService.deleteOrdenDetalle(id);
                return ResponseEntity.noContent().build();
        }

}
