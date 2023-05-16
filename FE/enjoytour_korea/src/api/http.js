import axios from "axios";

export default axios.create({
  baseURL: "http://localhost/EnjoyTour_Korea",
  headers: {
    "Content-Type": "application/json",
  },
});
