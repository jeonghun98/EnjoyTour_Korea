// var mapContainer = document.getElementById("map"), // 지도를 표시할 div
//   mapOption = {
//     center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
//     level: 5, // 지도의 확대 레벨
//   };

// // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
// var map = new kakao.maps.Map(mapContainer, mapOption);
// var typeIds = [12, 14, 15, 25, 28, 32, 38, 39]; // 관광지 타입을 만드는 배열
// var markers = []; // 마커를 담는 배열
// for (let i = 0; i <= 40; i++) {
//   markers[i] = [];
// }

// var infoWindows = []; // infoWindow를 담는 배열
// for (let i = 0; i <= 40; i++) {
//   infoWindows[i] = [];
// }

// let clickLine = new kakao.maps.Polyline({
//   map: map, // 선을 표시할 지도입니다
//   path: [], // 선을 구성하는 좌표 배열입니다 클릭한 위치를 넣어줍니다
//   strokeWeight: 3, // 선의 두께입니다
//   strokeColor: "#FF4444", // 선의 색깔입니다
//   strokeOpacity: 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
//   strokeStyle: "solid", // 선의 스타일입니다
// });

// let distance = 0; // 여행 경로의 총 길이를 담는 배열

// // index page 로딩시 검색 지역 설정
// let serviceKey =
//   "VNGBCIqs44uwn8xMbhrGD%2Fl5VwKzSG7YANxv%2FkCnYxQN4V%2BRmi67NfwG3UFMcEDXlrC36F7r1v5rR0xvQw3y7w%3D%3D";
// let areaUrl = `https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=${serviceKey}&numOfRows=40&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json`;
// let regionName;

// // TODO: area url to db
// fetch(areaUrl)
//   .then((response) => response.json())
//   .then((data) => makeOption(data));

// let regionMap = {
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

// // 관광 지역 만들기
// function makeOption(data) {
//   //    console.log(data);
//   // 각 지역에 대한 배열
//   let areas = data.response.body.items.item;
//   let sel = document.getElementById("search-area");
//   areas.forEach(function (area) {
//     let opt = document.createElement("option"); // option 엘리먼트 생성
//     opt.setAttribute("value", area.code);
//     opt.appendChild(document.createTextNode(area.name));
//     sel.appendChild(opt);
//   });
// }

// function areaOnclickHandler(e) {
//   areaCode = e.value;
//   let regionUrl = `https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=${serviceKey}&numOfRows=40&pageNo=1&MobileOS=ETC&MobileApp=AppTest&areaCode=${areaCode}&_type=json`;

//   // TODO: regionUrl to db
//   fetch(regionUrl)
//     .then((response) => response.json())
//     .then((data) => makeRegionKeyword(data));
// }

// function makeRegionKeyword(data) {
//   let regions = data.response.body.items.item;
//   let sel = document.getElementById("search-region");
//   let query = `<option value ="0" selected> 전체 </option>`;
//   //    console.log(regions);
//   regions.forEach(function (region) {
//     query += `<option value=${region.name}> ${region.name}</option>`;
//   });
//   sel.innerHTML = query;
// }

// function regionOnclickHandler(e, root) {
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
// }

// function makeMarker(data) {
//   let items = data;
//   if (!items) return;
//   let pos = [];
//   for (let i = 0; i <= 40; i++) {
//     pos[i] = [];
//   }

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
// }

// function closeInfo(first, second) {
//   infoWindows[first][second].setMap(null);
// }

// function addTrack2(contentid, title, firstimage, mapx, mapy) {
//   let myTrackList = document.getElementById("track-list");
//   if (!myTrackList) {
//     return;
//   }
//   let inputHidden = document.createElement("input");
//   inputHidden.type = "hidden";
//   inputHidden.classList.add("list-item-contentid");
//   inputHidden.value = contentid;

//   let inputHiddenFirst = document.createElement("input");
//   inputHiddenFirst.type = "hidden";
//   inputHiddenFirst.classList.add("list-item-mapx");
//   inputHiddenFirst.value = mapx;

//   let inputHiddenSecond = document.createElement("input");
//   inputHiddenSecond.type = "hidden";
//   inputHiddenSecond.classList.add("list-item-mapy");
//   inputHiddenSecond.value = mapy;

//   let inputHiddenTitle = document.createElement("input");
//   inputHiddenTitle.type = "hidden";
//   inputHiddenTitle.classList.add("list-item-title");
//   inputHiddenTitle.value = title;

//   let inputHiddenImage = document.createElement("input");
//   inputHiddenImage.type = "hidden";
//   inputHiddenImage.classList.add("list-item-image");
//   inputHiddenImage.value = firstimage;

//   let contentLi = document.createElement("li");
//   contentLi.classList.add("item");
//   contentLi.draggable = true;

//   let detailsDiv = document.createElement("div");
//   detailsDiv.classList.add("details");

//   let imageTag = document.createElement("img");
//   imageTag.src = firstimage;

//   let titleSpan = document.createElement("span");
//   titleSpan.textContent = title;

//   detailsDiv.appendChild(imageTag);
//   detailsDiv.appendChild(titleSpan);
//   detailsDiv.append(inputHidden);
//   detailsDiv.append(inputHiddenFirst);
//   detailsDiv.append(inputHiddenSecond);
//   detailsDiv.append(inputHiddenImage);
//   detailsDiv.append(inputHiddenTitle);

//   let dragIcon = document.createElement("i");
//   dragIcon.classList.add("uil", "uil-draggabledots");

//   contentLi.appendChild(detailsDiv);
//   contentLi.appendChild(dragIcon);

//   myTrackList.appendChild(contentLi);

//   let position = new kakao.maps.LatLng(mapy, mapx);

//   // 경로 표현 배열에 넣기
//   path = clickLine.getPath();
//   path.push(position);
//   clickLine.setPath(path);
//   distance = Math.round(clickLine.getLength());
// }

// function contentidOnclickHandler(e) {
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
// }

// function makeTripList(data) {
//   let trips = data.response.body.items.item;
//   let tripContent = ``;
//   trips.forEach(function (trip) {
//     tripContent += `<tr>
//         <td><img src ="${trip.firstimage}" width="100px"></td>
//         <td>${trip.title}</td>
//     <td>${trip.addr1}${trip.addr2}</td>
//     <td>${trip.mapy}</td>
//     <td>${trip.mapx}</td>
//     </tr>`;
//   });
//   document.getElementById("trip-list").innerHTML = tripContent;
// }

// function createMarkerImage(src, size, options) {
//   var markerImage = new kakao.maps.MarkerImage(src, size, options);
//   return markerImage;
// }

// // 좌표와 마커이미지를 받아 마커를 생성하여 리턴하는 함수입니다
// function createMarker(position, image) {
//   var marker = new kakao.maps.Marker({
//     position: position,
//     image: image,
//   });
//   return marker;
// }

// function searchByKeyword() {
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
// }

// function checkEnter(event) {
//   if (event.keyCode == 13) {
//     searchByKeyword();
//   }
// }

// function clearPath() {
//   let path = clickLine.getPath();
//   path = [];
//   clickLine.setPath(path);

//   let myTrackList = document.getElementById("track-list");
//   if (!myTrackList) {
//     return false;
//   }
//   myTrackList.replaceChildren();
//   return true;
// }

// function changePath(contentid, title, images, mapx, mapy) {
//   if (!clearPath()) {
//     return;
//   }
//   let N = Math.min(mapx.length, 16);

//   function getDist(pointA, pointB) {
//     return Math.abs(mapx[pointA] - mapx[pointB]) + Math.abs(mapy[pointA] - mapy[pointB]);
//   }

//   function TPS() {
//     let adj = new Array(N);
//     for (let i = 0; i < N; i++) {
//       adj[i] = new Array(N);
//     }

//     let cached = new Array(N);
//     let cached2 = new Array(N);
//     for (let i = 0; i < N; i++) {
//       cached[i] = new Array(1 << N);
//       cached[i].fill(0.0);
//       cached2[i] = new Array(1 << N);
//       cached2[i].fill(0);
//     }

//     for (let i = 0; i < N; i++) {
//       for (let j = 0; j < N; j++) {
//         if (i == j) {
//           adj[i][j] = 0.0;
//         } else {
//           adj[i][j] = getDist(i, j);
//         }
//       }
//     }

//     const MAXVAL = 100000.0;
//     function dfs(visited, idx, start) {
//       if (visited == (1 << N) - 1) {
//         if (adj[idx][start] != 0) {
//           return adj[idx][start];
//         }
//         return MAXVAL;
//       }
//       if (cached[idx][visited] == 0.0) {
//         cached[idx][visited] = MAXVAL;
//         for (let k = 0; k < N; k++) {
//           if (adj[idx][k] != 0 && (visited & (1 << k)) == 0) {
//             let minDist = dfs(visited | (1 << k), k, start) + adj[idx][k];
//             if (cached[idx][visited] > minDist) {
//               cached[idx][visited] = minDist;
//               cached2[idx][visited] = k;
//             }
//           }
//         }
//       }
//       return cached[idx][visited];
//     }

//     addTrack2(contentid[0], title[0], images[0], mapx[0], mapy[0]);

//     function tracking(visited, idx) {
//       if (visited == (1 << N) - 1) {
//         return;
//       }
//       let k = cached2[idx][visited];
//       console.log(k);
//       addTrack2(contentid[k], title[k], images[k], mapx[k], mapy[k]);
//       tracking(visited | (1 << k), k);
//     }

//     console.log(dfs(1 << 0, 0, 0));
//     tracking(1 << 0, 0);
//   }
//   TPS();
// }
