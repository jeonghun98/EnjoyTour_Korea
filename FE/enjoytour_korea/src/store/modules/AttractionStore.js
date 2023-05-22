import { AttractionList, AttractionSearchList, Attraction } from "@/api/attraction.js";

const attractionStore = {
  namespaced: true,
  state: {
    // map data
    latitude: 37.5013068,
    longitude: 127.0396597,
    markerPositions: [],
    planMarkers: [], // 선택 중인 여행 계획 마커

    travelList: [], // 기존 여행 계획 목록
    travelPlanId: 0, // 선택한 기존 여행 계획
    travelPlan: [], // 선택한 기존 여행 상세 경로
    travelMarkers: [], // 선택한 기존 여행 마커

    // attraction data
    attractions: [],
    attraction: null,
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
            attraction.contentid,
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
            planItem.contentid,
            planItem.contenttypeid,
            planItem.title,
            planItem.addr1,
            planItem.image1,
            planItem.tel,
          ]);
        });
      }
    },
    // SET_TRAVEL_LIST(state, travelPlanList) {
    //   state.travelList = [];
    //   if (travelPlanList.length != 0) {
    //     travelPlanList.forEach((travelPlan) => {
    //       let detailList = [];
    //       travelPlan.planDetailList.forEach((detail) => {
    //         detailList.push(detail);
    //       });

    //       state.travelList.push({
    //         planId: travelPlan.planId,
    //         title: travelPlan.title,
    //         content: travelPlan.content,
    //         userId: travelPlan.userId,
    //         planTime: travelPlan.planTime,
    //         planDetailList: detailList,
    //       });
    //     });
    //   }
    // },
    // SET_TRAVEL_PLAN(state, plan) {
    //   state.travelPlanId = plan.planId;
    //   state.travelPlan = [];
    //   plan.planDetailList.forEach((detail) => {
    //     state.travelPlan.push(detail);
    //   });
    // },
    // SET_TRAVEL_MARKERS(state) {
    //   if (state.travelPlan.length != 0) {
    //     state.travelMarkers = [];
    //     state.travelPlan.forEach((planItem) => {
    //       http
    //         .get(`/attraction/view/${planItem.contentId}`)
    //         .then(({ data }) => {
    //           state.travelMarkers.push([
    //             data.mapY,
    //             data.mapX,
    //             data.contentId,
    //             data.contentTypeId,
    //             data.title,
    //             planItem.planOrder,
    //           ]);
    //         })
    //         .catch((error) => {
    //           console.log(error);
    //         });
    //     });
    //     state.travelMarkers.sort((a, b) => a[5] - b[5]);
    //     console.log(state.travelMarkers);
    //   }
    // },
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
            // areaCode: attraction.areaCode,
            // bookTour: attraction.bookTour,
            // cat1: attraction.cat1,
            // cat2: attraction.cat2,
            // cat3: attraction.cat3,
            contentid: attraction.contentid,
            contenttypeid: attraction.contenttypeid,
            // createdTime: attraction.createdTime,
            image1: attraction.image1,
            image2: attraction.image2,
            longitude: attraction.longitude,
            latitude: attraction.latitude,
            // mLevel: attraction.mLevel,
            // modifiedTime: attraction.modifiedTime,
            // readCount: attraction.readCount,
            // sigunguCode: attraction.sigunguCode,
            tel: attraction.tel,
            title: attraction.title,
            // zipCode: attraction.zipCode,
            overview: attraction.overview,
          });
        });
        }
    },
    SET_ATTRACTION(state, attraction) {
      state.attraction = {
        addr1: attraction.addr1,
        addr2: attraction.addr2,
        // areaCode: attraction.areaCode,
        // bookTour: attraction.bookTour,
        // cat1: attraction.cat1,
        // cat2: attraction.cat2,
        // cat3: attraction.cat3,
        contentid: attraction.contentid,
        contenttypeid: attraction.contenttypeid,
        // createdTime: attraction.createdTime,
        image1: attraction.image1,
        image2: attraction.image2,
        longitude: attraction.longitude,
        latitude: attraction.latitude,
        // mLevel: attraction.mLevel,
        // modifiedTime: attraction.modifiedTime,
        // readCount: attraction.readCount,
        // sigunguCode: attraction.sigunguCode,
        tel: attraction.tel,
        title: attraction.title,
        // zipCode: attraction.zipCode,
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
      getAttraction({ commit }, contentId) {
        const params = {contentid : contentId};
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
    // getTravelList({ commit }) {
    //   http
    //     .get(`/plan/list`)
    //     .then(({ data }) => {
    //       if (data.message === "success") {
    //         commit("SET_TRAVEL_LIST", data.list);
    //       }
    //     })
    //     .catch((error) => {
    //       console.log(error);
    //     });
    // },
  },
};

export default attractionStore;
