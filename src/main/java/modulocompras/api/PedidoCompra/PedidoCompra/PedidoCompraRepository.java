package modulocompras.api.PedidoCompra.PedidoCompra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoCompraRepository extends JpaRepository<PedidoCompra, Integer>{
    // Aquí puedes añadir métodos personalizados de consulta si es necesario

}