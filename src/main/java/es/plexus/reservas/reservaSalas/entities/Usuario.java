package es.plexus.reservas.reservaSalas.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import es.plexus.reservas.generic.entities.GenericEntity;

/**
 * Clase que representa la tabla USUARIO
 * @author emilio.panero
 *
 */
@Entity
@Table(name="USUARIO")
public class Usuario extends GenericEntity {

	private static final long serialVersionUID = 7192445490170772652L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IDUSUARIO")
	private BigDecimal idUsuario;
	
	@Column(name="NOMBRE")
	private String nombre;
	
	@Column(name="EXTENSION")
	private String extension;
	
	@Column(name="EMAIL")
	private String email;
	
//	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
//			orphanRemoval = true, mappedBy = "idUsuario")
//	private List<Reserva> reservas;

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

//	public List<Reserva> getReservas() {
//		return reservas;
//	}
//
//	public void setReservas(List<Reserva> reservas) {
//		this.reservas = reservas;
//	}

}