<template>
  <div class="col">
            <b-card
              title="Title"
              :img-src="require('../../../assets/img/korea1.jpg')"
              img-alt="Image"
              img-top
            >
              <b-card-text>
                This is a wider card with supporting text below as a natural
                lead-in to additional content. This content is a little bit
                longer.
              </b-card-text>
              <template #footer>
                <div class="d-flex justify-content-between align-items-center">
                  <div class="btn-group">
                    <button
                      type="button"
                      class="btn btn-sm btn-outline-secondary"
                    >
                      자세히 보기
                    </button>
                  </div>
                  <small class="text-muted">9 mins</small>
                </div>
              </template>
            </b-card>
          </div>
</template>

<script>
export default {
  name: 'HotplaceListItem',
  components: {},
  data() {
    return {
      message: '',
      data() {
        
      },
    };
  },
  created() {},
  methods: {},
};
</script>

<style scoped></style>