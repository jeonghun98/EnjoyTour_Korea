<template>
  <b-modal id="modal-login" title="로그인">
    <b-form class="text-left">
      <b-form-group label="아이디:" label-for="userid">
        <b-form-input
          id="userid"
          v-model="user.userid"
          required
          placeholder="아이디 입력...."
          
        ></b-form-input>
      </b-form-group>
      <b-form-group label="비밀번호:" label-for="userpwd">
        <b-form-input
          type="password"
          id="userpwd"
          v-model="user.userpwd"
          required
          placeholder="비밀번호 입력...."
          
        ></b-form-input>
      </b-form-group>
      <b-button type="button" variant="primary" class="m-1">
        로그인
      </b-button>
    </b-form>
  </b-modal>
</template>

<script>
export default {
  name: "UserLogin",
  components: {},
  data() {
    return {
      // isLoginError: false,
      user: {
        userid: null,
        userpwd: null,
      },
    };
  },
  created() {},
  methods: {},
};
</script>

<style scoped></style>
