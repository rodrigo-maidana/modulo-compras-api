package modulocompras.api.PedidoCompra.PedidoDetalle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoDetalleRepository extends JpaRepository<PedidoDetalle, Integer>{
    // Aquí puedes añadir métodos personalizados de consulta si es necesario

}