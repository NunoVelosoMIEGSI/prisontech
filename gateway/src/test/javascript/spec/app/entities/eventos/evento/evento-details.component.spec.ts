/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import EventoDetailComponent from '@/entities/eventos/evento/evento-details.vue';
import EventoClass from '@/entities/eventos/evento/evento-details.component';
import EventoService from '@/entities/eventos/evento/evento.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Evento Management Detail Component', () => {
    let wrapper: Wrapper<EventoClass>;
    let comp: EventoClass;
    let eventoServiceStub: SinonStubbedInstance<EventoService>;

    beforeEach(() => {
      eventoServiceStub = sinon.createStubInstance<EventoService>(EventoService);

      wrapper = shallowMount<EventoClass>(EventoDetailComponent, { store, localVue, provide: { eventoService: () => eventoServiceStub } });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundEvento = { id: 123 };
        eventoServiceStub.find.resolves(foundEvento);

        // WHEN
        comp.retrieveEvento(123);
        await comp.$nextTick();

        // THEN
        expect(comp.evento).toBe(foundEvento);
      });
    });
  });
});
