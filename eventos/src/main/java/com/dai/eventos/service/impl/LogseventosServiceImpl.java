package com.dai.eventos.service.impl;

import com.dai.eventos.service.LogseventosService;
import com.dai.eventos.domain.Logseventos;
import com.dai.eventos.repository.LogseventosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Logseventos}.
 */
@Service
@Transactional
public class LogseventosServiceImpl implements LogseventosService {

    private final Logger log = LoggerFactory.getLogger(LogseventosServiceImpl.class);

    private final LogseventosRepository logseventosRepository;

    public LogseventosServiceImpl(LogseventosRepository logseventosRepository) {
        this.logseventosRepository = logseventosRepository;
    }

    /**
     * Save a logseventos.
     *
     * @param logseventos the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Logseventos save(Logseventos logseventos) {
        log.debug("Request to save Logseventos : {}", logseventos);
        return logseventosRepository.save(logseventos);
    }

    /**
     * Get all the logseventos.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Logseventos> findAll() {
        log.debug("Request to get all Logseventos");
        return logseventosRepository.findAll();
    }

    /**
     * Get one logseventos by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Logseventos> findOne(Long id) {
        log.debug("Request to get Logseventos : {}", id);
        return logseventosRepository.findById(id);
    }

    /**
     * Delete the logseventos by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Logseventos : {}", id);
        logseventosRepository.deleteById(id);
    }
}
