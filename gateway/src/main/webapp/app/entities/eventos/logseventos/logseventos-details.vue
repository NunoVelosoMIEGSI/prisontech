<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="logseventos">
                <h2 class="jh-entity-heading"><span v-text="$t('gatewayApp.eventosLogseventos.detail.title')">Logseventos</span> {{logseventos.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('gatewayApp.eventosLogseventos.dataHoraInicio')">Data Hora Inicio</span>
                    </dt>
                    <dd>
                        <span v-if="logseventos.dataHoraInicio">{{$d(Date.parse(logseventos.dataHoraInicio), 'long') }}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('gatewayApp.eventosLogseventos.dataHoraFim')">Data Hora Fim</span>
                    </dt>
                    <dd>
                        <span v-if="logseventos.dataHoraFim">{{$d(Date.parse(logseventos.dataHoraFim), 'long') }}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('gatewayApp.eventosLogseventos.classeOrigem')">Classe Origem</span>
                    </dt>
                    <dd>
                        <span>{{logseventos.classeOrigem}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('gatewayApp.eventosLogseventos.metodoOrigem')">Metodo Origem</span>
                    </dt>
                    <dd>
                        <span>{{logseventos.metodoOrigem}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="logseventos.id" :to="{name: 'LogseventosEdit', params: {logseventosId: logseventos.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./logseventos-details.component.ts">
</script>
