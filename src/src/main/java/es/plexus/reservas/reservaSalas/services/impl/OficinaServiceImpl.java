package es.plexus.reservas.reservaSalas.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.plexus.reservas.generic.exceptions.ExceptionBase;
import es.plexus.reservas.generic.exceptions.errors.ErrorCodes;
import es.plexus.reservas.generic.services.GenericService;
import es.plexus.reservas.reservaSalas.dtos.OficinaDTO;
import es.plexus.reservas.reservaSalas.entities.Oficina;
import es.plexus.reservas.reservaSalas.mappers.OficinaMapper;
import es.plexus.reservas.reservaSalas.repositories.IOficinaRepository;
import es.plexus.reservas.reservaSalas.services.IOficinaService;

/**
 * Clase que contendra la lógica de los servicios de Oficinas
 * @author emilio.panero
 *
 */
@Service
@Transactional
public class OficinaServiceImpl extends GenericService<Oficina, OficinaDTO> implements IOficinaService {
	
	private static final Logger log = Logger.getLogger(OficinaServiceImpl.class);
	
	@Autowired 
	private IOficinaRepository oficinaRepository;

	public List<OficinaDTO> selectAll() throws ExceptionBase {
		
		List<Oficina> result = new ArrayList<Oficina>();
		
		try {
			result = oficinaRepository.findAll();
		} catch (Exception e) {
			log.error("Error en el acceso al repositorio");
			throw new ExceptionBase(ErrorCodes.RS02, e.getMessage());
		}
		
		List<OficinaDTO> resultDTO = OficinaMapper.getInstance().convertListEntityToListDTO(result, OficinaDTO.class);
		
		return resultDTO;
	}
	
	public OficinaDTO selectByIdOficina (BigDecimal idOficina) throws ExceptionBase{
		Oficina result;
		try {
			result = this.oficinaRepository.findOne(idOficina);
		}catch(Exception e) {
			log.error("Error en el acceso al repositorio");
			throw new ExceptionBase(ErrorCodes.RS02, e.getMessage());
		}
		OficinaDTO resultado = OficinaMapper.getInstance().convertEntityToDTO(result, OficinaDTO.class);
		return resultado;
	}
}
