package es.plexus.reservas.reservaSalas.dtos.response;

import java.util.ArrayList;
import java.util.List;

import es.plexus.reservas.generic.dtos.response.ResponseDTO;
import es.plexus.reservas.reservaSalas.dtos.OficinaDTO;

/**
 * Response utilizado para devolver una lista de Oficinas
 * @author emilio.panero
 *
 */
public class OficinaListResponseDTO extends ResponseDTO {

	private static final long serialVersionUID = 7498267155735537795L;
	
	private List<OficinaDTO> oficinaList;
	
	public OficinaListResponseDTO() {
		//Inicializamos la lista para devolver lista vacia
		this.oficinaList = new ArrayList<OficinaDTO>();
		
	}

	public List<OficinaDTO> getOficinaList() {
		return oficinaList;	
	}
	public void setOficinaList(List<OficinaDTO> oficinaList) {
		this.oficinaList = oficinaList;
	}

}