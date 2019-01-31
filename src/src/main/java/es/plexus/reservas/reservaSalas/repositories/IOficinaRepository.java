package es.plexus.reservas.reservaSalas.repositories;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.plexus.reservas.reservaSalas.entities.Oficina;

/**
 * Repositorio para acceder a las Oficinas
 * @author emilio.panero
 *
 */
@Repository
public interface IOficinaRepository extends JpaRepository<Oficina, BigDecimal> {


}
