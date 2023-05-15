<template>
    <div id="app">
      <header></header>
      <router-view></router-view>
    </div>
  </template>
  
  <script>
  import TheHeader from "./component/include/TheHeader.vue";
  import TheFooter from "./component/include/TheFooter.vue"

  export default {
    components: {
            TheHeader,
            TheFooter
    }
  };
  </script>