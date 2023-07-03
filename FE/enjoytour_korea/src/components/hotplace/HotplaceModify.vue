<template>
  <div>
    <!-- 핫플레이스 수정화면입니다 -->
    <hotplace-input-item type="modify" />
  </div>
</template>

<script>
import HotplaceInputItem from './item/HotplaceInputItem.vue';

export default {
  name: 'HotplaceModify',
  components: {
    HotplaceInputItem,
  },
  data() {
    return {
      message: '',
    };
  },
  created() {},
  methods: {},
};
</script>

<style scoped></style>