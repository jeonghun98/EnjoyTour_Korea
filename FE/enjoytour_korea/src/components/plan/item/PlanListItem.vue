<template>
    <b-tr>
      <b-td>{{ planNo }}</b-td>
      <b-th class="text-left">
        <router-link :to="{ name: 'planView', params: { articleno: planNo } }">{{ title }}</router-link>
      </b-th>
      <b-td>{{ grade }}</b-td>
      <b-td>{{ userId }}</b-td>
      <b-td>{{ regtime | dateFormat }}</b-td>
    </b-tr>
  </template>
  
  <script>
//   import moment from "moment";
  
  export default {
    name: "PlanListItem",
    props: {
        planNo : Number,
        title : String,
        content  : String,
        userId  : String,
        startDate  : String,
        endDate  : String,
        planPulic : Boolean,
        grade : Number,
        // contentIds,
    },
    // filters: {
    //   dateFormat(regtime) {
    //     return moment(new Date(regtime)).format("YY.MM.DD");
    //   },
    // },
  };
  </script>
  
  <style></style>
  