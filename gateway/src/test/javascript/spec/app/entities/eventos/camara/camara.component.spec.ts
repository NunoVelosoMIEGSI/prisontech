/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import CamaraComponent from '@/entities/eventos/camara/camara.vue';
import CamaraClass from '@/entities/eventos/camara/camara.component';
import CamaraService from '@/entities/eventos/camara/camara.service';

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
  describe('Camara Management Component', () => {
    let wrapper: Wrapper<CamaraClass>;
    let comp: CamaraClass;
    let camaraServiceStub: SinonStubbedInstance<CamaraService>;

    beforeEach(() => {
      camaraServiceStub = sinon.createStubInstance<CamaraService>(CamaraService);
      camaraServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<CamaraClass>(CamaraComponent, {
        store,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          camaraService: () => camaraServiceStub
        }
      });
      comp = wrapper.vm;
    });

    it('should be a Vue instance', () => {
      expect(wrapper.isVueInstance()).toBeTruthy();
    });

    it('Should call load all on init', async () => {
      // GIVEN
      camaraServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllCamaras();
      await comp.$nextTick();

      // THEN
      expect(camaraServiceStub.retrieve.called).toBeTruthy();
      expect(comp.camaras[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      camaraServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeCamara();
      await comp.$nextTick();

      // THEN
      expect(camaraServiceStub.delete.called).toBeTruthy();
      expect(camaraServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
