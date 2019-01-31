package es.plexus.reservas.reservaSalas.dtos.response;

import es.plexus.reservas.generic.dtos.response.ResponseDTO;
import es.plexus.reservas.reservaSalas.dtos.OficinaDTO;

/**
 * Response utilizado para devolver una oficina
 * @author emilio.panero
 *
 */
public class OficinaResponseDTO extends ResponseDTO {

	private static final long serialVersionUID = 6199031230866686210L;
	
	private OficinaDTO oficina;
	
	public OficinaResponseDTO() {
		
	}

	public OficinaDTO getOficina() {
		return oficina;
	}
	public void setOficina(OficinaDTO oficina) {
		this.oficina = oficina;
	}

}
