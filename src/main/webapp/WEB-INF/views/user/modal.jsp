<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" /> 

<!-- 모달공간 login-modal start-->
<div id="login-modal-space">
<div
    class="modal fade"
    id="login-modal"
    data-bs-backdrop="static"
    data-bs-keyboard="false"
    tabindex="-1"
    aria-labelledby="staticBackdropLabel"
    aria-hidden="true"
>
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">
                    <i class="bi bi-chat-left-dots-fill text-info text-dark">로그인</i>
                </h3>
                <button
                    type="button"
                    class="btn-close"
                    data-bs-dismiss="modal"
                ></button>
            </div>

            <div class="modal-body">
                <form id="form-login" method="POST" action="">
				<input type="hidden" name="action" value="login" />
                    <div class="mb-3">
                        <label for="id-input" class="form-label">아이디:</label>
                        <input
                            type="text"
                            class="form-control"
                            id="login_id"
                            placeholder="아이디 입력..."
                            name="login_id"
                            value="${saveid}"
                       />
                   </div>
                   <div class="mb-3">
                       <label for="pw-input" class="form-label">비밀번호:</label>
                       <input
                           type="password"
                           class="form-control"
                           id="login_pwd"
                           placeholder="비밀번호 입력..."
                           name="login_pwd"
                       />
                   </div>
                   <div class="mb-3">
                       <div class="col d-flex justify-content-center">
                           <!-- Checkbox -->
                            <div class="form-check p-3">
                                <input
                                    class="form-check-input"
                                    type="checkbox"
                                    value="ok"
                                    id="saveid"
                                    name = "saveid"
                                    checked
                                />
                                <label class="form-check-label" for="id-save">
                                    아이디 저장
                                </label>
                            </div>
                            <a class="p-3" href="#!"
                         type="button"
                         data-bs-toggle="modal"
                         data-bs-target="#find-pwd-modal"
                            >비밀번호 찾기</a>
                        </div>
                    </div>
                </form>
            </div>

            <div class="modal-footer">
                <button
                    type="button"
                    id="btn-login"
                    class="btn btn-primary btn-sm"
                >
                    로그인 하기
                </button>
                <button
                    type="button"
                    class="btn btn-outline-danger btn-sm"
                    data-bs-dismiss="modal"
                >
                    Close
                </button>
            </div>
        </div>
    </div>
</div>
</div>
<!-- 모달공간 login-modal end-->

<!-- 모달공간 register-modal start-->
<div id="register-modal-space">
<div
    class="modal fade"
    id="register-modal"
    data-bs-backdrop="static"
    data-bs-keyboard="false"
    tabindex="-1"
    aria-labelledby="staticBackdropLabel"
    aria-hidden="true"
>
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">
                    <i class="bi bi-chat-left-dots-fill text-info text-dark">회원가입</i>
                </h3>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>

            <div class="modal-body">
                <form id="form-join" method="POST" action="">
    					<input type="hidden" name="action" value="join" />
                    <div class="mb-3">
                        <label for="id-register-input" class="form-label">아이디:</label>
                        <input
                            type="text"
                            class="form-control"
                            id="join_id"
                            placeholder="아이디 입력..."
                            name="join_id"
                        />
                    </div>
                    <div id="idcheck-result"></div>
                    <div class="mb-3">
                        <label for="pw-register-input" class="form-label">비밀번호:</label>
                        <input
                            type="password"
                            class="form-control"
                            id="join_pwd"
                            placeholder="비밀번호 입력..."
                            name="join_pwd"
                        />
                    </div>
                    <div class="mb-3">
                        <label for="name-register-input" class="form-label">이름:</label>
                        <input
                            type="text"
                            class="form-control"
                            id="join_name"
                            placeholder="이름 입력..."
                            name="join_name"
                        />
                    </div>
                    <div class="mb-3">
                        <label for="email-register-input" class="form-label">이메일:</label>
                        <input
                            type="email"
                            class="form-control"
                            id="join_email"
                            placeholder="이메일 입력..."
                            name="join_email"
                        />
                    </div>
                    <div class="mb-3">
                        <label for="age-register-input" class="form-label">나이:</label>
                        <input
                            type="number"
                            class="form-control"
                            id="join_age"
                            placeholder="나이 입력..."
                            name="join_age"
                        />
                    </div>
                </form>
            </div>

            <div class="modal-footer">
                <button
                    type="button"
                    id="btn-register"
                    class="btn btn-primary btn-sm"
                >
                    회원가입
                </button>
            </div>
        </div>
    </div>
</div>
</div>
<!-- 모달공간 register-modal end-->

<!-- 모달공간 mypage-modal start-->
<div id="mypage-modal-space">
<div class="modal fade"
    id="mypage-modal"
    data-bs-backdrop="static"
    data-bs-keyboard="false"
    tabindex="-1"
    aria-labelledby="staticBackdropLabel"
    aria-hidden="true"
>
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">
                    <i class="bi bi-chat-left-dots-fill text-info text-dark">마이페이지</i>
                </h3>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>

            <div class="modal-body">
              	<form id="form-update" method="POST" action="">
                    <div class="mb-3">
                        아이디:
                        <span class="float-end" id="mypage-id">${userinfo.userId}</span>
          </div>
          <div class="mb-3">
              이름:
              <span class="float-end" id="mypage-name">${userinfo.userName}</span>
          </div>
          <div class="mb-3">
              나이:
              <span class="float-end" id="mypage-age">${userinfo.age}</span>
          </div>
          <div class="mb-3">
              이메일:
              <span class="float-end" id="mypage-email">${userinfo.email}</span>
                    </div>
                    <div class="container border-secondary h3 border"></div>
                    <div class="mb-3">
                        <label for="pw-mypage-input" class="form-label"
                            >비밀번호 변경하기:</label
                        >
                        <input
                            type="password"
                            class="form-control"
                            id="mypage_pwd"
                            placeholder="비밀번호 입력..."
                            name="mypage_pwd"
                        />
                    </div>
                    <div class="mb-3">
                        <label for="email-mypage-input" class="form-label"
                            >이메일 변경하기:</label
                        >
                        <input
                            type="email"
                            class="form-control"
                            id="mypage_email"
                            placeholder="이메일 입력..."
                            name="mypage_email"
                        />
                    </div>
                </form>
            </div>

            <div class="modal-footer">
                <button
                    type="button"
                    id="btn-updateUser"
                    class="btn btn-primary btn-sm"
                >
                    수정하기
                </button>
                <button
                    type="button"
                    id="btn-deleteUser"
                    class="btn btn-primary btn-sm btn-danger"
                >
                    탈퇴하기
                </button>
            </div>
        </div>
    </div>
</div>
</div>
<!-- 모달공간 mypage-modal end-->
<!-- 모달공간 find-pwd-modal start-->
<div id="find-pwd-modal-space">
`<div
    class="modal fade"
    id="find-pwd-modal"
    data-bs-backdrop="static"
    data-bs-keyboard="false"
    tabindex="-1"
    aria-labelledby="staticBackdropLabel"
    aria-hidden="true"
>
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">
                    <i class="bi bi-chat-left-dots-fill text-info text-dark">비밀번호찾기</i>
                </h3>
                <button
                    type="button"
                    class="btn-close"
                    data-bs-dismiss="modal"
                ></button>
            </div>

            <div class="modal-body">
                <form action="">
                    <div class="mb-3">
                        <label for="id-input" class="form-label">아이디:</label>
                        <input
                            type="text"
                            class="form-control"
                            id="find_id"
                            placeholder="아이디 입력..."
                            name="find_id"
                        />
                    </div>
                    <div class="mb-3">
                        <label for="email-input" class="form-label">이메일:</label>
                        <input
                            type="email"
                            class="form-control"
                            id="find_email"
                            placeholder="이메일 입력..."
                            name="find_email"
                        />
                    </div>
                    <div id="pwdfind-result"></div>
                </form>
            </div>

            <div class="modal-footer">
                <button
                    type="button"
                    id="btn-find"
                    class="btn btn-primary btn-sm"
                >
                    비밀번호 찾기
                </button>
                <button
                    type="button"
                    id="btn-find-close"
                    class="btn btn-outline-danger btn-sm"
                    data-bs-dismiss="modal"
                >
                    Close
                </button>
            </div>
        </div>
    </div>
</div>`
</div>
<!-- 모달공간 search-pw-modal end-->

<script>
            // login
            document.querySelector("#btn-login").addEventListener("click", function () {
                if (!document.querySelector("#login_id").value) {
                  alert("아이디 입력해주세요");
                  return;
                } else if (!document.querySelector("#login_pwd").value) {
                  alert("비밀번호 입력해주세요");
                  return;
                } else {
                  let form = document.querySelector("#form-login");
                  form.setAttribute("action", "${root}/user");
                  form.submit();
                }
              });
            
         // find pwd
		    document.querySelector("#btn-find").addEventListener("click", function () {
				let userid = document.querySelector("#find_id").value;
				let usereamil = document.querySelector("#find_email").value;
				
		  	 	let resultDiv = document.querySelector("#pwdfind-result");
	  		 	fetch("${root}/user?action=pwdfind&userid=" + userid +"&email=" + usereamil)
		   		.then(response => response.text())
		   		.then(data => {
			 		if(data.length == 0) {
			   			resultDiv.setAttribute("class", "mb-3 text-danger");
		       			resultDiv.textContent = "해당 사용자의 비밀번호는 찾을 수 없습니다.";
			 		} else {
			   			resultDiv.setAttribute("class", "mb-3 text-primary");
	 		       		resultDiv.textContent = "해당 사용자의 비밀번호는 " + data + "입니다.";
			 		}
	  		   });
		    });
         
		 // find modal data Init
            document.querySelector("#btn-find-close").addEventListener("click", function () {
                location.href = "${root}/index.jsp";
              });
		    
            // register
		    let isUseId = false;
		    document.querySelector("#join_id").addEventListener("keyup", function () {
				let userid = this.value;
				
		  	 	let resultDiv = document.querySelector("#idcheck-result");
		  	 	if(userid.length < 3 || userid.length > 16) {
		  		 	resultDiv.setAttribute("class", "mb-3 text-dark");
		  		 	resultDiv.textContent = "아이디는 4자 이상 16자 이하 입니다.";
		  		 	isUseId = false;
		  	 	} else {
		  		 	fetch("${root}/user?action=idcheck&userid=" + userid)
			   		.then(response => response.text())
			   		.then(data => {
				 		if(data == 0) {
				   			resultDiv.setAttribute("class", "mb-3 text-primary");
			       			resultDiv.textContent = userid + "는 사용할 수 있습니다.";
			       			isUseId = true;
				 		} else {
				   			resultDiv.setAttribute("class", "mb-3 text-danger");
		 		       		resultDiv.textContent = userid + "는 사용할 수 없습니다.";
		 		     		isUseId = false;
				 		}
		  		   });
		  	 	}
		    });
		    
			document.querySelector("#btn-register").addEventListener("click", function () {
				if (!document.querySelector("#join_name").value) {
		          alert("이름 입력해주세요");
		          return;
		        } else if (!document.querySelector("#join_id").value) {
		          alert("아이디 입력해주세요");
		          return;
		        } else if (!document.querySelector("#join_pwd").value) {
		          alert("비밀번호 입력해주세요");
		          return;
		        } else {
		          let form = document.querySelector("#form-join");
		          form.setAttribute("action", "${root}/user");
		          form.submit();
		        }
			});
			
			//update user
			document.querySelector("#btn-updateUser").addEventListener("click", function () {
				if (!document.querySelector("#mypage_pwd").value && !document.querySelector("#mypage_email").value) {
		          alert("변경할 정보를 입력해주세요");
		          return;
		        } else {
		          let form = document.querySelector("#form-update");
		          form.setAttribute("action", "${root}/user?action=update");
		          form.submit();
		        }
			});
			
			//delete user
			document.querySelector("#btn-deleteUser").addEventListener("click", function () {
				if(confirm("탈퇴하시겠습니까?")) {
					location.href = "${root}/user?action=delete";
				}
			});
 </script>