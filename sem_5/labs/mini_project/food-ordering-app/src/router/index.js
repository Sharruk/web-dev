import { createRouter, createWebHistory } from "vue-router";

import Home from "../views/Home.vue";
import FoodMenu from "../views/FoodMenu.vue";
import Cart from "../views/Cart.vue";
import About from "../views/About.vue";
import Order from "../components/Order.vue";

const router = createRouter({
  history: createWebHistory(),

  routes: [
    {
      path: "/",
      component: Home,
    },
    {
      path: "/order",
      component: Order,
      children: [
        {
          path: "menu",
          component: FoodMenu,
        },
        {
          path: "cart",
          component: Cart,
        },
      ],
    },
    {
      path: "/about",
      component: About,
    },
  ],
});

export default router;