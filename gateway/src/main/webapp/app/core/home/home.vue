<template>
    <div class="home row">
        <div class="col-md-3">
            <!--<img src="../../../content/images/drone1.gif" style="margin-top: -50px; margin-left: 25px;">-->
            <img src="../../../content/images/img_prison.jpg" style="width: 375px; margin-top: 30px;">
        </div>
        <div class="col-md-9">
            <!--<img src="../../../content/images/drone 3.gif" style="float: right; width: 200px; margin-top: -60px;">-->

            <h1 class="display-4" style="font-weight: bold;">PrisionTech!</h1>
            <p class="lead" style="font-size: 30px;">Bem-vindo...
                <!--<img src="../../../content/images/drone 2.gif" style="width: 100px;">-->
            </p>
            <!--<p style="font-weight: bold; color: darkred; font-size: 18px;">PrisionTech - vá preso...em segurança!!!</p>-->

            <div>
                <div class="alert alert-success" v-if="authenticated">
                    <span v-if="username">Voçê está autenticado como "{{username}}"</span>
                </div>

                <div class="alert alert-warning" v-if="!authenticated">
                    <span>Para fazer </span>
                    <a class="alert-link" v-on:click="openLogin()">login</a><span >, experimente uma das contas existente por defeito:<br/>- Administrator (login="admin" and password="admin") <br/>- User (login="user" and password="user").</span>
                </div>
                <div class="alert alert-warning" v-if="!authenticated">
                    <span>Ainda não tem uma conta de utilizador?</span>&nbsp;
                    <router-link class="alert-link" to="/register">Criar uma nova conta de utilizador</router-link>
                </div>
            </div>

        </div>

    </div>
</template>

<script lang="ts" src="./home.component.ts">
</script>
