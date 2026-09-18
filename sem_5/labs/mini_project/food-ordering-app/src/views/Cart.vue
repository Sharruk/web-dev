<template>
  <div class="cart">
    <h2>Your Cart</h2>

    <p v-if="cartStore.cart.length === 0">
      Your cart is empty.
    </p>

    <div
      v-for="item in cartStore.cart"
      :key="item.id"
      class="cart-item"
    >
      <h3>{{ item.name }}</h3>

      <p>Price: ₹{{ item.price }}</p>

      <p>Quantity: {{ item.quantity }}</p>

      <button @click="cartStore.decreaseQuantity(item.id)">
        -
      </button>

      <button @click="cartStore.increaseQuantity(item.id)">
        +
      </button>

      <button @click="cartStore.removeFromCart(item.id)">
        Remove
      </button>

      <p>
        Item Total: ₹{{ item.price * item.quantity }}
      </p>
    </div>

    <div v-if="cartStore.cart.length > 0" class="summary">
      <h3>Total Items: {{ cartStore.totalItems }}</h3>

      <h3>Total Bill: ₹{{ cartStore.totalBill }}</h3>

      <button @click="cartStore.clearCart">
        Clear Cart
      </button>
    </div>

    <br />

    <router-link to="/order/menu">
      <button>Back to Menu</button>
    </router-link>
  </div>
</template>

<script setup>
import { useCartStore } from "../stores/cart";

const cartStore = useCartStore();
</script>

<style scoped>
.cart {
  text-align: center;
  padding: 20px;
}

.cart-item {
  border: 1px solid #ccc;
  border-radius: 8px;
  padding: 15px;
  margin: 15px auto;
  max-width: 350px;
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

.summary {
  margin-top: 25px;
  font-weight: bold;
}
</style>