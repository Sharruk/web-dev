<template>
  <div class="food-menu-page">
    <div class="menu-header">
      <h2>Available Food</h2>
      <p class="cart-count-note">Cart: {{ cart.totalItems }} item(s)</p>
    </div>

    <div class="food-list">
      <FoodItem
        v-for="item in foodItems"
        :key="item.id"
        :id="item.id"
        :food="item.name"
        :price="item.price"
        @add-to-cart="handleAddToCart"
      />
    </div>
  </div>
</template>

<script setup>
import { useCartStore } from '../stores/cartStore'
import FoodItem from '../components/FoodItem.vue'

const cart = useCartStore()

// Menu items
const foodItems = [
  { id: 1, name: 'Pizza', price: 250 },
  { id: 2, name: 'Burger', price: 150 },
  { id: 3, name: 'Pasta', price: 200 },
  { id: 4, name: 'Coke', price: 80 }
]

function handleAddToCart(food) {
  cart.addToCart(food)
}
</script>
