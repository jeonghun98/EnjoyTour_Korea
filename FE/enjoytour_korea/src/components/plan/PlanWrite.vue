<template>
  <div>
    <plan-attraction></plan-attraction>
    <b-row>
      <b-col class="col-3 p-1">
        <b-button type="button" variant="primary" @click="openModal">여행코스 작성</b-button>
      </b-col>
      <h5>public</h5>
      <b-form-checkbox
        v-model="checked"
        name="check-button"
        switch
        class="float-right mr-3 ml-3"
        size="lg"
      >
      </b-form-checkbox>
    </b-row>

    <b-modal v-model="modalShow" title="여행코스 작성하기" hide-footer>
      <b-form>
        <b-form-group id="input-group-title" label="제목 :" label-for="title">
          <b-form-input
            id="title"
            ref="title"
            v-model="title"
            placeholder=""
            required
          ></b-form-input>
        </b-form-group>

        <b-form-group id="input-group-content" label="내용 :" label-for="content">
          <b-form-textarea
            id="content"
            v-model="content"
            placeholder=""
            rows="6"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>

        <div class="col-auto text-center">
          <b-button type="button" @click="onSubmit" variant="primary">작성하기</b-button>
        </div>
      </b-form>
    </b-modal>
  </div>
</template>

<script>
import PlanAttraction from "./item/PlanAttraction.vue";

export default {
  name: "PlanWrite",
  components: {
    PlanAttraction,
  },
  data() {
    return {
      checked: false,
    };
  },
  watch: {
    checked: {
      handler() {
        this.checked = !this.checked;
      },
    },
  },
};
</script>
