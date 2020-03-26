/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import AreaComponent from '@/entities/eventos/area/area.vue';
import AreaClass from '@/entities/eventos/area/area.component';
import AreaService from '@/entities/eventos/area/area.service';

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
  describe('Area Management Component', () => {
    let wrapper: Wrapper<AreaClass>;
    let comp: AreaClass;
    let areaServiceStub: SinonStubbedInstance<AreaService>;

    beforeEach(() => {
      areaServiceStub = sinon.createStubInstance<AreaService>(AreaService);
      areaServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<AreaClass>(AreaComponent, {
        store,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          areaService: () => areaServiceStub
        }
      });
      comp = wrapper.vm;
    });

    it('should be a Vue instance', () => {
      expect(wrapper.isVueInstance()).toBeTruthy();
    });

    it('Should call load all on init', async () => {
      // GIVEN
      areaServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllAreas();
      await comp.$nextTick();

      // THEN
      expect(areaServiceStub.retrieve.called).toBeTruthy();
      expect(comp.areas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      areaServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeArea();
      await comp.$nextTick();

      // THEN
      expect(areaServiceStub.delete.called).toBeTruthy();
      expect(areaServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
