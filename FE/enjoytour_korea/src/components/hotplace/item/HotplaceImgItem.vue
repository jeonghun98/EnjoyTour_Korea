<template>
  <b-img-lazy id="imgItem" :src="hotplaceImg" alt="Image"></b-img-lazy>
</template>

<script>
import { getImageHotplace } from '@/api/hotplace';

export default {
  name: 'HotplaceImgItem',
  components: {},
  props:{
    originalFile: String,
    saveFile: String,
    saveFolder: String,
  },
  data() {
    return {
      message: '',
      hotplaceImg: "",
    };
  },
  created() {
    // console.log("HotplaceListItem : ", this.hotplaceNo);
    if(this.fileInfos != null){
      // console.log("HotplaceListItem - 이미지 파일 있음");

      let sfolder = this.fileInfos[0].saveFolder;
      let ofile = this.fileInfos[0].originalFile;
      let sfile = this.fileInfos[0].saveFile;

      getImageHotplace(
        sfolder, ofile, sfile,
        ({ data }) => {
          // console.log(data);
          // this.hotplaceImg = require(data);
          // console.log(this.hotplaceImg);
          const url = window.URL.createObjectURL(data);
          this.hotplaceImg = url;
            
        },
        (error) => {
          console.log(error);
        }
      );

    }else{
      console.log("HotplaceImgItem - 이미지 파일 없음");

      this.hotplaceImg = require('../../../assets/img/ssafy_logo.png');
      // console.log(this.hotplaceImg);
    }
  },
  methods: {},
};
</script>

<style scoped>
#imgItem {
  width: 600;
  height: 400;
}
</style>