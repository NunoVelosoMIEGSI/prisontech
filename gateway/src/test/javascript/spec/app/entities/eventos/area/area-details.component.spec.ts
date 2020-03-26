/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import AreaDetailComponent from '@/entities/eventos/area/area-details.vue';
import AreaClass from '@/entities/eventos/area/area-details.component';
import AreaService from '@/entities/eventos/area/area.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Area Management Detail Component', () => {
    let wrapper: Wrapper<AreaClass>;
    let comp: AreaClass;
    let areaServiceStub: SinonStubbedInstance<AreaService>;

    beforeEach(() => {
      areaServiceStub = sinon.createStubInstance<AreaService>(AreaService);

      wrapper = shallowMount<AreaClass>(AreaDetailComponent, { store, localVue, provide: { areaService: () => areaServiceStub } });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundArea = { id: 123 };
        areaServiceStub.find.resolves(foundArea);

        // WHEN
        comp.retrieveArea(123);
        await comp.$nextTick();

        // THEN
        expect(comp.area).toBe(foundArea);
      });
    });
  });
});
