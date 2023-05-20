import { apiInstance } from "./index.js";

const api = apiInstance();

function listHotplace(success, fail){
  console.log("listHotplace - axios start");
  api.get(`/hotplace`).then(success).catch(fail);
  console.log("listHotplace- axios stop")
}

function writeHotplace(formData, success, fail){
  // console.log("writeHotplace - axios start");

  api({
    method: "POST",
    url: `/hotplace`,
    headers: { "Content-Type": "multipart/form-data" },
    data: formData,
  }).then(success).catch(fail);
}

export { listHotplace, writeHotplace };