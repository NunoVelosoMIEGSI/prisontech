<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('gatewayApp.eventosArea.home.title')" id="area-heading">Areas</span>
            <router-link :to="{name: 'AreaCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-area">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('gatewayApp.eventosArea.home.createLabel')">
                    Create a new Area
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
            <span v-text="$t('gatewayApp.eventosArea.home.notFound')">No areas found</span>
        </div>
        <div class="table-responsive" v-if="areas && areas.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><span v-text="$t('global.field.id')">ID</span></th>
                    <th><span v-text="$t('gatewayApp.eventosArea.nome')">Nome</span></th>
                    <th><span v-text="$t('gatewayApp.eventosArea.numMinPessoa')">Num Min Pessoa</span></th>
                    <th><span v-text="$t('gatewayApp.eventosArea.numMaxPessoa')">Num Max Pessoa</span></th>
                    <th><span v-text="$t('gatewayApp.eventosArea.limiteArea')">Limite Area</span></th>
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
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'AreaEdit', params: {areaId: area.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(area)"
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
            <span slot="modal-title"><span id="gatewayApp.eventosArea.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-area-heading" v-text="$t('gatewayApp.eventosArea.delete.question', {'id': removeId})">Are you sure you want to delete this Area?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-area" v-text="$t('entity.action.delete')" v-on:click="removeArea()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./area.component.ts">
</script>
