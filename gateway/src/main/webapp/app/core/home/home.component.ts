import Component from 'vue-class-component';
import { Inject, Vue } from 'vue-property-decorator';
import LoginService from '@/account/login.service';
import { IEvento } from '@/shared/model/eventos/evento.model';
import axios from 'axios';

@Component
export default class Home extends Vue {
  @Inject('loginService')
  private loginService: () => LoginService;
  public eventos: IEvento[] = [];

  public openLogin(): void {
    this.loginService().openLogin((<any>this).$root);
  }

  public get authenticated(): boolean {
    return this.$store.getters.authenticated;
  }

  public get username(): string {
    return this.$store.getters.account ? this.$store.getters.account.login : '';
  }

  public mounted(): void {
    this.retrieveAllEventos();
  }

  public updated(): void {
    this.retrieveAllEventos();
  }

  public retrieveAllEventos(): void {
    this.retrieve().then(
      res => {
        this.eventos = res.data;
      },
      err => {}
    );
  }

  public retrieve(): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get('services/eventos/api/eventos')
        .then(function(res) {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
