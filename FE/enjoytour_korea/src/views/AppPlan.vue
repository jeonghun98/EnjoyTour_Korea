<template>
  <b-container class="bv-example-row mt-3">
    <div class="row bg-image p-1 me-1 ms-1 shadow-1-strong rounded content-title" id="background">
      <div class="col-12 text-light">
        <h3 class="fw-bold pt-3">여행계획</h3>
        <span class="content-desc">어떤 계획에 어떤 관광지가 있는지 찾아보세요</span>
      </div>
    </div>
    <router-view></router-view>
  </b-container>
</template>

<script>
export default {
  name: "AppPlanList",
  components: {
  },
};
</script>

<style>
#background {
  background-image: url("@/assets/img/bg0.jpg");
  background-size: cover;
  height: 105px;
}
</style>
