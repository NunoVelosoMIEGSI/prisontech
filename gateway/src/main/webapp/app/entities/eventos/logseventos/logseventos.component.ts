import { mixins } from 'vue-class-component';

import { Component, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ILogseventos } from '@/shared/model/eventos/logseventos.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import LogseventosService from './logseventos.service';

@Component
export default class Logseventos extends mixins(Vue2Filters.mixin, AlertMixin) {
  @Inject('logseventosService') private logseventosService: () => LogseventosService;
  private removeId: number = null;
  public logseventos: ILogseventos[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllLogseventoss();
  }

  public clear(): void {
    this.retrieveAllLogseventoss();
  }

  public retrieveAllLogseventoss(): void {
    this.isFetching = true;

    this.logseventosService()
      .retrieve()
      .then(
        res => {
          this.logseventos = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: ILogseventos): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeLogseventos(): void {
    this.logseventosService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('gatewayApp.eventosLogseventos.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();

        this.removeId = null;
        this.retrieveAllLogseventoss();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
