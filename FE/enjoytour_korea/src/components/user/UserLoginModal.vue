<template>
  <b-modal  id="modal-login" ref="modal-login" title="로그인" hide-footer>
    <b-form class="text-left">
      <b-alert show variant="danger" v-if="isLoginError">아이디 또는 비밀번호를 확인하세요.</b-alert>
      <b-form-group label="아이디:" label-for="userid">
        <b-form-input
          id="userid"
          v-model="user.userid"
          required
          placeholder="아이디 입력...."
          @keyup.enter="confirm"
        ></b-form-input>
        <!-- <div class="mt-2">Value: {{ user.userid }}</div> -->
      </b-form-group>
      <b-form-group label="비밀번호:" label-for="userpw">
        <b-form-input
          type="password"
          id="userpw"
          v-model="user.userpw"
          required
          placeholder="비밀번호 입력...."
          @keyup.enter="confirm"
        ></b-form-input>
        <!-- <div class="mt-2">Value: {{ user.userpwd }}</div> -->
      </b-form-group>

      <b-form-row>
          <b-button type="button" variant="primary" class="ml-auto" @click="confirm">
            로그인
          </b-button>
          <b-button type="button" variant="outline-primary" class="ml-1">
            아이디 찾기
          </b-button>
          <b-button type="button" variant="outline-primary" class="ml-1">
            비밀번호 찾기
          </b-button>
          <b-button type="button" variant="danger" class="ml-1" @click="cancel"> 취소 </b-button>
      </b-form-row>
    </b-form>
  </b-modal>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "UserLogin",
  components: {},
  data() {
    return {
      // isLoginError: false,
      user: {
        userid: null,
        userpw: null,
      },
    };
  },
  created() {},
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError", "userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user);
      console.log(this.user);
      let token = sessionStorage.getItem("access-token");
      console.log("1. confirm() token >> " + token);
      if (this.isLogin) {
        await this.getUserInfo(token);
        console.log("4. confirm() userInfo :: ", this.userInfo);
        // this.$refs['modal-login'].hide(); //error
        // this.$emit("hideLoginModal");
      }
    },
    // confirm() {
    //   console.log(`로그인 완료: id-${this.user.userid}, pw-${this.user.userpwd}`);
    //   this.user.userid="";
    //   this.user.userpwd="";
    //   this.$refs['modal-login'].hide();
    // },
    cancel(){
      console.log("취소");
      this.user.userid="";
      this.user.userpw="";
      this.$refs['modal-login'].hide();
    },

  },
};
</script>

<style scoped></style>
