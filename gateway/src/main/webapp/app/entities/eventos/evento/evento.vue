<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('gatewayApp.eventosEvento.home.title')" id="evento-heading">Eventos</span>
            <router-link :to="{name: 'EventoCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-evento">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('gatewayApp.eventosEvento.home.createLabel')">
                    Create a new Evento
                </span>
            </router-link>
        </h2>
        <b-alert :show="dismissCountDown"
            dismissible
            :variant="alertType"
            @dismissed="dismissCountDown=0"
            @dismiss-count-down="countDownChanged">
            {{alertMessage}}
        </b-alert>
        <br/>
        <div class="alert alert-warning" v-if="!isFetching && eventos && eventos.length === 0">
            <span v-text="$t('gatewayApp.eventosEvento.home.notFound')">No eventos found</span>
        </div>
        <div class="table-responsive" v-if="eventos && eventos.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><span v-text="$t('global.field.id')">ID</span></th>
                    <th><span v-text="$t('gatewayApp.eventosEvento.descricao')">Descricao</span></th>
                    <th><span v-text="$t('gatewayApp.eventosEvento.numPessoasPerm')">Num Pessoas Perm</span></th>
                    <th><span v-text="$t('gatewayApp.eventosEvento.numPessoasDet')">Num Pessoas Det</span></th>
                    <th><span v-text="$t('gatewayApp.eventosEvento.dataHoraInicio')">Data Hora Inicio</span></th>
                    <th><span v-text="$t('gatewayApp.eventosEvento.dataHoraFim')">Data Hora Fim</span></th>
                    <th><span v-text="$t('gatewayApp.eventosEvento.path')">Path</span></th>
                    <th><span v-text="$t('gatewayApp.eventosEvento.formato')">Formato</span></th>
                    <th><span v-text="$t('gatewayApp.eventosEvento.area')">Area</span></th>
                    <th><span v-text="$t('gatewayApp.eventosEvento.camara')">Camara</span></th>
                    <th><span v-text="$t('gatewayApp.eventosEvento.tipoevento')">Tipoevento</span></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="evento in eventos"
                    :key="evento.id">
                    <td>
                        <router-link :to="{name: 'EventoView', params: {eventoId: evento.id}}">{{evento.id}}</router-link>
                    </td>
                    <td>{{evento.descricao}}</td>
                    <td>{{evento.numPessoasPerm}}</td>
                    <td>{{evento.numPessoasDet}}</td>
                    <td v-if="evento.dataHoraInicio"> {{$d(Date.parse(evento.dataHoraInicio), 'short') }}</td>
                    <td v-if="evento.dataHoraFim"> {{$d(Date.parse(evento.dataHoraFim), 'short') }}</td>
                    <td>{{evento.path}}</td>
                    <td>{{evento.formato}}</td>
                    <td>
                        <div v-if="evento.area">
                            <router-link :to="{name: 'AreaView', params: {areaId: evento.area.id}}">{{evento.area.nome}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="evento.camara">
                            <router-link :to="{name: 'CamaraView', params: {camaraId: evento.camara.id}}">{{evento.camara.descricao}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="evento.tipoevento">
                            <router-link :to="{name: 'TipoeventoView', params: {tipoeventoId: evento.tipoevento.id}}">{{evento.tipoevento.descricao}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'EventoView', params: {eventoId: evento.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'EventoEdit', params: {eventoId: evento.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(evento)"
                                   variant="danger"
                                   class="btn btn-sm"
                                   v-b-modal.removeEntity>
                                <font-awesome-icon icon="times"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                            </b-button>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <b-modal ref="removeEntity" id="removeEntity" >
            <span slot="modal-title"><span id="gatewayApp.eventosEvento.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-evento-heading" v-text="$t('gatewayApp.eventosEvento.delete.question', {'id': removeId})">Are you sure you want to delete this Evento?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-evento" v-text="$t('entity.action.delete')" v-on:click="removeEvento()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./evento.component.ts">
</script>
