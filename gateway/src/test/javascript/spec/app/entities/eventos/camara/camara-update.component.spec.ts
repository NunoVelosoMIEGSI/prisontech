/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import CamaraUpdateComponent from '@/entities/eventos/camara/camara-update.vue';
import CamaraClass from '@/entities/eventos/camara/camara-update.component';
import CamaraService from '@/entities/eventos/camara/camara.service';

import AreaService from '@/entities/eventos/area/area.service';

import EventoService from '@/entities/eventos/evento/evento.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('Camara Management Update Component', () => {
    let wrapper: Wrapper<CamaraClass>;
    let comp: CamaraClass;
    let camaraServiceStub: SinonStubbedInstance<CamaraService>;

    beforeEach(() => {
      camaraServiceStub = sinon.createStubInstance<CamaraService>(CamaraService);

      wrapper = shallowMount<CamaraClass>(CamaraUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          camaraService: () => camaraServiceStub,

          areaService: () => new AreaService(),

          eventoService: () => new EventoService()
        }
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.camara = entity;
        camaraServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(camaraServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.camara = entity;
        camaraServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(camaraServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
