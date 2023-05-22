<template>
  <b-row class="mt-3">
    <b-col cols="9">
      <div id="attraction-map">
        <div id="kakaomap" style="width: 100%; height: 40rem"></div>
      </div>
    </b-col>
    <b-col>
      <div class="row" id="plan-item-list">
        <div>
          <h3 style="font-weight: bold">여행경로</h3>
          <draggable class="list-group" group="planListGroup" :list="planList" :key="planList.length" @change="setPlanMarker">
            <div v-if="planList.length == 0">여행경로를 추가해주세요</div>
            <div v-else class="list-group-item" v-for="(atr, index) in planList" :key="index">
              <b-card no-body class="attraction-item overflow-hidden">
                <b-row no-gutters>
                  <b-col md="4" class="m-auto">
                    <b-card-img
                      class="img align-middle"
                      v-if="atr.image1 != ''"
                      :src="atr.image1"
                      :alt="atr.title"
                    />
                    <b-card-img
                      class="img align-middle"
                      v-if="atr.image1 == ''"
                      :src="require('@/assets/img/ssafy_logo.png')"
                      alt="no image"
                    />
                  </b-col>
                  <b-col md="8" class="m-auto pl-3">
                    <table>
                      <tr>
                        <td v-if="atr.title.length > 15" class="item-title">
                          <strong>{{ index + 1 }}번</strong><br />{{ atr.title.substr(0, 15) }}...
                        </td>
                        <td v-if="atr.title.length <= 15" class="item-title">
                          <strong>{{ index + 1 }}번</strong><br />
                          {{ atr.title }}
                        </td>
                        <td>
                          <b-button
                            class="delete-btn"
                            variant="danger"
                            @click="deleteItem(atr.contentid)"
                          >
                            삭제
                          </b-button>
                        </td>
                      </tr>
                    </table>
                  </b-col>
                </b-row>
              </b-card>
            </div>
          </draggable>
        </div>
      </div>
    </b-col>
  </b-row>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
const attractionStore = "attractionStore";
import draggable from "vuedraggable";

export default {
  name: "PlanMap",
  data() {
    return {
      map: null,
      markers: [], // 마커를 담는 배열
      lat: null,
        lon: null,
      
      customOverlays: [],
      polyline: null,

      planList: [],
    };
  },
  components: {
    draggable,
  },
  props: {
    opt : Boolean,
  },
  computed: {
    // ...mapGetters(itemStore, ["getSidoText", "getGugunText", "getContentText"]),
    ...mapState(attractionStore, [
      "latitude",
      "longitude",
      "attractions",
      //   "planMarkers"
    ]),
  },
    watch: {
    opt() {
        console.log(this.opt)
    },
    planList() {
      // 오버레이 초기화
      this.customOverlays.forEach((overlay) => {
        overlay.setMap(null);
      });
      this.customOverlays = [];

      const positions = this.planList.map(
        (position) => new kakao.maps.LatLng(position.latitude, position.longitude)
      );
      if (this.planList.length > 0) this.makeLine(positions);
      this.setPlanMarker();
    },
    attractions: function (attractions) {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }
      const positions = attractions.map(
        (position) => new kakao.maps.LatLng(position.latitude, position.longitude)
      );
      if (attractions.length > 0) {
        this.markers = attractions.map((position) => {
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

          // 마커 mouseover 이벤트
          kakao.maps.event.addListener(marker, "mouseover", () => {
            infowindow.open(this.map, marker);
          });

          kakao.maps.event.addListener(marker, "mouseout", () => {
            infowindow.close();
          });

          // // 마커 클릭 이벤트
          kakao.maps.event.addListener(marker, "click", () => {
            this.getAttraction(position.contentid);
            this.map.panTo(new kakao.maps.LatLng(position.latitude, position.longitude));
            this.planList.push(position);
            console.log("push", this.planList);
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
  },
  created() {
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      this.loadScript();
    }
    this.CLEAR_PLAN_MARKERS();
  },
  methods: {
    ...mapMutations(attractionStore, [
      "CLEAR_ATTRACTION",
      "SET_PLAN_MARKERS",
      "CLEAR_PLAN_MARKERS",
    ]),
    ...mapActions(attractionStore, ["getPosition", "getAttraction"]),

    initMap() {
      const container = document.getElementById("kakaomap");
      const options = {
        center: new kakao.maps.LatLng(37.5013068, 127.0396597),
        level: 4,
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
    makeLine(positions) {
      if (this.polyline != null) {
        this.polyline.setMap(null);
      }
      this.polyline = new kakao.maps.Polyline({
        map: this.map,
        path: positions, // 선을 구성하는 좌표배열
        strokeWeight: 3, // 두께
        strokeColor: "#FF4444", // 색깔
        strokeOpacity: 0.9, // 불투명도(1에서 0 사이의 값, 0: 투명)
        strokeStyle: "solid", // 스타일
      });
      // 연결선 표시
      this.polyline.setMap(this.map);
    },

    deleteItem(contentid) {
      for (let i = 0; i < this.planList.length; i++) {
        if (this.planList[i].contentid === contentid) {
          this.planList.splice(i, 1);
          i--;
          break;
        }
      }
    },
    setPlanMarker() {
    //   console.log("setPlanMarker", this.planList);
      this.CLEAR_PLAN_MARKERS();
      this.SET_PLAN_MARKERS(this.planList);
    },

    //addTrack2 , clearPath, changePath(...)
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

#plan-item-list {
  width: 100%;
  height: 80vh;
  margin: 0;
}
.list-group {
  height: 73vh;
  overflow: scroll;
  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
  background-color: white;
}
.list-group::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera*/
}
.list-group-item {
  width: 100%;
 /* height: 100px;*/
  padding: 0;
  cursor: pointer;
}
.list-group-item img {
  height: 100px;
}
tr {
  height: 100px;
}
.item-title {
  width: 75%;
  font-size: 1.1rem;
  padding-right: 5px;
  align-items: center;
  text-overflow: ellipsis;
}
.delete-btn {
  width: 25%;
  width: 60px;
  margin-right: 5px;
  padding: 0;
  padding-block: 5px;
  align-items: center;
}
</style>
