<template>
  <div class="container-xxl bg-white p-0">
    <!-- 중앙 content start -->
    <div class="content">
      <!-- 중앙 top content start -->
      <div class="content-top">
        <div class="content-top-img">
          <img src="@/assets/img/homeBack.jpg" alt="" />
        </div>

        <div class="happyhouse">
          <h1 class="display-5 animated fadeIn mb-4">WELCOME! Take <span style="color: rgb(0, 185, 142)">Apartment
              Information</span> and Surroundings Info!!</h1>
          <hr>
          <h3>아파트 정보 및 주변 환경 커뮤니티 </h3>
        </div>

        <div  class="container-fluid mb-5 wow fadeIn" data-wow-delay="0.1s" style="padding: 35px; background-color: rgb(0, 185, 142)">
          <div class="container">
            <div class="row g-2">
              <div class="col-md-10">
                <div class="row g-2">
                  <div class="col-md-4">
                    <select class="form-select border-0 py-3" id="sl" v-model="sido">
                      <option value="0">시/도를 선택하세요.</option>
                      <option v-for="(sl, index) in sidoList" :value="sl.code" :key="index">
                        {{ sl.name }}
                      </option>
                    </select>
                  </div>
                  <div class="col-md-4">
                    <select class="form-select border-0 py-3" id="sl2" v-model="gugun">
                      <option value="0">구/군을 선택하세요.</option>
                      <option v-for="(sl2, index) in gugunList" :value="sl2.code" :key="index">
                        {{ sl2.name }}
                      </option>
                    </select>
                  </div>
                  <div class="col-md-4">
                    <select class="form-select border-0 py-3" id="sl3" v-model="dong">
                      <option value="0">읍/면/동을 선택하세요.</option>
                      <option v-for="(sl3, index) in dongList" :value="sl3.code" :key="index">
                        {{ sl3.name }}
                      </option>
                    </select>
                  </div>
                </div>
              </div>
              <div class="col-md-2">
                <button style="background-color:rgb(14, 46, 80)" class="btn border-0 w-100 py-3 text-white" @click="dongsearch">Search</button>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- 중앙 top content end -->

      <!-- 중앙 middle1 content start -->
      <div class="container-xxl py-5">
        <div class="container">
          <div class="row g-5 align-items-center">
            <div class="col-lg-6 wow fadeIn" data-wow-delay="0.1s">
              <div class="about-img position-relative overflow-hidden p-5 pe-0">
                <img class="img-fluid w-100" src="@/img/about.jpg">
              </div>
            </div>
            <div class="col-lg-6 wow fadeIn" data-wow-delay="0.5s">
              <h1 class="mb-4">원하는 정보를 찾으세요!!</h1>
              <p class="mb-4">여러분은 이 곳에서 최근 아파트 거래 정보, 주변 시설 정보, 실제 거주자들의 자유로운 게시글들을 확인하실 수 잇습니다!</p>
              <div class="text-start ms-5 ps-5">
                <p><i style="color: rgb(0, 185, 142)" class="fa fa-check text-start me-3"></i>우선 회원가입을 하세요!</p>
                <p><i style="color: rgb(0, 185, 142)" class="fa fa-check text-start me-3"></i>원하는 지역을 검색하실 수 있습니다!</p>
                <p><i style="color: rgb(0, 185, 142)" class="fa fa-check text-start me-3"></i>공지사항을 확인하세요!!</p>
              </div>
              <router-link to="/noticeboard" style="background-color:rgb(0, 185, 142)"
                class="btn py-3 px-5 mt-3 text-white">To Notice</router-link>
            </div>
          </div>
        </div>
      </div>
      <!-- 중앙 middle1 content end -->

      <!-- 중앙 middle2 content start -->
      <div class="container-xxl py-5">
        <div class="container">
          <div class="text-center mx-auto mb-5 wow fadeInUp" data-wow-delay="0.1s" style="max-width: 600px;">
            <h1 class="mb-3">Site Operators</h1>
            <p>Happy House Web의 개발 및 운영진을 소개합니다.</p>
          </div>
          <div class="row g-4">
            <div class="col-lg-6 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
              <div class="team-item rounded overflow-hidden">
                <div class="position-relative">
                  <img class="img-fluid" src="@/assets/img/members/son.jpg" alt="">
                  <div class="position-absolute start-50 top-100 translate-middle d-flex align-items-center">
                    <a class="btn btn-square mx-1" href=""><i class="fab fa-facebook-f"></i></a>
                    <a class="btn btn-square mx-1" href=""><i class="fab fa-twitter"></i></a>
                    <a class="btn btn-square mx-1" href=""><i class="fab fa-instagram"></i></a>
                  </div>
                </div>
                <div class="text-center p-4 mt-3">
                  <h5 class="fw-bold mb-0">손준혁</h5>
                  <small>Team Leader</small>
                </div>
              </div>
            </div>

            <div class="col-lg-6 col-md-6 wow fadeInUp" data-wow-delay="0.3s">
              <div class="team-item rounded overflow-hidden">
                <div class="position-relative">
                  <img class="img-fluid" src="@/assets/img/members/seo.jpg" alt="">
                  <div class="position-absolute start-50 top-100 translate-middle d-flex align-items-center">
                    <a class="btn btn-square mx-1" href=""><i class="fab fa-facebook-f"></i></a>
                    <a class="btn btn-square mx-1" href=""><i class="fab fa-twitter"></i></a>
                    <a class="btn btn-square mx-1" href=""><i class="fab fa-instagram"></i></a>
                  </div>
                </div>
                <div class="text-center p-4 mt-3">
                  <h5 class="fw-bold mb-0">서광석</h5>
                  <small>Team Member</small>
                </div>
              </div>
            </div>
          </div>

        </div>
      </div>
      <!-- 중앙 middle2 content end -->

    </div>


  </div>
</template>

<script>
import http from '@/common/axios.js';
import kakaoMap from '@/common/kakaoMap.js';

export default {
  data() {
    return {
      map: '',

      sidoName: "",
      gugunName: "",
      dongName: "",
      resultCount: ""
    }
  },
  methods: {
    getSidoList() {
      this.$store.dispatch("getSidoList");
    },
    getGugunList() {
      this.$store.dispatch("getGugunList");
    },
    getDongList() {
      this.$store.dispatch("getDongList");
    },
    changeSidoSelect() {
      this.searchGugun();
    },
    changeGugunSelect() {
      this.searchDong();
    },
    async searchGugun() {
      var sl = document.getElementById("sl");
      var sl_data = sl.options[sl.selectedIndex].value;
      console.log(sl_data);
      let response = await http.get("/search/sl2/" + sl_data);
      let { data } = response;
      console.log(data);
      this.appendGugun(data);
    },
    appendGugun(list) {
      this.listsl2 = [];
      this.listsl2.push({ value: -1, sl2: '시/군/구' });
      list.forEach(el => {
        this.listsl2.push({ value: el.code, sl2: el.name });
      });
    },
    async searchDong() {
      var sl2 = document.getElementById("sl2");
      var sl2_data = sl2.options[sl2.selectedIndex].value;
      let response = await http.get("/search/sl3/" + sl2_data);
      let { data } = response;
      this.appendDong(data);
    },
    appendDong(list) {
      this.listsl3 = [];
      this.listsl3.push({ value: -1, sl3: '동' });
      list.forEach(el => {
        this.listsl3.push({ value: el.code, sl3: el.name });
      });
    },
    dongsearch: function(){
      var sl = document.getElementById("sl");
      var sl2 = document.getElementById("sl2");
      var sl3 = document.getElementById("sl3");

      var sl_data = sl.options[sl.selectedIndex].value;
      var sl2_data = sl2.options[sl2.selectedIndex].value;
      var sl3_data = sl3.options[sl3.selectedIndex].value;

      if (sl_data == 0 || sl2_data == 0 || sl3_data == 0) {
        alert("지역을 동 까지 다 선택해주세요");
      }
      else {
        this.$router.push('/DongSearchPage');
      }
    }
  },
  computed: {
    sidoList() {
      return this.$store.getters.getSidoList;
    },
    gugunList() {
      return this.$store.getters.getGugunList;
    },
    dongList() {
      return this.$store.getters.getDongList;
    },
    sido: {
      get() {
        return this.$store.state.address.sido;
      },
      set(sido) {
        this.$store.commit("SET_ADDRESS_SIDO", sido);
      },
    },
    gugun: {
      get() {
        return this.$store.state.address.gugun;
      },
      set(gugun) {
        this.$store.commit("SET_ADDRESS_GUGUN", gugun);
      },
    },
    dong: {
      get() {
        return this.$store.state.address.dong;
      },
      set(dong) {
        this.$store.commit("SET_ADDRESS_DONG", dong);
      },
    },
  },
  watch: {
    sido: function () {
      console.log(this.$store.state.address.sido);
      if (this.$store.state.address != "0") this.getGugunList();
    },
    gugun: function () {
      console.log("watch : gugun() " + this.gugun);
      if (this.gugun != "0") this.getDongList();
    },
    dong: function () {
      console.log("watch : dong() " + this.dong);
      // this.getHouseDealList();
    },
  },
  created() {
    // console.log(this.$store.state.login.isKakao);
    this.$store.commit("SET_ADDRESS_SIDO", "0");
  },
  mounted() {
    this.getSidoList();
  }
}
</script>

<style>
* {
  margin: 0;
  padding: 0;
  list-style: none;
}

a {
  text-decoration: none;
  color: black;
}

/* select box 속성 설정 */
.select {
  background-color: rgb(77, 77, 77);
}

.do {
  display: inline-block;
  height: 70px;
  line-height: 70px;
  margin-left: 90px;
}

.si {
  display: inline-block;
  height: 70px;
  line-height: 70px;
}

.dong {
  display: inline-block;
  height: 70px;
  line-height: 70px;
}

.menu-item-search {
  display: inline-block;
  height: 90px;
  line-height: 90px;
  margin-left: 20px;
}

select {
  padding: 5px;
  background-color: gray;
  color: white;
}

/* 중앙 content 영역의 속성 설정 */
.content {
  margin: 10px auto;
  max-width: 1200px;
}

/* 중앙 top 영역의 속성 설정 */
.content-top {
  height: 35%;
  /* background-image: url("https://modo-phinf.pstatic.net/20170813_18/1502610893941BUcDz_JPEG/mosaeq2qra.jpeg?type=w1100");
    background-size: cover; */
  position: relative;
  text-align: center;
}

.content-top-img img {
  /* vertical-align: middle; */
  width: 100%;
  height: 300px;
  opacity: 0.6;
}

.happyhouse {
  line-height: 60px;
  position: absolute;
  top: 90px;
  text-align: center;
  width: 100%;
}

.happyhouse h1 {
  font-size: 45px;
  letter-spacing: 3px;
}

.happyhouse hr {
  width: 80%;
  border: 0.5px solid rgb(255, 255, 255);
  margin: auto;
}

/* 중앙 middle1 영역의 속성 설정 */
.content-middle1 {
  width: 100%;
  height: 35%;
}

/* 지도 */
.content-map {
  /* border: 1px solid red; */
  margin: 10px 5px;
  padding: 5px;
  /* max-width: 900px; */
  height: 150px;
}

/* 중앙 middle2 영역의 속성 설정 */
.content-middle2 {
  display: flex;
  padding-top: 30px;
  height: 20%;
  width: 98%;
  margin: auto;
}

/* 지도 밑 section 3개 */
/* .content-middle2 .ad {
  display: inline-block;
  width: 31%;
  margin: auto;
} */
/* .content-middle2 .ad img {
  width: 98%;
} */

.content-middle2 .article_tip {
  margin-left: 2%;
  margin-right: 2%;
  width: 31%;
}

.content-middle2 .article_news {
  width: 31%;
}

.content-middle2 h3 {
  font-size: 25px;
  font-weight: 400;
  margin-bottom: 5px;
}

.content-middle2 li {
  margin: 5px 0px;
  line-height: 30px;
  letter-spacing: -1px;
  font-size: 18px;

  height: 15%;
}

</style>
