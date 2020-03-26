import { Component, Vue, Inject } from 'vue-property-decorator';

import { ILogseventos } from '@/shared/model/eventos/logseventos.model';
import LogseventosService from './logseventos.service';

@Component
export default class LogseventosDetails extends Vue {
  @Inject('logseventosService') private logseventosService: () => LogseventosService;
  public logseventos: ILogseventos = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.logseventosId) {
        vm.retrieveLogseventos(to.params.logseventosId);
      }
    });
  }

  public retrieveLogseventos(logseventosId) {
    this.logseventosService()
      .find(logseventosId)
      .then(res => {
        this.logseventos = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
