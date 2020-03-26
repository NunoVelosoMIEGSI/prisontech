<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="gatewayApp.eventosEvento.home.createOrEditLabel">Create or edit a Evento</h2>
                <div>
                    <div class="form-group" v-if="evento.id">
                        <label for="id">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="evento.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="evento-descricao">Descricao</label>
                        <input type="text" class="form-control" name="descricao" id="evento-descricao"
                            :class="{'valid': !$v.evento.descricao.$invalid, 'invalid': $v.evento.descricao.$invalid }" v-model="$v.evento.descricao.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="evento-numPessoasPerm">Num Pessoas Perm</label>
                        <input type="number" class="form-control" name="numPessoasPerm" id="evento-numPessoasPerm"
                            :class="{'valid': !$v.evento.numPessoasPerm.$invalid, 'invalid': $v.evento.numPessoasPerm.$invalid }" v-model.number="$v.evento.numPessoasPerm.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="evento-numPessoasDet">Num Pessoas Det</label>
                        <input type="number" class="form-control" name="numPessoasDet" id="evento-numPessoasDet"
                            :class="{'valid': !$v.evento.numPessoasDet.$invalid, 'invalid': $v.evento.numPessoasDet.$invalid }" v-model.number="$v.evento.numPessoasDet.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="evento-dataHoraInicio">Data Hora Inicio</label>
                        <div class="d-flex">
                            <input id="evento-dataHoraInicio" type="datetime-local" class="form-control" name="dataHoraInicio" :class="{'valid': !$v.evento.dataHoraInicio.$invalid, 'invalid': $v.evento.dataHoraInicio.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.evento.dataHoraInicio.$model)"
                            @change="updateZonedDateTimeField('dataHoraInicio', $event)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="evento-dataHoraFim">Data Hora Fim</label>
                        <div class="d-flex">
                            <input id="evento-dataHoraFim" type="datetime-local" class="form-control" name="dataHoraFim" :class="{'valid': !$v.evento.dataHoraFim.$invalid, 'invalid': $v.evento.dataHoraFim.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.evento.dataHoraFim.$model)"
                            @change="updateZonedDateTimeField('dataHoraFim', $event)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="evento-area">Area</label>
                        <select class="form-control" id="evento-area" name="area" v-model="evento.area">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="evento.area && areaOption.id === evento.area.id ? evento.area : areaOption" v-for="areaOption in areas" :key="areaOption.id">{{areaOption.id}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="evento-camara">Camara</label>
                        <select class="form-control" id="evento-camara" name="camara" v-model="evento.camara">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="evento.camara && camaraOption.id === evento.camara.id ? evento.camara : camaraOption" v-for="camaraOption in camaras" :key="camaraOption.id">{{camaraOption.id}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="evento-tipoevento">Tipoevento</label>
                        <select class="form-control" id="evento-tipoevento" name="tipoevento" v-model="evento.tipoevento">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="evento.tipoevento && tipoeventoOption.id === evento.tipoevento.id ? evento.tipoevento : tipoeventoOption" v-for="tipoeventoOption in tipoeventos" :key="tipoeventoOption.id">{{tipoeventoOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span>Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.evento.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./evento-update.component.ts">
</script>
