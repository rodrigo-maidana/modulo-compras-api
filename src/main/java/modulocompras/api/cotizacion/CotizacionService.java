package modulocompras.api.cotizacion;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import modulocompras.api.categoria.Categoria;
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
        private CotizacionRepository pedidoCotizacionRepository;

        @Autowired
        private PedidoCompraService pedidoCompraService;

        @Autowired
        private ProveedorService proveedorService;

        @Autowired
        private ProveedorCategoriaService proveedorCategoriaService;

        @Autowired
        @Lazy
        private CotizacionDetalleService cotizacionDetalleService;

        @Autowired
        private PedidoDetalleService pedidoDetalleService;

        public CotizacionDTO createCotizacion(CotizacionCreateDTO pedidoCotizacionCreateDTO) {
                if (verifyIfExist(pedidoCotizacionCreateDTO.getIdPedidoCompra(),
                                pedidoCotizacionCreateDTO.getIdProveedor())) {
                        throw new IllegalStateException("Ya existe una cotización para este PedidoCompra y Proveedor");
                }

                PedidoCompraDTO pedidoCompraDTO = pedidoCompraService
                                .getPedidoCompraById(pedidoCotizacionCreateDTO.getIdPedidoCompra())
                                .orElseThrow(() -> new IllegalArgumentException("PedidoCompra no encontrado"));

                ProveedorDTO proveedorDTO = proveedorService
                                .getProveedorById(pedidoCotizacionCreateDTO.getIdProveedor())
                                .orElseThrow(() -> new IllegalArgumentException("Proveedor no encontrado"));

                PedidoCompra pedidoCompra = new PedidoCompra(pedidoCompraDTO);
                Proveedor proveedor = new Proveedor(proveedorDTO);
                pedidoCompra.setEstado("Cotización Generada");

                pedidoCompraService.updatePedidoCompra(pedidoCompra.getId(), new PedidoCompraDTO(pedidoCompra))
                                .orElseThrow(() -> new IllegalStateException("Error al actualizar PedidoCompra"));

                Cotizacion newCotizacion = new Cotizacion(pedidoCompra, proveedor);
                newCotizacion.setFechaEmision(new Date());
                newCotizacion.setEstado("Pendiente");

                Cotizacion savedCotizacion = pedidoCotizacionRepository.save(newCotizacion);

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

        public Optional<Cotizacion> getCotizacionById(Integer id) {
                return pedidoCotizacionRepository.findByIdAndEliminadoFalse(id);
        }

        public List<Cotizacion> getAllCotizaciones() {
                return pedidoCotizacionRepository.findByEliminadoFalse();
        }

        public boolean verifyIfExist(Integer pedidoCompraId, Integer proveedorId) {
                return pedidoCotizacionRepository.existsByPedidoCompraIdAndProveedorIdAndEliminadoFalse(pedidoCompraId,
                                proveedorId);
        }

}
