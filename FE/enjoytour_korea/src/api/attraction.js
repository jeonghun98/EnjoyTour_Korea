import { apiInstance } from "./index.js";

const api = apiInstance();

function AttractionList(param, success, fail) {
  api.get(`/attraction/attractionList`, { params: param }).then(success).catch(fail);
}

function AttractionSearchList(param, success, fail) {
  api.get(`/attraction/attractionSearchList`, { params: param }).then(success).catch(fail);
}

function Attraction(param, success, fail) {
  api.get(`/attraction`, { params: param }).then(success).catch(fail);
}

function AttractionRandomList(success, fail) {
  api.get(`/attraction/attractionRandomList`).then(success).catch(fail);
}

export { AttractionList, AttractionSearchList, Attraction, AttractionRandomList};
