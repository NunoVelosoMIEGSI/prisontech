import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import EventoService from '../evento/evento.service';
import { IEvento } from '@/shared/model/eventos/evento.model';

import CamaraService from '../camara/camara.service';
import { ICamara } from '@/shared/model/eventos/camara.model';

import AlertService from '@/shared/alert/alert.service';
import { IArea, Area } from '@/shared/model/eventos/area.model';
import AreaService from './area.service';

const validations: any = {
  area: {
    nome: {
      required
    },
    numMinPessoa: {
      required,
      numeric
    },
    numMaxPessoa: {
      required,
      numeric
    },
    limiteArea: {
      required,
      numeric
    }
  }
};

@Component({
  validations
})
export default class AreaUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('areaService') private areaService: () => AreaService;
  public area: IArea = new Area();

  @Inject('eventoService') private eventoService: () => EventoService;

  public eventos: IEvento[] = [];

  @Inject('camaraService') private camaraService: () => CamaraService;

  public camaras: ICamara[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.areaId) {
        vm.retrieveArea(to.params.areaId);
      }
      vm.initRelationships();
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.area.id) {
      this.areaService()
        .update(this.area)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('gatewayApp.eventosArea.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.areaService()
        .create(this.area)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('gatewayApp.eventosArea.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveArea(areaId): void {
    this.areaService()
      .find(areaId)
      .then(res => {
        this.area = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.eventoService()
      .retrieve()
      .then(res => {
        this.eventos = res.data;
      });
    this.camaraService()
      .retrieve()
      .then(res => {
        this.camaras = res.data;
      });
  }
}
