<template>
  <b-row class = "mt-3">
    <b-col>
      <div id="map"></div>
    </b-col>
    <b-col>
      <form
        id="form-register"
        method="POST"
        action=""
        enctype="multipart/form-data"
        :file-name-formatter="formatNames"
        @submit="onSubmit"
        @reset="onReset"
      >
        <!-- <input type="hidden" name="action" value="write" /> -->
        <div class="mt-3 text-danger fw-bold">
          스마트폰으로 찍은 사진을 올려주세요
        </div>
        <div class="mb-3 mt-1">
          <b-form-file
            multiple
            type="file"
            class="form-control"
            id="file"
            name="fileInfos[]"
            @change="setDetailImage"
            accept=".gif, .jpg, .png"
            v-model="hotplace.fileInfos"
          />
          <div id="images_container"></div>
        </div>
        <div>
          <b-img
            id="previewImg"
            :src="img"
            v-for="(img, index) in previewImg"
            :key="index"
          ></b-img>
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
            ref="title"
          />
        </div>
        <div class="mb-3">
          <label for="date" class="form-label">다녀온 날짜</label>
          <input
            type="date"
            class="form-control"
            id="date"
            name="date"
            v-model="hotplace.date"
            ref="date"
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
            ref="content"
          ></textarea>
        </div>
        <div class="col-auto text-center">
          <b-button
            type="submit"
            id="btn-inform-reg"
            class="btn mb-3 mr-3 btn-outline-success"
            v-if="this.type === 'write'"
          >
            등록
          </b-button>
          <button
            type="submit"
            id="btn-inform-modify"
            class="btn mb-3 mr-3 btn-outline-success"
            v-if="this.type === 'modify'"
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
          <b-button type="reset" variant="outline-danger" class="btn mb-3"
            >초기화</b-button
          >
        </div>
      </form>
    </b-col>
  </b-row>
</template>

<script>
import { writeHotplace, getHotplace, getImageHotplace, modifyHotplace } from "@/api/hotplace";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "HotplaceInputItem",
  components: {},
  props: {
    type: { type: String },
  },
  data() {
    return {
      hotplace: {
        hotplaceNo: 0,
        userId: "",
        img: "",
        title: "",
        date: "",
        content: "",
        fileInfos: [],
      },
      previewImg: [],
      isUserid: false,
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    this.hotplace.userId = this.userInfo.userid;

    if (this.type === "modify") {
      let param = this.$route.params.hotplaceNo;

      getHotplace(
        param,
        ({ data }) => {
          this.hotplace = data;
          // this.previewImg = this.hotplace.fileInfos;
          console.log("HotplaceInputItemdata -", data);
          // console.log(this.hotplace.fileInfos);

          if (this.hotplace.fileInfos != null) {
            // console.log("HotplaceListItem - 이미지 파일 있음");

            for (var file of this.hotplace.fileInfos) {
              let sfolder = file.saveFolder;
              let ofile = file.originalFile;
              let sfile = file.saveFile;
              console.log(file);
              console.log(sfolder, ofile, sfile);

              getImageHotplace(
                sfolder,
                ofile,
                sfile,
                ({ data }) => {
                  // console.log(data);
                  // this.hotplaceImg = require(data);
                  // console.log(this.hotplaceImg);
                  const url = window.URL.createObjectURL(data);
                  // console.log(url);
                  this.previewImg.push(url);
                },
                (error) => {
                  console.log(error);
                }
              );
            }
          }
        },
        (error) => {
          console.log(error);
        }
      );
    }

    console.log("created:",this.hotplace);
  },
  methods: {
    setDetailImage(event) {
      for (var image of event.target.files) {
        let url = URL.createObjectURL(image);
        console.log(url);
        this.previewImg.push(url);
      }
    },
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.hotplace.title &&
        ((msg = "제목 입력해주세요"),
        (err = false),
        this.$refs["title"].focus());
      err &&
        !this.hotplace.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs["content"].focus());
      err &&
        !this.hotplace.date &&
        ((msg = "날짜 입력해주세요"),
        (err = false),
        this.$refs["date"].focus());

      console.log("onSubmit - type:", this.type);
      if (!err) alert(msg);
      else
        this.type === "write" ? this.registHotplace() : this.modifyHotplace();
    },
    onReset(event) {
      event.preventDefault();
      this.hotplace.hotplaceNo = 0;
      this.hotplace.title = "";
      this.hotplace.content = "";
      // this.hotplace.img = "";
      this.hotplace.date = "";
      this.hotplace.fileInfos = [];
      this.moveListHotplace();
    },
    registHotplace() {
      const formData = new FormData();
      formData.append("hotplaceNo", this.hotplace.hotplaceNo);
      formData.append("userId", this.hotplace.userId);
      formData.append("title", this.hotplace.title);
      formData.append("content", this.hotplace.content);
      formData.append("date", this.hotplace.date);

      for (let i = 0; i < this.hotplace.fileInfos.length; i += 1) {
        const file = this.hotplace.fileInfos[i];
        formData.append(`thumbNail`, file);
      }

      writeHotplace(
        formData,
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
            // console.log("registHotplace - writeHotplace: 성공");
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
      // alert("핫플레이스 수정");
      console.log(this.hotplace);

      const formData = new FormData();
      formData.append("hotplaceNo", this.hotplace.hotplaceNo);
      formData.append("userId", this.hotplace.userId);
      formData.append("title", this.hotplace.title);
      formData.append("content", this.hotplace.content);
      formData.append("date", this.hotplace.date);

      for (let i = 0; i < this.hotplace.fileInfos.length; i += 1) {
        const file = this.hotplace.fileInfos[i];
        formData.append(`thumbNail`, file);
      }

      modifyHotplace(
        formData,
        this.hotplace.hotplaceNo,
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
            // console.log("registHotplace - writeHotplace: 성공");
          }
          alert(msg);
          this.moveListHotplace();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveListHotplace() {
      // alert("핫플레이스 목록 이동");
      this.$router.push({ name: "hotplaceList" });
    },
    formatNames(files) {
        return files.length === 1 ? files[0].name : `${files.length} files selected`
      }
  },
};
</script>

<style scoped>
#map {
  width: 100%;
  height: 30rem;
  background-color: lightgray;
}
#previewImg {
  width: 100%;
  height: 15rem;
}
</style>
