<template>
  <b-row class="mt-3">
    <b-col>
      <div id="kakaomap"></div>
    </b-col>
    <b-col>
      <form
        id="form-register"
        method="POST"
        action=""
        enctype="multipart/form-data"
        :file-name-formatter="formatNames"
        @submit="onSubmit"
        @reset="onReset"
      >
        <!-- <input type="hidden" name="action" value="write" /> -->
        <div class="mt-3 text-danger fw-bold">스마트폰으로 찍은 사진을 올려주세요</div>
        <div class="mb-3 mt-1">
          <b-form-file
            multiple
            type="file"
            class="form-control"
            id="file"
            name="fileInfos[]"
            @change="setDetailImage"
            accept=".gif, .jpg, .png"
            v-model="hotplace.fileInfos"
          />
          <div id="images_container"></div>
        </div>
        <div>
          <b-img id="previewImg" :src="img" v-for="(img, index) in previewImg" :key="index"></b-img>
        </div>

        <div class="mb-3 mt-3">
          <label for="title" class="form-label">핫플이름</label>
          <input
            type="text"
            class="form-control"
            id="title"
            name="title"
            placeholder="핫플이름.."
            v-model="hotplace.title"
            ref="title"
          />
        </div>
        <div class="mb-3">
          <label for="date" class="form-label">다녀온 날짜</label>
          <input
            type="date"
            class="form-control"
            id="date"
            name="date"
            v-model="hotplace.date"
            ref="date"
          />
        </div>
        <div class="mb-3">
          <label for="content" class="form-label">핫플 상세설명</label>
          <textarea
            class="form-control"
            id="content"
            name="content"
            rows="7"
            v-model="hotplace.content"
            ref="content"
          ></textarea>
        </div>
        <div class="col-auto text-center">
          <button
            type="submit"
            id="btn-inform-reg"
            class="btn mb-3 mr-3 btn-outline-success"
            v-if="this.type === 'write'"
          >
            등록
          </button>
          <button
            type="submit"
            id="btn-inform-modify"
            class="btn mb-3 mr-3 btn-outline-success"
            v-if="this.type === 'modify'"
          >
            수정
          </button>
          <button
            type="button"
            id="btn-move-list"
            class="btn btn-outline-primary mb-3 mr-3"
            @click="moveListHotplace"
          >
            목록
          </button>
          <b-button type="reset" variant="outline-danger" class="btn mb-3">초기화</b-button>
        </div>
      </form>
    </b-col>
  </b-row>
</template>

<script>
import { writeHotplace, getHotplace, getImageHotplace, modifyHotplace } from "@/api/hotplace";
import { mapState } from "vuex";
// import { getExif } from "@/assets/js/metadata.js";
import moment from "moment";
import EXIF from "exif-js";

const memberStore = "memberStore";

export default {
  name: "HotplaceInputItem",
  components: {},
  props: {
    type: { type: String },
  },
  data() {
    return {
      map: null,
      hotplace: {
        hotplaceNo: 0,
        userId: "",
        img: "",
        title: "",
        date: "",
        content: "",
        latitude: "",
        longitude: "",
        fileInfos: [],
      },
      previewImg: [],
      isUserid: false,
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    this.hotplace.userId = this.userInfo.userid;

    if (this.type === "modify") {
      let param = this.$route.params.hotplaceNo;
      getHotplace(
        param,
        ({ data }) => {
          this.hotplace = data;
          // this.previewImg = this.hotplace.fileInfos;
          console.log("HotplaceInputItemdata -", data);
          // console.log(this.hotplace.fileInfos);
          this.marker();
          if (this.hotplace.fileInfos != null) {
            // console.log("HotplaceListItem - 이미지 파일 있음");

            for (var file of this.hotplace.fileInfos) {
              let sfolder = file.saveFolder;
              let ofile = file.originalFile;
              let sfile = file.saveFile;
              console.log(file);
              console.log(sfolder, ofile, sfile);

              getImageHotplace(
                sfolder,
                ofile,
                sfile,
                ({ data }) => {
                  // console.log(data);
                  // this.hotplaceImg = require(data);
                  // console.log(this.hotplaceImg);
                  const url = window.URL.createObjectURL(data);
                  // console.log(url);
                  this.previewImg.push(url);
                },
                (error) => {
                  console.log(error);
                }
              );
            }
          }
        },
        (error) => {
          console.log(error);
        }
      );
    }

    console.log("created:", this.hotplace);
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      this.loadScript();
    }
  },
  methods: {
    initMap() {
      const container = document.getElementById("kakaomap");
      const options = {
        center: new kakao.maps.LatLng(37.5013068, 127.0396597),
        level: 5,
      };

      this.map = new kakao.maps.Map(container, options);
      this.map.relayout();

      // geolocation을 사용할 수 있는지 확인
      if (this.type === "write") {
        if (navigator.geolocation) {
          navigator.geolocation.getCurrentPosition((position) => {
            this.hotplace.latitude = position.coords.latitude; // 위도, 경도
            this.hotplace.longitude = position.coords.longitude;
            this.map.panTo(new kakao.maps.LatLng(this.hotplace.latitude, this.hotplace.longitude));
          });
        }
      }
    },
    loadScript() {
      const script = document.createElement("script");
      /* global kakao */
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=" +
        process.env.VUE_APP_KAKAO_MAP_API_KEY +
        "&autoload=false&libraries=services";
      document.head.appendChild(script);
      script.onload = () => kakao.maps.load(this.initMap);
    },
    setDetailImage(event) {
      let check = false;
      for (var image of event.target.files) {
        // console.log("image!!!!!!1", image);
        // console.log("image!!!!!!1", image.lastModifiedDate);
        // this.hotplace.date = this.dateFormat(image.lastModifiedDate);
        let url = URL.createObjectURL(image);
        console.log(url);
        this.previewImg.push(url);
        if (!check) {
          this.findMetaData(image);
          check = true;
        }
      }
      // if (!check) {
      //   if (confirm("사진의 위치가 없습니다. 현재 사용자의 위치로 저장할까요?")) {
      //     if (navigator.geolocation) {
      //       navigator.geolocation.getCurrentPosition((position) => {
      //         this.hotplace.latitude = position.coords.latitude; // 위도, 경도
      //         this.hotplace.longitude = position.coords.longitude;
      //         this.map.panTo(
      //           new kakao.maps.LatLng(this.hotplace.latitude, this.hotplace.longitude)
      //         );
      //       });
      //     }
      //   } else {
      //     //todo -> 위치 입력 받기
      //   }
      // }
    },
    findMetaData(img1) {
      EXIF.getData(img1, () => {
        var DateTime = EXIF.getTag(img1, "DateTime").split(" ");
        var time = DateTime[0].split(":");
        this.hotplace.date = time[0] + "-" + time[1] + "-" + time[2];
        console.log("DateTime", this.hotplace.date);

        var exifLong = EXIF.getTag(img1, "GPSLongitude");
        var exifLat = EXIF.getTag(img1, "GPSLatitude");
        var exifLongRef = EXIF.getTag(img1, "GPSLongitudeRef");
        var exifLatRef = EXIF.getTag(img1, "GPSLatitudeRef");
        //계산식 적용이유는 해당라이브러리가 위도경도를 도분초 단위로 출력하기 때문
        var latitude;
        var longitude;
        if (exifLatRef == "S") {
          latitude = exifLat[0] * -1 + (exifLat[1] * -60 + exifLat[2] * -1) / 3600;
        } else {
          latitude = exifLat[0] + (exifLat[1] * 60 + exifLat[2]) / 3600;
        }
        if (exifLongRef == "W") {
          longitude = exifLong[0] * -1 + (exifLong[1] * -60 + exifLong[2] * -1) / 3600;
        } else {
          longitude = exifLong[0] + (exifLong[1] * 60 + exifLong[2]) / 3600;
        }
        console.log(latitude, longitude);
        this.hotplace.latitude = latitude;
        this.hotplace.longitude = longitude;
        // console.log(latitude, longitude);

        var container = document.getElementById("kakaomap");
        var options = {
          center: new kakao.maps.LatLng(this.hotplace.latitude, this.hotplace.longitude), //wtmX, wtmY 받아옴
          level: 5,
        };

        var map = new kakao.maps.Map(container, options);

        // 마커가 표시될 위치입니다
        var markerPosition = new kakao.maps.LatLng(this.hotplace.latitude, this.hotplace.longitude);

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
          position: markerPosition,
        });

        // 마커가 지도 위에 표시되도록 설정합니다
        marker.setMap(map);
      });
    },
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.hotplace.title &&
        ((msg = "제목 입력해주세요"), (err = false), this.$refs["title"].focus());
      err &&
        !this.hotplace.content &&
        ((msg = "내용 입력해주세요"), (err = false), this.$refs["content"].focus());
      err &&
        !this.hotplace.date &&
        ((msg = "날짜 입력해주세요"), (err = false), this.$refs["date"].focus());

      console.log("onSubmit - type:", this.type);
      if (!err) alert(msg);
      else this.type === "write" ? this.registHotplace() : this.modifyHotplace();
    },
    onReset(event) {
      event.preventDefault();
      this.hotplace.hotplaceNo = 0;
      this.hotplace.title = "";
      this.hotplace.content = "";
      // this.hotplace.img = "";
      this.hotplace.date = "";
      this.hotplace.fileInfos = [];
      this.hotplace.latitude = null;
      this.hotplace.longitude = null;
      this.moveListHotplace();
    },
    registHotplace() {
      const formData = new FormData();
      formData.append("hotplaceNo", this.hotplace.hotplaceNo);
      formData.append("userId", this.hotplace.userId);
      formData.append("title", this.hotplace.title);
      formData.append("content", this.hotplace.content);
      formData.append("date", this.hotplace.date);
      formData.append("latitude", this.hotplace.latitude);
      formData.append("longitude", this.hotplace.longitude);

      for (let i = 0; i < this.hotplace.fileInfos.length; i += 1) {
        const file = this.hotplace.fileInfos[i];
        formData.append(`thumbNail`, file);
      }

      writeHotplace(
        formData,
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
            // console.log("registHotplace - writeHotplace: 성공");
          }
          alert(msg);
          this.moveListHotplace();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    modifyHotplace() {
      // alert("핫플레이스 수정");
      console.log(this.hotplace);

      const formData = new FormData();
      formData.append("hotplaceNo", this.hotplace.hotplaceNo);
      formData.append("userId", this.hotplace.userId);
      formData.append("title", this.hotplace.title);
      formData.append("content", this.hotplace.content);
      formData.append("date", this.hotplace.date);
      formData.append("latitude", this.hotplace.latitude);
      formData.append("longitude", this.hotplace.longitude);

      for (let i = 0; i < this.hotplace.fileInfos.length; i += 1) {
        const file = this.hotplace.fileInfos[i];
        formData.append(`thumbNail`, file);
      }

      modifyHotplace(
        formData,
        this.hotplace.hotplaceNo,
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
            // console.log("registHotplace - writeHotplace: 성공");
          }
          alert(msg);
          this.moveListHotplace();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveListHotplace() {
      // alert("핫플레이스 목록 이동");
      this.$router.push({ name: "hotplaceList" });
    },
    formatNames(files) {
      return files.length === 1 ? files[0].name : `${files.length} files selected`;
    },
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("yyyy-MM-DD");
    },
    marker() {
      var container = document.getElementById("kakaomap");
      var options = {
        center: new kakao.maps.LatLng(this.hotplace.latitude, this.hotplace.longitude), //wtmX, wtmY 받아옴
        level: 5,
      };

      var map = new kakao.maps.Map(container, options);

      // 마커가 표시될 위치입니다
      var markerPosition = new kakao.maps.LatLng(this.hotplace.latitude, this.hotplace.longitude);

      // 마커를 생성합니다
      var marker = new kakao.maps.Marker({
        position: markerPosition,
      });

      // 마커가 지도 위에 표시되도록 설정합니다
      marker.setMap(map);
    },
  },
};
</script>

<style scoped>
#map {
  width: 100%;
  height: 30rem;
  background-color: lightgray;
}
#previewImg {
  width: 100%;
  height: 15rem;
}
</style>
