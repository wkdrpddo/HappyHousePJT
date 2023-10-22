<template>
  <div id="app">
    <nav-bar class="container"></nav-bar>
    <div class="container bg-white">
      <router-view></router-view>
    </div>
    <page-footer></page-footer>
  </div>
</template>

<script>
import NavBar from '@/components/NavBar.vue';
import PageFooter from "@/components/PageFooter.vue";

export default {
  name: 'App',
  components: {
    NavBar,
    PageFooter,
  },
  created() {
    let dto = sessionStorage.getItem("userDto");
    if(dto != null) {
      this.$store.commit("SET_LOGIN", dto);
    }
  },
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
  background-color: rgb(239, 253, 245);
}
</style>
