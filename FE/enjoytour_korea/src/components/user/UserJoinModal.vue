<template>
  <b-modal  id="modal-join" ref="modal-join" title="회원가입" hide-footer>
    <b-form class="text-left">
      <b-form-group label="아이디:" label-for="userid">
        <b-form-input
          id="userid"
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
          v-model="user.userpwd"
          required
          placeholder="비밀번호 입력...."
        ></b-form-input>
        <!-- <div class="mt-2">Value: {{ user.userpwd }}</div> -->
      </b-form-group>
      <b-form-group label="이름:" label-for="username">
        <b-form-input
          id="username"
          v-model="user.username"
          required
          placeholder="이름 입력...."
        ></b-form-input>
        <!-- <div class="mt-2">Value: {{ user.userpwd }}</div> -->
      </b-form-group>
      <b-form-group label="이메일 입력:" label-for="useremail">
        <b-form-input
          id="useremail"
          v-model="user.useremail"
          required
          placeholder="이메일 입력...."
        ></b-form-input>
      </b-form-group>
        <b-form-group label="전화번호 입력:" label-for="userphone">
        <b-form-input
          id="userphone"
          v-model="user.userphone"
          required
          placeholder="전화번호 입력...."
        ></b-form-input>
        <!-- <div class="mt-2">Value: {{ user.userpwd }}</div> -->
      </b-form-group>

      <b-form-row>
          <b-button type="button" variant="primary" class="ml-auto" @click="confirm">
            회원가입
          </b-button>
      </b-form-row>
    </b-form>
  </b-modal>
</template>

<script>
export default {
  name: "UserJoin",
  components: {},
  data() {
    return {
      // isLoginError: false,
      user: {
        userid: "",
        userpwd: "",
        username:"",
        useremail: "",
        userphone: "",
      },
    };
  },
  created() {},
  computed() {},
  methods: {
    confirm() {
      console.log(`회원가입: ${this.user.userid}, ${this.user.userpwd}, ${this.user.username}, ${this.user.useremail}, ${this.user.userphone}`);
      this.user.userid="";
      this.user.userpwd="";
      this.user.username="";
      this.user.useremail="";
      this.user.userphone="";
      this.$refs['modal-join'].hide();
    },
  },
};
</script>

<style scoped></style>
