package com.dai.eventos.web.rest;

import com.dai.eventos.EventosApp;
import com.dai.eventos.domain.Evento;
import com.dai.eventos.repository.EventoRepository;
import com.dai.eventos.service.EventoService;
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
 * Integration tests for the {@link EventoResource} REST controller.
 */
@SpringBootTest(classes = EventosApp.class)
public class EventoResourceIT {

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUM_PESSOAS_PERM = 1;
    private static final Integer UPDATED_NUM_PESSOAS_PERM = 2;

    private static final Integer DEFAULT_NUM_PESSOAS_DET = 1;
    private static final Integer UPDATED_NUM_PESSOAS_DET = 2;

    private static final ZonedDateTime DEFAULT_DATA_HORA_INICIO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA_HORA_INICIO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATA_HORA_FIM = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA_HORA_FIM = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_PATH = "AAAAAAAAAA";
    private static final String UPDATED_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_FORMATO = "AAAAAAAAAA";
    private static final String UPDATED_FORMATO = "BBBBBBBBBB";

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private EventoService eventoService;

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

    private MockMvc restEventoMockMvc;

    private Evento evento;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EventoResource eventoResource = new EventoResource(eventoService);
        this.restEventoMockMvc = MockMvcBuilders.standaloneSetup(eventoResource)
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
    public static Evento createEntity(EntityManager em) {
        Evento evento = new Evento()
            .descricao(DEFAULT_DESCRICAO)
            .numPessoasPerm(DEFAULT_NUM_PESSOAS_PERM)
            .numPessoasDet(DEFAULT_NUM_PESSOAS_DET)
            .dataHoraInicio(DEFAULT_DATA_HORA_INICIO)
            .dataHoraFim(DEFAULT_DATA_HORA_FIM)
            .path(DEFAULT_PATH)
            .formato(DEFAULT_FORMATO);
        return evento;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Evento createUpdatedEntity(EntityManager em) {
        Evento evento = new Evento()
            .descricao(UPDATED_DESCRICAO)
            .numPessoasPerm(UPDATED_NUM_PESSOAS_PERM)
            .numPessoasDet(UPDATED_NUM_PESSOAS_DET)
            .dataHoraInicio(UPDATED_DATA_HORA_INICIO)
            .dataHoraFim(UPDATED_DATA_HORA_FIM)
            .path(UPDATED_PATH)
            .formato(UPDATED_FORMATO);
        return evento;
    }

    @BeforeEach
    public void initTest() {
        evento = createEntity(em);
    }

    @Test
    @Transactional
    public void createEvento() throws Exception {
        int databaseSizeBeforeCreate = eventoRepository.findAll().size();

        // Create the Evento
        restEventoMockMvc.perform(post("/api/eventos")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(evento)))
            .andExpect(status().isCreated());

        // Validate the Evento in the database
        List<Evento> eventoList = eventoRepository.findAll();
        assertThat(eventoList).hasSize(databaseSizeBeforeCreate + 1);
        Evento testEvento = eventoList.get(eventoList.size() - 1);
        assertThat(testEvento.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testEvento.getNumPessoasPerm()).isEqualTo(DEFAULT_NUM_PESSOAS_PERM);
        assertThat(testEvento.getNumPessoasDet()).isEqualTo(DEFAULT_NUM_PESSOAS_DET);
        assertThat(testEvento.getDataHoraInicio()).isEqualTo(DEFAULT_DATA_HORA_INICIO);
        assertThat(testEvento.getDataHoraFim()).isEqualTo(DEFAULT_DATA_HORA_FIM);
        assertThat(testEvento.getPath()).isEqualTo(DEFAULT_PATH);
        assertThat(testEvento.getFormato()).isEqualTo(DEFAULT_FORMATO);
    }

    @Test
    @Transactional
    public void createEventoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = eventoRepository.findAll().size();

        // Create the Evento with an existing ID
        evento.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEventoMockMvc.perform(post("/api/eventos")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(evento)))
            .andExpect(status().isBadRequest());

        // Validate the Evento in the database
        List<Evento> eventoList = eventoRepository.findAll();
        assertThat(eventoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEventos() throws Exception {
        // Initialize the database
        eventoRepository.saveAndFlush(evento);

        // Get all the eventoList
        restEventoMockMvc.perform(get("/api/eventos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(evento.getId().intValue())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO)))
            .andExpect(jsonPath("$.[*].numPessoasPerm").value(hasItem(DEFAULT_NUM_PESSOAS_PERM)))
            .andExpect(jsonPath("$.[*].numPessoasDet").value(hasItem(DEFAULT_NUM_PESSOAS_DET)))
            .andExpect(jsonPath("$.[*].dataHoraInicio").value(hasItem(sameInstant(DEFAULT_DATA_HORA_INICIO))))
            .andExpect(jsonPath("$.[*].dataHoraFim").value(hasItem(sameInstant(DEFAULT_DATA_HORA_FIM))))
            .andExpect(jsonPath("$.[*].path").value(hasItem(DEFAULT_PATH)))
            .andExpect(jsonPath("$.[*].formato").value(hasItem(DEFAULT_FORMATO)));
    }
    
    @Test
    @Transactional
    public void getEvento() throws Exception {
        // Initialize the database
        eventoRepository.saveAndFlush(evento);

        // Get the evento
        restEventoMockMvc.perform(get("/api/eventos/{id}", evento.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(evento.getId().intValue()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO))
            .andExpect(jsonPath("$.numPessoasPerm").value(DEFAULT_NUM_PESSOAS_PERM))
            .andExpect(jsonPath("$.numPessoasDet").value(DEFAULT_NUM_PESSOAS_DET))
            .andExpect(jsonPath("$.dataHoraInicio").value(sameInstant(DEFAULT_DATA_HORA_INICIO)))
            .andExpect(jsonPath("$.dataHoraFim").value(sameInstant(DEFAULT_DATA_HORA_FIM)))
            .andExpect(jsonPath("$.path").value(DEFAULT_PATH))
            .andExpect(jsonPath("$.formato").value(DEFAULT_FORMATO));
    }

    @Test
    @Transactional
    public void getNonExistingEvento() throws Exception {
        // Get the evento
        restEventoMockMvc.perform(get("/api/eventos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEvento() throws Exception {
        // Initialize the database
        eventoService.save(evento);

        int databaseSizeBeforeUpdate = eventoRepository.findAll().size();

        // Update the evento
        Evento updatedEvento = eventoRepository.findById(evento.getId()).get();
        // Disconnect from session so that the updates on updatedEvento are not directly saved in db
        em.detach(updatedEvento);
        updatedEvento
            .descricao(UPDATED_DESCRICAO)
            .numPessoasPerm(UPDATED_NUM_PESSOAS_PERM)
            .numPessoasDet(UPDATED_NUM_PESSOAS_DET)
            .dataHoraInicio(UPDATED_DATA_HORA_INICIO)
            .dataHoraFim(UPDATED_DATA_HORA_FIM)
            .path(UPDATED_PATH)
            .formato(UPDATED_FORMATO);

        restEventoMockMvc.perform(put("/api/eventos")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedEvento)))
            .andExpect(status().isOk());

        // Validate the Evento in the database
        List<Evento> eventoList = eventoRepository.findAll();
        assertThat(eventoList).hasSize(databaseSizeBeforeUpdate);
        Evento testEvento = eventoList.get(eventoList.size() - 1);
        assertThat(testEvento.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testEvento.getNumPessoasPerm()).isEqualTo(UPDATED_NUM_PESSOAS_PERM);
        assertThat(testEvento.getNumPessoasDet()).isEqualTo(UPDATED_NUM_PESSOAS_DET);
        assertThat(testEvento.getDataHoraInicio()).isEqualTo(UPDATED_DATA_HORA_INICIO);
        assertThat(testEvento.getDataHoraFim()).isEqualTo(UPDATED_DATA_HORA_FIM);
        assertThat(testEvento.getPath()).isEqualTo(UPDATED_PATH);
        assertThat(testEvento.getFormato()).isEqualTo(UPDATED_FORMATO);
    }

    @Test
    @Transactional
    public void updateNonExistingEvento() throws Exception {
        int databaseSizeBeforeUpdate = eventoRepository.findAll().size();

        // Create the Evento

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEventoMockMvc.perform(put("/api/eventos")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(evento)))
            .andExpect(status().isBadRequest());

        // Validate the Evento in the database
        List<Evento> eventoList = eventoRepository.findAll();
        assertThat(eventoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEvento() throws Exception {
        // Initialize the database
        eventoService.save(evento);

        int databaseSizeBeforeDelete = eventoRepository.findAll().size();

        // Delete the evento
        restEventoMockMvc.perform(delete("/api/eventos/{id}", evento.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Evento> eventoList = eventoRepository.findAll();
        assertThat(eventoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
