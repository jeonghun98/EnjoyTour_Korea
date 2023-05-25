<template>
  <b-row class="mt-3">
    <b-col cols="9">
      <div id="attraction-map">
        <div id="kakaomap" style="width: 100%; height: 40rem"></div>
      </div>
    </b-col>
    <b-modal v-model="modalShow" size="xl" ok-only>
      <attraction-modal></attraction-modal>
    </b-modal>
    <b-col>
      <div class="row" id="plan-item-list">
        <div>
          <h3 style="font-weight: bold">여행경로</h3>
          <draggable
            class="list-group"
            group="planListGroup"
            :list="planList"
            :key="planList.length"
            @change="setPlanMarker"
          >
            <div v-if="planList.length == 0">여행경로를 추가해주세요</div>
            <div
              v-else
              class="list-group-item"
              v-for="(atr, index) in planList"
              :key="index"
              @click="showModal(atr.contentId)"
            >
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
                          <button
                            class="btn btn-outline-danger mt-3 mb-3 mr-3"
                            id="delete-btn"
                            @click="deleteItem(atr.contentId)"
                          >
                            삭제
                          </button>
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
import AttractionModal from "@/components/attraction/AttractionModal.vue";

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

      modalShow: false,
    };
  },
  components: {
    draggable,
    AttractionModal,
  },
  props: {
    opt: Boolean,
  },
  computed: {
    // ...mapGetters(itemStore, ["getSidoText", "getGugunText", "getContentText"]),
    ...mapState(attractionStore, ["latitude", "longitude", "attractions", "planMarkers"]),
  },
  watch: {
    opt() {
      console.log(this.opt);
    },
    planList() {
      this.custonOverlay();
    },
    attractions: function (attractions) {
      this.writeMarker(attractions, false);
    },
  },
  created() {
    this.modalShow = false;
    this.planList = this.planMarkers;
    console.log();
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      this.loadScript();
    }
    this.custonOverlay();
  },
  methods: {
    ...mapMutations(attractionStore, [
      "CLEAR_ATTRACTION",
      "SET_PLAN_MARKERS",
      "CLEAR_PLAN_MARKERS",
    ]),
    ...mapActions(attractionStore, ["getPosition", "getAttraction"]),

    custonOverlay() {
      // 오버레이 초기화
      // this.customOverlays.forEach((overlay) => {
      //   overlay.setMap(null);
      // });
      // this.customOverlays = [];
      console.log("custonOverlay planmap", this.planList);
      this.writePlanMarker(this.planList);

      // const positions = this.planList.map(
      //   (position) => new kakao.maps.LatLng(position.latitude, position.longitude)
      // );
      // if (this.planList.length > 0) this.makeLine(positions);
      // this.setPlanMarker();
    },
    writePlanMarker(planList) {
      // 연결선 초기화
      if (this.polyline != null) {
        this.polyline.setMap(null);
      }

      this.customOverlays.forEach((overlay) => {
        overlay.setMap(null);
      });
      this.customOverlays = [];

      const positions = planList.map(
        (position) => new kakao.maps.LatLng(position.latitude, position.longitude)
      );
      if (planList.length > 0) {
        let index = 1;

        this.markers = planList.map((position) => {
          var pos = new kakao.maps.LatLng(position.latitude, position.longitude);

          var imageSrc;
          if (position.contenttypeid != null)
            imageSrc = require(`@/assets/img/icon_${position.contenttypeid}.png`);
          else imageSrc = require("@/assets/img/ssafy_logo.png");

          // const ssafyImageSrc = require("@/assets/img/ssafy_logo.png");

          var imageSize = new kakao.maps.Size(30, 30); // 기본 마커 이미지의 크기
          var imageOption = { offset: new kakao.maps.Point(25, 20) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

          var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);

          var marker = new kakao.maps.Marker({
            map: this.map,
            position: pos,
            clickable: true, // 마커 클릭 가능
            image: markerImage,
          });

          var content = `<div id = "overlaylabel" class ="label" style="padding: 5px; background-color: #3685f5;"><strong id="circle">${index++}</strong></div>`;
          var customOverlay = new kakao.maps.CustomOverlay({
            position: pos,
            content: content,
          });
          this.customOverlays.push(customOverlay);
          customOverlay.setMap(this.map);

          // // 마커 클릭 이벤트
          kakao.maps.event.addListener(marker, "click", () => {
            this.getAttraction(position.contentId);
            this.map.panTo(new kakao.maps.LatLng(position.latitude, position.longitude));
            this.planList.push(position);
            // console.log("push", this.planList);
          });
          return marker;
        });
        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds()
        );
        this.map.setBounds(bounds);
        this.makeLine(positions);
      }
    },
    writeMarker(attractions) {
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

          let find = false;
          for (let i = 0; i < this.planList.length; i++) {
            if (this.planList[i].contentId == position.contentId) {
              find = true;
            }
          }
          if (find) return;

          var marker = new kakao.maps.Marker({
            map: this.map,
            position: pos,
            clickable: true, // 마커 클릭 가능
            image: markerImage,
          });

          let contents =
            '<div class = "wrap">' +
            '   <div class="info">' +
            '    <div class="title" style="background-color:3685f5;">' +
            position.title +
            '   <div id="close"></div>' +
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
            "마커 클릭시 경로추가" +
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
            this.getAttraction(position.contentId);
            this.map.panTo(new kakao.maps.LatLng(position.latitude, position.longitude));
            this.planList.push(position);
            // console.log("push", this.planList);
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
    showModal(id) {
      if (this.modalShow) {
        this.modalShow = false;
        this.CLEAR_ATTRACTION();
      } else {
        this.CLEAR_ATTRACTION();
        this.getAttraction(id);
        this.modalShow = true;
      }
    },
    makeLine(positions) {
      console.log("makeLine", positions);
      if (this.polyline != null) {
        this.polyline.setMap(null);
      }
      this.polyline = new kakao.maps.Polyline({
        map: this.map,
        path: positions, // 선을 구성하는 좌표배열
        strokeWeight: 3, // 두께
        strokeColor: "#214ec2", // 색깔
        strokeOpacity: 0.9, // 불투명도(1에서 0 사이의 값, 0: 투명)
        strokeStyle: "solid", // 스타일
      });
      // 연결선 표시
      this.polyline.setMap(this.map);
    },

    deleteItem(contentId) {
      for (let i = 0; i < this.planList.length; i++) {
        if (this.planList[i].contentId === contentId) {
          this.planList.splice(i, 1);
          i--;
          break;
        }
      }
    },
    setPlanMarker() {
      console.log("setPlanMarker", this.planList);
      this.CLEAR_PLAN_MARKERS();
      this.SET_PLAN_MARKERS(this.planList);
    },

    //addTrack2 , clearPath, changePath(...)
  },
};
</script>
<style>
#attraction-map {
  position: relative;
}

#kakaomap {
  width: 100%;
  height: 40rem;
}

#overlaylabel {
  display: flex;
  justify-content: center;
  margin-left: -25px;
  margin-right: -15px;
  margin-top: -px;
  max-width: 1170px;
}

#circle {
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #3685f5;
  font-size: 20px;
  color: rgb(255 255 255);
  padding: 6px;
  width: calc(1em / 0.7);
  height: calc(1em / 0.7);
}
.row {
  display: flex;
  flex-wrap: wrap;
}

.item-title {
  font-weight: bold;
  font-size: 16px;
}

.list-group {
  height: 73vh;
  overflow: scroll;
  -ms-overflow-style: none;
  scrollbar-width: none;
  background-color: white;
}

.list-group::-webkit-scrollbar {
  display: none;
}

.list-group-item {
  width: 100%;
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

#delete-btn {
  width: 25%;
  width: 60px;
  margin-right: 5px;
  padding: 0;
  padding-block: 5px;
  align-items: center;
}
</style>
