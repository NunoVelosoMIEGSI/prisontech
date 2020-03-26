<template>
    <div class="modal-body">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <b-alert show variant="danger" v-if="authenticationError" >
                    <strong>Não foi possível efetuar login!</strong> Verifique por favor as suas credenciais e tente novamente.
                </b-alert>
            </div>
            <div class="col-md-8">
                <b-form role="form" v-on:submit.prevent="doLogin()">
                    <b-form-group label="Username"  label-for="username">
                        <b-form-input id="username" type="text" name="username" autofocus  placeholder="Introduza o seu username" v-model="login">
                        </b-form-input>
                    </b-form-group>
                    <b-form-group label="Password"  label-for="password">
                        <b-form-input id="password" type="password" name="password" v-model.trim="name"  placeholder="Introduza a sua password" v-model="password">
                        </b-form-input>
                    </b-form-group>
                    <b-form-checkbox id="rememberMe" name="rememberMe" v-model="rememberMe" checked >
                      <span>Lembrar-me</span>
                    </b-form-checkbox>
                    <div>
                        <b-button type="submit" variant="primary">Login</b-button>
                    </div>
                </b-form>
                <p></p>
                <div style="width: 290px; margin-left: -5px;">
                    <b-alert show variant="warning">
                        <b-link :to="'/account/reset/request'" class="alert-link">Esqueceu-se da sua password?</b-link>
                    </b-alert>
                </div>
                <div style="width: 290px; margin-left: -5px;">
                    <b-alert show variant="warning">
                      <span>Ainda não tem conta de utilizador?</span>
                      <b-link :to="'/register'" class="alert-link">Registar um novo utilizador</b-link>
                    </b-alert>
                </div>
            </div>
        </div>
    </div>
</template>
<script lang="ts" src="./login-form.component.ts">
</script>
