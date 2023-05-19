<template>
  <div class="col-lg-12 col-md-10 col-sm-12">
      <form id="form-register" method="POST" action="" @submit="onSubmit" @reset="onReset">
        <!-- <input type="hidden" name="action" value="write" /> -->
        <div class="mt-3 text-danger fw-bold">
          스마트폰으로 찍은 사진을 올려주세요
        </div>
        <div class="mb-3 mt-1">
          <input
            type="file"
            class="form-control"
            id="img"
            name="file"
            accept=".gif, .jpg, .png"
          />
        </div>
        <div class="mb-3 mt-3">
          <label for="title" class="form-label">핫플이름</label>
          <input
            type="text"
            class="form-control"
            id="title"
            name="title"
            placeholder="핫플이름.."
            v-model="hotplace.title"
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
          />
        </div>
        <!-- <div class="mb-3">
          <label for="date" class="form-label">장소유형</label>
          <select
            id="search-content-id"
            class="form-select"
            onchange="contentidOnclickHandler(this)"
            aria-label="Default select example"
          >
            <option value="0" selected>관광지 유형</option>
            <option value="12">관광지</option>
            <option value="14">문화시설</option>
            <option value="15">축제공연행사</option>
            <option value="25">여행코스</option>
            <option value="28">레포츠</option>
            <option value="32">숙박</option>
            <option value="38">쇼핑</option>
            <option value="39">음식점</option>
          </select>
        </div> -->
        <div class="mb-3">
          <label for="content" class="form-label">핫플 상세설명</label>
          <textarea
            class="form-control"
            id="content"
            name="content"
            rows="7"
            v-model="hotplace.content"
          ></textarea>
        </div>
        <div class="col-auto text-center">
          <button
            type="submit"
            id="btn-inform-register"
            class="btn btn-outline-primary mb-3 mr-3"
            
            v-if="this.type === 'write'"
          >
            등록
          </button>
          <button
            type="submit"
            id="btn-inform-register"
            class="btn btn-outline-primary mb-3 mr-3"
            @click="modifyHotplace"
            v-else
          >
            수정
          </button>
          <button
            type="button"
            id="btn-move-list"
            class="btn btn-outline-primary mb-3 mr-3"
            @click="moveListHotplace"
          >
            목록
          </button>
          <b-button type="reset" variant="danger" class="btn mb-3">초기화</b-button>
        </div>
      </form>
    </div>
</template>

<script>
import { writeHotplace } from "@/api/hotplace";

export default {
  name: 'HotplaceInputItem',
  components: {},
  props: {
    type: { type: String },
  },
  data() {
    return {
      hotplace: {
        hotplaceNo: 0,
        userId: "ssafy",
        img: "hotplace.img",
        title: "",
        date: "",
        content: "",
      },
      isUserid: false,
    };
  },
  created() {},
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.hotplace.date && ((msg = "날짜 입력해주세요"), (err = false), this.$refs.date.focus());
      err && !this.hotplace.title && ((msg = "제목 입력해주세요"), (err = false), this.$refs.title.focus());
      err && !this.hotplace.content && ((msg = "내용 입력해주세요"), (err = false), this.$refs.content.focus());

      if (!err) alert(msg);
      else this.type === "write" ? this.registHotplace() : this.modifyHotplace();
    },
    onReset(event) {
      event.preventDefault();
      this.hotplace.hotplaceNo = 0;
      this.hotplace.title = "";
      this.hotplace.content = "";
      this.hotplace.img = "";
      this.hotplace.date = "";
      this.moveListHotplace();
    },
    registHotplace() {
      alert("핫플레이스 등록");
      let param = {
        hotplaceNo: this.hotplace.hotplaceNo,
        userId: this.hotplace.userId,
        title: this.hotplace.title,
        content: this.hotplace.content,
        img: this.hotplace.img,
        date: this.hotplace.date,
      };
      console.log("registHotplace:",param);

      writeHotplace(
        param,
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
            console.log("registHotplace - writeHotplace: 성공");
          }
          alert(msg);
          this.moveListHotplace();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    modifyHotplace() {
      alert("핫플레이스 수정")
    },
    moveListHotplace() {
      alert("핫플레이스 목록 이동")
      this.$router.push({ name: "hotplaceList" })
    },

  },
};
</script>

<style scoped></style>