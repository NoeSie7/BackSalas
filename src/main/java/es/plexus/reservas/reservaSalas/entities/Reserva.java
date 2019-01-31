package es.plexus.reservas.reservaSalas.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import es.plexus.reservas.generic.entities.GenericEntity;
import es.plexus.reservas.generic.utils.EstadoReserva;

/**
 * Clase que representa la tabla RESERVA
 * @author emilio.panero
 *
 */
@Entity
@Table(name="RESERVA")
public class Reserva extends GenericEntity {

	private static final long serialVersionUID = 2227374056753780689L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IDRESERVA")
	private BigDecimal idReserva;
	
	@Column(name="IDSALA")
	private BigDecimal idSala;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDUSUARIO", referencedColumnName = "IDUSUARIO")
	private Usuario usuario;
	
	@Column(name="HORADESDE")
	private Date horaDesde;
	
	@Column(name="HORAHASTA")
	private Date horaHasta;
	
	@Column(name="ASUNTO")
	private String asunto;
	
	@Column(name="ESTADORESERVA")
	private String estadoReserva = EstadoReserva.ACTIVA.getCodigo();

	public BigDecimal getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(BigDecimal idReserva) {
		this.idReserva = idReserva;
	}

	public BigDecimal getIdSala() {
		return idSala;
	}

	public void setIdSala(BigDecimal idSala) {
		this.idSala = idSala;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getHoraDesde() {
		return horaDesde;
	}

	public void setHoraDesde(Date horaDesde) {
		this.horaDesde = horaDesde;
	}

	public Date getHoraHasta() {
		return horaHasta;
	}

	public void setHoraHasta(Date horaHasta) {
		this.horaHasta = horaHasta;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getEstadoReserva() {
		return estadoReserva;
	}

	public void setEstadoReserva(String estadoReserva) {
		this.estadoReserva = estadoReserva;
	}
	
}
