package modulocompras.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import modulocompras.api.entities.PedidoCompra;

@Repository
public interface PedidoCompraRepository extends JpaRepository<PedidoCompra, Integer> {
    // Aquí puedes añadir métodos personalizados de consulta si es necesario

}