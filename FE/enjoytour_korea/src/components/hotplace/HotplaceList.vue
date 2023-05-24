<template>
  <div class="row p-1">
    <div>
    <button type="button" id="btn-mv-register" class="btn btn-outline-primary btn-sm mr-1"
          @click="moveWrite">
                핫플 등록하기
          </button>
    </div>
    <div>
      <div class="album">
      <div class="container p-0 py-2">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            <hotplace-list-item v-for="hotplace in hotplaces" :key="hotplace.hotplaceNo" v-bind="hotplace"></hotplace-list-item> 
        </div>
      </div>
    </div>
    </div>      
    
  </div>
</template>

<script>
import { listHotplace } from "@/api/hotplace";
import HotplaceListItem from './item/HotplaceListItem.vue';
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
