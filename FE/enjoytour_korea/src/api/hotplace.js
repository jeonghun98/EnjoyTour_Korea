import { apiInstance } from "./index.js";

const api = apiInstance();

function listHotplace(success, fail){
  console.log("listHotplace - axios start");
  api.get(`/hotplace`).then(success).catch(fail);
  console.log("listHotplace- axios stop")
}

export { listHotplace };