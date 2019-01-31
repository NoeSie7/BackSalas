package es.plexus.reservas.reservaSalas.dtos;

import java.math.BigDecimal;
import java.util.Date;

import es.plexus.reservas.generic.dtos.GenericDTO;
import es.plexus.reservas.generic.utils.EstadoReserva;

/**
 * Clase que representa el DTO de Reservas
 * @author emilio.panero
 *
 */
public class ReservaDTO extends GenericDTO {

	private static final long serialVersionUID = -1376375048577471967L;

	private BigDecimal idReserva;
	
	private BigDecimal idSala;
	
	private UsuarioDTO usuario;
	
	private Date horaDesde;
	
	private Date horaHasta;
	
	private Boolean periodic;
	
	private Integer periodicTime;
	
	private String asunto;
	
	private String estadoReserva  = EstadoReserva.ACTIVA.getCodigo();

	public ReservaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReservaDTO(BigDecimal idReserva, BigDecimal idSala, UsuarioDTO usuario, Date horaDesde, Date horaHasta,
			Boolean periodic, Integer periodicTime, String asunto, String estadoReserva) {
		super();
		this.idReserva = idReserva;
		this.idSala = idSala;
		this.usuario = usuario;
		this.horaDesde = horaDesde;
		this.horaHasta = horaHasta;
		this.periodic = periodic;
		this.periodicTime = periodicTime;
		this.asunto = asunto;
		this.estadoReserva = estadoReserva;
	}

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
	
	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
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
	
	public Boolean getPeriodic() {
		return periodic;
	}
	public void setPeriodic(Boolean periodic) {
		this.periodic = periodic;
	}
	
	public Integer getPeriodicTime() {
		return periodicTime;
	}
	
	public void setPeriodicTime(Integer periodicTime) {
		this.periodicTime = periodicTime;
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

	@Override
	public String toString() {
		return "ReservaDTO [idReserva=" + idReserva + ", idSala=" + idSala + ", usuario=" + usuario + ", horaDesde="
				+ horaDesde + ", horaHasta=" + horaHasta + ", asunto=" + asunto + ", estadoReserva=" + estadoReserva
				+ "]";
	}

}