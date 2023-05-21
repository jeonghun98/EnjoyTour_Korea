<template>
  <div class="container" id="home-contents">
    <div class="row p-1">
      <!-- 여백 -->
      <section class="col-lg-1 d-none d-lg-inline-block bg-dark"></section>
      <!-- 슬라이드 -->
      <section class="col-lg-9 col-sm-12 p-0">
        <div id="carouselExampleDark" class="carousel carousel-dark slide" data-bs-ride="carousel">
          <div class="carousel-indicators">
            <button
              type="button"
              data-bs-target="#carouselExampleDark"
              data-bs-slide-to="0"
              class="active bg-light"
              aria-current="true"
              aria-label="Slide 1"
            ></button>
            <button
              type="button"
              data-bs-target="#carouselExampleDark"
              data-bs-slide-to="1"
              class="bg-light"
              aria-label="Slide 2"
            ></button>
            <button
              type="button"
              data-bs-target="#carouselExampleDark"
              data-bs-slide-to="2"
              class="bg-light"
              aria-label="Slide 3"
            ></button>
          </div>
          <div id="slideWrap" class="carousel" data-ride="carousel">
          <div class="carousel-inner">
            <div class="carousel-item active" data-bs-interval="10000">
              <img src="../assets/img/korea5.jpg" class="d-block w-100" alt="..." />
              <div class="carousel-caption d-none d-md-block text-light bg-opacity-50 bg-dark">
                <h5>Gamcheon culture village</h5>
                <p>busan, korea national, south korea.</p>
              </div>
            </div>
            <div class="carousel-item" data-bs-interval="2000">
              <img src="../assets/img/korea4.jpg" class="d-block w-100" alt="..." />
              <div class="carousel-caption d-none d-md-block text-light bg-opacity-50 bg-dark">
                <h5>Brown gazebo</h5>
                <p>nature, landscape, mountains, trees, forest, house</p>
              </div>
            </div>
            <div class="carousel-item">
              <img src="../assets/img/korea3.jpg" class="d-block w-100" alt="..." />
              <div class="carousel-caption d-none d-md-block text-light bg-opacity-50 bg-dark">
                <h5>Bukchon hanok village</h5>
                <p>south korea, seoul</p>
              </div>
            </div>
          </div>
        </div>

          
          <button
            class="carousel-control-prev text-bg-light"
            type="button"
            data-bs-target="#carouselExampleDark"
            data-bs-slide="prev"
          >
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
          </button>
          <button
            class="carousel-control-next"
            type="button"
            data-bs-target="#carouselExampleDark"
            data-bs-slide="next"
          >
            <!-- <span class="carousel-control-next-icon" aria-hidden="true"></span> -->
            <span class="visually-hidden">Next</span>
          </button>
        </div>
      </section>
      <!-- 여백 -->
      <section class="col-lg-2 d-none d-lg-inline-block bg-dark"></section>
    </div>
  </div>
</template>

<script>
export default {
  name: "AppHome",
};
</script>

<style>
@import "@/assets/css/bootstrapHorizon.css";
</style>
