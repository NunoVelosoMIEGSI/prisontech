import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import EventoService from '../evento/evento.service';
import { IEvento } from '@/shared/model/eventos/evento.model';

import AlertService from '@/shared/alert/alert.service';
import { ICamara, Camara } from '@/shared/model/eventos/camara.model';
import CamaraService from './camara.service';

const validations: any = {
  camara: {
    descricao: {}
  }
};

@Component({
  validations
})
export default class CamaraUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('camaraService') private camaraService: () => CamaraService;
  public camara: ICamara = new Camara();

  @Inject('eventoService') private eventoService: () => EventoService;

  public eventos: IEvento[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.camaraId) {
        vm.retrieveCamara(to.params.camaraId);
      }
      vm.initRelationships();
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.camara.id) {
      this.camaraService()
        .update(this.camara)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('gatewayApp.eventosCamara.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.camaraService()
        .create(this.camara)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('gatewayApp.eventosCamara.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveCamara(camaraId): void {
    this.camaraService()
      .find(camaraId)
      .then(res => {
        this.camara = res;
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
  }
}
