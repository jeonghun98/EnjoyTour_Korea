<!-- <template>
    <div>  
      <b-row v-model="modalShow" title="여행코스 작성하기">
        <b-form>
          <b-form-group id="input-group-title" label="계획 이름:" label-for="title">
            <b-form-input
              id="title"
              ref="title"
              v-model="title"
              placeholder=""
              required
            ></b-form-input>
          </b-form-group>
  
          <b-form-group id="input-group-content" label="계획 상세:" label-for="content">
            <b-form-textarea
              id="content"
              ref="content"
              v-model="content"
              placeholder=""
              rows="6"
              max-rows="15"
            ></b-form-textarea>
          </b-form-group>
  
          <label for="start-datepicker">출발일: </label>
          <b-form-datepicker id="start_date" v-model="start_date" class="mb-2" ref="start_date" />
  
          <label for="end-datepicker">도착일: </label>
          <b-form-datepicker id="end_date" v-model="end_date" class="mb-2" ref="end_date" />
  
          <b-form-checkbox
            v-model="checked"
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
      </b-row>
      <b-row>
        <b-col class="col-3 p-1">
          <b-button type="button" variant="primary" @click="openModal">여행코스 작성</b-button>
        </b-col>
      </b-row>
    </div>
  </template>
  
  <script>
  import PlanAttraction from "./item/PlanAttraction.vue";
  import { mapState, mapActions } from "vuex";
  const attractionStore = "attractionStore";
  
  import { writePlan } from "@/api/plan";
  
  export default {
    name: "PlanView",
    components: {
      PlanAttraction,
    },
    data() {
      return {
        modalShow: false,
  
        title: "",
        content: "",
        start_date: "",
        end_date: "",
        checked: false,
      };
    },
    computed: {
      ...mapState(attractionStore, ["planMarkers"]),
    },
  
    watch: {},
    methods: {
      ...mapActions(attractionStore, ["postPlan"]),
  
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
        this.title = "";
        this.content = "";
        this.start_date = "";
        this.end_date = "";
        this.checked = false;
      },
      checkValue() {
        let err = true;
        let msg = "";
  
        !this.title && ((msg = "제목을 입력해주세요"), (err = false), this.$refs.title.focus());
        err &&
          !this.content &&
          ((msg = "내용을 입력해주세요"), (err = false), this.$refs.content.focus());
        err &&
          !this.start_date &&
          ((msg = "출발일을 입력해주세요"), (err = false), this.$refs.start_date.focus());
        err &&
          !this.end_date &&
          ((msg = "출발일을 입력해주세요"), (err = false), this.$refs.end_date.focus());
  
        if (!err) alert(msg);
        else this.doWritePlan();
      },
      doWritePlan() {
        let List = [];
        console.log(this.planMarkers);
        this.planMarkers.forEach((item) => {
          List.push(item[2]);
        });
  
        let planList = {
          title: this.title,
          content: this.content,
          startDate: this.start_date,
          endDate: this.end_date,
          planPulic: this.checked,
          // userId: this.userInfo.userId,
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
          });
      },
      moveList() {
        this.$router.push({ name: "planList" });
      },
    },
  };
  </script>
   -->
