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
                        background-image: url('${root}/assets/img/bg1.jpg');
                        background-size: cover;
                        height: 105px;
                    "
                >
                    <div class="col-12 text-light">
                        <h3 class="fw-bold pt-3">여행정보공유 글작성</h3>
                        <span class="content-desc">EnjoyTrip의 새로운 여행 정보를 전해줄게요</span>
                    </div>
                    
                </div>
                    <!-- 전체 글 -->
                    <div class="my-3 p-3 bg-body rounded">
                        
			          <div class="col-lg-12 col-md-10 col-sm-12">
				          <form id="form-modify" method="POST" action="">
				            <div class="mb-3">
				              <label for="subject" class="form-label">제목 : </label>
				              <input type="text" class="form-control" id="subject" name="subject" value="${article.subject}" />
				            </div>
				            <div class="mb-3">
				              <label for="content" class="form-label">내용 : </label>
				              <textarea class="form-control" id="content" name="content" rows="7">${article.content}</textarea>
				            </div>
				            <div class="col-auto text-center">
				              <button type="button" id="btn-modify" class="btn btn-outline-primary mb-3">
				                글수정
				              </button>
				              <button type="button" id="btn-list" class="btn btn-outline-danger mb-3">
				                글목록
				              </button>
				            </div>
				          </form>
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
		        location.href = "${root}/article?action=list";
		      });
	      document.querySelector("#btn-modify").addEventListener("click", function () {
	          if (!document.querySelector("#subject").value) {
	            alert("제목을 입력해주세요");
	            return;
	          } else if (!document.querySelector("#content").value) {
	            alert("내용을 입력해주세요");
	            return;
	          } else {
	            let form = document.querySelector("#form-modify");
	            form.setAttribute("action", "${root}/article?action=modify&articleno=${article.articleNo}");
	            form.submit();
	          }
	        });
	    </script>
    </body>
</html>
