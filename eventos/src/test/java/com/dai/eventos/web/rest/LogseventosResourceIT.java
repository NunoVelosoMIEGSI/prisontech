package com.dai.eventos.web.rest;

import com.dai.eventos.EventosApp;
import com.dai.eventos.domain.Logseventos;
import com.dai.eventos.repository.LogseventosRepository;
import com.dai.eventos.service.LogseventosService;
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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static com.dai.eventos.web.rest.TestUtil.sameInstant;
import static com.dai.eventos.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link LogseventosResource} REST controller.
 */
@SpringBootTest(classes = EventosApp.class)
public class LogseventosResourceIT {

    private static final ZonedDateTime DEFAULT_DATA_HORA_INICIO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA_HORA_INICIO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATA_HORA_FIM = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA_HORA_FIM = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CLASSE_ORIGEM = "AAAAAAAAAA";
    private static final String UPDATED_CLASSE_ORIGEM = "BBBBBBBBBB";

    private static final String DEFAULT_METODO_ORIGEM = "AAAAAAAAAA";
    private static final String UPDATED_METODO_ORIGEM = "BBBBBBBBBB";

    @Autowired
    private LogseventosRepository logseventosRepository;

    @Autowired
    private LogseventosService logseventosService;

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

    private MockMvc restLogseventosMockMvc;

    private Logseventos logseventos;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LogseventosResource logseventosResource = new LogseventosResource(logseventosService);
        this.restLogseventosMockMvc = MockMvcBuilders.standaloneSetup(logseventosResource)
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
    public static Logseventos createEntity(EntityManager em) {
        Logseventos logseventos = new Logseventos()
            .dataHoraInicio(DEFAULT_DATA_HORA_INICIO)
            .dataHoraFim(DEFAULT_DATA_HORA_FIM)
            .classeOrigem(DEFAULT_CLASSE_ORIGEM)
            .metodoOrigem(DEFAULT_METODO_ORIGEM);
        return logseventos;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Logseventos createUpdatedEntity(EntityManager em) {
        Logseventos logseventos = new Logseventos()
            .dataHoraInicio(UPDATED_DATA_HORA_INICIO)
            .dataHoraFim(UPDATED_DATA_HORA_FIM)
            .classeOrigem(UPDATED_CLASSE_ORIGEM)
            .metodoOrigem(UPDATED_METODO_ORIGEM);
        return logseventos;
    }

    @BeforeEach
    public void initTest() {
        logseventos = createEntity(em);
    }

    @Test
    @Transactional
    public void createLogseventos() throws Exception {
        int databaseSizeBeforeCreate = logseventosRepository.findAll().size();

        // Create the Logseventos
        restLogseventosMockMvc.perform(post("/api/logseventos")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(logseventos)))
            .andExpect(status().isCreated());

        // Validate the Logseventos in the database
        List<Logseventos> logseventosList = logseventosRepository.findAll();
        assertThat(logseventosList).hasSize(databaseSizeBeforeCreate + 1);
        Logseventos testLogseventos = logseventosList.get(logseventosList.size() - 1);
        assertThat(testLogseventos.getDataHoraInicio()).isEqualTo(DEFAULT_DATA_HORA_INICIO);
        assertThat(testLogseventos.getDataHoraFim()).isEqualTo(DEFAULT_DATA_HORA_FIM);
        assertThat(testLogseventos.getClasseOrigem()).isEqualTo(DEFAULT_CLASSE_ORIGEM);
        assertThat(testLogseventos.getMetodoOrigem()).isEqualTo(DEFAULT_METODO_ORIGEM);
    }

    @Test
    @Transactional
    public void createLogseventosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = logseventosRepository.findAll().size();

        // Create the Logseventos with an existing ID
        logseventos.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLogseventosMockMvc.perform(post("/api/logseventos")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(logseventos)))
            .andExpect(status().isBadRequest());

        // Validate the Logseventos in the database
        List<Logseventos> logseventosList = logseventosRepository.findAll();
        assertThat(logseventosList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLogseventos() throws Exception {
        // Initialize the database
        logseventosRepository.saveAndFlush(logseventos);

        // Get all the logseventosList
        restLogseventosMockMvc.perform(get("/api/logseventos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(logseventos.getId().intValue())))
            .andExpect(jsonPath("$.[*].dataHoraInicio").value(hasItem(sameInstant(DEFAULT_DATA_HORA_INICIO))))
            .andExpect(jsonPath("$.[*].dataHoraFim").value(hasItem(sameInstant(DEFAULT_DATA_HORA_FIM))))
            .andExpect(jsonPath("$.[*].classeOrigem").value(hasItem(DEFAULT_CLASSE_ORIGEM)))
            .andExpect(jsonPath("$.[*].metodoOrigem").value(hasItem(DEFAULT_METODO_ORIGEM)));
    }
    
    @Test
    @Transactional
    public void getLogseventos() throws Exception {
        // Initialize the database
        logseventosRepository.saveAndFlush(logseventos);

        // Get the logseventos
        restLogseventosMockMvc.perform(get("/api/logseventos/{id}", logseventos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(logseventos.getId().intValue()))
            .andExpect(jsonPath("$.dataHoraInicio").value(sameInstant(DEFAULT_DATA_HORA_INICIO)))
            .andExpect(jsonPath("$.dataHoraFim").value(sameInstant(DEFAULT_DATA_HORA_FIM)))
            .andExpect(jsonPath("$.classeOrigem").value(DEFAULT_CLASSE_ORIGEM))
            .andExpect(jsonPath("$.metodoOrigem").value(DEFAULT_METODO_ORIGEM));
    }

    @Test
    @Transactional
    public void getNonExistingLogseventos() throws Exception {
        // Get the logseventos
        restLogseventosMockMvc.perform(get("/api/logseventos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLogseventos() throws Exception {
        // Initialize the database
        logseventosService.save(logseventos);

        int databaseSizeBeforeUpdate = logseventosRepository.findAll().size();

        // Update the logseventos
        Logseventos updatedLogseventos = logseventosRepository.findById(logseventos.getId()).get();
        // Disconnect from session so that the updates on updatedLogseventos are not directly saved in db
        em.detach(updatedLogseventos);
        updatedLogseventos
            .dataHoraInicio(UPDATED_DATA_HORA_INICIO)
            .dataHoraFim(UPDATED_DATA_HORA_FIM)
            .classeOrigem(UPDATED_CLASSE_ORIGEM)
            .metodoOrigem(UPDATED_METODO_ORIGEM);

        restLogseventosMockMvc.perform(put("/api/logseventos")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedLogseventos)))
            .andExpect(status().isOk());

        // Validate the Logseventos in the database
        List<Logseventos> logseventosList = logseventosRepository.findAll();
        assertThat(logseventosList).hasSize(databaseSizeBeforeUpdate);
        Logseventos testLogseventos = logseventosList.get(logseventosList.size() - 1);
        assertThat(testLogseventos.getDataHoraInicio()).isEqualTo(UPDATED_DATA_HORA_INICIO);
        assertThat(testLogseventos.getDataHoraFim()).isEqualTo(UPDATED_DATA_HORA_FIM);
        assertThat(testLogseventos.getClasseOrigem()).isEqualTo(UPDATED_CLASSE_ORIGEM);
        assertThat(testLogseventos.getMetodoOrigem()).isEqualTo(UPDATED_METODO_ORIGEM);
    }

    @Test
    @Transactional
    public void updateNonExistingLogseventos() throws Exception {
        int databaseSizeBeforeUpdate = logseventosRepository.findAll().size();

        // Create the Logseventos

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLogseventosMockMvc.perform(put("/api/logseventos")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(logseventos)))
            .andExpect(status().isBadRequest());

        // Validate the Logseventos in the database
        List<Logseventos> logseventosList = logseventosRepository.findAll();
        assertThat(logseventosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLogseventos() throws Exception {
        // Initialize the database
        logseventosService.save(logseventos);

        int databaseSizeBeforeDelete = logseventosRepository.findAll().size();

        // Delete the logseventos
        restLogseventosMockMvc.perform(delete("/api/logseventos/{id}", logseventos.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Logseventos> logseventosList = logseventosRepository.findAll();
        assertThat(logseventosList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
