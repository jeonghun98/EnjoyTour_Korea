<template>
  <!-- 전체 글 -->
  <div class="my-3 p-3 bg-body rounded">
    <div class="col-lg-12 col-md-10 col-sm-12">
      <div class="row align-self-center mb-2">
        <div class="col-md-2 text-start">
          <button type="button" id="btn-mv-register" class="btn btn-outline-primary btn-sm" @click="moveWrite">
                글쓰기
          </button>
          <!-- <c:if test="${userinfo.userId eq 'admin'}">
              <button type="button" id="btn-mv-register" class="btn btn-outline-primary btn-sm">
                글쓰기
              </button>
            </c:if> -->
        </div>
        <div class="col-md-7 offset-5 d-flex justify-content-end">
          <form class="d-flex" id="form-search" action="">
            <input type="hidden" name="action" value="list" />
            <input type="hidden" name="pgno" value="1" />
            <select
              name="key"
              id="key"
              class="form-select form-select-sm ms-5 me-1 w-50"
              aria-label="검색조건"
            >
              <option selected>검색조건</option>
              <option value="noticeno">글번호</option>
              <option value="title">제목</option>
              <option value="user_id">작성자</option>
            </select>
            <div class="input-group input-group-sm">
              <input
                type="text"
                name="word"
                id="word"
                class="form-control"
                placeholder="검색어..."
              />
              <button id="btn-search" class="btn btn-dark" type="button">검색</button>
            </div>
          </form>
          <div>
            <input type="hidden" name="action" value="sort" />
            <input type="hidden" name="pgno" value="1" />
            <select
              name="sort-key"
              id="sort-key"
              class="form-select form-select-sm ms-1"
              aria-label="정렬"
              onchange="changeSort()"
            >
              <!-- <option value="article_no" ${sortvalue == "article_no" ? 'selected="selected"' : '' }>글번호</option>
                <option value="subject" ${sortvalue == "subject" ? 'selected="selected"' : '' }>제목</option>
                <option value="user_id" ${sortvalue == "user_id" ? 'selected="selected"' : '' }>작성자</option>
                <option value="hit" ${sortvalue == "hit" ? 'selected="selected"' : '' }>조회수</option>
                <option value="register_time" ${sortvalue == "register_time" ? 'selected="selected"' : '' }>작성일</option> -->
            </select>
          </div>
        </div>
      </div>
      <table class="table table-hover">
        <thead>
          <tr class="text-center">
            <th scope="col">글번호</th>
            <th scope="col">제목</th>
            <th scope="col">작성자</th>
            <th scope="col">조회수</th>
            <th scope="col">작성일</th>
          </tr>
        </thead>
        <tbody>
          <!-- 하위 component인 ListRow에 데이터 전달(props) -->
          <notice-list-item v-for="notice in notices" :key="notice.noticeNo" v-bind="notice" />
        </tbody>
      </table>
    </div>
    <!-- <div class="row">${navigation.navigator}</div> -->
  </div>
</template>

<script>
import http from "@/api/http";
import NoticeListItem from "./item/NoticeListItem.vue";

export default {
  name: "NoticeList",
  components: {
    NoticeListItem,
  },
  data() {
    return {
      notices: [],
      // fields: [
      //   { key: "noticeNo", label: "글번호", tdClass: "tdClass" },
      //   { key: "title", label: "제목", tdClass: "tdSubject" },
      //   { key: "userId", label: "작성자", tdClass: "tdClass" },
      //   { key: "registerTime", label: "작성일", tdClass: "tdClass" },
      //   { key: "hit", label: "조회수", tdClass: "tdClass" },
      // ],
    };
  },
  created() {
    http.get(`/notice`).then(({ data }) => {
      // console.log(data);
      this.notices = data;
    });
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "noticeWrite" });
    },
    // viewArticle(notice) {
    //   this.$router.push({
    //     name: "noticeView",
    //     params: { noticeNo: notice.noticeNo },
    //   });
    // },
  },
};
</script>
