package es.plexus.reservas.reservaSalas.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.plexus.reservas.generic.entities.GenericEntity;

/**
 * Clase que representa la tabla OFICINA
 * @author emilio.panero
 *
 */
@Entity
@Table(name="OFICINA")
public class Oficina extends GenericEntity {

	private static final long serialVersionUID = -9026102955116709933L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IDOFICINA")
	private BigDecimal idOficina;
	
	@Column(name="NOMBREOFICINA")
	private String nombreOficina;
	
	@Column(name="DIRECCION")
	private String direccion;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
			orphanRemoval = true, mappedBy = "idOficina")
	private List<Sala> salas;
	
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

	public List<Sala> getSalas() {
		return salas;
	}

	public void setSalas(List<Sala> salas) {
		this.salas = salas;
	}
	
}
