<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="evento">
                <h2 class="jh-entity-heading"><span>Evento ocorrido</span> {{evento.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span>Descrição</span>
                    </dt>
                    <dd>
                        <span>{{evento.descricao}}</span>
                    </dd>
                    <dt>
                        <span>Número de pessoas permitidas</span>
                    </dt>
                    <dd>
                        <span>{{evento.numPessoasPerm}}</span>
                    </dd>
                    <dt>
                        <span>Número de pessoas detetadas</span>
                    </dt>
                    <dd>
                        <span>{{evento.numPessoasDet}}</span>
                    </dd>
                    <dt>
                        <span>Data/Hora de início</span>
                    </dt>
                    <dd>
                        <span>{{evento.dataHoraInicio | formatDate}}</span>
                    </dd>
                    <dt>
                        <span>Data/Hora de fim</span>
                    </dt>
                    <dd>
                        <span>{{evento.dataHoraFim | formatDate}}</span>
                    </dd>
                    <dt>
                        <span>Área</span>
                    </dt>
                    <dd>
                        <div v-if="evento.area">
                            <router-link :to="{name: 'AreaView', params: {areaId: evento.area.id}}">{{evento.area.nome}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span>Câmara</span>
                    </dt>
                    <dd>
                        <div v-if="evento.camara">
                            <router-link :to="{name: 'CamaraView', params: {camaraId: evento.camara.id}}">{{evento.camara.descricao}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span>Tipo de evento</span>
                    </dt>
                    <dd>
                        <div v-if="evento.tipoevento">
                            <router-link :to="{name: 'TipoeventoView', params: {tipoeventoId: evento.tipoevento.id}}">{{evento.tipoevento.descricao}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Voltar</span>
                </button>
                <router-link v-if="evento.id" :to="{name: 'EventoEdit', params: {eventoId: evento.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Editar</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./evento-details.component.ts">
</script>
