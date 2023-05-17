<template>
  <div class="container" id="trip-plan-contents">
    <div class="row bg-image p-1 me-1 ms-1 shadow-1-strong rounded content-title" id="background">
      <div class="col-12 text-light">
        <h3 class="fw-bold pt-3">공지사항</h3>
        <span class="content-desc">EnjoyTrip의 새로운 정보를 알려줄게요</span>
      </div>
    </div>
    <router-view></router-view>
  </div>
</template>

<script>
// import NoticeList from "@/components/notice/NoticeList.vue";
export default {
  name: "AppNoticeList",
  components: {
    // NoticeList,
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

