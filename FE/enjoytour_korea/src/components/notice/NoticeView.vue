<template>
  <!-- 전체 글 -->
  <div class="my-3 bg-body rounded">
    <div class="row my-2">
      <h4 class="text-secondary fw-bold">{{ notice.noticeNo }}. {{ notice.title }}</h4>
    </div>
    <div>
      <div class="col-md-8">
        <div class="clearfix align-content-center">
          <span>
            <img
              class="avatar me-2 float-md-start bg-light p-2"
              src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg"
            />
          </span>
          <span>
            <span class="fw-bold">{{ notice.userId }}</span> <br />
            <span class="text-secondary fw-light">
              {{ notice.registerTime | dateFormat }} 조회 : {{ notice.hit }}
            </span>
          </span>
        </div>
      </div>
      <div class="divider mb-3"></div>
      <div class="text-secondary">{{ notice.content }}</div>
      <div class="divider mt-3 mb-3"></div>
      <div class="d-flex justify-content-end">
        <button
          type="button"
          id="btn-list"
          class="btn btn-outline-primary mt-3 mb-3 mr-3"
          @click="moveList"
        >
          글목록
        </button>
        <span v-if="userInfo != null && userInfo.userid === 'admin'">
          <button
            type="button"
            id="btn-mv-modify"
            class="btn btn-outline-success mt-3 mb-3 mr-3"
            @click="moveModifyNotice"
          >
            글수정
          </button>
          <button
            type="button"
            id="btn-delete"
            class="btn btn-outline-danger mt-3 mb-3 mr-3"
            @click="deleteNotice"
          >
            글삭제
          </button>
        </span>
      </div>
    </div>
  </div>
  <!-- 전체 글 end-->
</template>

<script>
import moment from "moment";
import { getArticle, deleteArticle } from "@/api/notice";
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  name: "noticeView",
  components: {},
  data() {
    return {
      notice: {},
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
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
  },
  methods: {
    moveModifyNotice() {
      this.$router.replace({
        name: "noticeModify",
        params: { noticeNo: this.notice.noticeNo },
      });
    },
    deleteNotice() {
      if (confirm("정말로 삭제하시겠습니까?")) {
        let param = this.$route.params.noticeNo;
        deleteArticle(
          param,
          ({ data }) => {
            let msg = "삭제 처리시 문제가 발생했습니다.";
            if (data === "success") {
              msg = "삭제가 완료되었습니다.";
            }
            alert(msg);
            this.$router.push({ name: "noticeList" });
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },
    moveList() {
      this.$router.push({ name: "noticeList" });
    },
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
    },
  },
};
</script>
