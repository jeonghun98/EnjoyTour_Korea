<template>
  <b-modal id="modal-auth" ref="modal-auth" title="친구와 수정" hide-footer>
    <b-form class="text-left" @submit="onSubmit">
      <b-form-group
        label="친구ID:"
        label-for="userid"
        :valid-feedback="idCheckMsg"
        :invalid-feedback="idCheckMsg"
        :state="idCheckResult"
      >
        <b-form-input
          id="userid"
          ref="userid"
          v-model="userid"
          required
          placeholder="친구ID 입력...."
        ></b-form-input>
      </b-form-group>

      <b-form-row>
        <b-button type="submit" variant="primary" class="ml-auto"> 친구추가 </b-button>
      </b-form-row>
    </b-form>
  </b-modal>
</template>

<script>
import { checkId } from "@/api/member";
import { authAdd } from "@/api/plan";
const memberStore = "memberStore";
import { mapState } from "vuex";

export default {
  name: "PlanAddAuth",
  components: {},
  props: {
    planNo: Number,
  },
  data() {
    return {
      // isLoginError: false,
      userid: "",
      idCheckBool: null,
      idCheckMsg: "",
    };
  },
  created() {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    idCheckResult() {
      return this.idCheckBool;
    },
  },
  watch: {
    // 아이디 입력 변화에 따른 검사
    userid: function () {
      if (this.userInfo.userid == this.userid) {
        this.idCheckMsg = "자신의 아이디를 제외하고 입력해주세요";
        this.idCheckBool = false;
      } else if (this.userid.length == 0) {
        this.idCheckMsg = "아이디를 입력해주세요";
        this.idCheckBool = false;
      } else {
        checkId(this.userid, ({ data }) => {
          if (data == 1) {
            this.idCheckMsg = this.userid + "라는 사용자는 있습니다.";
            this.idCheckBool = true;
          } else {
            this.idCheckMsg = this.userid + "라는 사용자는 없습니다.";
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
    checkValue() {
      // 입력값 체크하기
      // 입력값이 없을 경우 각 항목에 맞는 메세지를 출력
      let err = true;
      let msg = "";
      !this.userid && ((msg = "아이디를 입력해주세요"), (err = false), this.$refs.userid.focus());

      if (!err) alert(msg);
      else this.addAuth();
    },
    addAuth() {
      //   console.log("친구추가: ", this.userid);
      let planList = {
        planNo: this.planNo,
        userId: this.userid,
      };
      //   console.log("친구추가: ", this.planList);
      authAdd(
        planList,
        ({ data }) => {
          let msg = "친구추가 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "친구추가가 완료되었습니다.";
          }
          alert(msg);
          this.$refs["modal-auth"].hide();
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
