<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('gatewayApp.eventosCamara.home.title')" id="camara-heading">Camaras</span>
            <router-link :to="{name: 'CamaraCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-camara">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('gatewayApp.eventosCamara.home.createLabel')">
                    Create a new Camara
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
            <span v-text="$t('gatewayApp.eventosCamara.home.notFound')">No camaras found</span>
        </div>
        <div class="table-responsive" v-if="camaras && camaras.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <!--<th><span v-text="$t('global.field.id')">ID</span></th>-->
                    <th><span v-text="$t('gatewayApp.eventosCamara.descricao')">Descricao</span></th>
                    <th><span v-text="$t('gatewayApp.eventosCamara.estado')">Estado</span></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="camara in camaras"
                    :key="camara.id">
                    <!--<td>
                        <router-link :to="{name: 'CamaraView', params: {camaraId: camara.id}}">{{camara.id}}</router-link>
                    </td>-->
                    <td>{{camara.descricao}}</td>
                    <!--<td>{{camara.estado}}</td>-->
                    <td v-if="camara.estado==true">Ativa</td>
                    <td v-if="camara.estado==false">Inativa</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'CamaraView', params: {camaraId: camara.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'CamaraEdit', params: {camaraId: camara.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(camara)"
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
            <span slot="modal-title"><span id="gatewayApp.eventosCamara.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-camara-heading" v-text="$t('gatewayApp.eventosCamara.delete.question', {'id': removeId})">Are you sure you want to delete this Camara?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-camara" v-text="$t('entity.action.delete')" v-on:click="removeCamara()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./camara.component.ts">
</script>
