<template>
  <div id="attraction-map">
    <div id="kakaomap" style="width: 100%; height: 40rem"></div>
    <b-modal v-model="modalShow" size="xl" ok-only>
      <!-- <template #modal-header>
      <h5 class = "modal-title">관광지 정보</h5>
      </template> -->
      <attraction-modal></attraction-modal>
    </b-modal>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
// import { searchByLoc } from "@/api/attraction";
import AttractionModal from "./AttractionModal.vue";
// const itemStore = "itemStore";
const attractionStore = "attractionStore";

export default {
  name: "AttractionView",
  data() {
    return {
      map: null,
      markers: [], // 마커를 담는 배열
      // infoWindows: [], // infoWindow를 담는 배열
      // clickLine: {},

      lat: null,
      lon: null,
      modalShow: false,
      nowOverlay: null,

      // regionName: null,
      // regionMap: {},
      // areaCode : null
    };
  },
  components: { AttractionModal },
  props: {
    // sidoCode: String,
    // gugunCode: String,
    // contentCode: Number,
  },
  computed: {
    // ...mapGetters(itemStore, ["getSidoText", "getGugunText", "getContentText"]),
    ...mapState(attractionStore, ["latitude", "longitude", "markerPositions"]),
  },
  watch: {
    markerPositions: function (markerPositions) {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }
      const positions = markerPositions.map(
        (position) => new kakao.maps.LatLng(position.latitude, position.longitude)
      );
      if (markerPositions.length > 0) {
        this.markers = markerPositions.map((position) => {
          var pos = new kakao.maps.LatLng(position.latitude, position.longitude);

          var imageSrc;
          if (position.contenttypeid != null)
            imageSrc = require(`@/assets/img/icon_${position.contenttypeid}.png`);
          else imageSrc = require("@/assets/img/ssafy_logo.png");

          const ssafyImageSrc = require("@/assets/img/ssafy_logo.png");

          var imageSize = new kakao.maps.Size(30, 30); // 기본 마커 이미지의 크기
          var imageOption = { offset: new kakao.maps.Point(25, 20) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

          var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);

          var marker = new kakao.maps.Marker({
            map: this.map,
            position: pos,
            clickable: true, // 마커 클릭 가능
            image: markerImage,
          });

          let contents =
            '<div class = "wrap">' +
            '   <div class="info">' +
            '    <div class="title" style="background-color:1BB1FF;">' +
            position.title +
            "   </div>" +
            '    <div class="body">' +
            '     <div class="img">' +
            `        <img src="${position.image1}" width ="73" height="70" onerror="this.src='${ssafyImageSrc}'">` +
            "     </div>" +
            '      <div class="desc">' +
            '       <div class="ellipsis"> ' +
            position.addr1 +
            "</div>" +
            '       <div class="desc_marker"> ' +
            "마커 클릭시 상세보기" +
            "</div>" +
            // '       <div class="jibun"> ' + position[5] + '</div>' +
            // '       <div><a href=" "target="_blank" class="link">홈페이지</a></div>' +
            "      </div>" +
            "     </div>" +
            "    </div>" +
            "   </div>";

          let infowindow = new kakao.maps.InfoWindow({
            content: contents,
            position: pos,
          });

          // let infoOverlay = new kakao.maps.CustomOverlay({
          //     content: content,
          //     position: marker.getPosition(),
          //     yAnchor: 1,
          //     zIndex: 2
          // });

          // 마커 mouseover 이벤트
          kakao.maps.event.addListener(marker, "mouseover", () => {
            // customOverlay.setMap(this.map);
            infowindow.open(this.map, marker);
          });

          kakao.maps.event.addListener(marker, "mouseout", () => {
            // customOverlay.setMap(null);
            infowindow.close();
          });

          // // 마커 클릭 이벤트
          kakao.maps.event.addListener(marker, "click", () => {
            this.getAttraction(position.contentId);
            this.map.panTo(new kakao.maps.LatLng(position.latitude, position.longitude));
            this.modalShow = !this.modalShow;
          });
          return marker;
        });
        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds()
        );
        this.map.setBounds(bounds);
      }
    },

    modalShow: function () {
      if (!this.modalShow) {
        this.CLEAR_ATTRACTION();
      }
    },
  },
  created() {
    this.modalShow = false;
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      this.loadScript();
    }
  },
  methods: {
    ...mapMutations(attractionStore, [
      "CLEAR_ATTRACTION",
      "CLEAR_ATTRACTION_LIST",
      "CLEAR_MARKER_POSITIONS",
    ]),
    ...mapActions(attractionStore, ["getPosition", "getAttraction"]),

    initMap() {
      const container = document.getElementById("kakaomap");
      const options = {
        center: new kakao.maps.LatLng(37.5013068, 127.0396597),
        level: 5,
      };

      this.map = new kakao.maps.Map(container, options);
      this.map.relayout();

      // geolocation을 사용할 수 있는지 확인
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition((position) => {
          // GeoLocation을 이용해서 접속 위치 획득
          this.lat = position.coords.latitude; // 위도, 경도
          this.lon = position.coords.longitude;
          this.getPosition({
            latitude: this.lat,
            longitude: this.lon,
          });
          this.map.panTo(new kakao.maps.LatLng(this.lat, this.lon));
        });
      }
      // makeOption(); -> search-area 생성
    },
    loadScript() {
      const script = document.createElement("script");
      /* global kakao */
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=" +
        process.env.VUE_APP_KAKAO_MAP_API_KEY +
        "&autoload=false&libraries=services";
      document.head.appendChild(script);
      script.onload = () => kakao.maps.load(this.initMap);
    },
  },
};
</script>
<style>
.wrap {
  position: absolute;
  left: 65px;
  bottom: -45px;
  width: 288px;
  height: 132px;
  margin-left: -144px;
  text-align: left;
  overflow: hidden;
  font-size: 12px;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  line-height: 1.5;
}
.wrap .info {
  width: 286px;
  height: 120px;
  border-radius: 5px;
  border-bottom: 2px solid #ccc;
  border-right: 1px solid #ccc;
  overflow: hidden;
  background: #fff;
}

.wrap .info:nth-child(1) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}

.info .title {
  padding: 5px 0 0 10px;
  height: 30px;
  background: #1bb1ff;
  border-bottom: 1px solid #ddd;
  font-size: 18px;
  font-weight: bold;
  overflow: hidden;
}

/* .info .close {
  position: absolute;
  top: 10px;
  right: 10px;
  color: #888;
  width: 17px;
  height: 17px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png");
}

.info .close:hover {
  cursor: pointer;
} */

.info .body {
  position: relative;
  overflow: hidden;
}

.info .desc {
  position: relative;
  margin: 13px 0 0 90px;
  height: 75px;
}

.desc .ellipsis {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.desc .desc_marker {
  color: #1e1f20;
  font-weight: bold;
}
.info .img {
  position: absolute;
  top: 6px;
  left: 5px;
  width: 73px;
  height: 71px;
  border: 1px solid #ddd;
  color: #888;
  overflow: hidden;
}

.info:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: 0;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}

.info .registerHotPlace {
  color: #5085bb;
}

.info .addTrack {
  color: #5085bb;
}
</style>
