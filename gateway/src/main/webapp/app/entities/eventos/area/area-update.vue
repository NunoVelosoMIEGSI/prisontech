<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="gatewayApp.eventosArea.home.createOrEditLabel" v-text="$t('gatewayApp.eventosArea.home.createOrEditLabel')">Create or edit a Area</h2>
                <div>
                    <!--<div class="form-group" v-if="area.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="area.id" readonly />
                    </div>-->
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('gatewayApp.eventosArea.nome')" for="area-nome">Nome</label>
                        <input type="text" class="form-control" name="nome" id="area-nome"
                            :class="{'valid': !$v.area.nome.$invalid, 'invalid': $v.area.nome.$invalid }" v-model="$v.area.nome.$model"  required/>
                        <div v-if="$v.area.nome.$anyDirty && $v.area.nome.$invalid">
                            <small class="form-text text-danger" v-if="!$v.area.nome.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('gatewayApp.eventosArea.numPessoasPerm')" for="area-numPessoasPerm">Num Pessoas Perm</label>
                        <input type="number" class="form-control" name="numPessoasPerm" id="area-numPessoasPerm"
                            :class="{'valid': !$v.area.numPessoasPerm.$invalid, 'invalid': $v.area.numPessoasPerm.$invalid }" v-model.number="$v.area.numPessoasPerm.$model"  required/>
                        <div v-if="$v.area.numPessoasPerm.$anyDirty && $v.area.numPessoasPerm.$invalid">
                            <small class="form-text text-danger" v-if="!$v.area.numPessoasPerm.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.area.numPessoasPerm.numeric" v-text="$t('entity.validation.number')">
                                This field should be a number.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('gatewayApp.eventosArea.limiteArea')" for="area-limiteArea">Limite Area</label>
                        <input type="number" class="form-control" name="limiteArea" id="area-limiteArea"
                            :class="{'valid': !$v.area.limiteArea.$invalid, 'invalid': $v.area.limiteArea.$invalid }" v-model.number="$v.area.limiteArea.$model"  required/>
                        <div v-if="$v.area.limiteArea.$anyDirty && $v.area.limiteArea.$invalid">
                            <small class="form-text text-danger" v-if="!$v.area.limiteArea.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.area.limiteArea.numeric" v-text="$t('entity.validation.number')">
                                This field should be a number.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('gatewayApp.eventosArea.camara')" for="area-camara">Camara</label>
                        <select class="form-control" id="area-camara" name="camara" v-model="area.camara">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="area.camara && camaraOption.id === area.camara.id ? area.camara : camaraOption" v-for="camaraOption in camaras" :key="camaraOption.id">{{camaraOption.descricao}}</option>
                        </select>
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
