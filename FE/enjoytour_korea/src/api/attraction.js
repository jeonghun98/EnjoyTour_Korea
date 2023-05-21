import { apiInstance } from "./index.js";

const api = apiInstance();

function AttractionList(param, success, fail) {
  api.get(`/attraction/attractionList`, { params: param }).then(success).catch(fail);
}

function Attraction(param, success, fail) {
  api.get(`/attraction`, { params: param }).then(success).catch(fail);
}

export { AttractionList,  Attraction};
