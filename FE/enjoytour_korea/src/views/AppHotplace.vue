<template>
  <div class="container" id="trip-plan-contents">
    <div
      class="row bg-image p-1 me-1 ms-1 shadow-1-strong rounded content-title"
      id="background"
    >
      <div class="col-12 text-light">
        <h3 class="fw-bold pt-3">HotPlace</h3>
        <span class="content-desc">나만의 HotPlace를 공유해보세요</span>
        
      </div>
    </div>
    <!-- <hotplace-list></hotplace-list> -->
    <router-view></router-view>
    
  </div>
</template>

<script>
// import HotplaceList from "@/components/hotplace/HotplaceList.vue"
export default {
  name: "AppHotplace",
  components: {
    // HotplaceList,
  },
};
</script>

<style scoped>
#background {
  background-image: url("@/assets/img/bg0.jpg");
  background-size: cover;
  height: 105px;
}
</style>
