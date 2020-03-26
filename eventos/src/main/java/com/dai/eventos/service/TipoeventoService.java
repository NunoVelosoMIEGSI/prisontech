package com.dai.eventos.service;

import com.dai.eventos.domain.Tipoevento;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Tipoevento}.
 */
public interface TipoeventoService {

    /**
     * Save a tipoevento.
     *
     * @param tipoevento the entity to save.
     * @return the persisted entity.
     */
    Tipoevento save(Tipoevento tipoevento);

    /**
     * Get all the tipoeventos.
     *
     * @return the list of entities.
     */
    List<Tipoevento> findAll();

    /**
     * Get the "id" tipoevento.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Tipoevento> findOne(Long id);

    /**
     * Delete the "id" tipoevento.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
