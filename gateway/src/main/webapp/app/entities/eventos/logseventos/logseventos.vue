<template>
    <div>
        <h2 id="page-heading">
            <span id="logseventos-heading">Logseventos</span>
            <router-link :to="{name: 'LogseventosCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-logseventos">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span >
                    Create a new Logseventos
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
        <div class="alert alert-warning" v-if="!isFetching && logseventos && logseventos.length === 0">
            <span>No logseventos found</span>
        </div>
        <div class="table-responsive" v-if="logseventos && logseventos.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><span>ID</span></th>
                    <th><span>Data Hora Inicio</span></th>
                    <th><span>Data Hora Fim</span></th>
                    <th><span>Classe Origem</span></th>
                    <th><span>Metodo Origem</span></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="logseventos in logseventos"
                    :key="logseventos.id">
                    <td>
                        <router-link :to="{name: 'LogseventosView', params: {logseventosId: logseventos.id}}">{{logseventos.id}}</router-link>
                    </td>
                    <td>{{logseventos.dataHoraInicio | formatDate}}</td>
                    <td>{{logseventos.dataHoraFim | formatDate}}</td>
                    <td>{{logseventos.classeOrigem}}</td>
                    <td>{{logseventos.metodoOrigem}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'LogseventosView', params: {logseventosId: logseventos.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline">View</span>
                            </router-link>
                            <router-link :to="{name: 'LogseventosEdit', params: {logseventosId: logseventos.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(logseventos)"
                                   variant="danger"
                                   class="btn btn-sm"
                                   v-b-modal.removeEntity>
                                <font-awesome-icon icon="times"></font-awesome-icon>
                                <span class="d-none d-md-inline">Delete</span>
                            </b-button>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <b-modal ref="removeEntity" id="removeEntity" >
            <span slot="modal-title"><span id="gatewayApp.eventosLogseventos.delete.question">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-logseventos-heading">Are you sure you want to delete this Logseventos?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-logseventos" v-on:click="removeLogseventos()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./logseventos.component.ts">
</script>
