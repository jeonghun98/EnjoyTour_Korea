import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";

import "@/api/lib/vueBootstrap.js";
// import "@/assets/js/map.js";
// import { getExif } from "@/assets/js/metadata.js"

Vue.config.productionTip = false;
// Vue.use(getExif);

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
