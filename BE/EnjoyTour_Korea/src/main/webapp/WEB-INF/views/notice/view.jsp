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
			          <div class="row my-2">
			            <h4 class="text-secondary fw-bold">${article.articleNo}. ${article.subject}</h4>
			          </div>
			          <div class="row">
			            <div class="col-md-8">
			              <div class="clearfix align-content-center">
			                <img
			                  class="avatar me-2 float-md-start bg-light p-2"
			                  src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg"
			                />
			                <p>
			                  <span class="fw-bold">${article.userId}</span> <br />
			                  <span class="text-secondary fw-light"> ${article.registerTime} 조회 : ${article.hit} </span>
			                </p>
			              </div>
			            </div>
			            <div class="divider mb-3"></div>
			            <div class="text-secondary">
			              ${article.content}
			            </div>
			            <div class="divider mt-3 mb-3"></div>
			            <div class="d-flex justify-content-end">
			              <button type="button" id="btn-list" class="btn btn-outline-primary mb-3">
			                글목록
			              </button>
			              <c:if test="${userinfo.userId eq '관리자'}">
			              <button type="button" id="btn-mv-modify" class="btn btn-outline-success mb-3 ms-1">
			                글수정
			              </button>
			              <button type="button" id="btn-delete" class="btn btn-outline-danger mb-3 ms-1">
			                글삭제
			              </button>
			              </c:if>
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
	      document.querySelector("#btn-mv-modify").addEventListener("click", function () {
	        location.href = "${root}/noticeArticle?action=mvmodify&articleno=${article.articleNo}";
	      });
	      document.querySelector("#btn-delete").addEventListener("click", function () {
	        location.href = "${root}/noticeArticle?action=delete&articleno=${article.articleNo}";
	      });
	    </script>
    </body>
</html>
