import { apiInstance } from "./index.js";

const api = apiInstance();

function listHotplace(success, fail){
  // console.log("listHotplace - axios start");
  api.get(`/hotplace`).then(success).catch(fail);
  // console.log("listHotplace- axios stop")
}

function writeHotplace(hotplace, success, fail){
  // console.log("writeHotplace - axios start");
  api.post(`/hotplace`, JSON.stringify(hotplace)).then(success).catch(fail);
  // console.log("writeHotplace- axios stop")
}

export { listHotplace, writeHotplace };