<template>
  <div>
    {{hotplace.hotplaceNo}}번 핫플레이스 상세보기 화면입니다
    <!-- 핫플레이스 이미지 -->
    <span>
      <div>
        <hotplace-img-item-vue v-for="fileInfo in hotplace.fileInfos" :key="fileInfo.originalFile"></hotplace-img-item-vue>
      </div>
      <div>
        <button
            type="button"
            id="btn-move-list"
            class="btn btn-outline-primary mb-3 mr-3"
            @click="moveListHotplace"
          >
            목록
        </button>
        <button
            type="button"
            id="btn-move-list"
            class="btn btn-outline-primary mb-3 mr-3"
            
          >
            글수정
        </button>
        <button
            type="button"
            id="btn-move-list"
            class="btn btn-outline-primary mb-3 mr-3"
            
          >
            글삭제
        </button>
      </div>
    </span>
    <!-- 핫플 정보, 위치 -->
    <span>
      <div>
      </div>
      <div>
      </div>
    </span>
  </div>
</template>

<script>
import { getHotplace } from '@/api/hotplace';
import HotplaceImgItemVue from './item/HotplaceImgItem.vue';

export default {
  name: 'HotplaceView',
  components: {
    HotplaceImgItemVue,
  },
  data() {
    return {
      hotplace: {},
      hotplaceImg: "",
    };
  },
  created() {
    let param = this.$route.params.hotplaceNo;
    getHotplace(
      param,
      ({ data }) => {
        this.hotplace = data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveListHotplace() {
      alert("핫플레이스 목록 이동")
      this.$router.push({ name: "hotplaceList" })
    },
  },
};
</script>

<style scoped></style>