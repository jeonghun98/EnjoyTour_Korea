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

export { writePlan };
