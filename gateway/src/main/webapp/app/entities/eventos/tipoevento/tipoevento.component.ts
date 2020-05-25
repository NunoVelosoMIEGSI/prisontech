import { mixins } from 'vue-class-component';

import { Component, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ITipoevento } from '@/shared/model/eventos/tipoevento.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import TipoeventoService from './tipoevento.service';
import { VERSION } from '@/constants';
import AccountService from '@/account/account.service';

@Component
export default class Tipoevento extends mixins(Vue2Filters.mixin, AlertMixin) {
  @Inject('tipoeventoService') private tipoeventoService: () => TipoeventoService;
  @Inject('accountService') private accountService: () => AccountService;
  private removeId: number = null;
  public tipoeventos: ITipoevento[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllTipoeventos();
  }

  public clear(): void {
    this.retrieveAllTipoeventos();
  }

  public retrieveAllTipoeventos(): void {
    this.isFetching = true;

    this.tipoeventoService()
      .retrieve()
      .then(
        res => {
          this.tipoeventos = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: ITipoevento): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeTipoevento(): void {
    this.tipoeventoService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('gatewayApp.eventosTipoevento.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();

        this.removeId = null;
        this.retrieveAllTipoeventos();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }

  public hasAnyAuthority(authorities: any): boolean {
    return this.accountService().hasAnyAuthority(authorities);
  }
}
