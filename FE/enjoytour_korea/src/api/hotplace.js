import { apiInstance } from "./index.js";

const api = apiInstance();

function listHotplace(success, fail){
  api.get(`/hotplace`).then(success).catch(fail);
}

function writeHotplace(formData, success, fail){
  api({
    method: "POST",
    url: `/hotplace`,
    headers: { "Content-Type": "multipart/form-data" },
    data: formData,
  }).then(success).catch(fail);
}

function getHotplace(hotplaceNo, success, fail){
  api.get(`/hotplace/${hotplaceNo}`).then(success).catch(fail);
}

function getImageHotplace(sfolder, ofile, sfile, success, fail){
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