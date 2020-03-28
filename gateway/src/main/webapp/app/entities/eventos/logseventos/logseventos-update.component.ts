import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';
import format from 'date-fns/format';
import parse from 'date-fns/parse';
import parseISO from 'date-fns/parseISO';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';
import { ILogseventos, Logseventos } from '@/shared/model/eventos/logseventos.model';
import LogseventosService from './logseventos.service';

const validations: any = {
  logseventos: {
    dataHoraInicio: {},
    dataHoraFim: {},
    classeOrigem: {},
    metodoOrigem: {}
  }
};

@Component({
  validations
})
export default class LogseventosUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('logseventosService') private logseventosService: () => LogseventosService;
  public logseventos: ILogseventos = new Logseventos();
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.logseventosId) {
        vm.retrieveLogseventos(to.params.logseventosId);
      }
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.logseventos.id) {
      this.logseventosService()
        .update(this.logseventos)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('gatewayApp.eventosLogseventos.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.logseventosService()
        .create(this.logseventos)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('gatewayApp.eventosLogseventos.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public convertDateTimeFromServer(date: Date): string {
    if (date) {
      return format(date, DATE_TIME_LONG_FORMAT);
    }
    return null;
  }

  public updateInstantField(field, event) {
    if (event.target.value) {
      this.logseventos[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.logseventos[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.logseventos[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.logseventos[field] = null;
    }
  }

  public retrieveLogseventos(logseventosId): void {
    this.logseventosService()
      .find(logseventosId)
      .then(res => {
        res.dataHoraInicio = new Date(res.dataHoraInicio);
        res.dataHoraFim = new Date(res.dataHoraFim);
        this.logseventos = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
