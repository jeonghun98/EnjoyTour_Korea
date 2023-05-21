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
      <select-content  :sidoCode="sidoCode" :gugunCode="gugunCode" @select-content="selectContent"></select-content>
    </b-row>
    <b-row class="mt-3">
      <b-col cols="12">
        <attraction-view></attraction-view>
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
    selectGugun(gugunCode) {
      this.gugunCode = gugunCode;
    },
    selectContent(contentCode) {
      this.contentCode = contentCode;
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
</style>
