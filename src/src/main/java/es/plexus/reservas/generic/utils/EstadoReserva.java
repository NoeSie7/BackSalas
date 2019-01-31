/**
 * 
 */
package es.plexus.reservas.generic.utils;

/**
 * Enum donde se definen los diferentes estados de una reserva.
 * @author emilio.panero
 *
 */
public enum EstadoReserva {
	
	ACTIVA("ACTIVA", "Reserva activa"),
	BORRADA("BORRADA", "Reserva con borrado logico");
	
	private String codigo; 
	private String descripcion; 
	
	private EstadoReserva(String codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	
	public String getCodigo() {
		return this.codigo;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}

}