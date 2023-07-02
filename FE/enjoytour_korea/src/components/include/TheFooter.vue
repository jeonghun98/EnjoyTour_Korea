<template>
  <footer class="navbar navbar-expand navbar-light bg-light container justify-content-end">
    <div class="row">
      <ul class="col navbar-nav">
        <li>
          <b-nav-item to="/" exact exact-active-class="active"><b>홈</b></b-nav-item>
        </li>
        <li>
          <b-nav-item to="/notice" exact exact-active-class="active"><b>공지사항</b></b-nav-item>
        </li>
        <li>
          <b-nav-item to="/attraction" exact exact-active-class="active"
            ><b>한국 여행지</b></b-nav-item
          >
        </li>
        <li>
          <b-nav-item to="/plan" exact exact-active-class="active"
            ><b>모두의 여행계획</b></b-nav-item
          >
        </li>
        <li>
          <b-nav-item to="/myplan" exact exact-active-class="active" v-if="userInfo"
            ><b>나의 여행계획</b></b-nav-item
          >
        </li>
        <li>
          <b-nav-item to="/hotplace" exact exact-active-class="active"
            ><b>핫플자랑하기</b></b-nav-item
          >
        </li>
        <li>
          <a href="https://github.com/jeonghun98/EnjoyTour_Korea" class="nav-link text-secondary"
            >사이트소개</a
          >
        </li>
        <li>
          <a href="https://github.com/jeonghun98/EnjoyTour_Korea" class="nav-link text-secondary"
            >개인정보처리방침</a
          >
        </li>
        <li>
          <a href="https://github.com/jeonghun98/EnjoyTour_Korea" class="nav-link text-secondary"
            >이용약관</a
          >
        </li>
        <li>
          <label class="nav-link text-secondary">&copy; Korea Corp.</label>
        </li>
      </ul>
    </div>
  </footer>
</template>

<script>
import { mapState } from "vuex";

const memberStore = "memberStore";
export default {
  name: "TheFooter",
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
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
