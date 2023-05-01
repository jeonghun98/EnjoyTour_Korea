<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<c:set var="path" value="${pageContext.request.servletPath}"/>

<!-- 상단 navbar start -->
<nav class="navbar navbar-expand-lg bg-body">
    <div class="container pe-1 ps-1">
        <a
            class="navbar-brand link-light fw-bold fs-5 bg-dark p-1 home-link"
            href=""
        >
            ET
        </a>
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
            <c:if test="${empty userinfo}">
                <ul
                    class="navbar-nav mb-2 mb-lg-0 before-login"
                    style="display: flex"
                >
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
            </c:if>
            <!-- 로그인 후-->
            <c:if test="${not empty userinfo}">
                <ul
                    class="navbar-nav mb-2 mb-lg-0 after-login"
                    style="display: flex"
                >
                    <li class="nav-item">
                        <a
                            class="nav-link active"
                            aria-current="page"
                            href="${root}/user?action=logout"
                            >로그아웃</a
                        >
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
                                <a class="dropdown-item" href="#"
                                    >회원정보관리</a
                                >
                            </li>
                        </ul>
                    </li>
                    </c:if>
                </ul>
            </c:if>
        </div>
    </div>
</nav>

<!-- 상단 navbar end -->
<div class="container bg-body">
    <div class="row">
        <div class="col p-1">
            <div class="nav-scroller">
                <nav
                    class="nav nav-underline"
                    aria-label="Secondary navigation"
                >
                    <c:if test = "${fn:contains(path, 'index')}">
                    <a
                        class="nav-link ps-0 pe-0 ms-2 me-2 active home-link"
                        href=""
                        >홈</a
                    >
                    </c:if>
                    <c:if test = "${not fn:contains(path, 'index')}">
                    <a
                        class="nav-link ps-0 pe-0 ms-2 me-2 home-link"
                        href=""
                        >홈</a
                    >
                    </c:if>
                    
                  	<c:if test = "${fn:contains(path, 'notice')}">
                    <a
                        class="nav-link ps-0 pe-0 ms-2 me-2 active notice-link"
                        href="#"
                        >공지사항</a
                    >
                    </c:if>
                    <c:if test = "${not fn:contains(path, 'notice')}">
                    <a
                        class="nav-link ps-0 pe-0 ms-2 me-2 notice-link"
                        href="#"
                        >공지사항</a
                    >
                    </c:if>
                    
                    <c:if test = "${fn:contains(path, 'attraction')}">
                    <a
	                    class="nav-link ps-0 pe-0 ms-2 me-2 active attraction-link"
	                    href=""
                        >관광지 정보</a
                    >
                    </c:if>
                    <c:if test = "${not fn:contains(path, 'attraction')}">
                    <a
	                    class="nav-link ps-0 pe-0 ms-2 me-2 attraction-link"
	                    href=""
                        >관광지 정보</a
                    >
                    </c:if>
                    <c:if test = "${fn:contains(path, 'inform')}">
                    <a
                        class="nav-link ps-0 pe-0 ms-2 me-2 active inform-link"
                        href=""
                        >여행정보공유</a
                    >
                    </c:if>
                    <c:if test = "${not fn:contains(path, 'inform')}">
                    <a
                        class="nav-link ps-0 pe-0 ms-2 me-2 inform-link"
                        href=""
                        >여행정보공유</a
                    >
                    </c:if>
                    
                    <c:if test = "${fn:contains(path, 'plan')}">
                    <a
                        class="nav-link ps-0 pe-0 ms-2 me-2 active plan-link"
                        href="#"
                        >나만의 여행경로</a
                    >
                    </c:if>
                    <c:if test = "${not fn:contains(path, 'plan')}">
					<a
                        class="nav-link ps-0 pe-0 ms-2 me-2 plan-link"
                        href="#"
                        >나만의 여행경로</a
                    >
                    </c:if>

                    <c:if test = "${fn:contains(path, 'hotplaces')}">
                    <a
                        class="nav-link link-danger ps-0 pe-0 ms-2 me-2 active hotPlace-link"
                        href=""
                        >HotPlace</a
                    >
                    </c:if>
                    <c:if test = "${not fn:contains(path, 'hotplaces')}">
                    <a
                        class="nav-link link-danger ps-0 pe-0 ms-2 me-2 hotPlace-link"
                        href=""
                        >HotPlace</a
                    >
                    </c:if>
                </nav>
            </div>
        </div>
        <hr style="position: relative; top: -5px" />
    </div>
</div>