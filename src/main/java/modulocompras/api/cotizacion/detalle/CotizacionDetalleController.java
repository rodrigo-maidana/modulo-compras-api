package modulocompras.api.cotizacion.detalle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/cotizacion-detalles") // Endpoint para Cotizacion Detalles
@Tag(name = "Cotizacion Detalles")
public class CotizacionDetalleController {

    @Autowired
    private CotizacionDetalleService cotizacionDetalleService;

    // Actualizar un cotización detalle existente
    @PutMapping("/{id}")
    public ResponseEntity<CotizacionDetalleDTO> updateCotizacionDetalle(@PathVariable Integer id,
            @RequestBody CotizacionDetalleDTO cotizacionDetalleDTO) {
        Optional<CotizacionDetalle> updatedCotizacionDetalle = cotizacionDetalleService.updateCotizacionDetalle(id,
                cotizacionDetalleDTO);
        if (updatedCotizacionDetalle.isPresent()) {
            return ResponseEntity.ok(new CotizacionDetalleDTO(updatedCotizacionDetalle.get()));
        }
        return ResponseEntity.notFound().build();
    }

}
