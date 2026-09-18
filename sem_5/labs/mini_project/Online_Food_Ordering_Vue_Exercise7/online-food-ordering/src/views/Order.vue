<template>
  <section class="page order-page">
    <h1>Order Food</h1>

    <!-- Sub-navigation for the nested routes: /order/menu and /order/cart -->
    <nav class="sub-nav">
      <RouterLink to="/order/menu" class="sub-nav-link">Food Menu</RouterLink>
      <RouterLink to="/order/cart" class="sub-nav-link">
        Cart
        <span v-if="cart.totalItems > 0" class="cart-badge">{{ cart.totalItems }}</span>
      </RouterLink>
    </nav>

    <!-- This is where the nested child route (FoodMenu.vue or Cart.vue)
         is actually rendered - this demonstrates nested routing. -->
    <div class="order-outlet">
      <RouterView />
    </div>
  </section>
</template>

<script setup>
import { useCartStore } from '../stores/cartStore'

// Used only to show the live item count badge next to "Cart" in the sub-nav.
const cart = useCartStore()
</script>
