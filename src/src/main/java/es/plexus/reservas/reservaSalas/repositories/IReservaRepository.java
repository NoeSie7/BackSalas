package es.plexus.reservas.reservaSalas.repositories;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.plexus.reservas.reservaSalas.entities.Reserva;
import es.plexus.reservas.reservaSalas.entities.Usuario;

/**
 * Repositorio para acceder a las Reservas
 * @author emilio.panero, jose.regorodriguez
 *
 */
@Repository
public interface IReservaRepository extends JpaRepository<Reserva, BigDecimal> {

	/**
     * Devuelve la reserva que se corresponde con el identificador
     * @param idReserva
     * @return Reserva
     */
	public Reserva findByIdReserva(BigDecimal idReserva);
	
    /**
     * Devuelve la lista de reservas activas que hay en una sala
     * @param BigDecimal idSala
     * @param String estadoReserva
     * @return List<Reserva>
     */
	public List<Reserva> findByIdSalaAndEstadoReservaEquals(BigDecimal idSala, String estadoReserva);
	
	/**
     * Devuelve la lista de reservas activas que hay en una sala para un rango de fechas
     * @param BigDecimal idSala
     * @param Date inicio
     * @param Date fin
     * @param String estadoReserva
     * @return List<Reserva>
     */
	public List<Reserva> findByIdSalaAndHoraDesdeAfterAndHoraHastaBeforeAndEstadoReservaEqualsOrderByHoraDesdeAsc(BigDecimal idSala, Date inicio, Date fin, String estadoReserva);
    
    /**
     * Deluelve una lista de reservas activas de una sala, donde su fecha de inicio esta entre las fechas pasadas como parametros 
     * @param BigDecimal idSala
     * @param Date inicio
     * @param Date fin
     * @param String estadoReserva
     * @return List<Reserva>
     */
    public List<Reserva> findByIdSalaAndHoraDesdeGreaterThanEqualAndHoraDesdeLessThanEqualAndEstadoReservaEquals(BigDecimal idSala, Date inicio, Date fin, String estadoReserva);
    
    /**
     * Deluelve una lista de reservas activas de una sala, donde su fecha de inicio esta entre las fechas pasadas como parametros, 
     * excluyendo de la lista la reserva que tenga el identificador pasado como parametro 
     * @param BigDecimal idSala
     * @param Date inicio
     * @param Date fin
     * @param BigDecimal idReserva
     * @param String estadoReserva
     * @return List<Reserva>
     */
    public List<Reserva> findByIdSalaAndHoraDesdeGreaterThanEqualAndHoraDesdeLessThanEqualAndIdReservaIsNotInAndEstadoReservaEquals(BigDecimal idSala, Date inicio, Date fin, BigDecimal idReserva, String estadoReserva);
    
    /**
     * Deluelve una lista de reservas activas de una sala, donde las fechas pasadas como parametros estan contenidas entre sus fechas
     * @param BigDecimal idSala
     * @param Date inicio
     * @param Date fin
     * @param String estadoReserva
     * @return List<Reserva>
     */
    public List<Reserva> findByIdSalaAndHoraDesdeLessThanEqualAndHoraHastaGreaterThanEqualAndEstadoReservaEquals(BigDecimal idSala, Date inicio, Date fin, String estadoReserva);
    
    /**
     * Deluelve una lista de reservas activas de una sala, donde las fechas pasadas como parametros estan contenidas entre sus fechas,
     * excluyendo de la lista la reserva que tenga el identificador pasado como parametro 
     * @param BigDecimal idSala
     * @param Date inicio
     * @param Date fin
     * @param BigDecimal idReserva
     * @param String estadoReserva
     * @return List<Reserva>
     */
    public List<Reserva> findByIdSalaAndHoraDesdeLessThanEqualAndHoraHastaGreaterThanEqualAndIdReservaIsNotInAndEstadoReservaEquals(BigDecimal idSala, Date inicio, Date fin, BigDecimal idReserva, String estadoReserva);
    
    /**
     * Deluelve una lista de reservas activas de una sala, donde la fecha de inicio pasada como parametro esta entre sus fechas
     * @param BigDecimal idSala
     * @param Date inicio
     * @param Date fin
     * @param String estadoReserva
     * @return List<Reserva>
     */
    public List<Reserva> findByIdSalaAndHoraHastaGreaterThanEqualAndHoraHastaLessThanEqualAndEstadoReservaEquals(BigDecimal idSala, Date inicio, Date fin, String estadoReserva);
    
    /**
     * Deluelve una lista de reservas activas de una sala, donde la fecha de inicio pasada como parametro esta entre sus fechas,
     * excluyendo de la lista la reserva que tenga el identificador pasado como parametro
     * @param BigDecimal idSala
     * @param Date inicio
     * @param Date fin
     * @param BigDecimal idReserva
     * @param String estadoReserva
     * @return List<Reserva>
     */
    public List<Reserva> findByIdSalaAndHoraHastaGreaterThanEqualAndHoraHastaLessThanEqualAndIdReservaIsNotInAndEstadoReservaEquals(BigDecimal idSala, Date inicio, Date fin, BigDecimal idReserva, String estadoReserva);
    
    /**
     * Deluelve una lista de reservas activas de un usuario, donde su fecha de inicio esta entre las fechas pasadas como parametros 
     * @param Usuario usuario
     * @param Date inicio
     * @param Date fin
     * @param String estadoReserva
     * @return List<Reserva>
     */
    public List<Reserva> findByUsuarioAndHoraDesdeAfterAndHoraDesdeBeforeAndEstadoReservaEquals(Usuario usuario, Date inicio, Date fin, String estadoReserva);
    
    /**
     * Deluelve una lista de reservas activas de un usuario, donde su fecha de inicio esta entre las fechas pasadas como parametros,
     * excluyendo de la lista la reserva que tenga el identificador pasado como parametro
     * @param Usuario usuario
     * @param Date inicio
     * @param Date fin
     * @param BigDecimal idReserva
     * @param String estadoReserva
     * @return List<Reserva>
     */
    public List<Reserva> findByUsuarioAndHoraDesdeAfterAndHoraDesdeBeforeAndIdReservaIsNotInAndEstadoReservaEquals(Usuario usuario, Date inicio, Date fin, BigDecimal idReserva, String estadoReserva);
    
    /**
     * Deluelve una lista de reservas activas de un usuario, donde las fechas pasadas como parametros estan contenidas entre sus fechas
     * @param Usuario usuario
     * @param Date inicio
     * @param Date fin
     * @param String estadoReserva
     * @return List<Reserva>
     */
    public List<Reserva> findByUsuarioAndHoraDesdeBeforeAndHoraHastaAfterAndEstadoReservaEquals(Usuario usuario, Date inicio, Date fin, String estadoReserva);
    
    /**
     * Deluelve una lista de reservas activas de un usuario, donde las fechas pasadas como parametros estan contenidas entre sus fechas,
     * excluyendo de la lista la reserva que tenga el identificador pasado como parametro
     * @param Usuario usuario
     * @param Date inicio
     * @param Date fin
     * @param BigDecimal idReserva
     * @param String estadoReserva
     * @return List<Reserva>
     */
    public List<Reserva> findByUsuarioAndHoraDesdeBeforeAndHoraHastaAfterAndIdReservaIsNotInAndEstadoReservaEquals(Usuario usuario, Date inicio, Date fin, BigDecimal idReserva, String estadoReserva);
    
    /**
     * Deluelve una lista de reservas activas de un usuario, donde la fecha de inicio pasada como parametro esta entre sus fechas
     * @param Usuario usuario
     * @param Date inicio
     * @param Date fin
     * @param String estadoReserva
     * @return List<Reserva>
     */
    public List<Reserva> findByUsuarioAndHoraHastaAfterAndHoraHastaBeforeAndEstadoReservaEquals(Usuario usuario, Date inicio, Date fin, String estadoReserva);
    
    /**
     * Deluelve una lista de reservas activas de un usuario, donde la fecha de inicio pasada como parametro esta entre sus fechas,
     * excluyendo de la lista la reserva que tenga el identificador pasado como parametro
     * @param Usuario usuario
     * @param Date inicio
     * @param Date fin
     * @param BigDecimal idReserva
     * @param String estadoReserva
     * @return List<Reserva>
     */
    public List<Reserva> findByUsuarioAndHoraHastaAfterAndHoraHastaBeforeAndIdReservaIsNotInAndEstadoReservaEquals(Usuario usuario, Date inicio, Date fin, BigDecimal idReserva, String estadoReserva);
    
    /**
     * Deluelve una lista de reservas activas de una sala, donde las fechas pasadas como parametros estan concuerdan entre sus contrarios, fecha fin con fecha inicio y viceversa
     * @param BigDecimal idReserva
     * @param Date inicio
     * @param Date fin
     * @param String estadoReserva
     * @return List<Reserva>
     */
    public List<Reserva> findByIdSalaAndHoraHastaEqualsOrHoraDesdeEqualsAndEstadoReservaEquals(BigDecimal idSala, Date inicio, Date fin, String estadoReserva);
    
    /**
     * Deluelve una lista de reservas activas de un usuario, donde la fecha de inicio pasada como parametro esta entre sus fechas,
     * excluyendo de la lista la reserva que tenga el identificador pasado como parametro, filtrado por el id de la sala.
     * @param Usuario usuario
     * @param Date inicio
     * @param Date fin
     * @param BigDecimal idReserva
     * @param String estadoReserva
     * @param BigDecimal idSala
     * @return List<Reserva>
     */
	public List<Reserva> findByUsuarioAndHoraHastaAfterAndHoraHastaBeforeAndIdReservaIsNotInAndEstadoReservaAndIdSalaEquals(
			Usuario usuario, Date horaDesde, Date horaHasta, BigDecimal idReserva, String codigo, BigDecimal idSala);
    
}