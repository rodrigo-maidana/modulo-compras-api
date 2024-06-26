package modulocompras.api.cotizacion;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import modulocompras.api.categoria.CategoriaDTO;
import modulocompras.api.cotizacion.detalle.CotizacionDetalleService;
import modulocompras.api.pedido_compra.PedidoCompra;
import modulocompras.api.pedido_compra.PedidoCompraDTO;
import modulocompras.api.pedido_compra.PedidoCompraService;
import modulocompras.api.pedido_compra.detalle.PedidoDetalle;
import modulocompras.api.pedido_compra.detalle.PedidoDetalleService;
import modulocompras.api.producto.Producto;
import modulocompras.api.proveedor.Proveedor;
import modulocompras.api.proveedor.ProveedorDTO;
import modulocompras.api.proveedor.ProveedorService;
import modulocompras.api.proveedor_categoria.ProveedorCategoriaService;

@Service
public class CotizacionService {

        @Autowired
        private CotizacionRepository cotizacionRepository;

        @Autowired
        @Lazy
        private CotizacionDetalleService cotizacionDetalleService;

        @Autowired
        private PedidoCompraService pedidoCompraService;

        @Autowired
        @Lazy
        private PedidoDetalleService pedidoDetalleService;

        @Autowired
        private ProveedorService proveedorService;

        @Autowired
        private ProveedorCategoriaService proveedorCategoriaService;

        public CotizacionDTO createCotizacion(CotizacionCreateDTO pedidoCotizacionCreateDTO) {
                if (verifyIfExist(pedidoCotizacionCreateDTO.getIdPedidoCompra(),
                                pedidoCotizacionCreateDTO.getIdProveedor())) {
                        return null;
                }

                PedidoCompraDTO pedidoCompraDTO = pedidoCompraService
                                .getPedidoCompraById(pedidoCotizacionCreateDTO.getIdPedidoCompra())
                                .map(pedidoCompra -> new PedidoCompraDTO(pedidoCompra))
                                .orElse(null);

                ProveedorDTO proveedorDTO = proveedorService
                                .getProveedorById(pedidoCotizacionCreateDTO.getIdProveedor())
                                .map(ProveedorDTO::new)
                                .orElse(null);

                PedidoCompra pedidoCompra = new PedidoCompra(pedidoCompraDTO);
                Proveedor proveedor = new Proveedor(proveedorDTO);
                pedidoCompra.setEstado("Cotización Generada");

                pedidoCompraService.updatePedidoCompra(pedidoCompra.getId(), new PedidoCompraDTO(pedidoCompra))
                                .orElse(null);

                Cotizacion newCotizacion = new Cotizacion(pedidoCompra, proveedor);
                newCotizacion.setFechaEmision(new Date());
                newCotizacion.setEstado("Pendiente");
                newCotizacion.setNroCotizacion(generateNroCotizacion());

                Cotizacion savedCotizacion = cotizacionRepository.save(newCotizacion);

                List<PedidoDetalle> pedidoDetalles = pedidoDetalleService.getDetallesByPedidoCompraId(
                                pedidoCotizacionCreateDTO.getIdPedidoCompra());

                Set<Integer> categoriasIds = proveedorCategoriaService
                                .getCategorias(pedidoCotizacionCreateDTO.getIdProveedor())
                                .stream()
                                .map(CategoriaDTO::getId)
                                .collect(Collectors.toSet());

                for (PedidoDetalle pedidoDetalle : pedidoDetalles) {
                        Producto producto = pedidoDetalle.getProducto();
                        if (categoriasIds.contains(producto.getCategoria().getId())) {
                                cotizacionDetalleService.createCotizacionDetalle(
                                                savedCotizacion.getId(), producto.getId(), pedidoDetalle.getCantidad());
                        }
                }

                return new CotizacionDTO(savedCotizacion);
        }

        // Generar el número de cotización
        private String generateNroCotizacion() {
                SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
                String currentDate = sdf.format(new Date());

                Date today = new Date(new java.util.Date().getTime());

                // Contar las cotizaciones de hoy
                List<Cotizacion> cotizacionesHoy = cotizacionRepository.findByFechaEmision(today);

                int secuencia = cotizacionesHoy.size() + 1;

                // Formatear la secuencia a cuatro dígitos
                String secuenciaStr = String.format("%04d", secuencia);

                return "CT-" + currentDate + "-" + secuenciaStr;
        }

        public Optional<Cotizacion> getCotizacionById(Integer id) {
                return cotizacionRepository.findByIdAndEliminadoFalse(id);
        }

        public List<Cotizacion> getAllCotizaciones() {
                return cotizacionRepository.findByEliminadoFalseOrderByFechaEmisionDesc();
        }

        // Verificar si ya existe una cotización para el PedidoCompra y Proveedor
        public boolean verifyIfExist(Integer pedidoCompraId, Integer proveedorId) {
                return cotizacionRepository.existsByPedidoCompraIdAndProveedorIdAndEliminadoFalse(pedidoCompraId,
                                proveedorId);
        }

        public CotizacionDTO updateCotizacion(Integer id, CotizacionDTO cotizacionDTO) {
                Cotizacion cotizacion = cotizacionRepository.findByIdAndEliminadoFalse(id).orElse(null);
                if (cotizacion == null) {
                        return null;
                }

                cotizacion.setEstado(cotizacionDTO.getEstado());

                Cotizacion updatedCotizacion = cotizacionRepository.save(cotizacion);

                return new CotizacionDTO(updatedCotizacion);
        }

        public List<Cotizacion> getCotizacionesByPedidoCompraId(Integer id) {
                return cotizacionRepository.findByPedidoCompraIdAndEliminadoFalse(id);
        }

}
