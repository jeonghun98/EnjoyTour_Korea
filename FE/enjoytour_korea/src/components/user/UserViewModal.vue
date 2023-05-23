<template>
  <b-modal
    id="modal-view"
    ref="modal-view"
    title="마이페이지"
    hide-footer
    no-stacking
  >
    <b-form id="form-view" method="" action="">
      <div class="mb-3">
        아이디:
        <span class="float-end" id="mypage-id">{{ userInfo.userid }}</span>
      </div>
      <div class="mb-3">
        이름:
        <span class="float-end" id="mypage-name">{{ userInfo.username }}</span>
      </div>
      <div class="mb-3">
        전화번호:
        <span class="float-end" id="mypage-age">{{ userInfo.userphone }}</span>
      </div>
      <div class="mb-3">
        이메일:
        <span class="float-end" id="mypage-email">{{
          userInfo.useremail
        }}</span>
      </div>

      <b-form-row>
        <b-button
          type="button"
          variant="outline-primary"
          class="ml-auto"
          v-b-modal.modal-modify
        >
          수정하기
        </b-button>
        <b-button
          type="button"
          variant="outline-danger"
          class="ml-1"
          @click="userDelete"
        >
          탈퇴하기
        </b-button>
      </b-form-row>
    </b-form>
  </b-modal>
</template>

<script>
import { mapState, mapActions } from "vuex";
import { deleteUser } from "@/api/member";

const memberStore = "memberStore";

export default {
  name: "UserView",
  components: {},
  data() {
    return {
      // isLoginError: false,
      user: {
        userid: "",
        userpw: "",
        username: "",
        useremail: "",
        userphone: "",
      },
    };
  },
  created() {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapActions("memberStore", ["userLogout"]),

    userDelete() {
      if (confirm("탈퇴하시겠습니까?")) {
        deleteUser(
          this.userInfo.userid,
          ({ data }) => {
            let msg = "회원 탈퇴 시 문제가 발생했습니다.";
            if (data.message === "success") {
              
              // 탈퇴 + 로그아웃
              this.userLogout(this.userInfo.userid);
              sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
              sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기

              this.$refs['modal-view'].hide();
              msg = "탈퇴 완료";
            }
            console.log(msg);
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },
  },
};
</script>

<style scoped></style>
