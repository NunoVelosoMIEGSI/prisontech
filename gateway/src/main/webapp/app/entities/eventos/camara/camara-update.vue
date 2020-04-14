<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="gatewayApp.eventosCamara.home.createOrEditLabel" v-text="$t('gatewayApp.eventosCamara.home.createOrEditLabel')">Create or edit a Camara</h2>
                <div>
                    <!--<div class="form-group" v-if="camara.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="camara.id" readonly />
                    </div>-->
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('gatewayApp.eventosCamara.descricao')" for="camara-descricao">Descricao</label>
                        <input type="text" class="form-control" name="descricao" id="camara-descricao"
                            :class="{'valid': !$v.camara.descricao.$invalid, 'invalid': $v.camara.descricao.$invalid }" v-model="$v.camara.descricao.$model"  required/>
                        <div v-if="$v.camara.descricao.$anyDirty && $v.camara.descricao.$invalid">
                            <small class="form-text text-danger" v-if="!$v.camara.descricao.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('gatewayApp.eventosCamara.estado')" for="camara-estado">Câmara ativa</label>
                        <input type="checkbox" class="form-check" name="estado" id="camara-estado"
                            :class="{'valid': !$v.camara.estado.$invalid, 'invalid': $v.camara.estado.$invalid }" v-model="$v.camara.estado.$model"  required/>
                        <div v-if="$v.camara.estado.$anyDirty && $v.camara.estado.$invalid">
                            <small class="form-text text-danger" v-if="!$v.camara.estado.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.camara.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./camara-update.component.ts">
</script>
