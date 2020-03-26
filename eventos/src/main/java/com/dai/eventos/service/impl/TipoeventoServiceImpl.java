package com.dai.eventos.service.impl;

import com.dai.eventos.service.TipoeventoService;
import com.dai.eventos.domain.Tipoevento;
import com.dai.eventos.repository.TipoeventoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Tipoevento}.
 */
@Service
@Transactional
public class TipoeventoServiceImpl implements TipoeventoService {

    private final Logger log = LoggerFactory.getLogger(TipoeventoServiceImpl.class);

    private final TipoeventoRepository tipoeventoRepository;

    public TipoeventoServiceImpl(TipoeventoRepository tipoeventoRepository) {
        this.tipoeventoRepository = tipoeventoRepository;
    }

    /**
     * Save a tipoevento.
     *
     * @param tipoevento the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Tipoevento save(Tipoevento tipoevento) {
        log.debug("Request to save Tipoevento : {}", tipoevento);
        return tipoeventoRepository.save(tipoevento);
    }

    /**
     * Get all the tipoeventos.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Tipoevento> findAll() {
        log.debug("Request to get all Tipoeventos");
        return tipoeventoRepository.findAll();
    }

    /**
     * Get one tipoevento by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Tipoevento> findOne(Long id) {
        log.debug("Request to get Tipoevento : {}", id);
        return tipoeventoRepository.findById(id);
    }

    /**
     * Delete the tipoevento by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Tipoevento : {}", id);
        tipoeventoRepository.deleteById(id);
    }
}
