<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="gatewayApp.eventosTipoevento.home.createOrEditLabel">Criar ou editar um tipo de evento</h2>
                <div>
                    <div class="form-group" v-if="tipoevento.id">
                        <label for="id">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="tipoevento.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="tipoevento-descricao">Descrição</label>
                        <input type="text" class="form-control" name="descricao" id="tipoevento-descricao"
                            :class="{'valid': !$v.tipoevento.descricao.$invalid, 'invalid': $v.tipoevento.descricao.$invalid }" v-model="$v.tipoevento.descricao.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span>Cancelar</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.tipoevento.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Guardar</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./tipoevento-update.component.ts">
</script>
