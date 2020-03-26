import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITipoevento } from '@/shared/model/eventos/tipoevento.model';
import TipoeventoService from './tipoevento.service';

@Component
export default class TipoeventoDetails extends Vue {
  @Inject('tipoeventoService') private tipoeventoService: () => TipoeventoService;
  public tipoevento: ITipoevento = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.tipoeventoId) {
        vm.retrieveTipoevento(to.params.tipoeventoId);
      }
    });
  }

  public retrieveTipoevento(tipoeventoId) {
    this.tipoeventoService()
      .find(tipoeventoId)
      .then(res => {
        this.tipoevento = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
