<template>
      <div class="property-item rounded overflow-hidden">
        <div class="position-relative overflow-hidden">
          <div><img @click="detailSelect(apartInfo.AptName)" class="img-fluid" src="@/img/property-1.jpg" alt="" /></div>
          
          <div class="bg-white rounded-top text-primary position-absolute start-0 bottom-0 mx-4 pt-1 px-3">
            Appartment
          </div>
        </div>
        <div class="p-4 pb-0">
          <h5 class="text-primary mb-3">{{apartInfo.dealAmount}}</h5>
          <a class="d-block h5 mb-2" @click="detailSelect(apartInfo.AptName)">{{apartInfo.AptName}}</a>
          <p>
            <i class="fa fa-map-marker-alt text-primary me-2"></i>{{apartInfo.dong}} {{apartInfo.jibun}}
          </p>
        </div>
      </div>
</template>

<script>
import http from "@/common/axios.js";
import aptDetails from "@/components/AptDetailPage.vue";

export default {
  props: ["apartInfo"],
  components: {"apartDetails": aptDetails},
  data() {
    return {
       name: ''
    };
  },
  methods: {
    detailSelect(name) {
      console.log(name);
      this.$store.commit('SET_APARTMENT_NAME', name);
      console.log("send aptName : " + this.$store.getters.getApartName);
      this.$router.push("/AptSearchPage");
    },
  }
};
</script>

<style>
</style>
