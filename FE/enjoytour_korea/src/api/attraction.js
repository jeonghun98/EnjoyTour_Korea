import { apiInstance } from "./index.js";

const api = apiInstance();

function searchByLoc(param, success, fail) {
  api.post(`/attraction/searchByLoc`, { params: param }).then(success).catch(fail);
}

export { searchByLoc };
