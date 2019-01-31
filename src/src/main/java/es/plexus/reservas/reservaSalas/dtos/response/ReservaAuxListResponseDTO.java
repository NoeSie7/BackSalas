package es.plexus.reservas.reservaSalas.dtos.response;

import java.util.ArrayList;
import java.util.List;

import es.plexus.reservas.generic.dtos.response.ResponseDTO;
import es.plexus.reservas.reservaSalas.dtos.ReservaDTOAux;

/**
 * Response utilizado para devolver una lista de ReservasAux
 *
 */
public class ReservaAuxListResponseDTO extends ResponseDTO {

	private static final long serialVersionUID = 9219337743132466467L;
	
	private List<ReservaDTOAux> reservaAuxList;
	
	public ReservaAuxListResponseDTO() {
		//Inicializamos la lista para devolver lista vacia
		this.reservaAuxList = new ArrayList<ReservaDTOAux>();
		
	}

	public List<ReservaDTOAux> getReservaAuxList() {
		return reservaAuxList;
	}

	public void setReservaAuxList(List<ReservaDTOAux> reservaAuxList) {
		this.reservaAuxList = reservaAuxList;
	}

}