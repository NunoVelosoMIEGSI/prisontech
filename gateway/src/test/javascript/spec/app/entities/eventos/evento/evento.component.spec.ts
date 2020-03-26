/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import EventoComponent from '@/entities/eventos/evento/evento.vue';
import EventoClass from '@/entities/eventos/evento/evento.component';
import EventoService from '@/entities/eventos/evento/evento.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-alert', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {}
  }
};

describe('Component Tests', () => {
  describe('Evento Management Component', () => {
    let wrapper: Wrapper<EventoClass>;
    let comp: EventoClass;
    let eventoServiceStub: SinonStubbedInstance<EventoService>;

    beforeEach(() => {
      eventoServiceStub = sinon.createStubInstance<EventoService>(EventoService);
      eventoServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<EventoClass>(EventoComponent, {
        store,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          eventoService: () => eventoServiceStub
        }
      });
      comp = wrapper.vm;
    });

    it('should be a Vue instance', () => {
      expect(wrapper.isVueInstance()).toBeTruthy();
    });

    it('Should call load all on init', async () => {
      // GIVEN
      eventoServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllEventos();
      await comp.$nextTick();

      // THEN
      expect(eventoServiceStub.retrieve.called).toBeTruthy();
      expect(comp.eventos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      eventoServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeEvento();
      await comp.$nextTick();

      // THEN
      expect(eventoServiceStub.delete.called).toBeTruthy();
      expect(eventoServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
