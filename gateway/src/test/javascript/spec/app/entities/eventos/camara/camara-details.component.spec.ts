/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import CamaraDetailComponent from '@/entities/eventos/camara/camara-details.vue';
import CamaraClass from '@/entities/eventos/camara/camara-details.component';
import CamaraService from '@/entities/eventos/camara/camara.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Camara Management Detail Component', () => {
    let wrapper: Wrapper<CamaraClass>;
    let comp: CamaraClass;
    let camaraServiceStub: SinonStubbedInstance<CamaraService>;

    beforeEach(() => {
      camaraServiceStub = sinon.createStubInstance<CamaraService>(CamaraService);

      wrapper = shallowMount<CamaraClass>(CamaraDetailComponent, { store, localVue, provide: { camaraService: () => camaraServiceStub } });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundCamara = { id: 123 };
        camaraServiceStub.find.resolves(foundCamara);

        // WHEN
        comp.retrieveCamara(123);
        await comp.$nextTick();

        // THEN
        expect(comp.camara).toBe(foundCamara);
      });
    });
  });
});
