<template>
  <tr class="text-center">
    <th scope="row">{{ notice_no }}</th>
    <td class="text-start">
        <router-link
        :to="{ name: 'noticeView', params: { notice_no: notice_no } }"
        >{{ notice_title }}</router-link
      >
    </td>
    <td>{{ user_id }}</td>
    <td>{{ notice_hit }}</td>
    <td>{{ notice_register_time }}</td>
  </tr>
</template>

<script>
import moment from "moment";

export default {
  name: "NoticeListItem",
  props: {
    notice_no: Number,
    user_id: String,
    notice_title: String,
    notice_content: String,
    notice_hit: Number,
    notice_register_time: String,
  },
  filters: {
    dateFormat(notice_register_time) {
      return moment(new Date(notice_register_time)).format("YY.MM.DD");
    },
  },
};
</script>

<style scoped></style>
