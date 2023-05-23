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

function getHotplace(hotplaceNo, success, fail){
  // console.log("getHotplace - axios start");
  api.get(`/hotplace/${hotplaceNo}`).then(success).catch(fail);
}

function getImageHotplace(sfolder, ofile, sfile, success, fail){
  console.log("getImageHotplace - axios start");
  console.log("getImageHotplace - image data", sfolder, ofile, sfile);
  
  api.get(`/hotplace/display/${sfolder}/${ofile}/${sfile}`, {responseType: 'blob'}).then(success).catch(fail);
}

function modifyHotplace(formData, hotplaceNo, success, fail) {
  api({
    method: "PUT",
    url: `/hotplace/${hotplaceNo}`,
    headers: { "Content-Type": "multipart/form-data" },
    data: formData,
  }).then(success).catch(fail);
}

function deleteHotplace(hotplaceNo, success, fail) {
  api.delete(`/hotplace/${hotplaceNo}`).then(success).catch(fail);
}

export { listHotplace, writeHotplace, getHotplace, getImageHotplace, modifyHotplace, deleteHotplace };