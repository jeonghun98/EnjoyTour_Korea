<template>
  <div id="app">
    <the-header></the-header>
    <the-nav></the-nav>
    <router-view :key="$route.fullPath"></router-view>
    <the-footer></the-footer>
  </div>
</template>

<script>

import TheHeader from "./components/include/TheHeader.vue";
import TheFooter from "./components/include/TheFooter.vue";
import TheNav from "./components/include/TheNav.vue";

export default {
  components: {
    TheHeader,
    TheNav,
    TheFooter,
  }
};
</script>
