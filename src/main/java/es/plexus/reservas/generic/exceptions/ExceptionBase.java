package es.plexus.reservas.generic.exceptions;

import es.plexus.reservas.generic.exceptions.errors.ErrorCodes;

/**
 * Excepcion Base.
 * @author emilio.panero
 *
 */
public class ExceptionBase extends Exception {
	
	private static final long serialVersionUID = -3842969474172464856L;
	
	private String codigoExcepcion;
	
	public ExceptionBase(ErrorCodes error, String mensaje) {
		super(mensaje);
		if(error!=null && !error.getCodigoError().isEmpty()) {
			this.codigoExcepcion = error.getCodigoError();
		} else  {
			this.codigoExcepcion = ErrorCodes.RSGEC.getCodigoError();
		}
	}
	
	public String getCodigoExcepcion() {
		return this.codigoExcepcion;
	}
}
