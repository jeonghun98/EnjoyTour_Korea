<template>
  <b-row class = "mt-3">
    <div class="row align-self-center mb-2" >
        <div class="text-start">
          <button
            type="button"
        id="btn-mv-register"
        variant="outline-primary"
        @click="moveWrite"
      >
        핫플 등록하기
      </button>
        </div>
    <b-row>
      <div class="album">
        <div class="p-0 py-2">
          <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            <hotplace-list-item
              v-for="hotplace in hotplaces"
              :key="hotplace.hotplaceNo"
              v-bind="hotplace"
            ></hotplace-list-item>
          </div>
        </div>
      </div>
    </b-row>
  </b-row>
</template>

<script>
import { listHotplace } from "@/api/hotplace";
import HotplaceListItem from "./item/HotplaceListItem.vue";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "HotplaceList",
  components: {
    HotplaceListItem,
  },
  data() {
    return {
      hotplaces: [],
    };
  },
  created() {
    listHotplace(
      ({ data }) => {
        console.log(data);
        this.hotplaces = data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    moveWrite() {
      // alert("핫플 write 이동");
      this.$router.push({ name: "hotplaceWrite" });
    },
  },
};
</script>

<style scoped></style>
