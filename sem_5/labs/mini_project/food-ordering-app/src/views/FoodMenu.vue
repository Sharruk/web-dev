<template>
  <div class="menu">
    <h2>Food Menu</h2>

    <div
      v-for="food in foods"
      :key="food.id"
      class="food-card"
    >
      <h3>{{ food.name }}</h3>

      <p>Price: ₹{{ food.price }}</p>

      <button @click="cartStore.addToCart(food)">
        Add to Cart
      </button>
    </div>

    <p class="items">
      Items in Cart: {{ cartStore.totalItems }}
    </p>

    <router-link to="/order/cart">
      <button>Go to Cart</button>
    </router-link>
  </div>
</template>

<script setup>
import { useCartStore } from "../stores/cart";

const cartStore = useCartStore();

const foods = [
  {
    id: 1,
    name: "Pizza",
    price: 250,
  },
  {
    id: 2,
    name: "Burger",
    price: 150,
  },
  {
    id: 3,
    name: "Pasta",
    price: 200,
  },
  {
    id: 4,
    name: "Coke",
    price: 80,
  },
];
</script>

<style scoped>
.menu {
  text-align: center;
  padding: 20px;
}

.food-card {
  border: 1px solid #ccc;
  border-radius: 8px;
  padding: 15px;
  margin: 15px auto;
  max-width: 300px;
}

button {
  padding: 8px 15px;
  margin: 5px;
  background-color: #42b883;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.items {
  font-weight: bold;
  margin: 20px;
}
</style>