<template>
    <div class="modal col-md-6" tabindx="2" id="loginModal">
        <div class="modal-dialog wow fadeInUp" data-wow-delay="0.5s">

            <div class="modal-content row g-3">
                <div class="modal-header col-md-6">
                    <h3 class="modal-title">Login</h3>
                </div>
                <div class="modal-body">

                    <div class="col-12 mb-3">
                        <div class="form-floating">
                            <input type="text" class="form-control" id="idForm" v-model="$store.state.login.id" placeholder="Input Your ID">
                            <label for="idForm">Your ID</label>
                        </div>
                    </div>
                    <div class="col-12">
                        <div class="form-floating">
                            <input type="password" class="form-control" id="pwForm" v-model="$store.state.login.password" placeholder="Input Your Password">
                            <label for="pwForm">Password</label>
                        </div>
                    </div>

                </div>

                <div class="modal-footer col-12">
                    <button @click="login" style="background-color: rgb(0, 185, 142)"  class="btn text-white">로그인</button>

                    <img type="button" @click="loginKakao" src="../kakaos/kakao_login.png">
                </div>
            </div>

        </div>
    </div>
</template>

<script>
import Vue from "vue";
import VueAlertify from "vue-alertify";

Vue.use(VueAlertify);

import { KAKAO_AUTH_URL } from "../kakaos/OAuth.js";

export default {
    data() {
        return {
            KAKAO_AUTH_URL,
        }
    },

    methods: {
        loginKakao() {
            window.location.href = this.KAKAO_AUTH_URL;
        },

        closeModal() {
            console.log('login-cancel');
            this.$emit('call-parent-cancel');
        },
        async login() {
            this.$emit('call-parent-cancel');
            this.$store.dispatch('login')
                .then(response=> {
                    this.$alertify.success('로그인에 성공했습니다.');
                }, error => {
                    this.$alertify.error('로그인에 실패했습니다.');
                });
        },
    },
}
</script>

<style>

</style>