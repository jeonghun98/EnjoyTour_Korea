<template>
  <b-modal  id="modal-join" ref="modal-join" title="회원가입" hide-footer>
    <b-form class="text-left" @submit="onSubmit" @reset="onReset">
      <b-form-group label="아이디:" label-for="userid"
        :valid-feedback="idCheckMsg"
        :invalid-feedback="idCheckMsg"
        :state="idCheckResult"
        >
        <b-form-input
          id="userid"
          ref="userid"
          v-model="user.userid"
          required
          placeholder="아이디 입력...."
        ></b-form-input>
        <!-- <div class="mt-2">Value: {{ user.userid }}</div> -->
      </b-form-group>
      <b-form-group label="비밀번호:" label-for="userpwd">
        <b-form-input
          type="password"
          id="userpwd"
          ref="userpwd"
          v-model="user.userpw"
          required
          placeholder="비밀번호 입력...."
        ></b-form-input>
        <!-- <div class="mt-2">Value: {{ user.userpwd }}</div> -->
      </b-form-group>
      <b-form-group label="이름:" label-for="username">
        <b-form-input
          id="username"
          ref="username"
          v-model="user.username"
          required
          placeholder="이름 입력...."
        ></b-form-input>
        <!-- <div class="mt-2">Value: {{ user.userpwd }}</div> -->
      </b-form-group>
      <b-form-group label="이메일 입력:" label-for="useremail">
        <b-form-input
          id="useremail"
          ref="useremail"
          v-model="user.useremail"
          required
          placeholder="이메일 입력...."
        ></b-form-input>
      </b-form-group>
        <b-form-group label="전화번호 입력:" label-for="userphone">
        <b-form-input
          id="userphone"
          ref="userphone"
          v-model="user.userphone"
          required
          placeholder="전화번호 입력...."
        ></b-form-input>
        <!-- <div class="mt-2">Value: {{ user.userpwd }}</div> -->
      </b-form-group>

      <b-form-row>
          <b-button type="submit" variant="primary" class="ml-auto">
            회원가입
          </b-button>
      </b-form-row>
    </b-form>
  </b-modal>
</template>

<script>


import { join, checkId } from '@/api/member';
export default {
  name: "UserJoin",
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
      idCheckBool : null,
      idCheckMsg: "",

    };
  },
  created() {},
  computed: {
    idCheckResult() {
      return this.idCheckBool;
    },
  },
  watch: {
    // 아이디 입력 변화에 따른 검사
    "user.userid": function () {
      if (this.user.length < 5 || this.user.length > 16) {
        this.idCheckMsg = "아이디는 5자 이상 16자 이하 입니다.";
        this.idCheckBool = false;
      } else {
        checkId(this.user.userid, ({ data }) => {
          if (data == 0) {
            this.idCheckMsg = this.user.userid + "는 사용할 수 있습니다.";
            this.idCheckBool = true;
          } else {
            this.idCheckMsg = this.user.userid + "는 사용할 수 없습니다.";
            this.idCheckBool = false;
          }
        });
      }
    },
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();
      this.checkValue();
    },
    onReset(event) {
      event.preventDefault();
      this.user.username = "";
      this.user.userid = "";
      this.user.userpw = "";
      this.user.useremail = "";
      this.user.userphone = "";
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
      err &&
        !this.user.userid &&
        ((msg = "아이디를 입력해주세요"),
        (err = false),
        this.$refs["userid"].focus());
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
      else this.joinUser();
    },
    joinUser() {
      console.log(`회원가입: ${this.user.userid}, ${this.user.userpw}, ${this.user.username}, ${this.user.useremail}, ${this.user.userphone}`);
      // this.user.userid="";
      // this.user.userpw="";
      // this.user.username="";
      // this.user.useremail="";
      // this.user.userphone="";
      // this.$refs['modal-join'].hide();

      let user = {
        username: this.user.username,
        userid: this.user.userid,
        userpw: this.user.userpw,
        useremail: this.user.useremail,
        userphone: this.user.userphone,
      };
      join(
        user,
        ({ data }) => {
          let msg = "회원가입 처리시 문제가 발생했습니다.";
          if (data.message === "success") {
            msg = "회원가입이 완료되었습니다.";
            console.log("UserJoinModal - ", data);
          }
          alert(msg);
          this.$refs['modal-join'].hide();
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
