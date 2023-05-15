<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
    	<%@ include file="/include/head.jsp" %>
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
                        <h3 class="fw-bold pt-3">공지사항</h3>
                        <span class="content-desc">EnjoyTrip의 새로운 정보를 알려줄게요</span>
                    </div>
                    
                </div>
                    <!-- 전체 글 -->
                    <div class="my-3 p-3 bg-body rounded">
                        
			          <div class="col-lg-12 col-md-10 col-sm-12">
			          <div class="card text-center bg-light">
			            <h2 class="fw-bold text-primary pt-3">등록 완료!</h2>
			            <div class="card-body">
			              <p class="card-text">글작성이 완료되었습니다.</p>
			              <button type="button" id="btn-list" class="btn btn-outline-primary">
			                	글목록
			              </button>
			            </div>
			          </div>
			        </div>
                        
                        
                    </div>
                    <!-- 전체 글 end-->
                </div>
            </div>
            <!-- 나만의 여로경로 컨텐츠 END  -->

            <!-- 모달공간 start-->
            <%@ include file="/user/modal.jsp" %>
            <!-- 모달공간 end-->
        </div>
        <%@ include file="/include/footer.jsp" %>
	    <script>
	      document.querySelector("#btn-list").addEventListener("click", function () {
		        location.href = "${root}/noticeArticle?action=list";
		      });
	    </script>
    </body>
</html>
