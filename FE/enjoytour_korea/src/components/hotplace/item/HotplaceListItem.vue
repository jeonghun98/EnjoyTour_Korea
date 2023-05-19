<template>
  <div class="col">
            <b-card
              :title="titleText"
              :img-src="require('../../../assets/img/ssafy_logo.png')"
              img-alt="글쓰기"
              img-top
            >
             <!-- :img-src="require('../../../assets/img/ssafy_logo.png')" -->
              <b-card-text>
                {{content}}
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
                  <small class="text-muted">좋아요 : {{like}}</small>
                </div>
              </template>
            </b-card>
          </div>
</template>

<script> 
export default {
  name: 'HotplaceListItem',
  components: {},
  props: {
    hotplaceNo: Number,
    userId: String,
    title: String,
    content: String,
    like: Number,
    date: String,
    img: String,
  }, 
  data() {
    return {
      message: '',
    };
  },
  created() {},
  computed: {
    titleText() {
      return this.title;
    }
  },
  methods: {
    moveView() {
      alert("핫플 view 이동");
    },
  },
};
</script>

<style scoped></style>