/**
 *
 */
package es.plexus.reservas.reservaSalas.dtos;

import java.math.BigDecimal;

import es.plexus.reservas.generic.dtos.GenericDTO;

/**
 * Clase que representa el DTO de Salas
 * @author pablo.soteloalvarez
 *
 */
public class SalaDTO extends GenericDTO{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private BigDecimal idSala;
    private BigDecimal idOficina;
    private String nombre;
    private String plazas;
    private String detalle;

    public SalaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SalaDTO(BigDecimal idSala, BigDecimal idOficina, String nombre, String plazas, String detalle) {
		super();
		this.idSala = idSala;
		this.idOficina = idOficina;
		this.nombre = nombre;
		this.plazas = plazas;
		this.detalle = detalle;
	}

	public BigDecimal getIdSala() {
        return this.idSala;
    }

    public void setIdSala(BigDecimal idSala) {
        this.idSala = idSala;
    }

    public BigDecimal getIdOficina() {
        return this.idOficina;
    }

    public void setIdOficina(BigDecimal idOficina) {
        this.idOficina = idOficina;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPlazas() {
        return this.plazas;
    }

    public void setPlazas(String plazas) {
        this.plazas = plazas;
    }

    public String getDetalle() {
        return this.detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

	@Override
	public String toString() {
		return "SalaDTO [idSala=" + idSala + ", idOficina=" + idOficina + ", nombre=" + nombre + ", plazas=" + plazas
				+ ", detalle=" + detalle + "]";
	}

}
