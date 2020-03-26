package com.dai.eventos.web.rest;

import com.dai.eventos.EventosApp;
import com.dai.eventos.domain.Tipoevento;
import com.dai.eventos.repository.TipoeventoRepository;
import com.dai.eventos.service.TipoeventoService;
import com.dai.eventos.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.dai.eventos.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link TipoeventoResource} REST controller.
 */
@SpringBootTest(classes = EventosApp.class)
public class TipoeventoResourceIT {

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    @Autowired
    private TipoeventoRepository tipoeventoRepository;

    @Autowired
    private TipoeventoService tipoeventoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restTipoeventoMockMvc;

    private Tipoevento tipoevento;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TipoeventoResource tipoeventoResource = new TipoeventoResource(tipoeventoService);
        this.restTipoeventoMockMvc = MockMvcBuilders.standaloneSetup(tipoeventoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tipoevento createEntity(EntityManager em) {
        Tipoevento tipoevento = new Tipoevento()
            .descricao(DEFAULT_DESCRICAO);
        return tipoevento;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tipoevento createUpdatedEntity(EntityManager em) {
        Tipoevento tipoevento = new Tipoevento()
            .descricao(UPDATED_DESCRICAO);
        return tipoevento;
    }

    @BeforeEach
    public void initTest() {
        tipoevento = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoevento() throws Exception {
        int databaseSizeBeforeCreate = tipoeventoRepository.findAll().size();

        // Create the Tipoevento
        restTipoeventoMockMvc.perform(post("/api/tipoeventos")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipoevento)))
            .andExpect(status().isCreated());

        // Validate the Tipoevento in the database
        List<Tipoevento> tipoeventoList = tipoeventoRepository.findAll();
        assertThat(tipoeventoList).hasSize(databaseSizeBeforeCreate + 1);
        Tipoevento testTipoevento = tipoeventoList.get(tipoeventoList.size() - 1);
        assertThat(testTipoevento.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
    }

    @Test
    @Transactional
    public void createTipoeventoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoeventoRepository.findAll().size();

        // Create the Tipoevento with an existing ID
        tipoevento.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoeventoMockMvc.perform(post("/api/tipoeventos")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipoevento)))
            .andExpect(status().isBadRequest());

        // Validate the Tipoevento in the database
        List<Tipoevento> tipoeventoList = tipoeventoRepository.findAll();
        assertThat(tipoeventoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTipoeventos() throws Exception {
        // Initialize the database
        tipoeventoRepository.saveAndFlush(tipoevento);

        // Get all the tipoeventoList
        restTipoeventoMockMvc.perform(get("/api/tipoeventos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoevento.getId().intValue())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO)));
    }
    
    @Test
    @Transactional
    public void getTipoevento() throws Exception {
        // Initialize the database
        tipoeventoRepository.saveAndFlush(tipoevento);

        // Get the tipoevento
        restTipoeventoMockMvc.perform(get("/api/tipoeventos/{id}", tipoevento.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tipoevento.getId().intValue()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO));
    }

    @Test
    @Transactional
    public void getNonExistingTipoevento() throws Exception {
        // Get the tipoevento
        restTipoeventoMockMvc.perform(get("/api/tipoeventos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoevento() throws Exception {
        // Initialize the database
        tipoeventoService.save(tipoevento);

        int databaseSizeBeforeUpdate = tipoeventoRepository.findAll().size();

        // Update the tipoevento
        Tipoevento updatedTipoevento = tipoeventoRepository.findById(tipoevento.getId()).get();
        // Disconnect from session so that the updates on updatedTipoevento are not directly saved in db
        em.detach(updatedTipoevento);
        updatedTipoevento
            .descricao(UPDATED_DESCRICAO);

        restTipoeventoMockMvc.perform(put("/api/tipoeventos")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTipoevento)))
            .andExpect(status().isOk());

        // Validate the Tipoevento in the database
        List<Tipoevento> tipoeventoList = tipoeventoRepository.findAll();
        assertThat(tipoeventoList).hasSize(databaseSizeBeforeUpdate);
        Tipoevento testTipoevento = tipoeventoList.get(tipoeventoList.size() - 1);
        assertThat(testTipoevento.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoevento() throws Exception {
        int databaseSizeBeforeUpdate = tipoeventoRepository.findAll().size();

        // Create the Tipoevento

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipoeventoMockMvc.perform(put("/api/tipoeventos")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipoevento)))
            .andExpect(status().isBadRequest());

        // Validate the Tipoevento in the database
        List<Tipoevento> tipoeventoList = tipoeventoRepository.findAll();
        assertThat(tipoeventoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTipoevento() throws Exception {
        // Initialize the database
        tipoeventoService.save(tipoevento);

        int databaseSizeBeforeDelete = tipoeventoRepository.findAll().size();

        // Delete the tipoevento
        restTipoeventoMockMvc.perform(delete("/api/tipoeventos/{id}", tipoevento.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Tipoevento> tipoeventoList = tipoeventoRepository.findAll();
        assertThat(tipoeventoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
