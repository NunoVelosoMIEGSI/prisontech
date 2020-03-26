<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="gatewayApp.eventosCamara.home.createOrEditLabel">Create or edit a Camara</h2>
                <div>
                    <div class="form-group" v-if="camara.id">
                        <label for="id">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="camara.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="camara-descricao">Descricao</label>
                        <input type="text" class="form-control" name="descricao" id="camara-descricao"
                            :class="{'valid': !$v.camara.descricao.$invalid, 'invalid': $v.camara.descricao.$invalid }" v-model="$v.camara.descricao.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span>Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.camara.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./camara-update.component.ts">
</script>
