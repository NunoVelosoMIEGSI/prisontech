<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="gatewayApp.eventosLogseventos.home.createOrEditLabel">Create or edit a Logseventos</h2>
                <div>
                    <div class="form-group" v-if="logseventos.id">
                        <label for="id">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="logseventos.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="logseventos-dataHoraInicio">Data Hora Inicio</label>
                        <div class="d-flex">
                            <input id="logseventos-dataHoraInicio" type="datetime-local" class="form-control" name="dataHoraInicio" :class="{'valid': !$v.logseventos.dataHoraInicio.$invalid, 'invalid': $v.logseventos.dataHoraInicio.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.logseventos.dataHoraInicio.$model)"
                            @change="updateZonedDateTimeField('dataHoraInicio', $event)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="logseventos-dataHoraFim">Data Hora Fim</label>
                        <div class="d-flex">
                            <input id="logseventos-dataHoraFim" type="datetime-local" class="form-control" name="dataHoraFim" :class="{'valid': !$v.logseventos.dataHoraFim.$invalid, 'invalid': $v.logseventos.dataHoraFim.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.logseventos.dataHoraFim.$model)"
                            @change="updateZonedDateTimeField('dataHoraFim', $event)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="logseventos-classeOrigem">Classe Origem</label>
                        <input type="text" class="form-control" name="classeOrigem" id="logseventos-classeOrigem"
                            :class="{'valid': !$v.logseventos.classeOrigem.$invalid, 'invalid': $v.logseventos.classeOrigem.$invalid }" v-model="$v.logseventos.classeOrigem.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="logseventos-metodoOrigem">Metodo Origem</label>
                        <input type="text" class="form-control" name="metodoOrigem" id="logseventos-metodoOrigem"
                            :class="{'valid': !$v.logseventos.metodoOrigem.$invalid, 'invalid': $v.logseventos.metodoOrigem.$invalid }" v-model="$v.logseventos.metodoOrigem.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span>Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.logseventos.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./logseventos-update.component.ts">
</script>
