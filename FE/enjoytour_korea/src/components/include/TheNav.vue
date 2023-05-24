<template>
  <div>
    <nav class="navbar navbar-expand-lg bg-body">
      <div class="container pe-1 ps-1">
        <a
          class="navbar-brand link-light fw-bold fs-5 bg-dark p-1 home-link"
          href=""
        >
          ET
        </a>
        <span class="navbar-brand link-dark fw-bold fs-3">EnjoyTrip</span>
        <!-- 네비게이션 바 버튼 -->
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <div class="navbar-nav mb-2 mb-lg-0 p-1">
            <div class="input-group">
              <span class="input-group-text" id="basic-addon1">@</span>
              <input
                type="text"
                class="form-control"
                placeholder="검색"
                aria-label="Search"
                aria-describedby="basic-addon1"
              />
            </div>
          </div>
          <div class="navbar-nav me-auto"></div>

          <!-- 로그인 후 -->
          <b-navbar-nav class="ml-auto"  v-if="userInfo">
            <b-nav-item class="align-self-center">
              {{ userInfo.username }}({{ userInfo.userid }})님 환영합니다.
          </b-nav-item>
            <b-nav-item-dropdown right>
              <template #button-content>
                <b>{{ userInfo.username }}</b>
              </template>
              <b-dropdown-item href="#" v-b-modal.modal-view>
                마이페이지
                <user-view-modal></user-view-modal>
                <user-modify-modal></user-modify-modal>
              </b-dropdown-item>
              <b-dropdown-item href="#" @click.prevent="onClickLogout">
                로그아웃
              </b-dropdown-item>
            </b-nav-item-dropdown>
          </b-navbar-nav>
          <!-- 로그인 전 -->
          <b-navbar-nav class="ml-auto" v-else>
            <!-- <b-navbar-nav class="ml-auto" v-if="userInfo"> -->
            <b-nav-item-dropdown right>
              <template #button-content>
                <b>비회원</b>
              </template>
              <b-dropdown-item href="#" v-b-modal.modal-join>
                회원가입
                <user-join-modal></user-join-modal>
              </b-dropdown-item>
              <b-dropdown-item href="#" v-b-modal.modal-login>
                로그인
                <user-login-modal></user-login-modal>
                <user-findpwd-modal></user-findpwd-modal>
              </b-dropdown-item>
            </b-nav-item-dropdown>
          </b-navbar-nav>
        </div>
      </div>
    </nav>
    <!-- 상단 navbar end -->
    <div class="container bg-body">
      <div class="row">
        <div>
          <b-nav tabs align="center">
            <b-nav-item to="/" exact exact-active-class="active"
              ><b>홈</b></b-nav-item
            >
            <b-nav-item to="/notice" exact exact-active-class="active"
              ><b>공지사항</b></b-nav-item
            >
            <b-nav-item to="/attraction" exact exact-active-class="active"
              ><b>한국 여행지</b></b-nav-item
            >
            <b-nav-item to="/plan" exact exact-active-class="active"
              ><b>모두의 여행계획</b></b-nav-item
            >
            <b-nav-item to="/hotplace" exact exact-active-class="active"
              ><b>핫플자랑하기</b></b-nav-item
            >
          </b-nav>
        </div>
        <hr style="position: relative; top: -5px" />
      </div>
    </div>
  </div>
  <!-- <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="registboard()">확인</el-button>
        <el-button @click="openPopup = false">취소</el-button>
      </span> -->
</template>

<script>
import UserJoinModal from '../user/UserJoinModal.vue';
import UserLoginModal from "../user/UserLoginModal.vue";
import UserViewModal from '../user/UserViewModal.vue';
import UserModifyModal from '../user/UserModifyModal.vue';
import UserFindpwdModal from '../user/UserFindpwdModal.vue';

import { mapState, mapGetters, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "TheNav",
  components: {
    UserLoginModal,
    UserJoinModal,
    UserViewModal,
    UserModifyModal,
    UserFindpwdModal,
  },
  data() {
    return {
      show: false,
      message: "",
    };
  },
  created() {},
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userLogout"]),
    // ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      // this.SET_IS_LOGIN(false);
      // this.SET_USER_INFO(null);
      // sessionStorage.removeItem("access-token");
      // if (this.$route.path != "/") this.$router.push({ name: "main" });
      console.log(this.userInfo.userid);
      //vuex actions에서 userLogout 실행(Backend에 저장 된 리프레시 토큰 없애기
      //+ satate에 isLogin, userInfo 정보 변경)
      // this.$store.dispatch("userLogout", this.userInfo.userid);
      this.userLogout(this.userInfo.userid);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path != "/") this.$router.push({ name: "home" });
    },
  },
};
</script>

<style scoped></style>
