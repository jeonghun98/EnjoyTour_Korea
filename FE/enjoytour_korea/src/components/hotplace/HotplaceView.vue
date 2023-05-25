<template>
  <b-row>
    <!-- {{hotplace.hotplaceNo}}번 핫플레이스 상세보기 화면입니다 -->
    <!-- 핫플레이스 이미지 -->
    <b-col>
      <b-row
        style="
          display: flex;
          flex-wrap: wrap;
          margin-right: -15px;
          margin-left: -15px;
          justify-content: center;
        "
      >
        <!-- <hotplace-img-item-vue v-for="fileInfo in hotplace.fileInfos" :key="fileInfo.originalFile"></hotplace-img-item-vue> -->
        <hotplace-img-item-vue
          v-for="(fileInfo, index) in hotplace.fileInfos"
          :key="index"
          v-bind="fileInfo"
        ></hotplace-img-item-vue>
      </b-row>
      <b-row>
        <form id="form-view" method="" action="" style="width: 100%">
          <div class="mb-3 mt-3">
            <label for="title" class="form-label">핫플이름</label>
            <input
              type="text"
              class="form-control"
              id="title"
              name="title"
              placeholder="핫플이름.."
              v-model="hotplace.title"
              readonly
            />
          </div>
          <div class="mb-3">
            <label for="date" class="form-label">다녀온 날짜</label>
            <input
              type="date"
              class="form-control"
              id="date"
              name="date"
              value="2023-03-27"
              v-model="hotplace.date"
              readonly
            />
          </div>
          <div class="mb-3">
            <label for="content" class="form-label">핫플 상세설명</label>
            <textarea
              class="form-control"
              id="content"
              name="content"
              rows="7"
              v-model="hotplace.content"
              readonly
            ></textarea>
          </div>
        </form>
      </b-row>
      <b-row>
        <b-col>
          <b-button
            type="button"
            variant="outline-primary"
            id="btn-move-list"
            class="btn mt-3 mb-3 mr-3"
            @click="moveListHotplace"
          >
            목록
          </b-button>
          <span v-if="userInfo != null && userInfo.userid === hotplace.userId">
            <b-button
              type="button"
              id="btn-move-list"
              variant="outline-success"
              class="btn mt-3 mb-3 mr-3"
              @click="modifyHotplace"
            >
              글수정
            </b-button>
            <b-button
              type="button"
              id="btn-move-list"
              variant="outline-danger"
              class="btn mt-3 mb-3 mr-auto"
              @click="deleteHotplace"
            >
              글삭제
            </b-button>
          </span>
        </b-col>
      </b-row>
    </b-col>
    <!-- 핫플 정보, 위치 -->
    <b-col>
      <b-row>
        <!-- map start -->
        <div class="col-lg-12 col-md-10 col-sm-12 mt-3 rounded">
          <div id="kakaomap"></div>
        </div>
        <!-- map end -->
      </b-row>
    </b-col>
  </b-row>
</template>

<script>
import { getHotplace, deleteHotplace } from "@/api/hotplace";
import HotplaceImgItemVue from "./item/HotplaceImgItem.vue";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "HotplaceView",
  components: {
    HotplaceImgItemVue,
  },
  data() {
    return {
      hotplace: {},
      hotplaceImg: "",
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    let param = this.$route.params.hotplaceNo;
    getHotplace(
      param,
      ({ data }) => {
        this.hotplace = data;
        console.log("HotplaceView:data -", data);
        this.marker();
      },
      (error) => {
        console.log(error);
      }
    );
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      this.loadScript();
    }
  },
  methods: {
    moveListHotplace() {
      // alert("핫플레이스 목록 이동");
      this.$router.push({ name: "hotplaceList" });
    },
    modifyHotplace() {
      // alert("핫플레이스 수정 이동");
      this.$router.replace({
        name: "hotplaceModify",
        params: { hotplaceNo: this.hotplace.hotplaceNo },
      });
    },
    deleteHotplace() {
      // alert("핫플레이스 삭제 이동")
      if (confirm("정말로 삭제하시겠습니까?")) {
        let param = this.$route.params.hotplaceNo;

        deleteHotplace(
          param,
          ({ data }) => {
            let msg = "삭제 처리시 문제가 발생했습니다.";
            if (data === "success") {
              msg = "삭제가 완료되었습니다.";
            }
            alert(msg);
            this.$router.push({ name: "hotplaceList" });
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },
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
          this.hotplace.latitude = position.coords.latitude; // 위도, 경도
          this.hotplace.longitude = position.coords.longitude;
          this.map.panTo(new kakao.maps.LatLng(this.hotplace.latitude, this.hotplace.longitude));
        });
      }
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
    marker() {
      var container = document.getElementById("kakaomap");
      var options = {
        center: new kakao.maps.LatLng(this.hotplace.latitude, this.hotplace.longitude), //wtmX, wtmY 받아옴
        level: 5,
      };

      var map = new kakao.maps.Map(container, options);

      // 마커가 표시될 위치입니다
      var markerPosition = new kakao.maps.LatLng(this.hotplace.latitude, this.hotplace.longitude);

      // 마커를 생성합니다
      var marker = new kakao.maps.Marker({
        position: markerPosition,
      });

      // 마커가 지도 위에 표시되도록 설정합니다
      marker.setMap(map);
    },
  },
};
</script>

<style scoped>
#kakaomap {
  width: 100%;
  height: 30rem;
  background-color: lightgray;
}
</style>
