package es.plexus.reservas.reservaSalas.mappers;

import java.util.List;

import org.modelmapper.ModelMapper;

import es.plexus.reservas.generic.exceptions.ExceptionBase;
import es.plexus.reservas.generic.exceptions.errors.ErrorCodes;
import es.plexus.reservas.generic.mappers.GenericMapper;
import es.plexus.reservas.reservaSalas.dtos.SalaDTO;
import es.plexus.reservas.reservaSalas.entities.Sala;

/**
 * Clase mapeadora de Sala. Transforma DTO en Entity y viceversa
 * @author pablo.soteloalvarez
 *
 */
public class SalaMapper extends GenericMapper<Sala, SalaDTO> {

    private static SalaMapper instance = null;

    private SalaMapper() {
        super(getModelMapper());
    }

    public static SalaMapper getInstance() {
        if (instance == null) {
            instance = new SalaMapper();
        }
        return instance;
    }

    @Override
    public SalaDTO convertEntityToDTO(Sala entity, Class<SalaDTO> clase) throws ExceptionBase  {

        try {
            final SalaDTO dto = super.convertEntityToDTO(entity, clase);
            return dto;
        } catch (final Exception e) {
            throw new ExceptionBase(ErrorCodes.RS03, e.getMessage());
        }

    }

    @Override
    public List<SalaDTO> convertListEntityToListDTO(List<Sala> entities, Class<SalaDTO> clase) throws ExceptionBase  {
        try {
            final List<SalaDTO> dtoList = super.convertListEntityToListDTO(entities, clase);
            return dtoList;
        } catch (final Exception e) {
            throw new ExceptionBase(ErrorCodes.RS03, e.getMessage());
        }
    }

    private static ModelMapper getModelMapper() {
        final ModelMapper  model = new ModelMapper();
        return model;
    }

}
