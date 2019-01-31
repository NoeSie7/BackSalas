package es.plexus.reservas.reservaSalas.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.plexus.reservas.reservaSalas.entities.Usuario;

/**
 * Repositorio para acceder a los Usuarios
 * @author emilio.panero
 *
 */
@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, BigDecimal> {

	/**
     * Devuelve el usuario que se corresponde con el identificador
     * @param idUsuario
     * @return Usuario
     */
	public Usuario findByIdUsuario(BigDecimal idUsuario);
	
	/**
     * Devuelve una lista de usuarios que tienen un nombre que se corresponde con el patron
     * @param String nombre
     * @return List<Usuario>
     */
	public List<Usuario> findByNombreContainingIgnoreCase(String nombre);
    
}