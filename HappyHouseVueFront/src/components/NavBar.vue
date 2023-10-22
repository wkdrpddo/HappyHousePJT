<template>
    <div class="container-fluid nav-bar bg-transparent">
        <nav class="navbar navbar-expand-lg bg-white navbar-light py-0 px-4">
            <router-link to="/MainPage" class="navbar-brand d-flex align-items-center text-center">
                <div class="icon p-2 me-2">
                    <img class="img-fluid" src="@/img/icon-apartment.png" alt="Icon" style="width: 30px; height: 30px;">
                </div>
                <h1 style="color: rgb(0, 185, 142)" class="m-0">Happy House</h1>
            </router-link>
            <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <div class="navbar-nav ms-auto">
                    <router-link to="/MainPage" class="nav-item nav-link active">Home</router-link>
                    <router-link to="/DongSearchPage" class="nav-item nav-link">지역 정보 검색</router-link>
                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">게시판</a>
                        <div class="dropdown-menu rounded-0 m-0">
                            <router-link to="/freeboard" class="dropdown-item">자유게시판</router-link>
                            <router-link to="/noticeboard" class="dropdown-item">공지사항</router-link>
                        </div>
                    </div>
                    <router-link v-show="!isLoginGetters" to="/regist" class="nav-item nav-link">REGIST</router-link>
                    <router-link v-show="isLoginGetters" class="nav-item nav-link" to="/userinfo">INFO</router-link>
                </div>
                <div v-show="!isLoginGetters" style="cursor=pointer; background-color: rgb(0, 185, 142)" @click="showLoginModal" class="btn px-3 text-white">Login</div>
                <login-modal v-on:call-parent-login="closeAfterLogin" v-on:call-parent-cancel="cancelAfterLogin"></login-modal>
                <div v-show="isLoginGetters" style="cursor=pointer; background-color: rgb(0, 185, 142)" @click="logout" class="btn px-3 text-white">Logout</div>
            </div>
        </nav>
    </div>
</template>

<script>
import loginModal from './modals/LoginModal.vue';

import { Modal } from 'bootstrap';

import http from "@/common/axios.js";

export default {
    components: {loginModal},
    methods: {
        showLoginModal() {
            this.loginModal.show();
        },
        closeAfterLogin(userInfo) {
            this.loginModal.hide();
        },
        cancelAfterLogin() {
            this.loginModal.hide();
        },
        async logout() {
            sessionStorage.clear();

            http.get("/logout");
            console.log("logout");

            this.$store.commit('SET_LOGOUT', '');
            this.$router.push('/MainPage');
        },
    },
    computed: {
        isLoginGetters() {
            return this.$store.getters.isLogin;
        },
    },
    mounted() {
        this.loginModal = new Modal( document.querySelector('#loginModal'))
    },
}
</script>

<style>
</style>