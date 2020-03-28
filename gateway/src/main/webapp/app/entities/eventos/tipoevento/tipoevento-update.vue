<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="gatewayApp.eventosTipoevento.home.createOrEditLabel" v-text="$t('gatewayApp.eventosTipoevento.home.createOrEditLabel')">Create or edit a Tipoevento</h2>
                <div>
                    <div class="form-group" v-if="tipoevento.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="tipoevento.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('gatewayApp.eventosTipoevento.descricao')" for="tipoevento-descricao">Descricao</label>
                        <input type="text" class="form-control" name="descricao" id="tipoevento-descricao"
                            :class="{'valid': !$v.tipoevento.descricao.$invalid, 'invalid': $v.tipoevento.descricao.$invalid }" v-model="$v.tipoevento.descricao.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.tipoevento.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./tipoevento-update.component.ts">
</script>
