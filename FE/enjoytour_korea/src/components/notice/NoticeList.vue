<template>
  <!-- 전체 글 -->
  <div class="my-3 p-3 bg-body rounded">
    <div class="col-lg-12 col-md-10 col-sm-12">
      <div class="row align-self-center mb-2">
        <div class="col-md-2 text-start">
          <button
            type="button"
            id="btn-mv-register"
            class="btn btn-outline-primary btn-sm"
            @click="moveWrite"
          >
            글쓰기
          </button>
        </div>
        <div class="col-md-7 offset-5 d-flex justify-content-end">
          <form class="d-flex" id="form-search" @submit="onSearch">
            <input type="hidden" name="action" value="list" />
            <input type="hidden" name="pgno" value="1" />
            <b-select v-model="selected" :options="options"></b-select>
            <div class="input-group input-group-sm">
              <input
                type="text"
                name="word"
                id="word"
                class="form-control"
                placeholder="검색어..."
                v-model="searchWord"
              />
              <button id="btn-search" class="btn btn-dark" type="submit" @submit="onSearch">
                검색
              </button>
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
              <!-- 옵션들 추가 -->
            </select>
          </div>
        </div>
      </div>
      <b-row>
        <b-col>
          <b-table hover :items="notices" :fields="fields" @row-clicked="viewNotice">
            <template #cell(subject)="data">
              <router-link :to="{ name: 'noticeView', params: { noticeNo: data.item.noticeNo } }">
                {{ data.item.title }}
              </router-link>
            </template>
            <template #cell(registerTime)="data">
                {{ data.item.registerTime |  dateFormat }}
            </template>
          </b-table>
        </b-col>
      </b-row>
    </div>
  </div>
</template>

<script>
import { listArticle } from "@/api/notice";
import moment from "moment";

export default {
  name: "NoticeList",
  data() {
    return {
      notices: [],
      selected: null,
      searchWord: null,
      param: {
        pg: 1,
        spp: 20,
        key: null,
        word: null,
      },
      options: [
        { value: null, text: "검색조건" },
        { value: "notice_no", text: "글번호" },
        { value: "title", text: "제목" },
      ],
      fields: [
        { key: "noticeNo", label: "글번호", tdClass: "tdClass" },
        { key: "title", label: "제목", tdClass: "tdTitle" },
        { key: "userId", label: "작성자", tdClass: "tdClass" },
        { key: "hit", label: "조회수", tdClass: "tdClass" },
        { key: "registerTime", label: "작성일", tdClass: "tdClass" },
      ],
    };
  },
  created() {
    listArticle(
      this.param,
      ({ data }) => {
        this.notices = data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "noticeWrite" });
    },
    onSearch(event) {
      event.preventDefault();
      this.param.key = this.selected;
      this.param.word = this.searchWord;

      listArticle(
        this.param,
        ({ data }) => {
          this.notices = data;
        },
        (error) => {
          console.log(error);
        }
      );
    },
    viewNotice(notice) {
      this.$router.push({
        name: "noticeView",
        params: { noticeNo: notice.noticeNo },
      });
    },
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("yyyy.MM.DD hh:mm:ss");
    },
  },
};
</script>

<style scoped>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdTitle {
  width: 300px;
  text-align: left;
}
</style>
