<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/head.jsp"%>
<style>
/* Import Google font - Poppins */
@import
	url("https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap")
	;

/* https://www.codingnepalweb.com/drag-and-drop-sortable-list-html-javascript/ --> */
.sortable-list .item {
	list-style: none;
	display: flex;
	cursor: move;
	background: #fff;
	align-items: center;
	border-radius: 5px;
	padding: 10px 17px;
	margin-bottom: 11px;
	margin-top: 11px;
	/* box-shadow: 0 2px 4px rgba(0,0,0,0.06); */
	border: 1px solid #ccc;
	justify-content: space-between;
}

.item .details {
	display: flex;
	align-items: center;
}

.item .details img {
	height: 43px;
	width: 43px;
	pointer-events: none;
	margin-right: 12px;
	object-fit: cover;
	border-radius: 50%;
}

.item .details span {
	font-size: 1.13rem;
}

.item i {
	color: #474747;
	font-size: 1.13rem;
}

.item.dragging {
	opacity: 0.6;
}
</style>
<style>
.wrap {
	position: absolute;
	left: 65px;
	bottom: -30px;
	width: 288px;
	height: 132px;
	margin-left: -144px;
	text-align: left;
	overflow: hidden;
	font-size: 12px;
	font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
	line-height: 1.5;
}

.wrap .info {
	width: 286px;
	height: 120px;
	border-radius: 5px;
	border-bottom: 2px solid #ccc;
	border-right: 1px solid #ccc;
	overflow: hidden;
	background: #fff;
}

.wrap .info:nth-child(1) {
	border: 0;
	box-shadow: 0px 1px 2px #888;
}

.info .title {
	padding: 5px 0 0 10px;
	height: 30px;
	background: #eee;
	border-bottom: 1px solid #ddd;
	font-size: 18px;
	font-weight: bold;
	overflow: hidden;
}

.info .close {
	position: absolute;
	top: 10px;
	right: 10px;
	color: #888;
	width: 17px;
	height: 17px;
	background:
		url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png");
}

.info .close:hover {
	cursor: pointer;
}

.info .body {
	position: relative;
	overflow: hidden;
}

.info .desc {
	position: relative;
	margin: 13px 0 0 90px;
	height: 75px;
}

.desc .ellipsis {
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}

.info .img {
	position: absolute;
	top: 6px;
	left: 5px;
	width: 73px;
	height: 71px;
	border: 1px solid #ddd;
	color: #888;
	overflow: hidden;
}

.info:after {
	content: "";
	position: absolute;
	margin-left: -12px;
	left: 50%;
	bottom: 0;
	width: 22px;
	height: 12px;
	background:
		url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}

.info .registerHotPlace {
	color: #5085bb;
}

.info .addTrack {
	color: #5085bb;
}
</style>
</head>
<body>
	<div class="wrapper">
		<%@ include file="/include/nav.jsp"%>

		<!-- 관광지 정보 컨텐츠 START  -->
		<div class="container">
			<div
				class="row text-light bg-image p-1 me-1 ms-1 shadow-1-strong rounded content-title"
				style="
                        background-image: url('${root}/assets/img/bg2.jpg');
                        background-size: cover;
                        height: 105px;
                    ">
				<div class="col-12 text-light">
					<h3 class="fw-bold pt-3">나만의 여행경로</h3>
					<span class="content-desc">나만의 여행경로를 마음껏 짜보세요</span>
					<button type="button" id="btn-plan-write"
						class="ms-2 btn btn-light btn-sm btn-light">저장하기</button>
					<button type="button" id="btn-plan-opt"
						class="ms-2 btn btn-light btn-sm btn-light">최적화하기</button>
				</div>
			</div>
			<div class="row">
				<div class="col-3">
					<ul class="sortable-list" id="track-list"></ul>
				</div>

				<div class="col">
					<!-- 관광지 검색 start -->
					<form class="d-flex justify-content-around m-3" role="search"
						style="height: 3rem">
						<select id="search-area" class="form-select m-1"
							onchange="areaOnclickHandler(this)"
							aria-label="Default select example">
							<option value="0" selected>지역 선택</option>
						</select> <select id="search-region" class="form-select m-1"
							onchange="regionOnclickHandler(this, '${root}')"
							aria-label="Default select example">
							<option value="0" selected>전체</option>
						</select> <select id="search-content-id" class="form-select m-1"
							onchange="contentidOnclickHandler(this)"
							aria-label="Default select example">
							<option value="0" selected>관광지 유형</option>
							<option value="12">관광지</option>
							<option value="14">문화시설</option>
							<option value="15">축제공연행사</option>
							<option value="25">여행코스</option>
							<option value="28">레포츠</option>
							<option value="32">숙박</option>
							<option value="38">쇼핑</option>
							<option value="39">음식점</option>
						</select>
					</form>
					<div>
						<!-- map start -->
						<div class="row d-flex justify-content-center m-1">
							<div id="map" style="width: 100%; height: 40rem"></div>
						</div>
					</div>
					<!-- map end -->
				</div>
			</div>
		</div>
		<!-- 관광지 컨텐츠 컨텐츠 END  -->

		<!-- 모달공간 start-->
		<%@ include file="/user/modal.jsp"%>
		<!-- 모달공간 end-->
	</div>

	<%@ include file="/include/footer.jsp"%>

	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=74c637e1bdf74a44bf0242d00be654fb&libraries=services"></script>
	<script src="./assets/js/map.js"></script>
	<script>
            document.getElementById("btn-plan-write").onclick = function () {
                let list = [];
                document.querySelectorAll(".list-item-contentid").forEach(
                    (item) => {
                        list.push(item.value);
                    }
                );

                fetch("${root}/tripplan", {
                    method: "PUT",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(list)
                })
                    .then((res) => res.json())
                    .then((res) => {
                        console.log(res);
                    });
            };
            
            document.getElementById("btn-plan-opt").onclick = function () {
            	let contentid = [];
            	let title = [];
            	let images = [];
                let mapx = [];
                let mapy = [];
                
                document.querySelectorAll(".list-item-contentid").forEach(
                        (item) => {
                        	contentid.push(item.value);
                        }
                );
                document.querySelectorAll(".list-item-title").forEach(
                            (item) => {
                            	title.push(item.value);
                            }
                );
                document.querySelectorAll(".list-item-image").forEach(
                        (item) => {
                        	images.push(item.value);
                        }
            	);
                document.querySelectorAll(".list-item-mapx").forEach(
                    (item) => {
                    	mapx.push(item.value);
                    }
                );
                document.querySelectorAll(".list-item-mapy").forEach(
                        (item) => {
                        	mapy.push(item.value);
                        }
                );
                
                changePath(contentid, title, images, mapx, mapy);
            };
            
    </script>
</body>
</html>
