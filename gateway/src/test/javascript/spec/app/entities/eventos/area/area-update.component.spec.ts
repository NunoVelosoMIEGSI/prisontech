/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import AreaUpdateComponent from '@/entities/eventos/area/area-update.vue';
import AreaClass from '@/entities/eventos/area/area-update.component';
import AreaService from '@/entities/eventos/area/area.service';

import EventoService from '@/entities/eventos/evento/evento.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('Area Management Update Component', () => {
    let wrapper: Wrapper<AreaClass>;
    let comp: AreaClass;
    let areaServiceStub: SinonStubbedInstance<AreaService>;

    beforeEach(() => {
      areaServiceStub = sinon.createStubInstance<AreaService>(AreaService);

      wrapper = shallowMount<AreaClass>(AreaUpdateComponent, {
        store,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          areaService: () => areaServiceStub,

          eventoService: () => new EventoService()
        }
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.area = entity;
        areaServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(areaServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.area = entity;
        areaServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(areaServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
