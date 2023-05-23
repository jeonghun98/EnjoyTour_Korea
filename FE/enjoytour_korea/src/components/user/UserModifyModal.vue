<template>
  <b-modal  id="modal-modify" ref="modal-modify" title="회원정보 수정" hide-footer>
    <b-form class="text-left" @submit="onSubmit" @reset="onReset">
      <b-form-group label="아이디:" label-for="userid"
        >
        <b-form-input
          id="userid"
          ref="userid"
          v-model="userInfo.userid"
          required
          readonly
        ></b-form-input>
      </b-form-group>
      <b-form-group label="비밀번호:" label-for="userpwd">
        <b-form-input
          type="password"
          id="userpwd"
          ref="userpwd"
          v-model="userInfo.userpw"
          required
          placeholder="비밀번호 입력...."
        ></b-form-input>
      </b-form-group>
      <b-form-group label="이름:" label-for="username">
        <b-form-input
          id="username"
          ref="username"
          v-model="userInfo.username"
          required
          placeholder="이름 입력...."
        ></b-form-input>
      </b-form-group>
      <b-form-group label="이메일 입력:" label-for="useremail">
        <b-form-input
          id="useremail"
          ref="useremail"
          v-model="userInfo.useremail"
          required
          placeholder="이메일 입력...."
        ></b-form-input>
      </b-form-group>
        <b-form-group label="전화번호 입력:" label-for="userphone">
        <b-form-input
          id="userphone"
          ref="userphone"
          v-model="userInfo.userphone"
          required
          placeholder="전화번호 입력...."
        ></b-form-input>
      </b-form-group>

      <b-form-row>
          <b-button type="submit" variant="primary" class="ml-auto">
            수정하기
          </b-button>
          <b-button type="reset" variant="primary" class="ml-1">
            취소
          </b-button>
      </b-form-row>
    </b-form>
  </b-modal>
</template>

<script>


import { update } from '@/api/member';
import { mapState } from "vuex";

export default {
  name: "UserModify",
  components: {},
  data() {
    return {
      // isLoginError: false,
      user: {
        userid: "",
        userpw: "",
        username:"",
        useremail: "",
        userphone: "",
      },

    };
  },
  created() {
    this.user.username = this.userInfo.username;
    this.user.userpw = this.userInfo.userpw;
    this.user.userid = this.userInfo.userid;
    this.user.useremail = this.userInfo.useremail;
    this.user.userphone = this.userInfo.userphone;
  },
  computed: {
    ...mapState("memberStore", ["userInfo"]),
  },
  watch: {
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();
      this.checkValue();
    },
    onReset(event) {
      event.preventDefault();
      this.$refs['modal-modify'].hide();
    },
    checkValue() {
      // 입력값 체크하기
      // 입력값이 없을 경우 각 항목에 맞는 메세지를 출력
      let err = true;
      let msg = "";
      !this.user.username &&
        ((msg = "이름을 입력해주세요"),
        (err = false),
        this.$refs["username"].focus());
      // err &&
      //   !this.user.userid &&
      //   ((msg = "아이디를 입력해주세요"),
      //   (err = false),
      //   this.$refs["userid"].focus());
      err &&
        !this.user.userpw &&
        ((msg = "비밀번호를 입력해주세요"),
        (err = false),
        this.$refs["userpw"].focus());
      err &&
        !this.user.useremail &&
        ((msg = "이메일을 입력해주세요"),
        (err = false),
        this.$refs["useremail"].focus());
      err &&
        !this.user.userphone &&
        ((msg = "전화번호를 입력해주세요"),
        (err = false),
        this.$refs["userphone"].focus());

      if (!err) alert(msg);
      else this.modifyUser();
    },
    modifyUser() {
      console.log(`회원정보수정: ${this.user.userid}, ${this.user.userpw}, ${this.user.username}, ${this.user.useremail}, ${this.user.userphone}`);
  
      let user = {
        username: this.user.username,
        userid: this.user.userid,
        userpw: this.user.userpw,
        useremail: this.user.useremail,
        userphone: this.user.userphone,
      };
      console.log("UserModifyModal: "+user);
      update(
        user,
        ({ data }) => {
          let msg = "회원 정보 수정시 문제가 발생했습니다.";
          if (data.message === "success") {
            msg = "정보 수정이 완료되었습니다.";
            console.log("UserJoinModal - ", data);
          }
          alert(msg);
          this.$refs['modal-modify'].hide();
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style scoped></style>
