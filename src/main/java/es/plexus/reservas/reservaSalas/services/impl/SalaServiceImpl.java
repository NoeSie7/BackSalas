package es.plexus.reservas.reservaSalas.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.plexus.reservas.generic.exceptions.ExceptionBase;
import es.plexus.reservas.generic.exceptions.errors.ErrorCodes;
import es.plexus.reservas.generic.services.GenericService;
import es.plexus.reservas.reservaSalas.dtos.SalaDTO;
import es.plexus.reservas.reservaSalas.entities.Sala;
import es.plexus.reservas.reservaSalas.mappers.SalaMapper;
import es.plexus.reservas.reservaSalas.repositories.ISalaRepository;
import es.plexus.reservas.reservaSalas.services.ISalaService;

/**
 * Clase que contendra la l�gica de los servicios de Salas
 * @author pablo.soteloalvarez
 *
 */
@Service
@Transactional
public class SalaServiceImpl extends GenericService<Sala, SalaDTO> implements ISalaService {

    private static final Logger log = Logger.getLogger(SalaServiceImpl.class);

    @Autowired
    private ISalaRepository salaRepository;

    @Override
    public List<SalaDTO> selectByIdOficina(BigDecimal idOficina) throws ExceptionBase {

        List<Sala> result = new ArrayList<Sala>();

        try {
            result = this.salaRepository.findByIdOficina(idOficina);
        } catch (final Exception e) {
            log.error("Error en el acceso al repositorio");
            throw new ExceptionBase(ErrorCodes.RS02, e.getMessage());
        }

        final List<SalaDTO> resultDTO = SalaMapper.getInstance().convertListEntityToListDTO(result, SalaDTO.class);

        return resultDTO;
    }
    
    public SalaDTO selectByIdSala(BigDecimal idSala)throws ExceptionBase {
    	Sala result;
    	 try {
    	result = this.salaRepository.findOne(idSala);
    	
    	
    	 }catch(final Exception e) {
    		 log.error("Error al acceder al repositorio");
    		 throw new ExceptionBase(ErrorCodes.RS02, e.getMessage());
    	 }
    	  SalaDTO resultado = SalaMapper.getInstance().convertEntityToDTO(result, SalaDTO.class);
    	 return resultado;
    }

}
