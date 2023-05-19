<template>
    <b-col class="sm-3">
      <b-form-select v-model="contentCode" :options="contents" @change="changeContent"></b-form-select>
    </b-col>
  </template>
  
  <script>
  import { mapState, mapMutations } from "vuex";
  
  const itemStore = "itemStore";
  
  export default {
    name: "SelectContent",
    data() {
      return {
        contentCode: 0,
      };
    },
    computed: {
      ...mapState(itemStore, ["contents"]),
    },
    created() {
          this.CLEAR_CONTENT_LIST();
          this.SET_CONTENT_LIST();
    },
    methods: {
        ...mapMutations(itemStore, ["CLEAR_CONTENT_LIST"],),
        ...mapMutations(itemStore, ["SET_CONTENT_LIST"],),
          changeContent() {
                console.log(this.contentCode);
        this.$emit("select-content", this.contentCode);
      },
    },
  };
  </script>
  
  <style scoped></style>
  