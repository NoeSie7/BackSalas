package es.plexus.reservas.reservaSalas.dtos;

import java.math.BigDecimal;

import es.plexus.reservas.generic.dtos.GenericDTO;

/**
 * Clase que representa el DTO de Oficinas
 * @author emilio.panero
 *
 */
public class OficinaDTO extends GenericDTO {

	private static final long serialVersionUID = -886926810535685428L;
	
	private BigDecimal idOficina;
	
	private String nombreOficina;
	
	private String direccion;

	public OficinaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public OficinaDTO(BigDecimal idOficina, String nombreOficina, String direccion) {
		super();
		this.idOficina = idOficina;
		this.nombreOficina = nombreOficina;
		this.direccion = direccion;
	}


	public BigDecimal getIdOficina() {
		return idOficina;
	}

	public void setIdOficina(BigDecimal idOficina) {
		this.idOficina = idOficina;
	}

	public String getNombreOficina() {
		return nombreOficina;
	}

	public void setNombreOficina(String nombreOficina) {
		this.nombreOficina = nombreOficina;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "OficinaDTO [idOficina=" + idOficina + ", nombreOficina=" + nombreOficina + ", direccion=" + direccion
				+ "]";
	}

}
