package es.plexus.reservas.reservaSalas.mappers;

import java.util.List;

import org.modelmapper.ModelMapper;

import es.plexus.reservas.generic.exceptions.ExceptionBase;
import es.plexus.reservas.generic.exceptions.errors.ErrorCodes;
import es.plexus.reservas.generic.mappers.GenericMapper;
import es.plexus.reservas.reservaSalas.dtos.UsuarioDTO;
import es.plexus.reservas.reservaSalas.entities.Usuario;

/**
 * Clase mapeadora de Usuario. Transforma DTO en Entity y viceversa
 * @author emilio.panero
 *
 */
public class UsuarioMapper extends GenericMapper<Usuario, UsuarioDTO> {

    private static UsuarioMapper instance = null;

    private UsuarioMapper() {
        super(getModelMapper());
    }

    public static UsuarioMapper getInstance() {
        if (instance == null) {
            instance = new UsuarioMapper();
        }
        return instance;
    }

    @Override
    public UsuarioDTO convertEntityToDTO(Usuario entity, Class<UsuarioDTO> clase) throws ExceptionBase  {

        try {
            final UsuarioDTO dto = super.convertEntityToDTO(entity, clase);
            return dto;
        } catch (final Exception e) {
            throw new ExceptionBase(ErrorCodes.RS03, e.getMessage());
        }

    }

    @Override
    public List<UsuarioDTO> convertListEntityToListDTO(List<Usuario> entities, Class<UsuarioDTO> clase) throws ExceptionBase  {
        try {
            final List<UsuarioDTO> dtoList = super.convertListEntityToListDTO(entities, clase);
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
