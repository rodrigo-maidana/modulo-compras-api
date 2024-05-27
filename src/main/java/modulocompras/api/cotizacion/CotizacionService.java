package modulocompras.api.cotizacion;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import modulocompras.api.categoria.Categoria;
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

        // Crear un nuevo cotizaciones
        public CotizacionDTO createCotizacion(CotizacionCreateDTO pedidoCotizacionCreateDTO) {
                PedidoCompraDTO pedidoCompraDTO = pedidoCompraService
                                .getPedidoCompraById(pedidoCotizacionCreateDTO.getIdPedidoCompra())
                                .orElseThrow(() -> new IllegalArgumentException("PedidoCompra no encontrado"));

                ProveedorDTO proveedorDTO = proveedorService
                                .getProveedorById(pedidoCotizacionCreateDTO.getIdProveedor())
                                .orElseThrow(() -> new IllegalArgumentException("Proveedor no encontrado"));

                PedidoCompra pedidoCompra = new PedidoCompra(pedidoCompraDTO);
                Proveedor proveedor = new Proveedor(proveedorDTO);
                pedidoCompra.setEstado("Pedido Cotización Generado");

                PedidoCompraDTO updatedPedidoCompraDTO = pedidoCompraService.updatePedidoCompra(
                                pedidoCompra.getId(), new PedidoCompraDTO(pedidoCompra))
                                .orElseThrow(() -> new IllegalStateException("Error al actualizar PedidoCompra"));

                Cotizacion newCotizacion = new Cotizacion(
                                new PedidoCompra(updatedPedidoCompraDTO), proveedor);
                newCotizacion.setFechaEmision(new Date());
                newCotizacion.setEstado("Pendiente");

                Cotizacion savedCotizacion = pedidoCotizacionRepository.save(newCotizacion);

                List<PedidoDetalle> pedidoDetalles = pedidoDetalleService.getDetallesByPedidoCompraId(
                                pedidoCotizacionCreateDTO.getIdPedidoCompra());
                List<Categoria> categorias = proveedorCategoriaService
                                .getCategorias(pedidoCotizacionCreateDTO.getIdProveedor())
                                .stream()
                                .map(categoriaDTO -> new Categoria(categoriaDTO))
                                .collect(Collectors.toList());

                for (PedidoDetalle pedidoDetalle : pedidoDetalles) {
                        Producto producto = pedidoDetalle.getProducto();
                        Integer cantidad = pedidoDetalle.getCantidad();

                        for (Categoria categoria : categorias) {
                                if (categoria.getId() == producto.getCategoria().getId()) {
                                        cotizacionDetalleService.createCotizacionDetalle(savedCotizacion.getId(),
                                                        producto.getId(), cantidad);
                                }
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

}
