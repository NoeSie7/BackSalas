package es.plexus.reservas.generic.mappers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import es.plexus.reservas.generic.exceptions.ExceptionBase;

/**
 * Clase que representa un mapeador Generico.
 * @author emilio.panero
 *
 * @param <E>
 * @param <D>
 */
public abstract class GenericMapper <E,D extends Object> {
	
	private ModelMapper modelMapper;
	
	public GenericMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public D convertEntityToDTO(E entity, Class<D> clase) throws ExceptionBase {
		D dto = null;
		try {
			dto = (D) modelMapper.map(entity, clase);
		} catch (Exception e) {
			throw new ExceptionBase(null, e.getMessage());
		}
		return dto;
	}
	
	public E convertDTOToEntity(D dto, Class<E> clase) throws ExceptionBase {
		E entity = null;
		try {
			entity = (E) modelMapper.map(dto, clase);
		} catch (Exception e) {
			throw new ExceptionBase(null, e.getMessage());
		}
		return entity;
	}
	
	public List<D> convertListEntityToListDTO(List<E> entities, Class<D> clase) throws ExceptionBase {
		List<D> dtos = new ArrayList<D>();
		try {
			if(entities != null && !entities.isEmpty()) {
				for (E entity : entities) {
					dtos.add(convertEntityToDTO(entity, clase));
				}
			}
		} catch (Exception e) {
			throw new ExceptionBase(null, e.getMessage());
		}
		return dtos;
	}

}
