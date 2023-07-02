<template>
  <!-- 전체 글 -->
  <div class="my-3 p-3 bg-body rounded">
    <div class="col-lg-12 col-md-10 col-sm-12">
      <form id="form-modify" method="POST" action="" @submit="onSubmit" @reset="onReset">
        <div class="mb-3">
          <label for="subject" class="form-label">제목 : </label>
          <input type="text" class="form-control" id="subject" v-model="notice.title" />
        </div>
        <div class="mb-3">
          <label for="content" class="form-label">내용 : </label>
          <textarea
            class="form-control"
            id="content"
            name="content"
            rows="7"
            v-model="notice.content"
          >
          </textarea>
        </div>
        <div class="col-auto text-center">
          <button
            type="submit"
            id="btn-inform-register"
            class="btn btn-outline-primary mt-3 mb-3 mr-3"
            v-if="this.type === 'write'"
          >
            글작성
          </button>

          <button
            type="submit"
            id="btn-modify"
            class="btn btn-outline-success mt-3 mb-3 mr-3"
            @submit="onSubmit"
            v-else
          >
            글수정
          </button>
          <button type="reset" class="btn btn-outline-danger mt-3 mb-3 mr-3">초기화</button>
          <button
            type="button"
            id="btn-list"
            class="btn btn-outline-primary mt-3 mb-3 mr-3"
            @click="moveList"
          >
            글목록
          </button>
        </div>
      </form>
    </div>
  </div>
  <!-- 전체 글 end-->
</template>

<script>
import { writeArticle, modifyArticle, getArticle } from "@/api/notice";
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  name: "NoticeInputItem",
  components: {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  data() {
    return {
      notice: {
        noticeNo: 0,
        userId: "",
        title: "",
        content: "",
      },
      isUserid: false,
    };
  },
  props: {
    type: { type: String },
  },
  created() {
    if (this.type === "modify") {
      let param = this.$route.params.noticeNo;
      getArticle(
        param,
        ({ data }) => {
          this.notice = data;
        },
        (error) => {
          console.log(error);
        }
      );
      this.isUserid = true;
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      // !this.notice.userId &&
      //   ((msg = "작성자 입력해주세요"), (err = false), this.$refs.userId.focus());
      // err &&
      !this.notice.title && ((msg = "제목 입력해주세요"), (err = false), this.$refs.title.focus());
      err &&
        !this.notice.content &&
        ((msg = "내용 입력해주세요"), (err = false), this.$refs.content.focus());

      if (!err) alert(msg);
      else this.type === "write" ? this.WriteNotice() : this.modifyNotice();
    },
    onReset(event) {
      event.preventDefault();
      this.notice.title = "";
      this.notice.content = "";
    },
    WriteNotice() {
      let param = {
        userId: this.userInfo.userid,
        title: this.notice.title,
        content: this.notice.content,
      };
      writeArticle(
        param,
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    modifyNotice() {
      let param = {
        noticeNo: this.notice.noticeNo,
        userId: this.notice.userId,
        title: this.notice.title,
        content: this.notice.content,
      };
      modifyArticle(
        param,
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveList() {
      this.$router.push({ name: "noticeList" });
    },
  },
};
</script>

<style scoped></style>
