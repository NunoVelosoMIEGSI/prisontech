package com.dai.eventos.service.impl;

import com.dai.eventos.service.AreaService;
import com.dai.eventos.domain.Area;
import com.dai.eventos.repository.AreaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Area}.
 */
@Service
@Transactional
public class AreaServiceImpl implements AreaService {

    private final Logger log = LoggerFactory.getLogger(AreaServiceImpl.class);

    private final AreaRepository areaRepository;

    public AreaServiceImpl(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    /**
     * Save a area.
     *
     * @param area the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Area save(Area area) {
        log.debug("Request to save Area : {}", area);
        return areaRepository.save(area);
    }

    /**
     * Get all the areas.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Area> findAll() {
        log.debug("Request to get all Areas");
        return areaRepository.findAllWithEagerRelationships();
    }

    /**
     * Get all the areas with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<Area> findAllWithEagerRelationships(Pageable pageable) {
        return areaRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     * Get one area by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Area> findOne(Long id) {
        log.debug("Request to get Area : {}", id);
        return areaRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the area by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Area : {}", id);
        areaRepository.deleteById(id);
    }
}
