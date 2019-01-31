package es.plexus.reservas.generic.dtos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Clase que representa un DTO Generico.
 * @author emilio.panero
 *
 */
@JsonInclude(Include.NON_NULL)
public class GenericDTO implements Serializable {

	private static final long serialVersionUID = 8760550325156698543L;

}
