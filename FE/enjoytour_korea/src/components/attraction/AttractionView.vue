<template>
  <div class="wrapper">
    <!-- 관광지 정보 컨텐츠 START  -->
    <div class="container">
      <div
        class="row bg-image p-1 me-1 ms-1 shadow-1-strong rounded content-title"
        style="
          /* background-image: url('${root}/assets/img/korea1.jpg'); */
          background-size: cover;
          height: 105px;
        "
      >
        <div class="col-12 text-light">
          <h3 class="fw-bold pt-3">관광지 정보</h3>
          <span class="content-desc">어떤 지역에 어떤 관광지가 있는지 찾아보세요</span>
        </div>
      </div>

      <div class="col">
        <!-- 관광지 검색 start -->
        <form class="d-flex justify-content-around m-3" role="search" style="height: 3rem">
          <select
            id="search-area"
            class="form-select m-1"
            onchange="areaOnclickHandler(this)"
            aria-label="Default select example"
          >
            <option value="0" selected>지역 선택</option>
          </select>
          <select
            id="search-region"
            class="form-select m-1"
            onchange="regionOnclickHandler(this, '${root}')"
            aria-label="Default select example"
          >
            <option value="0" selected>전체</option>
          </select>
          <select
            id="search-content-id"
            class="form-select m-1"
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
        </form>
      </div>
      <!-- map start -->
      <div class="row d-flex justify-content-center m-1">
        <div id="map" style="width: 100%; height: 40rem"></div>
      </div>
      <!-- map end -->
    </div>
  </div>
</template>

<script>
export default {
  name: "AttractionView",
  components: {},
  data() {
    return {
      //kakao
      map: null,
      typeIds: [12, 14, 15, 25, 28, 32, 38, 39], // 관광지 타입을 만드는 배열
      markers: [], // 마커를 담는 배열
      infoWindows: [], // infoWindow를 담는 배열
      clickLine: {},

      // trip
      regionName: null,
      regionMap: {},
      areaCode : null
    };
  },
  created() {},
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = "//dapi.kakao.com/v2/maps/sdk.js?appkey=" +
        process.env.VUE_APP_KAKAO_MAP_API_KEY +
        "&autoload=false";
      document.head.appendChild(script);

      for (let i = 0; i <= 40; i++) this.markers[i] = [];
      for (let i = 0; i <= 40; i++) this.infoWindows[i] = [];

      // this.clickLine = new kakao.maps.Polyline({
      //   map: map, // 선을 표시할 지도입니다
      //   path: [], // 선을 구성하는 좌표 배열입니다 클릭한 위치를 넣어줍니다
      //   strokeWeight: 3, // 선의 두께입니다
      //   strokeColor: "#FF4444", // 선의 색깔입니다
      //   strokeOpacity: 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
      //   strokeStyle: "solid", // 선의 스타일입니다
      // });
      // regionMap = {
      //   1: "서울",
      //   2: "인천",
      //   3: "대전",
      //   4: "대구",
      //   5: "광주",
      //   6: "부산",
      //   7: "울산",
      //   8: "세종특별자치시",
      //   31: "경기도",
      //   32: "강원도",
      //   33: "충청북도",
      //   34: "충청남도",
      //   35: "경상북도",
      //   36: "경상남도",
      //   37: "전라북도",
      //   38: "전라남도",
      //   39: "제주도",
      // };
    }
  },
  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.5013068, 127.0396597),
        level: 5,
      };
      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
      this.map.relayout();

      // makeOption(); -> search-area 생성
    },

    // makeOption(data) {
    //   // 각 지역에 대한 배열
    //   let areas = data.response.body.items.item;
    //   // TODO: regionUrl to db
    //   let sel = document.getElementById("search-area");
    //   areas.forEach(function (area) {
    //     let opt = document.createElement("option"); // option 엘리먼트 생성
    //     opt.setAttribute("value", area.code);
    //     opt.appendChild(document.createTextNode(area.name));
    //     sel.appendChild(opt);
    //   });
    // },

    // areaOnclickHandler(data) {
    //   this.areaCode = data.value;
    //   // let regionUrl = `https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=${serviceKey}&numOfRows=40&pageNo=1&MobileOS=ETC&MobileApp=AppTest&areaCode=${areaCode}&_type=json`;

    //   // fetch(regionUrl)
    //   //   .then((response) => response.json())
    //   //   .then((data) => makeRegionKeyword(data));

    //   // TODO: regionUrl to db
    //   makeRegionKeyword(data)
    // },

    // makeRegionKeyword(data) {
    //   let regions = data.response.body.items.item;
    //   let sel = document.getElementById("search-region");
    //   let query = `<option value ="0" selected> 전체 </option>`;
    //   //    console.log(regions);
    //   regions.forEach(function (region) {
    //     query += `<option value=${region.name}> ${region.name}</option>`;
    //   });
    //   sel.innerHTML = query;
    // },

    // regionOnclickHandler(e, root) {
    //   regionName = e.value;
    //   // v3가 모두 로드된 후, 이 콜백 함수가 실행됩니다.
    //   var geocoder = new kakao.maps.services.Geocoder();
    //   geocoder.addressSearch(`${regionMap[areaCode]} ${regionName}`, function (result, status) {
    //     // 정상적으로 검색이 완료됐으면
    //     if (status === kakao.maps.services.Status.OK) {
    //       var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
    //       // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
    //       map.setCenter(coords);
    //       let radius = 5000;
    //       let tripInfoUrl = `${root}/attraction/searchByLoc?mapX=${result[0].x}&mapY=${result[0].y}&radius=${radius}`;
    //       fetch(tripInfoUrl)
    //         .then((response) => response.json())
    //         .then((data) => makeMarker(data));
    //     }
    //   });
    // },

    // makeMarker(data) {
    //   let items = data;
    //   if (!items) return;
    //   let pos = [];
    //   for (let i = 0; i <= 40; i++) pos[i] = [];

    //   items.forEach(function (item) {
    //     let idx = item.contenttypeid;
    //     let markerImageSrc = `/assets/img/icon_${idx}.png`;
    //     let imageSize = new kakao.maps.Size(30, 30),
    //       imageOptions = {
    //         offset: new kakao.maps.Point(25, 20),
    //       };
    //     let markerImage = createMarkerImage(markerImageSrc, imageSize, imageOptions);
    //     var marker = createMarker(new kakao.maps.LatLng(item.mapy, item.mapx), markerImage);

    //     //	https://velog.io/@seochanh/newbie-00010
    //     let content =
    //       `<div class="wrap" id="${idx}_${markers[idx].length}">` +
    //       `    <div class="info">` +
    //       `        <div class="title">` +
    //       `            ${item.title}` +
    //       `            <div class="close" onclick ="closeInfo(${idx},${markers[idx].length})" id="close" title="닫기"></div>` +
    //       `        </div>` +
    //       `        <div class="body">` +
    //       `            <div class="img">` +
    //       `            <img src="${item.firstimage}" width ="73" height="70" onerror="this.src='/assets/img/ssafy_logo.png'">` +
    //       `           </div>` +
    //       `            <div class="desc">` +
    //       `                <div class="ellipsis">${item.addr1}</div>` +
    //       `                <div><a href="#" onclick ="addTrack2(${item.contentid}, \`${item.title}\`, \`${item.firstimage}\`, ${item.mapx}, ${item.mapy})" id="track2">여행경로 추가하기</div>` +
    //       `            </div>` +
    //       `        </div>` +
    //       `    </div>` +
    //       `</div>`;
    //     let infowindow = new kakao.maps.InfoWindow({
    //       content: content,
    //     });
    //     // let infoOverlay = new kakao.maps.CustomOverlay({
    //     //     content: content,
    //     //     position: marker.getPosition(),
    //     //     yAnchor: 1,
    //     //     zIndex: 2
    //     // });

    //     kakao.maps.event.addListener(marker, "click", function () {
    //       // 마커 위에 인포윈도우를 표시합니다
    //       infowindow.open(map, marker);
    //       // infoOverlay.setMap(map);
    //     });
    //     marker.setMap(map);
    //     markers[idx].push(marker);
    //     infoWindows[idx].push(infowindow);
    //   });
    // },


    // closeInfo(first, second) {
    // infoWindows[first][second].setMap(null);
    // },

    // contentidOnclickHandler(e) {
    //   let typeId = e.value;
    //   if (typeId == 0) {
    //     for (let i of typeIds) {
    //       for (let j = 0; j < markers[i].length; j++) {
    //         markers[i][j].setMap(map);
    //       }
    //     }
    //     return;
    //   }
    //   for (let i of typeIds) {
    //     if (i == typeId) {
    //       for (let j = 0; j < markers[i].length; j++) {
    //         markers[i][j].setMap(map);
    //       }
    //     } else
    //       for (let j = 0; j < markers[i].length; j++) {
    //         markers[i][j].setMap(null);
    //       }
    //   }
    // },


    // makeTripList(data) {
    // let trips = data.response.body.items.item;
    // let tripContent = ``;
    // trips.forEach(function (trip) {
    //     tripContent += `<tr>
    //     <td><img src ="${trip.firstimage}" width="100px"></td>
    //     <td>${trip.title}</td>
    // <td>${trip.addr1}${trip.addr2}</td>
    // <td>${trip.mapy}</td>
    // <td>${trip.mapx}</td>
    // </tr>`;
    // });
    // document.getElementById("trip-list").innerHTML = tripContent;
    // },

    // createMarkerImage(src, size, options) {
    // var markerImage = new kakao.maps.MarkerImage(src, size, options);
    // return markerImage;
    // },

    
    // // 좌표와 마커이미지를 받아 마커를 생성하여 리턴하는 함수
    // createMarker(position, image) {
    //   var marker = new kakao.maps.Marker({
    //     position: position,
    //     image: image,
    //   });
    //   return marker;
    // },
    
    // searchByKeyword() {
    //   let keyword = document.getElementById("search-keyword").value;
    //   console.log(keyword);
    //   regionName = e.value;
    //   // v3가 모두 로드된 후, 이 콜백 함수가 실행됩니다.
    //   var geocoder = new kakao.maps.services.Geocoder();
    //   geocoder.addressSearch(`${regionName}`, function (result, status) {
    //     // 정상적으로 검색이 완료됐으면
    //     if (status === kakao.maps.services.Status.OK) {
    //       var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
    //       // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
    //       map.setCenter(coords);
    //       let radius = 5000;
    //       let tripInfoUrl = `https://apis.data.go.kr/B551011/KorService1/locationBasedList1?serviceKey=${serviceKey}&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=${result[0].x}&mapY=${result[0].y}&radius=${radius}`;
    //       fetch(tripInfoUrl)
    //         .then((response) => response.json())
    //         .then((data) => makeMarker(data));
    //     }
    //   });
    // },

    // checkEnter(event) {
    //   if (event.keyCode == 13) searchByKeyword();
    // },

    //addTrack2 , clearPath, changePath(...)
  },
};
</script>

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
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png");
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
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}

.info .registerHotPlace {
  color: #5085bb;
}

.info .addTrack {
  color: #5085bb;
}
</style>
