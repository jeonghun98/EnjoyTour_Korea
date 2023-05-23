import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  console.log("member.js login-",user)
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${userid}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  api.defaults.headers["refresh-token"] = sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
  await api.post(`/user/refresh`, user).then(success).catch(fail);
}

async function logout(userid, success, fail) {
  await api.get(`/user/logout/${userid}`).then(success).catch(fail);
}

async function join(user, success, fail){
  await api.post(`/user/join`, JSON.stringify(user)).then(success).catch(fail);
}

async function checkId(userId, success, fail) {
  await api.get(`/user/idcheck/${userId}`).then(success).catch(fail);
}

async function update(user, success, fail) {
  api.defaults.headers["refresh-token"] = sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
  await api.put(`/user/update`, user).then(success).catch(fail);
}

async function deleteUser(userid, success, fail) {
  api.defaults.headers["refresh-token"] = sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
  await api.delete(`/user/delete/${userid}`).then(success).catch(fail);
}

async function pwdFind(userid, useremail, success, fail) {
  await api.get(`/user/pwdfind/${userid}/${useremail}`).then(success).catch(fail);
}

export { login, findById, tokenRegeneration, logout, join, checkId, update, deleteUser, pwdFind };
