import { apiInstance } from "./index.js";

const api = apiInstance();

function listArticle(param, success, fail) {
  api.get(`/notice`, { params: param }).then(success).catch(fail);
}

function writeArticle(article, success, fail) {
  api.post(`/notice`, JSON.stringify(article)).then(success).catch(fail);
}

function getArticle(noticeNo, success, fail) {
  api.get(`/notice/${noticeNo}`).then(success).catch(fail);
}

function modifyArticle(article, success, fail) {
  api.put(`/notice`, JSON.stringify(article)).then(success).catch(fail);
}

function deleteArticle(noticeNo, success, fail) {
  api.delete(`/notice/${noticeNo}`).then(success).catch(fail);
}

export { listArticle, writeArticle, getArticle, modifyArticle, deleteArticle };
