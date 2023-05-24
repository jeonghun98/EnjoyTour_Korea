<template>
  <div class="container" id="home-contents">
    <div class="row p-1">
      <!-- 여백 -->
      <section class="col-lg-1 d-none d-lg-inline-block bg-dark"></section>
      <!-- 슬라이드 -->
      <section class="col-lg-9 col-sm-12 p-0">
        <b-carousel
          id="carousel-1"
          v-model="slide"
          :interval="6000"
          controls
          indicators
          background="#ababab"
          img-width="1024"
          img-height="480"
          style="text-shadow: 1px 1px 2px #333"
        >
          <!-- Text slides with image -->
          <b-carousel-slide
            caption="South Korea Tour"
            text="지금 여행 계획을 세우고 다녀온 핫플을 자랑해보세요!!"
            :img-src="require('../assets/img/korea5.jpg')"
          >
          </b-carousel-slide>

          <b-carousel-slide
            caption="한국 여행지"
            text="지역과 관광지 유형에 따라 여행지를 검색해보세요"
            :img-src="require('../assets/img/korea4.jpg')"
            ><b-button
              variant="outline-secondary"
              class="btn-outline-secondary"
              @click="moveAttraction"
              >더보기</b-button
            ></b-carousel-slide
          >

          <b-carousel-slide
            caption="모두의 여행계획"
            text="여행 계획을 세우고 다른 사람들과 공유해보세요"
            :img-src="require('../assets/img/korea3.jpg')"
            ><b-button variant="outline-secondary" class="btn-outline-secondary" @click="movePlan"
              >더보기</b-button
            ></b-carousel-slide
          >

          <b-carousel-slide
            caption="핫플 자랑하기"
            text="자신이 다녀온 핫플레이스의 경험을 나누어주세요"
            :img-src="require('../assets/img/korea3.jpg')"
            ><b-button
              variant="outline-secondary"
              class="btn-outline-secondary"
              @click="moveHotplace"
              >더보기</b-button
            ></b-carousel-slide
          >
        </b-carousel>
      </section>

      <!-- 여백 -->
      <section class="col-lg-2 d-none d-lg-inline-block bg-dark"></section>
    </div>
    <div class="p-1 container-fluid" id="attraction">
      <b-row class="row align-items-center">
        <b-col class="col-lg-3 col-12">
          <!-- 설명글-->
          <div class="row">
            <div class="divider col-3 align-self-center" />
          </div>
          <h2 class="mb-3 text-secondary">한국 여행지</h2>
          <div class="row p-3">
            <b-row class="row pl-3 mb-2 font-weight-bold"
              >한국의 아름다움을 느낄 수 있는 관광지를 알려드립니다.</b-row
            >
            <b-row class="row pl-3 mb-2 font-weight-bold"
              >관광지 유형에 따라 여행지를 선택할 수 있습니다.</b-row
            >
            <b-row class="w-100 mb-3"></b-row>
            <b-row class="row pl-3 mb-2 font-weight-bold">
              <b-button
                variant="outline-secondary"
                class="btn-outline-secondary"
                @click="moveAttraction"
                >더보기</b-button
              >
            </b-row>
          </div>
        </b-col>
        <b-col class="col-lg-9 col-12">
          <!-- 카드 -->
          <div class="container" id="custom-cards">
            <div class="row row-cols-3 row-cols-lg-3 align-items-stretch g-4 py-5">
              <div class="col mb-1">
                <div
                  class="card card-cover h-100 overflow-hidden text-white bg-dark rounded-5 shadow-lg"
                  style="
                    background-image: url('https://images.squarespace-cdn.com/content/v1/586ebc34d482e9c69268b69a/1624387036637-3HYG08Z0XZEG4KALHROR/image-asset.png?format=750w');
                  "
                >
                  <div class="d-flex flex-column h-100 p-5 pb-3 text-white text-shadow-1">
                    <h2 class="pt-5 mt-5 mb-4 display-6 lh-1 fw-bold">서울</h2>
                  </div>
                </div>
              </div>
              <div class="col mb-1">
                <div
                  class="card card-cover h-100 overflow-hidden text-white bg-dark rounded-5 shadow-lg"
                  style="
                    background-image: url('https://images.squarespace-cdn.com/content/v1/586ebc34d482e9c69268b69a/1624387216622-W0X3QWQKO4N7J3PPSLIR/20201230201102636_L212Z69O.png?format=750w');
                  "
                >
                  <div class="d-flex flex-column h-100 p-5 pb-3 text-white text-shadow-1">
                    <h2 class="pt-5 mt-5 mb-4 display-6 lh-1 fw-bold">제주</h2>
                  </div>
                </div>
              </div>
              <div class="col mb-1">
                <div
                  class="card card-cover h-100 overflow-hidden text-white bg-dark rounded-5 shadow-lg"
                  style="
                    background-image: url('https://images.squarespace-cdn.com/content/v1/586ebc34d482e9c69268b69a/1624387234961-KJSQY04HPHDG9OGZJWVJ/20201230201238973_JSETFUI7.png?format=750w');
                  "
                >
                  <div class="d-flex flex-column h-100 p-5 pb-3 text-shadow-1">
                    <h2 class="pt-5 mt-5 mb-4 display-6 lh-1 fw-bold">보성</h2>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </b-col>
      </b-row>
    </div>
  </div>
</template>

<script>
export default {
  name: "AppHome",
  data() {
    return {
      slide: 0,
    };
  },
  methods: {
    moveAttraction() {
      this.$router.replace({
        name: "Attraction",
      });
    },
    movePlan() {
      this.$router.replace({
        name: "plan",
      });
    },
    moveHotplace() {
      this.$router.replace({
        name: "hotplace",
      });
    },
  },
};
</script>

<style>
@import "@/assets/css/bootstrapHorizon.css";

.row {
  display: flex;
  flex-wrap: wrap;
  margin-right: -15px;
  margin-left: -15px;
}
.align-items-center {
  align-items: center !important;
}
.divider {
  width: 55%;
  margin: 2rem auto;
  /* border-top: 0.5rem solid #59b3f7; */
}
.align-self-center {
  align-self: center !important;
}
.col-3 {
  flex: 0 0 25%;
  max-width: 25%;
}
.mb-3,
.my-3 {
  margin-bottom: 1rem !important;
}
.text-secondary {
  color: #6c757d !important;
}
.mb-2,
.my-2 {
  margin-bottom: 0.5rem !important;
}
.w-100 {
  width: 100% !important;
}
.font-weight-bold {
  font-weight: 700 !important;
}
.col-12 {
  flex: 0 0 100%;
  max-width: 100%;
}
.col-lg-3 {
  flex: 0 0 25%;
  max-width: 25%;
}
.col-lg-9 {
  flex: 0 0 75%;
  max-width: 75%;
}
</style>
