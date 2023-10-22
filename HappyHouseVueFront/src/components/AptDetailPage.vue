<template>
  <div>
    <!-- Search Start -->
    <div class="container-fluid bg-primary mb-5 wow fadeIn" data-wow-delay="0.1s" style="padding: 35px">
      <div class="container">
        <div class="row g-2">
          <div class="col-md-8">
            <div class="row g-2">
              <div class="col-md-4">
                <select class="form-select border-0 py-3" id="categorySelector">
                  <option v-for="(cate, index) in cateList" :value="cate.value" :key="index">
                    {{ cate.name }}
                  </option>
                </select>
              </div>
            </div>
          </div>
          <div class="col-md-2">
            <button class="btn btn-dark border-0 w-100 py-3" @click="changeCategorySelection()">Search</button>
          </div>
          <div class="col-md-2">
            <button class="btn btn-light border-0 w-100 py-3" @click="movetoChartPage()">Price</button>
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
            <h1 class="mb-4" style="float:left;">Apart Info.</h1>
            <p style="font-size:20px; float:left;" >
              <i class="fa fa-check text-primary me-3"></i>아파트명 :
              {{ aptInfo.AptName }}
            </p>
            <p style="font-size:20px; float:left;">
              <i class="fa fa-check text-primary me-3"></i>건축연도 :
              {{ aptInfo.buildYear }}년
            </p>
            <p style="font-size:20px; float:left;">
              <i class="fa fa-check text-primary me-3"></i>위치 :
              {{ aptInfo.dong }}, {{ aptInfo.jibun }}
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
              <h1 class="mb-3">부대시설</h1>
              <p style="font-size:20px">
                현재 {{ aptName }}아파트 기준 주변 부대시설정보 리스트입니다.
              </p>
            </div>
          </div>
          <div class="col-lg-6 text-start text-lg-end wow slideInRight" data-wow-delay="0.1s"></div>
        </div>
        <div class="tab-content">
          <div class="row">
            <div class="col-4" v-for="(around, index) in aroundlist" :key="index">
              <around-comp :aroundInfo="around"></around-comp>
            </div>

          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from '@/common/axios.js';
import kakaoMap from '@/common/kakaoMap.js';
// import aptList from '@//components/AptList.vue'npm
import aroundList from '@//components/aroundList.vue'

export default {
  components: { 'around-comp': aroundList },
  data() {
    return {
      map: '',
      cateList: [
        { value: '-1', name: '카테고리' },
        { value: 'MT1', name: '대형마트' },
        { value: 'PS3', name: '어린이집' },
        { value: 'SC4', name: '학교' },
        { value: 'PK6', name: '주차장' },
        { value: 'OL7', name: '주유소' },
        { value: 'FD6', name: '음식점' },
        { value: 'SW8', name: '지하철역' },
        { value: 'BK9', name: '은행' },
        { value: 'AG2', name: '중개업소' },
        { value: 'CE7', name: '카페' },
        { value: 'HP8', name: '병원' },
        { value: 'PM9', name: '약국' },
      ],
      dealInfo: {},
      dealList: [],
      aptInfo: {},
      markers: [],
      marker: [],
      aroundlist: [],
      infowindow: '',
    }
  },
  methods: {
    async searchAptDeal() {
      console.log(this.aptName);
      let response = await http.get("/loadDetailApt/" + this.aptName);
      let { data } = response;
      console.log(data);
      // makeApartList(data);
    },
    async searchAptInfo() {
      let response = await http.get("/InfoApt/" + this.aptName);
      let { data } = response;
      this.aptInfo = data[0];
      console.log(this.aptInfo);
      this.makeApartPoint();
    },
    movetoChartPage(){
      this.$router.push('/chart');
    },
    changeCategorySelection() {
      let category = document.getElementById("categorySelector");
      let data = category.options[category.selectedIndex].value;
      var ps = new kakao.maps.services.Places(this.map);
      ps.categorySearch(data, this.placesSearchCB, { useMapBounds: true });
    },
    placesSearchCB(data, status, pagination) {
      var place = [];
      if (status === kakao.maps.services.Status.OK) {

        for (var i = 1; i < this.marker.length; i++) {

          this.marker[i].setMap(null);
        }
        var tmp = this.markers[0];
        this.markers = [];
        this.markers.push(tmp);
        tmp = this.marker[0];
        this.marker = [];
        this.marker.push(tmp);
        console.log(data);
        this.aroundlist = data;

        for (var i = 0; i < data.length; i++) {
          this.displayMarker(data[i]);
        }
        //this.findNearCategory(data);
      }
    },

    displayMarker(place) {
      // 마커를 생성하고 지도에 표시합니다
      var mark = new kakao.maps.Marker({
        map: this.map,
        title: place.place_name,
        position: new kakao.maps.LatLng(place.y, place.x)
      });
      console.log(this.infowindow);
      // 마커에 클릭이벤트를 등록합니다
      var iwContent = '<div style="padding:5px;">Hello World!</div>';
      var infowindows = new kakao.maps.InfoWindow({
          zindex: 1,
      });
      var tmpMap = this.map;
      kakao.maps.event.addListener(mark, 'mouseover', function () {
        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
        infowindows.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
        infowindows.open(tmpMap, mark);
      });

      kakao.maps.event.addListener(mark, 'mouseout', function () {
        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
        infowindows.close();
      });
      this.marker.push(mark);
      var tmp = new Object();
      tmp.lat = place.y;
      tmp.lng = place.x;
      tmp.name = place.place_name;
      this.markers.push(tmp);
    },
    // async findNearCategory(positions) {
    //   console.log(positions);

    //   var polyline = new kakao.maps.Polyline({
    //     path: [
    //       new kakao.maps.LatLng(this.aptInfo.lat, this.aptInfo.lng), 
    //       new kakao.maps.LatLng(positions[0].y, positions[0].x)
    //       ],
    //   });

    //   var minDistance = polyline.getLength();
    //   var minIndex = 0;

    //   for (let i = 1; i < positions.length; i++) {
    //     polyline = new kakao.maps.Polyline({
    //       path: [
    //         new kakao.maps.LatLng(this.aptInfo.lat, this.aptInfo.lng), 
    //         new kakao.maps.LatLng(positions[0].y, positions[0].x)
    //         ],
    //     });

    //     var distance = polyline.getLength();
    //     console.log(distance);
    //     if (minDistance > distance) {
    //       minDistance = distance;
    //       minIndex = i;
    //     }
    //   }
    //   console.log(minIndex);
    //   var point = 0;
    //   for (var i = 0; i < this.marker.length; i++) {
    //     if (positions[minIndex].place_name === this.marker[i].Gb) {
    //       point = i;
    //       break;
    //     }
    //   }
    //   var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png";
    //   var imageSize = new kakao.maps.Size(28, 35);
    //   var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

    //   var recentPlace = this.marker[point];
    //   console.log(recentPlace);
    //   if (recentPlace !== undefined) recentPlace.setImage(markerImage);
    // },

    // async sendCategoryToBackend(marker) {
    //   var sendData = new Array;

    //   for (var i = 0; i <= this.markers.length; i++) {
    //     if (this.markers[i] === undefined) continue;
    //     sendData.push(this.markers[i]);
    //   }
    //   console.log(JSON.stringify(sendData));
    //   let response = await http.post('/categorySearch', JSON.stringify(sendData), { 'Content-type': 'application/json' });
    //   let { data } = response;
    //   console.log(data);

    //   var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png";
    //   var imageSize = new kakao.maps.Size(28, 35);
    //   var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);


    //   let position = new kakao.maps.LatLng(data.lat, data.lng);
    //   console.log(position);
    //   console.log(data);
    //   var point = 0;
    //   for (var i = 0; i < this.markers.length; i++) {
    //     if (this.markers[i] === undefined) {
    //       continue;
    //     }
    //     else if (data.lat === this.markers[i].lat && data.lng === this.markers[i].lng) {
    //       point = this.markers[i].name;
    //       break;
    //     }
    //   }
    //   console.log(point);
    //   for (var i = 0; i < marker.length; i++) {
    //     if (point === marker[i].Gb) {
    //       point = i;
    //       break;
    //     }
    //   }
    //   console.log(point);

    //   var recentPlace = marker[point];
    //   console.log(recentPlace);
    //   if (recentPlace !== undefined) recentPlace.setImage(markerImage);
    // },

    async makeApartPoint() {
      var position = {
        title: this.aptInfo.AptName,
        latlng: new kakao.maps.LatLng(this.aptInfo.lat, this.aptInfo.lng)
      }
      kakaoMap.changeMap(this.map, position);

      var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
      // 마커 이미지의 이미지 크기 입니다
      var imageSize = new kakao.maps.Size(24, 35);

      // 마커 이미지를 생성합니다    
      var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

      // 마커를 생성합니다
      var mark = new kakao.maps.Marker({
        map: this.map, // 마커를 표시할 지도
        position: position.latlng, // 마커를 표시할 위치
        title: position.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
        image: markerImage // 마커 이미지 
      });
      this.marker.push(mark);

      //kakaoMap.mapfunc(positions, this.map);
    },

    async makedealInfo(data) {
      var positions = [];
      var markers = [];
      var lat = data.lat;
      var lng = data.lng;
      var moveLoc = new kakao.maps.LatLng(lat, lng);

      var tmp = new Object();
      tmp.lat = lat;
      tmp.lng = lng;
      markers.push(tmp);

      map.setCenter(moveLoc);
      positions.push({
        title: apart_name,
        latlng: new kakao.maps.LatLng(lat, lng)
      });
      mapfunc(positions);
    },
    testfunc(){
      console.log(this.map);
    }
  },
  computed: {
    aptName: {
      get() {
        return this.$store.getters.getApartName;
      },
      set(aptName) {
        this.$store.commit("SET_APARTMENT_NAME", aptName);
      }
    },
    kakaoMap: {
      get() {
        return this.$store.getters.getKakaoMap;
      },
      set(kakaoMap) {
        this.$store.commit("SET_KAKAO_MAP", kakaoMap);
      }
    }
  },
  watch: {
    aptName: function () {
      console.log("watch : aptName() " + this.aptName);
    },
    kakaoMap: function () {
      console.log("watch : kakaoMap()" + this.kakaoMap);
    }
  },
  mounted() {
    let x = 37.50395942713657;
    let y = 127.04282459768903;
    this.map = kakaoMap.loadMap(document.getElementById("map"), x, y, 5);
    this.$store.commit('SET_KAKAO_MAP', this.map);
    this.map.setZoomable(false);
    this.infowindow = new kakao.maps.InfoWindow({ zindex: 1 });
    this.searchAptInfo();
    this.searchAptDeal();
  },
}
</script>

<style>
</style>
