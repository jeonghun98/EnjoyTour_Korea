<template>
  <!-- 전체 글 -->
  <div class="my-3 p-3 bg-body rounded">
    <div class="col-lg-12 col-md-10 col-sm-12">
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
            class="btn btn-outline-primary mb-3"
            @click="moveList"
          >
            글목록
          </button>
          <button
            type="button"
            id="btn-mv-modify"
            class="btn btn-outline-success mb-3 ms-1"
            @click="moveModifyArticle"
          >
            글수정
          </button>
          <button
            type="button"
            id="btn-delete"
            class="btn btn-outline-danger mb-3 ms-1"
            @click="deleteArticle"
          >
            글삭제
          </button>
        </div>
      </div>
    </div>
  </div>
  <!-- 전체 글 end-->
</template>

<script>
import moment from "moment";
import http from "@/api/http";

export default {
  name: "noticeView",
  components: {},
  data() {
    return {
      notice: {},
    };
  },
  created() {
    http.get(`/notice/${this.$route.params.noticeNo}`).then(({ data }) => {
      this.notice = data;
      // console.log(data);
    });
  },
  methods: {
    moveModifyArticle() {
      this.$router.replace({
        name: "noticeModify",
        params: { noticeNo: this.notice.noticeNo },
      });
    },
    deleteArticle() {
      if (confirm("정말로 삭제하시겠습니까?")) {
        http.delete(`/notice/${this.$route.params.noticeNo}`).then(({ data }) => {
          let msg = "삭제 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "삭제가 완료되었습니다.";
          }
          alert(msg);
          this.$router.push({ name: "noticeList" });
        });
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
