package modulocompras.api.factura;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {

    public List<Factura> findByEliminadoFalse();

    public Optional<Factura> findByIdAndEliminadoFalse(Integer id);

    public List<Factura> findByEliminadoFalseOrderByFechaEmisionDescIdDesc();

    @Query("SELECT f FROM Factura f WHERE DATE(f.fechaEmision) = DATE(:fecha)")
    List<Factura> findByFechaEmision(@Param("fecha") Date fecha);

}