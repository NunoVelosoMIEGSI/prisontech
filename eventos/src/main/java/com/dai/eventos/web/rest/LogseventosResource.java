package com.dai.eventos.web.rest;

import com.dai.eventos.domain.Logseventos;
import com.dai.eventos.service.LogseventosService;
import com.dai.eventos.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.dai.eventos.domain.Logseventos}.
 */
@RestController
@RequestMapping("/api")
public class LogseventosResource {

    private final Logger log = LoggerFactory.getLogger(LogseventosResource.class);

    private static final String ENTITY_NAME = "eventosLogseventos";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LogseventosService logseventosService;

    public LogseventosResource(LogseventosService logseventosService) {
        this.logseventosService = logseventosService;
    }

    /**
     * {@code POST  /logseventos} : Create a new logseventos.
     *
     * @param logseventos the logseventos to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new logseventos, or with status {@code 400 (Bad Request)} if the logseventos has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/logseventos")
    public ResponseEntity<Logseventos> createLogseventos(@RequestBody Logseventos logseventos) throws URISyntaxException {
        log.debug("REST request to save Logseventos : {}", logseventos);
        if (logseventos.getId() != null) {
            throw new BadRequestAlertException("A new logseventos cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Logseventos result = logseventosService.save(logseventos);
        return ResponseEntity.created(new URI("/api/logseventos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /logseventos} : Updates an existing logseventos.
     *
     * @param logseventos the logseventos to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated logseventos,
     * or with status {@code 400 (Bad Request)} if the logseventos is not valid,
     * or with status {@code 500 (Internal Server Error)} if the logseventos couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/logseventos")
    public ResponseEntity<Logseventos> updateLogseventos(@RequestBody Logseventos logseventos) throws URISyntaxException {
        log.debug("REST request to update Logseventos : {}", logseventos);
        if (logseventos.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Logseventos result = logseventosService.save(logseventos);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, logseventos.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /logseventos} : get all the logseventos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of logseventos in body.
     */
    @GetMapping("/logseventos")
    public List<Logseventos> getAllLogseventos() {
        log.debug("REST request to get all Logseventos");
        return logseventosService.findAll();
    }

    /**
     * {@code GET  /logseventos/:id} : get the "id" logseventos.
     *
     * @param id the id of the logseventos to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the logseventos, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/logseventos/{id}")
    public ResponseEntity<Logseventos> getLogseventos(@PathVariable Long id) {
        log.debug("REST request to get Logseventos : {}", id);
        Optional<Logseventos> logseventos = logseventosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(logseventos);
    }

    /**
     * {@code DELETE  /logseventos/:id} : delete the "id" logseventos.
     *
     * @param id the id of the logseventos to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/logseventos/{id}")
    public ResponseEntity<Void> deleteLogseventos(@PathVariable Long id) {
        log.debug("REST request to delete Logseventos : {}", id);
        logseventosService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
