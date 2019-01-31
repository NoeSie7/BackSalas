package es.plexus.reservas.reservaSalas.converters;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import es.plexus.reservas.reservaSalas.controllers.ReservaSalaController;
import es.plexus.reservas.reservaSalas.dtos.ReservaDTO;
import es.plexus.reservas.reservaSalas.dtos.ReservaDTOAux;
import es.plexus.reservas.reservaSalas.dtos.response.ReservaAuxListResponseDTO;
import es.plexus.reservas.reservaSalas.dtos.response.ReservaListResponseDTO;

@Component
public class Converter {
	 private static final Logger log = Logger.getLogger(ReservaSalaController.class);
	public ReservaDTO DtoReservaAuxToDtoReserva(ReservaDTOAux reservaAux){

		ReservaDTO reserva = new ReservaDTO();
		
		reserva.setIdReserva(reservaAux.getIdReserva());
		reserva.setIdSala(reservaAux.getIdSala());
		reserva.setUsuario(reservaAux.getUsuario());
		reserva.setPeriodic(reservaAux.getPeriodic());
		reserva.setPeriodicTime(reservaAux.getPeriodicTime());
		
		DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter timeformatter = DateTimeFormatter.ofPattern("HH:mm");
		LocalDate ld = LocalDate.parse(reservaAux.getFecha(), dateformatter);
		LocalTime ltd = LocalTime.parse(reservaAux.getHoraDesde(), timeformatter);
		LocalTime lth = LocalTime.parse(reservaAux.getHoraHasta(), timeformatter);
		
		LocalDateTime horaDesdeLDT; 
		horaDesdeLDT = ld.atTime(ltd);
		Date horaDesde = Date.from(horaDesdeLDT.atZone(ZoneId.systemDefault()).toInstant());
		reserva.setHoraDesde(horaDesde);
		
		LocalDateTime horaHastaLDT; 
		horaHastaLDT = ld.atTime(lth);
		Date horaHasta = Date.from(horaHastaLDT.atZone(ZoneId.systemDefault()).toInstant());
		reserva.setHoraHasta(horaHasta);
		
		reserva.setAsunto(reservaAux.getAsunto());
		
		return reserva;
	}
	public ReservaDTOAux DtoReservaADtoReservaaux(ReservaDTO reserva) {
	
		
			
			ReservaDTOAux reservaAux = new ReservaDTOAux();
			
			reservaAux.setIdReserva(reserva.getIdReserva());
			reservaAux.setIdSala(reserva.getIdSala());
			reservaAux.setUsuario(reserva.getUsuario());
			
			DateFormat timeFormat = new SimpleDateFormat("HH:mm");
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			
			Date horaD = new Date(reserva.getHoraDesde().getTime());
			reservaAux.setHoraDesde(timeFormat.format(horaD));
			
			Date horaH = new Date(reserva.getHoraHasta().getTime());
			reservaAux.setHoraHasta(timeFormat.format(horaH));
			
			reservaAux.setPeriodic(reserva.getPeriodic());
			
			reservaAux.setPeriodicTime(reserva.getPeriodicTime());
			
			reservaAux.setFecha(dateFormat.format(horaD));
			
			reservaAux.setAsunto(reserva.getAsunto());
			
			reservaAux.setMinutoDesde(CalculoMinutosDesde(horaD));
			
			
		
		
		
		return reservaAux;
		
		
	}
	
	public ReservaAuxListResponseDTO DtoReservaToDtoReservaAux(ReservaListResponseDTO response) throws ParseException{
		
		ReservaAuxListResponseDTO responseAux = new ReservaAuxListResponseDTO();
		
		responseAux.setResult(response.getResult());
		responseAux.setMensaje(response.getMensaje());
		
		for (ReservaDTO reserva : response.getReservaList()) {
			
			ReservaDTOAux reservaAux = new ReservaDTOAux();
			
			reservaAux.setIdReserva(reserva.getIdReserva());
			reservaAux.setIdSala(reserva.getIdSala());
			reservaAux.setUsuario(reserva.getUsuario());
			
			DateFormat timeFormat = new SimpleDateFormat("HH:mm");
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			
			Date horaD = new Date(reserva.getHoraDesde().getTime());
			reservaAux.setHoraDesde(timeFormat.format(horaD));
			
			Date horaH = new Date(reserva.getHoraHasta().getTime());
			reservaAux.setHoraHasta(timeFormat.format(horaH));
			
			reservaAux.setFecha(dateFormat.format(horaD));
			
			reservaAux.setAsunto(reserva.getAsunto());
			
			reservaAux.setMinutoDesde(CalculoMinutosDesde(horaD));
			
			responseAux.getReservaAuxList().add(reservaAux);
		}
		
		
		return responseAux;
	}
	
	//((hora de la reserva * 60 + minutos de la reserva) - 480) * 0.131
	//Por ejemplo, si la reserva es de 9:15 a 9:30 el calculo seria el siguiente:
	//((9 * 60 + 15) - 480) * 0.131
	//reservaAux.setMinutoDesde((int) (((horaD.getHours()*60 +horaD.getMinutes())-480)*0.131));
	private Double CalculoMinutosDesde(Date horas){
		
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(horas); 
		
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		Double calculoMinutos;
		
		calculoMinutos= ((((hour*60)+minute)-480)*0.131);
				
		return calculoMinutos;	
	}
	public Date StringToDate(String fechaBuscar){
		
		DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate ld = LocalDate.parse(fechaBuscar, dateformatter);
		
		Instant instant = Instant.from(ld.atStartOfDay(ZoneId.of("GMT")));
		Date fecha = Date.from(instant);
		
		return fecha;
	}
	
}
