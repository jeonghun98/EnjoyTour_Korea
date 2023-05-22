<template>
  <div>
    <b-row class="mt-3">
      <select-sido @select-sido="selectSido"></select-sido>
      <select-gugun :sidoCode="sidoCode" @select-gugun="selectGugun"></select-gugun>
      <select-content
        :sidoCode="sidoCode"
        :gugunCode="gugunCode"
        @select-content="selectContent"
      ></select-content>

      <b-col class="col-4">
        <b-form-input
          id="searchword"
          v-model="word"
          placeholder="검색어"
          @keyup.enter="searchAttraction"
        ></b-form-input>
      </b-col>
      <b-col class="col-2">
        <b-button type="button" variant="primary" @click="searchAttraction">검색</b-button>
      </b-col>
      <b-col class="col-2">
        <b-button type="button" variant="primary" @click="optimization">최적화</b-button>
      </b-col>
    </b-row>
    <plan-map :opt="opt"></plan-map>
  </div>
</template>

<script>
import { mapActions, mapMutations } from "vuex";
const attractionStore = "attractionStore";

import SelectSido from "@/components/item/SelectSido.vue";
import SelectGugun from "@/components/item/SelectGugun.vue";
import SelectContent from "@/components/item/SelectContent.vue";
import PlanMap from "./PlanMap.vue";

export default {
  name: "PlanAttraction",
  components: {
    SelectSido,
    SelectGugun,
    SelectContent,
    PlanMap,
  },
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      contentCode: 0,
      word: null,
      opt: false,
    };
  },
  props: {
    type: { type: String },
  },
  created() {
    this.CLEAR_ATTRACTION_LIST();
  },
  methods: {
    optimization() {
      this.opt = !this.opt;
    },
    selectSido(sidoCode) {
      this.sidoCode = sidoCode;
    },
    selectGugun(gugunCode) {
      this.gugunCode = gugunCode;
    },
    selectContent(contentCode) {
      this.contentCode = contentCode;
    },
    ...mapActions(attractionStore, ["getSearchAttractionList"]),
    ...mapMutations(attractionStore, ["CLEAR_ATTRACTION_LIST", "CLEAR_MARKER_POSITIONS"]),

    searchAttraction() {
      this.CLEAR_ATTRACTION_LIST();
      this.CLEAR_MARKER_POSITIONS();
      if (!this.sidoCode) this.sidoCode = "";
      if (!this.gugunCode) this.gugunCode = "";

      if (this.word) {
        this.getSearchAttractionList({
          sidoCode: this.sidoCode,
          gugunCode: this.gugunCode,
          word: this.word,
        });
      }
    },
  },
};
</script>

<style scoped></style>
