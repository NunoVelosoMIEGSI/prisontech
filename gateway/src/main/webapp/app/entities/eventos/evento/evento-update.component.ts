import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';
import format from 'date-fns/format';
import parse from 'date-fns/parse';
import parseISO from 'date-fns/parseISO';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AreaService from '../area/area.service';
import { IArea } from '@/shared/model/eventos/area.model';

import CamaraService from '../camara/camara.service';
import { ICamara } from '@/shared/model/eventos/camara.model';

import TipoeventoService from '../tipoevento/tipoevento.service';
import { ITipoevento } from '@/shared/model/eventos/tipoevento.model';

import AlertService from '@/shared/alert/alert.service';
import { IEvento, Evento } from '@/shared/model/eventos/evento.model';
import EventoService from './evento.service';

const validations: any = {
  evento: {
    descricao: {},
    numPessoasPerm: {},
    numPessoasDet: {},
    dataHoraInicio: {},
    dataHoraFim: {},
    path: {},
    formato: {}
  }
};

@Component({
  validations
})
export default class EventoUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('eventoService') private eventoService: () => EventoService;
  public evento: IEvento = new Evento();

  @Inject('areaService') private areaService: () => AreaService;

  public areas: IArea[] = [];

  @Inject('camaraService') private camaraService: () => CamaraService;

  public camaras: ICamara[] = [];

  @Inject('tipoeventoService') private tipoeventoService: () => TipoeventoService;

  public tipoeventos: ITipoevento[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.eventoId) {
        vm.retrieveEvento(to.params.eventoId);
      }
      vm.initRelationships();
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.evento.id) {
      this.eventoService()
        .update(this.evento)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('gatewayApp.eventosEvento.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.eventoService()
        .create(this.evento)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('gatewayApp.eventosEvento.created', { param: param.id });
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
      this.evento[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.evento[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.evento[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.evento[field] = null;
    }
  }

  public retrieveEvento(eventoId): void {
    this.eventoService()
      .find(eventoId)
      .then(res => {
        res.dataHoraInicio = new Date(res.dataHoraInicio);
        res.dataHoraFim = new Date(res.dataHoraFim);
        this.evento = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.areaService()
      .retrieve()
      .then(res => {
        this.areas = res.data;
      });
    this.camaraService()
      .retrieve()
      .then(res => {
        this.camaras = res.data;
      });
    this.tipoeventoService()
      .retrieve()
      .then(res => {
        this.tipoeventos = res.data;
      });
  }
}
