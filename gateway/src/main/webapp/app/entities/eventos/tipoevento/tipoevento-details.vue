<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="tipoevento">
                <h2 class="jh-entity-heading"><span>Tipoevento</span> {{tipoevento.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span>Descricao</span>
                    </dt>
                    <dd>
                        <span>{{tipoevento.descricao}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
                </button>
                <router-link v-if="tipoevento.id" :to="{name: 'TipoeventoEdit', params: {tipoeventoId: tipoevento.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./tipoevento-details.component.ts">
</script>
