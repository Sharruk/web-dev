import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Order from '../views/Order.vue'
import FoodMenu from '../views/FoodMenu.vue'
import Cart from '../views/Cart.vue'
import About from '../views/About.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: Home
  },
  {
    path: '/order',
    name: 'order',
    component: Order,
    // Nested (child) routes - both render inside Order.vue's <router-view>
    children: [
      {
        path: 'menu', // -> /order/menu
        name: 'food-menu',
        component: FoodMenu
      },
      {
        path: 'cart', // -> /order/cart
        name: 'cart',
        component: Cart
      }
    ]
  },
  {
    path: '/about',
    name: 'about',
    component: About
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
