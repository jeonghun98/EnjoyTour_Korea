import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

// module import
import NoticeStore from "@/store/modules/NoticeStore";
import itemStore from "./modules/item/itemStore";
import attractionStore from "./modules/AttractionStore"

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    NoticeStore,
    itemStore,
    attractionStore,
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});
