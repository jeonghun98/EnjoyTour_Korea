<template>
  <div class="container" id="home-contents">
    <div class="row p-1">
      <!-- 여백 -->
      <!-- <section class="col-lg-1 d-none d-lg-inline-block bg-dark"></section> -->
      <!-- 슬라이드 -->
      <section class="col-lg-12 col-sm-12 p-0">
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
            :img-src="require('../assets/img/korea0.png')"
          >
          </b-carousel-slide>

          <b-carousel-slide
            caption="한국 여행지"
            text="지역과 관광지 유형에 따라 여행지를 검색해보세요"
            :img-src="require('../assets/img/korea1.png')"
            ><b-button variant="outline-light" @click="moveAttraction"
              >더보기</b-button
            ></b-carousel-slide
          >

          <b-carousel-slide
            caption="모두의 여행계획"
            text="여행 계획을 세우고 다른 사람들과 공유해보세요"
            :img-src="require('../assets/img/korea2.png')"
            ><b-button variant="outline-light" @click="movePlan"
              >더보기</b-button
            ></b-carousel-slide
          >
          <b-carousel-slide
            caption="핫플 자랑하기"
            text="자신이 다녀온 핫플레이스의 경험을 나누어주세요"
            :img-src="require('../assets/img/korea3.png')"
            ><b-button variant="outline-light" @click="moveHotplace"
              >더보기</b-button
            ></b-carousel-slide
          >
        </b-carousel>
      </section>

      <!-- 여백 -->
      <!-- <section class="col-lg-1 d-none d-lg-inline-block bg-dark"></section> -->
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
        <template v-if="attractions.length !== 0">
          <b-col class="col-lg-9 col-12">
            <b-card-group deck>
              <b-col>
                <b-row>
                  <b-card
                    no-body
                    class="overflow-hidden"
                    style="
                      max-width: 300px;
                      height: 200px;
                      object-fit: cover;
                    "
                  >
                    <b-card-img
                      :src="attractions[0].image1"
                      alt="Image"
                      class="rounded-0"
                      style="object-fit: cover; height: 100%"
                    ></b-card-img> 
                    </b-card>
                </b-row>
                <b-row class="overflow-hidden mt-2">
                  <b-col class="col-lg-1"></b-col>
                  <b-col class="col-lg-10">
                    <h5
                      class="pt-1 display-6 lh-1 fw-bold text-secondary"
                      style="text-align: center"
                    >
                      {{ attractions[0].title }}
                    </h5>
                  </b-col>
                  <b-col class="col-lg-1"></b-col>
                </b-row>
              </b-col>
              
              <b-col>
                <b-row>
                  <b-card
                    no-body
                    class="overflow-hidden"
                    style="max-width: 300px; height: 200px; object-fit: cover"
                  >
                    <b-card-img
                      :src="attractions[1].image1"
                      alt="Image"
                      class="rounded-0"
                      style="object-fit: cover; height: 100%"
                    ></b-card-img>
                  </b-card>
                </b-row> 
                <b-row class="overflow-hidden mt-2">
                  <b-col class="col-lg-1"></b-col>
                  <b-col class="col-lg-10">
                    <h5
                      class="pt-1 display-6 lh-1 fw-bold text-secondary"
                      style="text-align: center"
                    >
                      {{ attractions[2].title }}
                    </h5>
                  </b-col>
                  <b-col class="col-lg-1"></b-col>
                </b-row>
              </b-col>
              
              <b-col>
                <b-row>
                  <b-card
                    no-body
                    class="overflow-hidden"
                    style="max-width: 300px; height: 200px; object-fit: cover"
                  >
                    <b-card-img
                      :src="attractions[2].image1"
                      alt="Image"
                      class="rounded-0"
                      style="object-fit: cover; height: 100%"
                    ></b-card-img>
                  </b-card>
                </b-row>
                <b-row class="overflow-hidden mt-2">
                  <b-col class="col-lg-1"></b-col>
                  <b-col class="col-lg-10">
                    <h5
                      class="pt-1 display-6 lh-1 fw-bold text-secondary"
                      style="text-align: center"
                    >
                      {{ attractions[1].title }}
                    </h5>
                  </b-col>
                  <b-col class="col-lg-1"></b-col>
                </b-row>
              </b-col>
            </b-card-group>
          </b-col>
        </template>
      </b-row>
    </div>
  </div>
</template>

<script>
import { AttractionRandomList } from "@/api/attraction";

export default {
  name: "AppHome",
  data() {
    return {
      slide: 0,
      attractions: [],
    };
  },
  mounted() {
    this.selectAttractionRandomList();
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
    selectAttractionRandomList() {
      AttractionRandomList(
        ({ data }) => {
          console.log("data", data);
          this.attractions = data;
          console.log(this.attractions[0].image1);
          console.log(this.attractions[0]);
          console.log(this.attractions[1]);
          console.log(this.attractions[2]);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style>
@import "@/assets/css/bootstrapHorizon.css";

.carousel-item {
  width: 750px;
  height: 460px;
}
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
/* .text-secondary {
  color: #6c757d !important;
} */
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
#custom-cards {
  color: white;
  font-size: x-small;
}
</style>
