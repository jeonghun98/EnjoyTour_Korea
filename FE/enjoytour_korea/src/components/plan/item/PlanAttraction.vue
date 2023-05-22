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
  
        <b-col class="col-4 p-1">
          <b-form-input
            id="searchword"
            v-model="word"
            placeholder="검색어"
            @keyup.enter="searchAttraction"
          ></b-form-input>
        </b-col>
        <b-col class="col-2 p-1">
          <b-button type="button" variant="primary" @click="searchAttraction">검색</b-button>
        </b-col>
      </b-row>
      <b-row class="mt-3">
        <b-col cols="9">
          <plan-map></plan-map>
        </b-col>
        <b-col>
          <plan-item></plan-item>
        </b-col>
      </b-row>
    </div>
  </template>
  
  <script>
  import { mapActions, mapMutations } from "vuex";
  const attractionStore = "attractionStore";

  import SelectSido from "@/components/item/SelectSido.vue";
  import SelectGugun from "@/components/item/SelectGugun.vue";
  import SelectContent from "@/components/item/SelectContent.vue";
  import PlanMap from "./PlanMap.vue";
  import PlanItem from "./PlanItem.vue";

  export default {
    name: "PlanAttraction",
    components: {
      SelectSido,
      SelectGugun,
      SelectContent,
      PlanMap,
      PlanItem
    },
    data() {
      return {
        sidoCode: null,
        gugunCode: null,
        contentCode: 0,
        word: null,
      };
    },
    props: {
        type: { type: String },
    },
    created() {
      this.CLEAR_ATTRACTION_LIST();
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
  