<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!-- Footer -->
<footer
    class="navbar navbar-expand navbar-light bg-light container justify-content-end"
>
    <div class="row">
        <ul class="col navbar-nav">
            <li>
                <a href="#" class="nav-link text-secondary home-link">홈</a>
            </li>
            <li>
                <a href="#" id="notice" class="nav-link text-secondary inform-link"
                    >여행정보공유</a
                >
            </li>
            <li>
                <a
                    href="#"
                    class="nav-link text-secondary attraction-link"
                    >관광지 정보</a
                >
            </li>
            <li>
                <a href="#" class="nav-link text-secondary plan-link"
                    >나만의 여행계획</a
                >
            </li>
            <li>
                <a href="#" id="hotPlace" class="nav-link text-secondary hotPlace-link"
                    >HotPlace</a
                >
            </li>
            <li>
                <a href="#" class="nav-link text-secondary">사이트소개</a>
            </li>
            <li>
                <a href="#" class="nav-link text-secondary">개인정보처리방침</a>
            </li>
            <li>
                <a href="#" class="nav-link text-secondary">이용약관</a>
            </li>
            <li>
                <label class="nav-link text-secondary"
                    >&copy; SSAFY Corp.</label
                >
            </li>
        </ul>
    </div>
    <script>

        let attractionLinks = document.querySelectorAll(".attraction-link");
        for(let attractionLink of attractionLinks){
            attractionLink.href = "${root}/attraction/view";
        }
        
        let hotPlaceLinks = document.querySelectorAll(".hotPlace-link");
        for(let hotPlaceLink of hotPlaceLinks){
        	hotPlaceLink.href = "${root}/hotplace/list.jsp"
        }
        
        let noticeLinks = document.querySelectorAll(".notice-link");
        for(let noticeLink of noticeLinks){
        	noticeLink.href = "${root}/noticeArticle?action=list"
        }
        
        let informLinks = document.querySelectorAll(".inform-link");
        for(let informLink of informLinks){
        	informLink.href = "${root}/article?action=list"
        }
        
        let homeLinks = document.querySelectorAll(".home-link");
        for(let homeLink of homeLinks){
        	homeLink.href = "${root}/index.jsp"
        }

        let planLinks = document.querySelectorAll(".plan-link");
        for(let planLink of planLinks){
        	planLink.href = "${root}/tripplan/view"
        }       
    </script>
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"
    ></script>
</footer>
