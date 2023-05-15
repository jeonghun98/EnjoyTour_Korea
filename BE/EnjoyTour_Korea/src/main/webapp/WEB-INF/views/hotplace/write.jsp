<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
    	<%@ include file="/include/head.jsp"  %>
    </head>

    <body>
        <div class="wrapper">
        	<%@ include file="/include/nav.jsp" %>

            <!-- 나만의 여로경로 컨텐츠 START  -->
            <div class="container" id="trip-plan-contents">
                <div
                    class="row bg-image p-1 me-1 ms-1 shadow-1-strong rounded content-title"
                    style="
                        background-image: url('${root}/assets/img/bg0.jpg');
                        background-size: cover;
                        height: 105px;
                    "
                >
                    <div class="col-12 text-light">
                        <h3 class="fw-bold pt-3">HotPlace</h3>
                        <span class="content-desc">나만의 HotPlace를 공유해보세요</span>
                    </div>

                </div>

                <div class="row p-1">
					<!-- map start -->
	                <div class="col-lg-12 col-md-10 col-sm-12 mt-3 rounded">
	                    <div id="map" style="width: 100%; height: 30rem"></div>
	                </div>
	                <!-- map end -->
					<div class="col-lg-12 col-md-10 col-sm-12">
				          <form id="form-register" method="POST" action="">
				          	<input type="hidden" name="action" value="write">
				          	<div class="mt-3 text-danger fw-bold">스마트폰으로 찍은 사진을 올려주세요</div>
				          	<div class="mb-3 mt-1">
				              <input
				                type="file"
				                class="form-control"
				                id="file"
				                name="file"
				                accept=".gif, .jpg, .png"
				              />
				            </div>
				            <div class="mb-3 mt-3">
				              <label for="title" class="form-label">핫플이름</label>
				              <input
				                type="text"
				                class="form-control"
				                id="name"
				                name="name"
				                placeholder="핫플이름.."
				              />
				            </div>
				            <div class="mb-3">
				              <label for="date" class="form-label">다녀온 날짜</label>
				              <input
				                type="date"
				                class="form-control"
				                id="name"
				                name="name"
				                value="2023-03-27"
				              />
				            </div>
				          	<div class="mb-3">
				            <label for="date" class="form-label">장소유형</label>
				            <select
                            id="search-content-id"
                            class="form-select"
                            onchange="contentidOnclickHandler(this)"
                            aria-label="Default select example"
                        	>
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
                        	</div>
				            <div class="mb-3">
				              <label for="content" class="form-label">핫플 상세설명</label>
				              <textarea class="form-control" id="content" name="content" rows="7"></textarea>
				            </div>
				            <div class="col-auto text-center">
				              <button type="button" id="btn-inform-register" class="btn btn-outline-primary mb-3">
				                등록
				              </button>
				            </div>
				          </form>
			        </div>
                </div>
            </div>
            <!-- 나만의 여로경로 컨텐츠 END  -->

            <!-- 모달공간 start-->
            <%@ include file="/user/modal.jsp" %>
            <!-- 모달공간 end-->
        </div>
       <script
            type="text/javascript"
            src="//dapi.kakao.com/v2/maps/sdk.js?appkey=74c637e1bdf74a44bf0242d00be654fb&libraries=services"
        ></script>
        <script src="${root}/assets/js/map.js"></script>
        <%@ include file="/include/footer.jsp" %>

    </body>
</html>
