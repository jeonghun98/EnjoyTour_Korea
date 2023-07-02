<template>
  <div class="col">
    <b-card
      :title="titleText"
      :img-src="hotplaceImg"
      img-alt="Image"
      img-top
      style="max-width: 20rem; margin: auto;"
      class="mb-2"
    >
      <b-card-text>
        {{ listContent }}
      </b-card-text>
      <template #footer>
        <div class="d-flex justify-content-between align-items-center">
          <div class="btn-group">
            <button
              type="button"
              class="btn btn-sm btn-outline-secondary"
              @click="moveView"
            >
              자세히 보기
            </button>
          </div>
          <small class="text-muted">좋아요 : {{ like }}</small>
        </div>
      </template>
    </b-card>
  </div>
</template>

<script>
import { getImageHotplace } from "@/api/hotplace";

export default {
  name: "HotplaceListItem",
  components: {},
  props: {
    hotplaceNo: Number,
    userId: String,
    title: String,
    content: String,
    like: Number,
    date: String,
    img: String,
    fileInfos: Array,
  },
  data() {
    return {
      hotplaceImg: "",
      // hotplace: {},
      listContent: "",
    };
  },
  created() {
    // console.log("HotplaceListItem : ", this.hotplaceNo);
    if (this.fileInfos != null) {
      // console.log("HotplaceListItem - 이미지 파일 있음");
      let sfolder = this.fileInfos[0].saveFolder;
      let ofile = this.fileInfos[0].originalFile;
      let sfile = this.fileInfos[0].saveFile;

      getImageHotplace(
        sfolder,
        ofile,
        sfile,
        ({ data }) => {
          // console.log(data);
          // this.hotplaceImg = require(data);
          // console.log(this.hotplaceImg);
          const url = window.URL.createObjectURL(data);
          this.hotplaceImg = url;
        },
        (error) => {
          console.log(error);
        }
      );
    } else {
      // console.log("HotplaceListItem - 이미지 파일 없음");
      this.hotplaceImg = require("../../../assets/img/ssafy_logo.png");
      // console.log(this.hotplaceImg);
    }

    if(this.content.length >= 45) {
      this.listContent = this.content.substring(0, 45) + "...";
    }
    else this.listContent = this.content;
  },
  computed: {
    titleText() {
      return this.title;
    },
  },
  methods: {
    moveView() {
      // alert(`${this.hotplaceNo} 번 핫플 view 이동`);
      this.$router.push({
        name: "hotplaceView",
        params: { hotplaceNo: this.hotplaceNo },
      });
    },
  },
};
</script>

<style scoped>
.card-img,
.card-img-top {
  height: 190px;
  border-top-left-radius: calc(0.25rem - 1px);
  border-top-right-radius: calc(0.25rem - 1px);
}
</style>
