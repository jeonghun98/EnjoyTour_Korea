import { AttractionList, AttractionSearchList, Attraction } from "@/api/attraction.js";
// import { writePlan, listPlan, getPlan, modifyPlan, deletePlan } from "@/api/plan.js";
import { listPlan, getPlan, mylistPlan } from "@/api/plan.js";

const attractionStore = {
  namespaced: true,
  state: {
    // map data
    latitude: 37.5013068,
    longitude: 127.0396597,
    markerPositions: [],

    planMarkers: [], // 선택 중인 여행 계획 마커

    // attraction data
    attractions: [],
    attraction: null,

    travelList: [], // 여행 계획 목록

    travelPlanNo: 0, // 선택한 기존 여행 Number
    travelPlanContent: [], // 선택한 기존 여행 계획
    travelPlan: [], // 선택한 기존 여행 모든 contentId
    travelMarkers: [], // 선택한 기존 여행 마커
  },
  getters: {},
  mutations: {
    // map data set
    SET_LATITUDE(state, latitude) {
      state.latitude = latitude;
    },
    SET_LONGITUDE(state, longitude) {
      state.longitude = longitude;
    },
    SET_MARKER_POSITIONS(state, attractionList) {
      if (attractionList.length != 0) {
        attractionList.forEach((attraction) => {
          state.markerPositions.push([
            attraction.latitude,
            attraction.longitude,
            attraction.contentId,
            attraction.contenttypeid,
            attraction.title,
            attraction.addr1,
            attraction.image1,
            attraction.tel,
          ]);
        });
      }
      // console.log(state.markerPositions);
    },

    SET_PLAN_MARKERS(state, planList) {
      if (planList.length != 0) {
        planList.forEach((planItem) => {
          state.planMarkers.push([
            planItem.latitude,
            planItem.longitude,
            planItem.contentId,
            planItem.contenttypeid,
            planItem.title,
            planItem.addr1,
            planItem.image1,
            planItem.tel,
          ]);
        });
      }
    },

    SET_TRAVEL_LIST(state, travelPlanList) {
      state.travelList = [];
      if (travelPlanList.length != 0) {
        travelPlanList.forEach((travelPlan) => {
          // let contentList = [];
          // travelPlan.contentIds.forEach((contentId) => {
          //   contentList.push(contentId);
          // });
          // console.log("contentList", contentList);
          state.travelList.push({
            planNo: travelPlan.planNo,
            title: travelPlan.title,
            content: travelPlan.content,
            userId: travelPlan.userId,
            startDate: travelPlan.startDate,
            endDate: travelPlan.endDate,
            planPulic: travelPlan.planPulic,
            grade: travelPlan.grade,
            // contentIds: contentList,
          });
        });
      }
    },

    SET_TRAVEL_PLAN_CONTENT(state, planNo) {
      state.travelPlanNo = planNo;
      state.travelPlanContent = [];
      state.travelList.forEach((travel) => {
        if (travel.planNo == planNo) {
          state.travelPlanContent = travel;
        }
      });
      console.log("travelPlanContent", state.travelPlanContent);
    },
    SET_TRAVEL_PLAN(state, data) {
      state.travelPlan = [];
      data.forEach((item) => {
        state.travelPlan.push(item.contentId);
      });
      console.log("travelPlan", state.travelPlan);
    },
    SET_TRAVEL_MARKERS(state, data) {
      state.travelMarkers = [];
      data.forEach((item) => {
        state.travelMarkers.push([
          item.latitude,
          item.longitude,
          item.contentId,
          item.contenttypeid,
          item.title,
          item.addr1,
          item.image1,
          item.tel,
        ]);
      });
      state.travelMarkers.sort((a, b) => a[5] - b[5]);
      console.log("travelMarkers", state.travelMarkers);
    },

    CLEAR_MARKER_POSITIONS(state) {
      state.markerPositions = [];
    },
    CLEAR_PLAN_MARKERS(state) {
      state.planMarkers = [];
    },
    CLEAR_TRAVEL_LIST(state) {
      state.travelList = [];
    },
    CLEAR_TRAVEL_PLAN(state) {
      state.travelPlan = [];
    },
    CLEAR_TRAVEL_PLAN_CONTENT(state) {
      state.travelPlanContent = [];
    },
    CLEAR_TRAVEL_MARKERS(state) {
      state.travelMarkers = [];
    },

    // attraction data set
    SET_ATTRACTION_LIST(state, attractionList) {
      console.log("검색된 관광지 개수 : " + attractionList.length);
      if (attractionList.length != 0) {
        attractionList.forEach((attraction) => {
          state.attractions.push({
            addr1: attraction.addr1,
            addr2: attraction.addr2,
            contentId: attraction.contentId,
            contenttypeid: attraction.contenttypeid,
            image1: attraction.image1,
            longitude: attraction.longitude,
            latitude: attraction.latitude,
            tel: attraction.tel,
            title: attraction.title,
            overview: attraction.overview,
          });
        });
      }
    },
    SET_ATTRACTION(state, attraction) {
      state.attraction = {
        addr1: attraction.addr1,
        addr2: attraction.addr2,
        contentId: attraction.contentId,
        contenttypeid: attraction.contenttypeid,
        image1: attraction.image1,
        image2: attraction.image2,
        longitude: attraction.longitude,
        latitude: attraction.latitude,
        tel: attraction.tel,
        title: attraction.title,
        overview: attraction.overview,
      };
    },
    CLEAR_ATTRACTION_LIST(state) {
      state.attractions = [];
    },
    CLEAR_ATTRACTION(state) {
      state.attraction = null;
    },
  },
  actions: {
    // map data get
    getPosition({ commit }, position) {
      commit("SET_LATITUDE", position.latitude);
      commit("SET_LONGITUDE", position.longitude);
    },

    // attraction data get
    getAttractionList({ commit }, Code) {
      const params = {
        sido: Code.sidoCode,
        gugun: Code.gugunCode,
        content: Code.contentCode,
      };
      AttractionList(
        params,
        ({ data }) => {
          console.log(data);
          commit("SET_MARKER_POSITIONS", data);
          commit("SET_ATTRACTION_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getSearchAttractionList({ commit }, attr) {
      if (attr.doIdx == null) attr.doIdx = "null";
      if (attr.sigunguIdx == null) attr.sigunguIdx = "null";
      if (attr.word == "") attr.word = "null";

      const params = {
        sido: attr.sidoCode,
        gugun: attr.gugunCode,
        word: attr.word,
      };

      AttractionSearchList(
        params,
        ({ data }) => {
          // console.log(data);
          commit("SET_MARKER_POSITIONS", data);
          commit("SET_ATTRACTION_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getAttraction({ commit }, contentid) {
      const params = { contentId: contentid };
      Attraction(
        params,
        ({ data }) => {
          commit("SET_ATTRACTION", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getTravelList({ commit }, param) {
      if (param.userId) {
        // console.log(param.userId);
        mylistPlan(
          param,
          ({ data }) => {
            commit("SET_TRAVEL_LIST", data);
          },
          (error) => {
            console.log(error);
          }
        );
      } else {
        listPlan(
          param,
          ({ data }) => {
            commit("SET_TRAVEL_LIST", data);
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },
    async getTravelPlan({ commit }, planNo) {
      await getPlan(
        planNo,
        ({ data }) => {
          // console.log("getTravelPlan", data);
          commit("SET_TRAVEL_PLAN", data);
          commit("SET_TRAVEL_MARKERS", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default attractionStore;
