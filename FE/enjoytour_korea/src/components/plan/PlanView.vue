<template>
  <div id="travel-info" v-if="travelPlanContent">
    <b-row class="mt-3">
      <b-col>
        <b-row>
          <b-navbar toggleable="lg">
            <b-navbar-brand class="ms-3">
              <h3>여행계획 상세보기</h3>
            </b-navbar-brand>
          </b-navbar>
        </b-row>

        <b-row class = "mr-2">
          <div id="travel-info-item">
            <table class="table table-hover">
              <tr>
                <td>제목</td>
                <td>{{ travelPlanContent.title }}</td>
              </tr>
              <tr>
                <td>작성자</td>
                <td>{{ travelPlanContent.userId }}</td>
              </tr>
              <tr>
                <td>출발일</td>
                <td>{{ travelPlanContent.startDate }}</td>
              </tr>
              <tr>
                <td>도착일</td>
                <td>{{ travelPlanContent.endDate }}</td>
              </tr>
              <tr>
                <td>내용</td>
                <td>{{ travelPlanContent.content }}</td>
              </tr>
            </table>
          </div>
        </b-row>
      </b-col>

      <b-col>
        <b-row>
          <b-navbar toggleable="lg">
            <b-navbar-brand class="ms-3">
              <h3>여행 경로</h3>
            </b-navbar-brand>
          </b-navbar>
        </b-row>
        <b-row>
          <table class="table table-hover">
            <div
              class="list-group-item"
              v-for="(trm, index) in travelMarkers"
              :key="index"
              @click="movePan(trm[0], trm[1])"
            >
              <b-card no-body class="attraction-item overflow-hidden">
                <b-row no-gutters>
                  <b-col md="4" class="m-auto">
                    <b-card-img
                      class="img align-middle"
                      v-if="trm[6] != ''"
                      :src="trm[6]"
                      :alt="trm[4]"
                    />
                    <b-card-img
                      class="img align-middle"
                      v-if="trm[6] == ''"
                      :src="require('@/assets/img/ssafy_logo.png')"
                      alt="no image"
                    />
                  </b-col>
                  <b-col md="8" class="m-auto pl-3">
                    <table>
                      <tr>
                        <td v-if="trm[4].length > 15" class="item-title">
                          <strong>{{ index + 1 }}번</strong><br />{{ trm[4].substr(0, 15) }}...
                        </td>
                        <td v-if="trm[4].length <= 15" class="item-title">
                          <strong>{{ index + 1 }}번</strong><br />
                          {{ trm[4] }}
                        </td>
                        <!-- <td>
                    <b-button
                      class="delete-btn"
                      variant="danger"
                      @click="deletePlan"
                    >
                      삭제
                    </b-button>
                  </td> -->
                      </tr>
                    </table>
                  </b-col>
                </b-row>
              </b-card>
            </div>
          </table>
        </b-row>
      </b-col>
    </b-row>
  </div>
  <!-- <table class="table table-hover">
        <tr
          v-for="(trm, index) in travelMarkers"
          :key="index"
          @click="movePan(trm[0], trm[1])"
        >
          <td>
            <strong>{{ index + 1 }}번</strong>
          </td>
          <td v-if="trm[4].length > 25">
            <strong>{{ trm[4].substr(0, 25) }}</strong
            >...
          </td>
          <td v-if="trm[4].length <= 25">
            <strong>{{ trm[4] }}</strong>
          </td>
        </tr>
      </table> -->
</template>

<script>
import { mapState, mapActions } from "vuex";
const attractionStore = "attractionStore";

export default {
  name: "PlanView",
  components: {},
  data() {
    return {};
  },
  computed: {
    ...mapState(attractionStore, ["travelPlanContent", "travelPlan", "travelMarkers"]),
  },
  watch: {},
  mounted() {},
  methods: {
    ...mapActions(attractionStore, ["getPosition"]),

    movePan(lat, lon) {
      this.getPosition({
        latitude: lat,
        longitude: lon,
      });
    },
    moveList() {
      this.$router.push({ name: "planList" });
    },
    moveModifyPlan() {
      this.$router.replace({
        name: "planModify",
        params: { planNo: this.travelPlanNo },
      });
    },

    deletePlan() {
      let param = this.$route.params.planNo;
      console.log(param);
      // if (confirm("정말로 삭제하시겠습니까?")) {
      //   let param = this.$route.params.noticeNo;
      //   deleteArticle(
      //     param,
      //     ({ data }) => {
      //       let msg = "삭제 처리시 문제가 발생했습니다.";
      //       if (data === "success") {
      //         msg = "삭제가 완료되었습니다.";
      //       }
      //       alert(msg);
      //       this.$router.push({ name: "noticeList" });
      //     },
      //     (error) => {
      //       console.log(error);
      //     }
      //   );
      // }
    },
  },
};
</script>

<style>
h3 {
  margin: 0;
}

/* #travel-info {
  width: 100%;
  height: 95vh;
  margin: 0;
  text-align: center;
} */

#travel-info-item {
  width: 100%;
  height: 90%;
  overflow: auto;
}

tr {
  background-color: rgba(255, 255, 255, 0.7);
  cursor: pointer;
}
</style>