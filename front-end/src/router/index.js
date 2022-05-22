import { createRouter, createWebHistory } from "vue-router";
import Home from "../views/Home.vue";
import Detail from "../views/Detail.vue";
import Booking from "../views/Booking.vue"
import EventCategory from "../views/EventCategory.vue"
const history = createWebHistory('/ssi3/');
// const history = createWebHistory();
const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/event-detail/:bookingId",
    name: "Detail",
    component: Detail,
  },
  {
    path: "/booking/",
    name: "Booking",
    component: Booking,
  },
  {
    path: "/event-category/",
    name: "EventCategory",
    component: EventCategory,
  },
];

const router = createRouter({ history, routes });
export default router;
