import Vue from "vue";
import VueRouter from "vue-router";
// views
import AppHome from "@/views/AppHome";
import AppAttraction from "@/views/AppAttraction";
import AppHotplace from "@/views/AppHotplace";
import AppNotice from "@/views/AppNotice";
import AppPlan from "@/views/AppPlan";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: AppHome,
  },
  {
    path: "/attraction",
    name: "Attraction",
    component: AppAttraction,
  },
  {
    path: "/hotplace",
    name: "hotplace",
    component: AppHotplace,
  },
  {
    path: "/notice",
    name: "notice",
    component: AppNotice,
  },
  {
    path: "/plan",
    name: "plan",
    component: AppPlan,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
