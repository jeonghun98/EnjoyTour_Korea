import Vue from "vue";
import VueRouter from "vue-router";
// views
import AppHome from "@/views/AppHome";
import AppAttraction from "@/views/AppAttraction";
import AppHotplace from "@/views/AppHotplace";
import AppNotice from "@/views/AppNotice";
import AppPlan from "@/views/AppPlan";

// components
import NoticeList from "@/components/notice/NoticeList";
import NoticeWrite from "@/components/notice/NoticeWrite";
import NoticeView from "@/components/notice/NoticeView";
import NoticeModify from "@/components/notice/NoticeModify";
import HotplaceList from '@/components/hotplace/HotplaceList';

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
    redirect: "/hotplace/list",
    children: [
      {
        path: "list",
        name: "hotplaceList",
        component: HotplaceList,
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
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
