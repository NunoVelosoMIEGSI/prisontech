package com.dai.eventos.service;

import com.dai.eventos.domain.Logseventos;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Logseventos}.
 */
public interface LogseventosService {

    /**
     * Save a logseventos.
     *
     * @param logseventos the entity to save.
     * @return the persisted entity.
     */
    Logseventos save(Logseventos logseventos);

    /**
     * Get all the logseventos.
     *
     * @return the list of entities.
     */
    List<Logseventos> findAll();

    /**
     * Get the "id" logseventos.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Logseventos> findOne(Long id);

    /**
     * Delete the "id" logseventos.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
