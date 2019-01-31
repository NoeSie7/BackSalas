package es.plexus.reservas.reservaSalas.mappers;

import java.util.List;

import org.modelmapper.ModelMapper;

import es.plexus.reservas.generic.exceptions.ExceptionBase;
import es.plexus.reservas.generic.exceptions.errors.ErrorCodes;
import es.plexus.reservas.generic.mappers.GenericMapper;
import es.plexus.reservas.reservaSalas.dtos.OficinaDTO;
import es.plexus.reservas.reservaSalas.entities.Oficina;

/**
 * Clase mapeadora de Oficina. Transforma DTO en Entity y viceversa
 * @author emilio.panero
 *
 */
public class OficinaMapper extends GenericMapper<Oficina, OficinaDTO> {

	private static OficinaMapper instance = null;
	
	private OficinaMapper() {
		super(getModelMapper());
	}
	
	public static OficinaMapper getInstance() {
		if (instance == null) {
			instance = new OficinaMapper();
		}
		return instance;
	}
	
	public OficinaDTO convertEntityToDTO(Oficina entity, Class<OficinaDTO> clase) throws ExceptionBase  {

		try {
			OficinaDTO dto = super.convertEntityToDTO(entity, clase);
			return dto;
		} catch (Exception e) {
			throw new ExceptionBase(ErrorCodes.RS03, e.getMessage());
		}

	}
	
	public List<OficinaDTO> convertListEntityToListDTO(List<Oficina> entities, Class<OficinaDTO> clase) throws ExceptionBase  {
		try {
			List<OficinaDTO> dtoList = super.convertListEntityToListDTO(entities, clase);
			return dtoList;
		} catch (Exception e) {
			throw new ExceptionBase(ErrorCodes.RS03, e.getMessage());
		}
	}
	
	private static ModelMapper getModelMapper() {
		ModelMapper  model = new ModelMapper();
		return model;
	}

}
