# EnjoyTrip_springboot_김정훈_손효민

**생성 일시**: May 1, 2023 2:22 PM

**생성자**: 김정훈

**최종 편집 일시**: May 2, 2023 11:57 PM

**최종 편집자**: 손효민

**태그**: 백엔드

## ✅ 사용한 기술 스택

**개발 언어** : Java, MySQL

**프로그램** : STS, Apache Tomcat

**라이브러리 / 오픈 소스** : Spring Boot, myBatis Framework, Swagger

## 1. EnjoyTrip 개선된 요구사항 목록

- 패키지 명 - package : com.ssafy.enjoytrip
- 여행 계획 권한 부여 외에도 친구(follow) 추가 기능을 고려중이다
- **이외에 요구사항은 전과 동일하다**
    
    ![Untitled](https://user-images.githubusercontent.com/68097374/235706485-a5707628-761b-42fb-916b-39ebda1f4c26.png)
    

## 2. EnjoyTrip Spring & Mybatis Framework로 변경한 Class-Diagram

<img width="881" alt="Untitled 1" src="https://user-images.githubusercontent.com/68097374/235706490-45077e85-a166-4310-a1b6-5ba8852548f7.png">

- `notice`

```java
▶ NoticeController.java 공지사항 (com.ssafy.notice.controller)
	 └─ RestAPI 활용
			└─ @Controller
				 @RequestMapping("/notice")
				public class NoticeController {
						...
						@ResponseBody
						@PostMapping(value = "/")
						public ResponseEntity<?> noticeWrite(@RequestBody NoticeDto noticeDto){...}

						@ResponseBody
						@GetMapping(value = "/")
						public ResponseEntity<?> noticeList(@RequestParam Map<String, String> map) {...}
				
						@GetMapping("/view")
						public String view(@RequestParam("noticeno") int noticeNo, @RequestParam Map<String, String> map, Model model)
						throws Exception {...}

						@ResponseBody
						@PutMapping(value = "/")
						public ResponseEntity<?> noticeModify(@RequestBody NoticeDto noticeDto, @RequestParam Map<String, String> map) {...}

						@ResponseBody
						@DeleteMapping(value = "/{noticeno}")
						public ResponseEntity<?> noticeDelete(@PathVariable("noticeno") int noticeNo, @RequestParam Map<String, String> map) {...}
						...
						}

▶ NoticeService.java (com.ssafy.notice.model.service)

▶ NoticeMapper.java (com.ssafy.notice.model.mapper)
		└─ notice.xml (src.main.resources.mapper)
				└─ myBatis 활용
▶ NoticeDto.java (com.ssafy.notice.model)
```

- `attraction`

```java
▶ AttractionController.java 관광지 정보 (com.ssafy.attraction.controller)
	 └─ RestAPI 활용
			└─ @Controller
				 @RequestMapping("/attraction")
				 public class AttractionController extends HttpServlet {
						...
						@GetMapping("/view")
						public String view(Model model) throws Exception {...}

						@ResponseBody
						@GetMapping("/searchByLoc")
						public ResponseEntity<?> searchByLoc(@RequestParam Float mapX, @RequestParam Float mapY,
																								 @RequestParam Float radius, Model model) {...}
						...
						}

▶ AttractionService.java (com.ssafy.attraction.model.service)

▶ AttractionMapper.java (com.ssafy.attraction.model.mapper)
		└─ attraction.xml (src.main.resources.mapper)
				└─ myBatis 활용
▶ AttractionDto.java (com.ssafy.attraction.model)
```

- `member`

```java
▶ MemberController.java 관광지 정보 (com.ssafy.member.controller)
	 └─ @ResponseBody 활용
			└─ @Controller
				 @RequestMapping("/user")
				 public class MemberController {
						...
						@GetMapping("/{userid}")
						@ResponseBody
						public int idCheck(@PathVariable("userid") String userId) throws Exception {...}

						@GetMapping("/pwdfind")
						@ResponseBody
						public String pwdfind(@RequestParam(name = "userid") String userId, @RequestParam(name = "email") String email)
						throws Exception {...}

						@PostMapping("/join")
						public String join(MemberDto memberDto) throws Exception{...}
						
						@PostMapping("/login")
						public String login(@RequestParam(name = "login_id") String userId, @RequestParam(name = "login_pwd") String userPwd,
																@RequestParam(name = "saveid", required = false) String idsave,
																HttpServletRequest request, HttpServletResponse response) throws Exception{...}
						
						@GetMapping("/logout")
						public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception{...}
						
						@PostMapping("/update")
						public String update(@RequestParam(name = "mypage_pwd") String pwd, @RequestParam(name = "mypage_email") String email,
																 HttpServletRequest request, HttpServletResponse response) throws Exception{...}
						
						@GetMapping("/delete")
						public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception{...}
						...
						}

▶ MemberService.java (com.ssafy.member.model.service)

▶ MemberMapper.java (com.ssafy.member.model.mapper)
		└─ member.xml (src.main.resources.mapper)
				└─ myBatis 활용
▶ MemberDto.java (com.ssafy.member.model)
```

## 3. EnjoyTrip 실행화면

### 1) 메인페이지 및 메뉴구성

- **네비게이션 바** - 홈, 공지사항, 관광지 정보, 여행정보 공유, 나만의 여행경로, HotPlace
- **로그인 / 회원가입**
    
    ![Untitled 2](https://user-images.githubusercontent.com/68097374/235706493-ccd2a062-216c-4373-bcbe-d552bc62284f.png)
    

### 2) 관광지 정보 조회

- **지역별 관광지 정보 수집**
    - 지역을 선택하면 지역 별 관광지 정보를 관광지 유형에 따라 검색해 지역별 관광지 정보를 지도에 나타낼 수 있다
        
        ![Untitled 3](https://user-images.githubusercontent.com/68097374/235706496-d6ef810d-9b53-49e2-9886-63206c90df20.png)
        
        ![Untitled 4](https://user-images.githubusercontent.com/68097374/235706498-3d90c551-b9d2-4748-9d47-1bcacdf1b956.png)
        
    - 시도 명과 구군 명이 정해지면 지도의 중심이 옮겨지고 관광지들이 지도에 표시된다
        
        ![Untitled 5](https://user-images.githubusercontent.com/68097374/235706500-098d67d2-41eb-4c24-8ad5-25b0aceca961.png)
        
- **관광지, 숙박, 음식점, 문화시설, 공연, 여행코스, 쇼핑 조회**
    - 지도에서 관광지, 숙박, 음식점, 문화시설, 공연, 여행코스, 쇼핑 정보가 지도에 나타난다
        
        ![Untitled 6](https://user-images.githubusercontent.com/68097374/235706501-437de12c-ef17-440d-bc46-26ef65d4208d.png)
        
    - 관광지 유형을 선택하면 원하는 관광지 유형에 해당하는 관광지만 표시된다
        
        ![Untitled 7](https://user-images.githubusercontent.com/68097374/235706503-da4c3abe-99c6-45e8-a48d-b79848ae2457.png)
        

### 3) 회원관리 페이지 - 회원정보 등록, 수정, 삭제, 조회 화면

- **회원정보 등록 (회원가입)**
    - 로그인을 하지 않은 상태에서 회원가입 탭을 누르면 회원가입 modal창이 뜬다
    - 회원 정보를 입력하고 회원 가입 버튼을 누르면 회원가입이 된다
        
        ![Untitled 8](https://user-images.githubusercontent.com/68097374/235706431-1651587b-268d-4a54-8a47-4c73e0a8e91f.png)
        
    - 아이디를 입력하게 되면 DB의 정보와 비교하여 사용할 수 있는 ID인지 체크한다
        
        ![Untitled 9](https://user-images.githubusercontent.com/68097374/235706439-30676945-7304-4dbb-86df-32ae131edfa4.png)
        
    - 회원 가입 전 DB
        
        ![Untitled 10](https://user-images.githubusercontent.com/68097374/235706445-b621d889-9af8-4694-b5d2-900494c7a12d.png)
        
    - 회원 가입 후 DB
        
        ![Untitled 11](https://user-images.githubusercontent.com/68097374/235706448-0d9a4bde-ee22-4550-9bbe-0734770b4a80.png)
        
- **회원정보 수정**
    - 로그인 한 상태에서 마이페이지 탭을 누른다
    - 회원정보를 조회하는 창에서 변경할 비밀번호나 이메일을 입력하고 수정하기 버튼을 누른다
        
        ![Untitled 12](https://user-images.githubusercontent.com/68097374/235706449-28f7c90a-3363-4718-bbea-37b08e908958.png)
        
    - 수정 전 DB
        
        ![Untitled 11](https://user-images.githubusercontent.com/68097374/235706448-0d9a4bde-ee22-4550-9bbe-0734770b4a80.png)
        
    - 수정 후 DB
        
        ![Untitled 13](https://user-images.githubusercontent.com/68097374/235710562-3a1d44fd-2591-4dce-ac4d-b2ccbc2765a1.png)
        
- **회원 삭제(탈퇴)**
    - 로그인 한 상태에서 마이페이지 탭을 누른다
    - 회원정보를 조회하는 창에서 탈퇴 버튼을 누르면 회원 정보가 삭제된다
        
        ![Untitled 14](https://user-images.githubusercontent.com/68097374/235706457-84f6e191-febf-4456-8fab-ba534c9dd258.png)
        
    - 탈퇴 여부를 묻는 confirm창이 뜬고 확인 버튼을 누르면 회원 정보가 삭제된다
        
        ![Untitled 15](https://user-images.githubusercontent.com/68097374/235706461-5f3aa8a6-e4e8-4b35-81b4-1c6d24d6854b.png)
        
    - 회원 삭제 전 DB
        
        ![Untitled 13](https://user-images.githubusercontent.com/68097374/235710562-3a1d44fd-2591-4dce-ac4d-b2ccbc2765a1.png)
        
    - 회원 삭제 후 DB
        
        ![Untitled 16](https://user-images.githubusercontent.com/68097374/235706465-b102c5de-d654-4e13-8ae6-987aaa8f3c90.png)
        
- **회원정보 조회**
    - 로그인 한 상태에서 마이페이지 탭을 누른다
    - 회원 정보를 조회할 수 있는 modal 창이 열린다
        
        ![Untitled 17](https://user-images.githubusercontent.com/68097374/235706466-1d6b8dd9-9083-4ad2-8610-e305a6344440.png)
        

### 4) 로그인/로그아웃 페이지

- **로그인**
    - 로그인 탭을 누르면 로그인 modal 창이 뜬다
    - 회원가입 했던 사용자라면 아이디와 비밀번호를 입력하고 로그인 modal 창에서 로그인하기 버튼을 누른다
    
    ![Untitled 18](https://user-images.githubusercontent.com/68097374/235706468-53a0b78a-2aeb-44bb-adfe-24faa7f0febf.png)
    
- **로그아웃**
    - 로그인 한 상태에서 로그아웃 탭을 누르면 바로 로그아웃이 된다
    
    ![Untitled 19](https://user-images.githubusercontent.com/68097374/235706469-51a42160-8872-4b69-b4b5-8a00011adc18.png)
    

### 5) 공지사항 관리

- **URL** : http://localhost/swagger-ui/index.html
- **Models** : NoticeDto (공지사항 정보)
    
    ![Untitled 20](https://user-images.githubusercontent.com/68097374/235706476-0a974455-c475-45bd-ad33-9c64a2017f12.png)
    
- **Notice Controller (공지사항 관리)**
    
    
    | noticeList | GET | /notice/ |
    | --- | --- | --- |
    | noticeWrite | POST | /notice/ |
    | noticeModify | PUT | /notice/ |
    | noticeDelete | DELETE | /notice/{noticeno} |
    - **noticeList** : 공지사항의 **전체 목록**을 리턴합니다
        
        ```java
        [
          {
            "noticeNo": 2,
            "userId": "ssafy",
            "title": "공지사항1",
            "content": "공지사항1입니다.",
            "hit": 0,
            "registerTime": "2023-04-14 21:09:06"
          },
          {
            "noticeNo": 1,
            "userId": "ssafy",
            "title": "공지사항",
            "content": "공지사항입니다.",
            "hit": 0,
            "registerTime": "2023-04-14 20:42:24"
          }
        ]
        ```
        
    - **noticeWrite** :  공지사항을 **등록**합니다
        
        ```java
        //Edit Value | Model
        {
          "content": "스프링 프로젝트 입니다",
          "title": "스프링 프로젝트",
          "userId": "ssafy"
        }
        
        // ResponseBody
        [
          {
            "noticeNo": 3,
            "userId": "ssafy",
            "title": "스프링 프로젝트",
            "content": "스프링 프로젝트 입니다",
            "hit": 0,
            "registerTime": "2023-05-02 17:07:29"
          },
          {
            "noticeNo": 2,
            "userId": "ssafy",
            "title": "공지사항1",
            "content": "공지사항1입니다.",
            "hit": 0,
            "registerTime": "2023-04-14 21:09:06"
          },
          {
            "noticeNo": 1,
            "userId": "ssafy",
            "title": "공지사항",
            "content": "공지사항입니다.",
            "hit": 0,
            "registerTime": "2023-04-14 20:42:24"
          }
        ]
        ```
        
    - **noticeModify** : 공지사항의 **수정**합니다
        
        ```java
        //Edit Value | Model
        {
          "content": "공지사항 수정했습니다",
          "noticeNo": 1,
          "title": "공지사항 수정"
        }
        
        // ResponseBody
        [
          {
            "noticeNo": 3,
            "userId": "ssafy",
            "title": "스프링 프로젝트",
            "content": "스프링 프로젝트 입니다",
            "hit": 0,
            "registerTime": "2023-05-02 17:07:29"
          },
          {
            "noticeNo": 2,
            "userId": "ssafy",
            "title": "공지사항1",
            "content": "공지사항1입니다.",
            "hit": 0,
            "registerTime": "2023-04-14 21:09:06"
          },
          {
            "noticeNo": 1,
            "userId": "ssafy",
            "title": "공지사항 수정",
            "content": "공지사항 수정했습니다",
            "hit": 0,
            "registerTime": "2023-04-14 20:42:24"
          }
        ]
        ```
        
    - **noticeDelete** : 공지사항의 **삭제**합니다
        
        ```java
        //Edit Value | noticeno //삭제 번호
        3
        
        // ResponseBody
        [
          {
            "noticeNo": 2,
            "userId": "ssafy",
            "title": "공지사항1",
            "content": "공지사항1입니다.",
            "hit": 0,
            "registerTime": "2023-04-14 21:09:06"
          },
          {
            "noticeNo": 1,
            "userId": "ssafy",
            "title": "공지사항 수정",
            "content": "공지사항 수정했습니다",
            "hit": 0,
            "registerTime": "2023-04-14 20:42:24"
          }
        ]
        ```
        
    

### 6)  여행계획 관리

- **여행 계획 경로 설정**
    - 나만의 여행 경로 탭을 누르면 여행 계획을 관리하는 페이지로 이동한다
    - 시도 명과 지역 명을 누르면 관광지들이 지도에 표시된다
    - 원하는 관광지들을 선택하면 관광지 정보 창이 뜬다. 이때, 여행 경로 추가하기를 누르면 경로가 설정되고 왼쪽에 관광지들이 나열된다
    - 관광지 정보 창의 사진 액박 처리를 했다
    - **페이지 이동 controller 만 구현했고, 추후에 마무리 할 예정이다**
    
    <img width="909" alt="Untitled 21" src="https://user-images.githubusercontent.com/68097374/235706479-b9f3a32f-307c-4b46-a4ea-403b929d3128.png">
