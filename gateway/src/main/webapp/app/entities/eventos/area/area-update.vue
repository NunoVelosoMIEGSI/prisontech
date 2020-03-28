<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="gatewayApp.eventosArea.home.createOrEditLabel" v-text="$t('gatewayApp.eventosArea.home.createOrEditLabel')">Create or edit a Area</h2>
                <div>
                    <div class="form-group" v-if="area.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="area.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('gatewayApp.eventosArea.nome')" for="area-nome">Nome</label>
                        <input type="text" class="form-control" name="nome" id="area-nome"
                            :class="{'valid': !$v.area.nome.$invalid, 'invalid': $v.area.nome.$invalid }" v-model="$v.area.nome.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('gatewayApp.eventosArea.numMinPessoa')" for="area-numMinPessoa">Num Min Pessoa</label>
                        <input type="number" class="form-control" name="numMinPessoa" id="area-numMinPessoa"
                            :class="{'valid': !$v.area.numMinPessoa.$invalid, 'invalid': $v.area.numMinPessoa.$invalid }" v-model.number="$v.area.numMinPessoa.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('gatewayApp.eventosArea.numMaxPessoa')" for="area-numMaxPessoa">Num Max Pessoa</label>
                        <input type="number" class="form-control" name="numMaxPessoa" id="area-numMaxPessoa"
                            :class="{'valid': !$v.area.numMaxPessoa.$invalid, 'invalid': $v.area.numMaxPessoa.$invalid }" v-model.number="$v.area.numMaxPessoa.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('gatewayApp.eventosArea.limiteArea')" for="area-limiteArea">Limite Area</label>
                        <input type="number" class="form-control" name="limiteArea" id="area-limiteArea"
                            :class="{'valid': !$v.area.limiteArea.$invalid, 'invalid': $v.area.limiteArea.$invalid }" v-model.number="$v.area.limiteArea.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.area.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./area-update.component.ts">
</script>
