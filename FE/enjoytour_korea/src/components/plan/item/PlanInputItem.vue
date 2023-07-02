<template>
  <div>
    <div v-if="type === 'write'">
      <plan-attraction type="write"></plan-attraction>
    </div>
    <div v-else>
      <plan-attraction type="modify"></plan-attraction>
    </div>

    <b-row v-if="type === 'write'">
      <b-col class="col-3 p-1">
        <b-button type="button" variant="primary" @click="openModal">여행계획 작성</b-button>
      </b-col>
    </b-row>

    <b-row v-else>
      <b-col class="col-3 p-1">
        <b-button type="button" variant="primary" @click="openModal">여행계획 수정</b-button>
      </b-col>
    </b-row>

    <b-modal v-model="modalShow" title="여행계획 작성하기" hide-footer>
      <b-form>
        <b-form-group id="input-group-title" label="계획 이름:" label-for="title">
          <b-form-input
            id="title"
            ref="title"
            v-model="plan.title"
            placeholder=""
            required
          ></b-form-input>
        </b-form-group>

        <b-form-group id="input-group-content" label="계획 상세:" label-for="content">
          <b-form-textarea
            id="content"
            ref="content"
            v-model="plan.content"
            placeholder=""
            rows="6"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>

        <label for="start-datepicker">출발일: </label>
        <b-form-datepicker id="start_date" v-model="plan.startDate" class="mb-2" ref="start_date" />

        <label for="end-datepicker">도착일: </label>
        <b-form-datepicker id="end_date" v-model="plan.endDate" class="mb-2" ref="end_date" />

        <b-form-checkbox
          v-model="plan.planPublic"
          name="check-button"
          switch
          class="float-right mr-3 ml-3 mt-1"
          size="lg"
          style="font-weight: bold"
        >
          모두 공개
        </b-form-checkbox>

        <b-button type="button" @click="onSubmit" variant="primary">작성하기</b-button>
        <b-button type="reset" class="ml-2" @click="onReset" variant="danger">초기화</b-button>
      </b-form>
    </b-modal>
  </div>
</template>

<script>
import PlanAttraction from "./PlanAttraction.vue";
import { mapState, mapMutations, mapActions } from "vuex";
const attractionStore = "attractionStore";
const memberStore = "memberStore";

import { writePlan, modifyPlan } from "@/api/plan";

export default {
  name: "PlanInputItem",
  components: {
    PlanAttraction,
  },
  props: {
    type: { type: String },
  },
  data() {
    return {
      modalShow: false,
      plan: {
        planNo: 0,
        title: "",
        content: "",
        startDate: "",
        endDate: "",
        planPublic: false,
      },
    };
  },
  computed: {
    ...mapState(attractionStore, [
      "planMarkers",
      "travelPlanContent",
      "travelPlan",
      "travelMarkers",
    ]),
    ...mapState(memberStore, ["userInfo"]),
  },
  watch: {},
  async created() {
    if (this.type === "modify") {
      this.planNo = this.$route.params.planNo;
      this.plan = this.travelPlanContent;
      this.CLEAR_PLAN_MARKERS();
      await this.SET_PLAN_MARKERS_MODIFY(this.travelMarkers);
      console.log("modify plan", this.plan);
      console.log("modify travelMarkers", this.travelMarkers);
      console.log("modify planMarkers", this.planMarkers);
    } else {
      this.CLEAR_PLAN_MARKERS();
    }
    console.log("inputItem", this.type);
  },
  methods: {
    ...mapActions(attractionStore, ["postPlan"]),
    ...mapMutations(attractionStore, [
      "SET_PLAN_MARKERS",
      "CLEAR_PLAN_MARKERS",
      "SET_PLAN_MARKERS_MODIFY",
    ]),

    openModal() {
      console.log(this.planMarkers);
      console.log(this.planMarkers.length);
      if (this.planMarkers.length == 0) {
        alert("여행 경로를 선택해주세요!");
      } else {
        this.modalShow = !this.modalShow;
      }
    },
    onSubmit() {
      this.checkValue();
    },

    onReset(event) {
      event.preventDefault();
      this.plan.title = "";
      this.plan.content = "";
      this.plan.startDate = "";
      this.plan.endDate = "";
      this.plan.planPublic = false;
    },
    checkValue() {
      let err = true;
      let msg = "";

      // console.log(this.userInfo);

      !this.plan.title && ((msg = "제목을 입력해주세요"), (err = false), this.$refs.title.focus());
      err &&
        !this.plan.content &&
        ((msg = "내용을 입력해주세요"), (err = false), this.$refs.content.focus());
      err &&
        !this.plan.startDate &&
        ((msg = "출발일을 입력해주세요"), (err = false), this.$refs.start_date.focus());
      err &&
        !this.plan.endDate &&
        ((msg = "출발일을 입력해주세요"), (err = false), this.$refs.end_date.focus());

      if (!err) alert(msg);
      else this.type === "write" ? this.WritePlan() : this.modifyPlan();
    },
    WritePlan() {
      let List = [];
      console.log(this.planMarkers);
      this.planMarkers.forEach((item) => {
        List.push(item.contentId);
      });

      let planList = {
        title: this.plan.title,
        content: this.plan.content,
        startDate: this.plan.startDate,
        endDate: this.plan.endDate,
        planPublic: this.plan.planPublic,
        userId: this.userInfo.userid,
        contentIds: List,
      };
      console.log(planList);
      writePlan(
        planList,
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
    modifyPlan() {
      let List = [];
      console.log(this.planMarkers);
      this.planMarkers.forEach((item) => {
        List.push(item.contentId);
      });

      let planList = {
        planNo: this.plan.planNo,
        title: this.plan.title,
        content: this.plan.content,
        startDate: this.plan.startDate,
        endDate: this.plan.endDate,
        planPublic: this.plan.planPublic,
        userId: this.userInfo.userid,
        contentIds: List,
      };
      console.log(planList);
      modifyPlan(
        planList,
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
      this.$router.push({ name: "planList" });
    },
  },
};
</script>
