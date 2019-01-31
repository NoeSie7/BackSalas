package es.plexus.reservas.reservaSalas.dtos;

import java.math.BigDecimal;

import es.plexus.reservas.generic.dtos.GenericDTO;

/**
 * Clase que representa el DTO de Reservas
 * @author emilio.panero
 *
 */
public class UsuarioDTO extends GenericDTO {

	private static final long serialVersionUID = 3660807197222673880L;

	private BigDecimal idUsuario;
	
	private String nombre;
	
	private String extension;
	
	private String email;

	public UsuarioDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsuarioDTO(BigDecimal idUsuario, String nombre, String extension, String email) {
		super();
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.extension = extension;
		this.email = email;
	}

	public BigDecimal getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(BigDecimal idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UsuarioDTO [idUsuario=" + idUsuario + ", nombre=" + nombre + ", extension=" + extension + ", email="
				+ email + "]";
	}

}