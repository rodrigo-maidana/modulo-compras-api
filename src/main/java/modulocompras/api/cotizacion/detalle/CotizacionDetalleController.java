package modulocompras.api.cotizacion.detalle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/cotizacion-detalles") // Endpoint para Pedidos Detalles
@Tag(name = "Cotizacion Detalles")
public class CotizacionDetalleController {

    @Autowired
    private CotizacionDetalleService pedidoDetalleService;

}
