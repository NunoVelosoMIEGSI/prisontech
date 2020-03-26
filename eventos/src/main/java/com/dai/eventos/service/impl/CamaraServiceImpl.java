package com.dai.eventos.service.impl;

import com.dai.eventos.service.CamaraService;
import com.dai.eventos.domain.Camara;
import com.dai.eventos.repository.CamaraRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Camara}.
 */
@Service
@Transactional
public class CamaraServiceImpl implements CamaraService {

    private final Logger log = LoggerFactory.getLogger(CamaraServiceImpl.class);

    private final CamaraRepository camaraRepository;

    public CamaraServiceImpl(CamaraRepository camaraRepository) {
        this.camaraRepository = camaraRepository;
    }

    /**
     * Save a camara.
     *
     * @param camara the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Camara save(Camara camara) {
        log.debug("Request to save Camara : {}", camara);
        return camaraRepository.save(camara);
    }

    /**
     * Get all the camaras.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Camara> findAll() {
        log.debug("Request to get all Camaras");
        return camaraRepository.findAll();
    }

    /**
     * Get one camara by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Camara> findOne(Long id) {
        log.debug("Request to get Camara : {}", id);
        return camaraRepository.findById(id);
    }

    /**
     * Delete the camara by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Camara : {}", id);
        camaraRepository.deleteById(id);
    }
}
