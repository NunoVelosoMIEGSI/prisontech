package com.dai.eventos.web.rest;

import com.dai.eventos.EventosApp;
import com.dai.eventos.domain.Camara;
import com.dai.eventos.repository.CamaraRepository;
import com.dai.eventos.service.CamaraService;
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
 * Integration tests for the {@link CamaraResource} REST controller.
 */
@SpringBootTest(classes = EventosApp.class)
public class CamaraResourceIT {

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    @Autowired
    private CamaraRepository camaraRepository;

    @Autowired
    private CamaraService camaraService;

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

    private MockMvc restCamaraMockMvc;

    private Camara camara;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CamaraResource camaraResource = new CamaraResource(camaraService);
        this.restCamaraMockMvc = MockMvcBuilders.standaloneSetup(camaraResource)
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
    public static Camara createEntity(EntityManager em) {
        Camara camara = new Camara()
            .descricao(DEFAULT_DESCRICAO);
        return camara;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Camara createUpdatedEntity(EntityManager em) {
        Camara camara = new Camara()
            .descricao(UPDATED_DESCRICAO);
        return camara;
    }

    @BeforeEach
    public void initTest() {
        camara = createEntity(em);
    }

    @Test
    @Transactional
    public void createCamara() throws Exception {
        int databaseSizeBeforeCreate = camaraRepository.findAll().size();

        // Create the Camara
        restCamaraMockMvc.perform(post("/api/camaras")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(camara)))
            .andExpect(status().isCreated());

        // Validate the Camara in the database
        List<Camara> camaraList = camaraRepository.findAll();
        assertThat(camaraList).hasSize(databaseSizeBeforeCreate + 1);
        Camara testCamara = camaraList.get(camaraList.size() - 1);
        assertThat(testCamara.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
    }

    @Test
    @Transactional
    public void createCamaraWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = camaraRepository.findAll().size();

        // Create the Camara with an existing ID
        camara.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCamaraMockMvc.perform(post("/api/camaras")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(camara)))
            .andExpect(status().isBadRequest());

        // Validate the Camara in the database
        List<Camara> camaraList = camaraRepository.findAll();
        assertThat(camaraList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCamaras() throws Exception {
        // Initialize the database
        camaraRepository.saveAndFlush(camara);

        // Get all the camaraList
        restCamaraMockMvc.perform(get("/api/camaras?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(camara.getId().intValue())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO)));
    }
    
    @Test
    @Transactional
    public void getCamara() throws Exception {
        // Initialize the database
        camaraRepository.saveAndFlush(camara);

        // Get the camara
        restCamaraMockMvc.perform(get("/api/camaras/{id}", camara.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(camara.getId().intValue()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO));
    }

    @Test
    @Transactional
    public void getNonExistingCamara() throws Exception {
        // Get the camara
        restCamaraMockMvc.perform(get("/api/camaras/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCamara() throws Exception {
        // Initialize the database
        camaraService.save(camara);

        int databaseSizeBeforeUpdate = camaraRepository.findAll().size();

        // Update the camara
        Camara updatedCamara = camaraRepository.findById(camara.getId()).get();
        // Disconnect from session so that the updates on updatedCamara are not directly saved in db
        em.detach(updatedCamara);
        updatedCamara
            .descricao(UPDATED_DESCRICAO);

        restCamaraMockMvc.perform(put("/api/camaras")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCamara)))
            .andExpect(status().isOk());

        // Validate the Camara in the database
        List<Camara> camaraList = camaraRepository.findAll();
        assertThat(camaraList).hasSize(databaseSizeBeforeUpdate);
        Camara testCamara = camaraList.get(camaraList.size() - 1);
        assertThat(testCamara.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
    }

    @Test
    @Transactional
    public void updateNonExistingCamara() throws Exception {
        int databaseSizeBeforeUpdate = camaraRepository.findAll().size();

        // Create the Camara

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCamaraMockMvc.perform(put("/api/camaras")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(camara)))
            .andExpect(status().isBadRequest());

        // Validate the Camara in the database
        List<Camara> camaraList = camaraRepository.findAll();
        assertThat(camaraList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCamara() throws Exception {
        // Initialize the database
        camaraService.save(camara);

        int databaseSizeBeforeDelete = camaraRepository.findAll().size();

        // Delete the camara
        restCamaraMockMvc.perform(delete("/api/camaras/{id}", camara.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Camara> camaraList = camaraRepository.findAll();
        assertThat(camaraList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
