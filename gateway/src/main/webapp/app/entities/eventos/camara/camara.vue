<template>
    <div>
        <h2 id="page-heading">
            <span id="camara-heading">Câmaras</span>
            <router-link :to="{name: 'CamaraCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-camara">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span >
                    Criar uma nova câmara
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
        <div class="alert alert-warning" v-if="!isFetching && camaras && camaras.length === 0">
            <span>Não existem registos</span>
        </div>
        <div class="table-responsive" v-if="camaras && camaras.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><span>ID</span></th>
                    <th><span>Descrição</span></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="camara in camaras"
                    :key="camara.id">
                    <td>
                        <router-link :to="{name: 'CamaraView', params: {camaraId: camara.id}}">{{camara.id}}</router-link>
                    </td>
                    <td>{{camara.descricao}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'CamaraView', params: {camaraId: camara.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline">Detalhes</span>
                            </router-link>
                            <router-link :to="{name: 'CamaraEdit', params: {camaraId: camara.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline">Editar</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(camara)"
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
            <span slot="modal-title"><span id="gatewayApp.eventosCamara.delete.question">Confirmar operação</span></span>
            <div class="modal-body">
                <p id="jhi-delete-camara-heading">Tem a certeza que deseja eliminar o registo?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancelar</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-camara" v-on:click="removeCamara()">Eliminar</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./camara.component.ts">
</script>
