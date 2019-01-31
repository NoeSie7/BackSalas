package es.plexus.reservas.generic.dtos.response;

import es.plexus.reservas.generic.dtos.GenericDTO;

/**
 * DTO base de respuesta de WS
 * @author emilio.panero
 *
 */
public class ResponseDTO extends GenericDTO {

	private static final long serialVersionUID = -2194987176399975244L;
	
	public static final String RESULT_OK = "Success";
	public static final String RESULT_KO = "Error";
	public static final String RESULT_WARNING = "Warning";

	private String result;
	private String mensaje;
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}