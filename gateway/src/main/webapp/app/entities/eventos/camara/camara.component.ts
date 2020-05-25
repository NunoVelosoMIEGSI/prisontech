import { mixins } from 'vue-class-component';

import { Component, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ICamara } from '@/shared/model/eventos/camara.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import CamaraService from './camara.service';
import { VERSION } from '@/constants';
import AccountService from '@/account/account.service';

@Component
export default class Camara extends mixins(Vue2Filters.mixin, AlertMixin) {
  @Inject('camaraService') private camaraService: () => CamaraService;
  @Inject('accountService') private accountService: () => AccountService;
  private removeId: number = null;
  public camaras: ICamara[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllCamaras();
  }

  public clear(): void {
    this.retrieveAllCamaras();
  }

  public retrieveAllCamaras(): void {
    this.isFetching = true;

    this.camaraService()
      .retrieve()
      .then(
        res => {
          this.camaras = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: ICamara): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeCamara(): void {
    this.camaraService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('gatewayApp.eventosCamara.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();

        this.removeId = null;
        this.retrieveAllCamaras();
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
