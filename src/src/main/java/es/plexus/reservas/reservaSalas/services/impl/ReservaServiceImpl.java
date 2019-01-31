package es.plexus.reservas.reservaSalas.services.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.plexus.reservas.generic.exceptions.ExceptionBase;
import es.plexus.reservas.generic.exceptions.errors.ErrorCodes;
import es.plexus.reservas.generic.services.GenericService;
import es.plexus.reservas.generic.utils.EstadoReserva;
import es.plexus.reservas.generic.utils.GenericUtil;
import es.plexus.reservas.reservaSalas.converters.Converter;
import es.plexus.reservas.reservaSalas.dtos.ReservaDTO;
import es.plexus.reservas.reservaSalas.dtos.ReservaDTOAux;
import es.plexus.reservas.reservaSalas.dtos.UsuarioDTO;
import es.plexus.reservas.reservaSalas.dtos.response.ReservaAuxListResponseDTO;
import es.plexus.reservas.reservaSalas.dtos.response.ReservaListResponseDTO;
import es.plexus.reservas.reservaSalas.entities.Reserva;
import es.plexus.reservas.reservaSalas.entities.Usuario;
import es.plexus.reservas.reservaSalas.mappers.ReservaMapper;
import es.plexus.reservas.reservaSalas.mappers.UsuarioMapper;
import es.plexus.reservas.reservaSalas.repositories.IReservaRepository;
import es.plexus.reservas.reservaSalas.services.IReservaService;
import es.plexus.reservas.reservaSalas.services.IUsuarioService;

/**
 * Clase que contendra la lógica de los servicios de Reservas
 * 
 * @author emilio.panero, jose.regorodriguez
 *
 */
@Service
@Transactional
public class ReservaServiceImpl extends GenericService<Reserva, ReservaDTO> implements IReservaService {

	private static final Logger log = Logger.getLogger(ReservaServiceImpl.class);

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IReservaRepository reservaRepository;

	@Autowired
	private Converter converter;

	
	public List<ReservaDTO> selectByIdSala(BigDecimal idSala) throws ExceptionBase {

		List<Reserva> result = new ArrayList<Reserva>();

		try {
			result = this.reservaRepository.findByIdSalaAndEstadoReservaEquals(idSala,
					EstadoReserva.ACTIVA.getCodigo());
		} catch (final Exception e) {
			log.error("Error en el acceso al repositorio");
			throw new ExceptionBase(ErrorCodes.RS02, e.getMessage());
		}

		List<ReservaDTO> resultDTO = ReservaMapper.getInstance().convertListEntityToListDTO(result, ReservaDTO.class);

		return resultDTO;
	}

	
	public List<ReservaDTO> selectByIdSalaAndDay(BigDecimal idSala, Date day) throws ExceptionBase {
		List<Reserva> result = new ArrayList<Reserva>();

		if (GenericUtil.startTimeGreaterThanEndTime(GenericUtil.startOfDay(), day)) {
			log.error("La fecha de inicio no puede ser pasada");
			throw new ExceptionBase(ErrorCodes.RS07, "La fecha de inicio no puede ser pasada");
		}

		Date dayAfter = GenericUtil.addDays(day, 1);

		try {
			result = this.reservaRepository
					.findByIdSalaAndHoraDesdeAfterAndHoraHastaBeforeAndEstadoReservaEqualsOrderByHoraDesdeAsc(idSala,
							day, dayAfter, EstadoReserva.ACTIVA.getCodigo());
		} catch (final Exception e) {
			log.error("Error en el acceso al repositorio");
			throw new ExceptionBase(ErrorCodes.RS02, e.getMessage());
		}

		List<ReservaDTO> resultDTO = ReservaMapper.getInstance().convertListEntityToListDTO(result, ReservaDTO.class);

		return resultDTO;
	}


	public ReservaDTO insertOrUpdateReserva(ReservaDTO reservaDTO) throws ExceptionBase, ParseException {

		Reserva result = new Reserva();
		ReservaDTO resultDTO = new ReservaDTO();

		// verificar si la reserva tiene horas dentro del horario laboral.
		if (!GenericUtil.reservaIsInHorarioLaboral(reservaDTO.getHoraDesde(), reservaDTO.getHoraHasta())) {
			log.error("La reserva no esta en horario laboral.");
			throw new ExceptionBase(ErrorCodes.RS08, "La reserva no esta en horario laboral.");
		}
		if (GenericUtil.startTimeGreaterThanEndTime(GenericUtil.startOfDay(), reservaDTO.getHoraDesde())) {
			log.error("La fecha de inicio no puede ser pasada");
			throw new ExceptionBase(ErrorCodes.RS07, "La fecha de inicio no puede ser pasada");
		}

		if (GenericUtil.startTimeGreaterThanEndTime(reservaDTO.getHoraDesde(), reservaDTO.getHoraHasta())) {
			log.error("La hora de inicio no puede ser mayor a la hora de fin");
			throw new ExceptionBase(ErrorCodes.RS06, "La hora de inicio no puede ser mayor a la hora de fin");
		}
		if (GenericUtil.reservaHasMinTime(reservaDTO.getHoraDesde(), reservaDTO.getHoraHasta())) {
			log.error("La reserva debe de ser de al menos 15min");
			throw new ExceptionBase(ErrorCodes.RS09, "La reserva debe de ser de al menos 15min");
		}
		// Verificar disponibilidad de la sala para esas horas
		if (notAvailableSala(reservaDTO)) {
			log.error("La sala no esta disponible para esas horas");
			throw new ExceptionBase(ErrorCodes.RS04, "La sala no esta disponible para esas horas");
		}
		// Verificar si existe el usuario y si tiene salas ya reservadas para esas horas
		if (usuarioHasReservedSala(reservaDTO)) {
			log.error("El usuario ya tiene una reserva para esas horas");
			throw new ExceptionBase(ErrorCodes.RS05, "El usuario ya tiene una reserva para esas horas");
		}
		// Si la sala esta disponible
		// Crear objeto reserva con entity usuario más datos de entrada de la reserva
		try {
			result = ReservaMapper.getInstance().convertDTOToEntity(reservaDTO, Reserva.class);
			result = this.reservaRepository.save(result);
		} catch (ExceptionBase e) {
			throw e;
		} catch (final Exception e) {
			log.error("Error en el acceso al repositorio");
			throw new ExceptionBase(ErrorCodes.RS02, e.getMessage());
		}

		resultDTO = ReservaMapper.getInstance().convertEntityToDTO(result, ReservaDTO.class);

		return resultDTO;
	}

	public boolean notAvailableSala(ReservaDTO reservaDTO) throws ParseException {

		List<Reserva> result = new ArrayList<Reserva>();
		List<Reserva> listaDesde = new ArrayList<Reserva>();
		List<Reserva> listaHasta = new ArrayList<Reserva>();
		List<Reserva> listaContiene = new ArrayList<Reserva>();

		List<Reserva> listaColindante = new ArrayList<Reserva>();
		ReservaAuxListResponseDTO listReservaDTOAux = new ReservaAuxListResponseDTO();
		ReservaDTOAux reservaDTOAux = new ReservaDTOAux();

		// Comprobamos si alguna de las reservas ya realizadas coincide en alguno de sus
		// limites con la nueva reserva que se esta intentando realzar
		listaColindante = this.reservaRepository.findByIdSalaAndHoraHastaEqualsOrHoraDesdeEqualsAndEstadoReservaEquals(
				reservaDTO.getIdSala(), reservaDTO.getHoraDesde(), reservaDTO.getHoraHasta(),
				EstadoReserva.ACTIVA.getCodigo());
		if (!listaColindante.isEmpty()) {
			// Formatos de fecha y hora
			DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			DateTimeFormatter timeformatter = DateTimeFormatter.ofPattern("HH:mm");

			// Convertimos la reserva para extraer la fecha
			ReservaListResponseDTO listReservaDTO = new ReservaListResponseDTO();
			listReservaDTO.getReservaList().add(reservaDTO);
			listReservaDTOAux = converter.DtoReservaToDtoReservaAux(listReservaDTO);
			reservaDTOAux = listReservaDTOAux.getReservaAuxList().get(0);
			LocalDate date = LocalDate.parse(reservaDTOAux.getFecha(), dateformatter);

			// Extraemos la horaDesde
			LocalTime ltDesde = LocalTime.parse(reservaDTOAux.getHoraDesde(), timeformatter);
			ltDesde = ltDesde.plusMinutes(1);

			// Extraemos la horaHasta
			LocalTime ltHasta = LocalTime.parse(reservaDTOAux.getHoraHasta(), timeformatter);
			ltHasta = ltHasta.minusMinutes(1);

			// Formamos la nueva fecha-hora desde
			LocalDateTime horaDesdeLDT;
			horaDesdeLDT = date.atTime(ltDesde);
			Date dateDesde = Date.from(horaDesdeLDT.atZone(ZoneId.systemDefault()).toInstant());
			// Modificamos la horaDesde de la reserva
			reservaDTO.setHoraDesde(dateDesde);

			// Formamos la nueva fecha-hora hasta
			LocalDateTime horaHastaLDT;
			horaHastaLDT = date.atTime(ltHasta);
			Date dateHasta = Date.from(horaHastaLDT.atZone(ZoneId.systemDefault()).toInstant());
			// Modificamos la horaHasta de la reserva
			reservaDTO.setHoraHasta(dateHasta);
		}

		if (reservaDTO.getIdReserva() == null) {
			listaDesde = this.reservaRepository
					.findByIdSalaAndHoraDesdeGreaterThanEqualAndHoraDesdeLessThanEqualAndEstadoReservaEquals(
							reservaDTO.getIdSala(), reservaDTO.getHoraDesde(), reservaDTO.getHoraHasta(),
							EstadoReserva.ACTIVA.getCodigo());
			listaHasta = this.reservaRepository
					.findByIdSalaAndHoraDesdeLessThanEqualAndHoraHastaGreaterThanEqualAndEstadoReservaEquals(
							reservaDTO.getIdSala(), reservaDTO.getHoraDesde(), reservaDTO.getHoraHasta(),
							EstadoReserva.ACTIVA.getCodigo());
			listaContiene = this.reservaRepository
					.findByIdSalaAndHoraHastaGreaterThanEqualAndHoraHastaLessThanEqualAndEstadoReservaEquals(
							reservaDTO.getIdSala(), reservaDTO.getHoraDesde(), reservaDTO.getHoraHasta(),
							EstadoReserva.ACTIVA.getCodigo());
		} else {
			listaDesde = this.reservaRepository
					.findByIdSalaAndHoraDesdeGreaterThanEqualAndHoraDesdeLessThanEqualAndIdReservaIsNotInAndEstadoReservaEquals(
							reservaDTO.getIdSala(), reservaDTO.getHoraDesde(), reservaDTO.getHoraHasta(),
							reservaDTO.getIdReserva(), EstadoReserva.ACTIVA.getCodigo());
			listaHasta = this.reservaRepository
					.findByIdSalaAndHoraDesdeLessThanEqualAndHoraHastaGreaterThanEqualAndIdReservaIsNotInAndEstadoReservaEquals(
							reservaDTO.getIdSala(), reservaDTO.getHoraDesde(), reservaDTO.getHoraHasta(),
							reservaDTO.getIdReserva(), EstadoReserva.ACTIVA.getCodigo());
			listaContiene = this.reservaRepository
					.findByIdSalaAndHoraHastaGreaterThanEqualAndHoraHastaLessThanEqualAndIdReservaIsNotInAndEstadoReservaEquals(
							reservaDTO.getIdSala(), reservaDTO.getHoraDesde(), reservaDTO.getHoraHasta(),
							reservaDTO.getIdReserva(), EstadoReserva.ACTIVA.getCodigo());
		}

		result.addAll(listaDesde);
		result.addAll(listaHasta);
		result.addAll(listaContiene);

		return !result.isEmpty();
	}

	private boolean usuarioHasReservedSala(ReservaDTO reservaDTO) throws ExceptionBase {

		List<Reserva> result = new ArrayList<Reserva>();
		Usuario usuario = null;

		if (reservaDTO.getUsuario() != null && reservaDTO.getUsuario().getIdUsuario() != null) {
			UsuarioDTO usuarioDTO = this.usuarioService.selectByIdUsuario(reservaDTO.getUsuario().getIdUsuario());
			usuario = UsuarioMapper.getInstance().convertDTOToEntity(usuarioDTO, Usuario.class);
		}

		// si existe usuario
		if (usuario != null) {
			List<Reserva> listaUsuarioDesde = new ArrayList<Reserva>();
			List<Reserva> listaUsuarioHasta = new ArrayList<Reserva>();
			List<Reserva> listaUsuarioContiene = new ArrayList<Reserva>();
			// verificar horas de ese usuario
			if (reservaDTO.getIdReserva() == null) {
				listaUsuarioDesde = this.reservaRepository
						.findByUsuarioAndHoraDesdeAfterAndHoraDesdeBeforeAndEstadoReservaEquals(usuario,
								reservaDTO.getHoraDesde(), reservaDTO.getHoraHasta(), EstadoReserva.ACTIVA.getCodigo());
				listaUsuarioHasta = this.reservaRepository
						.findByUsuarioAndHoraDesdeBeforeAndHoraHastaAfterAndEstadoReservaEquals(usuario,
								reservaDTO.getHoraDesde(), reservaDTO.getHoraHasta(), EstadoReserva.ACTIVA.getCodigo());
				listaUsuarioContiene = this.reservaRepository
						.findByUsuarioAndHoraHastaAfterAndHoraHastaBeforeAndEstadoReservaEquals(usuario,
								reservaDTO.getHoraDesde(), reservaDTO.getHoraHasta(), EstadoReserva.ACTIVA.getCodigo());
			} else {
				listaUsuarioDesde = this.reservaRepository
						.findByUsuarioAndHoraDesdeAfterAndHoraDesdeBeforeAndIdReservaIsNotInAndEstadoReservaEquals(
								usuario, reservaDTO.getHoraDesde(), reservaDTO.getHoraHasta(),
								reservaDTO.getIdReserva(), EstadoReserva.ACTIVA.getCodigo());
				listaUsuarioHasta = this.reservaRepository
						.findByUsuarioAndHoraDesdeBeforeAndHoraHastaAfterAndIdReservaIsNotInAndEstadoReservaEquals(
								usuario, reservaDTO.getHoraDesde(), reservaDTO.getHoraHasta(),
								reservaDTO.getIdReserva(), EstadoReserva.ACTIVA.getCodigo());
				listaUsuarioContiene = this.reservaRepository
						.findByUsuarioAndHoraHastaAfterAndHoraHastaBeforeAndIdReservaIsNotInAndEstadoReservaAndIdSalaEquals(
								usuario, reservaDTO.getHoraDesde(), reservaDTO.getHoraHasta(),
								reservaDTO.getIdReserva(), EstadoReserva.ACTIVA.getCodigo(), reservaDTO.getIdSala());
			}
			result.addAll(listaUsuarioDesde);
			result.addAll(listaUsuarioHasta);
			result.addAll(listaUsuarioContiene);
		} else {

			// Si el usuario no existe, lo creamos
			usuario = new Usuario();
			usuario.setNombre(reservaDTO.getUsuario().getNombre());
			usuario.setEmail(reservaDTO.getUsuario().getEmail());
			usuario.setExtension(reservaDTO.getUsuario().getExtension());

			this.usuarioService.insertNewUsuario(usuario);
			UsuarioDTO usuarioDTO = new UsuarioDTO();

			usuarioDTO = UsuarioMapper.getInstance().convertEntityToDTO(usuario, UsuarioDTO.class);
			// Asignar id del nuevo usuario a la reserva
			reservaDTO.setUsuario(usuarioDTO);

		}

		return !result.isEmpty();
	}

	
	public void deleteReserva(BigDecimal idReserva) throws ExceptionBase {

		Reserva result = new Reserva();

		try {
			result = this.reservaRepository.findByIdReserva(idReserva);
			result.setEstadoReserva(EstadoReserva.BORRADA.getCodigo());
			result = this.reservaRepository.save(result);
		} catch (final Exception e) {
			log.error("Error en el acceso al repositorio");
			throw new ExceptionBase(ErrorCodes.RS02, e.getMessage());
		}

	}
	
	public boolean checkAvailability(ReservaDTO reservaDTO) throws ParseException, ExceptionBase {
		// Verificar disponibilidad de la sala para esas horas
		//System.out.println("availability");
		log.info("availability");
		log.info(reservaDTO.getIdSala());
		if (notAvailableSala(reservaDTO)) {
			log.error("La sala no esta disponible para esas horas");
			throw new ExceptionBase(ErrorCodes.RS04, "La sala no esta disponible para esas horas");
		}
		return true;
	}
	
}
