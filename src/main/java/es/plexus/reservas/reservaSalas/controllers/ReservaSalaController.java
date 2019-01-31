package es.plexus.reservas.reservaSalas.controllers;

import java.math.BigDecimal;
import java.text.ParseException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.plexus.reservas.generic.exceptions.ExceptionBase;
import es.plexus.reservas.generic.exceptions.errors.ErrorCodes;
import es.plexus.reservas.reservaSalas.converters.Converter;
import es.plexus.reservas.reservaSalas.dtos.OficinaDTO;
import es.plexus.reservas.reservaSalas.dtos.ReservaDTO;
import es.plexus.reservas.reservaSalas.dtos.ReservaDTOAux;
import es.plexus.reservas.reservaSalas.dtos.SalaDTO;
import es.plexus.reservas.reservaSalas.dtos.UsuarioDTO;
import es.plexus.reservas.reservaSalas.dtos.response.OficinaListResponseDTO;
import es.plexus.reservas.reservaSalas.dtos.response.OficinaResponseDTO;
import es.plexus.reservas.reservaSalas.dtos.response.ReservaAuxListResponseDTO;
import es.plexus.reservas.reservaSalas.dtos.response.ReservaListResponseDTO;
import es.plexus.reservas.reservaSalas.dtos.response.ReservaResponseDTO;
import es.plexus.reservas.reservaSalas.dtos.response.SalaListResponseDTO;
import es.plexus.reservas.reservaSalas.dtos.response.UsuarioListResponseDTO;
import es.plexus.reservas.reservaSalas.services.IEmailService;
import es.plexus.reservas.reservaSalas.services.IOficinaService;
import es.plexus.reservas.reservaSalas.services.IReservaService;
import es.plexus.reservas.reservaSalas.services.ISalaService;
import es.plexus.reservas.reservaSalas.services.IUsuarioService;

/**
 * Controlador para todas las operaciones
 * 
 * @author emilio.panero
 *
 */
@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class ReservaSalaController {

	private static final Logger log = Logger.getLogger(ReservaSalaController.class);

	@Autowired
	private IOficinaService oficinaService;

	@Autowired
	private ISalaService salaService;

	@Autowired
	private IReservaService reservaService;
	
	@Autowired
	private IEmailService emailService;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private Converter converter;

	/**
	 * Devuelve la lista de todas las oficinas
	 *
	 * @return Objeto de respuesta con el resultado del filtrado
	 */
	@GetMapping("/api/getAllOficinas")
	public OficinaListResponseDTO getAllOficinas() {
		if (log.isDebugEnabled()) {
			log.debug("Buscando todas las oficinas...");
		}

		// Inicializamos valores
		List<OficinaDTO> oficinaDTOList = new ArrayList<OficinaDTO>();
		final OficinaListResponseDTO response = new OficinaListResponseDTO();
		response.setResult(OficinaListResponseDTO.RESULT_OK);
		response.setMensaje(OficinaListResponseDTO.RESULT_OK);

		// Invocamos al servicio
		try {
			oficinaDTOList = this.oficinaService.selectAll();
		} catch (final Exception e) {
			log.error("Error al recuperar la lista de Oficinas");
			ExceptionBase ex = null;
			if (e instanceof ExceptionBase) {
				ex = (ExceptionBase) e;
			} else {
				ex = new ExceptionBase(ErrorCodes.RS01, e.getMessage());
				log.error(e);
			}
			log.error("Codigo error: " + ex.getCodigoExcepcion());
			response.setResult(OficinaListResponseDTO.RESULT_KO);
			response.setMensaje(ex.getCodigoExcepcion() + " " + ex.getMessage());
		}

		// Construimos la respuesta
		if (response.getResult().equals(OficinaListResponseDTO.RESULT_OK)) {
			if ((oficinaDTOList != null) && !oficinaDTOList.isEmpty()) {
				log.debug("Select de todas las oficinas. " + "Total: " + String.valueOf(oficinaDTOList.size()));
				response.setOficinaList(oficinaDTOList);
			} else {
				log.debug("Select de todas las oficinas. " + "Total: 0");
				response.setMensaje("Todavia no hay oficinas.");
			}
		}

		return response;
	}

	/**
	 * Devuelve la lista de todas las salas de una oficina
	 * 
	 * @param idOficina
	 *
	 * @return Objeto de respuesta con el resultado del filtrado
	 */
	@GetMapping("/api/getAllSalasByIdOficina/{idOficina}")
	public SalaListResponseDTO getAllSalasByIdOficina(@PathVariable("idOficina") final BigDecimal idOficina) {
		if (log.isDebugEnabled()) {
			log.debug("Buscando todas las salas de la oficina " + idOficina);
		}

		// Inicializamos valores
		List<SalaDTO> salaDTOList = new ArrayList<SalaDTO>();
		final SalaListResponseDTO response = new SalaListResponseDTO();
		response.setResult(SalaListResponseDTO.RESULT_OK);
		response.setMensaje(SalaListResponseDTO.RESULT_OK);

		// Invocamos al servicio
		try {
			salaDTOList = this.salaService.selectByIdOficina(idOficina);
		} catch (final Exception e) {
			log.error("Error al recuperar la lista de Salas de la oficina");
			ExceptionBase ex = null;
			if (e instanceof ExceptionBase) {
				ex = (ExceptionBase) e;
			} else {
				ex = new ExceptionBase(ErrorCodes.RS01, e.getMessage());
				log.error(e);
			}
			log.error("Codigo error: " + ex.getCodigoExcepcion());
			response.setResult(SalaListResponseDTO.RESULT_KO);
			response.setMensaje(ex.getCodigoExcepcion() + " " + ex.getMessage());
		}

		// Construimos la respuesta
		if (response.getResult().equals(SalaListResponseDTO.RESULT_OK)) {
			if ((salaDTOList != null) && !salaDTOList.isEmpty()) {
				log.debug("Select de todas las salas de la oficina. " + "Total: " + String.valueOf(salaDTOList.size()));
				response.setSalaList(salaDTOList);
			} else {
				log.debug("Select de todas las salas. " + "Total: 0");
				response.setResult(SalaListResponseDTO.RESULT_WARNING);
				response.setMensaje("Todavia no hay salas para esa oficina.");
			}
		}

		return response;
	}

	/**
	 * Devuelve la lista de todas las reservas de una sala
	 * 
	 * @param idSala
	 *
	 * @return Objeto de respuesta con el resultado del filtrado
	 * @throws ParseException
	 */
	@GetMapping("/api/getAllReservasByIdSala/{idSala}")
	public ReservaAuxListResponseDTO getAllReservasByIdSala(@PathVariable("idSala") final BigDecimal idSala)
			throws ParseException {
		if (log.isDebugEnabled()) {
			log.debug("Buscando todas las reservas de la sala " + idSala);
		}

		// Inicializamos valores
		List<ReservaDTO> reservaDTOList = new ArrayList<ReservaDTO>();
		final ReservaListResponseDTO response = new ReservaListResponseDTO();
		response.setResult(ReservaListResponseDTO.RESULT_OK);
		response.setMensaje(ReservaListResponseDTO.RESULT_OK);

		// Invocamos al servicio
		try {
			reservaDTOList = this.reservaService.selectByIdSala(idSala);
		} catch (final Exception e) {
			log.error("Error al recuperar la lista de Reservas");
			ExceptionBase ex = null;
			if (e instanceof ExceptionBase) {
				ex = (ExceptionBase) e;
			} else {
				ex = new ExceptionBase(ErrorCodes.RS01, e.getMessage());
				log.error(e);
			}
			log.error("Codigo error: " + ex.getCodigoExcepcion());
			response.setResult(ReservaListResponseDTO.RESULT_KO);
			response.setMensaje(ex.getCodigoExcepcion() + ": " + ex.getMessage());
		}

		// Construimos la respuesta
		if (response.getResult().equals(ReservaListResponseDTO.RESULT_OK)) {
			if ((reservaDTOList != null) && !reservaDTOList.isEmpty()) {
				log.debug("Select de todas las salas de la oficina. " + "Total: "
						+ String.valueOf(reservaDTOList.size()));
				response.setReservaList(reservaDTOList);
			} else {
				log.debug("Select de todas las salas. " + "Total: 0");
				response.setResult(SalaListResponseDTO.RESULT_WARNING);
				response.setMensaje("Todavia no hay reservas para esa sala.");
			}
		}

		ReservaAuxListResponseDTO responseAux = new ReservaAuxListResponseDTO();
		responseAux = converter.DtoReservaToDtoReservaAux(response);

		return responseAux;
	}

	/**
	 * Devuelve la lista de todas las reservas de una sala para un dia
	 * 
	 * @param idSala
	 * @param day
	 *
	 * @return Objeto de respuesta con el resultado del filtrado
	 * @throws ParseException
	 */
	@GetMapping("/api/sala/{idSala}/reservas/{fechaBuscar}")
	public ReservaAuxListResponseDTO getAllReservasByDay(@PathVariable("idSala") final BigDecimal idSala,
			@PathVariable("fechaBuscar") final String fechaBuscar) throws ParseException {

		Date day;
		day = converter.StringToDate(fechaBuscar);

		if (log.isDebugEnabled()) {
			log.debug("Buscando todas las reservas de la sala " + idSala + " para el día " + day);
		}

		// Inicializamos valores
		List<ReservaDTO> reservaDTOList = new ArrayList<ReservaDTO>();
		final ReservaListResponseDTO response = new ReservaListResponseDTO();
		response.setResult(ReservaListResponseDTO.RESULT_OK);
		response.setMensaje(ReservaListResponseDTO.RESULT_OK);

		// Invocamos al servicio
		try {
			reservaDTOList = this.reservaService.selectByIdSalaAndDay(idSala, day);
		} catch (final Exception e) {
			log.error("Error al recuperar la lista de Reservas");
			ExceptionBase ex = null;
			if (e instanceof ExceptionBase) {
				ex = (ExceptionBase) e;
			} else {
				ex = new ExceptionBase(ErrorCodes.RS01, e.getMessage());
				log.error(e);
			}
			log.error("Codigo error: " + ex.getCodigoExcepcion());
			response.setResult(ReservaListResponseDTO.RESULT_KO);
			response.setMensaje(ex.getCodigoExcepcion() + ": " + ex.getMessage());
		}

		// Construimos la respuesta
		if (response.getResult().equals(ReservaListResponseDTO.RESULT_OK)) {
			if ((reservaDTOList != null) && !reservaDTOList.isEmpty()) {
				log.debug("Select de todas las salas de la oficina. " + "Total: "
						+ String.valueOf(reservaDTOList.size()));
				response.setReservaList(reservaDTOList);
			} else {
				log.debug("Select de todas las salas. " + "Total: 0");
				response.setResult(SalaListResponseDTO.RESULT_WARNING);
				response.setMensaje("Todavia no hay reservas para esa sala.");
			}
		}

		ReservaAuxListResponseDTO responseAux = new ReservaAuxListResponseDTO();
		responseAux = converter.DtoReservaToDtoReservaAux(response);

		return responseAux;
	}

	/**
	 * Inserta o actualiza la reserva recibida según corresponda.
	 * 
	 * @param reserva
	 *            Objeto a insertar/actualizar
	 * @return Objeto de respuesta con la entidad insertada/actualizada
	 */
	@PostMapping("/api/insertOrUpdateReserva")
	public ReservaResponseDTO insertOrUpdateReserva(@RequestBody ReservaDTOAux reservaaux)throws ExceptionBase {
		if (log.isDebugEnabled())
			log.debug("Peticion de insercion o update de reserva por filtro recibida");

		// Llamamos al método para convertir los datos recibidos en los que necesitamos
		// para la base de datos
		ReservaDTO reserva = new ReservaDTO();
		reserva = converter.DtoReservaAuxToDtoReserva(reservaaux);

		// Inicializamos valores
		ReservaDTO reservaDTO = new ReservaDTO();
		ReservaResponseDTO response = new ReservaResponseDTO();
		response.setResult(ReservaResponseDTO.RESULT_OK);
		response.setMensaje(ReservaResponseDTO.RESULT_OK);
		OficinaDTO actualOfi;
		SalaDTO actualSala;
		try {
			actualSala = this.salaService.selectByIdSala(reservaaux.getIdSala());
			actualOfi = this.oficinaService.selectByIdOficina(actualSala.getIdOficina());
		}catch (Exception e) {
			log.error("Error en el acceso a los repositorios");
			throw new ExceptionBase(ErrorCodes.RS02, e.getMessage());
		}
		// Invocamos al servicio
		try {
			
			reservaDTO = reservaService.insertOrUpdateReserva(reserva);
			reservaaux = converter.DtoReservaADtoReservaaux(reserva);
			String destinatario = reservaaux.getUsuario().getEmail();
			StringBuilder cuerpo = new StringBuilder("Hola "+reservaaux.getUsuario().getNombre()+ "!");
			cuerpo.append(System.getProperty("line.separator"));
			cuerpo.append("Su reserva para el dia  "+reservaaux.getFecha()+" de "+reservaaux.getHoraDesde()+" a "+ reservaaux.getHoraHasta());
			cuerpo.append(", en la sala: '" +actualSala.getNombre());
			cuerpo.append("' de la oficina de "+actualOfi.getNombreOficina());
			cuerpo.append(", con asunto: "+ reservaaux.getAsunto() +".");
			cuerpo.append(System.getProperty("line.separator"));
			cuerpo.append("Ha sido confirmada.");
			String asunto = "Confirmacion reserva de sala: "+"'"+reservaaux.getAsunto()+"'";
			emailService.sendMail(destinatario,cuerpo.toString(),asunto);
			

		} catch (Exception e) {
			log.error("Error al realizar el insert o update de la Reserva");
			ExceptionBase ex = null;
			if (e instanceof ExceptionBase) {
				ex = (ExceptionBase) e;
			} else {
				ex = new ExceptionBase(ErrorCodes.RS01, e.getMessage());
				log.error(e);
			}
			log.error("Codigo error: " + ex.getCodigoExcepcion());
			response.setResult(ReservaListResponseDTO.RESULT_KO);
			response.setMensaje(ex.getCodigoExcepcion() + ": " + ex.getMessage());
		}

		// Construimos la respuesta
		if (response.getResult().equals(ReservaListResponseDTO.RESULT_OK)) {
			if ((reservaDTO != null  )) {
				log.debug("Insercion o update correcto.");
				response.setReserva(reservaDTO);
				response.setMensaje("La reserva se ha guardado de forma correcta");
			}
		}

		return response;

	}
	
	/**
	 * Inserta o actualiza la reserva periodica recibida según corresponda.
	 * 
	 * @param reserva
	 *            Objeto a insertar/actualizar
	 * @return Objeto de respuesta con la entidad insertada/actualizada
	 */
	@PostMapping("/api/insertOrUpdateReservas")
	public ReservaAuxListResponseDTO insertOrUpdateReservas(@RequestBody ReservaDTOAux reservaData)throws ParseException, ExceptionBase {
		if (log.isDebugEnabled())
			log.debug("Peticion de insercion o update de reservas por filtro recibida");

		// Llamamos al método para convertir los datos recibidos en los que necesitamos
		// para la base de datos
		ReservaDTOAux reservaaux = reservaData;
		List<ReservaDTO> reservaDTOList = new ArrayList<ReservaDTO>();
		final ReservaListResponseDTO response = new ReservaListResponseDTO();
		ReservaDTO reserva = new ReservaDTO();
		reserva = converter.DtoReservaAuxToDtoReserva(reservaaux);
		// Inicializamos valores
		SalaDTO actualSala = new SalaDTO();
		OficinaDTO actualOfi = new OficinaDTO();
		Calendar calDesde = Calendar.getInstance();
		Calendar calHasta = Calendar.getInstance();
		try {
		actualSala = this.salaService.selectByIdSala(reservaaux.getIdSala());
		actualOfi= this.oficinaService.selectByIdOficina(actualSala.getIdOficina());
		
		}catch(Exception e ) {
			log.error("Error al acceder a los repositorios");
			throw new ExceptionBase(ErrorCodes.RS02, e.getMessage()); 
		}
		StringBuilder respuesta = new StringBuilder("No se han podido insertar las reservas en los dias: ");	
		respuesta.append(System.getProperty("line.separator"));
		StringBuilder envioEmail = new StringBuilder("Hola "+reservaaux.getUsuario().getNombre()+ "!" );
		envioEmail.append(System.getProperty("line.separator"));
		envioEmail.append("Sus reservas con asunto: '"+ reservaaux.getAsunto());
		envioEmail.append("', en la sala: '"+ actualSala.getNombre());		 
		envioEmail.append("' de la oficina de "+actualOfi.getNombreOficina());
		envioEmail.append(" se han realizado correctamente los dias: ");
		envioEmail.append(System.getProperty("line.separator"));
		reserva = new ReservaDTO();
		reserva = converter.DtoReservaAuxToDtoReserva(reservaaux);
		String asunto = "Confirmacion reserva de sala: "+"'"+reservaaux.getAsunto()+"'";
		String destinatario = reservaaux.getUsuario().getEmail();
		ReservaDTO reservaDTO = new ReservaDTO();
		response.setResult(ReservaResponseDTO.RESULT_OK);
		response.setMensaje(ReservaResponseDTO.RESULT_OK);

		// Invocamos al servicio
		try {
			if(reserva.getPeriodic()) {		
				reservaDTO = reservaService.insertOrUpdateReserva(reserva);	
				reservaDTOList.add(reservaDTO);				
				calDesde.setTime(reserva.getHoraDesde());
				calHasta.setTime(reserva.getHoraHasta());	
				reservaaux = converter.DtoReservaADtoReservaaux(reserva);
				envioEmail.append(System.getProperty("line.separator"));				
				envioEmail.append("--> "+reservaaux.getFecha() +" de "+ reservaaux.getHoraDesde() + " a "+reservaaux.getHoraHasta());
				envioEmail.append(System.getProperty("line.separator"));
				int dow;
				ArrayList<Integer> weekDays = reservaData.getWeekDays();
				if(weekDays != null){
					Calendar calDesdeAux = (Calendar) calDesde.clone();
					Calendar calHastaAux = (Calendar) calHasta.clone();
					for (Integer e : weekDays) {
						dow = calDesde.get(Calendar.DAY_OF_WEEK);
						// calcula la cantidad de dias entre la fecha escogida y el primer dia de la semana coincidente
						Integer amount = dow >= e ? (7 - dow) + e++ : e - dow;
						calDesde.add(Calendar.DATE, amount);
						calHasta.add(Calendar.DATE, amount);
						for (int i = 0; i < reserva.getPeriodicTime(); i++) {
							if(i > 0){
								calDesde.add(Calendar.DATE, 7);
								calHasta.add(Calendar.DATE, 7);
							}
							reserva.setHoraDesde(calDesde.getTime());
							reserva.setHoraHasta(calHasta.getTime());
							reservaaux = converter.DtoReservaADtoReservaaux(reserva);
							if(!reservaService.notAvailableSala(reserva)) {
								// se hace la insercion de la reserva
								reservaDTO = reservaService.insertOrUpdateReserva(reserva);
								reservaDTOList.add(reservaDTO);						
								// se añaden los datos al cuerpo del correo que se enviará 
								envioEmail.append(System.getProperty("line.separator"));
								envioEmail.append("--> "+reservaaux.getFecha()+" de "+reservaaux.getHoraDesde()+" a "+reservaaux.getHoraHasta());						
								envioEmail.append(System.getProperty("line.separator"));
							}else {							
								respuesta.append("--> "+reservaaux.getFecha()+" de "+reservaaux.getHoraDesde()+" a "+reservaaux.getHoraHasta());							
								respuesta.append(System.getProperty("line.separator"));
							}	
						}
						calDesde.set(calDesdeAux.get(Calendar.YEAR), calDesdeAux.get(Calendar.MONTH), calDesdeAux.get(Calendar.DATE), calDesdeAux.get(Calendar.HOUR_OF_DAY), calDesdeAux.get(Calendar.MINUTE),calDesdeAux.get(Calendar.SECOND));
						calHasta.set(calHastaAux.get(Calendar.YEAR), calHastaAux.get(Calendar.MONTH), calHastaAux.get(Calendar.DATE), calHastaAux.get(Calendar.HOUR_OF_DAY), calHastaAux.get(Calendar.MINUTE),calHastaAux.get(Calendar.SECOND));
					}
				}else{
					for(int i=0; i<reserva.getPeriodicTime();i++) {
						//se incrementa la fecha
						calDesde.add(Calendar.DATE, 1);						
						calHasta.add(Calendar.DATE, 1);
						dow =  calDesde.get (Calendar.DAY_OF_WEEK);
						//Se comprueba que la insercion sea entre lunes o viernes
						if((dow >= Calendar.MONDAY) && (dow <= Calendar.FRIDAY)) {	
							reserva.setHoraDesde(calDesde.getTime()); 
							reserva.setHoraHasta(calHasta.getTime());
							//se convierte a reservaaux para extraer su fecha						
							reservaaux = converter.DtoReservaADtoReservaaux(reserva);
							//Se comprueba si está disponible ese dia y en caso contrario lo notifica y sigue insertando las demás
							if(!reservaService.notAvailableSala(reserva)) {
								//se hace la insercion de la reserva
								reservaDTO = reservaService.insertOrUpdateReserva(reserva);
								reservaDTOList.add(reservaDTO);						
								//se añaden los datos al cuerpo del correo que se enviará 
								envioEmail.append(System.getProperty("line.separator"));
								envioEmail.append("--> "+reservaaux.getFecha()+" de "+reservaaux.getHoraDesde()+" a "+reservaaux.getHoraHasta());						
								envioEmail.append(System.getProperty("line.separator"));
							}else {							
								respuesta.append("--> "+reservaaux.getFecha()+" de "+reservaaux.getHoraDesde()+" a "+reservaaux.getHoraHasta());							
								respuesta.append(System.getProperty("line.separator"));
							}						
							
						}else {
							calDesde.add(Calendar.DATE, 1);
							calHasta.add(Calendar.DATE, 1);
							i--;
						}
					}
					
				}

			}

		} catch (Exception e) {
			log.error("Error al realizar el insert o update de las Reservas");
			ExceptionBase ex = null;
			if (e instanceof ExceptionBase) {
				ex = (ExceptionBase) e;
			} else {
				ex = new ExceptionBase(ErrorCodes.RS01, e.getMessage());
				log.error(e);
			}
			log.error("Codigo error: " + ex.getCodigoExcepcion());
			response.setResult(ReservaListResponseDTO.RESULT_KO);
			response.setMensaje(ex.getCodigoExcepcion() + ": " + ex.getMessage());
		}

		// Construimos la respuesta
		if (response.getResult().equals(ReservaListResponseDTO.RESULT_OK)) {
			
		
		Integer multiplicadorTotalReservas = reservaData.getWeekDays() != null ? reservaData.getWeekDays().size() : 1;
		
		if((reservaDTOList.size() == (reservaaux.getPeriodicTime() * multiplicadorTotalReservas + 1))  && reservaDTOList != null) {
			String cuerpo = envioEmail.toString();
			emailService.sendMail(destinatario,cuerpo,asunto);
			log.debug("Insercion o update correcto.");
			response.setReservaList(reservaDTOList);
			response.setMensaje("Todas las reservas se ha guardado de forma correcta");
		}else if ((reservaDTOList != null) ) {
			envioEmail.append(System.getProperty("line.separator"));
			envioEmail.append(respuesta.toString());			
			String cuerpo =envioEmail.toString();			
			emailService.sendMail(destinatario,cuerpo,asunto);
			log.debug("Alguna reserva no se pudo insertar");
			response.setReservaList(reservaDTOList);
			response.setMensaje(respuesta.toString());
			}
		
		}
		ReservaAuxListResponseDTO responseAux = new ReservaAuxListResponseDTO();
		responseAux = converter.DtoReservaToDtoReservaAux(response);
		
		return responseAux;

	}
	/**
	 * Elimina la reserva recibida.
	 * 
	 * @param idReserva
	 *            a eliminar
	 * @return Objeto de respuesta con la entidad eliminada
	 */
	@GetMapping("/api/deleteReserva/{idReserva}")
	public ReservaResponseDTO deleteReserva(@PathVariable("idReserva") final BigDecimal idReserva) {
		if (log.isDebugEnabled())
			log.debug("Peticion de eliminacion de reserva por identificador");

		// Inicializamos valores
		ReservaResponseDTO response = new ReservaResponseDTO();
		response.setResult(ReservaResponseDTO.RESULT_OK);
		response.setMensaje(ReservaResponseDTO.RESULT_OK);

		// Invocamos al servicio
		try {
			reservaService.deleteReserva(idReserva);
		} catch (Exception e) {
			log.error("Error al eliminar la Reserva");
			ExceptionBase ex = null;
			if (e instanceof ExceptionBase) {
				ex = (ExceptionBase) e;
			} else {
				ex = new ExceptionBase(ErrorCodes.RS01, e.getMessage());
				log.error(e);
			}
			log.error("Codigo error: " + ex.getCodigoExcepcion());
			response.setResult(ReservaListResponseDTO.RESULT_KO);
			response.setMensaje(ex.getCodigoExcepcion() + ": " + ex.getMessage());
		}

		// Construimos la respuesta
		if (response.getResult().equals(ReservaListResponseDTO.RESULT_OK)) {
			log.debug("Borrado logico correcto.");
			response.setMensaje("Se ha eliminado la reserva de forma correcta.");
		}

		return response;

	}

	/**
	 * Devuelve la lista de todos los usuarios buscando por nombre
	 * 
	 * @param nombre
	 *
	 * @return Objeto de respuesta con el resultado del filtrado
	 */
	@GetMapping("/api/getAllUsuariosByNombre/{nombre}")
	public UsuarioListResponseDTO getAllUsuariosByNombre(@PathVariable("nombre") final String nombre) {
		if (log.isDebugEnabled()) {
			log.debug("Buscando todas los usuarios que cumplen el siguiente patron " + nombre);
		}

		// Inicializamos valores
		List<UsuarioDTO> usuarioDTOList = new ArrayList<UsuarioDTO>();
		final UsuarioListResponseDTO response = new UsuarioListResponseDTO();
		response.setResult(UsuarioListResponseDTO.RESULT_OK);
		response.setMensaje(UsuarioListResponseDTO.RESULT_OK);

		// Invocamos al servicio
		try {
			usuarioDTOList = this.usuarioService.selectByNombre(nombre);
		} catch (final Exception e) {
			log.error("Error al recuperar la lista de Usuarios");
			ExceptionBase ex = null;
			if (e instanceof ExceptionBase) {
				ex = (ExceptionBase) e;
			} else {
				ex = new ExceptionBase(ErrorCodes.RS01, e.getMessage());
				log.error(e);
			}
			log.error("Codigo error: " + ex.getCodigoExcepcion());
			response.setResult(UsuarioListResponseDTO.RESULT_KO);
			response.setMensaje(ex.getCodigoExcepcion() + " " + ex.getMessage());
		}

		// Construimos la respuesta
		if (response.getResult().equals(UsuarioListResponseDTO.RESULT_OK)) {
			if ((usuarioDTOList != null) && !usuarioDTOList.isEmpty()) {
				log.debug("Select de todas los usuarios que cumplen el patron. " + "Total: "
						+ String.valueOf(usuarioDTOList.size()));
				response.setUsuarioList(usuarioDTOList);
			} else {
				log.debug("Select de todos los usuarios que cumplen el patron. " + "Total: 0");
				response.setResult(SalaListResponseDTO.RESULT_WARNING);
				response.setMensaje("No se han recuperado valores.");
			}
		}

		return response;
	}
	
	@PostMapping("/api/checkAvailability")
	public boolean checkAvailability(@RequestBody ReservaDTOAux reservaaux)throws ExceptionBase {
		if (log.isDebugEnabled())
			log.debug("Peticion de comprobar disponibilidad");

		// Llamamos al método para convertir los datos recibidos en los que necesitamos
		// para la base de datos
		ReservaDTO reserva = new ReservaDTO();
		reserva = converter.DtoReservaAuxToDtoReserva(reservaaux);


		// Invocamos al servicio
		try {
			reservaService.checkAvailability(reserva);
		} catch (Exception e) {
			log.error("No hay disponibilidad para esta sala");
			return false;
		}

		return true;

	}
	
	@GetMapping("/api/getOficinaById/{idOficina}")
	public OficinaResponseDTO getOficinaByID(@PathVariable("idOficina") final BigDecimal idOficina){
		if(log.isDebugEnabled()){
			log.debug(String.format("Buscando oficina con id %f", idOficina));
		}
		OficinaDTO office = null;
		final OficinaResponseDTO response = new OficinaResponseDTO();
		response.setResult(OficinaResponseDTO.RESULT_OK);
		response.setMensaje(OficinaResponseDTO.RESULT_OK);
		try {
			office = this.oficinaService.selectByIdOficina(idOficina);
		} catch (Exception e) {
			log.error("Error al buscar la oficina");
			ExceptionBase ex = null;
			if (e instanceof ExceptionBase) {
				ex = (ExceptionBase) e;
			} else {
				ex = new ExceptionBase(ErrorCodes.RS01, e.getMessage());
				log.error(e);
			}
			log.error("Codigo error: " + ex.getCodigoExcepcion());
			response.setResult(OficinaResponseDTO.RESULT_KO);
			response.setMensaje(ex.getCodigoExcepcion() + " " + ex.getMessage());
		}
		if (response.getResult().equals(OficinaResponseDTO.RESULT_OK)) {
			if (office != null) {
				response.setOficina(office);
			} else {
				log.debug("Oficina no encontrada");
				response.setMensaje("Oficina no encontrada");
			}
		}

		return response;
	}
}
