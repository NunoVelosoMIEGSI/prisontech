<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="evento">
                <h2 class="jh-entity-heading"><span v-text="$t('gatewayApp.eventosEvento.detail.title')">Evento</span> {{evento.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('gatewayApp.eventosEvento.descricao')">Descricao</span>
                    </dt>
                    <dd>
                        <span>{{evento.descricao}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('gatewayApp.eventosEvento.numPessoasPerm')">Num Pessoas Perm</span>
                    </dt>
                    <dd>
                        <span>{{evento.numPessoasPerm}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('gatewayApp.eventosEvento.numPessoasDet')">Num Pessoas Det</span>
                    </dt>
                    <dd>
                        <span>{{evento.numPessoasDet}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('gatewayApp.eventosEvento.dataHoraInicio')">Data Hora Inicio</span>
                    </dt>
                    <dd>
                        <span v-if="evento.dataHoraInicio">{{$d(Date.parse(evento.dataHoraInicio), 'long') }}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('gatewayApp.eventosEvento.dataHoraFim')">Data Hora Fim</span>
                    </dt>
                    <dd>
                        <span v-if="evento.dataHoraFim">{{$d(Date.parse(evento.dataHoraFim), 'long') }}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('gatewayApp.eventosEvento.path')">Path</span>
                    </dt>
                    <dd>
                        <span>{{evento.path}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('gatewayApp.eventosEvento.formato')">Formato</span>
                    </dt>
                    <dd>
                        <span>{{evento.formato}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('gatewayApp.eventosEvento.area')">Area</span>
                    </dt>
                    <dd>
                        <div v-if="evento.area">
                            <router-link :to="{name: 'AreaView', params: {areaId: evento.area.id}}">{{evento.area.nome}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('gatewayApp.eventosEvento.camara')">Camara</span>
                    </dt>
                    <dd>
                        <div v-if="evento.camara">
                            <router-link :to="{name: 'CamaraView', params: {camaraId: evento.camara.id}}">{{evento.camara.descricao}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('gatewayApp.eventosEvento.tipoevento')">Tipoevento</span>
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
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="evento.id" :to="{name: 'EventoEdit', params: {eventoId: evento.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./evento-details.component.ts">
</script>
