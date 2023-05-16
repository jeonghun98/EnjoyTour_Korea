<template>
  <footer class="navbar navbar-expand navbar-light bg-light container justify-content-end">
    <div class="row">
      <ul class="col navbar-nav">
        <li>
          <a href="#" class="nav-link text-secondary home-link">홈</a>
        </li>
        <li>
          <a href="#" id="notice" class="nav-link text-secondary inform-link">여행정보공유</a>
        </li>
        <li>
          <a href="#" class="nav-link text-secondary attraction-link">관광지 정보</a>
        </li>
        <li>
          <a href="#" class="nav-link text-secondary plan-link">나만의 여행계획</a>
        </li>
        <li>
          <a href="#" id="hotPlace" class="nav-link text-secondary hotPlace-link">HotPlace</a>
        </li>
        <li>
          <a href="#" class="nav-link text-secondary">사이트소개</a>
        </li>
        <li>
          <a href="#" class="nav-link text-secondary">개인정보처리방침</a>
        </li>
        <li>
          <a href="#" class="nav-link text-secondary">이용약관</a>
        </li>
        <li>
          <label class="nav-link text-secondary">&copy; SSAFY Corp.</label>
        </li>
      </ul>
    </div>
  </footer>
</template>

<script>
export default {
  name: "TheFooter",
  components: {},
  data() {
    return {
      message: "",
    };
  },
  created() {},
  methods: {},
};
</script>

<style scoped></style>
