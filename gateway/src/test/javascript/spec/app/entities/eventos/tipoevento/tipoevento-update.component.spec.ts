/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import TipoeventoUpdateComponent from '@/entities/eventos/tipoevento/tipoevento-update.vue';
import TipoeventoClass from '@/entities/eventos/tipoevento/tipoevento-update.component';
import TipoeventoService from '@/entities/eventos/tipoevento/tipoevento.service';

import EventoService from '@/entities/eventos/evento/evento.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('Tipoevento Management Update Component', () => {
    let wrapper: Wrapper<TipoeventoClass>;
    let comp: TipoeventoClass;
    let tipoeventoServiceStub: SinonStubbedInstance<TipoeventoService>;

    beforeEach(() => {
      tipoeventoServiceStub = sinon.createStubInstance<TipoeventoService>(TipoeventoService);

      wrapper = shallowMount<TipoeventoClass>(TipoeventoUpdateComponent, {
        store,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          tipoeventoService: () => tipoeventoServiceStub,

          eventoService: () => new EventoService()
        }
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.tipoevento = entity;
        tipoeventoServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(tipoeventoServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.tipoevento = entity;
        tipoeventoServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(tipoeventoServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
