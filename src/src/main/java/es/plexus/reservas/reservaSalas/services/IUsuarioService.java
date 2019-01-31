package es.plexus.reservas.reservaSalas.services;

import java.math.BigDecimal;
import java.util.List;

import es.plexus.reservas.generic.exceptions.ExceptionBase;
import es.plexus.reservas.reservaSalas.dtos.UsuarioDTO;
import es.plexus.reservas.reservaSalas.entities.Usuario;

/**
 * Interface del servicio de Usuarios
 * @author emilio.panero
 *
 */
public interface IUsuarioService {

    /**
     * Devuelve un usuario por su identificador
     * @param idUsuario
     *
     * @return Usuario
     * @throws ExceptionBase
     */
    public UsuarioDTO selectByIdUsuario(BigDecimal idUsuario) throws ExceptionBase;

    /**
     * Devuelve una lista de usuarios por nombre
     * @param String nombre
     *
     * @return List<Usuario>
     * @throws ExceptionBase
     */
    public List<UsuarioDTO> selectByNombre(String nombre) throws ExceptionBase;

	public void insertNewUsuario(Usuario usuario);
    
}
