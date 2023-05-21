<template>
  <div>
    {{hotplace.hotplaceNo}}번 핫플레이스 상세보기 화면입니다
  </div>
</template>

<script>
import { getHotplace } from '@/api/hotplace';

export default {
  name: 'HotplaceView',
  components: {},
  data() {
    return {
      hotplace: {},
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
  methods: {},
};
</script>

<style scoped></style>