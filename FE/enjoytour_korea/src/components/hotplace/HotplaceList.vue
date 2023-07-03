<template>
  <div class="my-3 bg-body rounded">
    <b-row>
      <button
          type="button"
          id="btn-mv-register"
          class="btn btn-outline-primary btn-sm mr-1"
          @click="moveWrite"
        >
          핫플 등록하기
        </button>
    </b-row>
    <div class="p-0 py-2">
      <div class="album">
        <div class="row row-cols-3">
            <hotplace-list-item
              v-for="hotplace in hotplaces"
              :key="hotplace.hotplaceNo"
              v-bind="hotplace"
            ></hotplace-list-item>
          </div>
      </div>
    </div>
  </div>
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
        // console.log(data);
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
