<template>
  <b-modal  id="modal-findpwd" ref="modal-findpwd" title="비밀번호 찾기" hide-footer>
    <b-form class="text-left">
      <b-form-group label="아이디:" label-for="userid">
        <b-form-input
          id="userid"
          v-model="user.userid"
          required
          placeholder="아이디 입력...."
          @keyup.enter="confirm"
        ></b-form-input>
      </b-form-group>
      <b-form-group label="이메일:" label-for="useremail">
        <p>입력한 이메일로 임시 비밀번호가 전송됩니다.</p>
        <b-form-input
          type="text"
          id="useremail"
          v-model="user.useremail"
          required
          placeholder="이메일 입력...."
          @keyup.enter="confirm"
        ></b-form-input>
      </b-form-group>

      <b-form-row>
          <b-button type="button" variant="outline-primary" class="ml-auto" @click="confirm">
            비밀번호 찾기
          </b-button>
          <b-button type="button" variant="danger" class="ml-1" @click="cancel"> 취소 </b-button>
      </b-form-row>
    </b-form>
  </b-modal>
</template>

<script>
import { sendEmail } from '@/api/member';

export default {
  name: "UserFindpwd",
  components: {},
  data() {
    return {
      user: {
        userid: "",
        useremail: "",
      },
    };
  },
  created() {},
  computed: {
  },
  methods: {
    confirm() {
      console.log("비밀번호 찾기 start");

      sendEmail(
        this.user,
        ({ data }) => {
          let msg = "문제가 발생했습니다.";
          if (data.message === "success") {
            msg = "메일로 임시 비밀번호 발급이 완료되었습니다.";
            console.log("UserFindpwdModal :sendEmail - ", data);
          }
          alert(msg);

          this.$refs['modal-findpwd'].hide();
        },
        (error) => {
          console.log(error);
        }
      );
    },

    cancel(){
      console.log("취소");
      this.user.userid="";
      this.user.useremail="";
      this.$refs['modal-findpwd'].hide();
    },

  },
};
</script>

<style scoped></style>
