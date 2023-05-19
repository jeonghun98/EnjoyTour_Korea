<template>
  <b-container class="bv-example-row mt-3">
    <div class="row bg-image p-1 me-1 ms-1 shadow-1-strong rounded content-title" id="background">
      <div class="col-12 text-light">
        <h3 class="fw-bold pt-3">관광지 정보</h3>
        <span class="content-desc">어떤 지역에 어떤 관광지가 있는지 찾아보세요</span>
      </div>
    </div>

    <b-row class="mt-3">
      <select-sido @select-sido="selectSido"></select-sido>
      <select-gugun :sidoCode="sidoCode" @select-gugun="selectGugun"></select-gugun>
      <select-content @select-content="selectContent"></select-content>
    </b-row>
    <b-row class="mt-3">
      <b-col cols="12">
        <attraction-view :sidoCode="sidoCode" :gugunCode="gugunCode" :contentCode="contentCode"></attraction-view>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import AttractionView from "@/components/attraction/AttractionView.vue";
import SelectSido from "@/components/item/SelectSido.vue";
import SelectGugun from "@/components/item/SelectGugun.vue";
import SelectContent from "@/components/item/SelectContent.vue";

export default {
  name: "AppAttractionView",
  components: {
    SelectSido,
    SelectGugun,
    SelectContent,
    AttractionView,
  },
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      contentCode : 0,
    };
  },
  methods: {
    selectSido(sidoCode) {
      this.sidoCode = sidoCode;
    },
    selectContent(contentCode) {
      this.contentCode = contentCode;
    },
    selectGugun(gugunCode) {
      console.log("구군바뀜", gugunCode);
      this.gugunCode = gugunCode;
      //   console.log("구군바꼈으니 충전소 찾으러 가자!!!", gugunCode);
      //   const SERVICE_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;

      //   const params = {
      //     pageNo: 1,
      //     numOfRows: 30,
      //     zscode: gugunCode,
      //     serviceKey: decodeURIComponent(SERVICE_KEY),
      //   };
      //   // if (gugunCode) params.zscode = gugunCode;
      //   // else params.zcode = this.sidoCode;

      //   electricChargerStationList(
      //     params,
      //     ({ data }) => {
      //       this.chargerList = data.items[0].item;
      //     },
      //     (error) => {
      //       console.log(error);
      //     }
      //   );
    },
  },
};
</script>

<style>
#background {
  background-image: url("@/assets/img/bg0.jpg");
  background-size: cover;
  height: 105px;
}
.wrap {
  position: absolute;
  left: 65px;
  bottom: -30px;
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
  background: #eee;
  border-bottom: 1px solid #ddd;
  font-size: 18px;
  font-weight: bold;
  overflow: hidden;
}

.info .close {
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
}

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
