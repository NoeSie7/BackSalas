package es.plexus.reservas.reservaSalas.dtos.response;

import java.util.ArrayList;
import java.util.List;

import es.plexus.reservas.generic.dtos.response.ResponseDTO;
import es.plexus.reservas.reservaSalas.dtos.ReservaDTO;

/**
 * Response utilizado para devolver una lista de Reservas
 * @author emilio.panero
 *
 */
public class ReservaListResponseDTO extends ResponseDTO {

	private static final long serialVersionUID = -1501604436948426052L;
	
	private List<ReservaDTO> reservaList;
	
	public ReservaListResponseDTO() {
		//Inicializamos la lista para devolver lista vacia
		this.reservaList = new ArrayList<ReservaDTO>();
		
	}

	public List<ReservaDTO> getReservaList() {
		return reservaList;
	}

	public void setReservaList(List<ReservaDTO> reservaList) {
		this.reservaList = reservaList;
	}

}