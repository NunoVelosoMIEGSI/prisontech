package com.dai.eventos.web.rest;

import com.dai.eventos.domain.Camara;
import com.dai.eventos.service.CamaraService;
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
 * REST controller for managing {@link com.dai.eventos.domain.Camara}.
 */
@RestController
@RequestMapping("/api")
public class CamaraResource {

    private final Logger log = LoggerFactory.getLogger(CamaraResource.class);

    private static final String ENTITY_NAME = "eventosCamara";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CamaraService camaraService;

    public CamaraResource(CamaraService camaraService) {
        this.camaraService = camaraService;
    }

    /**
     * {@code POST  /camaras} : Create a new camara.
     *
     * @param camara the camara to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new camara, or with status {@code 400 (Bad Request)} if the camara has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/camaras")
    public ResponseEntity<Camara> createCamara(@RequestBody Camara camara) throws URISyntaxException {
        log.debug("REST request to save Camara : {}", camara);
        if (camara.getId() != null) {
            throw new BadRequestAlertException("A new camara cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Camara result = camaraService.save(camara);
        return ResponseEntity.created(new URI("/api/camaras/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /camaras} : Updates an existing camara.
     *
     * @param camara the camara to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated camara,
     * or with status {@code 400 (Bad Request)} if the camara is not valid,
     * or with status {@code 500 (Internal Server Error)} if the camara couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/camaras")
    public ResponseEntity<Camara> updateCamara(@RequestBody Camara camara) throws URISyntaxException {
        log.debug("REST request to update Camara : {}", camara);
        if (camara.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Camara result = camaraService.save(camara);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, camara.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /camaras} : get all the camaras.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of camaras in body.
     */
    @GetMapping("/camaras")
    public List<Camara> getAllCamaras() {
        log.debug("REST request to get all Camaras");
        return camaraService.findAll();
    }

    /**
     * {@code GET  /camaras/:id} : get the "id" camara.
     *
     * @param id the id of the camara to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the camara, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/camaras/{id}")
    public ResponseEntity<Camara> getCamara(@PathVariable Long id) {
        log.debug("REST request to get Camara : {}", id);
        Optional<Camara> camara = camaraService.findOne(id);
        return ResponseUtil.wrapOrNotFound(camara);
    }

    /**
     * {@code DELETE  /camaras/:id} : delete the "id" camara.
     *
     * @param id the id of the camara to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/camaras/{id}")
    public ResponseEntity<Void> deleteCamara(@PathVariable Long id) {
        log.debug("REST request to delete Camara : {}", id);
        camaraService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
