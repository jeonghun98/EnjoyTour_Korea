# South Korea Tour (EnjoyTrip)

**개발자**: 김정훈, 손효민

**생성 일시**: May 1, 2023

**최종 편집 일시**: Jul 3, 2023
<br><br>

## ✅ 사용한 기술 스택

**개발 언어 / 프로그램** : Java, STS, Apache Tomcat, MySQL, VSCode

**라이브러리 / 오픈 소스** : Spring Boot, myBatis Framework, Vue.js, JavaScript, Bootstrap-vue

**사용 데이터** : 전국관광지 정보, 관광지 사진정보(한국관광공사_국문관광정보 서비스_GW)
<br><br>


## 1. South Korea Tour 요구사항

- 사용자에게 한국의 다양한 관광지, 먹거리, 축제, 행사 등을 소개하여 지역 관광 활성화를 위한 지역 관광 소개 페이지를 구축하려고 한다. 한국관광공사에서 제공하는 국문관광정보서비스_GW의 다양한 상세기능정보 API를 활용하여 지역별 관광지 데이터를 분석하고 화면에 표시한다. 또한, 여행계획을 위한 스케줄과 여행경로 공유 등 사용자 편의 기능을 구현하고 나만의 숨은 관광지를 소개하는 페이지와 공지사항 게시판을 구현한다.


## 2. South Korea Tour 실행화면

### 1) 메인페이지

1. 네비게이션 바로 한국 여행지, 여행계획 등의 다른 페이지로 넘어간다.
2. 소개글과 한국 여행지의 사진을 넣은 carousel이 있다. 이 곳의 버튼을 통해서도 다른 페이지로 넘어갈 수 있다.
3. 랜덤으로 3개의 여행지들의 사진과 명소의 이름을 나타내준다.
    
    ![image](https://github.com/jeonghun98/South_Korea_Tour/assets/52409864/d31f8dbf-6716-44f8-9be7-5d143153c506)
    

### 2) 한국 여행지
1. 시도, 구군 별로 공공 데이터 포털에서 가져온 관광지 정보를 지도에 나타낸다.
2. 관광지 유형별로 공공 데이터 포털에서 가져온 관광지 정보를 지도에 나타낸다.

   ![image](https://github.com/jeonghun98/South_Korea_Tour/assets/52409864/6a70a67b-68b5-42af-ac79-346285839ef3)

### 3) 회원 관리, 로그인 관리

1. 모달창을 이용해서 회원 가입을 한다. 회원 가입시 아이디 중복체크를 한다.
2. 로그인을 할 수 있다.
3. 비밀번호 찾기 버튼을 클릭한 후 아이디와 이메일 정보를 이용해 임시 비밀번호를 발급받을 수 있다.
   
   ![image](https://github.com/jeonghun98/South_Korea_Tour/assets/52409864/a8c115cc-3b6a-4e2e-8643-ee9e5031b8ca)
   ![image](https://github.com/jeonghun98/South_Korea_Tour/assets/52409864/4250c71f-e93c-4745-8331-3ee3f6357fff)
   ![image](https://github.com/jeonghun98/South_Korea_Tour/assets/52409864/d2419b61-d17e-4a25-b879-413bf59fec6c)
   ![image](https://github.com/jeonghun98/South_Korea_Tour/assets/52409864/684bde1a-1dc9-41b0-8b7f-8ae649715adf)


### 4) 공지사항
1. 관리자가 작성한 글을 볼 수 있다.
2. 관리자의 경우에만 글을 수정하거나 삭제할 수 있다.

   ![image](https://github.com/jeonghun98/South_Korea_Tour/assets/52409864/3ea97db5-f1ef-48b3-8246-c1dfd5215eb5)
   ![image](https://github.com/jeonghun98/South_Korea_Tour/assets/52409864/1a9ac2f0-d14e-4a24-9c18-9537534902ab)

    

### 5) 핫플 자랑하기
1. 사용자들이 작성한 핫플레이스 게시글들의 목록을 볼 수 있다.
2. 사진을 이용해 핫플레이스를 소개하는 게시글을 작성할 수 있다.
3. 사진의 위도, 경도 정보를 이용해 핫플레이스의 좌표를 지도에 자동으로 등록할 수 있다.
4. 사진의 정보를 이용해 사진 찍은 날짜를 자동으로 등록할 수 있다.

   ![image](https://github.com/jeonghun98/South_Korea_Tour/assets/52409864/0f8f9290-9f01-4b7f-9de2-013dfa7a0cc9)
   ![image](https://github.com/jeonghun98/South_Korea_Tour/assets/52409864/94e8816f-80b9-4a77-964b-b135f4f4fad5)



### 6) 여행 계획 관리
1. 모두의 여행계획 페이지에서 여행 계획을 public(공개)한 게시글들을 볼 수 있다.
2. 시도, 구군, 관광지 유형 별 관광지를 지도에서 여행 예정지로 선택할 수 있다.
3. 선택하는 동시에 여행 코스 부분에 실시간으로 추가된다.
4. 출발일과 도착일, 상세 내용을 작성할 수 있다.

   ![image](https://github.com/jeonghun98/South_Korea_Tour/assets/52409864/714f56c5-ef7b-4717-8ff5-5ba21d1a07ae)
   ![image](https://github.com/jeonghun98/South_Korea_Tour/assets/52409864/dac7f4f5-61a8-4af8-82d3-4fc5b12e308b)
   ![image](https://github.com/jeonghun98/South_Korea_Tour/assets/52409864/86d046e8-1bdd-4020-9068-7e07f0317cdf)
   ![image](https://github.com/jeonghun98/South_Korea_Tour/assets/52409864/4dce1b0b-db27-4d7f-83f0-5d79cec802f8)
