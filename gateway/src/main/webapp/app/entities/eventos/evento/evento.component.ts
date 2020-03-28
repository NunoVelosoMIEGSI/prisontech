import { mixins } from 'vue-class-component';

import { Component, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IEvento } from '@/shared/model/eventos/evento.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import EventoService from './evento.service';

@Component
export default class Evento extends mixins(Vue2Filters.mixin, AlertMixin) {
  @Inject('eventoService') private eventoService: () => EventoService;
  private removeId: number = null;
  public eventos: IEvento[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllEventos();
  }

  public clear(): void {
    this.retrieveAllEventos();
  }

  public retrieveAllEventos(): void {
    this.isFetching = true;

    this.eventoService()
      .retrieve()
      .then(
        res => {
          this.eventos = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IEvento): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeEvento(): void {
    this.eventoService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('gatewayApp.eventosEvento.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();

        this.removeId = null;
        this.retrieveAllEventos();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
