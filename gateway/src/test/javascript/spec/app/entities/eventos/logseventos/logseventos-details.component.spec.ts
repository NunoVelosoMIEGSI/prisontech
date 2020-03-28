/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import LogseventosDetailComponent from '@/entities/eventos/logseventos/logseventos-details.vue';
import LogseventosClass from '@/entities/eventos/logseventos/logseventos-details.component';
import LogseventosService from '@/entities/eventos/logseventos/logseventos.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Logseventos Management Detail Component', () => {
    let wrapper: Wrapper<LogseventosClass>;
    let comp: LogseventosClass;
    let logseventosServiceStub: SinonStubbedInstance<LogseventosService>;

    beforeEach(() => {
      logseventosServiceStub = sinon.createStubInstance<LogseventosService>(LogseventosService);

      wrapper = shallowMount<LogseventosClass>(LogseventosDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { logseventosService: () => logseventosServiceStub }
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundLogseventos = { id: 123 };
        logseventosServiceStub.find.resolves(foundLogseventos);

        // WHEN
        comp.retrieveLogseventos(123);
        await comp.$nextTick();

        // THEN
        expect(comp.logseventos).toBe(foundLogseventos);
      });
    });
  });
});
