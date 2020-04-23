<template>
    <div class="home row">
        <div class="col-md-3">
            <!--<img src="../../../content/images/drone1.gif" style="margin-top: -50px; margin-left: 25px;">-->
            <img src="../../../content/images/img_prison.jpg" style="width: 375px; margin-top: 30px;">
        </div>
        <div class="col-md-9">
            <h1 class="display-4" v-text="$t('home.title')">Welcome, Java Hipster!</h1>
            <p class="lead" v-text="$t('home.subtitle')">This is your homepage</p>

            <div>
                <div class="alert alert-success" v-if="authenticated">
                    <span v-if="username" v-text="$t('home.logged.message', { 'username': username})">You are logged in as user "{{username}}"</span>
                </div>

                <div v-if="authenticated">
                    <br>
                    <br>                    
                    <h2 id="page-heading">
                        <span v-text="$t('global.menu.entities.eventosEvento')" id="evento-heading">Eventos</span>
                    </h2>
                    <br/>
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <!--<th><span v-text="$t('global.field.id')">ID</span></th>-->
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
                            <tr v-for="evento in eventos"
                                :key="evento.id">
                                <!--<td>
                                    <router-link :to="{name: 'EventoView', params: {eventoId: evento.id}}">{{evento.id}}</router-link>
                                </td>-->
                                <!--<td>{{evento.descricao}}</td>-->
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

                <div class="alert alert-warning" v-if="!authenticated">
                    <span v-text="$t('global.messages.info.authenticated.prefix')">If you want to </span>
                    <a class="alert-link" v-on:click="openLogin()" v-text="$t('global.messages.info.authenticated.link')">sign in</a><span v-html="$t('global.messages.info.authenticated.suffix')">, you can try the default accounts:<br/>- Administrator (login="admin" and password="admin") <br/>- User (login="user" and password="user").</span>
                </div>
                <div class="alert alert-warning" v-if="!authenticated">
                    <span v-text="$t('global.messages.info.register.noaccount')">You don't have an account yet?</span>&nbsp;
                    <router-link class="alert-link" to="/register" v-text="$t('global.messages.info.register.link')">Register a new account</router-link>
                </div>
            </div>

        </div>
    </div>
</template>

<script lang="ts" src="./home.component.ts">
</script>
