package es.plexus.reservas.reservaSalas.mappers;

import java.util.List;

import org.modelmapper.ModelMapper;

import es.plexus.reservas.generic.exceptions.ExceptionBase;
import es.plexus.reservas.generic.exceptions.errors.ErrorCodes;
import es.plexus.reservas.generic.mappers.GenericMapper;
import es.plexus.reservas.reservaSalas.dtos.ReservaDTO;
import es.plexus.reservas.reservaSalas.entities.Reserva;

/**
 * Clase mapeadora de Reserva. Transforma DTO en Entity y viceversa
 * @author emilio.panero
 *
 */
public class ReservaMapper extends GenericMapper<Reserva, ReservaDTO> {

	private static ReservaMapper instance = null;
	
	private ReservaMapper() {
		super(getModelMapper());
	}
	
	public static ReservaMapper getInstance() {
		if (instance == null) {
			instance = new ReservaMapper();
		}
		return instance;
	}
	
	public ReservaDTO convertEntityToDTO(Reserva entity, Class<ReservaDTO> clase) throws ExceptionBase  {

		try {
			ReservaDTO dto = super.convertEntityToDTO(entity, clase);
			return dto;
		} catch (Exception e) {
			throw new ExceptionBase(ErrorCodes.RS03, e.getMessage());
		}

	}
	
	public List<ReservaDTO> convertListEntityToListDTO(List<Reserva> entities, Class<ReservaDTO> clase) throws ExceptionBase  {
		try {
			List<ReservaDTO> dtoList = super.convertListEntityToListDTO(entities, clase);
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
