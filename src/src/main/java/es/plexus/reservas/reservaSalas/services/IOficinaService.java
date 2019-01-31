package es.plexus.reservas.reservaSalas.services;

import java.math.BigDecimal;
import java.util.List;

import es.plexus.reservas.generic.exceptions.ExceptionBase;
import es.plexus.reservas.reservaSalas.dtos.OficinaDTO;

/**
 * Interface del servicio de Oficinas
 * @author emilio.panero
 *
 */
public interface IOficinaService {
	
	/**
	 * Devuelve una lista con todas las Oficinas
	 * 
	 * @return Lista de Oficinas
	 * @throws ExceptionBase 
	 */
	public List<OficinaDTO> selectAll() throws ExceptionBase;
	
	public OficinaDTO selectByIdOficina (BigDecimal idOficina) throws ExceptionBase;

}
