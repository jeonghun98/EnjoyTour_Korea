<template>
  <!-- <div>
    <router-link :to="{ name: 'planWrite'}">
        planWrite 이동 (후에 view로 변경예정)
    </router-link>
    </div> -->
  <div class="my-3 bg-body rounded">
    <div class="row align-self-center mb-2" style="justify-content: space-between">
      <div class="text-start">
        <button type="button" id="btn-mv-register" class="btn btn-primary" @click="moveWrite">
          글쓰기
        </button>
      </div>
      <div class="offset-5 d-flex justify-content-end">
        <form class="d-flex mr-2" id="form-search" @submit="onSearch">
          <input type="hidden" name="action" value="list" />
          <input type="hidden" name="pgno" value="1" />
          <b-select v-model="serchSelected" :options="searchOptions" class="mr-2"></b-select>
          <div class="input-group input-group-sm">
            <input
              type="text"
              name="word"
              id="word"
              class="form-control mr-2"
              placeholder="검색어..."
              v-model="searchWord"
              style="height: auto"
            />
            <button id="btn-search" class="btn btn-dark" type="submit" @submit="onSearch">
              검색
            </button>
          </div>
        </form>
        <div>
          <input type="hidden" name="action" value="sort" />
          <input type="hidden" name="pgno" value="1" />
          <b-select v-model="sortSelected" :options="sortOptions" class="mr-2"></b-select>
        </div>
      </div>
    </div>
    <b-row>
      <!-- <b-col>
        <plan-list-item v-for="travel in travelList" :key="travel.planNo" v-bind="travel"></plan-list-item>
      </b-col> -->
      <b-col>
        <b-table hover :items="travelList" :fields="fields" @row-clicked="viewPlan">
          <template #cell(subject)="data">
            <router-link :to="{ name: 'planView', params: { noticeNo: data.item.planNo } }">
              {{ data.item.title }}
            </router-link>
          </template>
        </b-table>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
// import PlanListItem from "./item/PlanListItem.vue";

export default {
  name: "PlanList",
  components: {
    // PlanListItem
  },
  data() {
    return {
      message: "",
      serchSelected: null,
      searchWord: null,
      sortSelected: null,
      param: {
        pg: 1,
        spp: 20,
        key: null,
        word: null,
      },
      searchOptions: [
        { value: null, text: "검색조건" },
        { value: "notice_no", text: "글번호" },
        { value: "title", text: "제목" },
      ],
      fields: [
        { key: "planNo", label: "글번호", tdClass: "tdClass" },
        { key: "title", label: "제목", tdClass: "tdTitle" },
        { key: "userId", label: "작성자", tdClass: "tdClass" },
        { key: "startDate", label: "출발일", tdClass: "tdClass" },
          { key: "endDate", label: "도착일", tdClass: "tdClass" },
          { key: "grade", label: "평점", tdClass: "tdClass" },
      ],
      sortOptions: [
        { value: null, text: "정렬" },
        { value: "notice_no", text: "글번호" },
        { value: "title", text: "제목" },
      ],
    };
  },
  computed: {
    ...mapState("attractionStore", ["travelList"]),
  },
  created() {
    this.loadTravelList();
  },
  mounted() {
    this.loadTravelList();
  },
  methods: {
    ...mapActions("attractionStore", ["getTravelList"]),
    ...mapMutations("attractionStore", [
      "SET_TRAVEL_PLAN",
      "SET_TRAVEL_MARKERS",
      "CLEAR_TRAVEL_MARKERS",
      "CLEAR_TRAVEL_LIST",
      "CLEAR_TRAVEL_PLAN",
    ]),
    loadTravelList() {
      this.CLEAR_TRAVEL_MARKERS();
      this.CLEAR_TRAVEL_LIST();
      this.CLEAR_TRAVEL_PLAN();

      this.param.key = this.serchSelected;
      this.param.word = this.searchWord;
      this.getTravelList(this.param);
    },

    onSearch(event) {
      event.preventDefault();
      this.loadTravelList();
    },
    moveWrite() {
      this.$router.push({ name: "planWrite" });
    },

    viewPlan(plan) {
      console.log("마커 세팅");
      this.CLEAR_TRAVEL_MARKERS();

      //todo
      //   this.SET_TRAVEL_PLAN(this.travelList[plan.planNo]);
      //   this.SET_TRAVEL_MARKERS();

      this.$router.push({
        name: "planView",
        params: { planNo: plan.planNo },
      });
    },
  },
};
</script>

<style scoped>
.row {
  display: flex;
  flex-wrap: wrap;
}
.col {
  padding: 0px;
}
</style>
