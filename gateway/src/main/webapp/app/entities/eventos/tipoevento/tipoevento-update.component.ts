import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import EventoService from '../evento/evento.service';
import { IEvento } from '@/shared/model/eventos/evento.model';

import AlertService from '@/shared/alert/alert.service';
import { ITipoevento, Tipoevento } from '@/shared/model/eventos/tipoevento.model';
import TipoeventoService from './tipoevento.service';

const validations: any = {
  tipoevento: {
    descricao: {}
  }
};

@Component({
  validations
})
export default class TipoeventoUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('tipoeventoService') private tipoeventoService: () => TipoeventoService;
  public tipoevento: ITipoevento = new Tipoevento();

  @Inject('eventoService') private eventoService: () => EventoService;

  public eventos: IEvento[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.tipoeventoId) {
        vm.retrieveTipoevento(to.params.tipoeventoId);
      }
      vm.initRelationships();
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.tipoevento.id) {
      this.tipoeventoService()
        .update(this.tipoevento)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A Tipoevento is updated with identifier ' + param.id;
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.tipoeventoService()
        .create(this.tipoevento)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A Tipoevento is created with identifier ' + param.id;
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveTipoevento(tipoeventoId): void {
    this.tipoeventoService()
      .find(tipoeventoId)
      .then(res => {
        this.tipoevento = res;
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
