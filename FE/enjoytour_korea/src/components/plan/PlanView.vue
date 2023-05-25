<template>
  <div id="travel-info" v-if="travelPlanContent">
    <b-col class="mt-3">
      <div id="attraction-map">
        <div id="kakaomap" style="width: 100%; height: 40rem"></div>
      </div>
    </b-col>

    <b-row class="mt-3">
      <b-col>
        <b-row>
          <b-navbar toggleable="lg">
            <b-navbar-brand class="ms-3">
              <h3>여행계획 상세보기</h3>
            </b-navbar-brand>
          </b-navbar>
        </b-row>

        <b-row class="mr-2">
          <div id="travel-info-item">
            <table class="table table-hover">
              <tr>
                <td>제목</td>
                <td>{{ travelPlanContent.title }}</td>
              </tr>
              <tr>
                <td>작성자</td>
                <td>{{ travelPlanContent.userId }}</td>
              </tr>
              <tr>
                <td>출발일</td>
                <td>{{ travelPlanContent.startDate }}</td>
              </tr>
              <tr>
                <td>도착일</td>
                <td>{{ travelPlanContent.endDate }}</td>
              </tr>
              <tr>
                <td>내용</td>
                <td>{{ travelPlanContent.content }}</td>
              </tr>
            </table>
          </div>
        </b-row>

        <b-row>
          <b-col>
            <button
              type="button"
              id="btn-move-list"
              class="btn btn-outline-primary mt-3 mb-3 mr-3"
              @click="moveListPlan"
            >
              목록
            </button>
            <span v-if="userInfo != null && userInfo.userid === travelPlanContent.userId">
              <button
                type="button"
                id="btn-move-list"
                class="btn btn-outline-success mt-3 mb-3 mr-3"
                @click="moveModifyPlan"
              >
                글수정
              </button>
              <button
                type="button"
                id="btn-move-list"
                class="btn btn-outline-danger mt-3 mb-3 mr-3"
                @click="deletePlan"
              >
                글삭제
              </button>
            </span>
          </b-col>
        </b-row>
      </b-col>
      <b-col>
        <b-row>
          <b-navbar toggleable="lg">
            <b-navbar-brand class="ms-3">
              <h3>여행 경로</h3>
            </b-navbar-brand>
          </b-navbar>
        </b-row>
        <b-row>
          <table class="table table-hover">
            <div
              class="list-group-item"
              v-for="(trm, index) in travelMarkers"
              :key="index"
              @click="movePan(trm.latitude, trm.longitude)"
            >
              <b-card no-body class="attraction-item overflow-hidden">
                <b-row no-gutters>
                  <b-col md="4" class="m-auto">
                    <b-card-img
                      class="img align-middle"
                      v-if="trm.image1 != ''"
                      :src="trm.image1"
                      :alt="trm.title"
                    />
                    <b-card-img
                      class="img align-middle"
                      v-if="trm.image1 == ''"
                      :src="require('@/assets/img/ssafy_logo.png')"
                      alt="no image"
                    />
                  </b-col>
                  <b-col md="8" class="m-auto pl-3">
                    <table>
                      <tr>
                        <td v-if="trm.title.length > 15" class="item-title">
                          <strong>{{ index + 1 }}번</strong><br />{{ trm.title.substr(0, 15) }}...
                        </td>
                        <td v-if="trm.title.length <= 15" class="item-title">
                          <strong>{{ index + 1 }}번</strong><br />
                          {{ trm.title }}
                        </td>
                      </tr>
                    </table>
                  </b-col>
                </b-row>
              </b-card>
            </div>
          </table>
        </b-row>
      </b-col>
    </b-row>
  </div>
  <!-- <table class="table table-hover">
        <tr
          v-for="(trm, index) in travelMarkers"
          :key="index"
          @click="movePan(trm[0], trm[1])"
        >
          <td>
            <strong>{{ index + 1 }}번</strong>
          </td>
          <td v-if="trm[4].length > 25">
            <strong>{{ trm[4].substr(0, 25) }}</strong
            >...
          </td>
          <td v-if="trm[4].length <= 25">
            <strong>{{ trm[4] }}</strong>
          </td>
        </tr>
      </table> -->
</template>

<script>
import { mapState, mapActions } from "vuex";
import { deletePlan } from "@/api/plan";
const attractionStore = "attractionStore";
const memberStore = "memberStore";

export default {
  name: "PlanView",
  components: {},
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
  props: {
    type: { type: String },
  },
  computed: {
    ...mapState(attractionStore, ["travelPlanContent", "travelPlan", "travelMarkers"]),
    ...mapState(memberStore, ["userInfo"]),
    ...mapState(attractionStore, ["latitude", "longitude"]),
  },
  watch: {
    travelMarkers() {
      this.custonOverlay();
    },
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      this.loadScript();
    }
  },
  async created() {
    this.planList = this.travelMarkers;
  },
  methods: {
    ...mapActions(attractionStore, ["getPosition"]),

    movePan(lat, lon) {
      this.getPosition({
        latitude: lat,
        longitude: lon,
      });
    },
    moveListPlan() {
      if (this.$route.params.type === "plan") this.$router.push({ name: "planList" });
      if (this.$route.params.type === "myplan") this.$router.push({ name: "myplanList" });
    },
    moveModifyPlan() {
      this.$router.replace({
        name: "planModify",
        params: { planNo: this.$route.params.planNo },
      });
    },

    deletePlan() {
      if (confirm("정말로 삭제하시겠습니까?")) {
        let param = this.$route.params.planNo;
        deletePlan(
          param,
          ({ data }) => {
            let msg = "삭제 처리시 문제가 발생했습니다.";
            if (data === "success") {
              msg = "삭제가 완료되었습니다.";
            }
            alert(msg);
            this.$router.push({ name: "planList" });
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },

    custonOverlay() {
      this.writePlanMarker(this.travelMarkers);
      console.log("custonOverlay travelMarkers", this.travelMarkers);
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

          // // // 마커 클릭 이벤트
          // kakao.maps.event.addListener(marker, "click", () => {
          //   this.getAttraction(position.contentId);
          //   this.map.panTo(new kakao.maps.LatLng(position.latitude, position.longitude));
          //   this.planList.push(position);
          //   // console.log("push", this.planList);
          // });
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
  },
};
</script>

<style>
h3 {
  margin: 0;
}

/* #travel-info {
  width: 100%;
  height: 95vh;
  margin: 0;
  text-align: center;
} */

#travel-info-item {
  width: 100%;
  height: 90%;
  overflow: auto;
}

tr {
  background-color: rgba(255, 255, 255, 0.7);
  cursor: pointer;
}
</style>
