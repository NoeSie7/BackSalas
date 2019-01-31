package es.plexus.reservas.reservaSalas.services;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import es.plexus.reservas.generic.exceptions.ExceptionBase;
import es.plexus.reservas.reservaSalas.dtos.ReservaDTO;

/**
 * Interface del servicio de Reservas
 * @author emilio.panero
 *
 */
public interface IReservaService {

    /**
     * Devuelve una lista con todas las Reservas de una Sala
     * @param idSala
     *
     * @return Lista de Reservas
     * @throws ExceptionBase
     */
    public List<ReservaDTO> selectByIdSala(BigDecimal idSala) throws ExceptionBase;
    
    /**
     * Devuelve una lista con todas las Reservas de una Sala para un dia
     * @param idSala
     * @param day
     *
     * @return Lista de Reservas
     * @throws ExceptionBase
     */
    public List<ReservaDTO> selectByIdSalaAndDay(BigDecimal idSala, Date day) throws ExceptionBase;
    
    /**
     * Devuelve una Reserva que se ha creado
     * @param reservaDTO
     *
     * @return ReservaDTO
     * @throws ExceptionBase
     * @throws ParseException 
     */
    public ReservaDTO insertOrUpdateReserva(ReservaDTO reservaDTO) throws ExceptionBase, ParseException;
    
    /**
     * Elimina una Reserva
     * @param idReserva
     *
     * @throws ExceptionBase
     */
    public void deleteReserva(BigDecimal idReserva) throws ExceptionBase;
    
    
    public boolean notAvailableSala(ReservaDTO reservaDTO) throws ParseException;
    
    public boolean checkAvailability(ReservaDTO reservaDTO) throws ParseException, ExceptionBase;

}
