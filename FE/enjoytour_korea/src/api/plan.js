import { apiInstance } from "./index.js";

const api = apiInstance();

function writePlan(planList, success, fail) {
  console.log("api", planList);
  api({
    method: "POST",
    url: `/plan`,
    data: planList,
  })
    .then(success)
    .catch(fail);
}

function listPlan(param, success, fail) {
  api.get(`/plan`, { params: param }).then(success).catch(fail);
}

function mylistPlan(param, success, fail) {
  api.get(`/plan/myplan/${param.userId}`).then(success).catch(fail);
}

function getPlan(planNo, success, fail) {
  api.get(`/plan/${planNo}`).then(success).catch(fail);
}

function authAdd(planList, success, fail) {
  console.log("authAdd",planList)
  api.post(`/plan/addAuth`, JSON.stringify(planList)).then(success).catch(fail);
}

function modifyPlan(planList, success, fail) {
  api.put(`/plan`, JSON.stringify(planList)).then(success).catch(fail);
}

function deletePlan(planNo, success, fail) {
  api.delete(`/plan/${planNo}`).then(success).catch(fail);
}

export { mylistPlan, writePlan, listPlan, getPlan, modifyPlan, deletePlan, authAdd};
