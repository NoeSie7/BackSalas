package es.plexus.reservas.reservaSalas.dtos.response;

import java.util.ArrayList;
import java.util.List;

import es.plexus.reservas.generic.dtos.response.ResponseDTO;
import es.plexus.reservas.reservaSalas.dtos.SalaDTO;

/**
 * Response utilizado para devolver una lista de Salas con las reservas actuales
 * @author pablo.soteloalvarez
 *
 */
public class SalaListResponseDTO extends ResponseDTO {

    private static final long serialVersionUID = 7498267155735537795L;

    private List<SalaDTO> salaList;

    public SalaListResponseDTO() {
        //Inicializamos la lista para devolver lista vacia
        this.setSalaList(new ArrayList<SalaDTO>());
    }

    public List<SalaDTO> getSalaList() {
        return salaList;
    }

    public void setSalaList(List<SalaDTO> salaList) {
        this.salaList = salaList;
    }

}