import { Component, Vue, Inject } from 'vue-property-decorator';

import { ICamara } from '@/shared/model/eventos/camara.model';
import CamaraService from './camara.service';

@Component
export default class CamaraDetails extends Vue {
  @Inject('camaraService') private camaraService: () => CamaraService;
  public camara: ICamara = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.camaraId) {
        vm.retrieveCamara(to.params.camaraId);
      }
    });
  }

  public retrieveCamara(camaraId) {
    this.camaraService()
      .find(camaraId)
      .then(res => {
        this.camara = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
