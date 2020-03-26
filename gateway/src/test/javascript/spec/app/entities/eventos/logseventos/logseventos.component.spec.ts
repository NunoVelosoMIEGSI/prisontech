/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import LogseventosComponent from '@/entities/eventos/logseventos/logseventos.vue';
import LogseventosClass from '@/entities/eventos/logseventos/logseventos.component';
import LogseventosService from '@/entities/eventos/logseventos/logseventos.service';

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
  describe('Logseventos Management Component', () => {
    let wrapper: Wrapper<LogseventosClass>;
    let comp: LogseventosClass;
    let logseventosServiceStub: SinonStubbedInstance<LogseventosService>;

    beforeEach(() => {
      logseventosServiceStub = sinon.createStubInstance<LogseventosService>(LogseventosService);
      logseventosServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<LogseventosClass>(LogseventosComponent, {
        store,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          logseventosService: () => logseventosServiceStub
        }
      });
      comp = wrapper.vm;
    });

    it('should be a Vue instance', () => {
      expect(wrapper.isVueInstance()).toBeTruthy();
    });

    it('Should call load all on init', async () => {
      // GIVEN
      logseventosServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllLogseventoss();
      await comp.$nextTick();

      // THEN
      expect(logseventosServiceStub.retrieve.called).toBeTruthy();
      expect(comp.logseventos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      logseventosServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeLogseventos();
      await comp.$nextTick();

      // THEN
      expect(logseventosServiceStub.delete.called).toBeTruthy();
      expect(logseventosServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
