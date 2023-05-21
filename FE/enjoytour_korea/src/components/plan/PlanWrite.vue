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
    </b-row>
    <b-row class="mt-3">
      <b-col cols="12">
        <attraction-view></attraction-view>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import AttractionView from "@/components/attraction/AttractionView.vue";
import SelectSido from "@/components/item/SelectSido.vue";
import SelectGugun from "@/components/item/SelectGugun.vue";
import SelectContent from "@/components/item/SelectContent.vue";
export default {
  name: "PlanWrite",
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
  created() {},
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

<style scoped></style>
