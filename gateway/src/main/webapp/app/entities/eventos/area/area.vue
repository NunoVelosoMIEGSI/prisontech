<template>
    <div>
        <h2 id="page-heading">
            <span id="area-heading">Áreas</span>
            <router-link :to="{name: 'AreaCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-area">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span >
                    Criar uam nova área
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
        <div class="alert alert-warning" v-if="!isFetching && areas && areas.length === 0">
            <span>Não existem registos</span>
        </div>
        <div class="table-responsive" v-if="areas && areas.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><span>ID</span></th>
                    <th><span>Nome</span></th>
                    <th><span>Número minimo de pessoas</span></th>
                    <th><span>Número máximo de pessoas</span></th>
                    <th><span>Limite área</span></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="area in areas"
                    :key="area.id">
                    <td>
                        <router-link :to="{name: 'AreaView', params: {areaId: area.id}}">{{area.id}}</router-link>
                    </td>
                    <td>{{area.nome}}</td>
                    <td>{{area.numMinPessoa}}</td>
                    <td>{{area.numMaxPessoa}}</td>
                    <td>{{area.limiteArea}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'AreaView', params: {areaId: area.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline">Detalhes</span>
                            </router-link>
                            <router-link :to="{name: 'AreaEdit', params: {areaId: area.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline">Editar</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(area)"
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
            <span slot="modal-title"><span id="gatewayApp.eventosArea.delete.question">Confirmar operação</span></span>
            <div class="modal-body">
                <p id="jhi-delete-area-heading">Tem a certeza que deseja eliminar o registo?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancelar</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-area" v-on:click="removeArea()">Eliminar</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./area.component.ts">
</script>
