<template>
    <div class="home row">
        <div class="col-md-3">
            <img src="../../../content/images/img_prison.jpg" style="width: 375px; margin-top: 30px;">
        </div>
        <div class="col-md-9">
            <div v-if="!authenticated">
            <h1 class="display-4" v-text="$t('home.title')">Welcome, Java Hipster!</h1>
            <p class="lead" v-text="$t('home.subtitle')">This is your homepage</p>
            </div>
            <div v-if="authenticated">
                <div>               
                    <h2 id="page-heading">
                        <span id="evento-heading">Eventos a Ocorrer</span>
                    </h2>
                    <br/>
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th><span v-text="$t('gatewayApp.eventosEvento.descricao')">Descricao</span></th>
                                <th style="width: 175px;"><span v-text="$t('gatewayApp.eventosEvento.numPessoasPerm')">Num Pessoas Perm</span></th>
                                <th style="width: 175px;"><span v-text="$t('gatewayApp.eventosEvento.numPessoasDet')">Num Pessoas Det</span></th>
                                <th><span v-text="$t('gatewayApp.eventosEvento.dataHoraInicio')">Data Hora Inicio</span></th>
                                <!-- <th><span v-text="$t('gatewayApp.eventosEvento.dataHoraFim')">Data Hora Fim</span></th> -->
                                <th><span v-text="$t('gatewayApp.eventosEvento.area')">Area</span></th>
                                <th><span v-text="$t('gatewayApp.eventosEvento.camara')">Camara</span></th>
                                <th><span v-text="$t('gatewayApp.eventosEvento.tipoevento')">Tipoevento</span></th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr v-for="evento in eventos" v-if="evento.dataHoraInicio==evento.dataHoraFim"
                                :key="evento.id">
                                <td>
                                    <router-link :to="{name: 'EventoView', params: {eventoId: evento.id}}">{{evento.descricao}}</router-link>
                                </td>
                                <td>{{evento.numPessoasPerm}}</td>
                                <td>{{evento.numPessoasDet}}</td>
                                <td v-if="evento.dataHoraInicio"> {{$d(Date.parse(evento.dataHoraInicio), 'short') }}</td>
                                <!-- <td v-if="evento.dataHoraFim"> {{$d(Date.parse(evento.dataHoraFim), 'short') }}</td> -->
                                <td>
                                    <div v-if="evento.area">
                                        <router-link :to="{name: 'AreaView', params: {areaId: evento.area.id}}">{{evento.area.nome}}</router-link>
                                    </div>
                                </td>
                                <td>
                                    <div v-if="evento.camara">
                                        <router-link :to="{name: 'CamaraView', params: {camaraId: evento.camara.id}}">{{evento.camara.descricao}}</router-link>
                                    </div>
                                </td>
                                <td>
                                    <div v-if="evento.tipoevento">
                                        <router-link :to="{name: 'TipoeventoView', params: {tipoeventoId: evento.tipoevento.id}}">{{evento.tipoevento.descricao}}</router-link>
                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <br>
                    <br>                    
                </div>

                    <div >               
                    <h2 id="page-heading">
                        <span v-text="$t('global.menu.entities.eventosEvento')" id="evento-heading">Eventos</span>
                    </h2>
                    <br/>
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th><span v-text="$t('gatewayApp.eventosEvento.descricao')">Descricao</span></th>
                                <th style="width: 175px;"><span v-text="$t('gatewayApp.eventosEvento.numPessoasPerm')">Num Pessoas Perm</span></th>
                                <th style="width: 175px;"><span v-text="$t('gatewayApp.eventosEvento.numPessoasDet')">Num Pessoas Det</span></th>
                                <th><span v-text="$t('gatewayApp.eventosEvento.dataHoraInicio')">Data Hora Inicio</span></th>
                                <th><span v-text="$t('gatewayApp.eventosEvento.dataHoraFim')">Data Hora Fim</span></th>
                                <th><span v-text="$t('gatewayApp.eventosEvento.area')">Area</span></th>
                                <th><span v-text="$t('gatewayApp.eventosEvento.camara')">Camara</span></th>
                                <th><span v-text="$t('gatewayApp.eventosEvento.tipoevento')">Tipoevento</span></th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr v-for="evento in eventos" v-if="evento.dataHoraInicio!=evento.dataHoraFim"
                                :key="evento.id">
                                <td>
                                    <router-link :to="{name: 'EventoView', params: {eventoId: evento.id}}">{{evento.descricao}}</router-link>
                                </td>
                                <td>{{evento.numPessoasPerm}}</td>
                                <td>{{evento.numPessoasDet}}</td>
                                <td v-if="evento.dataHoraInicio"> {{$d(Date.parse(evento.dataHoraInicio), 'short') }}</td>
                                <td v-if="evento.dataHoraFim"> {{$d(Date.parse(evento.dataHoraFim), 'short') }}</td>
                                <td>
                                    <div v-if="evento.area">
                                        <router-link :to="{name: 'AreaView', params: {areaId: evento.area.id}}">{{evento.area.nome}}</router-link>
                                    </div>
                                </td>
                                <td>
                                    <div v-if="evento.camara">
                                        <router-link :to="{name: 'CamaraView', params: {camaraId: evento.camara.id}}">{{evento.camara.descricao}}</router-link>
                                    </div>
                                </td>
                                <td>
                                    <div v-if="evento.tipoevento">
                                        <router-link :to="{name: 'TipoeventoView', params: {tipoeventoId: evento.tipoevento.id}}">{{evento.tipoevento.descricao}}</router-link>
                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <br>
                    <br>                    
                </div>
                
            </div>

        </div>
    </div>
</template>

<script lang="ts" src="./home.component.ts">
</script>
