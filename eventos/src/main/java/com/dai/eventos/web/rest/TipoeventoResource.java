package com.dai.eventos.web.rest;

import com.dai.eventos.domain.Tipoevento;
import com.dai.eventos.service.TipoeventoService;
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
 * REST controller for managing {@link com.dai.eventos.domain.Tipoevento}.
 */
@RestController
@RequestMapping("/api")
public class TipoeventoResource {

    private final Logger log = LoggerFactory.getLogger(TipoeventoResource.class);

    private static final String ENTITY_NAME = "eventosTipoevento";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TipoeventoService tipoeventoService;

    public TipoeventoResource(TipoeventoService tipoeventoService) {
        this.tipoeventoService = tipoeventoService;
    }

    /**
     * {@code POST  /tipoeventos} : Create a new tipoevento.
     *
     * @param tipoevento the tipoevento to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tipoevento, or with status {@code 400 (Bad Request)} if the tipoevento has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tipoeventos")
    public ResponseEntity<Tipoevento> createTipoevento(@RequestBody Tipoevento tipoevento) throws URISyntaxException {
        log.debug("REST request to save Tipoevento : {}", tipoevento);
        if (tipoevento.getId() != null) {
            throw new BadRequestAlertException("A new tipoevento cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Tipoevento result = tipoeventoService.save(tipoevento);
        return ResponseEntity.created(new URI("/api/tipoeventos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tipoeventos} : Updates an existing tipoevento.
     *
     * @param tipoevento the tipoevento to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tipoevento,
     * or with status {@code 400 (Bad Request)} if the tipoevento is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tipoevento couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tipoeventos")
    public ResponseEntity<Tipoevento> updateTipoevento(@RequestBody Tipoevento tipoevento) throws URISyntaxException {
        log.debug("REST request to update Tipoevento : {}", tipoevento);
        if (tipoevento.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Tipoevento result = tipoeventoService.save(tipoevento);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tipoevento.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tipoeventos} : get all the tipoeventos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tipoeventos in body.
     */
    @GetMapping("/tipoeventos")
    public List<Tipoevento> getAllTipoeventos() {
        log.debug("REST request to get all Tipoeventos");
        return tipoeventoService.findAll();
    }

    /**
     * {@code GET  /tipoeventos/:id} : get the "id" tipoevento.
     *
     * @param id the id of the tipoevento to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tipoevento, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tipoeventos/{id}")
    public ResponseEntity<Tipoevento> getTipoevento(@PathVariable Long id) {
        log.debug("REST request to get Tipoevento : {}", id);
        Optional<Tipoevento> tipoevento = tipoeventoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tipoevento);
    }

    /**
     * {@code DELETE  /tipoeventos/:id} : delete the "id" tipoevento.
     *
     * @param id the id of the tipoevento to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tipoeventos/{id}")
    public ResponseEntity<Void> deleteTipoevento(@PathVariable Long id) {
        log.debug("REST request to delete Tipoevento : {}", id);
        tipoeventoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
