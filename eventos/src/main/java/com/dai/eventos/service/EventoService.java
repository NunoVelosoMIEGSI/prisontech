package com.dai.eventos.service;

import com.dai.eventos.domain.Evento;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Evento}.
 */
public interface EventoService {

    /**
     * Save a evento.
     *
     * @param evento the entity to save.
     * @return the persisted entity.
     */
    Evento save(Evento evento);

    /**
     * Get all the eventos.
     *
     * @return the list of entities.
     */
    List<Evento> findAll();

    /**
     * Get the "id" evento.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Evento> findOne(Long id);

    /**
     * Delete the "id" evento.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
