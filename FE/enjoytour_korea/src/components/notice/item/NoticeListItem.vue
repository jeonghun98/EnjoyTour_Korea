<!-- <template>
  <tr class="text-center">
    <b-th scope="row">{{ noticeNo }}</b-th>
    <b-td class="text-start" >
        <router-link
        :to="{ name: 'noticeView', params: { noticeNo: noticeNo } }"
        >{{ title }}</router-link
      >
      <!-- {{ title }} -->
    </b-td>
    <b-td>{{ userId }}</b-td>
    <b-td>{{ hit }}</b-td>
    <b-td>{{ registerTime | dateFormat }}</b-td>
  </tr>
</template>
<script>
import moment from "moment";

export default {
  name: "NoticeListItem",
  props: {
    noticeNo: Number,
    userId: String,
    title: String,
    content: String,
    hit: Number,
    registerTime: String,
  },
  methods: {
    moveView() {
      this.$router.push({ name: 'noticeView', params: { noticeNo: this.noticeNo } });
    },
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style scoped></style> -->
