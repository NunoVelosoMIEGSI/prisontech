package com.dai.eventos.service;

import com.dai.eventos.domain.Camara;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Camara}.
 */
public interface CamaraService {

    /**
     * Save a camara.
     *
     * @param camara the entity to save.
     * @return the persisted entity.
     */
    Camara save(Camara camara);

    /**
     * Get all the camaras.
     *
     * @return the list of entities.
     */
    List<Camara> findAll();

    /**
     * Get the "id" camara.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Camara> findOne(Long id);

    /**
     * Delete the "id" camara.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
