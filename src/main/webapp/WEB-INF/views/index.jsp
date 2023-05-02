<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@ include file="/WEB-INF/views/include/head.jsp" %>
    </head>

    <body>
        <div class="wrapper">
            <%@ include file="/WEB-INF/views/include/nav.jsp" %>
           

            <!-- 홈 컨텐츠 START  -->
            <div class="container" id="home-contents">
                <div class="row p-1">
                    <!-- 여백 -->
                    <section
                        class="col-lg-1 d-none d-lg-inline-block bg-dark"
                    ></section>
                    <!-- 슬라이드 -->
                    <section class="col-lg-9 col-sm-12 p-0">
                        <div
                            id="carouselExampleDark"
                            class="carousel carousel-dark slide"
                            data-bs-ride="carousel"
                        >
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
                            <div class="carousel-inner">
                                <div
                                    class="carousel-item active"
                                    data-bs-interval="10000"
                                >
                                    <img
                                        src="./assets/img/korea0.jpg"
                                        class="d-block w-100"
                                        alt="..."
                                    />
                                    <div
                                        class="carousel-caption d-none d-md-block text-light bg-opacity-50 bg-dark"
                                    >
                                        <h5>Gamcheon culture village</h5>
                                        <p>
                                            busan, korea national, south korea.
                                        </p>
                                    </div>
                                </div>
                                <div
                                    class="carousel-item"
                                    data-bs-interval="2000"
                                >
                                    <img
                                        src="./assets/img/korea1.jpg"
                                        class="d-block w-100"
                                        alt="..."
                                    />
                                    <div
                                        class="carousel-caption d-none d-md-block text-light bg-opacity-50 bg-dark"
                                    >
                                        <h5>Brown gazebo</h5>
                                        <p>
                                            nature, landscape, mountains, trees,
                                            forest, house
                                        </p>
                                    </div>
                                </div>
                                <div class="carousel-item">
                                    <img
                                        src="./assets/img/korea2.jpg"
                                        class="d-block w-100"
                                        alt="..."
                                    />
                                    <div
                                        class="carousel-caption d-none d-md-block text-light bg-opacity-50 bg-dark"
                                    >
                                        <h5>Bukchon hanok village</h5>
                                        <p>south korea, seoul</p>
                                    </div>
                                </div>
                            </div>
                            <button
                                class="carousel-control-prev text-bg-light"
                                type="button"
                                data-bs-target="#carouselExampleDark"
                                data-bs-slide="prev"
                            >
                                <span
                                    class="carousel-control-prev-icon"
                                    aria-hidden="true"
                                ></span>
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
                    <section
                        class="col-lg-2 d-none d-lg-inline-block bg-dark"
                    ></section>
                </div>
            </div>
            <!-- 홈 컨텐츠 END  -->

            <!-- 모달공간 start-->
            <%@ include file="/WEB-INF/views/user/modal.jsp" %>
            <!-- 모달공간 end-->
        </div>
        
        <%@ include file="/WEB-INF/views/include/footer.jsp" %>

    </body>
</html>
