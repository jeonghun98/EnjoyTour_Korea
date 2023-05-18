<template>
  <div class="row p-1">
    <button type="button" id="btn-mv-register" class="btn btn-outline-primary btn-sm"
          @click="moveWrite">
                핫플 등록하기
          </button>
    <div class="album">
      <div class="container p-0 py-2">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            <hotplace-list-item></hotplace-list-item> <!-- v-for해야함 -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import HotplaceListItem from './item/HotplaceListItem.vue';

export default {
  name: "HotplaceList",
  components: {
    HotplaceListItem,
  },
  data() {
    return {
      message: "",
    };
  },
  created() {},
  methods: {
    moveWrite() {
        alert("등록하기 버튼");
    },
  },
};
</script>

<style scoped></style>
