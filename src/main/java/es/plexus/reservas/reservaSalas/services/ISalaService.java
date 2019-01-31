package es.plexus.reservas.reservaSalas.services;

import java.math.BigDecimal;
import java.util.List;

import es.plexus.reservas.generic.exceptions.ExceptionBase;
import es.plexus.reservas.reservaSalas.dtos.SalaDTO;

/**
 * Interface del servicio de Salas
 * @author pablo.sotelo
 *
 */
public interface ISalaService {

    /**
     * Devuelve una lista con todas las Salas de una Oficina
     * @param idoficina
     *
     * @return Lista de Salas
     * @throws ExceptionBase
     */
    public List<SalaDTO> selectByIdOficina(BigDecimal idOficina) throws ExceptionBase;

    
    public SalaDTO selectByIdSala(BigDecimal idSala)throws ExceptionBase;
}
