package com.bioplusteste.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.bioplusteste.IntegrationTest;
import com.bioplusteste.domain.Estabelecimento;
import com.bioplusteste.repository.EstabelecimentoRepository;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link EstabelecimentoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EstabelecimentoResourceIT {

    private static final Integer DEFAULT_ID_LOCAL = 1;
    private static final Integer UPDATED_ID_LOCAL = 2;

    private static final String DEFAULT_OBJECT_ID = "AAAAAAAAAA";
    private static final String UPDATED_OBJECT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_1 = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PLACE_ID = "AAAAAAAAAA";
    private static final String UPDATED_PLACE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TYPES = "AAAAAAAAAA";
    private static final String UPDATED_TYPES = "BBBBBBBBBB";

    private static final String DEFAULT_VALIDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_VALIDATED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_VICINITY = "AAAAAAAAAA";
    private static final String UPDATED_VICINITY = "BBBBBBBBBB";

    private static final String DEFAULT_VAGA = "AAAAAAAAAA";
    private static final String UPDATED_VAGA = "BBBBBBBBBB";

    private static final String DEFAULT_FOTO = "AAAAAAAAAA";
    private static final String UPDATED_FOTO = "BBBBBBBBBB";

    private static final Double DEFAULT_NOTA = 1D;
    private static final Double UPDATED_NOTA = 2D;

    private static final String DEFAULT_LAT_2_LNG_2 = "AAAAAAAAAA";
    private static final String UPDATED_LAT_2_LNG_2 = "BBBBBBBBBB";

    private static final String DEFAULT_HAS_CHAIR_WHEEL = "AAAAAAAAAA";
    private static final String UPDATED_HAS_CHAIR_WHEEL = "BBBBBBBBBB";

    private static final String DEFAULT_HAS_HANDICAPPED_HELP = "AAAAAAAAAA";
    private static final String UPDATED_HAS_HANDICAPPED_HELP = "BBBBBBBBBB";

    private static final String DEFAULT_HAS_INTERPRETER = "AAAAAAAAAA";
    private static final String UPDATED_HAS_INTERPRETER = "BBBBBBBBBB";

    private static final String DEFAULT_HAS_SPECIAL_FURNITURE = "AAAAAAAAAA";
    private static final String UPDATED_HAS_SPECIAL_FURNITURE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/estabelecimentos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEstabelecimentoMockMvc;

    private Estabelecimento estabelecimento;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Estabelecimento createEntity(EntityManager em) {
        Estabelecimento estabelecimento = new Estabelecimento()
            .idLocal(DEFAULT_ID_LOCAL)
            .objectId(DEFAULT_OBJECT_ID)
            .name(DEFAULT_NAME)
            .phone1(DEFAULT_PHONE_1)
            .placeId(DEFAULT_PLACE_ID)
            .types(DEFAULT_TYPES)
            .validatedBy(DEFAULT_VALIDATED_BY)
            .vicinity(DEFAULT_VICINITY)
            .vaga(DEFAULT_VAGA)
            .foto(DEFAULT_FOTO)
            .nota(DEFAULT_NOTA)
            .lat2lng2(DEFAULT_LAT_2_LNG_2)
            .hasChairWheel(DEFAULT_HAS_CHAIR_WHEEL)
            .hasHandicappedHelp(DEFAULT_HAS_HANDICAPPED_HELP)
            .hasInterpreter(DEFAULT_HAS_INTERPRETER)
            .hasSpecialFurniture(DEFAULT_HAS_SPECIAL_FURNITURE);
        return estabelecimento;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Estabelecimento createUpdatedEntity(EntityManager em) {
        Estabelecimento estabelecimento = new Estabelecimento()
            .idLocal(UPDATED_ID_LOCAL)
            .objectId(UPDATED_OBJECT_ID)
            .name(UPDATED_NAME)
            .phone1(UPDATED_PHONE_1)
            .placeId(UPDATED_PLACE_ID)
            .types(UPDATED_TYPES)
            .validatedBy(UPDATED_VALIDATED_BY)
            .vicinity(UPDATED_VICINITY)
            .vaga(UPDATED_VAGA)
            .foto(UPDATED_FOTO)
            .nota(UPDATED_NOTA)
            .lat2lng2(UPDATED_LAT_2_LNG_2)
            .hasChairWheel(UPDATED_HAS_CHAIR_WHEEL)
            .hasHandicappedHelp(UPDATED_HAS_HANDICAPPED_HELP)
            .hasInterpreter(UPDATED_HAS_INTERPRETER)
            .hasSpecialFurniture(UPDATED_HAS_SPECIAL_FURNITURE);
        return estabelecimento;
    }

    @BeforeEach
    public void initTest() {
        estabelecimento = createEntity(em);
    }

    @Test
    @Transactional
    void createEstabelecimento() throws Exception {
        int databaseSizeBeforeCreate = estabelecimentoRepository.findAll().size();
        // Create the Estabelecimento
        restEstabelecimentoMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(estabelecimento))
            )
            .andExpect(status().isCreated());

        // Validate the Estabelecimento in the database
        List<Estabelecimento> estabelecimentoList = estabelecimentoRepository.findAll();
        assertThat(estabelecimentoList).hasSize(databaseSizeBeforeCreate + 1);
        Estabelecimento testEstabelecimento = estabelecimentoList.get(estabelecimentoList.size() - 1);
        assertThat(testEstabelecimento.getIdLocal()).isEqualTo(DEFAULT_ID_LOCAL);
        assertThat(testEstabelecimento.getObjectId()).isEqualTo(DEFAULT_OBJECT_ID);
        assertThat(testEstabelecimento.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testEstabelecimento.getPhone1()).isEqualTo(DEFAULT_PHONE_1);
        assertThat(testEstabelecimento.getPlaceId()).isEqualTo(DEFAULT_PLACE_ID);
        assertThat(testEstabelecimento.getTypes()).isEqualTo(DEFAULT_TYPES);
        assertThat(testEstabelecimento.getValidatedBy()).isEqualTo(DEFAULT_VALIDATED_BY);
        assertThat(testEstabelecimento.getVicinity()).isEqualTo(DEFAULT_VICINITY);
        assertThat(testEstabelecimento.getVaga()).isEqualTo(DEFAULT_VAGA);
        assertThat(testEstabelecimento.getFoto()).isEqualTo(DEFAULT_FOTO);
        assertThat(testEstabelecimento.getNota()).isEqualTo(DEFAULT_NOTA);
        assertThat(testEstabelecimento.getLat2lng2()).isEqualTo(DEFAULT_LAT_2_LNG_2);
        assertThat(testEstabelecimento.getHasChairWheel()).isEqualTo(DEFAULT_HAS_CHAIR_WHEEL);
        assertThat(testEstabelecimento.getHasHandicappedHelp()).isEqualTo(DEFAULT_HAS_HANDICAPPED_HELP);
        assertThat(testEstabelecimento.getHasInterpreter()).isEqualTo(DEFAULT_HAS_INTERPRETER);
        assertThat(testEstabelecimento.getHasSpecialFurniture()).isEqualTo(DEFAULT_HAS_SPECIAL_FURNITURE);
    }

    @Test
    @Transactional
    void createEstabelecimentoWithExistingId() throws Exception {
        // Create the Estabelecimento with an existing ID
        estabelecimento.setId(1L);

        int databaseSizeBeforeCreate = estabelecimentoRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEstabelecimentoMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(estabelecimento))
            )
            .andExpect(status().isBadRequest());

        // Validate the Estabelecimento in the database
        List<Estabelecimento> estabelecimentoList = estabelecimentoRepository.findAll();
        assertThat(estabelecimentoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllEstabelecimentos() throws Exception {
        // Initialize the database
        estabelecimentoRepository.saveAndFlush(estabelecimento);

        // Get all the estabelecimentoList
        restEstabelecimentoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(estabelecimento.getId().intValue())))
            .andExpect(jsonPath("$.[*].idLocal").value(hasItem(DEFAULT_ID_LOCAL)))
            .andExpect(jsonPath("$.[*].objectId").value(hasItem(DEFAULT_OBJECT_ID)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].phone1").value(hasItem(DEFAULT_PHONE_1)))
            .andExpect(jsonPath("$.[*].placeId").value(hasItem(DEFAULT_PLACE_ID)))
            .andExpect(jsonPath("$.[*].types").value(hasItem(DEFAULT_TYPES)))
            .andExpect(jsonPath("$.[*].validatedBy").value(hasItem(DEFAULT_VALIDATED_BY)))
            .andExpect(jsonPath("$.[*].vicinity").value(hasItem(DEFAULT_VICINITY)))
            .andExpect(jsonPath("$.[*].vaga").value(hasItem(DEFAULT_VAGA)))
            .andExpect(jsonPath("$.[*].foto").value(hasItem(DEFAULT_FOTO)))
            .andExpect(jsonPath("$.[*].nota").value(hasItem(DEFAULT_NOTA.doubleValue())))
            .andExpect(jsonPath("$.[*].lat2lng2").value(hasItem(DEFAULT_LAT_2_LNG_2)))
            .andExpect(jsonPath("$.[*].hasChairWheel").value(hasItem(DEFAULT_HAS_CHAIR_WHEEL)))
            .andExpect(jsonPath("$.[*].hasHandicappedHelp").value(hasItem(DEFAULT_HAS_HANDICAPPED_HELP)))
            .andExpect(jsonPath("$.[*].hasInterpreter").value(hasItem(DEFAULT_HAS_INTERPRETER)))
            .andExpect(jsonPath("$.[*].hasSpecialFurniture").value(hasItem(DEFAULT_HAS_SPECIAL_FURNITURE)));
    }

    @Test
    @Transactional
    void getEstabelecimento() throws Exception {
        // Initialize the database
        estabelecimentoRepository.saveAndFlush(estabelecimento);

        // Get the estabelecimento
        restEstabelecimentoMockMvc
            .perform(get(ENTITY_API_URL_ID, estabelecimento.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(estabelecimento.getId().intValue()))
            .andExpect(jsonPath("$.idLocal").value(DEFAULT_ID_LOCAL))
            .andExpect(jsonPath("$.objectId").value(DEFAULT_OBJECT_ID))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.phone1").value(DEFAULT_PHONE_1))
            .andExpect(jsonPath("$.placeId").value(DEFAULT_PLACE_ID))
            .andExpect(jsonPath("$.types").value(DEFAULT_TYPES))
            .andExpect(jsonPath("$.validatedBy").value(DEFAULT_VALIDATED_BY))
            .andExpect(jsonPath("$.vicinity").value(DEFAULT_VICINITY))
            .andExpect(jsonPath("$.vaga").value(DEFAULT_VAGA))
            .andExpect(jsonPath("$.foto").value(DEFAULT_FOTO))
            .andExpect(jsonPath("$.nota").value(DEFAULT_NOTA.doubleValue()))
            .andExpect(jsonPath("$.lat2lng2").value(DEFAULT_LAT_2_LNG_2))
            .andExpect(jsonPath("$.hasChairWheel").value(DEFAULT_HAS_CHAIR_WHEEL))
            .andExpect(jsonPath("$.hasHandicappedHelp").value(DEFAULT_HAS_HANDICAPPED_HELP))
            .andExpect(jsonPath("$.hasInterpreter").value(DEFAULT_HAS_INTERPRETER))
            .andExpect(jsonPath("$.hasSpecialFurniture").value(DEFAULT_HAS_SPECIAL_FURNITURE));
    }

    @Test
    @Transactional
    void getNonExistingEstabelecimento() throws Exception {
        // Get the estabelecimento
        restEstabelecimentoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewEstabelecimento() throws Exception {
        // Initialize the database
        estabelecimentoRepository.saveAndFlush(estabelecimento);

        int databaseSizeBeforeUpdate = estabelecimentoRepository.findAll().size();

        // Update the estabelecimento
        Estabelecimento updatedEstabelecimento = estabelecimentoRepository.findById(estabelecimento.getId()).get();
        // Disconnect from session so that the updates on updatedEstabelecimento are not directly saved in db
        em.detach(updatedEstabelecimento);
        updatedEstabelecimento
            .idLocal(UPDATED_ID_LOCAL)
            .objectId(UPDATED_OBJECT_ID)
            .name(UPDATED_NAME)
            .phone1(UPDATED_PHONE_1)
            .placeId(UPDATED_PLACE_ID)
            .types(UPDATED_TYPES)
            .validatedBy(UPDATED_VALIDATED_BY)
            .vicinity(UPDATED_VICINITY)
            .vaga(UPDATED_VAGA)
            .foto(UPDATED_FOTO)
            .nota(UPDATED_NOTA)
            .lat2lng2(UPDATED_LAT_2_LNG_2)
            .hasChairWheel(UPDATED_HAS_CHAIR_WHEEL)
            .hasHandicappedHelp(UPDATED_HAS_HANDICAPPED_HELP)
            .hasInterpreter(UPDATED_HAS_INTERPRETER)
            .hasSpecialFurniture(UPDATED_HAS_SPECIAL_FURNITURE);

        restEstabelecimentoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedEstabelecimento.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedEstabelecimento))
            )
            .andExpect(status().isOk());

        // Validate the Estabelecimento in the database
        List<Estabelecimento> estabelecimentoList = estabelecimentoRepository.findAll();
        assertThat(estabelecimentoList).hasSize(databaseSizeBeforeUpdate);
        Estabelecimento testEstabelecimento = estabelecimentoList.get(estabelecimentoList.size() - 1);
        assertThat(testEstabelecimento.getIdLocal()).isEqualTo(UPDATED_ID_LOCAL);
        assertThat(testEstabelecimento.getObjectId()).isEqualTo(UPDATED_OBJECT_ID);
        assertThat(testEstabelecimento.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testEstabelecimento.getPhone1()).isEqualTo(UPDATED_PHONE_1);
        assertThat(testEstabelecimento.getPlaceId()).isEqualTo(UPDATED_PLACE_ID);
        assertThat(testEstabelecimento.getTypes()).isEqualTo(UPDATED_TYPES);
        assertThat(testEstabelecimento.getValidatedBy()).isEqualTo(UPDATED_VALIDATED_BY);
        assertThat(testEstabelecimento.getVicinity()).isEqualTo(UPDATED_VICINITY);
        assertThat(testEstabelecimento.getVaga()).isEqualTo(UPDATED_VAGA);
        assertThat(testEstabelecimento.getFoto()).isEqualTo(UPDATED_FOTO);
        assertThat(testEstabelecimento.getNota()).isEqualTo(UPDATED_NOTA);
        assertThat(testEstabelecimento.getLat2lng2()).isEqualTo(UPDATED_LAT_2_LNG_2);
        assertThat(testEstabelecimento.getHasChairWheel()).isEqualTo(UPDATED_HAS_CHAIR_WHEEL);
        assertThat(testEstabelecimento.getHasHandicappedHelp()).isEqualTo(UPDATED_HAS_HANDICAPPED_HELP);
        assertThat(testEstabelecimento.getHasInterpreter()).isEqualTo(UPDATED_HAS_INTERPRETER);
        assertThat(testEstabelecimento.getHasSpecialFurniture()).isEqualTo(UPDATED_HAS_SPECIAL_FURNITURE);
    }

    @Test
    @Transactional
    void putNonExistingEstabelecimento() throws Exception {
        int databaseSizeBeforeUpdate = estabelecimentoRepository.findAll().size();
        estabelecimento.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEstabelecimentoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, estabelecimento.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(estabelecimento))
            )
            .andExpect(status().isBadRequest());

        // Validate the Estabelecimento in the database
        List<Estabelecimento> estabelecimentoList = estabelecimentoRepository.findAll();
        assertThat(estabelecimentoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEstabelecimento() throws Exception {
        int databaseSizeBeforeUpdate = estabelecimentoRepository.findAll().size();
        estabelecimento.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEstabelecimentoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(estabelecimento))
            )
            .andExpect(status().isBadRequest());

        // Validate the Estabelecimento in the database
        List<Estabelecimento> estabelecimentoList = estabelecimentoRepository.findAll();
        assertThat(estabelecimentoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEstabelecimento() throws Exception {
        int databaseSizeBeforeUpdate = estabelecimentoRepository.findAll().size();
        estabelecimento.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEstabelecimentoMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(estabelecimento))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Estabelecimento in the database
        List<Estabelecimento> estabelecimentoList = estabelecimentoRepository.findAll();
        assertThat(estabelecimentoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEstabelecimentoWithPatch() throws Exception {
        // Initialize the database
        estabelecimentoRepository.saveAndFlush(estabelecimento);

        int databaseSizeBeforeUpdate = estabelecimentoRepository.findAll().size();

        // Update the estabelecimento using partial update
        Estabelecimento partialUpdatedEstabelecimento = new Estabelecimento();
        partialUpdatedEstabelecimento.setId(estabelecimento.getId());

        partialUpdatedEstabelecimento
            .objectId(UPDATED_OBJECT_ID)
            .phone1(UPDATED_PHONE_1)
            .placeId(UPDATED_PLACE_ID)
            .types(UPDATED_TYPES)
            .vicinity(UPDATED_VICINITY)
            .vaga(UPDATED_VAGA)
            .foto(UPDATED_FOTO)
            .lat2lng2(UPDATED_LAT_2_LNG_2)
            .hasInterpreter(UPDATED_HAS_INTERPRETER)
            .hasSpecialFurniture(UPDATED_HAS_SPECIAL_FURNITURE);

        restEstabelecimentoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEstabelecimento.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEstabelecimento))
            )
            .andExpect(status().isOk());

        // Validate the Estabelecimento in the database
        List<Estabelecimento> estabelecimentoList = estabelecimentoRepository.findAll();
        assertThat(estabelecimentoList).hasSize(databaseSizeBeforeUpdate);
        Estabelecimento testEstabelecimento = estabelecimentoList.get(estabelecimentoList.size() - 1);
        assertThat(testEstabelecimento.getIdLocal()).isEqualTo(DEFAULT_ID_LOCAL);
        assertThat(testEstabelecimento.getObjectId()).isEqualTo(UPDATED_OBJECT_ID);
        assertThat(testEstabelecimento.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testEstabelecimento.getPhone1()).isEqualTo(UPDATED_PHONE_1);
        assertThat(testEstabelecimento.getPlaceId()).isEqualTo(UPDATED_PLACE_ID);
        assertThat(testEstabelecimento.getTypes()).isEqualTo(UPDATED_TYPES);
        assertThat(testEstabelecimento.getValidatedBy()).isEqualTo(DEFAULT_VALIDATED_BY);
        assertThat(testEstabelecimento.getVicinity()).isEqualTo(UPDATED_VICINITY);
        assertThat(testEstabelecimento.getVaga()).isEqualTo(UPDATED_VAGA);
        assertThat(testEstabelecimento.getFoto()).isEqualTo(UPDATED_FOTO);
        assertThat(testEstabelecimento.getNota()).isEqualTo(DEFAULT_NOTA);
        assertThat(testEstabelecimento.getLat2lng2()).isEqualTo(UPDATED_LAT_2_LNG_2);
        assertThat(testEstabelecimento.getHasChairWheel()).isEqualTo(DEFAULT_HAS_CHAIR_WHEEL);
        assertThat(testEstabelecimento.getHasHandicappedHelp()).isEqualTo(DEFAULT_HAS_HANDICAPPED_HELP);
        assertThat(testEstabelecimento.getHasInterpreter()).isEqualTo(UPDATED_HAS_INTERPRETER);
        assertThat(testEstabelecimento.getHasSpecialFurniture()).isEqualTo(UPDATED_HAS_SPECIAL_FURNITURE);
    }

    @Test
    @Transactional
    void fullUpdateEstabelecimentoWithPatch() throws Exception {
        // Initialize the database
        estabelecimentoRepository.saveAndFlush(estabelecimento);

        int databaseSizeBeforeUpdate = estabelecimentoRepository.findAll().size();

        // Update the estabelecimento using partial update
        Estabelecimento partialUpdatedEstabelecimento = new Estabelecimento();
        partialUpdatedEstabelecimento.setId(estabelecimento.getId());

        partialUpdatedEstabelecimento
            .idLocal(UPDATED_ID_LOCAL)
            .objectId(UPDATED_OBJECT_ID)
            .name(UPDATED_NAME)
            .phone1(UPDATED_PHONE_1)
            .placeId(UPDATED_PLACE_ID)
            .types(UPDATED_TYPES)
            .validatedBy(UPDATED_VALIDATED_BY)
            .vicinity(UPDATED_VICINITY)
            .vaga(UPDATED_VAGA)
            .foto(UPDATED_FOTO)
            .nota(UPDATED_NOTA)
            .lat2lng2(UPDATED_LAT_2_LNG_2)
            .hasChairWheel(UPDATED_HAS_CHAIR_WHEEL)
            .hasHandicappedHelp(UPDATED_HAS_HANDICAPPED_HELP)
            .hasInterpreter(UPDATED_HAS_INTERPRETER)
            .hasSpecialFurniture(UPDATED_HAS_SPECIAL_FURNITURE);

        restEstabelecimentoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEstabelecimento.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEstabelecimento))
            )
            .andExpect(status().isOk());

        // Validate the Estabelecimento in the database
        List<Estabelecimento> estabelecimentoList = estabelecimentoRepository.findAll();
        assertThat(estabelecimentoList).hasSize(databaseSizeBeforeUpdate);
        Estabelecimento testEstabelecimento = estabelecimentoList.get(estabelecimentoList.size() - 1);
        assertThat(testEstabelecimento.getIdLocal()).isEqualTo(UPDATED_ID_LOCAL);
        assertThat(testEstabelecimento.getObjectId()).isEqualTo(UPDATED_OBJECT_ID);
        assertThat(testEstabelecimento.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testEstabelecimento.getPhone1()).isEqualTo(UPDATED_PHONE_1);
        assertThat(testEstabelecimento.getPlaceId()).isEqualTo(UPDATED_PLACE_ID);
        assertThat(testEstabelecimento.getTypes()).isEqualTo(UPDATED_TYPES);
        assertThat(testEstabelecimento.getValidatedBy()).isEqualTo(UPDATED_VALIDATED_BY);
        assertThat(testEstabelecimento.getVicinity()).isEqualTo(UPDATED_VICINITY);
        assertThat(testEstabelecimento.getVaga()).isEqualTo(UPDATED_VAGA);
        assertThat(testEstabelecimento.getFoto()).isEqualTo(UPDATED_FOTO);
        assertThat(testEstabelecimento.getNota()).isEqualTo(UPDATED_NOTA);
        assertThat(testEstabelecimento.getLat2lng2()).isEqualTo(UPDATED_LAT_2_LNG_2);
        assertThat(testEstabelecimento.getHasChairWheel()).isEqualTo(UPDATED_HAS_CHAIR_WHEEL);
        assertThat(testEstabelecimento.getHasHandicappedHelp()).isEqualTo(UPDATED_HAS_HANDICAPPED_HELP);
        assertThat(testEstabelecimento.getHasInterpreter()).isEqualTo(UPDATED_HAS_INTERPRETER);
        assertThat(testEstabelecimento.getHasSpecialFurniture()).isEqualTo(UPDATED_HAS_SPECIAL_FURNITURE);
    }

    @Test
    @Transactional
    void patchNonExistingEstabelecimento() throws Exception {
        int databaseSizeBeforeUpdate = estabelecimentoRepository.findAll().size();
        estabelecimento.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEstabelecimentoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, estabelecimento.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(estabelecimento))
            )
            .andExpect(status().isBadRequest());

        // Validate the Estabelecimento in the database
        List<Estabelecimento> estabelecimentoList = estabelecimentoRepository.findAll();
        assertThat(estabelecimentoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEstabelecimento() throws Exception {
        int databaseSizeBeforeUpdate = estabelecimentoRepository.findAll().size();
        estabelecimento.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEstabelecimentoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(estabelecimento))
            )
            .andExpect(status().isBadRequest());

        // Validate the Estabelecimento in the database
        List<Estabelecimento> estabelecimentoList = estabelecimentoRepository.findAll();
        assertThat(estabelecimentoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEstabelecimento() throws Exception {
        int databaseSizeBeforeUpdate = estabelecimentoRepository.findAll().size();
        estabelecimento.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEstabelecimentoMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(estabelecimento))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Estabelecimento in the database
        List<Estabelecimento> estabelecimentoList = estabelecimentoRepository.findAll();
        assertThat(estabelecimentoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEstabelecimento() throws Exception {
        // Initialize the database
        estabelecimentoRepository.saveAndFlush(estabelecimento);

        int databaseSizeBeforeDelete = estabelecimentoRepository.findAll().size();

        // Delete the estabelecimento
        restEstabelecimentoMockMvc
            .perform(delete(ENTITY_API_URL_ID, estabelecimento.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Estabelecimento> estabelecimentoList = estabelecimentoRepository.findAll();
        assertThat(estabelecimentoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
