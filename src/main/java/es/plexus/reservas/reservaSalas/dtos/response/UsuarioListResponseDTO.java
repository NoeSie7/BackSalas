package es.plexus.reservas.reservaSalas.dtos.response;

import java.util.ArrayList;
import java.util.List;

import es.plexus.reservas.generic.dtos.response.ResponseDTO;
import es.plexus.reservas.reservaSalas.dtos.UsuarioDTO;

/**
 * Response utilizado para devolver una lista de Usuarios
 * @author emilio.panero
 *
 */
public class UsuarioListResponseDTO extends ResponseDTO {

	private static final long serialVersionUID = -1501604436948426052L;
	
	private List<UsuarioDTO> usuarioList;
	
	public UsuarioListResponseDTO() {
		//Inicializamos la lista para devolver lista vacia
		this.usuarioList = new ArrayList<UsuarioDTO>();
		
	}

	public List<UsuarioDTO> getUsuarioList() {
		return usuarioList;
	}

	public void setUsuarioList(List<UsuarioDTO> usuarioList) {
		this.usuarioList = usuarioList;
	}

}