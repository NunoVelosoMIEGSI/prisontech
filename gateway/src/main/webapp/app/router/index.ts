import Vue from 'vue';
import Component from 'vue-class-component';
Component.registerHooks([
  'beforeRouteEnter',
  'beforeRouteLeave',
  'beforeRouteUpdate' // for vue-router 2.2+
]);
import Router from 'vue-router';
import { Authority } from '@/shared/security/authority';
const Home = () => import('../core/home/home.vue');
const Error = () => import('../core/error/error.vue');
const Register = () => import('../account/register/register.vue');
const Activate = () => import('../account/activate/activate.vue');
const ResetPasswordInit = () => import('../account/reset-password/init/reset-password-init.vue');
const ResetPasswordFinish = () => import('../account/reset-password/finish/reset-password-finish.vue');
const ChangePassword = () => import('../account/change-password/change-password.vue');
const Settings = () => import('../account/settings/settings.vue');
const JhiUserManagementComponent = () => import('../admin/user-management/user-management.vue');
const JhiUserManagementViewComponent = () => import('../admin/user-management/user-management-view.vue');
const JhiUserManagementEditComponent = () => import('../admin/user-management/user-management-edit.vue');
const JhiConfigurationComponent = () => import('../admin/configuration/configuration.vue');
const JhiDocsComponent = () => import('../admin/docs/docs.vue');
const JhiHealthComponent = () => import('../admin/health/health.vue');
const JhiLogsComponent = () => import('../admin/logs/logs.vue');
const JhiAuditsComponent = () => import('../admin/audits/audits.vue');
const JhiMetricsComponent = () => import('../admin/metrics/metrics.vue');
const JhiGatewayComponent = () => import('../admin/gateway/gateway.vue');
/* tslint:disable */
// prettier-ignore
const Area = () => import('../entities/eventos/area/area.vue');
// prettier-ignore
const AreaUpdate = () => import('../entities/eventos/area/area-update.vue');
// prettier-ignore
const AreaDetails = () => import('../entities/eventos/area/area-details.vue');
// prettier-ignore
const Camara = () => import('../entities/eventos/camara/camara.vue');
// prettier-ignore
const CamaraUpdate = () => import('../entities/eventos/camara/camara-update.vue');
// prettier-ignore
const CamaraDetails = () => import('../entities/eventos/camara/camara-details.vue');
// prettier-ignore
const Tipoevento = () => import('../entities/eventos/tipoevento/tipoevento.vue');
// prettier-ignore
const TipoeventoUpdate = () => import('../entities/eventos/tipoevento/tipoevento-update.vue');
// prettier-ignore
const TipoeventoDetails = () => import('../entities/eventos/tipoevento/tipoevento-details.vue');
// prettier-ignore
const Evento = () => import('../entities/eventos/evento/evento.vue');
// prettier-ignore
const EventoUpdate = () => import('../entities/eventos/evento/evento-update.vue');
// prettier-ignore
const EventoDetails = () => import('../entities/eventos/evento/evento-details.vue');
// prettier-ignore
const Logseventos = () => import('../entities/eventos/logseventos/logseventos.vue');
// prettier-ignore
const LogseventosUpdate = () => import('../entities/eventos/logseventos/logseventos-update.vue');
// prettier-ignore
const LogseventosDetails = () => import('../entities/eventos/logseventos/logseventos-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

Vue.use(Router);

// prettier-ignore
export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/forbidden',
      name: 'Forbidden',
      component: Error,
      meta: { error403: true }
    },
    {
      path: '/not-found',
      name: 'NotFound',
      component: Error,
      meta: { error404: true }
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: '/account/activate',
      name: 'Activate',
      component: Activate
    },
    {
      path: '/account/reset/request',
      name: 'ResetPasswordInit',
      component: ResetPasswordInit
    },
    {
      path: '/account/reset/finish',
      name: 'ResetPasswordFinish',
      component: ResetPasswordFinish
    },
    {
      path: '/account/password',
      name: 'ChangePassword',
      component: ChangePassword,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/account/settings',
      name: 'Settings',
      component: Settings,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/admin/user-management',
      name: 'JhiUser',
      component: JhiUserManagementComponent,
      meta: { authorities: [Authority.ADMIN] }
    },
    {
      path: '/admin/user-management/new',
      name: 'JhiUserCreate',
      component: JhiUserManagementEditComponent,
      meta: { authorities: [Authority.ADMIN] }
    },
    {
      path: '/admin/user-management/:userId/edit',
      name: 'JhiUserEdit',
      component: JhiUserManagementEditComponent,
      meta: { authorities: [Authority.ADMIN] }
    },
    {
      path: '/admin/user-management/:userId/view',
      name: 'JhiUserView',
      component: JhiUserManagementViewComponent,
      meta: { authorities: [Authority.ADMIN] }
    },
    {
      path: '/admin/docs',
      name: 'JhiDocsComponent',
      component: JhiDocsComponent,
      meta: { authorities: [Authority.ADMIN] }
    },
    {
      path: '/admin/audits',
      name: 'JhiAuditsComponent',
      component: JhiAuditsComponent,
      meta: { authorities: [Authority.ADMIN] }
    },
    {
      path: '/admin/jhi-health',
      name: 'JhiHealthComponent',
      component: JhiHealthComponent,
      meta: { authorities: [Authority.ADMIN] }
    },
    {
      path: '/admin/logs',
      name: 'JhiLogsComponent',
      component: JhiLogsComponent,
      meta: { authorities: [Authority.ADMIN] }
    },
    {
      path: '/admin/jhi-metrics',
      name: 'JhiMetricsComponent',
      component: JhiMetricsComponent,
      meta: { authorities: [Authority.ADMIN] }
    },
    {
      path: '/admin/jhi-configuration',
      name: 'JhiConfigurationComponent',
      component: JhiConfigurationComponent,
      meta: { authorities: [Authority.ADMIN] }
    }
,
    {
      path: '/admin/gateway',
      name: 'JhiGatewayComponent',
      component: JhiGatewayComponent,
      meta: { authorities: [Authority.ADMIN] }
    }
    ,
    {
      path: '/area',
      name: 'Area',
      component: Area,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/area/new',
      name: 'AreaCreate',
      component: AreaUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/area/:areaId/edit',
      name: 'AreaEdit',
      component: AreaUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/area/:areaId/view',
      name: 'AreaView',
      component: AreaDetails,
      meta: { authorities: [Authority.USER] }
    }
    ,
    {
      path: '/camara',
      name: 'Camara',
      component: Camara,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/camara/new',
      name: 'CamaraCreate',
      component: CamaraUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/camara/:camaraId/edit',
      name: 'CamaraEdit',
      component: CamaraUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/camara/:camaraId/view',
      name: 'CamaraView',
      component: CamaraDetails,
      meta: { authorities: [Authority.USER] }
    }
    ,
    {
      path: '/tipoevento',
      name: 'Tipoevento',
      component: Tipoevento,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/tipoevento/new',
      name: 'TipoeventoCreate',
      component: TipoeventoUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/tipoevento/:tipoeventoId/edit',
      name: 'TipoeventoEdit',
      component: TipoeventoUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/tipoevento/:tipoeventoId/view',
      name: 'TipoeventoView',
      component: TipoeventoDetails,
      meta: { authorities: [Authority.USER] }
    }
    ,
    {
      path: '/evento',
      name: 'Evento',
      component: Evento,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/evento/new',
      name: 'EventoCreate',
      component: EventoUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/evento/:eventoId/edit',
      name: 'EventoEdit',
      component: EventoUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/evento/:eventoId/view',
      name: 'EventoView',
      component: EventoDetails,
      meta: { authorities: [Authority.USER] }
    }
    ,
    {
      path: '/logseventos',
      name: 'Logseventos',
      component: Logseventos,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/logseventos/new',
      name: 'LogseventosCreate',
      component: LogseventosUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/logseventos/:logseventosId/edit',
      name: 'LogseventosEdit',
      component: LogseventosUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/logseventos/:logseventosId/view',
      name: 'LogseventosView',
      component: LogseventosDetails,
      meta: { authorities: [Authority.USER] }
    }
    // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  ]
});
