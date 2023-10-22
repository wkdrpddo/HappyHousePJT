<template>
  <div>
    <!-- Search Start -->
    <div class="container-fluid mb-5 wow fadeIn" data-wow-delay="0.1s" style="padding: 35px; background-color: rgb(0, 185, 142)">
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
            <button style="background-color:rgb(14, 46, 80)" class="btn text-white border-0 w-100 py-3" @click="searchMap">Search</button>
          </div>
        </div>
      </div>
    </div>
    <!-- Search End -->

    <!-- About Start -->
    <div class="container-xxl py-5">
      <div class="container">
        <div class="row g-5 align-items-center">
          <div class="col-lg-8 wow fadeIn" data-wow-delay="0.1s">
            <div class="about-img position-relative overflow-hidden p-5 pe-0">
              <div class="map">
                <div id="map" style="width: 100%; height: 100%"></div>
              </div>
            </div>
          </div>
          <div class="col-lg-4 wow fadeIn" data-wow-delay="0.5s">
            <div class="col-8" style="margin:auto;">
            <h1 class="mb-4"><p>검색한 지역의</p> 정보입니다.</h1>
            <p style="font-size:20px; float:left;">
              <i class="fa fa-check me-3" style="color: rgb(0, 185, 142)" ></i>광역시/도 : {{sidoName}}
            </p>
            <p style="font-size:20px; float:left;">
              <i class="fa fa-check me-3" style="color: rgb(0, 185, 142)"></i>시/군/구 : {{gugunName}}
            </p>
            <p style="font-size:20px; float:left;">
              <i class="fa fa-check me-3" style="color: rgb(0, 185, 142)"></i>동/읍/면 : {{dongName}}
            </p>
            <p style="font-size:20px; float:left;">
              <i class="fa fa-check me-3" style="color: rgb(0, 185, 142)"></i>검색 결과 수 : {{resultCount}} 건
            </p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- About End -->

    <!-- Property List Start -->
    <div class="container-xxl py-5">
      <div class="container">
        <div class="row g-0 gx-5 align-items-end">
          <div class="col-lg-6">
            <div class="text-start mx-auto mb-5 wow slideInLeft" data-wow-delay="0.1s">
              <h1 class="mb-3">거래정보</h1>
              <p style="font-size:20px">현재 지역 기준 아파트 거래정보 리스트입니다.</p>
            </div>
          </div>
          <div class="col-lg-6 text-start text-lg-end wow slideInRight" data-wow-delay="0.1s"></div>
        </div>
        <div class="tab-content">
          <div id="tab-1" class="tab-pane fade show p-0 active">
            <div class="row g-4">
              <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.1s" v-for="(aptList, index) in apartList"
                :key="index">
                <dong-list :apartInfo="aptList"></dong-list>
              </div>
            </div>
          </div>
          <div id="tab-2" class="tab-pane fade show p-0">
            <div class="row g-4">
              <div v-for="(aptList, index) in apartList" :key="index">
                <dong-list :apartInfo="aptList"></dong-list>
              </div>
            </div>
          </div>
          <div id="tab-3" class="tab-pane fade show p-0">
            <div class="row g-4">
              <div v-for="(aptList, index) in apartList" :key="index">
                <dong-list :apartInfo="aptList"></dong-list>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- Property List End -->
  </div>
</template>

<script>
import http from "@/common/axios.js";
import kakaoMap from "@/common/kakaoMap.js";
import DongList from "@/components/DongList.vue";

export default {
  components: { "dong-list": DongList },
  data() {
    return {
      map: "",
      apartList: {},
      apartInfo: {
        name: "",
        date: "",
      },
      sidoName: "",
      gugunName: "",
      dongName: "",
      resultCount: ""
    };
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
    getHouseDealList() {
      console.log(this.$store.state.address.dong);
    },
    async searchMap() {
      var sl = document.getElementById("sl");
      var sl2 = document.getElementById("sl2");
      var sl3 = document.getElementById("sl3");

      var sl_data = this.$store.state.address.sido;
      var sl2_data = this.$store.state.address.gugun;
      var sl3_data = this.$store.state.address.dong;
      if (sl_data == 0 || sl2_data == 0 || sl3_data == 0) {
        alert("지역을 동 까지 다 선택해주세요");
      } else {
        sl3_data = sl3.options[sl3.selectedIndex].innerText.replace(" ","");
        this.dongName = sl3_data;
        this.gugunName = sl2.options[sl2.selectedIndex].innerText.replace(" ","");
        this.sidoName = sl.options[sl.selectedIndex].innerText.replace(" ","");
        console.log(sl3_data);
        let response = await http.get("/RecentDong/" + sl3_data);
        let { data } = response;
        this.apartList = data;
        console.log(data);
        this.resultCount = data.length;
        this.makeApartList(data);
      }
    },
    async searchMapFromMain() {
      var sl = document.getElementById("sl");
      var sl2 = document.getElementById("sl2");
      var sl3 = document.getElementById("sl3");

      var sl_data = this.$store.state.address.sido;
      var sl2_data = this.$store.state.address.gugun;
      var sl3_data = this.$store.state.address.dong;
      if (sl_data == 0 || sl2_data == 0 || sl3_data == 0) {
        //alert("지역을 동 까지 다 선택해주세요");
        this.getSidoList();
      } else {
        sl3_data = sl3.options[sl3.selectedIndex].innerText.replace(" ","");
        this.dongName = sl3_data;
        this.gugunName = sl2.options[sl2.selectedIndex].innerText.replace(" ","");
        this.sidoName = sl.options[sl.selectedIndex].innerText.replace(" ","");
        console.log(sl3_data);
        let response = await http.get("/RecentDong/" + sl3_data);
        let { data } = response;
        this.apartList = data;
        console.log(data);
        this.resultCount = data.length;
        this.makeApartList(data);
      }
    },
    async makeApartList(list) {
      var positions = [];
      console.log(list);
      var x = "";
      var y = "";
      for (var i = 0; i < list.length; i++) {
        var apart_name = list[i].AptName;

        let response = await http.get("/InfoApt/" + apart_name);
        console.log(response);
        let { data } = response;

        console.log(data);

        var dong = list[i].dong;
        var date = list[i].dealYear + "-" + list[i].dealMonth;
        var lat = data[0].lat;
        var lng = data[0].lng;
        x = lat;
        y = lng;

        positions.push({
          title: apart_name,
          latlng: new kakao.maps.LatLng(lat, lng),
        });
      }
      this.map = kakaoMap.loadMap(document.getElementById("map"), x, y, 5);
      this.mapfunc(positions);
    },
    mapfunc(positions) {
      //마커 이미지의 이미지 주소입니다
      console.log(positions);
      var imageSrc =
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

      for (var i = 0; i < positions.length; i++) {
        // 마커 이미지의 이미지 크기 입니다
        var imageSize = new kakao.maps.Size(24, 35);

        // 마커 이미지를 생성합니다
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
          map: this.map, // 마커를 표시할 지도
          position: positions[i].latlng, // 마커를 표시할 위치
          title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          image: markerImage, // 마커 이미지
        });
      }
    },
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
      this.getHouseDealList();
    },
  },
  created() {
    this.searchMapFromMain();
  },
  mounted() {
    let x = 37.50395942713657;
    let y = 127.04282459768903;
    this.map = kakaoMap.loadMap(document.getElementById("map"), x, y, 5);
  },
};
</script>

<style>
* {
  margin: 0;
  padding: 0;
  list-style: none;
}

.header {
  height: 100px;
}

.select {
  max-width: 1200px;
  margin: auto;
  width: calc(100% - 120px);
  background-color: rgb(77, 77, 77);
}

.do {
  display: inline-block;
  height: 70px;
  line-height: 70px;
  margin-left: 500px;
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

select {
  padding: 5px;
  background-color: gray;
  color: white;
}

.content {
  margin: auto;
  max-width: 1200px;
}

.content-left {
  width: 25%;
  float: left;
}

.info {
  padding: 5px;
  height: 500px;
}

.content-right {
  width: 70%;
  height: 70%;
  float: right;
}

/* 지도 */
.map {
  /* border: 1px solid red; */
  margin: 10px 5px;
  padding: 5px;
  /* max-width: 900px; */
  height: 500px;
}

.menu-item-search {
  display: inline-block;
  height: 90px;
  line-height: 90px;
  margin-left: 20px;
}
</style>
