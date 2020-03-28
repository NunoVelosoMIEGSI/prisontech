/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import TipoeventoDetailComponent from '@/entities/eventos/tipoevento/tipoevento-details.vue';
import TipoeventoClass from '@/entities/eventos/tipoevento/tipoevento-details.component';
import TipoeventoService from '@/entities/eventos/tipoevento/tipoevento.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Tipoevento Management Detail Component', () => {
    let wrapper: Wrapper<TipoeventoClass>;
    let comp: TipoeventoClass;
    let tipoeventoServiceStub: SinonStubbedInstance<TipoeventoService>;

    beforeEach(() => {
      tipoeventoServiceStub = sinon.createStubInstance<TipoeventoService>(TipoeventoService);

      wrapper = shallowMount<TipoeventoClass>(TipoeventoDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { tipoeventoService: () => tipoeventoServiceStub }
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundTipoevento = { id: 123 };
        tipoeventoServiceStub.find.resolves(foundTipoevento);

        // WHEN
        comp.retrieveTipoevento(123);
        await comp.$nextTick();

        // THEN
        expect(comp.tipoevento).toBe(foundTipoevento);
      });
    });
  });
});
