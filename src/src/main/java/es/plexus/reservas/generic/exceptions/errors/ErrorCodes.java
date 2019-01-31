/**
 * 
 */
package es.plexus.reservas.generic.exceptions.errors;

/**
 * Enum donde se definen los codigos de error y mensajes.
 * @author emilio.panero
 *
 */
public enum ErrorCodes {
	
	RSGEC("RSGEC", "Error generico en la aplicacion"),
	RS01("RS01", "Error generico"),
	RS02("RS02", "Error accediendo a los datos"),
	RS03("RS03", "Error convirtiendo las entidades"),
	RS04("RS04", "La sala no esta disponible para esas horas"),
	RS05("RS05", "El usuario ya tiene una reserva para esas horas"),
	RS06("RS06", "La hora de inicio no puede ser mayor a la hora de fin"),
	RS07("RS07", "La fecha de inicio no puede ser pasada"),
	RS08("RS08","La reserva no esta en horario laboral."),
	RS09("RS09","La reserva debe de ser de al menos 15min");
	
	private String codigoError; 
	private String mensaje; 
	
	private ErrorCodes(String codigoError, String mensaje) {
		this.codigoError = codigoError;
		this.mensaje = mensaje;
	}
	
	public String getCodigoError() {
		return this.codigoError;
	}
	
	public String getMensaje() {
		return this.mensaje;
	}

}