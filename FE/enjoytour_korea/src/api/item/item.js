import { apiInstance } from "../index.js";

const api = apiInstance();

function sidoList(success, fail) {
  api.get(`/attraction/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/attraction/gugun`, { params: params }).then(success).catch(fail);
}

export { sidoList, gugunList };
