<template>
    <div>
        <h2 id="page-heading">
            <span id="evento-heading">Eventos ocorridos</span>
            <router-link :to="{name: 'EventoCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-evento">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span >
                    Criar um novo evento
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
            <span>Não existem registos</span>
        </div>
        <div class="table-responsive" v-if="eventos && eventos.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><span>ID</span></th>
                    <th><span>Descrição</span></th>
                    <th><span>Número de pessoas permitidas</span></th>
                    <th><span>Número de pessoas detetadas</span></th>
                    <th><span>Data/Hora de início</span></th>
                    <th><span>Data/Hora de fim</span></th>
                    <th><span>Área</span></th>
                    <th><span>Câmara</span></th>
                    <th><span>Tipo de evento</span></th>
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
                    <td>{{evento.dataHoraInicio | formatDate}}</td>
                    <td>{{evento.dataHoraFim | formatDate}}</td>
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
                                <span class="d-none d-md-inline">Detalhes</span>
                            </router-link>
                            <router-link :to="{name: 'EventoEdit', params: {eventoId: evento.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline">Editar</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(evento)"
                                   variant="danger"
                                   class="btn btn-sm"
                                   v-b-modal.removeEntity>
                                <font-awesome-icon icon="times"></font-awesome-icon>
                                <span class="d-none d-md-inline">Eliminar</span>
                            </b-button>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <b-modal ref="removeEntity" id="removeEntity" >
            <span slot="modal-title"><span id="gatewayApp.eventosEvento.delete.question">Confirmar operação</span></span>
            <div class="modal-body">
                <p id="jhi-delete-evento-heading">Tem a certeza que deseja eliminar o registo?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancelar</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-evento" v-on:click="removeEvento()">Eliminar</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./evento.component.ts">
</script>
