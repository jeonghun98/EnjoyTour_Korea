import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";

/* npm install bootstrap@4.6.1 */
import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
