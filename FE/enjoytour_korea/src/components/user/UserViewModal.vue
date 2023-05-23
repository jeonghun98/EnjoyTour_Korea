<template>
  <b-modal  id="modal-view" ref="modal-view" title="회원가입" hide-footer>
    <b-form class="text-left" @submit="onSubmit" @reset="onReset">
      <b-form-group label="아이디:" label-for="userid"
        >
        <b-form-input
          id="userid"
          ref="userid"
          value="userinfo.userid"
          readonly
        ></b-form-input>
        <!-- <div class="mt-2">Value: {{ user.userid }}</div> -->
      </b-form-group>
      <!-- <b-form-group label="비밀번호:" label-for="userpwd">
        <b-form-input
          type="password"
          id="userpwd"
          ref="userpwd"
          value="userinfo.userpw"
          required
          readonly
        ></b-form-input> -->
        <!-- <div class="mt-2">Value: {{ user.userpwd }}</div> -->
      <!-- </b-form-group> -->
      <b-form-group label="이름:" label-for="username">
        <b-form-input
          id="username"
          ref="username"
          value="userinfo.username"
          required
        ></b-form-input>
        <!-- <div class="mt-2">Value: {{ user.userpwd }}</div> -->
      </b-form-group>
      <b-form-group label="이메일:" label-for="useremail">
        <b-form-input
          id="useremail"
          ref="useremail"
          value="userinfo.useremail"
          readonly
        ></b-form-input>
      </b-form-group>
        <b-form-group label="전화번호:" label-for="userphone">
        <b-form-input
          id="userphone"
          ref="userphone"
          value="userinfo.userphone"
          readonly
        ></b-form-input>
        <!-- <div class="mt-2">Value: {{ user.userpwd }}</div> -->
      </b-form-group>

      <b-form-row>
          <b-button type="button" variant="primary" class="ml-auto">
            회원정보 수정
          </b-button>
          <b-button type="button" variant="primary" class="ml-auto">
            회원 탈퇴
          </b-button>
          <b-button type="button" variant="primary" class="ml-auto" @click="cancel">
            닫기
          </b-button>
      </b-form-row>
    </b-form>
  </b-modal>
</template>

<script>
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "UserView",
  components: {},
  data() {
    return {
      // isLoginError: false,
      // user: {
      //   userid: "",
      //   userpw: "",
      //   username: "",
      //   useremail: "",
      //   userphone: "",
      // },

    };
  },
  created() {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    cancel(){
      console.log("닫기");
      this.$refs['modal-view'].hide();
    },
  },
};
</script>

<style scoped></style>
