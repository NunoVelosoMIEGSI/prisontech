import { Component, Vue, Inject } from 'vue-property-decorator';

import { IEvento } from '@/shared/model/eventos/evento.model';
import EventoService from './evento.service';

@Component
export default class EventoDetails extends Vue {
  @Inject('eventoService') private eventoService: () => EventoService;
  public evento: IEvento = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.eventoId) {
        vm.retrieveEvento(to.params.eventoId);
      }
    });
  }

  public retrieveEvento(eventoId) {
    this.eventoService()
      .find(eventoId)
      .then(res => {
        this.evento = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
