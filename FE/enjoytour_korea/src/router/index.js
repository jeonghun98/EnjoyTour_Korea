import Vue from "vue";
import VueRouter from "vue-router";
// views
import AppHome from "@/views/AppHome";
import AppAttraction from "@/views/AppAttraction";
import AppHotplace from "@/views/AppHotplace";
import AppNotice from "@/views/AppNotice";
import AppPlan from "@/views/AppPlan";

// components
// notice
import NoticeList from "@/components/notice/NoticeList";
import NoticeWrite from "@/components/notice/NoticeWrite";
import NoticeView from "@/components/notice/NoticeView";
import NoticeModify from "@/components/notice/NoticeModify";

// hotplace
import HotplaceList from "@/components/hotplace/HotplaceList";
import HotplaceWrite from "@/components/hotplace/HotplaceWrite";
import HotplaceModify from "@/components/hotplace/HotplaceModify";
import HotplaceView from "@/components/hotplace/HotplaceView";
import HotplaceDelete from "@/components/hotplace/HotplaceDelete";

// plan
import PlanList from '@/components/plan/PlanList';
import PlanModify from '@/components/plan/PlanModify';
import PlanView from '@/components/plan/PlanView';
import PlanWrite from '@/components/plan/PlanWrite'

import store from "@/store";

Vue.use(VueRouter);

// https://v3.router.vuejs.org/kr/guide/advanced/navigation-guards.html
const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const checkToken = store.getters["memberStore/checkToken"];
  let token = sessionStorage.getItem("access-token");
  console.log("로그인 처리 전", checkUserInfo, token);

  if (checkUserInfo != null && token) {
    console.log("토큰 유효성 체크하러 가자!!!!");
    await store.dispatch("memberStore/getUserInfo", token);
  }
  if (!checkToken || checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    // next({ name: "login" });
    router.push({ name: "login" });
  } else {
    console.log("로그인 했다!!!!!!!!!!!!!.");
    next();
  }
};

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
    redirect: "/hotplace/list",
    children: [
      {
        path: "list",
        name: "hotplaceList",
        component: HotplaceList,
      },
      {
        path: "write",
        name: "hotplaceWrite",
        beforeEnter: onlyAuthUser,
        component: HotplaceWrite,
      },
      {
        path: "view/:hotplaceNo",
        name: "hotplaceView",
        beforeEnter: onlyAuthUser,
        component: HotplaceView,
      },
      {
        path: "modify/:hotplaceNo",
        name: "hotplaceModify",
        beforeEnter: onlyAuthUser,
        component: HotplaceModify,
      },
      {
        path: "delete/:hotplaceNo",
        name: "hotplaceDelete",
        beforeEnter: onlyAuthUser,
        component: HotplaceDelete,
      },
    ],
  },
  {
    path: "/notice",
    name: "notice",
    component: AppNotice,
    redirect: "/notice/list",
    children: [
      {
        path: "list",
        name: "noticeList",
        component: NoticeList,
      },
      {
        path: "write",
        name: "noticeWrite",
        component: NoticeWrite,
      },
      {
        path: "view/:noticeNo",
        name: "noticeView",
        component: NoticeView,
      },
      {
        path: "modify/:noticeNo",
        name: "noticeModify",
        component: NoticeModify,
      },
    ],
  },
  {
    path: "/plan",
    name: "plan",
    component: AppPlan,
    redirect: "/plan/list",
    children: [
      {
        path: "list",
        name: "planList",
        component: PlanList,
      },
      {
        path: "write",
        name: "planWrite",
        component: PlanWrite,
      },
      {
        path: "view/:planNo",
        name: "planView",
        component: PlanView,
      },
      {
        path: "modify/:planNo",
        name: "planModify",
        component: PlanModify,
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
