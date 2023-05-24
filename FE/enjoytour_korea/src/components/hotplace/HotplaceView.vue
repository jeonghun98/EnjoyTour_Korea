<template>
  <b-row>
    <!-- {{hotplace.hotplaceNo}}번 핫플레이스 상세보기 화면입니다 -->
    <!-- 핫플레이스 이미지 -->
    <b-col>
      <b-row>
        <!-- <hotplace-img-item-vue v-for="fileInfo in hotplace.fileInfos" :key="fileInfo.originalFile"></hotplace-img-item-vue> -->
        <hotplace-img-item-vue
          v-for="(fileInfo, index) in hotplace.fileInfos"
          :key="index"
          v-bind="fileInfo"
        ></hotplace-img-item-vue>
      </b-row>
      <b-row>
        <b-col>
          <b-button
            type="button"
            variant="outline-primary"
            id="btn-move-list"
            class="btn mt-3 mb-3 mr-3"
            @click="moveListHotplace"
          >
            목록
          </b-button>
        </b-col>
        <b-col v-if="userInfo.userid === hotplace.userId">
          <b-button
            type="button"
            id="btn-move-list"
            variant="outline-success"
            class="btn mt-3 mb-3 mr-3"
            @click="modifyHotplace"
          >
            글수정
          </b-button>
          <b-button
            type="button"
            id="btn-move-list"
            variant="outline-danger"
            class="btn mt-3 mb-3 mr-auto"
            @click="deleteHotplace"
          >
            글삭제
          </b-button>
        </b-col>
      </b-row>
    </b-col>
    <!-- 핫플 정보, 위치 -->
    <b-col>
      <b-row>
        <form id="form-view" method="" action="" style="width: 100%">
          <div class="mb-3 mt-3">
            <label for="title" class="form-label">핫플이름</label>
            <input
              type="text"
              class="form-control"
              id="title"
              name="title"
              placeholder="핫플이름.."
              v-model="hotplace.title"
              readonly
            />
          </div>
          <div class="mb-3">
            <label for="date" class="form-label">다녀온 날짜</label>
            <input
              type="date"
              class="form-control"
              id="date"
              name="date"
              value="2023-03-27"
              v-model="hotplace.date"
              readonly
            />
          </div>
          <div class="mb-3">
            <label for="content" class="form-label">핫플 상세설명</label>
            <textarea
              class="form-control"
              id="content"
              name="content"
              rows="7"
              v-model="hotplace.content"
              readonly
            ></textarea>
          </div>
        </form>
      </b-row>
      <b-row>
        <!-- map start -->
        <div class="col-lg-12 col-md-10 col-sm-12 mt-3 rounded">
          <div id="map"></div>
        </div>
        <!-- map end -->
      </b-row>
    </b-col>
  </b-row>
</template>

<script>
import { getHotplace, deleteHotplace } from "@/api/hotplace";
import HotplaceImgItemVue from "./item/HotplaceImgItem.vue";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "HotplaceView",
  components: {
    HotplaceImgItemVue,
  },
  data() {
    return {
      hotplace: {},
      hotplaceImg: "",
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    let param = this.$route.params.hotplaceNo;
    getHotplace(
      param,
      ({ data }) => {
        this.hotplace = data;
        console.log("HotplaceView:data -", data);
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveListHotplace() {
      alert("핫플레이스 목록 이동");
      this.$router.push({ name: "hotplaceList" });
    },
    modifyHotplace() {
      alert("핫플레이스 수정 이동");
      this.$router.replace({
        name: "hotplaceModify",
        params: { hotplaceNo: this.hotplace.hotplaceNo },
      });
    },
    deleteHotplace() {
      // alert("핫플레이스 삭제 이동")
      if (confirm("정말로 삭제하시겠습니까?")) {
        let param = this.$route.params.hotplaceNo;

        deleteHotplace(
          param,
          ({ data }) => {
            let msg = "삭제 처리시 문제가 발생했습니다.";
            if (data === "success") {
              msg = "삭제가 완료되었습니다.";
            }
            alert(msg);
            this.$router.push({ name: "hotplaceList" });
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },
  },
};
</script>

<style scoped>
#map {
  width: 100%;
  height: 30rem;
  background-color: lightgray;
}
</style>
