package es.plexus.reservas.reservaSalas.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.plexus.reservas.generic.entities.GenericEntity;

/**
 * Clase que representa la tabla SALA
 * @author emilio.panero
 *
 */
@Entity
@Table(name="SALA")
public class Sala extends GenericEntity {

	private static final long serialVersionUID = -1798024931801064386L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IDSALA")
	private BigDecimal idSala;
	
	@Column(name="IDOFICINA")
	private BigDecimal idOficina;
	
	@Column(name="NOMBRE")
	private String nombre;
	
	@Column(name="PLAZAS")
	private String plazas;
	
	@Column(name="DETALLE")
	private String detalle;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
			orphanRemoval = true, mappedBy = "idSala")
	private List<Reserva> reservas;

	public BigDecimal getIdSala() {
		return idSala;
	}

	public void setIdSala(BigDecimal idSala) {
		this.idSala = idSala;
	}

	public BigDecimal getIdOficina() {
		return idOficina;
	}

	public void setIdOficina(BigDecimal idOficina) {
		this.idOficina = idOficina;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPlazas() {
		return plazas;
	}

	public void setPlazas(String plazas) {
		this.plazas = plazas;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	
}