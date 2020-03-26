import { mixins } from 'vue-class-component';

import { Component, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IArea } from '@/shared/model/eventos/area.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import AreaService from './area.service';

@Component
export default class Area extends mixins(Vue2Filters.mixin, AlertMixin) {
  @Inject('areaService') private areaService: () => AreaService;
  private removeId: number = null;
  public areas: IArea[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllAreas();
  }

  public clear(): void {
    this.retrieveAllAreas();
  }

  public retrieveAllAreas(): void {
    this.isFetching = true;

    this.areaService()
      .retrieve()
      .then(
        res => {
          this.areas = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IArea): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeArea(): void {
    this.areaService()
      .delete(this.removeId)
      .then(() => {
        const message = 'A Area is deleted with identifier ' + this.removeId;
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();

        this.removeId = null;
        this.retrieveAllAreas();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
