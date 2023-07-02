<template>
  <span>
    <b-img-lazy id="imgItem" :src="hotplaceImg" alt="Image" class="m-3"></b-img-lazy>
  <!-- {{originalFile}} -->
  </span>
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
    fileInfo: Array,
  },
  data() {
    return {
      message: '',
      hotplaceImg: "",
    };
  },
  created() {
    console.log("ImgItem - props: ", this.fileInfo);
    console.log("ImgItem - props: ", this.originalFile, this.saveFile, this.saveFolder);
    if(this.originalFile != null){
      // console.log("HotplaceImgItem.vue - 이미지 파일 있음");

      let sfolder = this.saveFolder;
      let ofile = this.originalFile;
      let sfile = this.saveFile;
      console.log(sfolder, ofile, sfile);

      getImageHotplace(
        sfolder, ofile, sfile,
        ({ data }) => {
          // console.log(this.hotplaceImg);
          const url = window.URL.createObjectURL(data);
          this.hotplaceImg = url;
            
        },
        (error) => {
          console.log(error);
        }
      );

    }else{
      // console.log("HotplaceImgItem - 이미지 파일 없음");
      this.hotplaceImg = require('../../../assets/img/ssafy_logo.png');
      // console.log(this.hotplaceImg);
    }

  },
  methods: {},
};
</script>

<style scoped>
#imgItem {
  width: 25rem;
  height: 15rem;
  /* object-fit: cover; */
}
</style>