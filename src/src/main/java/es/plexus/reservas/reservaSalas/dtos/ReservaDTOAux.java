package es.plexus.reservas.reservaSalas.dtos;

import java.math.BigDecimal;
import java.util.ArrayList;

import es.plexus.reservas.generic.dtos.GenericDTO;

/**
 * Clase que representa el DTO de Reservas
 * @author emilio.panero
 *
 */
public class ReservaDTOAux extends GenericDTO {

	private static final long serialVersionUID = -1376375048577471967L;

	private BigDecimal idReserva;
	
	private BigDecimal idSala;
	
	private UsuarioDTO usuario;
	
	private String fecha;
	
	private String horaDesde;
	
	private String horaHasta;

	private Boolean periodic;
	
	private Integer periodicTime;
	
	private ArrayList<Integer> weekDays;
	
	
	private String asunto;
	
	private Double	minutoDesde;

	public ReservaDTOAux() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ReservaDTOAux(BigDecimal idReserva, BigDecimal idSala, UsuarioDTO usuario, String fecha, String horaDesde,
			String horaHasta, Boolean periodic, Integer periodicTime, String asunto, Double minutoDesde) {
		super();
		this.idReserva = idReserva;
		this.idSala = idSala;
		this.usuario = usuario;
		this.fecha = fecha;
		this.horaDesde = horaDesde;
		this.horaHasta = horaHasta;
		this.periodic = periodic;
		this.periodicTime = periodicTime;
		this.asunto = asunto;
		this.minutoDesde = minutoDesde;
	}
	
	public Double getMinutoDesde() {
		return minutoDesde;
	}

	public void setMinutoDesde(Double minutoDesde) {
		this.minutoDesde = minutoDesde;
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

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHoraDesde() {
		return horaDesde;
	}

	public void setHoraDesde(String horaDesde) {
		this.horaDesde = horaDesde;
	}

	public String getHoraHasta() {
		return horaHasta;
	}

	public void setHoraHasta(String horaHasta) {
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

	@Override
	public String toString() {
		return "ReservaDTOAux [idReserva=" + idReserva + ", idSala=" + idSala + ", usuario=" + usuario + ", fecha="
				+ fecha + ", horaDesde=" + horaDesde + ", horaHasta=" + horaHasta + ", asunto=" + asunto
				+ ", minutoDesde=" + minutoDesde + "]";
	}
	
	
	
	public ArrayList<Integer> getWeekDays(){
		return this.weekDays;
	}

	public void setWeekDays(ArrayList<Integer> weekDays){
		this.weekDays = weekDays;
	}
}