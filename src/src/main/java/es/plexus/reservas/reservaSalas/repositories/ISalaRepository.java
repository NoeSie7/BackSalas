package es.plexus.reservas.reservaSalas.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.plexus.reservas.reservaSalas.entities.Sala;

/**
 * Repositorio para acceder a las Salas
 * @author pablo.soteloalvarez
 *
 */
@Repository
public interface ISalaRepository extends JpaRepository<Sala, BigDecimal> {

    public List<Sala> findByIdOficina(BigDecimal idOficina);
   
}
