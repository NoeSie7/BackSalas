package es.plexus.reservas.reservaSalas.dtos.response;

import es.plexus.reservas.generic.dtos.response.ResponseDTO;
import es.plexus.reservas.reservaSalas.dtos.ReservaDTO;

/**
 * Response utilizado para devolver una Reserva
 * @author emilio.panero
 *
 */
public class ReservaResponseDTO extends ResponseDTO {

	private static final long serialVersionUID = -6389343872888320692L;
	
	private ReservaDTO reserva;
	
	public ReservaResponseDTO() {
		
	}

	public ReservaDTO getReserva() {
		return reserva;
	}
	public void setReserva(ReservaDTO reserva) {
		this.reserva = reserva;
	}

}
