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
import es.plexus.reservas.reservaSalas.dtos.SalaDTO;
import es.plexus.reservas.reservaSalas.dtos.UsuarioDTO;
import es.plexus.reservas.reservaSalas.entities.Usuario;
import es.plexus.reservas.reservaSalas.mappers.UsuarioMapper;
import es.plexus.reservas.reservaSalas.repositories.IUsuarioRepository;
import es.plexus.reservas.reservaSalas.services.IUsuarioService;

/**
 * Clase que contendra la lógica de los servicios de Usuarios
 * @author emilio.panero
 *
 */
@Service
@Transactional
public class UsuarioServiceImpl extends GenericService<Usuario, SalaDTO> implements IUsuarioService {

    private static final Logger log = Logger.getLogger(UsuarioServiceImpl.class);

    @Autowired
    private IUsuarioRepository usuarioRepository;

    /*
     * (non-Javadoc)
     * @see es.plexus.reservas.reservaSalas.services.IUsuarioService#selectByIdUsuario(java.math.BigDecimal)
     */
    @Override
    public UsuarioDTO selectByIdUsuario(BigDecimal idUsuario) throws ExceptionBase {

        Usuario result = new Usuario();

        try {
            result = this.usuarioRepository.findByIdUsuario(idUsuario);
        } catch (final Exception e) {
            log.error("Error en el acceso al repositorio");
            throw new ExceptionBase(ErrorCodes.RS02, e.getMessage());
        }

        final UsuarioDTO resultDTO = UsuarioMapper.getInstance().convertEntityToDTO(result, UsuarioDTO.class);

        return resultDTO;
    }
    
    /*
     * (non-Javadoc)
     * @see es.plexus.reservas.reservaSalas.services.IUsuarioService#selectByNombre(java.lang.String)
     */
    @Override
    public List<UsuarioDTO> selectByNombre(String nombre) throws ExceptionBase {

        List<Usuario> result = new ArrayList<Usuario>();

        try {
            result = this.usuarioRepository.findByNombreContainingIgnoreCase(nombre);
        } catch (final Exception e) {
            log.error("Error en el acceso al repositorio");
            throw new ExceptionBase(ErrorCodes.RS02, e.getMessage());
        }

        final List<UsuarioDTO> resultDTO = UsuarioMapper.getInstance().convertListEntityToListDTO(result, UsuarioDTO.class);

        return resultDTO;
    }

    /*
     * (non-Javadoc)
     * @see es.plexus.reservas.reservaSalas.services.IUsuarioService#insertNewUsuario(es.plexus.reservas.reservaSalas.entities.Usuario)
     */
    @Override
	public void insertNewUsuario(Usuario usuario) {

       this.usuarioRepository.saveAndFlush(usuario);

	}

}
