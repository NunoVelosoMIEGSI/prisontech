<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="area">
                <h2 class="jh-entity-heading"><span>Área</span> {{area.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span>Nome</span>
                    </dt>
                    <dd>
                        <span>{{area.nome}}</span>
                    </dd>
                    <dt>
                        <span>Número minimo de pessoas</span>
                    </dt>
                    <dd>
                        <span>{{area.numMinPessoa}}</span>
                    </dd>
                    <dt>
                        <span>Número máximo de pessoas</span>
                    </dt>
                    <dd>
                        <span>{{area.numMaxPessoa}}</span>
                    </dd>
                    <dt>
                        <span>Limite área</span>
                    </dt>
                    <dd>
                        <span>{{area.limiteArea}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Voltar</span>
                </button>
                <router-link v-if="area.id" :to="{name: 'AreaEdit', params: {areaId: area.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Editar</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./area-details.component.ts">
</script>
