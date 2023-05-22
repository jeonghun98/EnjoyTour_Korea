<template>
  <div>
    <!-- map start -->
    <!-- <div class="col-lg-12 col-md-10 col-sm-12 mt-3 rounded">
      <div id="map"></div>
    </div> -->
    <!-- map end -->
    <hotplace-input-item type= "write"/>
  </div>
</template>

<script>
import HotplaceInputItem from './item/HotplaceInputItem.vue';

export default {
  name: "HotplaceWrite",
  components: {
    HotplaceInputItem,
  },
  data() {
    return {
      message: "",
    };
  },
  created() {},
  methods: {
    
  },
};
</script>

<style scoped>
/* #map {
  width: 100%; 
  height: 30rem; 
  background-color: lightgray;
} */
</style>
