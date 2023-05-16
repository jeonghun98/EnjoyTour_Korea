<template>
  <div>
    <nav class="navbar navbar-expand-lg bg-body">
      <div class="container pe-1 ps-1">
        <a class="navbar-brand link-light fw-bold fs-5 bg-dark p-1 home-link" href=""> ET </a>
        <span class="navbar-brand link-dark fw-bold fs-3">EnjoyTrip</span>
        <!-- 네비게이션 바 버튼 -->
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <div class="navbar-nav mb-2 mb-lg-0 p-1">
            <div class="input-group">
              <span class="input-group-text" id="basic-addon1">@</span>
              <input
                type="text"
                class="form-control"
                placeholder="검색"
                aria-label="Search"
                aria-describedby="basic-addon1"
              />
            </div>
          </div>
          <div class="navbar-nav me-auto"></div>

          <!-- 로그인 전 -->
          <!-- <c:if test="${empty userinfo}">
          <ul class="navbar-nav mb-2 mb-lg-0 before-login" style="display: flex">
            <li class="nav-item">
              <a
                id="login"
                class="nav-link dropdown-item active btn btn-primary btn-dark"
                type="button"
                aria-current="page"
                data-bs-toggle="modal"
                data-bs-target="#login-modal"
                >로그인</a
              >
            </li>
            <li class="nav-item">
              <a
                class="nav-link dropdown-item active btn btn-primary btn-dark"
                type="button"
                data-bs-toggle="modal"
                data-bs-target="#register-modal"
                >회원가입</a
              >
            </li>
          </ul>
        </c:if> -->

          <!-- 로그인 후-->
          <!-- <c:if test="${not empty userinfo}">
          <ul class="navbar-nav mb-2 mb-lg-0 after-login" style="display: flex">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="${root}/user/logout">로그아웃</a>
            </li>
            <li class="nav-item">
              <a
                class="nav-link active"
                type="button"
                aria-current="page"
                data-bs-toggle="modal"
                data-bs-target="#mypage-modal"
                href="#"
                >마이페이지</a
              >
            </li>
            <c:if test="${userinfo.userId eq '관리자'}">
              <li class="nav-item dropdown">
                <a
                  class="nav-link dropdown-toggle"
                  href="#"
                  role="button"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
                >
                  관리자
                </a>
                <ul class="dropdown-menu">
                  <li>
                    <a
                      class="dropdown-item btn btn-primary"
                      type="button"
                      data-bs-toggle="modal"
                      data-bs-target="#pollModal"
                      href="#"
                      >회원정보보기</a
                    >
                  </li>
                  <li>
                    <a class="dropdown-item" href="#">회원정보관리</a>
                  </li>
                </ul>
              </li>
            </c:if>
          </ul>
        </c:if> -->
        </div>
      </div>
    </nav>
    <!-- 상단 navbar end -->
    <div class="container bg-body">
      <div class="row">
        <div>
          <b-nav tabs align="center">
            <b-nav-item active><router-link to="/">홈</router-link></b-nav-item>
            <b-nav-item><router-link to="/notice">공지사항</router-link></b-nav-item>
            <b-nav-item><router-link to="/attraction">한국 여행지</router-link></b-nav-item>
            <b-nav-item><router-link to="/plan">모두의 여행계획</router-link></b-nav-item>
            <b-nav-item><router-link to="/hotplace">핫플자랑하기</router-link></b-nav-item>
          </b-nav>
        </div>
        <hr style="position: relative; top: -5px" />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "TheNav",
  components: {},
  data() {
    return {
      message: "",
    };
  },
  created() {},
  methods: {},
};
</script>

<style scoped></style>
