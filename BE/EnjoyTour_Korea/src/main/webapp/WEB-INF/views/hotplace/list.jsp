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
                    <button
                        type="button"
                        id="btn-place-write"
                        class="ms-2 btn btn-light btn-sm btn-light">
                        공유하기
                    </button>
                    </div>

                </div>

                <div class="row p-1">
                    <div class="album">
                        <div class="container p-0 py-2">
                            <div
                                class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3"
                            >
                            
                            
                            <div class="col">
                              <div class="card shadow-sm">
                                  <img
                                      class="bd-placeholder-img card-img-top"
                                      src="${root}/assets/img/korea1.jpg"
                                      role="img"
                                      aria-label="Placeholder: Thumbnail"
                                      preserveAspectRatio="xMidYMid slice"
                                      focusable="false"
                                  />
                                    
                                  <div class="card-body">
                                      <p class="card-text">
                                          밤에 오면  ...
                                      </p>
                                      <div
                                          class="d-flex justify-content-between align-items-center"
                                      >
                                          <div class="btn-group">
                                              <button
                                                  type="button"
                                                  class="btn btn-sm btn-outline-secondary"
                                              >
                                                  View
                                              </button>
                                             
                                          </div>
                                          <small class="text-muted"
                                              >9 mins</small
                                          >
                                      </div>
                                  </div>
                              </div>
                          </div>


                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 나만의 여로경로 컨텐츠 END  -->

            <!-- 모달공간 start-->
            <%@ include file="/user/modal.jsp" %>
            <!-- 모달공간 end-->
        </div>
        <script>
        document.querySelector("#btn-place-write").addEventListener("click", function () {
            location.href = "${root}/hotplace/write.jsp";
          });
        </script>
        <%@ include file="/include/footer.jsp" %>

    </body>
</html>
