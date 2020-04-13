<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="gatewayApp.eventosEvento.home.createOrEditLabel" v-text="$t('gatewayApp.eventosEvento.home.createOrEditLabel')">Create or edit a Evento</h2>
                <div>
                    <!--<div class="form-group" v-if="evento.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="evento.id" readonly />
                    </div>-->
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('gatewayApp.eventosEvento.descricao')" for="evento-descricao">Descricao</label>
                        <input type="text" class="form-control" name="descricao" id="evento-descricao"
                            :class="{'valid': !$v.evento.descricao.$invalid, 'invalid': $v.evento.descricao.$invalid }" v-model="$v.evento.descricao.$model"  required/>
                        <div v-if="$v.evento.descricao.$anyDirty && $v.evento.descricao.$invalid">
                            <small class="form-text text-danger" v-if="!$v.evento.descricao.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('gatewayApp.eventosEvento.numPessoasPerm')" for="evento-numPessoasPerm">Num Pessoas Perm</label>
                        <input type="number" class="form-control" name="numPessoasPerm" id="evento-numPessoasPerm"
                            :class="{'valid': !$v.evento.numPessoasPerm.$invalid, 'invalid': $v.evento.numPessoasPerm.$invalid }" v-model.number="$v.evento.numPessoasPerm.$model"  required/>
                        <div v-if="$v.evento.numPessoasPerm.$anyDirty && $v.evento.numPessoasPerm.$invalid">
                            <small class="form-text text-danger" v-if="!$v.evento.numPessoasPerm.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.evento.numPessoasPerm.numeric" v-text="$t('entity.validation.number')">
                                This field should be a number.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('gatewayApp.eventosEvento.numPessoasDet')" for="evento-numPessoasDet">Num Pessoas Det</label>
                        <input type="number" class="form-control" name="numPessoasDet" id="evento-numPessoasDet"
                            :class="{'valid': !$v.evento.numPessoasDet.$invalid, 'invalid': $v.evento.numPessoasDet.$invalid }" v-model.number="$v.evento.numPessoasDet.$model"  required/>
                        <div v-if="$v.evento.numPessoasDet.$anyDirty && $v.evento.numPessoasDet.$invalid">
                            <small class="form-text text-danger" v-if="!$v.evento.numPessoasDet.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.evento.numPessoasDet.numeric" v-text="$t('entity.validation.number')">
                                This field should be a number.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('gatewayApp.eventosEvento.dataHoraInicio')" for="evento-dataHoraInicio">Data Hora Inicio</label>
                        <div class="d-flex">
                            <input id="evento-dataHoraInicio" type="datetime-local" class="form-control" name="dataHoraInicio" :class="{'valid': !$v.evento.dataHoraInicio.$invalid, 'invalid': $v.evento.dataHoraInicio.$invalid }"
                             required
                            :value="convertDateTimeFromServer($v.evento.dataHoraInicio.$model)"
                            @change="updateZonedDateTimeField('dataHoraInicio', $event)"/>
                        </div>
                        <div v-if="$v.evento.dataHoraInicio.$anyDirty && $v.evento.dataHoraInicio.$invalid">
                            <small class="form-text text-danger" v-if="!$v.evento.dataHoraInicio.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.evento.dataHoraInicio.ZonedDateTimelocal" v-text="$t('entity.validation.ZonedDateTimelocal')">
                                This field should be a date and time.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('gatewayApp.eventosEvento.dataHoraFim')" for="evento-dataHoraFim">Data Hora Fim</label>
                        <div class="d-flex">
                            <input id="evento-dataHoraFim" type="datetime-local" class="form-control" name="dataHoraFim" :class="{'valid': !$v.evento.dataHoraFim.$invalid, 'invalid': $v.evento.dataHoraFim.$invalid }"
                             required
                            :value="convertDateTimeFromServer($v.evento.dataHoraFim.$model)"
                            @change="updateZonedDateTimeField('dataHoraFim', $event)"/>
                        </div>
                        <div v-if="$v.evento.dataHoraFim.$anyDirty && $v.evento.dataHoraFim.$invalid">
                            <small class="form-text text-danger" v-if="!$v.evento.dataHoraFim.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.evento.dataHoraFim.ZonedDateTimelocal" v-text="$t('entity.validation.ZonedDateTimelocal')">
                                This field should be a date and time.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('gatewayApp.eventosEvento.path')" for="evento-path">Path</label>
                        <input type="text" class="form-control" name="path" id="evento-path"
                            :class="{'valid': !$v.evento.path.$invalid, 'invalid': $v.evento.path.$invalid }" v-model="$v.evento.path.$model"  required/>
                        <div v-if="$v.evento.path.$anyDirty && $v.evento.path.$invalid">
                            <small class="form-text text-danger" v-if="!$v.evento.path.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('gatewayApp.eventosEvento.formato')" for="evento-formato">Formato</label>
                        <input type="text" class="form-control" name="formato" id="evento-formato"
                            :class="{'valid': !$v.evento.formato.$invalid, 'invalid': $v.evento.formato.$invalid }" v-model="$v.evento.formato.$model"  required/>
                        <div v-if="$v.evento.formato.$anyDirty && $v.evento.formato.$invalid">
                            <small class="form-text text-danger" v-if="!$v.evento.formato.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('gatewayApp.eventosEvento.area')" for="evento-area">Area</label>
                        <select class="form-control" id="evento-area" name="area" v-model="evento.area">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="evento.area && areaOption.id === evento.area.id ? evento.area : areaOption" v-for="areaOption in areas" :key="areaOption.id">{{areaOption.nome}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('gatewayApp.eventosEvento.camara')" for="evento-camara">Camara</label>
                        <select class="form-control" id="evento-camara" name="camara" v-model="evento.camara">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="evento.camara && camaraOption.id === evento.camara.id ? evento.camara : camaraOption" v-for="camaraOption in camaras" :key="camaraOption.id">{{camaraOption.descricao}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('gatewayApp.eventosEvento.tipoevento')" for="evento-tipoevento">Tipoevento</label>
                        <select class="form-control" id="evento-tipoevento" name="tipoevento" v-model="evento.tipoevento">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="evento.tipoevento && tipoeventoOption.id === evento.tipoevento.id ? evento.tipoevento : tipoeventoOption" v-for="tipoeventoOption in tipoeventos" :key="tipoeventoOption.id">{{tipoeventoOption.descricao}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.evento.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./evento-update.component.ts">
</script>
