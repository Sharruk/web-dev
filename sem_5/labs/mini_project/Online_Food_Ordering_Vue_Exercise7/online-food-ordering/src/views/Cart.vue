<template>
  <div class="cart-page">
    <h2>Cart ({{ cart.totalItems }} items)</h2>

    <!-- Empty cart state -->
    <div v-if="cart.items.length === 0" class="empty-cart">
      <p>Your cart is empty.</p>
      <RouterLink to="/order/menu" class="btn btn-primary">
        Go to Food Menu
      </RouterLink>
    </div>

    <!-- Cart with items -->
    <div v-else>
      <div class="cart-list">
        <div v-for="item in cart.items" :key="item.id" class="cart-item">
          <div class="cart-item-info">
            <span class="cart-item-name">{{ item.name }}</span>
            <span class="cart-item-unit-price">Rs. {{ item.price }} each</span>
          </div>

          <div class="cart-item-controls">
            <button class="btn btn-qty" @click="cart.decreaseQuantity(item.id)">-</button>
            <span class="qty-value">{{ item.quantity }}</span>
            <button class="btn btn-qty" @click="cart.increaseQuantity(item.id)">+</button>
          </div>

          <div class="cart-item-subtotal">
            Rs. {{ item.price * item.quantity }}
          </div>

          <button class="btn btn-remove" @click="cart.removeFromCart(item.id)">
            Remove
          </button>
        </div>
      </div>

      <div class="cart-summary">
        <p class="cart-total">Total: Rs. {{ cart.totalBill }}</p>
        <button class="btn btn-danger" @click="cart.clearCart()">
          Clear Cart
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useCartStore } from '../stores/cartStore'

const cart = useCartStore()
</script>
