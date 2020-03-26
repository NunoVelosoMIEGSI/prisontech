import { Component, Vue, Inject } from 'vue-property-decorator';

import { IArea } from '@/shared/model/eventos/area.model';
import AreaService from './area.service';

@Component
export default class AreaDetails extends Vue {
  @Inject('areaService') private areaService: () => AreaService;
  public area: IArea = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.areaId) {
        vm.retrieveArea(to.params.areaId);
      }
    });
  }

  public retrieveArea(areaId) {
    this.areaService()
      .find(areaId)
      .then(res => {
        this.area = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
