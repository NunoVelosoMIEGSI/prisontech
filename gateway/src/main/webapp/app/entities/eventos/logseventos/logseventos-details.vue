<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="logseventos">
                <h2 class="jh-entity-heading"><span>Logseventos</span> {{logseventos.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span>Data Hora Inicio</span>
                    </dt>
                    <dd>
                        <span>{{logseventos.dataHoraInicio | formatDate}}</span>
                    </dd>
                    <dt>
                        <span>Data Hora Fim</span>
                    </dt>
                    <dd>
                        <span>{{logseventos.dataHoraFim | formatDate}}</span>
                    </dd>
                    <dt>
                        <span>Classe Origem</span>
                    </dt>
                    <dd>
                        <span>{{logseventos.classeOrigem}}</span>
                    </dd>
                    <dt>
                        <span>Metodo Origem</span>
                    </dt>
                    <dd>
                        <span>{{logseventos.metodoOrigem}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
                </button>
                <router-link v-if="logseventos.id" :to="{name: 'LogseventosEdit', params: {logseventosId: logseventos.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./logseventos-details.component.ts">
</script>
