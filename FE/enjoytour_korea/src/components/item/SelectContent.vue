<template>
  <b-col class="sm-3">
    <b-form-select
      v-model="contentCode"
      :options="contents"
      @change="changeContent"
    ></b-form-select>
  </b-col>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

const itemStore = "itemStore";
const attractionStore = "attractionStore";

export default {
  name: "SelectContent",
  data() {
    return {
      contentCode: 0,
    };
  },
  props: {
    sidoCode: String,
    gugunCode : String,
  },
  computed: {
    ...mapState(itemStore, ["contents"]),
  },
  created() {
    this.CLEAR_CONTENT_LIST();
    this.SET_CONTENT_LIST();
  },
  methods: {
    ...mapActions(attractionStore, ["getAttractionList"]),
    ...mapMutations(itemStore, ["CLEAR_CONTENT_LIST", "SET_CONTENT_LIST"]),
    ...mapMutations(attractionStore, ["CLEAR_ATTRACTION_LIST", "CLEAR_MARKER_POSITIONS",]),
    ...mapMutations(itemStore, {
      setcontentcode: "SET_CONTENT",
    }),
    changeContent() {
      this.setcontentcode(this.contentCode);
      this.$emit("select-content", this.contentCode);

      this.CLEAR_ATTRACTION_LIST();
      this.CLEAR_MARKER_POSITIONS();
    
      if (this.sidoCode && this.gugunCode && this.contentCode) {
        this.getAttractionList({
          sidoCode: this.sidoCode,
          gugunCode: this.gugunCode,
          contentCode: this.contentCode,
        });
      }
    },
  },
};
</script>

<style scoped></style>
